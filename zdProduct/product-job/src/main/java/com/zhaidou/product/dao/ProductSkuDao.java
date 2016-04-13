/*
 * 文 件 名:  ProductSkuDAO.java
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
import java.util.Map;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ProductPriceModel;
import com.zhaidou.product.model.ProductSkuModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductSkuDao extends IDao
{   
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    public List<ProductSkuModel> getSkuByProductId(ProductSkuModel productSkuModel);
    /**
     * 批量删除
     *
     * @param listStr
     * @throws Exception
     */
    public void deleteByIds(Map<String,Object> map);
    
    /**
     * 批量逻辑删除 修改 is_available 状态
     *
     * @param listStr
     * @throws Exception
     */
    public Integer updateByIds(Map<String,Object> map) throws DaoException;
    
    /**
     * 根据商品编号 获取 所有 SKU 以及价格
     *
     * @param listStr
     * @throws Exception
     */
    public ProductPriceModel getSkuPriceByProductCode(String productCode) throws DaoException;
    
    /**
     * 获取该商品所有已删除的SKU
     *
     * @param isAvailable
     * @return
     * @throws Exception
     */
    public List<ProductSkuModel> getDelSku(Long productId)throws Exception;
}
