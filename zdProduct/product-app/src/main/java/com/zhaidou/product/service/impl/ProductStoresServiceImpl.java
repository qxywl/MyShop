/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResultSetModel;
import com.zhaidou.framework.util.http.HttpClientUtil;
import com.zhaidou.product.model.BaseModel;
import com.zhaidou.product.model.ProductStoresModel;
import com.zhaidou.product.po.ProductStoresPO;
import com.zhaidou.product.service.ProductStoresService;
import com.zhaidou.product.util.APIConfig;
import com.zhaidou.product.util.ProductConstantUtil;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */
@Service("productStoresServiceV001")
public class ProductStoresServiceImpl implements ProductStoresService {
    private Logger logger = Logger.getLogger(ProductStoresServiceImpl.class);

    /* (non-Javadoc)
     * @see com.teshehui.product.service.ProductStoresService#getProductAllStores()
     */
    @Override
    public ResultSetModel<ProductStoresPO> getProductAllStores() {
        ResultSetModel<ProductStoresPO> resultSet=new ResultSetModel<ProductStoresPO>();
        List<ProductStoresPO> storesList=new ArrayList<ProductStoresPO>();
        try {
            String sourStr = HttpClientUtil.sendHttpRequest(APIConfig.productStoresUrl,null);
            logger.debug(sourStr);
//            System.out.println(sourStr);
            Gson gson = new Gson();
            BaseModel<List<ProductStoresModel>> baseModel=gson.fromJson(sourStr, new TypeToken<BaseModel<List<ProductStoresModel>>>(){}.getType());
            if(baseModel!=null){
                if(baseModel.getStatus()==200&&baseModel.getData()!=null){
                    for(int i=0;i<baseModel.getData().size();i++){
                        storesList.add(conversionStores(baseModel.getData().get(i)));
                    }
                    resultSet.setTotalCount(storesList.size());
                    resultSet.setBeginRow(1);
                    resultSet.setItems(storesList);
                }else{
                    logger.error(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE002+"-获取店铺php接口返回错误:"+sourStr);
                    throw new ZhaidouRuntimeException(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE002,"获取店铺php接口返回错误:"+sourStr,null);
                }
            }else{
                logger.error(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE003+"-获取店铺json未解析到数据:"+sourStr);
                throw new ZhaidouRuntimeException(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE003,"获取店铺json未解析到数据:"+sourStr,null);
            }
        } catch (Exception e) {
            logger.error(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE004+"-获取店铺数据错误", e);
            throw new ZhaidouRuntimeException(ProductConstantUtil.BIZ_STORES_LIST_DATA_ERROR_CODE004,"获取店铺数据错误",e);
        }
        return resultSet;
    }
    
    /**
     * 店铺输入输出数据转换
     * @param storesModel
     * @return
     */
    private ProductStoresPO conversionStores(ProductStoresModel storesModel){
        ProductStoresPO storesPO=new ProductStoresPO();
        storesPO.setDescription(storesModel.getDescription());
        storesPO.setImg(storesModel.getImg());
        storesPO.setKeyword(storesModel.getKeyword());
        storesPO.setOwnerName(storesModel.getOwner_name());
        storesPO.setStoreId(storesModel.getStore_id());
        storesPO.setStoreName(storesModel.getStore_name());
        storesPO.setThumb(storesModel.getThumb());
        return storesPO;
    }
    
    public static void main(String[] args) {
        ProductStoresServiceImpl ps=new ProductStoresServiceImpl();
        ps.getProductAllStores();
    }
}
