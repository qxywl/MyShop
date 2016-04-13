/*
 * 文 件 名:  ProductOtherJobService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-20
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.ProductOtherJobModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductOtherJobService
{
    public void addProductOtherJob(ProductOtherJobModel productOtherJobModel)throws Exception;

    public void updateProductOtherJob(ProductOtherJobModel productOtherJobModel)throws Exception;

    public ProductOtherJobModel getProductOtherJobById(ProductOtherJobModel productOtherJobModel)throws Exception;

    public long getProductOtherJobCount(ProductOtherJobModel productOtherJobModel)throws Exception;

    public List<ProductOtherJobModel> getProductOtherJob(ProductOtherJobModel productOtherJobModel, Page page)throws Exception;

    public void deleteById(ProductOtherJobModel productOtherJobModel)throws Exception;

}
