package com.zhaidou.product.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.ProductAttrGroupManager;
import com.zhaidou.product.model.base.ProductCateAttrGroupModel;
import com.zhaidou.product.po.base.ProductCateAttrGroupPO;
import com.zhaidou.product.po.base.request.RequestAttrGroupValuePO;
import com.zhaidou.product.service.ProductAttrGroupService;

@Service("productAttrGroupManager")
public class ProductAttrGroupManagerImpl implements ProductAttrGroupManager{
	private Logger logger = Logger.getLogger(ProductBrandManagerImpl.class);
	
	@Resource(name = "productAttrGroupServiceMap")
	private Map<String, ProductAttrGroupService> productAttrGroupServiceMap;	
	
	@Override
	public ResponseObject getProductAttrValByCateCode(RequestObject requestObj)
	{
		
		ResponseObject response = null;  
	    try { 
		   
		    RequestAttrGroupValuePO reqBrandPO = (RequestAttrGroupValuePO)requestObj.getRequestParams();
		    if(reqBrandPO==null || StringUtils.isEmpty(reqBrandPO.getCateCode())){
				response = new ResponseObject(-1,"1001001","请求的分类编号为空！",null);
				logger.error("请求的分类编号为空！");
				return response;
		    }
		   
		    ProductAttrGroupService productAttrGroupService = productAttrGroupServiceMap.get("1.0.0");
		       
			List<ProductCateAttrGroupModel> modelList = productAttrGroupService.getProductAttrValByCateCode(reqBrandPO.getCateCode());
			List<ProductCateAttrGroupPO > POList = cateAttrGroupValueList2POList(modelList); 
		    response =  new ResponseObject(0,null,null,POList);
	
		} catch (Exception e) {
			response = new ResponseObject(-1,"1001000","服务器异常",null);
			logger.error("异常信息:" + e.getMessage(), e);
		}
		
		return response;
		
	}
	
	
	/**
	 * 将(分类属性组属性项属性值)模型对象集合 转成转成PO集合
	 * */
    public List<ProductCateAttrGroupPO> cateAttrGroupValueList2POList(List<ProductCateAttrGroupModel> cateAttrList){
    	
    	List<ProductCateAttrGroupPO> POList = null;
    	if(cateAttrList != null ){
    		POList = new ArrayList<ProductCateAttrGroupPO>();
    		ProductCateAttrGroupPO PO = null;
    		for (ProductCateAttrGroupModel model : cateAttrList) {
    			PO = cateAttrGroupModel2PO(model);
    			if(PO !=null ){
    				POList.add(PO);
    			}
			}
    		
    	}
    	return POList;
    }
	
    
	/**
	 * 将 (分类属性组属性项属性值)模型对象 转成PO对象
	 * */
    public ProductCateAttrGroupPO cateAttrGroupModel2PO(ProductCateAttrGroupModel model){
    	ProductCateAttrGroupPO PO = null;
    	if(model != null){
    		PO = new ProductCateAttrGroupPO();
    		PO.setCategoryId(model.getCategoryId());
    		PO.setCategoryCode(model.getCategoryCode());
    		PO.setGroupId(model.getCategoryId());
    		PO.setGroupCode(model.getGroupCode());
    		PO.setGroupName(model.getGroupName());
    		PO.setAttrId(model.getAttrId());
    		PO.setAttrCode(model.getAttrCode());
    		PO.setAttrName(model.getAttrName());
    		PO.setAttrEname(model.getAttrEname());
    		PO.setAttrValueId(model.getAttrValueId());
    		PO.setAttrValueCode(model.getAttrValueCode());
    		PO.setAttrValue(model.getAttrValue());
    		PO.setAttrValueNum(model.getAttrValueNum());
    		PO.setAttrDesign(model.getAttrDesign());
    		PO.setRequired(model.getRequired());
    	}
    	return PO;
    }

}
