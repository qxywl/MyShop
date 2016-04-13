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
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.ProductStockManager;
import com.zhaidou.product.po.request.RequestMallProductStockPO;
import com.zhaidou.product.service.ProductStockService;
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

@Service("productStockManager")
public class ProductStockMangerImpl implements ProductStockManager {
    private Logger logger = Logger.getLogger(ProductStockMangerImpl.class);

    @Resource(name = "productStockServiceMap")
    private Map<String, ProductStockService> storesStockMap;

    /* (non-Javadoc)
     * @see com.teshehui.product.manager.ProductStockManager#getProductStock(com.teshehui.framework.model.RequestObject)
     */
    @Override
    public ResponseObject getProductStock(RequestObject request) {
        ResponseObject result = null;
        try {
            String businessType = request.getBusinessType();
            Object data = null;
            ProductStockService stockService = storesStockMap.get(request.getVersion());
            // 根据业务类型调用不同服务
             if (ProductConstantUtil.BIZ_PRODUCT_MALL_CODE.equals(businessType)) {
                RequestMallProductStockPO po = (RequestMallProductStockPO) request.getRequestParams();
                data = stockService.getProductStock((RequestMallProductStockPO)po);
            } else {
                result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL,
                        ProductConstantUtil.BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE, "业务类型无法匹配：" + businessType,
                        null);
            }

            if (data != null) {
                result = new ResponseObject(ProductConstantUtil.HANDEL_SUCCESS, null, null, data);
            }
        } catch (Exception e) {
            logger.error("getProductStock illegal," + e.getMessage(), e);
            ZhaidouRuntimeException te;
            if (e instanceof ZhaidouRuntimeException) {
                te = (ZhaidouRuntimeException) e;
            } else {
                te = new ZhaidouRuntimeException(ProductConstantUtil.BIZ_REQUEST_VERSION_ERROR_CODE001,"请求参数版本号或业务线错误:v="+request.getVersion()+" b="+request.getBusinessType(),e);

//                te = new TeshehuiRuntimeException(ProductConstantUtil.BIZ_REQUEST_PARAM_CONVERT_ERROR_CODE,"请求对象转换失败",e);
            }
            result = new ResponseObject(ProductConstantUtil.HANDEL_FAIL, te.getCode(), te.getMessage(), null);
        }
        return result;
    }

}
