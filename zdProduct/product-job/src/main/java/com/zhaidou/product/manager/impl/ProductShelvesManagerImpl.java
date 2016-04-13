package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.jms.JmsMessageSender;
import com.zhaidou.framework.manager.impl.BaseManagerImpl;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductDao;
import com.zhaidou.product.dao.ProductOperateDao;
import com.zhaidou.product.dao.ProductViewDao;
import com.zhaidou.product.manager.ProductShelvesManager;
import com.zhaidou.product.model.ProductModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductOperateModel;
import com.zhaidou.product.model.ProductPriceModel;
import com.zhaidou.product.model.ProductShelvesJobModel;
import com.zhaidou.product.model.ProductShelvesLogModel;
import com.zhaidou.product.model.ProductShelvesModel;
import com.zhaidou.product.service.ProductService;
import com.zhaidou.product.service.ProductShelvesJobService;
import com.zhaidou.product.service.ProductShelvesLogService;
import com.zhaidou.product.service.ProductShelvesService;
import com.zhaidou.product.service.ProductSkuService;

@Service("productShelvesManager")
public class ProductShelvesManagerImpl  extends  BaseManagerImpl implements ProductShelvesManager {

	public static final Log logger = LogFactory.getLog(ProductShelvesManagerImpl.class);
	
	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条
	
    private static  boolean JOB_Lock = true;	 
	
	private static  boolean IMP_Lock = true;	 
	
	private static  boolean CRE_Lock = true;	 
	
    private static  boolean MEMCACHE_Lock = true;
	
	private static  boolean MQ_Lock = true;
	
    private static  boolean VIEW_Lock = true;
	
	
	
	@Resource
	private ProductShelvesJobService productShelvesJobService;
	
	@Resource
    private ProductDao productDao;
	
	@Resource
    private ProductViewDao productViewDao;
	
	@Resource
	private ProductShelvesLogService productShelvesLogService;
	
	@Resource
	private ProductShelvesService productShelvesService;
	
	@Resource
    private ProductService productService;
	
	@Resource(name="jmsMessageSender")
    private JmsMessageSender productInfoChangeSender;
	
	
	@Resource
	private ProductOperateDao productOperateDao;
	
