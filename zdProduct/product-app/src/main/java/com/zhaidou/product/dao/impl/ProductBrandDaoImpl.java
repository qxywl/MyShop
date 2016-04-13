/*
 * 文 件 名:  ProductBrandDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductBrandDao;
import com.zhaidou.product.model.base.ProductBrandModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productBrandDao")
public class ProductBrandDaoImpl extends BaseDao  implements ProductBrandDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

    
	@Override
	public List<ProductBrandModel> getBrandAllList() {
		List<ProductBrandModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryAllList", null);
		return list;
	}
	
	
	@Override
	public List<ProductBrandModel> getEnableBrandAllList() {
		List<ProductBrandModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryEnableAllList", null);
		return list;
	}

	
	@Override
	public ProductBrandModel getBrandByCode(String brandCode) {
		ProductBrandModel ProductBrandModel = null;
		ProductBrandModel = getSqlSession().selectOne(getNameSpace() + ".queryOneByCode", brandCode);
		return ProductBrandModel;
	}

	
	@Override
	public ProductBrandModel getEnableBrandByCode(String brandCode) {
		ProductBrandModel ProductBrandModel = null;
		ProductBrandModel = getSqlSession().selectOne(getNameSpace() + ".queryEnableOneByCode", brandCode);
		return ProductBrandModel;
	}

	
	@Override
	public ProductBrandModel getBrandByName(String brandName) {
		ProductBrandModel ProductBrandModel = null;
		ProductBrandModel = getSqlSession().selectOne(getNameSpace() + ".queryOneByName", brandName);
		return ProductBrandModel;
	}
	
	
	@Override
	public ProductBrandModel getEnableBrandByName(String brandName) {
		ProductBrandModel ProductBrandModel = null;
		ProductBrandModel = getSqlSession().selectOne(getNameSpace() + ".queryEnableOneByName", brandName);
		return ProductBrandModel;
	}
	
	@Override
	public  Long getMaxBrandId() {
		Long maxBrandId = getSqlSession().selectOne(getNameSpace() + ".getMaxBrandId", null);
		return maxBrandId;
	}


	


	
}
