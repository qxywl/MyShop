package com.zhaidou.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ProductJobTimeDao;
import com.zhaidou.product.model.ProductJobTimeModel;
import com.zhaidou.product.service.ProductJobTimeService;

@Service("productJobTimeService")
public class ProductJobTimeServiceImpl implements ProductJobTimeService {

	@Autowired
	private ProductJobTimeDao productJobTimeDao;
	
	public ProductJobTimeModel queryOne(ProductJobTimeModel productJobTimeModel) throws Exception{
		
		return productJobTimeDao.queryOne(productJobTimeModel);
	}

	@Override
	public void insertErpJobRecord(ProductJobTimeModel productJobTimeModel)	throws Exception {
		productJobTimeDao.insert(productJobTimeModel);
	}
}
