package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductToErpLogDao;

/**
 * 推送商品到ERP错误日志
 * @author google
 *
 */
@Repository("productToErpLogDao")
public class ProductToErpLogDaoImpl extends BaseDao implements ProductToErpLogDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

}
