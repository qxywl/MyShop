package com.zhaidou.product.category.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.category.model.CategoryPO;

/**
 * @Title: SaleCategoryDao.java
 *
 * @Package com.teshehui.product.category.dao
 *
 * @Description: 运营分类Dao接口
 *
 * @author lvshuding
 *
 * @date 2015年3月25日 上午10:27:55
 *
 * @version V1.0
 */
public interface SaleCategoryDao extends IDao {

	/**
	 * @Description: 查询所有分类数据
	 * @throws DaoException
	 *             return List<CategoryPO>
	 */
	public List<CategoryPO> queryAll() throws DaoException;

	
	/**
	 * @Description: 根据父分类编号，显示其下所有的子分类
	 * @param po
	 *            父分类
	 * @throws DaoException
	 *             return Integer
	 */
	public Integer showByParenCode(CategoryPO po)throws DaoException;
	
	
	/**
	 * @Description: 根据父分类编号，隐藏其下所有的子分类
	 * @param po
	 *            父分类
	 * @throws DaoException
	 *             return Integer
	 */
	public Integer hiddenByParenCode(CategoryPO po)throws DaoException;
	
	
	
	/**
	 * @Description: 根据父分类编号，删除其下所有的子分类
	 * @param po
	 *            父分类
	 * @throws DaoException
	 *             return Integer
	 */
	public Integer deleteByParenCode(CategoryPO po) throws DaoException;

	/**
	 * @Description: 父分类编号，查询其下所有的子分类
	 * @param po
	 * @throws DaoException
	 *             return List<CategoryPO>
	 */
	public List<CategoryPO> selectByParentCode(CategoryPO po) throws DaoException;

	/**
	 * @Description: 父分类编号，查询其下和直接子分类
	 * @param po
	 * @throws DaoException
	 *             return List<CategoryPO>
	 */
	public List<CategoryPO> selectSonsByParentCode(CategoryPO po) throws DaoException;

	/**
	 * @Description:根据分类名称和当前分类的父分类ID查找对象
	 * @param po
	 * @return CategoryPO
	 * @throws DaoException
	 */
	public CategoryPO selectByParentIdAndCategoryName(CategoryPO po) throws DaoException;

	public List<CategoryPO> getListByCategoryCodes(List<String> categoryCodeList);

	public List<CategoryPO> getCategoryListByCategoryCodes(List<String> categoryCodeList) throws DaoException;

	public Long countCategoryAndSubList(Long categoryId) throws DaoException;
	
	/**
	 * 根据categoryCode 统计 当前运营分类和它的子分类数
	 * 返回 0 ，表示没有该分类
	 * 返回 1 ，表示没有子节点
	 * 返回 >1，表示有子节点
	 * */
	public Long countCategoryAndSubList(String categoryCode) throws DaoException;

	
	public List<String> queryCodeListByIds(List<Integer> idList) throws DaoException;
	
	/**
     * @Description: 查询等级分类下的所有商品,如果等级分类为空，则返回所有
     * @param level 
     * @throws Exception
     * return List<CategoryModel>
     */
	public List<CategoryPO> selectAllByLevel(Integer level) throws Exception;
	
	/**
	 * @Description:通过运营分类code，查找当前节点和节点下的所有子分类,如果不传categoryCode,则返回所有
	 * @param categoryCode 
	 * @throws Exception
	 * return List<CategoryModel>
	 * */
	public List<CategoryPO> selectParentAndSonByCode(String categoryCode)throws Exception;

}
