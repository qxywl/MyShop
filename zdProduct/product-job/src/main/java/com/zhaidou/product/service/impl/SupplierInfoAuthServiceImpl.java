package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.SupplierInfoAuthDao;
import com.zhaidou.product.model.SupplierInfoAuthModel;
import com.zhaidou.product.service.SupplierInfoAuthService;

@Service("supplierInfoAuthService")
public class SupplierInfoAuthServiceImpl implements SupplierInfoAuthService {
    
    @Resource
    private SupplierInfoAuthDao supplierInfoAuthDao;
    
    @Override
    public List<SupplierInfoAuthModel> getProduct(SupplierInfoAuthModel supplierInfoAuthModel, Page page)
            throws Exception {
        
        return supplierInfoAuthDao.queryListPage(supplierInfoAuthModel, page.getPageNum(), page.getNumPerPage());
    }

    @Override
    public void deleteById(SupplierInfoAuthModel supplierInfoAuthModel) throws Exception {
        supplierInfoAuthDao.delete(supplierInfoAuthModel);
    }

}
