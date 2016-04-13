/*
 * 文 件 名:  ProductShelvesService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.ProductShelvesModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductShelvesService
{
    public void addProductShelves(ProductShelvesModel productShelvesModel)throws Exception;

    public void updateProductShelves(ProductShelvesModel productShelvesModel)throws Exception;

    public ProductShelvesModel getProductShelvesById(ProductShelvesModel productShelvesModel)throws Exception;

    public long getProductShelvesCount(ProductShelvesModel productShelvesModel)throws Exception;

    public List<ProductShelvesModel> getProductShelves(ProductShelvesModel productShelvesModel, Page page)throws Exception;

    public void deleteById(ProductShelvesModel productShelvesModel)throws Exception;
    
    /**
     * 根据商品编码查询是否完整
     * @param productShelvesModel
     * @return
     * @throws Exception
     */
    public boolean getProductIntegrity(ProductShelvesModel productShelvesModel)throws Exception;
    
    public void updateStatusList(Map<String,Object> map)throws Exception;
}
