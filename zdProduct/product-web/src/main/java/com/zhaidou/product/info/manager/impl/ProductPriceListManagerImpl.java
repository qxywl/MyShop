package com.zhaidou.product.info.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductPriceListManager;
import com.zhaidou.product.info.model.ProductPriceListModel;
import com.zhaidou.product.info.model.ProductPriceLogModel;
import com.zhaidou.product.info.service.ProductPriceListService;
import com.zhaidou.product.info.service.ProductPriceLogService;
import com.zhaidou.product.info.util.InfoUtil;

@Service("productPriceListManager")
public class ProductPriceListManagerImpl implements ProductPriceListManager {
	
	private static final Log logger = LogFactory.getLog(ProductPriceListManagerImpl.class);
	
	@Resource
	private ProductPriceListService  productPriceListService;
	
	@Resource
	private ProductPriceLogService  productPriceLogService;
	 
	@Value("#{propertyConfigurerForProject2['price_sku_code']}")
    private String priceSkuCode;
	@Value("#{propertyConfigurerForProject2['price_cost_price']}")
    private String priceCostPrice;
	@Value("#{propertyConfigurerForProject2['price_tsh_price']}")
    private String priceTshPrice;
	@Value("#{propertyConfigurerForProject2['price_market_price']}")
    private String priceMarketPrice;
	@Value("#{propertyConfigurerForProject2['price_reason']}")
    private String priceReason;
	@Value("#{propertyConfigurerForProject2['price_start_time']}")
    private String priceStartTime;
	
	@Override
	public void addProductPriceList(List<List<String>> list,ProductPriceListModel productPriceListModel) throws Exception {
        List<String> cellList = null;
        
        boolean flag = true;
        if (list != null) {
            cellList = list.get(0);
            
            for(int x=0;x<cellList.size();x++){
                if(x==0){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(priceSkuCode))){
                        flag = false;
                    }
                }
                if(x==1){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(priceTshPrice))){
                        flag = false;
                    }
                }
                if(x==2){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(priceMarketPrice))){
                        flag = false;
                    }
                }
                if(x==3){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(priceReason))){
                        flag = false;
                    }
                }
                if(x==4){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(priceStartTime))){
                        flag = false;
                    }
                }
            }
            if(flag){
                try {
                    productPriceListService.addProductPriceList(list,productPriceListModel);
                } catch (Exception e) {
                    logger.error("",e);
                    throw new ZhaidouRuntimeException(e.getMessage());
                }
            }else{
                logger.error("价格上传,文件不符合规范！");
                throw new ZhaidouRuntimeException("上传文件不符合规范！");
            }
        }
		
	}

	@Override
	public void updateProductPriceList(Long[] ids,ProductPriceListModel productPriceListModel) throws Exception {
		productPriceListService.updateProductPriceList(ids,productPriceListModel);
	}

	@Override
	public ProductPriceListModel getProductPriceListById(
			ProductPriceListModel productPriceListModel) throws Exception {
		return productPriceListService.getProductPriceListById(productPriceListModel);
	}


	@Override
	public void deleteById(ProductPriceListModel productPriceListModel)
			throws Exception {

	}

	@Override
	public Map<String, Object> getProductPriceList( ProductPriceListModel productPriceListModel, Page page) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
        List<ProductPriceListModel> productPriceList = null;
        try {
        	productPriceList = productPriceListService.getProductPriceList(productPriceListModel, page);
        	 if(productPriceList!=null && productPriceList.size()>0){
                 for(ProductPriceListModel productPrice:productPriceList){
                     if(productPrice.getCreateTime()!=null){
                    	 productPrice.setCreateTimes(InfoUtil.dateLongToString(productPrice.getCreateTime(), InfoUtil.dateString));
                     }
                     if(productPrice.getUpdateTime()!=null){
                    	 productPrice.setUpdateTimes(InfoUtil.dateLongToString(productPrice.getUpdateTime(), InfoUtil.dateString));
                     }
                 }
             }
        } catch (Exception e) {
        	logger.error("",e);
        }
        map.put("productPriceList", productPriceList);
        map.put("page", page);
      return map;
	}

	@Override
	public Map<String, Object> getProductPriceLogList( ProductPriceLogModel productPriceLogModel, Page page)
			throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
        List<ProductPriceLogModel> productPriceLogList = null;
        try {
        	productPriceLogList = productPriceLogService.getProductPriceLog(productPriceLogModel, page);
        	if(productPriceLogList!=null && productPriceLogList.size()>0){
                for(ProductPriceLogModel productPriceLog:productPriceLogList){
                    if(productPriceLog.getCreateTime()!=null){
                    	productPriceLog.setCreateTimes(InfoUtil.dateLongToString(productPriceLog.getCreateTime(), InfoUtil.dateString));
                    }
                    if(productPriceLog.getUpdateTime()!=null){
                    	productPriceLog.setUpdateTimes(InfoUtil.dateLongToString(productPriceLog.getUpdateTime(), InfoUtil.dateString));
                    }
                }
            }
        	
        } catch (Exception e) {
        	logger.error("",e);
        }
        map.put("productPriceLogList", productPriceLogList);
        map.put("page", page);
		return map;
	}

    @Override
    public void updateProductPriceList(ProductPriceListModel productPriceListModel, ProductPriceListModel oldPriceModel)
            throws Exception {
        productPriceListService.updateProductPriceList(productPriceListModel, oldPriceModel);
    }

}
