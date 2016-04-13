package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.ProductStockModel;

public interface ProductStockDao extends IDao{
	
	
	/**
	 * 通过skuCode 获取库存
	 * @param queryModel 做参数传递
	 * @return ProductStockModel
	 * @throws Exception
	 * */
	public ProductStockModel queryBySkuCode(ProductStockModel queryModel) throws Exception;
	
	
	/**
	 * 通过skuCode List 获取库存
	 * @param 
	 * */
	public List<ProductStockModel> queryBySkuCodeList(ProductStockModel queryModel) throws Exception;

}
