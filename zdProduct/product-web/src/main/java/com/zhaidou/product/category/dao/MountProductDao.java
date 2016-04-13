/*
 * 文 件 名:  SalecategoryProductRelationDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.category.model.MountProduct;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MountProductDao extends IDao {
	public MountProduct queryOneByProductAndCategoryCode(MountProduct model);

	public List<MountProduct> queryProductsByCategoryId(MountProduct model, int pageNo, int pageSize) throws DaoException;
	
	public List<MountProduct> queryExprotProdsByCategoryId(MountProduct model) throws DaoException;
	
	public long countProductsListPageByCategoryId(MountProduct relationModel) throws DaoException;

	public boolean batchInsert(List<MountProduct> list) throws DaoException;

	public List<MountProduct> queryListByCategoryCode(MountProduct mountProduct) throws DaoException;
	
	/**
	 * @Description:  根据运营分类ID加载其下所有opt_type=1的商品
	 * @param model
	 * @throws DaoException
	 * @return List<MountProduct>
	 */
	public List<MountProduct> selectOpt1ByCategoryId(MountProduct model) throws DaoException;

	/**
	 * 查询该子分类下挂载的商品数
	 * @param relationModel
	 * @return
	 * @throws DaoException
	 */
	public Long countProductsInSubCategory(List<String> categoryCode) throws DaoException;

	/**
	 * 根据运营分类ID查询挂载在其下的商品数， 其操作类型是更新（即未删除的有效商品）
	 * 
	 * @param categoryId 
	 * @return
	 * @throws DaoException
	 */
	public Long countProductsByCategoryId(Long categoryId) throws DaoException;

	
	/**
	 * 通过relationId 获取运营分类下商品的排序值
	 * @param relationId 运营分类 -- 商品关联 的 id
	 * @return MountProduct
	 * */
	public MountProduct getMpOrderValByRelId(Long relationId) throws DaoException;

	
	/**
	 * 通过relationId 更新运营分类下商品的排序值
	 * @param relationId 运营分类 -- 商品关联 的 id
	 * @return MountProduct
	 * */
	public int updateMpOrderValueByRelId(MountProduct mpModel);
}
