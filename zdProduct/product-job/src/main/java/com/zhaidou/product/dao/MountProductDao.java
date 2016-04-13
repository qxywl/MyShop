/*
 * 文 件 名:  SalecategoryProductRelationDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.MountProductModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MountProductDao extends IDao {

	public boolean batchInsert(List<MountProductModel> list) throws DaoException;

	public List<MountProductModel> queryListByCategoryCode(MountProductModel mountProduct) throws DaoException;
	
	/**
	 * @Description:  根据运营分类ID加载其下所有opt_type=1的商品
	 * @param model
	 * @throws DaoException
	 * @return List<MountProduct>
	 */
	public List<MountProductModel> selectOpt1ByCategoryId(MountProductModel model) throws DaoException;

}
