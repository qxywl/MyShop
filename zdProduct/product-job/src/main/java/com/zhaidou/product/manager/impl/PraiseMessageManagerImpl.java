package com.zhaidou.product.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.message.manager.MessageManager;
import com.zhaidou.message.po.SMSPO;
import com.zhaidou.product.manager.PraiseMessageManager;
import com.zhaidou.product.model.PraiseMessageModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.service.PraiseMessageService;
import com.zhaidou.product.service.ProductService;
import com.zhaidou.product.util.ProductUtil;

@Service("praiseMessageManager")
public class PraiseMessageManagerImpl implements PraiseMessageManager {
    
    public static final Log logger = LogFactory.getLog(PraiseMessageManagerImpl.class);
    
    private static final Integer ROWS_ONE_TIME = 500;   //一次处理多少条
    
    private static  boolean JOB_Lock = true;      
    
    private static  boolean IMP_Lock = true;     
    
    private static  boolean CRE_Lock = true;    
    
    private static  boolean MEMCACHE_Lock = true;
    
    private static  boolean MQ_Lock = true;
    
    private static  boolean VIEW_Lock = true;
    
    @Resource
    private PraiseMessageService praiseMessageService;
    @Resource
    private ProductService productService;
    @Resource
    private MessageManager messageManager;
    
    @Value("#{propertyConfigurerForProject2['retry_num']}")
    private Integer retryNum;
    @Value("#{propertyConfigurerForProject2['one_grade']}")
    private Integer oneGrade;
    @Value("#{propertyConfigurerForProject2['two_grade']}")
    private Integer twoGrade;
    @Value("#{propertyConfigurerForProject2['three_grade']}")
    private Integer threeGrade;
    @Value("#{propertyConfigurerForProject2['four_grade']}")
    private Integer fourGrade;
    @Value("#{propertyConfigurerForProject2['five_grade']}")
    private Integer fiveGrade;
    
    @Value("#{propertyConfigurerForProject2['message_content']}")
    private String messageContent;
    
    @Override
    public void sendMessage(ProductObjBufCondiction configuration) {
//        if(IMP_Lock){
//            IMP_Lock=false;
//            PraiseMessageModel mm = null;
//            try {
//                logger.info("AutoPraiseMessageCmd:     do imoprt databae");
//                Page page = new Page();
//                page.setNumPerPage(ROWS_ONE_TIME);
//                PraiseMessageModel praiseMessageModel = new PraiseMessageModel();
//                praiseMessageModel.setRemainder(configuration.getCurrentNode());
//                praiseMessageModel.setTotal(configuration.getTotalNode());
//                praiseMessageModel.setSendStatus(1);
//                
//                long count = praiseMessageService.getCountPraise(praiseMessageModel);
//                if(count>0){
//                    int totalPage =  (int)(count % ROWS_ONE_TIME == 0 ? count / ROWS_ONE_TIME : count / ROWS_ONE_TIME + 1 );
//                    for(int x=1;x<=totalPage;x++){
//                        page.setPageNum(x);
//                        List<PraiseMessageModel> list = praiseMessageService.getProductInfoAuth(praiseMessageModel, page);
//                        if(null!=list){
//                            for (int i = 0; i < list.size(); i++) {
//                                PraiseMessageModel bb=list.get(i);
//                                mm = bb;
//                                PraiseMessageModel pp = new PraiseMessageModel();
//                                if(bb.getRetryNum()<retryNum){
//                                    //修改状态
//                                    if(bb.getSendStatus()==1){
//                                        if(bb.getSupplierPhone()!=null && bb.getOperatorPhone()!=null){
//                                            if(bb.getSupplierPhone().equals(bb.getOperatorPhone())){
//                                                send(bb,bb.getSupplierPhone());
//                                            }else{
//                                                send(bb,bb.getSupplierPhone());
//                                                send(bb,bb.getOperatorPhone());
//                                            }
//                                        }else{
//                                            if(bb.getSupplierPhone()!=null){
//                                                send(bb,bb.getSupplierPhone());
//                                            }else{
//                                                send(bb,bb.getOperatorPhone());
//                                            }
//                                        }
//                                        
//                                        pp.setProductId(bb.getProductId());
//                                        pp.setLastSendAccount(bb.getPraiseAccount());
//                                        pp.setLastSendGrade(bb.getSendGrade());
//                                        pp.setLastSendTime(new Date().getTime()/1000);
//                                        pp.setSendStatus(2);
//                                        praiseMessageService.updatePraiseMessage(pp);
//                                    }
//                                }else{
//                                    pp.setProductId(bb.getProductId());
//                                    pp.setSendStatus(3);
//                                    praiseMessageService.updatePraiseMessage(pp);
//                                }
//                            }
//                        }
//                    }
//                }
//               
//            } catch (Exception e) {
//                logger.error(e);
//                if(mm!=null){
//                    throw new RuntimeException(mm.getProductId()+":"+mm.getRetryNum());
//                }
//            } finally{
//                IMP_Lock=true;
//            }
//        }else{
//            logger.info("AutoPraiseMessageCmd  imoprt  Last time not  end");
//        }

    }

