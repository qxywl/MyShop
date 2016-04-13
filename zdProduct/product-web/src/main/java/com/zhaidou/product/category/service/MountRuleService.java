package com.zhaidou.product.category.service;

import java.util.List;

import com.zhaidou.common.util.Response;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.category.model.MountRulePO;

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

	public Response insert(MountRulePO po);
	
	public Response delete(MountRulePO po);
	
	public Response update(MountRulePO po);
	
	/**
	 * @Description: 根据Id或分类编号或分类Id查询规则对象
	 * @param po
	 * @return
	 * return MountRulePO
	 */
	public MountRulePO  queryOne(MountRulePO po);
	
	/**
	 * @Description:查询deleteFlag=0的记录总条数
	 * @throws Exception
	 * return Long
	 */
	public Long totCountByEffective() throws Exception;
	
	/**
	 * @Description: 分页查询有效的规则数据
	 * @param page
	 * @throws Exception
	 * return List<MountRulePO>
	 */
	public List<MountRulePO> queryPage(Page page) throws Exception;
	
	/**
	 * @Description:  根据删除日期加载所有已删除的规则数据
	 * @param date    格式 ：yyyy-MM-dd
	 * @throws Exception
	 * return List<MountRulePO>
	 */
	public List<MountRulePO> queryDeleteRuleByDelDate(String date) throws Exception;
}
