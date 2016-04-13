package com.zhaidou.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.common.util.Page;
import com.zhaidou.product.dao.ProductRefundGoodsDao;
import com.zhaidou.product.model.ProductRefundGoodsModel;
import com.zhaidou.product.service.ProductRefundGoodsService;

@Service("productRefundGoodsService")
public class ProductRefundGoodsServiceImpl implements ProductRefundGoodsService {
	
	/** slf4j*/
    private static final Logger  LOGGER = LoggerFactory.getLogger(ProductPurchaseStockServiceImpl.class);

    @Autowired
    private ProductRefundGoodsDao productRefundGoodsDao;

	@Override
	public List<ProductRefundGoodsModel> pageList(ProductRefundGoodsModel productRefundGoodsModel, Page page) throws Exception {
		long count = productRefundGoodsDao.countListPage(productRefundGoodsModel);
		List<ProductRefundGoodsModel> list = null;
		if(count > 0){
			page.setTotalCount(count);
			list = productRefundGoodsDao.queryListPage(productRefundGoodsModel, page.getPageNum(), page.getNumPerPage());
		}
		return list;
	}

	@Override
	public void insertProductRefundGoodsList(List<ProductRefundGoodsModel> productRefundGoodsModels){
		for(ProductRefundGoodsModel productRefundGoodsModel : productRefundGoodsModels){
			try {
				productRefundGoodsDao.insert(productRefundGoodsModel);
				LOGGER.info("同步退货单成功success,productRefundGoodsModel = " + productRefundGoodsModel);
			} catch (Exception e) {
				LOGGER.error("同步退货单失败faile,productRefundGoodsModel = " + productRefundGoodsModel, e);
			}
		}
		
	}

	@Override
	public long countListPage(Long id) throws Exception {
		ProductRefundGoodsModel productRefundGoodsModel = new ProductRefundGoodsModel();
		productRefundGoodsModel.setRefundId(id);
		return productRefundGoodsDao.countListPage(productRefundGoodsModel); 
	}

	@Override
	public void updateList(List<ProductRefundGoodsModel> productRefundGoodsModels){
		for(ProductRefundGoodsModel productRefundGoodsModel : productRefundGoodsModels){
			try {
				productRefundGoodsDao.update(productRefundGoodsModel);
				LOGGER.info("更改退货单成功success,productRefundGoodsModel = " + productRefundGoodsModel);
			} catch (Exception e) {
				LOGGER.error("更改退货单失败faile, productRefundGoodsModel = " +productRefundGoodsModel, e);
			}
		}
	}


}
