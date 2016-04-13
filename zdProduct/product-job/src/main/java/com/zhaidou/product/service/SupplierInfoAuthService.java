package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.SupplierInfoAuthModel;

public interface SupplierInfoAuthService {
    
    public List<SupplierInfoAuthModel> getProduct(SupplierInfoAuthModel supplierInfoAuthModel, Page page) throws Exception;
    
    public void deleteById(SupplierInfoAuthModel supplierInfoAuthModel) throws Exception;

}
