package com.zhaidou.product.manager.impl;

import com.zhaidou.framework.jms.JmsMessageSender;
import com.zhaidou.framework.manager.impl.BaseManagerImpl;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.*;
import com.zhaidou.product.manager.ProductPriceManager;
import com.zhaidou.product.model.*;
import com.zhaidou.product.service.ProductPriceJobService;
import com.zhaidou.product.service.ProductPriceListService;
import com.zhaidou.product.service.ProductPriceLogService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;

@Service("productPriceManager")
public class ProductPriceManagerImpl extends BaseManagerImpl implements ProductPriceManager {
	
	
	public static final Log logger = LogFactory.getLog(ProductPriceManagerImpl.class);

	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条
	
	
	private static  boolean JOB_Lock = true;	 
	
	private static  boolean IMP_Lock = true;	 
	
	private static  boolean CRE_Lock = true;
	
	private static  boolean MEMCACHE_Lock = true;
	
	private static  boolean MQ_Lock = true;
	
	
	@Resource
	private ProductPriceJobService productPriceJobService;
	
	@Resource
	private ProductPriceListService productPriceListService;

	@Resource
	private ProductPriceLogService productPriceLogService;
	
	@Resource
	private ProductPriceDao productPriceDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private ProductSkuDao productSkuDao;
	@Resource
	private ProductViewDao proudctViewDao;
	@Resource
	private SkuViewDao skuViewDao;
	
//	@Resource
//	private ProductSkuSupplierService productSkuSupplierService;
	
	@Resource(name="jmsMessageSender")
    private JmsMessageSender productInfoChangeSender;

	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		// doit
		if(JOB_Lock){
			JOB_Lock=false;
		try {
			logger.info("AutoProductPriceCmd:     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductPriceJobModel productPriceJobModel = new ProductPriceJobModel();
			productPriceJobModel.setRemainder(configuration.getCurrentNode());
			productPriceJobModel.setTotal(configuration.getTotalNode());
			productPriceJobModel.setStaus(0);
			List<ProductPriceJobModel> list=productPriceJobService.getProductPriceJob(productPriceJobModel,page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list && list.size()>0){
                Map<String,Object> statusSupplierMap = new HashMap<String, Object>();
                Map<String,Object> statusJobMap = new HashMap<String, Object>();
                List<Long> jobIds = new ArrayList<Long>();
                List<String> codes = new ArrayList<String>();
			    
    			for (int i = 0; i < list.size(); i++) {
    				ProductPriceJobModel bb=list.get(i);
    				map.put("price_"+list.get(i).getProductSkuCode(), list.get(i).getProductSkuCode());
    				//修改价格
    				ProductPriceModel  pp=new ProductPriceModel();
    				pp.setCostPrice(bb.getCostPrice());
    				pp.setTb(bb.getTb());
    				pp.setTshPrice(bb.getTshPrice());			
    				pp.setMarketPrice(bb.getMarketPrice());
    				pp.setProductSkuCode(bb.getProductSkuCode());
    				productPriceDao.update(pp);
    				
    				//判断 如果是修改了主SKU的价格 同步到t_product 表
    				ProductSkuModel skuModel = new ProductSkuModel();
    				skuModel.setProductSkuCode(bb.getProductSkuCode());
    				skuModel = productSkuDao.queryOne(skuModel);
    				if(skuModel!=null && skuModel.getMainSku()!=null && skuModel.getMainSku()==1){
    				    ProductModel product = new ProductModel();
    	                product.setProductCode(bb.getProductSkuCode().substring(0, 12));
    	                product.setCostPrice(bb.getCostPrice());
//    	                product.setTb(bb.getTb());
    	                product.setTshPrice(bb.getTshPrice());           
    	                product.setMarketPrice(bb.getMarketPrice());
    	                productDao.updateByCode(product);
    	                
    	                proudctViewDao.updateMallViewPrice(product);
    				}
    				
                    codes.add(bb.getProductSkuCode());
    				//跟新JOB
    				jobIds.add(bb.getProductPriceJob());
    			}
    			
    			Long deleteViewStart = new Date().getTime()/1000;
    			skuViewDao.deleteSkuViewBySkuCode(codes);
                Long deleteViewEnd = new Date().getTime()/1000;
                logger.info("priceTime, doJob, 删除SkuView横表,总记录数："+codes.size()+" ,花费时间："+(deleteViewEnd-deleteViewStart)+"秒");
    			
                Long insertViewStart = new Date().getTime()/1000;
                skuViewDao.addSkuViewList(codes);
                Long insertViewEnd = new Date().getTime()/1000;
                logger.info("priceTime, doJob, 插入SkuView横表,总记录数："+codes.size()+" ,花费时间："+(insertViewEnd-insertViewStart)+"秒");
    			
    			
    			statusSupplierMap.put("status", 2);
    			statusSupplierMap.put("list", codes);
//    			Long updateSupplierStart = new Date().getTime()/1000;
//    			productSkuSupplierService.updateStatusList(statusSupplierMap);
//                Long updateSupplierEnd = new Date().getTime()/1000;
//                logger.info("priceTime, doJob, 修改SupplierSku状态,1-2,总记录数："+codes.size()+" ,花费时间："+(updateSupplierEnd-updateSupplierStart)+"秒");
    			
    			
    			statusJobMap.put("status", 2);
    			statusJobMap.put("list", jobIds);
    			Long updateJobStart = new Date().getTime()/1000;
    			productPriceJobService.updateStatusList(statusJobMap);
                Long updateJobEnd = new Date().getTime()/1000;
                logger.info("priceTime, doJob, 修改价格JOB状态,0-2,总记录数："+jobIds.size()+" ,花费时间："+(updateJobEnd-updateJobStart)+"秒");
    			
			}
			logger.info("AutoProductPriceCmd:end     do databae");
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			JOB_Lock=true;
		}
        }else{
        	logger.info("AutoImportPriceCmd  job  Last time not  end");
        }
	}

