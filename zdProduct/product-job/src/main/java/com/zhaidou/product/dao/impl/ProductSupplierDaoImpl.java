/*
 * 文 件 名:  ProductDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.zhaidou.common.dao.Base2Dao;
import com.zhaidou.product.dao.ProductSupplierDao;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("productSupplierDao")
public class ProductSupplierDaoImpl extends Base2Dao implements ProductSupplierDao {
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public <T> Long countListPage(T t) throws Exception {
        try {
            Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".countListPage", t);
            
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @Override
    public <T> Integer delete(T t) throws Exception {
        try {
            return getSqlSession().delete(getNameSpace() + ".delete", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public <T> Integer insert(T arg0) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> queryListPage(T t, int pageNo, int pageSize) throws Exception {
        try {
            List<T> list = null;
            if (pageSize != Integer.MAX_VALUE) {
                list = getSqlSession().selectList(getNameSpace() + ".queryListPage", t, new RowBounds(pageNo, pageSize));
            } else {
                list = getSqlSession().selectList(getNameSpace() + ".queryListPage", t);
            }
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public <T> T queryOne(T arg0) throws Exception {
        try {
            T t = (T) getSqlSession().selectOne(getNameSpace() + ".queryOne", arg0);
            return t;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public <T> Integer update(T t) throws Exception {
        try {
            return getSqlSession().update(getNameSpace() + ".update", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public <T> Integer updateSupplierProductByProductCode(T t) throws Exception  {
        try {
            return getSqlSession().update(getNameSpace() + ".updateSupplierProductByProductCode", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
        
    }

    

}
