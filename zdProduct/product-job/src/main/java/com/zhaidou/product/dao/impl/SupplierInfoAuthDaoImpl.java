package com.zhaidou.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.dao.Base2Dao;
import com.zhaidou.product.dao.SupplierInfoAuthDao;

@Repository("supplierInfoAuthDao")
public class SupplierInfoAuthDaoImpl extends Base2Dao implements SupplierInfoAuthDao{

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }
    
    
}
