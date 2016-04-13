package com.zhaidou.product.category.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.SaleCategoryDao;
import com.zhaidou.product.category.model.CategoryPO;

@Repository("saleCategoryDao")
public class SaleCategoryDaoImpl extends BaseDao implements SaleCategoryDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<CategoryPO> queryAll() throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryAll");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Integer showByParenCode(CategoryPO po) throws DaoException{
		try {
			return getSqlSession().update(getNameSpace() + ".showByParentCode", po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public Integer hiddenByParenCode(CategoryPO po) throws DaoException{
		try {
			return getSqlSession().update(getNameSpace() + ".hiddenByParentCode", po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	
	
	@Override
	public Integer deleteByParenCode(CategoryPO po) throws DaoException {
		try {
			return getSqlSession().update(getNameSpace() + ".deleteByParentCode", po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> selectByParentCode(CategoryPO po) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectByParentCode", po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> selectSonsByParentCode(CategoryPO po) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectSonsByParentCode", po);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> getListByCategoryCodes(List<String> categoryCodeList) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryListByCategoryCode", categoryCodeList);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<CategoryPO> getCategoryListByCategoryCodes(List<String> categoryCodeList) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryCategoryListByCategoryCodes", categoryCodeList);
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
	public Long countCategoryAndSubList(Long categoryId) throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".countCategoryAndSubList", categoryId);
		} catch (Exception e) {
			logger.error("查询 category_id 或 parent_id 字段为 categoryId 的总条数失败", e);
			throw new DaoException("查询 category_id 或 parent_id 字段为 categoryId 的总条数失败",e);
		}
	}

	@Override
	public Long countCategoryAndSubList(String categoryCode) throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".countCategoryAndSubListByCode", categoryCode);
		} catch (Exception e) {
			logger.error("查询 categoryCode 或 categoryCode 字段为 "+categoryCode+" 的总条数失败", e);
			throw new DaoException("查询 categoryCode 或 categoryCode 字段为 "+categoryCode+" 的总条数失败",e);
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
