package com.zhaidou.product.category.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.category.model.FilterMountPO;

/**
 * 
 * @Title: FilterMountDao.java 
 *
 * @Package com.teshehui.product.category.dao 
 *
 * @Description:   筛选项挂载DAO
 *
 * @author lvshuding 
 *
 * @date 2015年4月14日 下午3:52:27 
 *
 * @version V1.0
 */
public interface FilterMountDao extends IDao {

	/**
	 * @Description:  根据运营分类ID删除筛选项列表
	 * @param po
	 * @throws DaoException
	 * return Integer
	 */
	public Integer deleteByCategoryId(FilterMountPO po) throws DaoException;
	
	/**
	 * @Description: 根据运营分类Code+属性项Code删除筛选项
	 * @param po
	 * @throws DaoException
	 * return Integer
	 */
	public Integer deleteByCateCodeAndAttrCode(FilterMountPO po) throws DaoException;
	
	/**
	 * @Description:  根据运营分类Code+属性项Code修改排序值
	 * @param po
	 * @throws DaoException
	 * return Integer
	 */
	public Integer updateOrderByCateCodeAndAttrCode(FilterMountPO po) throws DaoException;
	
	/**
	 * @Description:  根据运营分类加载
	 * @param po
	 * @throws DaoException
	 * return List<FilterMountPO>
	 */
	public List<FilterMountPO> queryListByCategoryId(FilterMountPO po) throws DaoException;
}
