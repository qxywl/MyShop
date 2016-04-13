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
import com.zhaidou.product.model.ProductObjBufCondiction;

public class AutoImportPraiseMessageCmd extends Command{
    
    public static final Log logger = LogFactory.getLog(AutoImportPraiseMessageCmd.class);

    private static final String className = AutoImportPraiseMessageCmd.class.getSimpleName();
    
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
            praiseMessageManager.imoprt(condiction);
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
