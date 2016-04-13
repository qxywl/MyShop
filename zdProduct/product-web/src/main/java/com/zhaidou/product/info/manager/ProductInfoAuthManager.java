package com.zhaidou.product.info.manager;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductInfoAuthModel;

public interface ProductInfoAuthManager {
    
    /**
     * 获取商品 审核列表
     *
     * @param page
     * @return
     */
    public List<ProductInfoAuthModel> getProductInfoAuthList(Page page,ProductInfoAuthModel productInfoAuthModel)throws Exception;
    
    /**
     * 修改 商品审核对象
     *
     * @param page
     * @return
     */
    public boolean updateProductInfo(ProductInfoAuthModel productInfoAuthModel)throws Exception;
    
    /**
     * 查看 商品审核 详情
     *
     * @param page
     * @return
     */
    public Map<String,Object> getProductInfoAuth(ProductInfoAuthModel productInfoAuthModel)throws Exception;
    
    /**
     * 查看 商品审核 详情
     *
     * @param page
     * @return
     */
    public void getProductInfoAuthTwo(ProductInfoAuthModel productInfoAuthModel) throws Exception;
    
    public ProductInfoAuthModel getProductInfoAuthById(ProductInfoAuthModel productInfoAuthModel) throws Exception;
}
