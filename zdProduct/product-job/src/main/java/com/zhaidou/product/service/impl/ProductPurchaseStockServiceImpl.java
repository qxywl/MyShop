/* 
 * @(#) ProductPurchaseStockQueryBizImpl.java 2015-12-03
 * Copyright (c) 2015 hdxw Technologies, Inc. All rights reserved.
 */
package com.zhaidou.product.service.impl;


import com.zhaidou.common.util.Page;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.product.dao.ProductPurchaseStockDao;
import com.zhaidou.product.model.ProductPurchaseStock;
import com.zhaidou.product.service.ProductPurchaseStockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 查询实现类
 *
 * @author donnie
 * @version 1.0.0
 *
 */
@Service("productPurchaseStockService")
public class ProductPurchaseStockServiceImpl implements ProductPurchaseStockService {

    /** slf4j*/
    private static final Logger  LOGGER = LoggerFactory.getLogger(ProductPurchaseStockServiceImpl.class);

    /** 数据访问层接口*/
    @Autowired
    private ProductPurchaseStockDao productPurchaseStockDao;

	/**
     * 根据主键查找
     *  
     * @param id 主键
     * @return ProductPurchaseStock 业务实体
     */
    public ProductPurchaseStock find(Long id) throws Exception {
        ProductPurchaseStock query = new ProductPurchaseStock();
        query.setId(id);
        ProductPurchaseStock productPurchaseStock = productPurchaseStockDao.queryOne(query);
        return productPurchaseStock;
    }


    public List<ProductPurchaseStock> pageList(ProductPurchaseStock productPurchaseStock,Page page) throws Exception {


        long count = productPurchaseStockDao.countListPage(productPurchaseStock);
        List<ProductPurchaseStock> listProductPurchaseStock = null;
        if (count > 0) {
            page.setTotalCount(count);
            listProductPurchaseStock = productPurchaseStockDao.queryListPage
                    (productPurchaseStock, page.getPageNum(), page.getNumPerPage());
        }

        return listProductPurchaseStock;
    }

    @Override
    public long countListPage(Long purchaseId)  {
        ProductPurchaseStock productPurchaseStock = new ProductPurchaseStock();
        productPurchaseStock.setPurchaseId(purchaseId);
        try {
            return productPurchaseStockDao.countListPage(productPurchaseStock);
        } catch (Exception e) {
            LOGGER.error("query productPurchaseStockDao error",e);
            throw new ZhaidouRuntimeException();
        }
    }

    @Override
    public void insertProductPurchaseStockList(List<ProductPurchaseStock> productPurchaseStocks) {

        for (ProductPurchaseStock productPurchaseStock : productPurchaseStocks) {
            try {
                productPurchaseStockDao.insert(productPurchaseStock);
                LOGGER.info("同步采购入库单成功 success, productPurchaseStock =" + productPurchaseStock);
            } catch (Exception e) {
                LOGGER.error("同步采购入库单异常 faile, productPurchaseStock =" + productPurchaseStock, e);
            }
        }

    }

    @Override
    public void updateList(List<ProductPurchaseStock> productPurchaseStocks) {

        for (ProductPurchaseStock productPurchaseStock : productPurchaseStocks) {
            try {
                productPurchaseStock.setUpdateTime(new Date());
                productPurchaseStockDao.update(productPurchaseStock);
                LOGGER.info("更改同步采购入库单成功 success, productPurchaseStock =" + productPurchaseStock);
            } catch (Exception e) {
                LOGGER.error("更改同步采购入库单异常 faile, productPurchaseStock =" + productPurchaseStock, e);
            }
        }
    }
}
