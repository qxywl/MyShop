package com.zhaidou.product.manager.impl;

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
import com.zhaidou.product.manager.ProductOtherManager;
import com.zhaidou.product.model.ProductInfoJobModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductOtherJobModel;
import com.zhaidou.product.service.ProductInfoJobService;
import com.zhaidou.product.service.ProductInfoLogService;
import com.zhaidou.product.service.ProductOtherJobService;

@Service("productOtherManager")
public class ProductOtherManagerImpl extends  BaseManagerImpl  implements ProductOtherManager {
	
	
    public static final Log logger = LogFactory.getLog(ProductOtherManagerImpl.class);
	
	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条
	
	private static  boolean BRAND_Lock = true;	 
		
    private static  boolean CAT_Lock = true;	 
		
	private static  boolean ATTR_Lock = true;	
	
	@Resource
	private ProductOtherJobService productOtherJobService;
	
	@Resource(name="jmsMessageSender")
    private JmsMessageSender productInfoChangeSender;
 
	

	@Override
	public void doJobBrand(ProductObjBufCondiction configuration) {
		if(BRAND_Lock){
			BRAND_Lock=false;
		try {
			logger.info("ProductOtherManagerImpl:doBrandJob     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductOtherJobModel productOtherJobModel = new ProductOtherJobModel();
			productOtherJobModel.setRemainder(configuration.getCurrentNode());
			productOtherJobModel.setTotal(configuration.getTotalNode());
			productOtherJobModel.setType(1l);
			List<ProductOtherJobModel> list = productOtherJobService.getProductOtherJob(productOtherJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				map.put("brand_"+list.get(i).getOtherCode(), list.get(i).getOtherCode());
				
			}
			logger.info("ProductOtherManagerImpl:doBrandJob     do memcache  start");
			logger.info("ProductOtherManagerImpl:doBrandJob     do mq  start");
			for (String key : map.keySet()) {
				// 删除缓存
				this.getCachedClient().releaseObject(key);
				// 发送MQ
				productInfoChangeSender.sendMessage(true, "brand_", map.get(key));
				logger.info("ProductOtherManagerImpl:doBrandJob     do mq memcache key:"+key);
			}
			logger.info("ProductOtherManagerImpl:doBrandJob     do memcache  end");
			logger.info("ProductOtherManagerImpl:doBrandJob     do mq  end");
			
			for (int i = 0; i < list.size(); i++) {
				productOtherJobService.deleteById(list.get(i));
			}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			BRAND_Lock=true;
		}
        }else{
        	logger.info("ProductOtherManagerImpl:doBrandJob    Last time not  end");
        }

	}

	@Override
	public void doJobCat(ProductObjBufCondiction configuration) {
		if(CAT_Lock){
			CAT_Lock=false;
		try {
			logger.info("ProductOtherManagerImpl:doCatJob     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductOtherJobModel productOtherJobModel = new ProductOtherJobModel();
			productOtherJobModel.setRemainder(configuration.getCurrentNode());
			productOtherJobModel.setTotal(configuration.getTotalNode());
			productOtherJobModel.setType(2l);
			List<ProductOtherJobModel> list = productOtherJobService.getProductOtherJob(productOtherJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				map.put("cat_"+list.get(i).getOtherCode(), list.get(i).getOtherCode());
				
			}
			logger.info("ProductOtherManagerImpl:doCatJob     do memcache  start");
			logger.info("ProductOtherManagerImpl:doCatJob     do mq  start");
			for (String key : map.keySet()) {
				// 删除缓存
				this.getCachedClient().releaseObject(key);
				// 发送MQ
				productInfoChangeSender.sendMessage(true, "cat_", map.get(key));
				logger.info("ProductOtherManagerImpl:doCatJob     do mq memcache key:"+key);
			}
			logger.info("ProductOtherManagerImpl:doCatJob     do memcache  end");
			logger.info("ProductOtherManagerImpl:doCatJob     do mq  end");
			
			for (int i = 0; i < list.size(); i++) {
				productOtherJobService.deleteById(list.get(i));
			}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			CAT_Lock=true;
		}
        }else{
        	logger.info("ProductOtherManagerImpl:doCatJob     Last time not  end");
        }


	}

	@Override
	public void doJobAttr(ProductObjBufCondiction configuration) {
		if(ATTR_Lock){
			ATTR_Lock=false;
		try {
			logger.info("ProductOtherManagerImpl:doAttrJob     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductOtherJobModel productOtherJobModel = new ProductOtherJobModel();
			productOtherJobModel.setRemainder(configuration.getCurrentNode());
			productOtherJobModel.setTotal(configuration.getTotalNode());
			productOtherJobModel.setType(3l);
			List<ProductOtherJobModel> list = productOtherJobService.getProductOtherJob(productOtherJobModel, page);
			Map<String,String> map=new HashMap<String, String>();
			if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				map.put("attr_"+list.get(i).getOtherCode(), list.get(i).getOtherCode());
				
			}
			logger.info("ProductOtherManagerImpl:doAttrJob     do memcache  start");
			logger.info("ProductOtherManagerImpl:doAttrJob     do mq  start");
			for (String key : map.keySet()) {
				// 删除缓存
				this.getCachedClient().releaseObject(key);
				// 发送MQ
				productInfoChangeSender.sendMessage(true, "attr_", map.get(key));
				logger.info("ProductOtherManagerImpl:doAttrJob     do mq memcache key:"+key);
			}
			logger.info("ProductOtherManagerImpl:doAttrJob     do memcache  end");
			logger.info("ProductOtherManagerImpl:doAttrJob     do mq  end");
			
			for (int i = 0; i < list.size(); i++) {
				productOtherJobService.deleteById(list.get(i));
			}
			}
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}finally{
			ATTR_Lock=true;
		}
        }else{
        	logger.info("ProductOtherManagerImpl:doAttrJob    Last time not  end");
        }


	}

}
