package com.zhaidou.product.manager.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhaidou.common.util.Page;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.PutInStockFromPurchaseManager;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductPurchaseStock;
import com.zhaidou.product.service.ProductPurchaseStockService;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.model.ProductStockPO;
import com.zhaidou.product.stock.po.request.ImportStockRequestPo;
import com.zhaidou.stock.dao.StockProductStockDao;
import com.zhaidou.stock.model.StockProductStock;


@Service("putInStockFromPurchaseManager")
public class PutinStockFromPurchaseManagerImpl implements PutInStockFromPurchaseManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PutinStockFromPurchaseManagerImpl.class);

	private static final Integer ROWS_ONE_TIME = 500;

	private static  boolean JOB_Lock = true;

	@Resource
	private ProductPurchaseStockService productPurchaseStockService;

	@Resource
	private ProductStockManager productStockManager;

	@Resource
	private StockProductStockDao stockProductStockDao;


	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if (JOB_Lock) {
			JOB_Lock = false;
			try {
				LOGGER.info("查询从采购入库数据并同步到stock");
				Page page = new Page();
				page.setNumPerPage(ROWS_ONE_TIME);
				ProductPurchaseStock productPurchaseStock = new ProductPurchaseStock();
				productPurchaseStock.setRemainder(configuration.getCurrentNode());
				productPurchaseStock.setTotal(configuration.getTotalNode());
				productPurchaseStock.setStatus(0);
				List<ProductPurchaseStock> list = productPurchaseStockService.pageList(productPurchaseStock, page);
				if (list == null) {
					return;
				}
				List<String> skuCodes = getSkuCodes(list);

				Map<String, Integer> stockPercentMap = getStockPercentMap(skuCodes);
				if (stockPercentMap == null || stockPercentMap.size() == 0) {
					LOGGER.warn("无库存百分比 stockPercentMap =" + ToStringBuilder.reflectionToString(stockPercentMap));
					return;
				}

				List<ProductStockPO> pos = Lists.newArrayList();
				for (ProductPurchaseStock purchaseStock : list) {
					Integer stockPercent = stockPercentMap.get(purchaseStock.getSkudm());
					if (stockPercent == null) {
						LOGGER.warn("无库存百分比 purchaseStock=" + purchaseStock);
						continue;
					}
					ProductStockPO productStockPO = new ProductStockPO();
					productStockPO.setSkuCode(purchaseStock.getSkudm());
					double addStockNum = 0.0;
					if(purchaseStock.getType() == 1){ //采购退货信息
						double stockNum = Math.abs(purchaseStock.getSl());
						addStockNum = -(Math.ceil(stockNum * stockPercent / 100));
					}
					if(purchaseStock.getType() == 0){ //采购入库信息
						addStockNum = Math.floor(purchaseStock.getSl() * stockPercent / 100);
					}
					productStockPO.setVirtualStock(addStockNum);
					productStockPO.setStockType(3); //实库
					productStockPO.setChangeUserName("系统同步");
					productStockPO.setChangeUserCode("0");
					productStockPO.setChangeOption(1); //增量变更
					pos.add(productStockPO);
				}


				ImportStockRequestPo resqPo = new ImportStockRequestPo();
				RequestObject reqObj = new RequestObject();
				resqPo.setPos(pos);
				resqPo.setType(2);
				reqObj.setRequestParams(resqPo);
				ResponseObject responseObj = productStockManager.importStock(reqObj);
				if (responseObj.getCode() == 0) {
					//处理数据
					List<ProductPurchaseStock> productPurchaseStocks = Lists.newArrayList();
					for (ProductPurchaseStock purchaseStock : list) {
						ProductPurchaseStock updatePurchaseStock = new ProductPurchaseStock();
						updatePurchaseStock.setId(purchaseStock.getId());
						updatePurchaseStock.setStatus(1); //已处理
						Integer stockPercent = stockPercentMap.get(purchaseStock.getSkudm());
						if(stockPercent == null){
							//如果分配百分比为空   默认为100 公共库默认为0 状态默认
							updatePurchaseStock.setRealStock(purchaseStock.getSl());
							updatePurchaseStock.setAssignPercent(100);
							updatePurchaseStock.setAssignStatus(1);
							updatePurchaseStock.setPublicStock(0);
						}else{
							//设置实库数量和分配百分比
							Integer realStock = null;
							if(purchaseStock.getType() == 1){ //采购退货信息
								double stockNum = Math.abs(purchaseStock.getSl());
								realStock = -(Integer.valueOf((int)Math.ceil(stockNum * stockPercent / 100)));
							}
							if(purchaseStock.getType() == 0){ //入库信息
								realStock = Integer.valueOf((int)Math.floor(purchaseStock.getSl() * stockPercent / 100));
							}
							updatePurchaseStock.setRealStock(realStock);
							updatePurchaseStock.setAssignPercent(stockPercent);
							updatePurchaseStock.setPublicStock(purchaseStock.getSl() - realStock);
						}
						productPurchaseStocks.add(updatePurchaseStock);
					}
					productPurchaseStockService.updateList(productPurchaseStocks);
					LOGGER.info("同步erp库存成功:" + ToStringBuilder.reflectionToString(responseObj)
							+ ", stockPercentMap =" + ToStringBuilder.reflectionToString(stockPercentMap));
				} else {
					LOGGER.warn("同步erp库存异常:" + ToStringBuilder.reflectionToString(responseObj)
							+ ", stockPercentMap =" + ToStringBuilder.reflectionToString(stockPercentMap));

				}

			} catch (Exception e) {
				LOGGER.error("查询从采购入库数据并同步到stock error", e);
			} finally {
				JOB_Lock = true;
			}
		} else {
			LOGGER.info("查询从采购入库数据并同步到stock  job  Last time not  end");
		}
	}
	
	private Map<String, Integer> getStockPercentMap(List<String> skuCodes) throws Exception {
		List<StockProductStock> stockProductStocks = stockProductStockDao.queryBySkuCodeList(skuCodes);
		Map<String, Integer> stockPercentMap = Maps.newHashMap();
		for (StockProductStock stockProductStock : stockProductStocks) {
            stockPercentMap.put(stockProductStock.getSkuCode(), stockProductStock.getStockPercent());
        }
		return stockPercentMap;
	}

	private List<String> getSkuCodes(List<ProductPurchaseStock> list) {
		List<String> skuCodes = Lists.newArrayList();
		for (ProductPurchaseStock purchaseStock : list) {
            skuCodes.add(purchaseStock.getSkudm());
        }
		return skuCodes;
	}


}
