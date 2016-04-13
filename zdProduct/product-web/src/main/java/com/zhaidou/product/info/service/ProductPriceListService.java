/*
 * 文 件 名:  ProductPriceListService.java
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
import com.zhaidou.product.info.model.ProductPriceListModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductPriceListService

{
    public void addProductPriceList(ProductPriceListModel productPriceListModel)throws Exception;
    
    public void addProductPriceList(List<List<String>> list,ProductPriceListModel productPriceListModel)throws Exception;

    public void updateProductPriceList(Long[] ids,ProductPriceListModel productPriceListModel)throws Exception;

    public ProductPriceListModel getProductPriceListById(ProductPriceListModel productPriceListModel)throws Exception;

    public long getProductPriceListCount(ProductPriceListModel productPriceListModel)throws Exception;

    public List<ProductPriceListModel> getProductPriceList(ProductPriceListModel productPriceListModel, Page page)throws Exception;

    public void deleteById(ProductPriceListModel productPriceListModel)throws Exception;
    
    public void updateProductPriceList(ProductPriceListModel productPriceListModel,ProductPriceListModel oldPriceModel)throws Exception;

}
