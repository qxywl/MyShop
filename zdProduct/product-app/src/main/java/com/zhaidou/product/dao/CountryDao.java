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
package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.CountryModel;






/**
 * 国家相关信息操作
 */
public interface CountryDao extends IDao
{
	/**
	 * 获取所有国家列表
	 * 
	 * @param CountryModel
	 * @return List<CountryModel>
	 * */
    List<CountryModel> getCountryAllList();
    
    
    /**
     * 获取满足条件的国家列表
     * @param CountryModel
     * @return List<CountryModel>
     * */
    List<CountryModel> getConditionList(CountryModel countryModel);
 
}
