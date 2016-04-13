package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.ProductSkuViewModel;
import com.zhaidou.product.model.base.ProductMallViewModel;


/**
 * @author wwei
 * @date 2015-05-12
 * */

public interface ProductSkuViewService {
	

	/**
	 * 通过productId 获取productSkuView List
	 * @param productId  商品ID
	 * @return List<ProductSkuViewModel> 模型集合
	 * @throws Exception
	 * */
	public List<ProductSkuViewModel> getProductSkuViewByProdId(Long productId) throws Exception;
	
	
	/**
	 * 通过sku集合  获取 productSkuView List
	 * @param skuList  sku 集合
	 * @return  List<ProductSkuViewModel>模型集合
	 * @throws Exception
	 * */
	public List<ProductSkuViewModel> getProductSkuViewBySkuList(List<String> skuList) throws Exception;
	
	
	/**
	 * 通过skuId集合  获取 productSkuView List
	 * @param skuIdList  skuId 集合
	 * @return  List<ProductSkuViewModel>模型集合
	 * @throws Exception
	 * */
	public List<ProductSkuViewModel> getProductSkuViewBySkuIdList(List<Long> skuIdList) throws Exception;

   
}
