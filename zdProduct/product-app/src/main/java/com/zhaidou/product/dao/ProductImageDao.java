/*
 * 文 件 名:  ProductImageDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao;


import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.ProductImageModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductImageDao extends IDao
{
    /**
     * 根据SKU ID获取该 SKU下所有图片
     *
     * @param productImageModel
     * @return
     */
    public List<ProductImageModel> getImageBySkuId(ProductImageModel productImageModel) throws Exception ;
    
    
    /**
     * 根据SKU 集合获取该 SKU下所有图片
     * @param List<Long> skuList
     * @return List<ProductImageModel>
     * @throws Exception
     */
    public List<ProductImageModel> getImageBySkuIdList(List<Long> skuList) throws Exception ;
}
