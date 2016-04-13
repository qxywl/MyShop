package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductRefundGoodsDao;

@Repository("productRefundGoodsDao")
public class ProductRefundGoodsDaoImpl extends BaseDao implements ProductRefundGoodsDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

}
