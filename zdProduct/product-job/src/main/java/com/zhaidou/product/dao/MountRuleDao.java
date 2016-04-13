package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.BalanceInfoModel;
import com.zhaidou.product.model.MountRuleModel;


/**
 * 
 * @Title: MountRuleDao.java 
 *
 * @Package com.teshehui.product.dao 
 *
 * @Description:    自动挂载规则Dao
 *
 * @author lvshuding 
 *
 * @date 2015年6月1日 下午3:37:27 
 *
 * @version V1.0
 */
public interface MountRuleDao extends IDao {

	/**
	 * @Description:查询deleteFlag=0的记录总条数
	 * @param balance
	 * @throws DaoException
	 * return Long
	 */
	public Long totCountByEffective(BalanceInfoModel balance) throws DaoException;
	
	/**
	 * @Description: 分页查询有效的规则数据
	 * @param balance
	 * return List<MountRuleModel>
	 */
	public List<MountRuleModel> queryPage(BalanceInfoModel balance) throws DaoException;
	
	/**
	 * @Description:  根据删除日期加载所有已删除的规则数据
	 * @param balance   date格式 ：yyyy-MM-dd
	 * return List<MountRuleModel>
	 */
	public List<MountRuleModel> queryDeleteRuleByDelDate(BalanceInfoModel balance) throws DaoException;
	
}
