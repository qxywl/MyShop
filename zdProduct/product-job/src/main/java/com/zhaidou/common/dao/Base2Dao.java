package com.zhaidou.common.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zhaidou.framework.dao.IDao;


public abstract class Base2Dao extends BaseSqlSessionDao2Support implements IDao {

    public <T> Integer insert(T t) throws Exception {
        try {
            return (Integer) getSqlSession().insert(getNameSpace() + ".insert", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public <T> Integer delete(T t) throws Exception {
        try {
            return getSqlSession().delete(getNameSpace() + ".delete", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public <T> Integer update(T t) throws Exception {
        try {
            return getSqlSession().update(getNameSpace() + ".update", t);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T queryOne(T keyId) throws Exception {
        try {
            T t = (T) getSqlSession().selectOne(getNameSpace() + ".queryOne", keyId);
            return t;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public <T> List<T> queryListPage(T t, int pageNo, int pageSize) throws Exception {
        try {
            List<T> list = null;
            if (pageSize != Integer.MAX_VALUE) {
                list = getSqlSession()
                        .selectList(getNameSpace() + ".queryListPage", t, new RowBounds(pageNo, pageSize));
            } else {
                list = getSqlSession().selectList(getNameSpace() + ".queryListPage", t);
            }
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public <T> Long countListPage(T t) throws Exception {
        try {
            Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".countListPage", t);
            return count;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public abstract String getNameSpace();
}
