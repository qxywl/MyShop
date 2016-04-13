package com.zhaidou.product.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.dao.MountRuleDao;
import com.zhaidou.product.category.model.MountRulePO;

@Repository("mountRuleDao")
public class MountRuleDaoImpl extends BaseDao implements MountRuleDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}
	
	

	@Override
	public Long totCountByEffective() throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".totCountByEffective");
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}



	@Override
	public List<MountRulePO> queryPage(Page page) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryPage",page);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<MountRulePO> queryDeleteRuleByDelDate(String dateStr)
			throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryDeleteRuleByDelDate",dateStr);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
