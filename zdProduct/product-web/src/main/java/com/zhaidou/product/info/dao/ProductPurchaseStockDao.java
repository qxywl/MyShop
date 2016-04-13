package com.zhaidou.product.info.dao;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;

public interface ProductPurchaseStockDao extends IDao{
	
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
}
