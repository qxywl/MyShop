package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.CategoryModel;

/**
 * @Title: BaseCategoryDao.java 
 *
 * @Package com.teshehui.product.category.dao 
 *
 * @Description:   基础分类Dao接口
 *
 * @author lvshuding 
 *
 * @date 2015年3月25日 上午10:28:17 
 *
 * @version V1.0
 */
public interface BaseCategoryDao extends IDao {
	
	/**
	 * @Description:  父分类编号，查询其下和直接子分类
	 * @param po
	 * @throws Exception
	 * return List<CategoryPO>
	 */
	public List<CategoryModel> selectSonsByParentCode(CategoryModel po) throws Exception;
	
	/**
	 * @Description: 根据名称查询分类 
	 * @param model
	 * @throws Exception
	 * return List<CategoryModel>
	 */
	public List<CategoryModel> selectByName(CategoryModel model) throws Exception;
	
	/**
     * @Description: 根据编号查询分类 
     * @param model
     * @throws Exception
     * return List<CategoryModel>
     */
	public CategoryModel selectByCode(String categoryCode) throws Exception;
	
	
	/**
     * @Description: 查询等级分类下的所有商品,如果等级分类为空，则返回所有
     * @param level 
     * @throws Exception
     * return List<CategoryModel>
     */
	public List<CategoryModel> selectAllByLevel(Integer level) throws Exception;
}
