/*
 * 文 件 名:  ProductInfoAuthService.java
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
import com.zhaidou.product.model.ProductInfoAuthModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductInfoAuthService
{
    public void addProductInfoAuth(ProductInfoAuthModel productInfoAuthModel)throws Exception;

    public void updateProductInfoAuth(ProductInfoAuthModel productInfoAuthModel)throws Exception;

    public ProductInfoAuthModel getProductInfoAuthById(ProductInfoAuthModel productInfoAuthModel)throws Exception;

    public long getProductInfoAuthCount(ProductInfoAuthModel productInfoAuthModel)throws Exception;

    public List<ProductInfoAuthModel> getProductInfoAuth(ProductInfoAuthModel productInfoAuthModel,Page page)throws Exception;

    public void deleteById(ProductInfoAuthModel productInfoAuthModel)throws Exception;
    
    /**
     * 根据商品编号获取 审核记录
     *
     * @param productInfoAuthModel
     * @return
     */
    public List<ProductInfoAuthModel> getInfoAuthByProductCode(String productCode)throws Exception;
    
    public void updateStatusList(Map<String,Object> map)throws Exception;

}
