package com.zhaidou.product.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductMallViewDao;
import com.zhaidou.product.model.base.ProductCountModel;
import com.zhaidou.product.model.base.ProductMallViewModel;

@Repository("productMallViewDao")
public class ProductMallViewDaoImpl  extends BaseDao  implements ProductMallViewDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public List<ProductMallViewModel> getProductMallViewBySKU (
			List<String> skuList,String type) throws Exception{
		
		List<ProductMallViewModel> list = null;
		Map<String,Object> map = new HashMap<String,Object>();
		if(skuList != null && skuList.size() > 0 ){
			map.put("skuList", skuList);
		}
		map.put("type", type);
		list = getSqlSession().selectList(getNameSpace()+".selectListBySkuList", map);
		return list;
	}

	
	@Override
	public ProductMallViewModel getProductMallViewBySkuCode(String skuCode,String type)
			throws Exception {
		ProductMallViewModel model = null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		map.put("skuCode",skuCode);
		model = getSqlSession().selectOne(getNameSpace()+".selectListBySkuCode",map);
		return model;
	}

	@Override
	public ProductMallViewModel getProductMallViewByProductId(Long productId,
			String type) throws Exception {
		
		ProductMallViewModel query = new ProductMallViewModel();
		ProductMallViewModel model = null;
		query.setProductId(productId);
		query.setType(type);
		model = getSqlSession().selectOne(getNameSpace()+".selectByProductId",query);
		return model;
	}
	
	
	@Override
	public ProductMallViewModel getProductMallViewByProductCode(
			String productCode, String type) throws Exception {
		ProductMallViewModel query = new ProductMallViewModel();
		ProductMallViewModel model = null;
		query.setProductCode(productCode);
		query.setType(type);
		model = getSqlSession().selectOne(getNameSpace()+".selectByProductCode",query);
		return model;
	}

	@Override
	public List<ProductMallViewModel> getProductMallViewByProductIdList(
			ProductMallViewModel queryModel) throws Exception {
		List<ProductMallViewModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".selectListByProductIdList", queryModel);
		return list;
	}

	
	@Override
	public List<ProductMallViewModel> getProductMallViewByProductCodeList(
			ProductMallViewModel queryModel) throws Exception {
		List<ProductMallViewModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".selectListByProductCodeList", queryModel);
		return list;
	}
	
	
	@Override
	public Long getProductShelvesFiledByProductCode(String productCode)
			throws Exception {
		Long productShelves = null;
		productShelves = getSqlSession().selectOne(getNameSpace() + ".getProductShelvesFiledByProductCode", productCode);
		return productShelves;
	}

	

}
