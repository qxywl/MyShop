package com.zhaidou.product.service;

import java.util.Map;

import com.zhaidou.product.model.ProductSkuModel;

public interface ProductSkuSupplierService {
    
    public void updateStatusBySkuCode(ProductSkuModel productSkuModel)throws Exception;
    
    public void updateStatusList(Map<String, Object> map) throws Exception;
}
