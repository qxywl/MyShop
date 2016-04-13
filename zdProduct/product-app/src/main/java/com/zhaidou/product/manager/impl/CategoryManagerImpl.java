package com.zhaidou.product.manager.impl;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.manager.CategoryManager;
import com.zhaidou.product.model.base.CategoryModel;
import com.zhaidou.product.po.base.CategoryPO;
import com.zhaidou.product.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("categoryManager")
public class CategoryManagerImpl implements CategoryManager {

	@Resource(name = "categoryServiceMap")
    private Map<String, CategoryService> categoryServiceMap;
	
	private Logger logger = Logger.getLogger(CategoryManagerImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseObject queryDirectSonListByParentCode(RequestObject requestObj) {
		
		ResponseObject response = null;  
		CategoryPO po = (CategoryPO)requestObj.getRequestParams();
		
		if(po==null){
			response = new ResponseObject(-1,"404","参数对象中的requestParams为NULL！",null);
			logger.info("参数对象中的requestParams为NULL！");
			return response;
		}
		
		CategoryService categoryService = categoryServiceMap.get("1.0.0");
		try {
			CategoryModel parent = new CategoryModel();
			parent.setCategoryCode(po.getCategoryCode());
			List<CategoryModel> list = categoryService.queryDirectSonListByParentCode(parent);
			response =  new ResponseObject(0,null,null,this.buildModelList2POList(list));
		} catch (Exception e) {
			logger.error("queryDirectSonListByParentCode 异常信息:" + e.getMessage(), e);
			response = new ResponseObject(-1,"404","服务器异常",null);
		}
		return response;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseObject queryCategoryByName(RequestObject requestObj) {
		ResponseObject response = null;  
		
		if(requestObj.getRequestParams() ==null){
			response = new ResponseObject(-1,"404","参数对象中的requestParams为NULL！",null);
			logger.info("参数对象中的requestParams为NULL！");
			return response;
		}
		
		CategoryService categoryService = categoryServiceMap.get("1.0.0");
		try {
			CategoryModel model = new CategoryModel();
			model.setCategoryName((String)requestObj.getRequestParams());
			List<CategoryModel> list = categoryService.queryByName(model);
			response =  new ResponseObject(0,null,null,this.buildModelList2POList(list));
		} catch (Exception e) {
			logger.error("queryCategoryByName 异常信息:" + e.getMessage(), e);
			response = new ResponseObject(-1,"404","服务器异常",null);
		}
		return response;
	}



	private List<CategoryPO> buildModelList2POList(List<CategoryModel> mList){
		if(mList==null || mList.size()==0){
			return null;
		}
		CategoryPO tmpPo;
		List<CategoryPO> resultList = new ArrayList<CategoryPO>();
		for(CategoryModel m:mList){
			tmpPo = new CategoryPO();
			tmpPo.setId(m.getId());
			tmpPo.setCategoryCode(m.getCategoryCode());
			tmpPo.setCategoryName(m.getCategoryName());
			tmpPo.setParentId(m.getParentId());
			tmpPo.setParentCode(m.getParentCode());
			tmpPo.setImageUrl(m.getImageUrl());
			tmpPo.setColorName(m.getColorName());
			tmpPo.setSizeName(m.getSizeName());
			resultList.add(tmpPo);
		}
		return resultList;
	}


    @Override
    public ResponseObject queryCategoryByCode(RequestObject requestObj) {
        ResponseObject response = null;  
        
        if(requestObj.getRequestParams() ==null){
            response = new ResponseObject(-1,"404","参数对象中的requestParams为NULL！",null);
            logger.info("参数对象中的requestParams为NULL！");
            return response;
        }
        
        CategoryService categoryService = categoryServiceMap.get("1.0.0");
        try {
            CategoryModel model = null;
            model = categoryService.queryByCode((String)requestObj.getRequestParams());
            
            CategoryPO categoryPo = new CategoryPO();
            categoryPo.setId(model.getId());
            categoryPo.setCategoryCode(model.getCategoryCode());
            categoryPo.setCategoryName(model.getCategoryName());
            categoryPo.setParentId(model.getParentId());
            categoryPo.setParentCode(model.getParentCode());
            categoryPo.setImageUrl(model.getImageUrl());
            categoryPo.setColorName(model.getColorName());
            categoryPo.setSizeName(model.getSizeName());
            response =  new ResponseObject(0,null,null,categoryPo);
        } catch (Exception e) {
			logger.error("queryCategoryByCode 异常信息:" + e.getMessage(), e);
			response = new ResponseObject(-1,"404","服务器异常",null);
        }
        return response;
    }


	@Override
	public ResponseObject queryCategoryAllByLevel(RequestObject requestObj) {
		
		ResponseObject response = null;  
		CategoryService categoryService = null;
		Integer level = (Integer)requestObj.getRequestParams();
		logger.info("参数对象中的requestParams为"+level);
		try {
			categoryService = categoryServiceMap.get(requestObj.getVersion());
			List<CategoryModel> list = categoryService.selectAllByLevel(level);
			response =  new ResponseObject(this.buildModelList2POList(list));
		} catch (Exception e) {
			response = new ResponseObject(-1,"404","服务器异常",null);
			logger.error("请求参数version = "+requestObj.getVersion()+",异常信息:" + e.getMessage(), e);
		}
		return response;
	}

}
