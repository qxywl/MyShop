/*
 * 文 件 名:  ProductShelvesJobService.java
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
import com.zhaidou.product.model.ProductShelvesJobModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductShelvesJobService
{
    public void addProductShelvesJob(ProductShelvesJobModel productShelvesJobModel)throws Exception;

    public void updateProductShelvesJob(ProductShelvesJobModel productShelvesJobModel)throws Exception;

    public ProductShelvesJobModel getProductShelvesJobById(ProductShelvesJobModel productShelvesJobModel)throws Exception;

    public long getProductShelvesJobCount(ProductShelvesJobModel productShelvesJobModel)throws Exception;

    public List<ProductShelvesJobModel> getProductShelvesJob(ProductShelvesJobModel productShelvesJobModel, Page page)throws Exception;

    public void deleteById(ProductShelvesJobModel productShelvesJobModel)throws Exception;
    
    public void updateStatusList(Map<String, Object> map) throws Exception;
    
    public void addShelvesJobList(List<ProductShelvesJobModel> shelvesJobList)throws Exception;
    
    public void deleteByIds(List<Long> jobIds) throws Exception;
}
