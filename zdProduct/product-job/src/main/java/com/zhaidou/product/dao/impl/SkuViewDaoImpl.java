package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.SkuViewDao;

@Repository("skuViewDao")
public class SkuViewDaoImpl extends BaseDao implements SkuViewDao {
    
    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public void deleteSkuViewBySkuCode(List<String> codes) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".deleteSkuViewBySkuCode",codes);
    }

    @Override
    public void addSkuViewList(List<String> codes) throws Exception {
        getSqlSession().insert(this.getNameSpace()+".addSkuViewList",codes);
    }

    

}
