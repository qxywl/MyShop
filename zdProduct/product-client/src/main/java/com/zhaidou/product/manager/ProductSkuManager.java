package com.zhaidou.product.manager;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;

public interface ProductSkuManager {
	
	

	/**
	 * 通过productId 获取productSkuView List
	 * @param requestObject 传productID
	 * @return List<ProductSkuViewModel> 模型集合
	 * @throws Exception
	 * */
	public ResponseObject getProductSkuViewByProdId(RequestObject requestObject) ;
	
	
	/**
	 * 通过sku集合  获取 productSkuView List
	 * @param requestObject 传skuList
	 * @return  List<ProductSkuViewModel>模型集合
	 * @throws Exception
	 * */
	public ResponseObject getProductSkuViewBySkuList(RequestObject requestObject) ;	
	
}
