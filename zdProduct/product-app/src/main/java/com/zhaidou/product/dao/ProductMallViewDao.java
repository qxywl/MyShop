package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.ProductMallViewModel;

public interface ProductMallViewDao  extends IDao{
	
	
	
	/**
	 * 通过SKU来获取商品
	 * @param skuCode 
	 * @param type 类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
	 * @return ProductMallViewModel
	 * */
	public ProductMallViewModel getProductMallViewBySkuCode(String skuCode,String type) throws Exception;
	
	
	/**
	 * 通过SKU集合来获取商品集合
	 * @param skuList SKU 集合
	 * @param type 业务类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
	 * @return List<ProductMallViewModel>
	 * */
	public List<ProductMallViewModel> getProductMallViewBySKU(List<String> skuList,String type) throws Exception;
	
	
	/**
     * 通过productId 获取鲜花信息
     * @param productCode 商品编号
     * @param type 业务类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getProductMallViewByProductId(Long productId ,String type) throws Exception;
	
    
	/**
     * 通过productCode 获取鲜花信息
     * @param productCode 商品编号
     * @param type 业务类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getProductMallViewByProductCode(String productCode ,String type) throws Exception;
    
    
    /**
     * 通过productId List 获取鲜花信息
     * @param queryModel model 集合
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public List<ProductMallViewModel> getProductMallViewByProductIdList(ProductMallViewModel queryModel) throws Exception;
    
    
    /**
     * 通过productCode List 获取鲜花信息
     * @param queryModel model 集合
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public List<ProductMallViewModel> getProductMallViewByProductCodeList(
			ProductMallViewModel queryModel) throws Exception;
    
    
    /**
     * 通过productCode 获取商品的上下架信息
     * @param productCode  商品code
     * @return Long 
     * @throws Exception
     * */
    public Long getProductShelvesFiledByProductCode(String productCode) throws Exception;


	
    
}
