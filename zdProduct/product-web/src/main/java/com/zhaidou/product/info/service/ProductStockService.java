/*
 * 文 件 名:  ProductStockService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductStockModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductStockService
{
    public void addProductStock(ProductStockModel productStockModel)throws Exception;

    public void updateProductStock(ProductStockModel productStockModel)throws Exception;

    public ProductStockModel getProductStockById(ProductStockModel productStockModel)throws Exception;

    public long getProductStockCount(ProductStockModel productStockModel)throws Exception;

    public List<ProductStockModel> getProductStock(ProductStockModel productStockModel, Page page)throws Exception;

    public void deleteById(ProductStockModel productStockModel)throws Exception;
    
    public List<ProductStockModel> getProductStockListByProductId(List<Long> productIdList)throws Exception;

}
