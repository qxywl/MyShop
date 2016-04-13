/*
 * 文 件 名:  ProductBrandServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhaidou.product.dao.ProductInfoDao;
import com.zhaidou.product.dao.SupplierShopDao;
import com.zhaidou.product.model.base.ProductInfoModel;
import com.zhaidou.product.model.base.SupplierShopModel;
import com.zhaidou.product.service.ProductInfoService;
import com.zhaidou.product.service.SupplierShopService;


/**
 * 处理的是商品描述信息类型
 * 包括app和pc端的
 * */
@Service("productInfoService")
@Transactional()
public class ProductInfoServiceImpl implements ProductInfoService
{
    private static final Log logger = LogFactory.getLog(ProductInfoServiceImpl.class);

    @Resource
    private ProductInfoDao  productInfoDao;

	@Override
	public ProductInfoModel getProInfoByProductId(Long productId)
			throws Exception {
		ProductInfoModel queryModel = new ProductInfoModel();
		queryModel.setProductId(productId);
		
		ProductInfoModel resultModel = productInfoDao.getProInfoByProductId(queryModel);
		return resultModel;
	}

   
}
