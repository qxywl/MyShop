package com.zhaidou.product.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ProductSkuSupplierDao;
import com.zhaidou.product.model.ProductSkuModel;
import com.zhaidou.product.service.ProductSkuSupplierService;

@Service("productSkuSupplierService")
public class ProductSkuSupplierServiceImpl implements ProductSkuSupplierService {
    
    @Resource
    private ProductSkuSupplierDao productSkuSupplierDao;
    
    @Override
    public void updateStatusBySkuCode(ProductSkuModel productSkuModel) throws Exception {
        
        productSkuSupplierDao.update(productSkuModel);
    }

    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        productSkuSupplierDao.updateStatusList(map);
    }

}
