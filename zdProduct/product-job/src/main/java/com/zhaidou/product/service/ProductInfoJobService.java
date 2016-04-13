/*
 * 文 件 名:  ProductInfoJobService.java
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
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.ProductInfoJobModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductInfoJobService
{
    public void addProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception;

    public void updateProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception;

    public ProductInfoJobModel getProductInfoJobById(ProductInfoJobModel productInfoJobModel)throws Exception;

    public long getProductInfoJobCount(ProductInfoJobModel productInfoJobModel)throws Exception;

    public List<ProductInfoJobModel> getProductInfoJob(ProductInfoJobModel productInfoJobModel, Page page)throws Exception;

    public void deleteById(ProductInfoJobModel productInfoJobModel)throws Exception;
    
    public void deleteByIds(List<Long> ids)throws Exception;
    
    public void updateStatusList(Map<String,Object> map)throws Exception;
    
    public void addInfoJobList(List<ProductInfoJobModel> infoJobList) throws Exception;
}
