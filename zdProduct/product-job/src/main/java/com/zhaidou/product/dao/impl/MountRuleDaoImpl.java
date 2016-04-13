package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.dataSource.DatabaseContextHolder;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.MountRuleDao;
import com.zhaidou.product.model.BalanceInfoModel;
import com.zhaidou.product.model.MountRuleModel;

@Repository("mountRuleDao")
public class MountRuleDaoImpl extends BaseDao implements MountRuleDao {

	@Override
	public String getNameSpace() {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_ONE);
		return this.getClass().getSimpleName();
	}

	@Override
	public Long totCountByEffective(BalanceInfoModel balance) throws DaoException {
		try {
			return getSqlSession().selectOne(getNameSpace() + ".totCountByEffective",balance);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}



	@Override
	public List<MountRuleModel> queryPage(BalanceInfoModel balance) throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryPage",balance);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<MountRuleModel> queryDeleteRuleByDelDate(BalanceInfoModel balance)
			throws DaoException {
		try {
			return getSqlSession().selectList(getNameSpace() + ".queryDeleteRuleByDelDate",balance);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
}
