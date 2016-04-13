/*
 * 文 件 名:  BasecategoryOperateLogDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-03-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.BaseCategoryOperateLogDao;
import com.zhaidou.product.category.model.BaseCategoryOperateLog;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("baseCategoryOperateLogDao")
public class BaseCategoryOperateLogDaoImpl extends BaseDao implements BaseCategoryOperateLogDao {
	@Override
	public String getNameSpace() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Integer deleteByIds(List<Long> ids) throws DaoException{
		try{
			return this.getSqlSession().delete(getNameSpace() + ".deleteByIds", ids);
		}catch(Exception e){
			throw new DaoException();
		}
	}

	@Override
	public Integer inserts(List<BaseCategoryOperateLog> list) throws DaoException{
		try{
			return this.getSqlSession().insert(getNameSpace() + ".inserts", list);
		}catch(Exception e){
			throw new DaoException();
		}
	}
}
