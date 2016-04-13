package com.zhaidou.product.info.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.SupplierModel;

public interface SupplierDao extends IDao{
    	
	public SupplierModel getSupplierByCode(Integer supplierCode);
}
