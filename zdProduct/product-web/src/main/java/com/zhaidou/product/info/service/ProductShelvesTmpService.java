package com.zhaidou.product.info.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;

public interface ProductShelvesTmpService {
    
    public void addShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel) throws Exception;
    
    public ProductShelvesTmpModel getShelvesTmpByProductCode(ProductShelvesTmpModel productShelvesTmpModel)throws Exception;
    
    public void updateShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel)throws Exception;
    
    public List<ProductShelvesTmpModel> getShelvesTmpList(ProductShelvesTmpModel productShelvesTmpModel,Page page)throws Exception;
}
