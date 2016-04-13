package com.zhaidou.product.category.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.MountRulePO;


/**
 * 
 * @Title: MountRuleDao.java 
 *
 * @Package com.teshehui.product.category.dao 
 *
 * @Description:   自动挂载规则Dao
 *
 * @author lvshuding 
 *
 * @date 2015年4月9日 下午4:12:09 
 *
 * @version V1.0
 */
public interface MountRuleDao extends IDao {

	/**
	 * @Description:查询deleteFlag=0的记录总条数
	 * @throws DaoException
	 * return Long
	 */
	public Long totCountByEffective() throws DaoException;
	
	/**
	 * @Description: 分页查询有效的规则数据
	 * @param page
	 * return List<MountRulePO>
	 */
	public List<MountRulePO> queryPage(Page page) throws DaoException;
	
	/**
	 * @Description:  根据删除日期加载所有已删除的规则数据
	 * @param date    格式 ：yyyy-MM-dd
	 * return List<MountRulePO>
	 */
	public List<MountRulePO> queryDeleteRuleByDelDate(String date) throws DaoException;
	
}
