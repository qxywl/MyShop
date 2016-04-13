/*
 * 文 件 名:  ProductSalecategoryRelationService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import com.zhaidou.product.model.ProductSalecategoryRelationModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ProductSalecategoryRelationService {


	public void insertOrUpdate(ProductSalecategoryRelationModel productCategory, Integer optType) throws Exception;

	/**
	 * @Description: 根据商品编号 ，把运营分类编号替换为空
	 * @param model
	 * @throws Exception
	 * return Integer
	 */
	public Integer updateCategorys(ProductSalecategoryRelationModel model) throws Exception;
	
	/**
	 * @Description: 删除 运营分类编号字段为空的记录 
	 * @throws Exception
	 * return Integer
	 */
	public Integer deleteByCategorysIsEmpty() throws Exception;
}
