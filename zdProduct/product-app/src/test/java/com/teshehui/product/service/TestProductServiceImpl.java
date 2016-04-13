package com.teshehui.product.service;

import junit.framework.TestCase;

import com.zhaidou.product.service.impl.ProductServiceImpl;
import com.zhaidou.product.service.impl.ProductStockServiceImpl;

public class TestProductServiceImpl extends TestCase {
    ProductServiceImpl service = new ProductServiceImpl();
    ProductStockServiceImpl stockService = new ProductStockServiceImpl();
}
