package com.zhaidou.product.service.impl;

import com.zhaidou.product.dao.BaseCategoryDao;
import com.zhaidou.product.model.base.CategoryModel;
import com.zhaidou.product.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	private static Logger LOG = Logger.getLogger(CategoryServiceImpl.class);

	@Autowired
	private BaseCategoryDao baseCategoryDao;// 基础分类

	@Override
	public List<CategoryModel> queryDirectSonListByParentCode(CategoryModel parent) {
		List<CategoryModel> modelList = null;
		try {
			modelList = baseCategoryDao.selectSonsByParentCode(parent);
		} catch (Exception e) {
			LOG.error("queryDirectSonListByParentCode 根据分根据父分类编号查询直接子分类列表[基础分类]操作失败：CategoryModel ="+parent, e);
		}
		return modelList;
	}
	
	@Override
	public List<CategoryModel> queryByName(CategoryModel model) {
		try {
			return baseCategoryDao.selectByName(model);
		} catch (Exception e) {
			LOG.error("queryByName 根据分分类名称["+model.getCategoryName()+"]查询列表[基础分类]操作失败：", e);
			return null;
		}
	}

	public void setBaseCategoryDao(BaseCategoryDao baseCategoryDao) {
		this.baseCategoryDao = baseCategoryDao;
	}

    @Override
    public CategoryModel queryByCode(String categoryCode) {
        try {
            return baseCategoryDao.selectByCode(categoryCode);
        } catch (Exception e) {
            LOG.error("queryByCode 根据分分类编号["+categoryCode+"]查询列表[基础分类]操作失败：", e);
            return null;
        }
    }
    
    
    @Override
    public List<CategoryModel> selectAllByLevel(Integer level) throws Exception {
    	if(level != null){
    		level = level * 2; 
    	}
    	return baseCategoryDao.selectAllByLevel(level);
    }
    

}
