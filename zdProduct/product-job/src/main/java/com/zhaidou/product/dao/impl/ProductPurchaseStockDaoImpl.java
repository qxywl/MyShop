
package com.zhaidou.product.dao.impl;


import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductPurchaseStockDao;
import com.zhaidou.product.model.ProductPurchaseStock;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  donnie
 * @version  [版本号, 2015-12-03]
 */
@Repository("productPurchaseStockDao")
public class ProductPurchaseStockDaoImpl extends BaseDao  implements ProductPurchaseStockDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

    @Override
    public List<ProductPurchaseStock> queryByParams(ProductPurchaseStock productPurchaseStock) {
        return getSqlSession().selectList(getNameSpace() + ".queryByParams", productPurchaseStock);
    }
}
