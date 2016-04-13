/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonSyntaxException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HttpClientUtil;
import com.zhaidou.framework.util.string.StringUtil;
import com.zhaidou.product.model.BaseModel;
import com.zhaidou.product.model.mall.MallCategory;
import com.zhaidou.product.po.ProductCategoryPO;
import com.zhaidou.product.service.ProductCategoryService;
import com.zhaidou.product.util.APIConfig;
import com.zhaidou.product.util.ProductConstantUtil;

/**
 * TODO liq: Change to the actual description of this class
 * 
 * @version Revision History
 * 
 *          <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-12     Created
 * 
 * </pre>
 * @since 1.
 */
@Service("productCategoryServiceV001")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private Logger logger = Logger.getLogger(ProductCategoryServiceImpl.class);
    /*
     * (non-Javadoc)
     * 
     * @see com.teshehui.product.service.ProductCategoryService#getMallProductCategoryList(java.lang.String)
     */
    @Override
    public ResponseObject getMallProductCategoryList(String parentCategoryCode) {
        ResponseObject responseObject=new ResponseObject();
        List<ProductCategoryPO> categorys = new ArrayList<ProductCategoryPO>();
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (!StringUtil.isEmpty(parentCategoryCode)) {
                params.put("parent_id", parentCategoryCode);
            }
            //获取php商城类别数据
            String sourStr = HttpClientUtil.sendHttpRequest(APIConfig.mallCategoryUrl, params);
            logger.debug(APIConfig.mallCategoryUrl+"==="+sourStr);
            Gson gson = new Gson();
            BaseModel<List<MallCategory>> phpRtn=gson.fromJson(sourStr, new TypeToken<BaseModel<List<MallCategory>>>(){}.getType());
            //判断php接口是否正确返回数据
            if (phpRtn != null) {
                if(phpRtn.getStatus()==200){
                    for (int i = 0; i < phpRtn.getData().size(); i++) {
                        ProductCategoryPO pcate = new ProductCategoryPO();
                        pcate.setCategoryId(phpRtn.getData().get(i).getCate_id());
                        pcate.setCategoryCode(phpRtn.getData().get(i).getCate_id().toString());
                        pcate.setCategoryName(phpRtn.getData().get(i).getCate_name());
                        pcate.setCategoryEname(phpRtn.getData().get(i).getCate_name());
                        pcate.setCategoryParentCode(phpRtn.getData().get(i).getParent_id().toString());
                        pcate.setCategorySortNum(Integer.parseInt(phpRtn.getData().get(i).getCate_id().toString()));
                        pcate.setCategoryComment(phpRtn.getData().get(i).getBrief());
                        pcate.setCategorySmallImageUrl(phpRtn.getData().get(i).getGcategory_logo());
                        pcate.setCategoryMidImageUrl(phpRtn.getData().get(i).getThumbnail_small());
                        pcate.setCategoryLargeImageUrl(phpRtn.getData().get(i).getThumbnail_middle());
                        categorys.add(pcate);
                    }
                    responseObject.setCode(0);
                    responseObject.setMsg("Success");
                    responseObject.setData(categorys);
                }else{
                    responseObject.setCode(1);
                    responseObject.setErrorCode(ProductConstantUtil.BIZ_MALL_CATEGORY_DATA_ERROR_CODE001);
                    responseObject.setMsg("php接口请求错误");
                    responseObject.setData(null);
                }
            }else{
//                throw new TeshehuiRuntimeException("20101001","未从php端获取到数据",null);
                responseObject.setCode(1);
                responseObject.setErrorCode(ProductConstantUtil.BIZ_MALL_CATEGORY_DATA_ERROR_CODE002);
                responseObject.setMsg("未从php端获取到数据");
                responseObject.setData(null);
            }
        } catch (JsonSyntaxException e) {
//            throw new TeshehuiRuntimeException("20101002","json解析错误",e);
            responseObject.setCode(1);
            responseObject.setErrorCode(ProductConstantUtil.BIZ_MALL_CATEGORY_DATA_ERROR_CODE003);
            responseObject.setMsg("json解析错误");
            responseObject.setData(e.toString());
        } catch (NumberFormatException e) {
//            throw new TeshehuiRuntimeException("20101003","数字转换错误",e);
            responseObject.setCode(1);
            responseObject.setErrorCode(ProductConstantUtil.BIZ_MALL_CATEGORY_DATA_ERROR_CODE004);
            responseObject.setMsg("数字转换错误");
            responseObject.setData(e.toString());
        }
        return responseObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.teshehui.product.service.ProductCategoryService#getFlowerProductCategoryList(java.lang.String)
     */
    @Override
    public ResponseObject getFlowerProductCategoryList(String parentCategoryCode) {
        ResponseObject responseObject=new ResponseObject();
        return responseObject;
    }

    public static void main(String[] args) {
        ProductCategoryServiceImpl p = new ProductCategoryServiceImpl();
        String parentCategoryCode="1";
        p.getFlowerProductCategoryList(parentCategoryCode);
    }
}
