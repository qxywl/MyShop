package com.zhaidou.product.service.impl;

import com.zhaidou.product.dao.ProductToErpLogDao;
import com.zhaidou.product.model.ProductToErpLogModel;
import com.zhaidou.product.service.ProductToErpLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 推送商品到ERP错误日志
 * @author google
 *
 */
@Service("productToErpLogService")
public class ProductToErpLogServiceImpl implements ProductToErpLogService{
	
	private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);
	
	@Resource
	private ProductToErpLogDao productToErpLogDao;

	@Override
	public void addProductToErpLog(ProductToErpLogModel productToErpLogModel)throws Exception {

		productToErpLogDao.insert(productToErpLogModel);
	}
	

}
