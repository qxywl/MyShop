package com.zhaidou.product.task;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.zhaidou.framework.common.BusinessConstants;
import com.zhaidou.framework.redis.RedisCache;
import com.zhaidou.framework.util.data.MapObj;
import com.zhaidou.framework.util.data.ObjUtil;
import com.zhaidou.product.cache.po.ProductDataCountPO;
import com.zhaidou.product.dao.PraiseMessageDao;
import com.zhaidou.product.model.PraiseMessageModel;
import com.zhaidou.product.model.base.ProductCountModel;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.service.ProductCountService;
import com.zhaidou.product.service.ProductService;

public class ProductCountTask {
	
	private static final Log    logger = LogFactory.getLog(ProductCountTask.class);
	
	@Resource
	private RedisCache redisCache;
	
	@Resource
    private ProductCountService  productCountService;
	
	@Autowired
	private ProductService     productService;
	
	@Resource
	private PraiseMessageDao praiseMessageDao;
	
	/****   统计类型    *****/
	private static String PRODUCT_COUNT_TYPE_VIEWS = "10";//浏览次数
	private static String PRODUCT_COUNT_TYPE_COLLECTS = "20";//收藏次数
	private static String PRODUCT_COUNT_TYPE_CARTS = "30";//加入购物车次数
	private static String PRODUCT_COUNT_TYPE_ORDERS = "40";//添加到订单次数
	private static String PRODUCT_COUNT_TYPE_SALES = "50";//销售次数
	private static String PRODUCT_COUNT_TYPE_COMMENTS = "60";//评论次数
	private static String PRODUCT_COUNT_TYPE_APPLAUDS = "70";//点赞次数
    private static String PRODUCT_COUNT_TYPE_FAVORS = "80";//喜欢次数
	
	@Value("#{propertyConfigurerForProject2['product_count_id_map_cache']}")
    private String PRODUCT_ID_MAP_KEY ;
	
