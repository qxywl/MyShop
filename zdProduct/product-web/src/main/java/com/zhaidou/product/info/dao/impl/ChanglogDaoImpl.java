/*
 * 文 件 名:  ChanglogDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-31
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ChanglogDao;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("changlogDao")
public class ChanglogDaoImpl extends BaseDao  implements ChanglogDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
    
    public  Integer deleteByIds(List<Long> ids) throws Exception {
		try {
			return Integer.valueOf(getSqlSession().delete(
					getNameSpace() + ".deleteByIds", ids));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
