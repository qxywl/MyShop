package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.common.util.Page;
import com.zhaidou.product.model.ProductRefundGoodsModel;

public interface ProductRefundGoodsService {
	
    /**
     * 列表查询
     * 
     * @param productPurchaseStock 业务实体
     * @param page
     * @return List<ProductPurchaseStockDo> 查询结果
     */
    List<ProductRefundGoodsModel> pageList(ProductRefundGoodsModel productRefundGoodsModel, Page page) throws Exception;

    /**
     * 批量插入数据
     * @param productPurchaseStocks
     */
    void insertProductRefundGoodsList(List<ProductRefundGoodsModel> productRefundGoodsModels);

    /**
     * 根据采购入库id查询数据
     * @param purchaseId
     * @return
     */
    long countListPage(Long purchaseId) throws Exception;

    /**
     * 批量更改数据
     * @param productPurchaseStocks
     */
    void updateList(List<ProductRefundGoodsModel> productRefundGoodsModels);
}
