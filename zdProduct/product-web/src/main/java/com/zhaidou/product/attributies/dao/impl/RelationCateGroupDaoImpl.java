/*
 * 文 件 名:  RelationGroupAttrDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.attributies.dao.RelationCateGroupDao;
import com.zhaidou.product.attributies.model.RelationCateGroupModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  hutongqign
 * @version  [版本号, 2015-04-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("relationCateGroupDao")
public class RelationCateGroupDaoImpl extends BaseDao  implements RelationCateGroupDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

	@Override
	public void deleteByGroupId(Long groupId) throws Exception {
		
		getSqlSession().delete(getNameSpace() + ".deleteByGroupId", groupId);
		
	}

	
	@Override
	public List<RelationCateGroupModel> queryConditionList(
			RelationCateGroupModel relationCateGroupModel) throws Exception {
		List<RelationCateGroupModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryConditionList", relationCateGroupModel);
		return list;
	}

	@Override
	public void deleteModel(RelationCateGroupModel model) throws Exception
	{
		getSqlSession().delete(getNameSpace() + ".deleteModel", model);
		
	}

    
}
