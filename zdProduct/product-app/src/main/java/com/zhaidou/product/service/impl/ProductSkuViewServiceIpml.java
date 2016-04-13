package com.zhaidou.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ProductSkuViewDao;
import com.zhaidou.product.model.ProductSkuViewModel;
import com.zhaidou.product.service.ProductSkuViewService;

@Service(value="productSkuViewServiceV001")
public class ProductSkuViewServiceIpml implements ProductSkuViewService{

	@Autowired
	private ProductSkuViewDao productSkuViewDao;
	
	@Override
	public List<ProductSkuViewModel> getProductSkuViewByProdId(Long productId)
			throws Exception {
		List<ProductSkuViewModel> list = null;
		list = productSkuViewDao.getProductSkuViewByProdId(productId);
		return list;
	}

	@Override
	public List<ProductSkuViewModel> getProductSkuViewBySkuList(
			List<String> skuList) throws Exception {
		List<ProductSkuViewModel> list = productSkuViewDao.getProductSkuViewBySkuList(skuList);
		return list;
	}

	@Override
	public List<ProductSkuViewModel> getProductSkuViewBySkuIdList(
			List<Long> skuIdList) throws Exception {
		List<ProductSkuViewModel> list = productSkuViewDao.getProductSkuViewBySkuIdList(skuIdList);
		return list;
	}
	


}
