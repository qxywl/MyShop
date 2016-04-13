package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zhaidou.product.info.controller.ProductStockController;
import com.zhaidou.product.info.dao.ProductJobTimeDao;
import com.zhaidou.product.info.model.ProductJobTimeModel;
import com.zhaidou.product.info.model.ProductPurchaseStockModel;
import com.zhaidou.product.info.service.ProductJobTimeService;
import com.zhaidou.product.info.service.ProductPurchaseStockService;
import com.zhaidou.product.info.util.PurchaseQuery;

@Service("productJobTimeService")
public class ProductJobTimeServiceImpl implements ProductJobTimeService {

	private static Logger logger = Logger.getLogger(ProductStockController.class);    
	
	@Autowired
	private ProductJobTimeDao productJobTimeDao;
	
	@Resource
	private ProductPurchaseStockService productPurchaseStockService;
	
	
	public ProductJobTimeModel queryOne(ProductJobTimeModel productJobTimeModel) throws Exception{
		
		return productJobTimeDao.queryOne(productJobTimeModel);
	}

	/**
	 * 插入一条jobitme记录表
	 * @param productJobTimeModel
	 * @throws Exception
	 */
	public void insertErpJobRecord(ProductJobTimeModel productJobTimeModel)	throws Exception {
		productJobTimeDao.insert(productJobTimeModel);
	}

	/**
	 * 插入增量库存表以及job记录
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public String addPurchaseStockAndJobTime(ProductJobTimeModel newProductJobTimeModel, String dateStart, String dateEnd, Long userId)throws Exception {
		String msg = null;

		//ERP-JOB表插入新数据
		this.insertErpJobRecord(newProductJobTimeModel);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[{'paramname':'SH', 'paramcompair': '=', 'paramvalue':'1'},");
		stringBuilder.append("{'paramname':'shrq', 'paramcompair': '>', 'paramvalue':'"+dateStart+"'},");
		stringBuilder.append("{'paramname':'shrq', 'paramcompair': '<=', 'paramvalue':'"+dateEnd+"'}]");
		Document document = PurchaseQuery.findPurchasePutInStorage(stringBuilder.toString());
		Node node = document.selectSingleNode("//purchase_get_response/total_resultsads");
		if (node == null || node.getText() == null ||"0".equals(node.getText()) ) {
			logger.info("erp当前时间段无查询结果:dateStart ="+dateStart+", dateEnd ="+dateEnd);
			return msg = "erp当前时间段无查询结果";
		}

		List<ProductPurchaseStockModel> productPurchaseStocks = Lists.newArrayList();
		List<Node> list = document.selectNodes("//purchase_get_response/purchases/purchase");
		for (Node purchaseNode : list) {
			Node idNode = purchaseNode.selectSingleNode("id");
			Long purchaseId = Long.valueOf(idNode.getText());
			long count= productPurchaseStockService.countListPage(purchaseId);
			if(count > 0) {
				logger.warn("this data is exist count ="+count +", purchaseNode ="+purchaseNode.asXML());
				continue;
			}

			Node djbhNode = purchaseNode.selectSingleNode("djbh");
			Node shNode = purchaseNode.selectSingleNode("sh");
			Node shrNode = purchaseNode.selectSingleNode("shr");
			Node shrqNode = purchaseNode.selectSingleNode("shrq");

			List<Node> cgddmxNodes = purchaseNode.selectNodes("cgddmxs/cgddmx");

			for (Node cgddmxNode : cgddmxNodes) {

				Node skudmNode = cgddmxNode.selectSingleNode("skudm");
				Node spmcNode = cgddmxNode.selectSingleNode("spmc");
				Node spdmNode = cgddmxNode.selectSingleNode("spdm");
				Node skumcNode = cgddmxNode.selectSingleNode("skumc");
				Node slNode = cgddmxNode.selectSingleNode("sl");
				ProductPurchaseStockModel productPurchaseStock = new ProductPurchaseStockModel();
			    productPurchaseStock.setPurchaseId(purchaseId);
			    productPurchaseStock.setType(0);
			    productPurchaseStock.setDjbh(djbhNode.getText());
			    productPurchaseStock.setSh(Integer.valueOf(shNode.getText()));
			    productPurchaseStock.setPurchaseShr(shrNode.getText());
				DateTime dateTime = new DateTime(shrqNode.getText());
				productPurchaseStock.setShrq(dateTime.toDate());
			    productPurchaseStock.setSpdm(spdmNode.getText());
			    productPurchaseStock.setSpmc(spmcNode.getText());
			    productPurchaseStock.setSkudm(skudmNode.getText());
			    productPurchaseStock.setSkumc(skumcNode.getText());
			    productPurchaseStock.setSl(Integer.valueOf(slNode.getText()));
			    productPurchaseStock.setStatus(0);
			    productPurchaseStock.setCreateBy(0);
			    productPurchaseStock.setUpdateBy(0);
				productPurchaseStocks.add(productPurchaseStock);
			}
		}
		productPurchaseStockService.insertProductPurchaseStockList(productPurchaseStocks);
		return msg = "同步成功";
	}
}
