package com.zhaidou.product.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.product.dao.MountRuleDao;
import com.zhaidou.product.model.BalanceInfoModel;
import com.zhaidou.product.model.MountRuleModel;
import com.zhaidou.product.service.MountRuleService;

@Service("mountRuleService")
public class MountRuleServiceImpl implements MountRuleService {

	private static Logger LOG = Logger.getLogger(MountRuleServiceImpl.class);
	
	@Autowired
	private MountRuleDao mountRuleDao;
	
	@Override
	public Long totCountByEffective(BalanceInfoModel balance) throws Exception{
		
		try{
			return mountRuleDao.totCountByEffective(balance);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("查询有效挂载规则总量操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("查询有效挂载规则总量操作失败：",e);
			throw e;
		}
	}

	@Override
	public List<MountRuleModel> queryPage(BalanceInfoModel balance) throws Exception{
		try{
			return mountRuleDao.queryPage(balance);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("分页查询挂载规则操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("分页查询挂载规则操作失败：",e);
			throw e;
		}
	}

	@Override
	public List<MountRuleModel> queryDeleteRuleByDelDate(BalanceInfoModel balance) throws Exception {
		try{
			return mountRuleDao.queryDeleteRuleByDelDate(balance);
		}catch(DaoException e){
			e.printStackTrace();
			LOG.error("根据日期分页查询已删除挂载规则操作失败：",e);
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("根据日期分页查询已删除挂载规则操作失败：",e);
			throw e;
		}
	}

	public void setMountRuleDao(MountRuleDao mountRuleDao) {
		this.mountRuleDao = mountRuleDao;
	}

}

