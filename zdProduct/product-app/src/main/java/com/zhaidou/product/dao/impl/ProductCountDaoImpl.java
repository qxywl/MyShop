package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductCountDao;
import com.zhaidou.product.model.base.ProductCountModel;
import com.zhaidou.product.model.base.ProductImageModel;

@Repository("productCountDao")
public class ProductCountDaoImpl  extends BaseDao  implements ProductCountDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

    
	@Override
	public List<ProductCountModel> queryByProductIdList(ProductCountModel queryModel) {
		
		List<ProductCountModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryByproductIdList", queryModel);
		return list;
	}


	@Override
	public void updateByProductCode(ProductCountModel updateModel) {
		getSqlSession().update(getNameSpace() + ".queryByproductIdList", updateModel);
	}

	
}
