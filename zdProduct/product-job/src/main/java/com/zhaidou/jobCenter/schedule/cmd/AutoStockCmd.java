package com.zhaidou.jobCenter.schedule.cmd;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidou.jobCenter.biz.utils.ObjTypeConstant;
import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.product.manager.ProductStockManager;
import com.zhaidou.product.model.ProductObjBufCondiction;

/**
 * 自动价格同步
 * 
 * @author kaili
 * 
 */
public class AutoStockCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoStockCmd.class);

	private static final String className = AutoStockCmd.class.getSimpleName();
	

	@Autowired
	private ProductStockManager productStockManager;
	 

	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");

		try {
			int objType = ObjTypeConstant.AUTO_ONSALE_ING;
			ScheduleConfiguration configuration = (ScheduleConfiguration) context.get("scheduleConfiguration");
			int c = configuration.getCurrentNode();
			int t = configuration.getTotalNode();
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
			condiction.setState(Integer.valueOf(0));
			condiction.setObjType(Integer.valueOf(objType));
			condiction.setCurrentNode(Integer.valueOf(c));
			condiction.setTotalNode(Integer.valueOf(t));
			logger.info("para: " + condiction.toString());
			productStockManager.doJob(condiction);
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
