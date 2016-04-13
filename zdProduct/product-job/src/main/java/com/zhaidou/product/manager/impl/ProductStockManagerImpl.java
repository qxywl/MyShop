package com.zhaidou.product.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhaidou.framework.jms.JmsMessageListenerContainer;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductDao;
import com.zhaidou.product.dao.ProductOperateDao;
import com.zhaidou.product.dao.ProductStockDao;
import com.zhaidou.product.manager.ProductStockManager;
import com.zhaidou.product.model.ProductModel;
import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductOperateModel;
import com.zhaidou.product.model.ProductShelvesModel;
import com.zhaidou.product.model.ProductStockModel;
import com.zhaidou.product.model.ProductStockQueueModel;
import com.zhaidou.product.service.ProductShelvesService;


@Service("productStockManager")
public class ProductStockManagerImpl   extends JmsMessageListenerContainer implements ProductStockManager {
	
	public static final Log logger = LogFactory.getLog(ProductStockManagerImpl.class);
	

	private static final Integer ROWS_ONE_TIME = 500;	//一次处理多少条
	
    private static  boolean JOB_Lock = true;	 
	
	@Resource
	private ProductStockDao productStockDao;
	
	@Resource
	private ProductOperateDao productOperateDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProductShelvesService productShelvesService;
	
	@Override
	public void handleMessage(Object message) {
        logger.info("mq  start stock"+message);
		try {
		JSONObject  stockInfo=JSONObject.parseObject(message.toString());
		ProductStockQueueModel productStockQueueModel = new ProductStockQueueModel();
		productStockQueueModel.setSkuCode(stockInfo.getString("skuCode"));
		productStockQueueModel.setProductCode(stockInfo.getString("productCode"));
		productStockQueueModel.setVirtualStock(stockInfo.getDouble("virtualStock"));
		productStockQueueModel.setManualStock(stockInfo.getDouble("manStock"));
		productStockQueueModel.setEntityStock(stockInfo.getDouble("entityStock"));
		productStockQueueModel.setStock(stockInfo.getDouble("stockCount"));
		productStockQueueModel.setUpType(stockInfo.getIntValue("upType"));
		productStockDao.insertQueue(productStockQueueModel);
		} catch (Exception e) {
			logger.error("mq  error stock"+message +e);
			throw new RuntimeException();
		}
		 logger.info("mq  end stock"+message);
	}
	
	@Override
	public void doJob(ProductObjBufCondiction configuration) {
		if(JOB_Lock){
			JOB_Lock=false;
		try {
			logger.info("AutoProductStockCmd:     do databae");
			Page page = new Page();
			page.setNumPerPage(ROWS_ONE_TIME);
			ProductStockQueueModel productStockQueueModel = new ProductStockQueueModel();
			productStockQueueModel.setRemainder(configuration.getCurrentNode());
			productStockQueueModel.setTotal(configuration.getTotalNode());
			List<ProductStockQueueModel> list = productStockDao.queryQueueListPage(productStockQueueModel,page.getPageNum(), page.getNumPerPage());
			if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				ProductStockQueueModel bb=list.get(i);
				logger.info("AutoProductStockCmd  sku"+bb.getSkuCode());
				changeStock(bb);
				productStockDao.deleteQueue(bb);
			}
			}
		} catch (Exception e) {
			logger.error("AutoProductStockCmd error"+e);
		}finally{
			JOB_Lock=true;
		}
        }else{
        	logger.info("AutoProductStockCmd  job  Last time not  end");
        }
	}
	@Override
	public void changeStock(ProductStockQueueModel productStockQueueModel) {
		try {
		String  sku=productStockQueueModel.getSkuCode();
		String  productCode=productStockQueueModel.getProductCode();
		Double  VStock=productStockQueueModel.getVirtualStock();
		Double  MStock=productStockQueueModel.getManualStock();
		Double  EStock=productStockQueueModel.getEntityStock();
		Double  productStock=productStockQueueModel.getStock();
		int  type=productStockQueueModel.getUpType();
		//更新库存
		ProductStockModel t=new ProductStockModel();
		t.setSkuCode(sku);
		t.setEntityStock(EStock);
		t.setVirtualStock(VStock);
		t.setManualStock(MStock);
		productStockDao.update(t);
		//查询是否自动上架
		ProductModel productModel=new ProductModel();
		productModel.setProductCode(productCode);
		productModel =productDao.getProductByProductCode(productModel);
		//如果总库存为0 则下架
		if(productStock==0){
			if(productModel.getProductShelves()==1l){
			ProductShelvesModel pp=new ProductShelvesModel();
			pp.setProductCode(productCode);
			pp.setShelvesStatus(0);
			pp.setStatus(0);
			pp.setCreateBy(1l);
	        pp.setCreateUserName("admin");
	        pp.setCreateTime(new Date().getTime()/1000);
	        pp.setSourceType(1);
			pp.setReason("stock_0");
			productShelvesService.addProductShelves(pp);
			//设置自动上架
			ProductOperateModel productOperateModel=new ProductOperateModel();
			productOperateModel.setProductId(productModel.getProductId());
			productOperateModel.setProductAutoUp(1l);
			productOperateDao.update(productOperateModel);
			}
			
		}else{
			if(type==0&&productModel.getProductShelves()==0l&& productModel.getStaus()!=null && productModel.getStaus()==1){
				ProductShelvesModel pp=new ProductShelvesModel();
				pp.setProductCode(productCode);
				pp.setShelvesStatus(1);
				pp.setStatus(0);
				pp.setCreateBy(1l);
		        pp.setCreateUserName("admin");
		        pp.setCreateTime(new Date().getTime()/1000);
		        pp.setSourceType(1);
				pp.setReason("stock_not_0");
				productShelvesService.addProductShelves(pp);
			}
		}
		} catch (Exception e) {
			logger.error("AutoProductStockCmd changeStock error"+e);
		}
	}
}
