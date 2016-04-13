package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.SupplierShopDao;
import com.zhaidou.product.model.base.SupplierShopModel;

@Repository("supplierShopDao")
public class SupplierShopDaoImpl  extends BaseDao  implements SupplierShopDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public SupplierShopModel getSupplierById(SupplierShopModel supplierModel)
			throws Exception {
		SupplierShopModel result = null;
		result = getSqlSession().selectOne(getNameSpace() + ".queryOneById", supplierModel);
		return result;
	}

	@Override
	public List<SupplierShopModel> getSupplierByIdList(
			SupplierShopModel supplierShopModel) throws Exception {
		List<SupplierShopModel> result = null;
		result = getSqlSession().selectList(getNameSpace() + ".queryByIdList", supplierShopModel);
		return result;
	}

	
}