	/**
	 * 扫描productCount缓存，先获取
	 * */
	@SuppressWarnings("unchecked")
	public void scanProductCountCache(){
		
		ProductCountModel countModel = null;
		long startTime = System.currentTimeMillis();
		logger.info("ProductCountTask--扫描缓存任务开始！！！！！！");
		
		Map<Long, Boolean> map = null;
		try {
			map = (Map<Long,Boolean>)redisCache.getObject(PRODUCT_ID_MAP_KEY+"Map");
			logger.info("获取的mapId:"+map);
		} catch (Exception e) {
			logger.error("ProductCountTask--redisCache获取<"+PRODUCT_ID_MAP_KEY+"Map> 失败 ， 结束本次统计任务！！！！！！");
			return;
		}
		
		if(map == null || map.isEmpty()){
			logger.info("ProductCountTask--redisCache获取<"+PRODUCT_ID_MAP_KEY+"Map>为空!！！ 结束本次统计任务  ！！！");
			return ;
		}
		
		String countType;
	    Long countValue;
	    Set<Entry<Long, Boolean>> entrySet = map.entrySet();
	    
	    for (Entry<Long, Boolean> entry : entrySet) {
        
	    	Boolean isDelete = entry.getValue();
	    	Long id = entry.getKey();
	    	if(isDelete == null || isDelete){
	    		logger.info("ProductCountTask--查询productId= "+id+"缓存是删除标志，不需要操作统计 ！！！");
	    		continue;
	    	}
	    	
	    	MapObj mapObj = null;
			try {
				mapObj = (MapObj) redisCache.getObject(PRODUCT_ID_MAP_KEY+id);
			} catch (Exception e) {
				logger.error("ProductCountTask--查询productId= "+id+"缓存是报错！！！",e);
				continue;
			}
			
			if(mapObj == null){
				logger.info("ProductCountTask--查询productId= "+id+"缓存是对象为空！！！");
				continue;
			}
			
			ProductDataCountPO dataCount = ObjUtil.toModel(mapObj, ProductDataCountPO.class);
			if(dataCount ==null){
				logger.info("ProductCountTask--dataCount对象为空！！！");
				continue;
			}
			
			if(!BusinessConstants.BUSINESS_TYPE_MALL.equals(dataCount.getBusinessType())){
				logger.info("ProductCountTask--dataCount.getBusinessType()== "+dataCount.getBusinessType()+"不是商城业务类型！！！");
				continue;
			}
			
			try {
				
				ProductMallViewModel mallViewModel= productService.getMallProductMallViewByProductCode(dataCount.getProductId());
				if(mallViewModel != null){
				    countModel = productCountService.getProductCountByProdId(mallViewModel.getProductId());
				    //根据商品ID 判断是否存在该条数据
				    if(countModel!=null){
				        //存在
				        countModel = new ProductCountModel();
				        countModel.setProductId(mallViewModel.getProductId());
				        countType = dataCount.getCountType();
				        countValue = dataCount.getCountValue();
				        setProductCountValueByType(countModel,countType,countValue);
				        try {
				            productCountService.updateProductCount(countModel);
				        } catch (Exception e) {
				            logger.error("更新统计次数失败，失败原因："+e.getMessage()+countModel, e);
				        }

				    }else{
				        //不存在
				        countModel = new ProductCountModel();
				        countModel.setProductId(mallViewModel.getProductId());
				        countType = dataCount.getCountType();
				        countValue = dataCount.getCountValue();
				        setProductCountValueByType(countModel,countType,countValue);
				        
				        try {
				            productCountService.addProductCount(countModel);
				        } catch (Exception e) {
				            logger.error("插入统计次数失败，失败原因："+e.getMessage()+countModel, e);
				        }
				    }
				    
				    if(PRODUCT_COUNT_TYPE_APPLAUDS.equals(countType)){
				        
				        PraiseMessageModel praiseMessageModel = new PraiseMessageModel();
				        praiseMessageModel.setProductId(countModel.getProductId());
				        praiseMessageModel = praiseMessageDao.queryOne(praiseMessageModel);
				        int praiseNum = Integer.parseInt(String.valueOf(countModel.getApplaudCount()));
				        if(praiseMessageModel!=null){
				            
				            if(praiseNum>praiseMessageModel.getPraiseAccount()){
				                praiseMessageModel.setPraiseAccount(praiseNum);
				                praiseMessageModel.setSendStatus(0);
				                praiseMessageModel.setUpdateTime(new Date().getTime()/1000);
				                praiseMessageDao.update(praiseMessageModel);
				                logger.info("更新点赞短信：商品ID="+countModel.getProductId()+" ,点赞次数="+praiseNum);
				            }
				        }else{
				            praiseMessageModel = new PraiseMessageModel();
				            praiseMessageModel.setProductId(countModel.getProductId());
				            praiseMessageModel.setPraiseAccount(praiseNum);
				            praiseMessageModel.setSendStatus(0);
				            praiseMessageModel.setCreateTime(new Date().getTime()/1000);
				            praiseMessageDao.insert(praiseMessageModel);
				            logger.info("插入点赞短信：商品ID="+countModel.getProductId()+" ,点赞次数="+praiseNum);
				        }
				    }
				}
			}  catch (Exception e) {
				logger.error("扫描任务失败，失败信息" + e.getMessage(), e);
			}
	    }
		
	    long endTime = System.currentTimeMillis();
		logger.info("ProductCountTask--扫描缓存任务结束！！！！！！  总共花费("+((endTime-startTime)/1000)+"秒)");
		
	}

	
	/**
	 * 根据countType类型来设置ProductCountModel的countValue值
	 * countType 类型：10浏览次数，20收藏次数，30加入购物车次数，40添加到订单次数，
	 *               50销售次数，60评论次数，70点赞次数，80喜欢次数
	 * */
	public void setProductCountValueByType(ProductCountModel countModel ,String countType,Long countValue){
		
		if(PRODUCT_COUNT_TYPE_VIEWS.equals(countType)){
			
			countModel.setViews(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_COLLECTS.equals(countType)){
			
			countModel.setCollects(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_CARTS.equals(countType)){
			
			countModel.setCarts(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_ORDERS.equals(countType)){
			
			countModel.setOrders(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_SALES.equals(countType)){
			
			countModel.setSales(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_COMMENTS.equals(countType)){
			
			countModel.setComments(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_APPLAUDS.equals(countType)){
			
			countModel.setApplaudCount(countValue);
			
		}else if(PRODUCT_COUNT_TYPE_FAVORS.equals(countType)){
            
            countModel.setFavorCount(countValue);
            
        }
		
	}
	
}
