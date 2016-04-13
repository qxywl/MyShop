package com.zhaidou.product.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.imageservice.model.request.GetImagePathRequestPO;
import com.zhaidou.imageservice.model.response.GetImageResponsePO;
import com.zhaidou.product.model.ProductImageModel;

public class ProductUtil {
    public static final Log logger = LogFactory.getLog(ProductUtil.class);
    
    public static Map<String,Object> map = new HashMap<String, Object>();
    
    public static String dateString = "yyyy-MM-dd hh:mm:ss";
    
    public static String dateLongToString(Long dateLong,String format){
        if(String.valueOf(dateLong).length()<=10){
            dateLong = dateLong*1000;
        }
        return DatetimeUtil.longToDateTimeString(dateLong, format);
    };
    
    public static String getStringToUtf(String str){
        if(str!=null){
            str = new String(str.getBytes(Charset.forName("ISO-8859-1")),Charset.forName("UTF-8"));
        }
        return str;
    }
    
    /**
     * 生成编码
     *
     * @param codeHeader
     * @return
     */
    public static String newOrdercode(String codeHeader) {
        String[] code=new String[]{"A","B","C","D","E","F","G","H","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","1","2","3","4","5","6","7","8","9","0"};
        String rtn=codeHeader+code[DatetimeUtil.getYear()-2013]+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+code[DatetimeUtil.getHour()]+DatetimeUtil.getMinAndSec()+(int)(Math.random()*10000);
        return rtn;
    }
    
    /**
     * 实施获取图片 单个SKU
     */
    public static List<ProductImageModel> imageList(String skuCode,ImageSearchManager imageSearchManager){
        RequestObject reqeustObject = new RequestObject();
        GetImagePathRequestPO requestPo = new GetImagePathRequestPO();
        requestPo.setRelationCode(skuCode);
        requestPo.setRelationType(1l);
        reqeustObject.setRequestParams(requestPo);
        
        List<ProductImageModel> imageList = new ArrayList<ProductImageModel>();
        ProductImageModel productImageModel = null;
        ResponseObject responseObject = imageSearchManager.getImagePath(reqeustObject);
        if(responseObject.getCode()==0){
            List<GetImageResponsePO> responsePo = (List<GetImageResponsePO>)responseObject.getData();
            if(responsePo!=null && responsePo.size()>0){
                for(GetImageResponsePO imagePo: responsePo){
                    productImageModel = new ProductImageModel();
                    productImageModel.setImage(imagePo.getImagePath()+"/"+imagePo.getImageName()+imagePo.getImageType());
                    productImageModel.setLevel(imagePo.getImageIndex());
                    imageList.add(productImageModel);
                }
            }
        }else{
            logger.error("获取SKU编号为："+skuCode+" 图片失败");
            throw new ZhaidouRuntimeException("图片获取异常");
        }
        return imageList;
    }
}

