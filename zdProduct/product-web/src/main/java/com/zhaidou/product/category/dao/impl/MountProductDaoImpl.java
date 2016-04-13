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
package com.zhaidou.product.category.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.MountProductDao;
import com.zhaidou.product.category.model.MountProduct;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-09]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("mountProductDao")
public class MountProductDaoImpl extends BaseDao  implements MountProductDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

	@Override
	public MountProduct queryOneByProductAndCategoryCode(MountProduct model) throws DaoException{
		try{
			return this.getSqlSession().selectOne(getNameSpace() + ".queryOneByProductAndCategoryCode", model);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<MountProduct> queryProductsByCategoryId(MountProduct model, int pageNo, int pageSize) throws DaoException{
		try {
			List<MountProduct> list = null;
			if (pageSize != 2147483647)
				list = getSqlSession()
						.selectList(getNameSpace() + ".queryProductsByCategoryId", model, new RowBounds(pageNo,
								pageSize));
			else {
				list = getSqlSession().selectList(getNameSpace() + ".queryProductsByCategoryId", model);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
	@Override
	public List<MountProduct> queryExprotProdsByCategoryId(MountProduct model)
			throws DaoException {
		List<MountProduct> list = null; 
		list = getSqlSession().selectList(getNameSpace() + ".queryExprotProdsByCategoryId", model);
		return list;
	}
	
	
	@Override
	public long countProductsListPageByCategoryId(MountProduct relationModel) throws DaoException{
		try {
			Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".countProductsListPageByCategoryId", relationModel);
			return count;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean batchInsert(List<MountProduct> list) throws DaoException{
		Map<String,List<MountProduct>> map = new HashMap<String,List<MountProduct>>();
		map.put("list", list);
		try{
			getSqlSession().insert(getNameSpace() + ".batchInsert" , map);
		}catch(Exception e){
			throw new DaoException("产品挂载批量插入失败：[MountProduct]", e);
		}
		return true;
	}

	@Override
	public List<MountProduct> queryListByCategoryCode(MountProduct mountProduct) throws DaoException{
		try{
			return getSqlSession().selectList(getNameSpace() + ".queryListByCategoryCode", mountProduct);
		}catch(Exception e){
			throw new DaoException("根据categoryCode获取产品挂载关系列表失败", e);
		}
	}

	@Override
	public List<MountProduct> selectOpt1ByCategoryId(MountProduct model)
			throws DaoException {
		try{
			return getSqlSession().selectList(getNameSpace() + ".selectOpt1ByCategoryId", model);
		}catch(Exception e){
			throw new DaoException("根据运营分类ID加载其下所有opt_type=1的商品,失败", e);
		}
	}

	@Override
	public Long countProductsInSubCategory(List<String> categoryCodeList) throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".countProductsInSubCategory", categoryCodeList);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/**
	 * 根据运营分类ID查询挂载在其下的商品数， 其操作类型是更新（即未删除的有效商品）
	 */
	@Override
	public Long countProductsByCategoryId(Long categoryId) throws DaoException{
		try {
			return getSqlSession().selectOne(getNameSpace() + ".countProductsByCategoryId", categoryId);
		} catch (Exception e) {
			logger.error("根据categoryId查询挂载在其下商品失败", e);
			throw new DaoException(e);
		}
	}

	
	@Override
	public MountProduct getMpOrderValByRelId(Long relationId) {
		return  getSqlSession().selectOne(getNameSpace() + ".getMpOrderValByRelId", relationId);
	}

	
	@Override
	public int updateMpOrderValueByRelId(MountProduct mpModel) {
		return  getSqlSession().update(getNameSpace() + ".updateMpOrderValueByRelId", mpModel);
	}

	
	
}
