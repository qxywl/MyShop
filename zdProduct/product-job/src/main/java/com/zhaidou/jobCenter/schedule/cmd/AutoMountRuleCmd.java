package com.zhaidou.jobCenter.schedule.cmd;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.product.manager.MountRuleManager;
import com.zhaidou.product.model.ProductObjBufCondiction;

/***
 * 
 * @Title: AutoMountRuleCmd.java 
 *
 * @Package com.teshehui.jobCenter.schedule.cmd 
 *
 * @Description:   自动解析自动挂载关系
 *
 * @author lvshuding 
 *
 * @date 2015年6月1日 下午7:31:14 
 *
 * @version V1.0
 */
public class AutoMountRuleCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoMountRuleCmd.class);

	private static final String className = AutoMountRuleCmd.class.getSimpleName();
	
	@Autowired
	private MountRuleManager mountRuleManager;
	 
	@Override
	public boolean doExecute(HashMap<String, Object> context) {
		logEntry(className, "doExecute");
		try {
			ScheduleConfiguration configuration = (ScheduleConfiguration) context.get("scheduleConfiguration");
			int c = configuration.getCurrentNode();
			int t = configuration.getTotalNode();
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
			condiction.setState(Integer.valueOf(0));
			condiction.setCurrentNode(Integer.valueOf(c));
			condiction.setTotalNode(Integer.valueOf(t));
			logger.info("para: " + condiction.toString());
			return mountRuleManager.doJob(condiction);
		} catch (Exception e) {
			context.put("message", e.getMessage());
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			logExit(className, "doExecute");
		}
	}
}
