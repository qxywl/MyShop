package com.zhaidou.jobCenter.schedule.cmd;

import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.product.manager.ErpPurchasePutinManager;
import com.zhaidou.product.model.ProductObjBufCondiction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 自动同步erp采购入库单
 * 
 * @author donnie
 * 
 */
public class AutoSyncPurchaseStockCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoSyncPurchaseStockCmd.class);

	private static final String className = AutoSyncPurchaseStockCmd.class.getSimpleName();

	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条

	@Autowired
	private ErpPurchasePutinManager erpPurchasePutinManager;

	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");

		try {
			logger.info("=========start==========");
//			int objType = ObjTypeConstant.AUTO_ONSALE_ING;
			ScheduleConfiguration configuration = (ScheduleConfiguration) context.get("scheduleConfiguration");
			int c = configuration.getCurrentNode();
			int t = configuration.getTotalNode();
//			Paging paging = new Paging(1, ROWS_ONE_TIME);
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
//			condiction.setPaging(paging);
			condiction.setState(Integer.valueOf(0));
//			condiction.setObjType(Integer.valueOf(objType));
			condiction.setCurrentNode(Integer.valueOf(c));
			condiction.setTotalNode(Integer.valueOf(t));
			logger.info("para: " + condiction.toString());
			erpPurchasePutinManager.doJob(condiction);

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
