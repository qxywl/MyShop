/*
 * 文 件 名:  ProductMallViewService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.model.mall.ProductModel;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-10-01]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductMallViewService
{
    public void addProductMallView(ProductMallViewModel productMallViewModel)throws Exception;

    public void updateProductMallViewById(ProductMallViewModel productMallViewModel)throws Exception;

    public ProductMallViewModel getProductMallViewById(ProductMallViewModel productMallViewModel)throws Exception;

    public long getProductMallViewCount(ProductMallViewModel productMallViewModel)throws Exception;

    public List<ProductMallViewModel> getProductMallView(ProductMallViewModel productMallViewModel, Page page)throws Exception;

    public void deleteById(ProductMallViewModel productMallViewModel)throws Exception;    
   
    public List<ProductMallViewModel> getProductMallViewByProductCodes(List<String> productCodeList)throws Exception;
}
