package com.zhaidou.product.service;

import com.zhaidou.product.model.ProductToErpLogModel;

/**
 * 推送商品到ERP错误日志
 * @author google
 *
 */
public interface ProductToErpLogService {
	/**
	 * 添加日志
	 */
	public void addProductToErpLog(ProductToErpLogModel productToErpLogModel)throws Exception;
}
