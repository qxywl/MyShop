package com.zhaidou.product.dao.impl;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.dao.Base2Dao;
import com.zhaidou.product.dao.ProductSkuSupplierDao;

@Repository("productSkuSupplierDao")
public class ProductSkuSupplierDaoImpl extends Base2Dao implements ProductSkuSupplierDao {


    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateStatusList",map);
    }

}
