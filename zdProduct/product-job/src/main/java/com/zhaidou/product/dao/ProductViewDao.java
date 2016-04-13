package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.product.model.ProductModel;

public interface ProductViewDao {
    
    /**
     * 根据商品编号 删除SKU横表
     *
     * @param codes
     * @throws Exception
     */
    public void deleteSkuViewByProductCodes(List<String> codes)throws Exception;
    
    /**
     * 根据商品编号 删除MALL横表
     *
     * @param codes
     * @throws Exception
     */
    public void deleteMallViewByProductCodes(List<String> codes)throws Exception;
    
    /**
     * 根据商品编号 插入SKU横表
     *
     * @param codes
     * @throws Exception
     */
    public void addSkuViewList(List<String> codes)throws Exception;
    
    /**
     * 根据商品编号 插入MALL横表
     *
     * @param codes
     * @throws Exception
     */
    public void addMallViewList(List<String> codes)throws Exception;
    
    /**
     * 根据商品编号 插入MALL横表
     *
     * @param codes
     * @throws Exception
     */
    public void updateMallViewZeroType(List<String> codes)throws Exception;
    
    public void updateMallViewPrice(ProductModel productModel)throws Exception;
}