	@Override
	public void imoprt(ProductObjBufCondiction configuration) {
		// doit 
		        if(IMP_Lock){
		        IMP_Lock=false;
				try {
					logger.info("AutoImportPriceCmd:     do imoprt databae");
					Page page = new Page();
					page.setNumPerPage(ROWS_ONE_TIME);
					ProductPriceListModel productPriceListModel = new ProductPriceListModel();
					productPriceListModel.setRemainder(configuration.getCurrentNode());
					productPriceListModel.setTotal(configuration.getTotalNode());
					productPriceListModel.setStatus(0);
					List<ProductPriceListModel> list=productPriceListService.getProductPriceList(productPriceListModel,page);
					
					Map<String,Object> statusMap = new HashMap<String, Object>();
					List<Long> successIds = new ArrayList<Long>();
					List<Long> errorIds = new ArrayList<Long>();
					List<String> skuCodes = new ArrayList<String>();
					
					if(null!=list && list.size()>0){
					    Long updateSuccess1 = new Date().getTime()/1000;
    					for (int i = 0; i < list.size(); i++) {
    						ProductPriceListModel bb=list.get(i);
    						
    						skuCodes.add(bb.getProductSkuCode());
    						
//    						List<ProductPriceListModel> priceList = productPriceListService.getPriceListBySkuCode(bb.getProductSkuCode());
//    						for(int y=0;y<priceList.size();y++){
//    						    if(priceList.get(y).getStatus()==1){
//    						        priceList.get(y).setStatus(6);
//    						        priceList.get(y).setReason("系统自动处理旧数据");
//    						        productPriceListService.updateProductPriceList(priceList.get(y));
//    						    }
//    						} 
    						
    						ProductPriceModel productPriceModel=new ProductPriceModel();
    						productPriceModel.setProductSkuCode(bb.getProductSkuCode());
    						ProductPriceModel  pp=productPriceListService.getProductPriceById(productPriceModel);
    						//检验
    						if(pp!=null){
    						    successIds.add(bb.getProductPriceList());
    						}else{
    						    errorIds.add(bb.getProductPriceList());
    						}
    						
    					}
    					 Long updateSuccess2 = new Date().getTime()/1000;
    					 
    					 logger.info("priceTime, imoprt,过滤, 总记录数："+list.size()+" ,花费时间："+(updateSuccess2-updateSuccess1)+"秒");
    					 
    					 Long updateOldStart = new Date().getTime()/1000;
                         productPriceListService.updateOldStatusList(skuCodes);
                         Long updateOldEnd = new Date().getTime()/1000;
                         logger.info("priceTime, imoprt, 修改价格审核表旧数据状态, 1-6, 总记录数："+skuCodes.size()+" ,花费时间："+(updateOldEnd-updateOldStart)+"秒");
    					 
//    					//修改状态
    					Long updateSuccessStart = new Date().getTime()/1000;
    					productPriceListService.updateStatusPriceList(successIds);
                        Long updateSuccessEnd = new Date().getTime()/1000;
                        logger.info("priceTime, imoprt, 修改价格审核表状态, 0-1, 总记录数："+successIds.size()+" ,花费时间："+(updateSuccessEnd-updateSuccessStart)+"秒");
    					
                        if(errorIds.size()>0){
                            statusMap.put("status", 9);
                            statusMap.put("list", errorIds);
                            Long updateErrorStart = new Date().getTime()/1000;
                            productPriceListService.updateStatusList(statusMap);
                            Long updateErrorEnd = new Date().getTime()/1000;
                            logger.info("priceTime, imoprt, 修改价格审核表状态, 0-9, 总记录数："+errorIds.size()+" ,花费时间："+(updateErrorEnd-updateErrorStart)+"秒");
                        }
					}
				} catch (Exception e) {
					logger.error(e);
					throw new RuntimeException(e);
				} finally{
					IMP_Lock=true;
				}
		        }else{
		        	logger.info("AutoImportPriceCmd  imoprt  Last time not  end");
		        }
		
	}

