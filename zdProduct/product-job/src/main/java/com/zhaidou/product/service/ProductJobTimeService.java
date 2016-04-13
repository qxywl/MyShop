package com.zhaidou.product.service;

import com.zhaidou.product.model.ProductJobTimeModel;

public interface ProductJobTimeService {

	/**
	 * 获取最新job记录
	 * @param productJobTimeModel
	 * @return
	 * @throws Exception
	 */
	public ProductJobTimeModel queryOne(ProductJobTimeModel productJobTimeModel) throws Exception;
	
	/**
	 * 插入一条job记录
	 * @throws Exception
	 */
	public void insertErpJobRecord(ProductJobTimeModel productJobTimeModel)throws Exception;
}
