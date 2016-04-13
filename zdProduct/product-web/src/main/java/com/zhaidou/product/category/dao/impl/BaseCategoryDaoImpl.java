package com.zhaidou.product.category.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.BaseCategoryDao;
import com.zhaidou.product.category.model.CategoryPO;

@Repository("baseCategoryDao")
public class BaseCategoryDaoImpl extends BaseDao implements BaseCategoryDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<CategoryPO> queryAll() throws DaoException{
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryAll");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Integer deleteByParenCode(CategoryPO po) throws DaoException {
		try {
			return getSqlSession().update(getNameSpace() + ".deleteByParentCode",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> selectByParentCode(CategoryPO po)
			throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectByParentCode",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> selectSonsByParentCode(CategoryPO po)
			throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectSonsByParentCode",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> selectByIds(List<Long> list) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectByIds",list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public CategoryPO selectByParentIdAndCategoryName(CategoryPO po)
			throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".selectByParentIdAndCategoryName",po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<String> queryCodeListByIds(List<Integer> idList) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryCodeListByIds", idList);
		} catch (Exception e) {
			logger.error("根据分类categoryId列表返回categoryCode列表失败",e);
			throw new DaoException("根据分类categoryId列表返回categoryCode列表失败",e);
		}
	}

	@Override
	public List<CategoryPO> selectAllByLevel(Integer level) throws Exception {
		List<CategoryPO> allList = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
    	map.put("level", level);
		allList = getSqlSession().selectList(getNameSpace() + ".selectAllByLevel",map);
		return allList;
	}
	
	
	@Override
	public List<CategoryPO> selectParentAndSonByCode(String categoryCode)
			throws Exception {
		List<CategoryPO> allList = null;
		Map<String,String> map = new HashMap<String,String>();
    	map.put("categoryCode", categoryCode);
		allList = getSqlSession().selectList(getNameSpace() + ".selectParentAndSonByCode",map);
		return allList;
	}
}
