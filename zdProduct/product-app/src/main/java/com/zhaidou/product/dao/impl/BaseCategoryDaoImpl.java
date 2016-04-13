package com.zhaidou.product.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.BaseCategoryDao;
import com.zhaidou.product.model.base.CategoryModel;

@Repository("baseCategoryDao")
public class BaseCategoryDaoImpl extends BaseDao implements BaseCategoryDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public List<CategoryModel> selectSonsByParentCode(CategoryModel po)
			throws Exception {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectSonsByParentCode",po);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<CategoryModel> selectByName(CategoryModel model) throws Exception {
		try {
			return getSqlSession().selectList(getNameSpace() + ".selectOneByName",model);
		} catch (Exception e) {
			throw e;
		}
	}

    @Override
    public CategoryModel selectByCode(String categoryCode) throws Exception {
        try {
            return getSqlSession().selectOne(getNameSpace() + ".selectOneByCode",categoryCode);
        } catch (Exception e) {
            throw e;
        }
    }

    
	@Override
	public List<CategoryModel> selectAllByLevel(Integer level) throws Exception {
		
		List<CategoryModel> allList = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
    	map.put("level", level);
		allList = getSqlSession().selectList(getNameSpace() + ".selectAllByLevel",map);
		return allList;
	}
	
}
