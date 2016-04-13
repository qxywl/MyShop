package com.zhaidou.jobCenter.schedule.cmd;

import com.zhaidou.jobCenter.biz.utils.ObjTypeConstant;
import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.jobCenter.utils.Paging;
import com.zhaidou.product.manager.ProductInfoManager;
import com.zhaidou.product.model.ProductObjBufCondiction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 自动商品信息同步(到mq)
 * 
 * @author kaili
 * 
 */
public class AutoMqProductInfoCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoMqProductInfoCmd.class);

	private static final String className = AutoMqProductInfoCmd.class.getSimpleName();
	
	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条
	
	@Autowired
	private ProductInfoManager productInfoManager;
	 

	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");

		try {
			int objType = ObjTypeConstant.AUTO_ONSALE_ING;
			ScheduleConfiguration configuration = (ScheduleConfiguration) context.get("scheduleConfiguration");
			int c = configuration.getCurrentNode();
			int t = configuration.getTotalNode();
			Paging paging = new Paging(1, ROWS_ONE_TIME);
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
			condiction.setPaging(paging);
			condiction.setState(Integer.valueOf(0));
			condiction.setObjType(Integer.valueOf(objType));
			condiction.setCurrentNode(Integer.valueOf(c));
			condiction.setTotalNode(Integer.valueOf(t));
			logger.info("para: " + condiction.toString());
			productInfoManager.sendMq(condiction);
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
