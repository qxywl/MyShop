package com.zhaidou.jobCenter.schedule.cmd;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.product.manager.ErpPurchaseRefundManager;
import com.zhaidou.product.model.ProductObjBufCondiction;

/**
 * 自动同步erp采购退货单
 * @author google
 *
 */
public class AutoSyncPurchaseRefundCmd extends Command{
	
	public static final Log logger = LogFactory.getLog(AutoSyncPurchaseStockCmd.class);

	private static final String className = AutoSyncPurchaseStockCmd.class.getSimpleName();

	@Autowired
	private ErpPurchaseRefundManager erpPurchaseRefundManager;

	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");

		try {
			logger.info("=========start==========");
			ScheduleConfiguration configuration = (ScheduleConfiguration) context.get("scheduleConfiguration");
			int c = configuration.getCurrentNode();
			int t = configuration.getTotalNode();
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
			condiction.setState(Integer.valueOf(0));
			condiction.setCurrentNode(Integer.valueOf(c));
			condiction.setTotalNode(Integer.valueOf(t));
			logger.info("para: " + condiction.toString());
			erpPurchaseRefundManager.doJob(condiction);

			return true;
		} catch (Exception e) {
			context.put("message", e.getMessage());
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			logExit(className, "doExecute");
		}
	}
}
