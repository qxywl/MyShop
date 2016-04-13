package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.PraiseMessageDao;
import com.zhaidou.product.model.PraiseMessageModel;

@Repository("praiseMessageDao")
public class PraiseMessageDaoImpl extends BaseDao implements PraiseMessageDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public PraiseMessageModel getProductInfo(PraiseMessageModel praiseMessageModel) throws Exception {
        
        return this.getSqlSession().selectOne(getNameSpace()+".getProductInfo",praiseMessageModel);
    }

    @Override
    public void changeRetryNum(PraiseMessageModel praiseMessageModel) throws Exception {
        this.getSqlSession().selectOne(getNameSpace()+".changeRetryNum",praiseMessageModel);
    }
    
}
