package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.base.ProductCountModel;


/**
 * @author wwei
 * @date 2015-05-12
 * */

public interface ProductCountService {
	
	/**
	 * 添加统计
	 * @param productCountModel 
	 * @return null
	 * */
	public void addProductCount(ProductCountModel productCountModel) throws Exception;
	
	
	/**
	 * 更新统计
	 * @param productCountModel 
	 * @return null
	 * */
	public void updateProductCount(ProductCountModel productCountModel) throws Exception;
	
	
	/**
	 * 通过productId 获取ProductCountModel 模型
	 * @param productId  商品ID
	 * @return ProductCountModel 模型集合
	 * @throws Exception
	 * */
	public ProductCountModel getProductCountByProdId(Long productId) throws Exception;
	
	
	/**
	 * 通过productId List 集合获取productCount 结合
	 * 
	 * @param List<Long> productIdList  商品ID集合
	 * @return List<ProductCountModel> 模型集合
	 * @throws Exception
	 * */
	public List<ProductCountModel> getProCountByProductIdList(List<Long> productIdList)throws Exception;

	
   
}
