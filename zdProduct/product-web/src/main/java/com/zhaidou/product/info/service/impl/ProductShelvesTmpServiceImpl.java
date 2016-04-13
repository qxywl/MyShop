package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductShelvesTmpDao;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;
import com.zhaidou.product.info.service.ProductShelvesTmpService;

@Service("productShelvesTmpService")
public class ProductShelvesTmpServiceImpl implements ProductShelvesTmpService {
    
    @Resource
    private ProductShelvesTmpDao productShelvesTmpDao;
    
    @Override
    public void addShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel) throws Exception {
        productShelvesTmpDao.insert(productShelvesTmpModel);
    }

    @Override
    public ProductShelvesTmpModel getShelvesTmpByProductCode(ProductShelvesTmpModel productShelvesTmpModel)
            throws Exception {
        return productShelvesTmpDao.queryOne(productShelvesTmpModel);
    }

    @Override
    public void updateShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel) throws Exception {
        productShelvesTmpDao.update(productShelvesTmpModel);
    }

    @Override
    public List<ProductShelvesTmpModel> getShelvesTmpList(ProductShelvesTmpModel productShelvesTmpModel,Page page)
            throws Exception {
        long count = productShelvesTmpDao.countListPage(productShelvesTmpModel);
        List<ProductShelvesTmpModel> list = null;
        if (count > 0) {
            page.setTotalCount(count);
            list = productShelvesTmpDao.queryListPage(productShelvesTmpModel, page.getPageNum(), page.getNumPerPage());
        }
        return list;
    }

}
