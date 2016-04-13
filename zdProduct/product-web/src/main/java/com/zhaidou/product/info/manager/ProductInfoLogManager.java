package com.zhaidou.product.info.manager;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductInfoLogModel;

public interface ProductInfoLogManager {
    
//    public void addInfoLog(ProductModel productModel,Map<String,String> map);
    
    /**
     * 商品信息日志列表
     *
     * @param page
     * @param productInfoLogModel
     * @return
     */
    public List<ProductInfoLogModel> infoLogList(Page page,ProductInfoLogModel productInfoLogModel);
    
    /**
     * 商品信息日志  详情
     *
     * @param page
     * @param productInfoLogModel
     * @return
     */
    public ProductInfoLogModel getProductInfoLogById(ProductInfoLogModel productInfoLogModel);
}
