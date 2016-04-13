/*
 * 文 件 名:  ProductImageService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductImageModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductImageService
{
    public void addProductImage(ProductImageModel productImageModel)throws Exception;

    public void updateProductImage(ProductImageModel productImageModel)throws Exception;

    public ProductImageModel getProductImageById(ProductImageModel productImageModel)throws Exception;

    public long getProductImageCount(ProductImageModel productImageModel)throws Exception;

    public List<ProductImageModel> getProductImage(ProductImageModel productImageModel, Page page)throws Exception;

    public void deleteById(ProductImageModel productImageModel)throws Exception;
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    public List<ProductImageModel> getImageBySkuId(ProductImageModel productImageModel)throws Exception;

}
