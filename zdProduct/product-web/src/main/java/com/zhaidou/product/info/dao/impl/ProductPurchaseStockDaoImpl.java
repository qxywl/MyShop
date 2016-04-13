package com.zhaidou.product.info.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductPurchaseStockDao;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;

@Repository("productPurchaseStockDao")
public class ProductPurchaseStockDaoImpl extends BaseDao implements ProductPurchaseStockDao{

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<ProductPurchaseStockModel> getpurchaseStockListByStatus() {
		List<ProductPurchaseStockModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryListByStatus", null);
		return list;
	}

	@Override
	public void updateAssignStatusAndAssignBy(Map<String, Object> map) {
		getSqlSession().update(getNameSpace() + ".updateStatus", map);
	}
	
	
}
