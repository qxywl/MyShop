package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductJobTimeDao;

@Repository("productJobTimeDao")
public class ProductJobTimeDaoImpl extends BaseDao implements ProductJobTimeDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

}
