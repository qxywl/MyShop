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
package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.ProductCateAttrGroupModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  weiwei
 * @version  [版本号, 2015-05-06]
 * @see     [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductCateAttrGroupDao extends IDao
{
	/**
	 * 通过categoryCode 获取 ProductCateAttrGroupModel集合提供给搜索用
	 * @param String categoryCode
	 * @return  List<ProductCateAttrGroupModel>
	 * */
	public List<ProductCateAttrGroupModel> queryGroupAndAttrValueByCate(String categoryCode) throws Exception;
	
	
	/**
	 * 通过categoryCode 获取ProductCateAttrGroupModel集合 提供给product 
	 * @param String categoryCode
	 * @return  List<ProductCateAttrGroupModel>
	 * */
	public List<ProductCateAttrGroupModel> queryProductAttrValueByCate(String categoryCode) throws Exception;
	
	
}
