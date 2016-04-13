package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductInfoDao;
import com.zhaidou.product.model.base.ProductInfoModel;
import com.zhaidou.product.model.base.SupplierModel;

@Repository("productInfoDao")
public class ProductInfoDaoImpl  extends BaseDao  implements ProductInfoDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public ProductInfoModel getProInfoByProductId(
			ProductInfoModel productInfoModel) throws Exception {
		ProductInfoModel result = null;
		result = getSqlSession().selectOne(getNameSpace() + ".queryOneByProductId", productInfoModel);
		return result;
	}

	
}
