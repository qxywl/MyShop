package com.zhaidou.product.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ProductSkuDao;
import com.zhaidou.product.model.ProductPriceModel;
import com.zhaidou.product.service.ProductSkuService;

@Service("productSkuService")
public class ProductSkuServiceImpl implements ProductSkuService {
    
    @Resource
    private ProductSkuDao productSkuDao;
    
    /**
     * 根据商品编号 获取 所有 SKU 以及价格
     *
     * @param listStr
     * @throws Exception
     */
    @Override
    public ProductPriceModel getSkuPriceByProductCode(String productCode) throws Exception {
        
        return productSkuDao.getSkuPriceByProductCode(productCode);
    }

}
