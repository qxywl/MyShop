/*
 * 文 件 名:  ProductAttrGroupDAO.java
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

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttrGroupDao extends IDao
{
    
	/**
	 * 根据属性组的名称，模糊查询属性组列表
	 * @param groupName 属性组名
	 * @return List<ProductAttrGroupModel>
	 * @throws Exception
	 * 
	 * @author hutongqing
	 */
	public List<ProductAttrGroupModel> queryAttrGroupByName(String groupName) throws Exception;
	
	/**
	 * 根据表名 获取表的最大id
	 * @param tableName
	 * @return
	 */
	public Long getMaxId() throws Exception;

	/**
	 * 根据基础分类ID 查询关联的属性组
	 * @param categoryId 基础分类ID
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrGroupModel> queryByCategoryId(Long categoryId) throws DaoException;
}
