/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.model.ResultSetModel;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.manager.ProductBrandManager;
import com.zhaidou.product.model.base.ProductBrandModel;
import com.zhaidou.product.po.base.ProductBrandPO;
import com.zhaidou.product.po.base.request.RequestProductBrandPO;
import com.zhaidou.product.service.ProductBrandService;

/**品牌管理实现
 *@author wwei
 *@date 2015-05-13
 **/
@Service("productBrandManager")
public class ProductBrandManagerImpl implements ProductBrandManager {
    private Logger logger = Logger.getLogger(ProductBrandManagerImpl.class);

    @Resource(name = "productBrandServiceMap")
    private Map<String, ProductBrandService> productBrandServiceMap;

	@Override
	public ResponseObject getEnableBrandByCode(RequestObject requestObj) {
		
		ResponseObject response = null;  
		RequestProductBrandPO reqBrandPO = (RequestProductBrandPO)requestObj.getRequestParams();
		try {
			if(reqBrandPO==null || StringUtils.isEmpty(reqBrandPO.getBrandCode())){
				response = new ResponseObject("1001001","请求的品牌编号为空！");
				logger.error("请求的品牌编号为空");
				return response;
			}
			ProductBrandService brandService = productBrandServiceMap.get(requestObj.getVersion());
			ProductBrandModel model = brandService.getMallEnableBrandByCode(reqBrandPO.getBrandCode());
			ProductBrandPO productBrandPO = brandModel2PO(model);
			response =  new ResponseObject(productBrandPO);
	
		} catch (Exception e) {
			response = new ResponseObject("1001000","系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage());
			logger.error("系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage(), e);
		}
		return response;
	}

	
	@Override
	public ResponseObject getEnableBrandByName(RequestObject requestObj) {
		ResponseObject response = null;  
		try {
			
			 RequestProductBrandPO reqBrandPO = (RequestProductBrandPO)requestObj.getRequestParams();
		     if(reqBrandPO==null || StringUtils.isEmpty(reqBrandPO.getBrandName())){
				response = new ResponseObject("1001001","请求的品牌名称为空！");
				logger.error("请求的品牌名称为空！");
				return response;
			 }
			
			ProductBrandService brandService = productBrandServiceMap.get(requestObj.getVersion());
			ProductBrandModel model = brandService.getMallEnableBrandByName(reqBrandPO.getBrandName());
			ProductBrandPO productBrandPO = brandModel2PO(model);
			response =  new ResponseObject(productBrandPO);
	
		} catch (Exception e) {
			response = new ResponseObject("1001000","系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage());
			logger.error("系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage(), e);
		}
		return response;
	}

	
	@Override
	public ResponseObject getEnableProductBrandListPage(
			RequestObject requestObj) {
		ResponseObject response = null;  
		
		try {
			
			RequestProductBrandPO reqBrandPO = (RequestProductBrandPO)requestObj.getRequestParams();
			
			if(reqBrandPO == null ){
				response = new ResponseObject("1001001","请求参数为空！",null);
				logger.error("请求的品牌名称为空！");
				return response;
			}
			
			ProductBrandService brandService = productBrandServiceMap.get(requestObj.getVersion());
			ProductBrandModel brandQuery = new ProductBrandModel();
			brandQuery.setBrandCode(reqBrandPO.getBrandCode());
			brandQuery.setBrandName(reqBrandPO.getBrandName());
			Page page = new Page();
			page.setPageNum(reqBrandPO.getPageNum());
			page.setNumPerPage(reqBrandPO.getNumPerPage());
			
			List<ProductBrandModel> modelList = brandService.getMallEnableProductBrandListPage(brandQuery, page);
			List<ProductBrandPO> OPList = brandList2POList(modelList);
			
			//封装返回数据
			ResultSetModel<ProductBrandPO> resultSet = new ResultSetModel<ProductBrandPO>();
			resultSet.setBeginRow(page.getNumPerPage()*(page.getPageNum()-1)+1);
			resultSet.setItems(OPList);
			resultSet.setPage(reqBrandPO.getPageNum());
			resultSet.setPageSize(reqBrandPO.getNumPerPage());
			resultSet.setTotalCount((int)page.getTotalCount());
			
			response =  new ResponseObject(resultSet);
	
		} catch (Exception e) {
			response = new ResponseObject("1001000","系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage());
			logger.error("系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage(), e);
		}
		return response;
	}
  
	
	@Override
	public ResponseObject getAllEnableProductBrandList(RequestObject requestObj) {
		
		ResponseObject response = null;  
		try {
			ProductBrandService brandService = productBrandServiceMap.get(requestObj.getVersion());
			List<ProductBrandModel> modelList = brandService.getAllMallEnableProductBrandList();
			List<ProductBrandPO> OPList = brandList2POList(modelList);
			response =  new ResponseObject(OPList);
	
		} catch (Exception e) {
			response = new ResponseObject("1001000","系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage());
			logger.error("系统错误！参数版本号或业务线错误:v="+requestObj.getVersion()+" b="+requestObj.getBusinessType()+";错误信息:" + e.getMessage(), e);
		}
		return response;
	}
	
	
	/**
	 * 将品牌集合模型转换品牌PO集合
	 * */
    public List<ProductBrandPO> brandList2POList(List<ProductBrandModel> brandList){
    	
    	List<ProductBrandPO> POList = null;
    	if(brandList != null ){
    		POList = new ArrayList<ProductBrandPO>();
    		ProductBrandPO PO = null;
    		for (ProductBrandModel productBrandModel : brandList) {
    			PO = brandModel2PO(productBrandModel);
    			if(PO !=null ){
    				POList.add(PO);
    			}
			}
    		
    	}
    	return POList;
    }
	
    
	/**
	 * 将品牌模型转换品牌PO
	 * */
    public ProductBrandPO brandModel2PO(ProductBrandModel brandModel){
    	ProductBrandPO PO = null;
    	if(brandModel != null){
    		PO = new ProductBrandPO();
    		PO.setBrandId(brandModel.getBrandId());
    		PO.setBrandCode(brandModel.getBrandCode());
    		PO.setBrandName(brandModel.getBrandName());
    		PO.setBrandInfo(brandModel.getBrandInfo());
    		PO.setBrandCountry(brandModel.getBrandCountry());
    		PO.setBrandLogo(brandModel.getBrandLogo());
    		PO.setBrandStatus(brandModel.getBrandStatus());
    		PO.setFlagshipStoreCode(brandModel.getFlagshipStoreCode());
    		PO.setBrandStory(brandModel.getBrandStory());
    		PO.setUpdateUser(brandModel.getUpdateUser());
    		PO.setCreator(brandModel.getCreator());
    	}
    	return PO;
    }


	
 
    
    

}
