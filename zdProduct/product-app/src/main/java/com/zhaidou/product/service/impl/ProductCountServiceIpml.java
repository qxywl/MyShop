package com.zhaidou.product.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.redis.RedisCache;
import com.zhaidou.product.cache.po.ProductDataCountPO;
import com.zhaidou.product.dao.ProductCountDao;
import com.zhaidou.product.model.base.ProductCountModel;
import com.zhaidou.product.service.ProductCountService;

@Service(value="productCountServiceV001")
public class ProductCountServiceIpml implements ProductCountService{

	@Autowired
	private ProductCountDao productCountDao;
	
	
	
	@Resource
	private RedisCache redisCache;
	
	@Override
    public void addProductCount(ProductCountModel productCountModel) throws Exception
    {
		if(productCountModel != null ){
			productCountDao.insert(productCountModel);
		}
    	
    }

	
	@Override
	public void updateProductCount(ProductCountModel productCountModel)
			throws Exception {
		if(productCountModel != null ){
			productCountDao.update(productCountModel);
		}
	}
	
	
	@Override
	public ProductCountModel getProductCountByProdId(Long productId)
			throws Exception {
		ProductCountModel resultModel = null;
		ProductCountModel queryModel = new ProductCountModel();
		queryModel.setProductId(productId);
		
		resultModel = productCountDao.queryOne(queryModel);
		return resultModel;
	}

	
	@Override
	public List<ProductCountModel> getProCountByProductIdList(
			List<Long> productIdList) throws Exception {
		
		List<ProductCountModel> resultModel = null;
		ProductCountModel queryModel = new ProductCountModel();
		if(productIdList != null && productIdList.size()>0){
			queryModel.setProductIdList(productIdList);
		}
		resultModel = productCountDao.queryByProductIdList(queryModel);
		return resultModel;
	}

	


}
