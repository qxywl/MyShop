/*
 * 文 件 名:  VirtualProductDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.VirtualProductDao;
import com.zhaidou.product.model.VirtualProductModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author mingbao
 * @version [版本号, 2015-10-01]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("virtualProductDao")
public class VirtualProductDaoImpl extends BaseDao implements VirtualProductDao {
	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	public Integer deleteByIds(List<Long> ids) throws Exception {
		try {
			return Integer.valueOf(getSqlSession().delete(
					getNameSpace() + ".deleteByIds", ids));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<VirtualProductModel> queryVirtualProductBySkuCodes(
			List<String> virtualProductCodeList,int pageNo, int pageSize) throws Exception {
		try {
			List<VirtualProductModel>  list= null;
			if (pageSize != 2147483647) {
				list = getSqlSession().selectList(
						getNameSpace() + ".queryVirtualProductBySkuCodes", virtualProductCodeList,
						new RowBounds(pageNo, pageSize));
			} else {
				list = getSqlSession().selectList(
						getNameSpace() + ".queryVirtualProductBySkuCodes", virtualProductCodeList);
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
}
