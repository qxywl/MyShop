/*
 * 文 件 名:  SalecategoryProductRelationLogDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.category.dao.MountProductLogDao;
import com.zhaidou.product.category.model.MountProductLog;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("mountProductLog")
public class MountProductLogDaoImpl extends BaseDao  implements MountProductLogDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

    @Override
    public boolean batchInsert(List<MountProductLog> logList) throws DaoException{
    	try{
    		getSqlSession().insert(getNameSpace() + ".batchInsert", logList);
    	}catch(Exception e){
    		throw new DaoException("产品挂载日志批量插入失败", e);
    	}
    	return true;
    }
}
