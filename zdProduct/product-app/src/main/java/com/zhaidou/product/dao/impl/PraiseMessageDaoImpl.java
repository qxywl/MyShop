package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.PraiseMessageDao;

@Repository("praiseMessageDao")
public class PraiseMessageDaoImpl extends BaseDao implements PraiseMessageDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getName();
    }
    
}
