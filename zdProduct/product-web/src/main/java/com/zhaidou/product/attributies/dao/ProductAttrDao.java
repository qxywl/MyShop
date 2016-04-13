/*
 * 文 件 名:  ProductAttrDAO.java
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
import com.zhaidou.product.attributies.model.ProductAttrModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttrDao extends IDao
{
	/**
	 * 根据属性组编码 查询相关的属性项
	 * 
	 * @param groupCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryAttrListByGroupCode(String groupCode, int pageNo, int pageSize ) throws Exception;
	
	/**
	 * 根据属性组编码 查询相关的属性项的总条数
	 * @param groupCode
	 * @return
	 * @throws Exception
	 */
	public Long countListPageByGroupCode(String groupCode) throws Exception;
	
	/**
	 * 根据属性项名 模糊查询属性项列表
	 * @param attrName
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryAttrListByAttrName(String attrName) throws Exception;
	
	/**
	 * 查询与属性组相关联的属性
	 * @param model 不代表一个Model,且用作传递数据 ----》其中的attrId为传入的groupId的值
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryGroupRelationAttrList(ProductAttrModel model,int pageNo, int pageSize)throws Exception;

	public Long countQueryGroupRelationAttrList(ProductAttrModel model) throws Exception;
	
	/**
	 * 查询与属性组还没有相关联的属性
	 * @param model 不代表一个Model,且用作传递数据 ----》其中的attrId为传入的groupId的值
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryGroupRelationAttrNotBundList(ProductAttrModel model,int pageNo, int pageSize)throws Exception;

	public Long countQueryGroupRelationAttrNotBundList(ProductAttrModel model) throws Exception;
	
	/**
	 * 获取表中最大Id
	 * @return
	 * @throws Exception
	 */
	public Long getMaxId() throws Exception;

	/**
	 * 查询当前属性名是否存在
	 * @param attrName
	 * @return
	 * @throws DaoException
	 */
	public ProductAttrModel selectByAttrName( String attrName )throws DaoException;
}
