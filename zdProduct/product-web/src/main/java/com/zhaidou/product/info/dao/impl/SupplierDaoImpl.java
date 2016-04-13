package com.zhaidou.product.info.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.SupplierDao;
import com.zhaidou.product.info.model.SupplierModel;

@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDao implements SupplierDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    /**
     * 查询供应商code是否存在
     */
	@Override
	public SupplierModel getSupplierByCode(Integer supplierCode) {
		SupplierModel supplierModel = null;
		supplierModel = getSqlSession().selectOne(getNameSpace() + ".querySupplierByCode", supplierCode);
		return supplierModel;
	}
	
    
}
