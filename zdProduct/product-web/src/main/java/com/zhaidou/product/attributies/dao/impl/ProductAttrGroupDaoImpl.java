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
package com.zhaidou.product.attributies.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.attributies.dao.ProductAttrGroupDao;
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
@Repository("productAttrGroupDao")
public class ProductAttrGroupDaoImpl extends BaseDao  implements ProductAttrGroupDao
{
	private static Logger logger = Logger.getLogger(ProductAttrGroupDaoImpl.class);
	
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

	@Override
	public List<ProductAttrGroupModel> queryAttrGroupByName(String groupName)throws Exception
	{
		
		return getSqlSession().selectList(getNameSpace() + ".queryAttrGroupByName", groupName);
	}

	@Override
	public Long getMaxId() throws Exception {
		
		return getSqlSession().selectOne(getNameSpace() + ".getMaxId");
	}


	@Override
	public List<ProductAttrGroupModel> queryByCategoryId(Long categoryId) throws DaoException {
		try{
			return getSqlSession().selectList(getNameSpace() + ".queryByCategoryId", categoryId);
		}catch(Exception e){
			logger.error("根据基础分类ID categoryId 查询关联内存", e);
			throw new DaoException("根据基础分类ID categoryId 查询关联内存",e);
		}
	}
	
}
