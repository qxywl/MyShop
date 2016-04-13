/*
 * 文 件 名:  ProductInfoLogService.java
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
import com.zhaidou.product.info.model.ProductInfoLogModel;
import com.zhaidou.product.info.model.ProductModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductInfoLogService
{
    public void addProductInfoLog(ProductInfoLogModel productInfoLogModel)throws Exception;

    public void updateProductInfoLog(ProductInfoLogModel productInfoLogModel)throws Exception;

    public ProductInfoLogModel getProductInfoLogById(ProductInfoLogModel productInfoLogModel)throws Exception;

    public long getProductInfoLogCount(ProductInfoLogModel productInfoLogModel)throws Exception;

    public List<ProductInfoLogModel> getProductInfoLog(ProductInfoLogModel productInfoLogModel, Page page)throws Exception;

    public void deleteById(ProductInfoLogModel productInfoLogModel)throws Exception;

}