    @Override
    public void imoprt(ProductObjBufCondiction configuration) {
//        if(IMP_Lock){
//            IMP_Lock=false;
//            try {
//                logger.info("importPraiseMessageCmd:     do imoprt databae");
//                Page page = new Page();
//                page.setNumPerPage(ROWS_ONE_TIME);
//                PraiseMessageModel praiseMessageModel = new PraiseMessageModel();
//                praiseMessageModel.setRemainder(configuration.getCurrentNode());
//                praiseMessageModel.setTotal(configuration.getTotalNode());
//                praiseMessageModel.setSendStatus(0);
//                List<PraiseMessageModel> list = praiseMessageService.getProductInfoAuth(praiseMessageModel, page);
//                if(null!=list){
//                    for (int i = 0; i < list.size(); i++) {
//                        PraiseMessageModel bb=list.get(i);
//                        //修改状态
//                        int sendGrade = judgeGrade(bb.getPraiseAccount());
//                        
//                        if(sendGrade>0 && bb.getLastSendGrade()!=null && sendGrade>bb.getLastSendGrade()){
//                            PraiseMessageModel pp = new PraiseMessageModel();
//                            pp.setProductId(bb.getProductId());
//                            //查询商品
//                            PraiseMessageModel kk = praiseMessageService.getProductInfo(bb.getProductId());
//                            if(kk!=null){
//                                pp.setProductCode(kk.getProductCode());
//                                pp.setProductName(kk.getProductName());
//                                pp.setSupplierPhone(kk.getSupplierPhone());
//                                pp.setOperatorPhone(kk.getOperatorPhone());
//                                pp.setSendGrade(sendGrade);
//                                pp.setSendStatus(1);
//                                pp.setUpdateTime(new Date().getTime()/1000);
//                            }else{
//                                pp.setSendStatus(3);
//                            }
//                            praiseMessageService.updatePraiseMessage(pp);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                logger.error(e);
//                throw new RuntimeException(e);
//            } finally{
//                IMP_Lock=true;
//            }
//        }else{
//            logger.info("importPraiseMessageCmd  imoprt  Last time not  end");
//        }
    }
    
    private Integer judgeGrade(Integer account){
        int grade = 0;
        if(account>=oneGrade && account<twoGrade){
            grade = 1;
        }else if(account>=twoGrade && account<threeGrade){
            grade = 2;
        }else if(account>=threeGrade && account<fourGrade){
            grade = 3;
        }else if(account>=fourGrade && account<fiveGrade){
            grade = 4;
        }else if(account>fiveGrade){
            grade = 5;
        }
        return grade;
    }
    
    private void send(PraiseMessageModel bb,String phone){
//        try {
//            // 发送短信
//            SMSPO smsPO = new SMSPO();
//            
//            String smsContentTemplate = ProductUtil.getStringToUtf(messageContent).replace("#productCode#", bb.getProductCode());
//            if(bb.getProductName().length()>10){
//                smsContentTemplate = smsContentTemplate.replace("#productName#", bb.getProductName().substring(0,10).trim()+"...");
//            }else{
//                smsContentTemplate = smsContentTemplate.replace("#productName#", bb.getProductName());
//            }
//            smsContentTemplate = smsContentTemplate.replace("#praiseAccount#", String.valueOf(bb.getPraiseAccount()));
//                                                                                  
//            smsPO.setContent(smsContentTemplate);
//            smsPO.setDesMobiles(phone);
//            smsPO.setStatus("1");
//
//            ResponseObject messageManagerResponsePO = messageManager
//                    .addSMS(smsPO);
//            logger.info("messageManager addSMS requestPO="
//                    + JSONUtil.toString(smsPO) + ",responsePO="
//                    + JSONUtil.toString(messageManagerResponsePO));
//            logger.info("点赞发送短信成功! 商品编号：  "+bb.getProductCode()+", 商品名称："+bb.getProductName()+", 发送号码：" + smsPO.getDesMobiles());
//
//        } catch (Exception e) {
//            
//            logger.error("点赞发送短失败!"+JSONUtil.toString(bb), e);
//            throw new TeshehuiRuntimeException("点赞发送短失败!");
//        }
    }

    @Override
    public void changeRetryNum(PraiseMessageModel praiseMessageModel) {
        try {
            praiseMessageService.changeRetryNum(praiseMessageModel);
        } catch (Exception e) {
            logger.error("修改点赞发送短信重试次数失败！",e);
            throw new ZhaidouRuntimeException("修改点赞发送短信重试次数失败！");
        }
    }
}