	@Resource
	private ProductSkuService productSkuService;
	
	
	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if(JOB_Lock){
			JOB_Lock=false;
		try {
			logger.info("AutoProductShelvesCmd:     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductShelvesJobModel productShelvesJobModel = new ProductShelvesJobModel();
			productShelvesJobModel.setRemainder(configuration.getCurrentNode());
			productShelvesJobModel.setTotal(configuration.getTotalNode());
			productShelvesJobModel.setStatus(0);
			List<ProductShelvesJobModel> list = productShelvesJobService.getProductShelvesJob(productShelvesJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list && list.size()>0){
			    Map<String,Object> statusMap = new HashMap<String, Object>();
			    List<Long> ids = new ArrayList<Long>();
			    
    			for (int i = 0; i < list.size(); i++) {
    				ProductShelvesJobModel bb=list.get(i);
    				map.put("shelves_"+list.get(i).getProductCode(), list.get(i).getProductCode());
    				
    				ProductModel productModelInfo=new  ProductModel();
    				productModelInfo.setProductCode(bb.getProductCode());
    				ProductModel pp=productService.getProductById(productModelInfo);
    				if(null!=pp){
        				ProductModel productModel = new ProductModel();
        				productModel.setProductId(pp.getProductId());;
        				productModel.setProductShelves(bb.getShelvesStatus());
        				if(bb.getShelvesStatus()==1){
        				if(null==pp.getFirstShelves()){
        					productModel.setFirstShelves(bb.getCreateBy());
        					productModel.setFirstShelvesName(bb.getCreateUserName());
        					productModel.setFirstShelvesTime(bb.getCreateTime());
        					productModel.setLastShelves(bb.getCreateBy());
        					productModel.setLastShelvesName(bb.getCreateUserName());
        					productModel.setLastShelvesTime(bb.getCreateTime());
        				}else{
        					productModel.setLastShelves(bb.getCreateBy());
        					productModel.setLastShelvesName(bb.getCreateUserName());
        					productModel.setLastShelvesTime(bb.getCreateTime());
        				}
        				}else{
        					productModel.setDownShelves(bb.getCreateBy());
        					productModel.setDownShelvesName(bb.getCreateUserName());
        					productModel.setDownShelvesTime(bb.getCreateTime());
        				}
        				productService.updateProduct(productModel);
    				}
    				ids.add(bb.getProductShelvesJob());
    			}
    			statusMap.put("status", 1);
    			statusMap.put("list", ids);
    			
    			Long updateStart = new Date().getTime()/1000;
    			productShelvesJobService.updateStatusList(statusMap);
    			Long updateEnd = new Date().getTime()/1000;
    			logger.info("shelvesTime , doJob , 修改ShelvesJob表状态, 0-1 ,总记录数："+ids.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
			}
			logger.info("AutoProductShelvesCmd:    end do databae");
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			JOB_Lock=true;
		}
        }else{
        	logger.info("AutoProductShelvesCmd  job  Last time not  end");
        }
	}

	/**
	 * 处理导入的数据 并记 log
	 */
	@Override
	public void imoprt(ProductObjBufCondiction configuration) {
		if(IMP_Lock){
	        IMP_Lock=false;
		try {
			logger.info("AutoProductShelvesCmd:     do imoprt databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductShelvesModel productShelvesModel = new ProductShelvesModel();
			productShelvesModel.setRemainder(configuration.getCurrentNode());
			productShelvesModel.setTotal(configuration.getTotalNode());
			productShelvesModel.setStatus(0);
			List<ProductShelvesModel> list = productShelvesService.getProductShelves(productShelvesModel, page);
			
			if(null!=list && list.size()>0){
			    Map<String,Object> successStatusMap = new HashMap<String, Object>();
			    Map<String,Object> errorStatusMap1 = new HashMap<String, Object>();
			    Map<String,Object> errorStatusMap2 = new HashMap<String, Object>();
			    List<Long> successIds = new ArrayList<Long>();
			    List<Long> errorIds1 = new ArrayList<Long>();
			    List<Long> errorIds2 = new ArrayList<Long>();
			    List<ProductShelvesJobModel> shelvesJobList = new ArrayList<ProductShelvesJobModel>();
			    List<ProductShelvesLogModel> shelvesLogList = new ArrayList<ProductShelvesLogModel>();
			    
    			for (int i = 0; i < list.size(); i++) {
    				ProductShelvesModel  bb=list.get(i);
                    //判断商品是否完整 不完整不可以上架不创建任务。 且状态失败
                    
                    ProductModel productModelInfo=new ProductModel();
                    productModelInfo.setProductCode(bb.getProductCode());
                    ProductModel pp=productService.getProductById(productModelInfo);
                    if(null!=pp&&pp.getIntegrity()==100){
                        boolean flag = false;
                        
                        //如果 上架需求  则需要判断主SKU的价格是否大于0
                        if(bb.getShelvesStatus()==1){
                            ProductPriceModel priceList = productSkuService.getSkuPriceByProductCode(bb.getProductCode());
                            if(priceList!=null){
                                if(priceList.getTshPrice()!=null){
                                    flag = true;
                                }
                            }
                        }else{
                            flag = true;
                        }
                        
                        if(flag){
                            successIds.add(bb.getProductShelvesId());
                            
                            ProductShelvesJobModel productShelvesJobModel = new ProductShelvesJobModel();
                            productShelvesJobModel.setProductCode(bb.getProductCode());
                            productShelvesJobModel.setCreateTime(bb.getCreateTime());
                            productShelvesJobModel.setStatus(0);
                            productShelvesJobModel.setProductId(bb.getProductId());
                            productShelvesJobModel.setProductShelvesId(bb.getProductShelvesId());
                            productShelvesJobModel.setCreateBy(bb.getCreateBy());
                            productShelvesJobModel.setCreateUserName(bb.getCreateUserName());
                            productShelvesJobModel.setShelvesStatus(Long.valueOf(bb.getShelvesStatus()));
                            productShelvesJobModel.setTimeStart(bb.getTimeStart());
                            shelvesJobList.add(productShelvesJobModel);
                            
                            //记录LOG
                            ProductShelvesLogModel productShelvesLogModel = new ProductShelvesLogModel();
                            productShelvesLogModel.setProductId(bb.getProductId());
                            productShelvesLogModel.setProductName(bb.getProductName());
                            productShelvesLogModel.setProductCode(bb.getProductCode());
                            productShelvesLogModel.setShelvesStatus(bb.getShelvesStatus());
                            productShelvesLogModel.setCreateBy(bb.getCreateBy());
                            productShelvesLogModel.setCreateUserName(bb.getCreateUserName());
                            productShelvesLogModel.setCreateTime(bb.getCreateTime());
                            productShelvesLogModel.setTimeStart(bb.getTimeStart());
                            productShelvesLogModel.setStatus(4);
                            shelvesLogList.add(productShelvesLogModel);
                            
                            productShelvesModel.setProductCode(bb.getProductCode());
                            
                            if(!"stock_0".equals(bb.getReason())){
                                //设置自动上架
                                ProductOperateModel productOperateModel=new ProductOperateModel();
                                productOperateModel.setProductId(pp.getProductId());
                                productOperateModel.setProductAutoUp(0l);
                                productOperateDao.update(productOperateModel);
                            }
                        }else{
                            errorIds1.add(bb.getProductShelvesId());
                            
                            //记录LOG
                            ProductShelvesLogModel productShelvesLogModel = new ProductShelvesLogModel();
                            productShelvesLogModel.setProductId(bb.getProductId());
                            productShelvesLogModel.setProductName(bb.getProductName());
                            productShelvesLogModel.setProductCode(bb.getProductCode());
                            productShelvesLogModel.setShelvesStatus(bb.getShelvesStatus());
                            productShelvesLogModel.setCreateBy(bb.getCreateBy());
                            productShelvesLogModel.setCreateUserName(bb.getCreateUserName());
                            productShelvesLogModel.setCreateTime(bb.getCreateTime());
                            productShelvesLogModel.setTimeStart(bb.getTimeStart());
                            productShelvesLogModel.setStatus(8);
                            productShelvesLogModel.setReason("商品SKU价格不通过");
                            shelvesLogList.add(productShelvesLogModel);
                        }
                        
                    }else{
                        errorIds2.add(bb.getProductShelvesId());
                        
                        //记录LOG
                        ProductShelvesLogModel productShelvesLogModel = new ProductShelvesLogModel();
                        productShelvesLogModel.setProductId(bb.getProductId());
                        productShelvesLogModel.setProductName(bb.getProductName());
                        productShelvesLogModel.setProductCode(bb.getProductCode());
                        productShelvesLogModel.setShelvesStatus(bb.getShelvesStatus());
                        productShelvesLogModel.setCreateBy(bb.getCreateBy());
                        productShelvesLogModel.setCreateUserName(bb.getCreateUserName());
                        productShelvesLogModel.setCreateTime(bb.getCreateTime());
                        productShelvesLogModel.setTimeStart(bb.getTimeStart());
                        productShelvesLogModel.setStatus(8);
                        productShelvesLogModel.setReason(bb.getReason());
                        shelvesLogList.add(productShelvesLogModel);
                    }
    			} 
    			if(shelvesJobList.size()>0){
    			
    			Long addJobStart = new Date().getTime()/1000;
                productShelvesJobService.addShelvesJobList(shelvesJobList);
                Long addJobEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , imoprt , 插入ShelvesJob表 ,总记录数："+shelvesJobList.size()+" ,花费时间："+(addJobEnd-addJobStart)+"秒");
                
                Long addLogStart = new Date().getTime()/1000;
                productShelvesLogService.addShelvesLogList(shelvesLogList);
                Long addLogEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , imoprt , 插入ShelvesLog表 ,总记录数："+shelvesLogList.size()+" ,花费时间："+(addLogEnd-addLogStart)+"秒");
    			
    			successStatusMap.put("status", 3);
    			successStatusMap.put("list", successIds);
                
                Long updateStart = new Date().getTime()/1000;
                productShelvesService.updateStatusList(successStatusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , imoprt , 修改Shelves表状态, 0-1 ,总记录数："+successIds.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
                
                if(errorIds1.size()>0){
                    errorStatusMap1.put("status", 8);
                    errorStatusMap1.put("reason", "商品SKU价格不通过");
                    errorStatusMap1.put("list", errorIds1);
                    Long updateStart1 = new Date().getTime()/1000;
                    productShelvesService.updateStatusList(errorStatusMap1);
                    Long updateEnd1 = new Date().getTime()/1000;
                    logger.info("shelvesTime , imoprt , 修改Shelves表状态, 0-8 ,总记录数："+errorIds1.size()+" ,花费时间："+(updateEnd1-updateStart1)+"秒");
                }
                
                if(errorIds2.size()>0){
                    errorStatusMap2.put("status", 8);
                    errorStatusMap2.put("reason", "Product not full");
                    errorStatusMap2.put("list", errorIds2);
                    Long updateStart2 = new Date().getTime()/1000;
                    productShelvesService.updateStatusList(errorStatusMap2);
                    Long updateEnd2 = new Date().getTime()/1000;
                    logger.info("shelvesTime , imoprt , 修改Shelves表状态, 0-8 ,总记录数："+errorIds2.size()+" ,花费时间："+(updateEnd2-updateStart2)+"秒");
                }
    		  }
			}
		} catch (Exception e) {
			logger.error("",e);
			throw new RuntimeException(e);
		} finally{
			IMP_Lock=true;
		}
        }else{
        	logger.info("AutoProductShelvesCmd  imoprt  Last time not  end");
        }
		
	}

	@Override
	public void createJob(ProductObjBufCondiction configuration) {
		if(CRE_Lock){
			CRE_Lock=false;
		try {
			 
		} catch (Exception e) {
			logger.error(e);
		} finally{
			CRE_Lock=true;
		}
        } 
		
	}

	@Override
	public void sendMemcache(ProductObjBufCondiction configuration) {
		if(MEMCACHE_Lock){
			MEMCACHE_Lock=false;
		try {
			logger.info("AutoProductShelvesCmd:     do sendMemcache");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductShelvesJobModel productShelvesJobModel = new ProductShelvesJobModel();
			productShelvesJobModel.setRemainder(configuration.getCurrentNode());
			productShelvesJobModel.setTotal(configuration.getTotalNode());
			productShelvesJobModel.setStatus(2);
			List<ProductShelvesJobModel> list = productShelvesJobService.getProductShelvesJob(productShelvesJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			Map<String,Object> statusMap = new HashMap<String, Object>();
			List<Long> jobIds = new ArrayList<Long>();
			
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    			    jobIds.add(list.get(i).getProductShelvesJob());
    				map.put("shelves_"+list.get(i).getProductCode(), list.get(i).getProductCode());
    			}
    			for (String key : map.keySet()) {
    				// 删除缓存
    				this.getCachedClient().releaseObject(key);
    			}
    			
    			statusMap.put("status", 3);
    			statusMap.put("list", jobIds);
    			Long updateStart = new Date().getTime()/1000;
                productShelvesJobService.updateStatusList(statusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , sendMemcache , 修改ShelvesJob表 状态,2-3,总记录数："+jobIds.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
			}
			logger.info("AutoProductShelvesCmd:     do sendMemcache end");
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			MEMCACHE_Lock=true;
		}
        }else{
        	logger.info("AutoProductShelvesCmd  job  Last time not  end");
        }
		
	}

	@Override
	public void sendMq(ProductObjBufCondiction configuration) {
		if(MQ_Lock){
			MQ_Lock=false;
		try {
			logger.info("AutoProductShelvesCmd:     do sendMq");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductShelvesJobModel productShelvesJobModel = new ProductShelvesJobModel();
			productShelvesJobModel.setRemainder(configuration.getCurrentNode());
			productShelvesJobModel.setTotal(configuration.getTotalNode());
			productShelvesJobModel.setStatus(3);
			List<ProductShelvesJobModel> list = productShelvesJobService.getProductShelvesJob(productShelvesJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			Map<String,Object> statusMap = new HashMap<String, Object>();
            List<Long> ids = new ArrayList<Long>();
            List<Long> jobIds = new ArrayList<Long>();
            
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    			    ids.add(list.get(i).getProductShelvesId());
    			    jobIds.add(list.get(i).getProductShelvesJob());
    				map.put("shelves_"+list.get(i).getProductCode(), list.get(i).getProductCode());
    			}
    			for (String key : map.keySet()) {
    				// 发送MQ
    				productInfoChangeSender.sendMessage(false, "shelves_", map.get(key));
    			}
    			for (int i = 0; i < list.size(); i++) {
    				ProductShelvesModel  bb=new ProductShelvesModel();
    				bb.setProductShelvesId(list.get(i).getProductShelvesId());
    				bb.setStatus(4);
    				bb.setSynTime(new Date().getTime()/1000);
    				productShelvesService.updateProductShelves(bb);
    				productShelvesJobService.deleteById(list.get(i));
    			}
    			
    			statusMap.put("status", 4);
    			statusMap.put("synTime", new Date().getTime()/1000);
                statusMap.put("list", ids);
                Long updateStart = new Date().getTime()/1000;
                productShelvesJobService.updateStatusList(statusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , sendMq , 修改Shelves表 状态,3-4,总记录数："+ids.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
                
                Long deleteStart = new Date().getTime()/1000;
                productShelvesJobService.deleteByIds(jobIds);
                Long deleteEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , sendMq , 删除ShelvesJob,总记录数："+jobIds.size()+" ,花费时间："+(deleteEnd-deleteStart)+"秒");
			}
			logger.info("AutoProductShelvesCmd:     do sendMq end");
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			MQ_Lock=true;
		}
        }else{
        	logger.info("AutoProductShelvesCmd  sendMq job  Last time not  end");
        }
		
	}

	@Override
	public void createView(ProductObjBufCondiction configuration) {
		if(VIEW_Lock){
			VIEW_Lock=false;
		try {
			logger.info("AutoProductShelvesCmd:     do createView");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductShelvesJobModel productShelvesJobModel = new ProductShelvesJobModel();
			productShelvesJobModel.setRemainder(configuration.getCurrentNode());
			productShelvesJobModel.setTotal(configuration.getTotalNode());
			productShelvesJobModel.setStatus(1);
			List<ProductShelvesJobModel> list = productShelvesJobService.getProductShelvesJob(productShelvesJobModel, page);
			Map<String,Object> statusMap = new HashMap<String, Object>();
            List<Long> ids = new ArrayList<Long>();
            List<String> codes = new ArrayList<String>();
            
			if(null!=list && list.size()>0){
    			for (int i = 0; i < list.size(); i++) {
    			    ids.add(list.get(i).getProductShelvesJob());
    			    codes.add(list.get(i).getProductCode());
    			}
    			if(codes.size()>0){
                    Long viewStart = new Date().getTime()/1000;
                    productViewDao.deleteSkuViewByProductCodes(codes);
                    productViewDao.addSkuViewList(codes);
                    productViewDao.deleteMallViewByProductCodes(codes);
                    productViewDao.addMallViewList(codes);
                    productViewDao.updateMallViewZeroType(codes);
                    Long viewEnd  = new Date().getTime()/1000;
                    logger.info("shelvesTime, createView, 横表处理数据："+codes.size()+"条,花费时间："+(viewEnd-viewStart)+"秒");
                }
    			
    			statusMap.put("status", 2);
    			statusMap.put("list", ids);
    			Long updateStart = new Date().getTime()/1000;
                productShelvesJobService.updateStatusList(statusMap);
                Long updateEnd = new Date().getTime()/1000;
                logger.info("shelvesTime , createView , 修改ShelvesJob表 状态,1-2,总记录数："+ids.size()+" ,花费时间："+(updateEnd-updateStart)+"秒");
			}
			logger.info("AutoProductShelvesCmd:     do createView  end");
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			VIEW_Lock=true;
		}
        }else{
        	logger.info("AutoProductShelvesCmd  job  Last time not  end");
        }
		
	}

}
