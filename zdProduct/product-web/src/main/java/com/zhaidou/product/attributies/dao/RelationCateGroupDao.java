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
package com.zhaidou.product.attributies.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.attributies.model.RelationCateGroupModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RelationCateGroupDao extends IDao
{
	/**
	 * 通过groupId,删除分类与属性组的关联关系
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteByGroupId(Long groupId) throws Exception;
	
	/**
	 * 通过groupId,categoryId 删除分类与属性组的关联关系
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteModel(RelationCateGroupModel model) throws Exception;
	
	
	/**
	 * 获取符合条件的RelationCateGroupModel 
	 * @param RelationCateGroupModel 分类属性组关联对象模型
	 * @return List<RelationCateGroupModel>分类属性组关联对象模型 集合
	 * @throws Exception
	 * */
	public List<RelationCateGroupModel> queryConditionList(RelationCateGroupModel relationCateGroupModel)throws Exception;
}
