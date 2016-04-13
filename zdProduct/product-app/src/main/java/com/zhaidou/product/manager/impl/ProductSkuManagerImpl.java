package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.ProductSkuManager;
import com.zhaidou.product.model.ProductPriceLadderModel;
import com.zhaidou.product.model.ProductSkuViewModel;
import com.zhaidou.product.po.ProductMallSkuPO;
import com.zhaidou.product.po.ProductPriceLadderPO;
import com.zhaidou.product.po.request.RequestProductMallSkuPO;
import com.zhaidou.product.service.ProductSkuViewService;
import com.zhaidou.product.service.ProductStockService;
import com.zhaidou.product.util.ProductConstantUtil;

@Service("productSkuManager")
public class ProductSkuManagerImpl implements ProductSkuManager{
	
    private Logger logger = Logger.getLogger(ProductSkuManagerImpl.class);

   
    @Resource(name = "productSkuViewMap")
    private Map<String, ProductSkuViewService> productSkuViewMap;
	
    
    @Override
	public ResponseObject getProductSkuViewByProdId(RequestObject requestObj) {
		ResponseObject response = null;
		try {
			RequestProductMallSkuPO request = (RequestProductMallSkuPO)requestObj.getRequestParams();
			Long productId = request.getProductId();
			if(StringUtils.isEmpty(productId)){
				response = new ResponseObject("10104001","productId 不能为空");
				logger.error("productId 参数不能为空");
				return response;
			}
			ProductSkuViewService productSkuViewService = productSkuViewMap.get(requestObj.getVersion());
			List<ProductSkuViewModel> list = productSkuViewService.getProductSkuViewByProdId(productId);
			List<ProductMallSkuPO> poList = modelList2PO(list);
			response = new ResponseObject(poList);
		} catch (Exception e){
			logger.error("系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage(), e);
			response = new ResponseObject("10104001","系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage());
		}
		return response;
	}

	
	@Override
	public ResponseObject getProductSkuViewBySkuList(RequestObject requestObj) {
		ResponseObject response = null;
		try {
			RequestProductMallSkuPO request = (RequestProductMallSkuPO)requestObj.getRequestParams();
			List<String> skuList  = request.getSkuList();
			if(StringUtils.isEmpty(skuList) || skuList.size()<1){
				response = new ResponseObject("10104001","skuList 参数不能为空");
				logger.error("skuList 参数不能为空");
				return response;
			}
			ProductSkuViewService productSkuViewService = productSkuViewMap.get(requestObj.getVersion());
			List<ProductSkuViewModel> list = productSkuViewService.getProductSkuViewBySkuList(skuList);
			List<ProductMallSkuPO> poList = modelList2PO(list);
			response = new ResponseObject(poList);
		} catch (Exception e) {
			logger.error("系统错误！请求参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+ ";" + e.getMessage(), e);
			response = new ResponseObject("10104001","系统错误！请求参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+ ";" + e.getMessage());
		}
		return response;
	}
	
	  
    /**
     * 将sku模型List转换PO
     * */
    public List<ProductMallSkuPO> modelList2PO(List<ProductSkuViewModel> modelList){
    	List<ProductMallSkuPO> poList = null;
    	if(modelList != null){
    		poList = new ArrayList<ProductMallSkuPO>();
    		ProductMallSkuPO po = null;
    		for (ProductSkuViewModel model : modelList) {
    			po = model2PO(model);
    			if(po != null){
    				poList.add(po);
    			}
			}
    	}
    	return poList;
    }
    
    
	/**
	 * 将sku模型转换PO
	 * */
    public ProductMallSkuPO model2PO(ProductSkuViewModel model){
    	ProductMallSkuPO PO = null;
    	if(model != null){
    		PO = new ProductMallSkuPO();
    		PO.setProductSkuId(model.getProductSkuId());
    		PO.setProductId(model.getProductId());
    		PO.setProductSkuCode(model.getProductSkuCode());
    		
    		PO.setAttrColorName(model.getAttrColorName());
    		PO.setAttrColorValue(model.getAttrColorValue());
    		
    		PO.setAttrSpecName(model.getAttrSpecName());
    		PO.setAttrSpecValue(model.getAttrSpecValue());
    		
    		PO.setMainSku(model.getMainSku());
    		PO.setIfShow(model.getIfShow());
    		
    		PO.setMarketPrice(model.getMarketPrice());
    		PO.setCostPrice(model.getCostPrice());
    		PO.setTshPrice(model.getTshPrice());
    		PO.setTb(model.getTb());
    	}
    	return PO;
    }
}
