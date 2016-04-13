package com.zhaidou.product.info.service;

import com.zhaidou.product.info.model.ProductJobTimeModel;

public interface ProductJobTimeService {

	/**
	 * 获取最新job记录
	 * @param productJobTimeModel
	 * @return
	 * @throws Exception
	 */
	public ProductJobTimeModel queryOne(ProductJobTimeModel productJobTimeModel) throws Exception;
	
	/**
	 * 插入增量库存表以及job记录
	 * @param startDate
	 * @param endDate
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String addPurchaseStockAndJobTime(ProductJobTimeModel newProductJobTimeModel, String startDate, String endDate, Long userId)throws Exception;
}
