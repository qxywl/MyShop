/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.product.dao.ProductStockDao;
import com.zhaidou.product.model.base.ProductStockModel;
import com.zhaidou.product.po.mall.MallProductStockPO;
import com.zhaidou.product.po.request.RequestMallProductStockPO;
import com.zhaidou.product.service.ProductStockService;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */
@Service("productStockServiceV001")
public class ProductStockServiceImpl implements ProductStockService {
    private Logger logger = Logger.getLogger(ProductStockServiceImpl.class);

    @Autowired
    private ProductStockDao productStockDao;
    
    
     
    @Override
    public MallProductStockPO getProductStock(RequestMallProductStockPO request) {
        MallProductStockPO stockPO=new MallProductStockPO();
        return stockPO;
    }

   
    
	@Override
	public ProductStockModel queryBySkuCode(String skuCode) throws Exception {
		ProductStockModel resultModel = null;
		ProductStockModel queryModel = new ProductStockModel();
		queryModel.setSkuCode(skuCode);
		resultModel = productStockDao.queryBySkuCode(queryModel);
		return resultModel;
	}
	
	
	@Override
	public List<ProductStockModel> queryBySkuCodeList(List<String> skuCodeList)
			throws Exception {
		List<ProductStockModel> resultModel = null;
		ProductStockModel queryModel = new ProductStockModel();
		if(skuCodeList != null && skuCodeList.size()>0){
			queryModel.setSkuCodeList(skuCodeList);
		}
		resultModel = productStockDao.queryBySkuCodeList(queryModel);
		return resultModel;
	}
	

}
