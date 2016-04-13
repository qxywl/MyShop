/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.base.ProductStockModel;
import com.zhaidou.product.po.mall.MallProductStockPO;
import com.zhaidou.product.po.request.RequestMallProductStockPO;

/**商品可售数量服务
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductStockService {
    /**
     * 商城商品库存查询
     * @param request
     * @return
     */
    public MallProductStockPO getProductStock(RequestMallProductStockPO request);
    
    
    /**
     * 本地库存，通过skuCode 获取库存
     * 
     * */
    public ProductStockModel queryBySkuCode(String skuCode) throws Exception;
    
    
    /**
	 * 通过skuCodeList 获取库存
	 * @param  skuCodeList sku集合
	 * @param List<ProductStockModel>
	 * */
	public List<ProductStockModel> queryBySkuCodeList(List<String> skuCodeList) throws Exception;
}
