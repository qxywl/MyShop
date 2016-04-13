package com.zhaidou.product.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ProductPurchaseStock;

import java.util.List;


public interface ProductPurchaseStockDao extends IDao {


    List<ProductPurchaseStock> queryByParams(ProductPurchaseStock productPurchaseStock);

} 