	@Override
	public void createJob(ProductObjBufCondiction configuration) {
		// doit
		if(CRE_Lock){
			CRE_Lock=false;
				try {
					logger.info("AutoProductPriceCmd:     do createJob databae");
					Page page = new Page();
					page.setNumPerPage(ROWS_ONE_TIME);
					ProductPriceListModel productPriceListModel = new ProductPriceListModel();
					productPriceListModel.setRemainder(configuration.getCurrentNode());
					productPriceListModel.setTotal(configuration.getTotalNode());
					productPriceListModel.setStatus(2);
					List<ProductPriceListModel> list=productPriceListService.getProductPriceList(productPriceListModel,page);
					
					List<ProductPriceJobModel> priceJobList = new ArrayList<ProductPriceJobModel>();
					List<Long> listIds = new ArrayList<Long>();
					Map<String,Object> statusMap = new HashMap<String, Object>();
					if(null!=list && list.size()>0){
					    
    					for (int i = 0; i < list.size(); i++) {
    						ProductPriceListModel bb=list.get(i);
    						//创建job
    						ProductPriceJobModel pp = new ProductPriceJobModel();
    						pp.setCostPrice(bb.getCostPrice());
    						pp.setMarketPrice(bb.getMarketPrice());
    						pp.setTb(bb.getTb());
    						pp.setTshPrice(bb.getTshPrice());
    						pp.setStaus(0);
    						pp.setCreateBy(bb.getCreateBy());
    						pp.setCreateTime(bb.getCreateTime());
    						pp.setCreateUserName(bb.getCreateUserName());
    						
    						pp.setProductPriceList(bb.getProductPriceList());
    						pp.setProductSkuCode(bb.getProductSkuCode());
    						pp.setTimeStart(bb.getTimeStart());
    						priceJobList.add(pp);
    						//修改状态
    						listIds.add(bb.getProductPriceList());
    						//创建下架任务
    					}
    					
    					Long addJobStart = new Date().getTime()/1000;
    					productPriceJobService.addPriceJobList(priceJobList);
    					Long addJobEnd = new Date().getTime()/1000;
    					logger.info("priceTime, createJob, 价格审核通过,插入价格JOB表,总记录数："+priceJobList.size()+" ,花费时间："+(addJobEnd-addJobStart)+"秒");


    					statusMap.put("status", 3);
    					statusMap.put("list", listIds);
    					Long updateStart = new Date().getTime()/1000;
    					productPriceListService.updateStatusList(statusMap);
    					Long updateEnd = new Date().getTime()/1000;
    					logger.info("priceTime, createJob, 修改priceList表状态,2-3,总记录数："+listIds.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
					}
				} catch (Exception e) {
					logger.error(e);
					throw new RuntimeException(e);
				} finally{
					CRE_Lock=true;
				}
		        }else{
		        	logger.info("AutoProductPriceCmd createJob   Last time not  end");
		        }
		
	}

	@Override
	public void sendMemcache(ProductObjBufCondiction configuration) {
		if(MEMCACHE_Lock){
			MEMCACHE_Lock=false;
		try {
			logger.info("AutoProductPriceCmd:     do sendMemcache");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductPriceJobModel productPriceJobModel = new ProductPriceJobModel();
			productPriceJobModel.setRemainder(configuration.getCurrentNode());
			productPriceJobModel.setTotal(configuration.getTotalNode());
			productPriceJobModel.setStaus(2);
			List<ProductPriceJobModel> list=productPriceJobService.getProductPriceJob(productPriceJobModel,page);
			Map<String,String> map=new HashMap<String, String>();
			
            List<Long> ids = new ArrayList<Long>();
            Map<String,Object> statusMap = new HashMap<String, Object>();
            
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    				ProductPriceJobModel bb=list.get(i);
    				map.put("product_sku_view_"+list.get(i).getProductSkuCode(), list.get(i).getProductSkuCode());
    				ids.add(bb.getProductPriceJob());
    			}
    			for (String key : map.keySet()) {
    				// 删除缓存
    				this.getCachedClient().releaseObject(key);
    			}
    			
    			statusMap.put("status",3);
    			statusMap.put("list", ids);
    			
    			Long updateStart = new Date().getTime()/1000;
    			productPriceJobService.updateStatusList(statusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("priceTime, sendMemcache, 修改价格JOB表状态,2-3,总记录数："+ids.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
			}
			logger.info("AutoProductPriceCmd:     do memcache end");

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			MEMCACHE_Lock=true;
		}
        }else{
        	logger.info("AutoImportPriceCmd  sendMemcache job  Last time not  end");
        }
		
	}

	@Override
	public void sendMq(ProductObjBufCondiction configuration) {
		if(MQ_Lock){
			MQ_Lock=false;
		try {
			logger.info("AutoProductPriceCmd:     do sendMq");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductPriceJobModel productPriceJobModel = new ProductPriceJobModel();
			productPriceJobModel.setRemainder(configuration.getCurrentNode());
			productPriceJobModel.setTotal(configuration.getTotalNode());
			productPriceJobModel.setStaus(3);
			List<ProductPriceJobModel> list=productPriceJobService.getProductPriceJob(productPriceJobModel,page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list && list.size()>0){
			    List<ProductPriceLogModel> priceLogList = new ArrayList<ProductPriceLogModel>();
			    Map<String,Object> listStatusMap = new HashMap<String, Object>();
			    List<Long> listIds = new ArrayList<Long>();
			    List<Long> jobIds = new ArrayList<Long>();
			    
    			for (int i = 0; i < list.size(); i++) {
    				map.put("price_"+list.get(i).getProductSkuCode(), list.get(i).getProductSkuCode());
    				listIds.add(list.get(i).getProductPriceList());
    				jobIds.add(list.get(i).getProductPriceJob());
    				
    				ProductPriceListModel productPriceListModel=new ProductPriceListModel();
                    productPriceListModel.setProductPriceList(list.get(i).getProductPriceList());
                    ProductPriceListModel bb = productPriceListService.getProductPriceListById(productPriceListModel);
                    //插入LOG
                    ProductPriceLogModel productPriceLogModel = new ProductPriceLogModel();
                    productPriceLogModel.setCostPrice(bb.getCostPrice());
                    productPriceLogModel.setTb(bb.getTb());
                    productPriceLogModel.setTshPrice(bb.getTshPrice());         
                    productPriceLogModel.setMarketPrice(bb.getMarketPrice());
                    
                    productPriceLogModel.setCreateBy(bb.getCreateBy());
                    productPriceLogModel.setCreateTime(bb.getCreateTime());
                    productPriceLogModel.setCreateUserName(bb.getCreateUserName());
                    
                    productPriceLogModel.setUpdateBy(bb.getUpdateBy());
                    productPriceLogModel.setUpdateTime(bb.getUpdateTime());
                    productPriceLogModel.setUpdateUserName(bb.getUpdateUserName());
                    
                    productPriceLogModel.setOldCostPrice(bb.getOldCostPrice());
                    productPriceLogModel.setOldMarketPrice(bb.getOldMarketPrice());
                    productPriceLogModel.setOldTb(bb.getOldTb());
                    productPriceLogModel.setOldTshPrice(bb.getOldTshPrice());
                    
                    productPriceLogModel.setProductSkuCode(bb.getProductSkuCode());
                    productPriceLogModel.setStatus(bb.getStatus());
                    productPriceLogModel.setSourceType(bb.getSourceType());
                    productPriceLogModel.setReason(bb.getReason());
                    productPriceLogModel.setTimeStart(bb.getTimeStart());
                    priceLogList.add(productPriceLogModel);
    			}
    			logger.info("AutoProductPriceCmd:     do mq start");
    			for (String key : map.keySet()) {
    				// 发送MQ
    				productInfoChangeSender.sendMessage(false, "price_", map.get(key));
    			}
    			
    			listStatusMap.put("status", 4);
    			listStatusMap.put("synTime", new Date().getTime()/1000);
    			listStatusMap.put("list", listIds);
    			
    			Long updateStart = new Date().getTime()/1000;
    			productPriceListService.updateStatusList(listStatusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("priceTime, sendMq, 修改价格审核状态,3-4,总记录数："+listIds.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
    			
                
                Long deleteStart = new Date().getTime()/1000;
                productPriceJobService.deleteByIds(jobIds);
                Long deleteEnd = new Date().getTime()/1000;
                logger.info("priceTime, sendMq, 删除价格JOB,总记录数："+jobIds.size()+" ,花费时间："+(deleteEnd-deleteStart)+"秒");
    			
                Long addStart = new Date().getTime()/1000;
                productPriceLogService.addPriceLogList(priceLogList);
                Long addEnd = new Date().getTime()/1000;
                logger.info("priceTime, sendMq, 批量插入价格日志,总记录数："+priceLogList.size()+" ,花费时间："+(addEnd-addStart)+"秒");
                
                
    			logger.info("AutoProductPriceCmd:     do mq end");
			}
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			MQ_Lock=true;
		}
        }else{
        	logger.info("AutoImportPriceCmd  sendMq job  Last time not  end");
        }
		
	}

	@Override
	public void priceToSupplier(ProductObjBufCondiction configuration) {
	 // doit
        if(CRE_Lock){
            CRE_Lock=false;
                try {
                    logger.info("AutoProductPriceCmd:     do createJob databae");
                    Page page = new Page();
                    page.setNumPerPage(ROWS_ONE_TIME);
                    ProductPriceListModel productPriceListModel = new ProductPriceListModel();
                    productPriceListModel.setRemainder(configuration.getCurrentNode());
                    productPriceListModel.setTotal(configuration.getTotalNode());
                    productPriceListModel.setStatus(8);
                    List<ProductPriceListModel> list=productPriceListService.getProductPriceList(productPriceListModel,page);
                    if(null!=list && list.size()>0){
                        List<ProductPriceLogModel> priceLogList = new ArrayList<ProductPriceLogModel>();
                        Map<String,Object> listStatusMap = new HashMap<String, Object>();
                        List<Long> listIds = new ArrayList<Long>();
                        
                        for (int i = 0; i < list.size(); i++) {
                            ProductPriceListModel bb=list.get(i);
                            
                            ProductSkuModel productSkuModel = new ProductSkuModel();
                            productSkuModel.setStatus(3);
                            productSkuModel.setReason(bb.getReason());
                            productSkuModel.setProductSkuCode(bb.getProductSkuCode());
//                            productSkuSupplierService.updateStatusBySkuCode(productSkuModel);
                            
                            //修改状态
                            listIds.add(bb.getProductPriceList());
                            
                            //插入LOG
                            ProductPriceLogModel productPriceLogModel = new ProductPriceLogModel();
                            productPriceLogModel.setCostPrice(bb.getCostPrice());
                            productPriceLogModel.setTb(bb.getTb());
                            productPriceLogModel.setTshPrice(bb.getTshPrice());         
                            productPriceLogModel.setMarketPrice(bb.getMarketPrice());
                            
                            productPriceLogModel.setCreateBy(bb.getCreateBy());
                            productPriceLogModel.setCreateTime(bb.getCreateTime());
                            productPriceLogModel.setCreateUserName(bb.getCreateUserName());
                            productPriceLogModel.setUpdateBy(bb.getUpdateBy());
                            productPriceLogModel.setUpdateTime(bb.getUpdateTime());
                            productPriceLogModel.setUpdateUserName(bb.getUpdateUserName());
                            
                            productPriceLogModel.setOldCostPrice(bb.getOldCostPrice());
                            productPriceLogModel.setOldMarketPrice(bb.getOldMarketPrice());
                            productPriceLogModel.setOldTb(bb.getOldTb());
                            productPriceLogModel.setOldTshPrice(bb.getOldTshPrice());
                            
                            productPriceLogModel.setProductSkuCode(bb.getProductSkuCode());
                            productPriceLogModel.setStatus(bb.getStatus());
                            productPriceLogModel.setSourceType(bb.getSourceType());
                            productPriceLogModel.setReason(bb.getReason());
                            productPriceLogModel.setTimeStart(bb.getTimeStart());
                            priceLogList.add(productPriceLogModel);
                        }
                        listStatusMap.put("status", 5);
                        listStatusMap.put("synTime", new Date().getTime()/1000);
                        listStatusMap.put("list", listIds);
                        
                        Long updateStart = new Date().getTime()/1000;
                        productPriceListService.updateStatusList(listStatusMap);
                        Long updateEnd = new Date().getTime()/1000;
                        logger.info("priceTime, priceToSupplier, 修改价格审核状态,8-5,总记录数："+listIds.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
                    
                        Long addStart = new Date().getTime()/1000;
                        productPriceLogService.addPriceLogList(priceLogList);
                        Long addEnd = new Date().getTime()/1000;
                        logger.info("priceTime, priceToSupplier, 批量插入价格日志,总记录数："+priceLogList.size()+" ,花费时间："+(addEnd-addStart)+"秒");
                    }
                } catch (Exception e) {
                    logger.error(e);
                    throw new RuntimeException(e);
                } finally{
                    CRE_Lock=true;
                }
                }else{
                    logger.info("AutoProductPriceCmd createJob   Last time not  end");
                }
		
	}
	 
}
