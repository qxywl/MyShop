package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.SupplierDao;
import com.zhaidou.product.model.base.SupplierModel;

@Repository("supplierDao")
public class SupplierDaoImpl  extends BaseDao  implements SupplierDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public SupplierModel getSupplierById(SupplierModel supplierModel)
			throws Exception {
		SupplierModel result = null;
		result = getSqlSession().selectOne(getNameSpace() + ".queryOneById", supplierModel);
		return result;
	}

	
}
