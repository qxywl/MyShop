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
package com.zhaidou.product.dao;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ProductSalecategoryRelationModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-09]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductSalecategoryRelationDao extends IDao
{

	public ProductSalecategoryRelationModel getByProductCode(ProductSalecategoryRelationModel productCategory) throws Exception;
    
	/**
	 * @Description: 根据商品编号 ，把运营分类编号替换为空
	 * @param model
	 * @throws DaoException
	 * return Integer
	 */
	public Integer updateCategorys(ProductSalecategoryRelationModel model) throws DaoException;
	
	/**
	 * @Description: 删除 运营分类编号字段为空的记录 
	 * @throws DaoException
	 * return Integer
	 */
	public Integer deleteByCategorysIsEmpty() throws DaoException;
}
