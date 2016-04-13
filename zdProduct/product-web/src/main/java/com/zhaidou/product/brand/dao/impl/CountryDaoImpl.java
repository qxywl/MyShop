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
package com.zhaidou.product.brand.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.brand.dao.CountryDao;
import com.zhaidou.product.brand.dao.ProductBrandDao;
import com.zhaidou.product.brand.model.CountryModel;
import com.zhaidou.product.brand.model.ProductBrandModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("countryDao")
public class CountryDaoImpl extends BaseDao  implements CountryDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

	@Override
	public List<CountryModel> getCountryAllList() {
		List<CountryModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryAllList", null);
		return list;
	}

	@Override
	public List<CountryModel> getConditionList(CountryModel countryModel) {
		List<CountryModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryConditionList", countryModel);
		return list;
	}

    
	
}
