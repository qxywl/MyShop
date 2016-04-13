/*
 * 文 件 名:  ProductSalecategoryRelationDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductSalecategoryRelationDao;
import com.zhaidou.product.model.ProductSalecategoryRelationModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("productSalecategoryRelationDao")
public class ProductSalecategoryRelationDaoImpl extends BaseDao implements
		ProductSalecategoryRelationDao {
	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public ProductSalecategoryRelationModel getByProductCode(ProductSalecategoryRelationModel productCategory) throws Exception {
		try{
			return getSqlSession().selectOne(getNameSpace() + ".queryOneByProductCode", productCategory);
		}catch(Exception e){
			throw new DaoException(e);
		}
		
	}

	@Override
	public Integer updateCategorys(ProductSalecategoryRelationModel model)
			throws DaoException {
		try{
			return getSqlSession().update(getNameSpace() + ".updateCategorys", model);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public Integer deleteByCategorysIsEmpty() throws DaoException {
		try{
			return getSqlSession().delete(getNameSpace() + ".deleteByCategorysIsEmpty");
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	
}
