/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.manager.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.ProductCategoryManager;
import com.zhaidou.product.po.request.RequestProductBaseObject;
import com.zhaidou.product.po.request.RequestProductCategoryPO;
import com.zhaidou.product.service.ProductCategoryService;
import com.zhaidou.product.util.ProductConstantUtil;

/**商品类别管理
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-12     Created
 *
 * </pre>
 * @since 1.
 */
@Service("productCategoryManager")
public class ProductCategoryManagerImpl implements ProductCategoryManager {
    private Logger logger = Logger.getLogger(ProductCategoryManagerImpl.class);

    @Resource(name = "productCategoryServiceMap")
    private Map<String, ProductCategoryService> productCategoryServiceMap;
    /* (non-Javadoc)
     * @see com.teshehui.product.manager.ProductCategoryManager#getProductCategoryList(java.lang.String)
     */
    @Override
    public ResponseObject getProductCategoryList(RequestObject requestObj) {
    	
    	RequestProductCategoryPO reqProCatePO =(RequestProductCategoryPO)requestObj.getRequestParams();
    	String parentCategoryCode = reqProCatePO.getParentCategoryCode(); 
        ProductCategoryService cateService=productCategoryServiceMap.get(requestObj.getVersion());
        
        logger.debug("clientType:"+requestObj.getClientType()+";businessType:"+requestObj.getBusinessType()+";ves:"+requestObj.getVersion());
        
        if(requestObj.getBusinessType().equals(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE)){
            return cateService.getMallProductCategoryList(parentCategoryCode);
        }else if(requestObj.getBusinessType().equals(ProductConstantUtil.BIZ_PRODUCT_FLOWER_CODE)){
            return cateService.getFlowerProductCategoryList(parentCategoryCode);
        }else{
            return new ResponseObject(ProductConstantUtil.HANDEL_FAIL,ProductConstantUtil.BIZ_CATEGORY_ILLEGAL_ERROR_CODE001,"请求参数不全或错误（酒店和机票无类别查询）",null);
        }
    }

}
