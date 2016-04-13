package com.zhaidou.product.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.FilterMountDao;
import com.zhaidou.product.category.model.FilterMountPO;

@Repository("filterMountDao")
public class FilterMountDaoImpl extends BaseDao implements FilterMountDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public Integer deleteByCategoryId(FilterMountPO po) throws DaoException {
		try {
			return getSqlSession().update(getNameSpace() + ".deleteByCategoryId",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public Integer deleteByCateCodeAndAttrCode(FilterMountPO po)
			throws DaoException {
		try {
			return getSqlSession().update(getNameSpace() + ".deleteByCateCodeAndAttrCode",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public Integer updateOrderByCateCodeAndAttrCode(FilterMountPO po)
			throws DaoException {
		try {
			return getSqlSession().update(getNameSpace() + ".updateOrderByCateCodeAndAttrCode",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<FilterMountPO> queryListByCategoryId(FilterMountPO po)
			throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryListByCategoryId",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
