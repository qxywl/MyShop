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
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.attributies.dao.RelationGroupAttrDao;
import com.zhaidou.product.attributies.model.RelationGroupAttrModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("relationGroupAttrDao")
public class RelationGroupAttrDaoImpl extends BaseDao  implements RelationGroupAttrDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

	@Override
	public void deleteByAttrId(Long attrId) throws Exception
	{
		getSqlSession().delete(getNameSpace() + ".deleteByAttrId", attrId);
		getSqlSession().commit();
	}


	@Override
	public List<RelationGroupAttrModel> queryRelationGroupAttrList(RelationGroupAttrModel model)throws Exception
	{
		return getSqlSession().selectList(getNameSpace() + ".queryListPage", model);
		
	}

	@Override
	public void deleteByAttrIdAndGroupId(Map<String,Object> map)throws Exception 
	{
		getSqlSession().delete(getNameSpace()+".deleteByAttrIdAndGroupId", map);
		
	}
    
    
}
