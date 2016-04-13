package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;

public interface ProductManager {
    
    /**
     * 获取商品详情
     *
     * @param productModel
     * @return
     */
    public Map<String,Object> getProductInfo(ProductModel productModel);
    /**
     * 获取商品列表
     *
     * @param productModel
     * @return
     */
    public List<ProductModel> getProductList(Page page,ProductModel productModel);
    /**
     * 获取商品
     *
     * @param productModel
     * @return
     */
    public Map<String,Object> getProductById(ProductModel productModel);
    /**
     * 新增商品
     *
     * @param productModel
     * @return
     */
    public String addProduct(String thumbnailFileName,ProductModel productModel,Map<String,Object> map,String[] fileNames);
    /**
     * 删除商品
     *
     * @param productModel
     * @return
     */
    public Boolean deleteProduct(ProductModel productModel);
    /**
     * 修改商品
     *
     * @param productModel
     * @return
     */
    public void updateProduct(ProductModel productModel,List<ProductSkuModel> listUpdate,List<ProductSkuModel> listInsert,Map<String,Object> map,String[] fileNames,String thumbnailFileName);
    
    /**
     * 获取 品牌
     */
    public List<ProductBrandModel> getBrand(ProductBrandModel productBrandModel,Page page);
    
    /**
     * 商品 审核 操作
     *
     * @param productModel
     * @return
     */
//    public String productExamineOperation(ProductModel productModel);
    
    /**
     * 获取 所有颜色 尺码
     *
     * @return
     */
    public Map<String,Object> getColorAndSize();
    
    public Integer getProductCode(ProductModel productModel) throws DaoException;
    
    public ProductModel getProductByCode(String productCode)throws Exception;
    
    public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel,Page page) throws Exception;

    public Long exportPrdouctCount(ProductSkuExportModel productSkuExportModel) throws Exception ;
	
    public List<ProductModel> getProductListAndMarkUpRate(Page page,
			ProductModel productModel)throws Exception;
}
