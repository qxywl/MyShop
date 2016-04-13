package com.zhaidou.product.service;

import com.zhaidou.product.model.ProductPriceModel;

public interface ProductSkuService {
    
    /**
     * 根据商品编号 获取 所有 SKU 以及价格
     *
     * @param listStr
     * @throws Exception
     */
    public ProductPriceModel getSkuPriceByProductCode(String productCode) throws Exception;
}
