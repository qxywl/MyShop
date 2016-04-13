package com.zhaidou.product.info.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.SupplierModel;

public interface SupplierService {
    
    public SupplierModel getSupplier(SupplierModel supplierModel)throws Exception;
    
    public List<SupplierModel> getSupplier(SupplierModel supplierModel, Page page)throws Exception;
    
    public String importdSupplier(String filename,  byte[] buf,List<String> titleList,List<String> fieldList) throws Exception;
}
