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
package com.zhaidou.product.attributies.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.attributies.dao.ProductAttrDao;
import com.zhaidou.product.attributies.model.ProductAttrModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("productAttrDao")
public class ProductAttrDaoImpl extends BaseDao implements ProductAttrDao {
	@Override
	public String getNameSpace() {
		return this.getClass().getName();
	}

	@Override
	public List<ProductAttrModel> queryAttrListByGroupCode(String groupCode,int pageNo, int pageSize) throws Exception
	{
		
		List<ProductAttrModel> list = getSqlSession().selectList(
				getNameSpace() + ".queryAttrListByGroupCode", groupCode,new RowBounds(pageNo, pageSize));
		
		return list;
	}

	@Override
	public Long countListPageByGroupCode(String groupCode) throws Exception 
	{
		Long count = getSqlSession().selectOne(getNameSpace() + ".countListPageByGroupCode", groupCode);
		
		return count;
	}

	@Override
	public List<ProductAttrModel> queryAttrListByAttrName(String attrName) throws Exception 
	{
		List<ProductAttrModel> list = getSqlSession().selectList(
				getNameSpace() + ".queryAttrListByAttrName", attrName);
		
		return list;
	}

	@Override
	public List<ProductAttrModel> queryGroupRelationAttrList(ProductAttrModel model, int pageNo, int pageSize) 
			throws Exception 
	{
		List<ProductAttrModel> list = getSqlSession().selectList(
				getNameSpace() + ".queryGroupRelationAttrList", model,new RowBounds(pageNo, pageSize));
		
		return list;
	}

	@Override
	public Long countQueryGroupRelationAttrList(ProductAttrModel model)throws Exception {
		
		Long count = getSqlSession().selectOne(getNameSpace() + ".countQueryGroupRelationAttrList", model);
		
		return count;
	}

	@Override
	public List<ProductAttrModel> queryGroupRelationAttrNotBundList(
			ProductAttrModel model, int pageNo, int pageSize) throws Exception {
		List<ProductAttrModel> list = getSqlSession().selectList(
				getNameSpace() + ".queryGroupRelationAttrNotBundList", model,new RowBounds(pageNo, pageSize));
		
		return list;
	}

	@Override
	public Long countQueryGroupRelationAttrNotBundList(ProductAttrModel model)throws Exception {
		
		Long count = getSqlSession().selectOne(getNameSpace() + ".countQueryGroupRelationAttrNotBundList", model);
		
		return count;
	}


	@Override
	public Long getMaxId() throws Exception {
		
		return getSqlSession().selectOne(getNameSpace() + ".getMaxId");
	}

	@Override
	public ProductAttrModel selectByAttrName( String attrName ) throws DaoException {

		return getSqlSession().selectOne(getNameSpace() + ".selectByAttrName",attrName);
	}
	
}
