package com.zhaidou.product.info.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductShelvesTmpDao;

@Repository("productShelvesTmpDao")
public class ProductShelvesTmpDaoImpl extends BaseDao implements ProductShelvesTmpDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

}
