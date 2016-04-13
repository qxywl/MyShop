package com.zhaidou.product.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Node;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zhaidou.common.util.Constants;
import com.zhaidou.ecerp.PurchaseQuery;
import com.zhaidou.jobCenter.utils.DateUtil;
import com.zhaidou.product.manager.ErpPurchasePutinManager;
import com.zhaidou.product.model.ProductJobTimeModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductPurchaseStock;
import com.zhaidou.product.service.ProductJobTimeService;
import com.zhaidou.product.service.ProductPurchaseStockService;


@Service("erpPurchasePutinManager")
public class ErpPurchasePutINManagerImpl  implements ErpPurchasePutinManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErpPurchasePutINManagerImpl.class);

    private static  boolean JOB_Lock = true;

	@Resource
	private ProductPurchaseStockService productPurchaseStockService;
	
	@Resource
	private ProductJobTimeService productJobTimeService;
	

	@SuppressWarnings("unchecked")
	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if (JOB_Lock) {
			JOB_Lock = false;
			try {
				ProductJobTimeModel newProductJobTimeModel = new ProductJobTimeModel();
				DateTime date = new DateTime();
				String datetimestr = "yyyy-MM-dd'T'HH:mm:ss";
				String dateStart = null;
				String dateEnd = null;
				LOGGER.info("从erp获取采购入库数据");
				//获取JOB时间记录数据
				ProductJobTimeModel productJobTimeModel = new ProductJobTimeModel();
				productJobTimeModel.setType(Constants.ERP_JOB_TYPE.PURCHASESTOCK);
				productJobTimeModel = productJobTimeService.queryOne(productJobTimeModel);
				if(productJobTimeModel != null){
					newProductJobTimeModel.setStartTime(productJobTimeModel.getEndTime());
					dateStart = DateUtil.formatErpDateTime(productJobTimeModel.getEndTime()); 
					dateEnd = date.toString(datetimestr);
				}else{
					newProductJobTimeModel.setStartTime(new Date());
					//当前时间一小时内
					dateStart = date.minusHours(1).toString(datetimestr);
					dateEnd = date.toString(datetimestr);
				}
				
				//准备ERP-JOB记录数据


				newProductJobTimeModel.setEndTime(new Date());
				newProductJobTimeModel.setType(Constants.ERP_JOB_TYPE.PURCHASESTOCK);
				newProductJobTimeModel.setCreateBy(0l);
				newProductJobTimeModel.setUpdateBy(0l);
				//ERP-JOB表插入新数据
				productJobTimeService.insertErpJobRecord(newProductJobTimeModel);
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("[{'paramname':'SH', 'paramcompair': '=', 'paramvalue':'1'},");
				stringBuilder.append("{'paramname':'shrq', 'paramcompair': '>', 'paramvalue':'"+dateStart+"'},");
				stringBuilder.append("{'paramname':'shrq', 'paramcompair': '<=', 'paramvalue':'"+dateEnd+"'}]");
				Document document = PurchaseQuery.findPurchasePutInStorage(stringBuilder.toString());
				Node node = document.selectSingleNode("//purchase_get_response/total_results");
				if (node == null || node.getText() == null ||"0".equals(node.getText()) ) {
					LOGGER.info("erp当前时间段无查询结果:dateStart ="+dateStart+", dateEnd ="+dateEnd);
					return;
				}

				List<ProductPurchaseStock> productPurchaseStocks = Lists.newArrayList();
				List<Node> list = document.selectNodes("//purchase_get_response/purchases/purchase");
				for (Node purchaseNode : list) {
					Node idNode = purchaseNode.selectSingleNode("id");
					Long purchaseId = Long.valueOf(idNode.getText());
					long count= productPurchaseStockService.countListPage(purchaseId);
					if(count > 0) {
						LOGGER.warn("this data is exist count ="+count +", purchaseNode ="+purchaseNode.asXML());
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
						ProductPurchaseStock productPurchaseStock = new ProductPurchaseStock();
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
			} catch (Exception e) {
				LOGGER.error("从erp获取采购入库数据 error" , e);
			} finally {
				JOB_Lock = true;
			}
		} else {
			LOGGER.info("从erp获取采购入库数据  job  Last time not  end");
		}
	}
	
}
