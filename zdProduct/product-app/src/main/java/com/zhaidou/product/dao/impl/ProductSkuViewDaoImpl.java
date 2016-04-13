package com.zhaidou.product.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductSkuViewDao;
import com.zhaidou.product.model.ProductSkuViewModel;

@Repository("productSkuViewDao")
public class ProductSkuViewDaoImpl  extends BaseDao  implements ProductSkuViewDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }

	@Override
	public List<ProductSkuViewModel> getProductSkuViewByProdId(Long productId)
			throws Exception {
		List<ProductSkuViewModel>  list = null;
		list = getSqlSession().selectList(getNameSpace()+".selectListByProductId", productId);
		return list;
	}

	@Override
	public List<ProductSkuViewModel> getProductSkuViewBySkuList(
			List<String> skuList) throws Exception {
		
		List<ProductSkuViewModel>  list = null;
		Map<String,Object> map = new HashMap<String,Object>();
		if(skuList != null && skuList.size() > 0){
			map.put("skuList", skuList);
		}
		list = getSqlSession().selectList(getNameSpace()+".selectListBySkuList", map);
		return list;
	}

	
	@Override
	public List<ProductSkuViewModel> getProductSkuViewBySkuIdList(
			List<Long> skuIdList) throws Exception {
		List<ProductSkuViewModel>  list = null;
		Map<String,Object> map = new HashMap<String,Object>();
		if(skuIdList != null && skuIdList.size() > 0){
			map.put("skuIdList", skuIdList);
		}
		list = getSqlSession().selectList(getNameSpace()+".selectListBySkuIdList", map);
		return list;
	}

	
	/*@Override
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
	}*/

}
