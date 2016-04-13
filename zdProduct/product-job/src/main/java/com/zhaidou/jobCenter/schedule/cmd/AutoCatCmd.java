package com.zhaidou.jobCenter.schedule.cmd;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidou.jobCenter.biz.utils.ObjTypeConstant;
import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.jobCenter.utils.Paging;
import com.zhaidou.product.manager.ProductInfoManager;
import com.zhaidou.product.manager.ProductOtherManager;
import com.zhaidou.product.manager.ProductPriceManager;
import com.zhaidou.product.model.ProductObjBufCondiction;

/**
 * 自动价格同步
 * 
 * @author kaili
 * 
 */
public class AutoCatCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoCatCmd.class);

	private static final String className = AutoCatCmd.class.getSimpleName();
	

	@Autowired
	private ProductOtherManager productOtherManager;
	 

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
			productOtherManager.doJobCat(condiction);
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
