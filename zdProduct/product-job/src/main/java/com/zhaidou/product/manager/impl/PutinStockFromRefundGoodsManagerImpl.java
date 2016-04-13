package com.zhaidou.product.manager.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zhaidou.common.util.Page;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.PutinStockFromRefundGoodsManager;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductRefundGoodsModel;
import com.zhaidou.product.service.ProductRefundGoodsService;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.model.ProductStockPO;
import com.zhaidou.product.stock.po.request.ImportStockRequestPo;
import com.zhaidou.stock.dao.StockProductStockDao;

/**
 * 退货单数据同步到stock
 * @author google
 *
 */
@Service("putinStockFromRefundGoodsManager")
public class PutinStockFromRefundGoodsManagerImpl implements PutinStockFromRefundGoodsManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PutinStockFromRefundGoodsManagerImpl.class);

	private static final Integer ROWS_ONE_TIME = 500;

	private static  boolean JOB_Lock = true;

	@Resource
	private ProductRefundGoodsService productRefundGoodsService;
	
	@Resource
	private ProductStockManager productStockManager;

	@Resource
	private StockProductStockDao stockProductStockDao;


	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if (JOB_Lock) {
			JOB_Lock = false;
			try {
				LOGGER.info("查询从退货单数据并同步到stock");
				Page page = new Page();
				page.setNumPerPage(ROWS_ONE_TIME);
				ProductRefundGoodsModel productRefundGoodsModel = new ProductRefundGoodsModel();
				productRefundGoodsModel.setStatus(0);
				List<ProductRefundGoodsModel> list = productRefundGoodsService.pageList(productRefundGoodsModel, page);
				if (list == null) {
					LOGGER.info("没有需要同步退货单数据");
					return;
				}

				List<ProductStockPO> pos = Lists.newArrayList();
				for (ProductRefundGoodsModel refundGoodsStock : list) {
					//准备同步宅豆库数据
					ProductStockPO productStockPO = new ProductStockPO();
					productStockPO.setSkuCode(refundGoodsStock.getSkudm());
					productStockPO.setVirtualStock(Double.valueOf(refundGoodsStock.getSl()));
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
					List<ProductRefundGoodsModel> productRefundGoodsStocks = Lists.newArrayList();
					for (ProductRefundGoodsModel refundStock : list) {
						ProductRefundGoodsModel updateRefundGoodsStock = new ProductRefundGoodsModel();
						updateRefundGoodsStock.setId(refundStock.getId());
						updateRefundGoodsStock.setStatus(1); //已处理
						updateRefundGoodsStock.setUpdateTime(new Date());
						productRefundGoodsStocks.add(updateRefundGoodsStock);
					}
					productRefundGoodsService.updateList(productRefundGoodsStocks);
					LOGGER.info("同步erp库存成功:" + ToStringBuilder.reflectionToString(responseObj));
				} else {
					LOGGER.warn("同步erp库存异常:" + ToStringBuilder.reflectionToString(responseObj));

				}

			} catch (Exception e) {
				LOGGER.error("查询退货单数据并同步到stock error", e);
			} finally {
				JOB_Lock = true;
			}
		} else {
			LOGGER.info("查询退货单数据并同步到stock  job  Last time not  end");
		}
	}

}
