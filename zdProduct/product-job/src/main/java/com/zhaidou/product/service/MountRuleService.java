package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.product.model.BalanceInfoModel;
import com.zhaidou.product.model.MountRuleModel;

/**
 * 
 * @Title: MountRuleService.java 
 *
 * @Package com.teshehui.product.category.service 
 *
 * @Description:   运营分类自动挂载规则Service
 *
 * @author lvshuding 
 *
 * @date 2015年4月9日 上午10:45:55 
 *
 * @version V1.0
 */
public interface MountRuleService {

	/**
	 * @Description:查询deleteFlag=0的记录总条数
	 * @param balance
	 * @throws Exception
	 * return Long
	 */
	public Long totCountByEffective(BalanceInfoModel balance) throws Exception;
	
	/**
	 * @Description: 分页查询有效的规则数据
	 * @param balance
	 * @throws Exception
	 * return List<MountRulePO>
	 */
	public List<MountRuleModel> queryPage(BalanceInfoModel balance) throws Exception;
	
	/**
	 * @Description:  根据删除日期加载所有已删除的规则数据
	 * @param balance  date格式 ：yyyy-MM-dd
	 * @throws Exception
	 * return List<MountRulePO>
	 */
	public List<MountRuleModel> queryDeleteRuleByDelDate(BalanceInfoModel balance) throws Exception;
}
