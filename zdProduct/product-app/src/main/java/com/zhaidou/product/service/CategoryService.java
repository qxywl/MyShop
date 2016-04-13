package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.base.CategoryModel;

/**
 * 
 * @Title: CategoryService.java 
 *
 * @Package com.teshehui.product.service 
 *
 * @Description:   基础分类分类Service
 *
 * @author lvshuding 
 *
 * @date 2015年5月6日 上午10:55:37 
 *
 * @version V1.0
 */
public interface CategoryService {

	
	/**
	 * @Description: 根据父分类编号查询直接子分类列表[对外接口]
	 * 
	 * @param parent   父分类对象【如果父分类对象中categoryCode==null，返回所有一级分类】
	 * 
	 * return List<CategoryPO>
	 */
	public List<CategoryModel> queryDirectSonListByParentCode(CategoryModel parent);
	
	/**
	 * @Description: 根据分类名称查询分类对象
	 * @param model
	 * @return List<CategoryModel>
	 */
	public List<CategoryModel> queryByName(CategoryModel model);
	
	/**
     * @Description: 根据分类编号查询分类对象
     * @param model
     * @return List<CategoryModel>
     */
    public CategoryModel queryByCode(String categoryCode);
    
    
    /**
     * @Description: 查询等级分类下的所有分类,如果等级分类为空，则返回所有
     * @param null
     * @return List<CategoryModel>
     * */
    public List<CategoryModel> selectAllByLevel(Integer level) throws Exception;
}
