package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductViewDao;
import com.zhaidou.product.model.ProductModel;

@Repository("productViewDao")
public class ProductViewDaoImpl extends BaseDao implements ProductViewDao {
    
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public void deleteSkuViewByProductCodes(List<String> codes) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".deleteSkuViewByProductCodes", codes);
    }

    @Override
    public void deleteMallViewByProductCodes(List<String> codes) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".deleteMallViewByProductCodes", codes);
    }

    @Override
    public void addSkuViewList(List<String> codes) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".addSkuViewList", codes);
    }

    @Override
    public void addMallViewList(List<String> codes) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".addMallViewList", codes);
    }

    @Override
    public void updateMallViewPrice(ProductModel productModel) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateMallViewPrice", productModel);
    }

	@Override
	public void updateMallViewZeroType(List<String> codes) throws Exception {
		  getSqlSession().delete(this.getNameSpace()+".updateMallViewZeroType", codes);
	}
    
}
