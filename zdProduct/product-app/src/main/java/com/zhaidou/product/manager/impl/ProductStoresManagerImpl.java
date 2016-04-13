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

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.ProductStoresManager;
import com.zhaidou.product.po.request.RequestProductBaseObject;
import com.zhaidou.product.service.ProductStoresService;
import com.zhaidou.product.util.ProductConstantUtil;

/**
 * TODO liq: Change to the actual description of this class
 * 
 * @version Revision History
 * 
 *          <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 * 
 * </pre>
 * @since 1.
 */
@Service("productStoresManager")
public class ProductStoresManagerImpl implements ProductStoresManager {
    private Logger logger = Logger.getLogger(ProductStoresManagerImpl.class);

    @Resource(name = "productStoresServiceMap")
    private Map<String, ProductStoresService> storesServiceMap;

    /*
     * (non-Javadoc)
     * 
     * @see com.teshehui.product.manager.ProductStoresManager#getProductAllStores()
     */
    @Override
    public ResponseObject getProductStoresList(RequestProductBaseObject requestObj) {
        ResponseObject result = null;
        try {
            String businessType = requestObj.getBusinessType();
            Object data = null;
            ProductStoresService storesService = storesServiceMap.get(requestObj.getVersion());
            // 根据业务类型调用不同服务
            if (ProductConstantUtil.BIZ_PRODUCT_AIRFLIGHT_CODE.equals(businessType)) {

            } else if (ProductConstantUtil.BIZ_PRODUCT_FLOWER_CODE.equals(businessType)) {

            } else if (ProductConstantUtil.BIZ_PRODUCT_HOTEL_CODE.equals(businessType)) {

            } else if (ProductConstantUtil.BIZ_PRODUCT_MALL_CODE.equals(businessType)) {
                data = storesService.getProductAllStores();
            } else {
                result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
                        ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE, "业务类型无法匹配：" + businessType,
                        null);
            }

            if (data != null) {
                result = new ResponseObject(ProductConstantUtil.HANDEL_SUCCESS, null, null, data);
            }
        } catch (Exception e) {
            logger.error("getProductStoresList illegal," + e.getMessage(), e);
            ZhaidouRuntimeException te;
            if (e instanceof ZhaidouRuntimeException) {
                te = (ZhaidouRuntimeException) e;
            } else {
                te = new ZhaidouRuntimeException(e);
            }
            result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL, te.getCode(), te.getMessage(), null);
        }
        return result;
    }

}
