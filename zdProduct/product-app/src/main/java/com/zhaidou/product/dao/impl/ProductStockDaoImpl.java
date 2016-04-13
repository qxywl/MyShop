package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.dataSource.DatabaseContextHolder;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductStockDao;
import com.zhaidou.product.model.base.ProductStockModel;

@Repository("productStockDao")
public class ProductStockDaoImpl  extends BaseDao  implements ProductStockDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public <T> T queryOne(T keyId) throws Exception {
		
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_TWO);
		T aa= (T) getSqlSession().selectOne(getNameSpace() + ".queryOne", null);
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_ONE);
		
		return aa;
	}
    
	
    public ProductStockModel queryBySkuCode(ProductStockModel queryModel){
    	DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_TWO);
    	ProductStockModel resultModel= (ProductStockModel) getSqlSession().selectOne(getNameSpace() + ".queryBySkuCode", queryModel);
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_ONE);
		return resultModel;
    }

    
	@Override
	public List<ProductStockModel> queryBySkuCodeList(
			ProductStockModel queryModel) throws Exception {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_TWO);
    	List<ProductStockModel> resultModel=  getSqlSession().selectList(getNameSpace() + ".queryBySkuCodeList", queryModel);
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_ONE);
		
		return resultModel;
		
	}

	
}
