/*
 * 文 件 名:  ProductShelvesLogService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.ProductShelvesLogModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductShelvesLogService
{
    public void addProductShelvesLog(ProductShelvesLogModel productShelvesLogModel)throws Exception;

    public void updateProductShelvesLog(ProductShelvesLogModel productShelvesLogModel)throws Exception;

    public ProductShelvesLogModel getProductShelvesLogById(ProductShelvesLogModel productShelvesLogModel)throws Exception;

    public long getProductShelvesLogCount(ProductShelvesLogModel productShelvesLogModel)throws Exception;

    public List<ProductShelvesLogModel> getProductShelvesLog(ProductShelvesLogModel productShelvesLogModel, Page page)throws Exception;

    public void deleteById(ProductShelvesLogModel productShelvesLogModel)throws Exception;
    
    public void addShelvesLogList(List<ProductShelvesLogModel> shelvesLogList) throws Exception;
}
