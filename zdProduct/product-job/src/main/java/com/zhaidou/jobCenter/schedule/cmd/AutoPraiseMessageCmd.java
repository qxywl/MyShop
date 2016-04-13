package com.zhaidou.jobCenter.schedule.cmd;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhaidou.jobCenter.biz.utils.ObjTypeConstant;
import com.zhaidou.jobCenter.chain.Command;
import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.jobCenter.utils.Paging;
import com.zhaidou.product.manager.PraiseMessageManager;
import com.zhaidou.product.model.PraiseMessageModel;
import com.zhaidou.product.model.ProductObjBufCondiction;

public class AutoPraiseMessageCmd extends Command{
    public static final Log logger = LogFactory.getLog(AutoPraiseMessageCmd.class);

    private static final String className = AutoPraiseMessageCmd.class.getSimpleName();
    
    private static final Integer ROWS_ONE_TIME = 500;   //一次处理多少条
    
    @Autowired
    private PraiseMessageManager praiseMessageManager;
     

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
            praiseMessageManager.sendMessage(condiction);
            return true;
        } catch (Exception e) {
            if(e.getMessage().indexOf(":")>0){
                String[] str = e.getMessage().split(":");
                PraiseMessageModel praiseMessageModel = new PraiseMessageModel();
                praiseMessageModel.setProductId(Long.parseLong(str[0]));
                praiseMessageModel.setRetryNum(Integer.parseInt(str[1])+1);
                praiseMessageManager.changeRetryNum(praiseMessageModel);
            }
            context.put("message", e.getMessage());
            logger.error(e);
            throw new RuntimeException(e);
        } finally {
            logExit(className, "doExecute");
        }
    }
}
