package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductPriceListModel;
import com.zhaidou.product.info.model.ProductPriceLogModel;

public interface ProductPriceListManager {
	
	public void addProductPriceList(List<List<String>> list,ProductPriceListModel productPriceListModel)throws Exception;

    public void updateProductPriceList(Long[] ids,ProductPriceListModel productPriceListModel)throws Exception;
    
    public ProductPriceListModel getProductPriceListById(ProductPriceListModel productPriceListModel)throws Exception;

    public Map<String, Object> getProductPriceList(ProductPriceListModel productPriceListModel, Page page)throws Exception;
    
    
    public Map<String, Object> getProductPriceLogList(ProductPriceLogModel  productPriceLogModel, Page page)throws Exception;
    

    public void deleteById(ProductPriceListModel productPriceListModel)throws Exception;
    
    public void updateProductPriceList(ProductPriceListModel productPriceListModel,ProductPriceListModel oldPriceModel)throws Exception;
    
}
