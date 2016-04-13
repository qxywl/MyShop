/* 
 * @(#) ProductPurchaseStockQueryBiz.java 2015-12-03
 * Copyright (c) 2015 hdxw Technologies, Inc. All rights reserved.
 */
package com.zhaidou.product.service;


import com.zhaidou.common.util.Page;
import com.zhaidou.product.model.ProductPurchaseStock;

import java.util.List;

/**
 * 查询接口
 *
 * @author donnie
 * @version 1.0.0
 *
 */
public interface ProductPurchaseStockService {
    
	/**
     * 根据主键查找
     *  
     * @param id 主键
     * @return ProductPurchaseStock 业务实体
     */
    ProductPurchaseStock find(Long id) throws Exception;

    /**
     * 列表查询
     * 
     * @param productPurchaseStock 业务实体
     * @param page
     * @return List<ProductPurchaseStockDo> 查询结果
     */
    List<ProductPurchaseStock> pageList(ProductPurchaseStock productPurchaseStock, Page page) throws Exception;

    /**
     * 批量插入数据
     * @param productPurchaseStocks
     */
    void insertProductPurchaseStockList(List<ProductPurchaseStock> productPurchaseStocks);

    /**
     * 根据采购入库id查询数据
     * @param purchaseId
     * @return
     */
    long countListPage(Long purchaseId) ;

    /**
     * 批量更改数据
     * @param productPurchaseStocks
     */
    void updateList(List<ProductPurchaseStock> productPurchaseStocks);
}
