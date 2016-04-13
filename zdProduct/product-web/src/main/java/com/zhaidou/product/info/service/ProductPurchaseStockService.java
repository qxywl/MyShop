package com.zhaidou.product.info.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;

public interface ProductPurchaseStockService {

	/**
	 * 获取采购库列表
	 * @param productPurchaseStockModel
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ProductPurchaseStockModel> purchaseStockList(ProductPurchaseStockModel productPurchaseStockModel, Page page)throws Exception;
	
	/**
	 * 分配公共库
	 * @param ids
	 * @return
	 */
	public boolean purchaseStockDistribuTion(String[] ids, String userName);
	
	/**
	 * 获取所有列表
	 * @return
	 */
	public List<ProductPurchaseStockModel> getpurchaseStockListByStatus();
	
	/**
	 * 修改导出数据状态以及操作人
	 * @param status
	 * @param userName
	 * @return
	 */
	public void updateAssignStatusAndAssignBy(Map<String, Object> map);
	
	public long countListPage(Long purchaseId)throws ZhaidouRuntimeException;
	
	/**
	 * 插入一条记录
	 * @param productPurchaseStocks
	 */
	public void insertProductPurchaseStockList(List<ProductPurchaseStockModel> productPurchaseStockModel);
}
