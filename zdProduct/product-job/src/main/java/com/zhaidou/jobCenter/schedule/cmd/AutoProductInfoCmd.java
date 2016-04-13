package com.zhaidou.jobCenter.schedule.cmd;

import com.zhaidou.jobCenter.biz.utils.ObjTypeConstant;
import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.jobCenter.utils.Paging;
import com.zhaidou.product.manager.ProductInfoManager;
import com.zhaidou.product.model.ProductInfoJobModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 自动商品信息同步(到erp)
 * 
 * @author kaili
 * 
 */
public class AutoProductInfoCmd extends Command {

	public static final Log logger = LogFactory.getLog(AutoProductInfoCmd.class);

	private static final String className = AutoProductInfoCmd.class.getSimpleName();
	
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
			productInfoManager.doJob(condiction);
			return true;
		} catch (Exception e) {
		    if(e.getMessage().indexOf(":")>0){
		        String[] str = e.getMessage().split(":");
		        ProductInfoJobModel infoJobModel = new ProductInfoJobModel();
		        infoJobModel.setProductLogId(Long.parseLong(str[0]));
		        infoJobModel.setRetryNum(Integer.parseInt(str[1]));
		        productInfoManager.changeRetryNum(infoJobModel);
		    }
			context.put("message", e.getMessage());
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			logExit(className, "doExecute");
		}
	}
	 
}
