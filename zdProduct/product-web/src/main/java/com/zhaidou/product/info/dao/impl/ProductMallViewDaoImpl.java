/*
 * 文 件 名:  ProductDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-23
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductMallViewDao;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("productMallViewDao")
public class ProductMallViewDaoImpl extends BaseDao implements ProductMallViewDao {

	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public long countByCatCodes(List<String> categoryCodeList) throws DaoException {
		try {
			return (Long)getSqlSession().selectOne(getNameSpace() + ".countByCatCodes", categoryCodeList);
		} catch (Exception e) {
			logger.error("根据基础分类Code查询挂在该分类及子分类下的有效商品失败:", e);
			throw new DaoException("根据基础分类Code查询挂在该分类及子分类下的有效商品失败", e);
		}
	}

}