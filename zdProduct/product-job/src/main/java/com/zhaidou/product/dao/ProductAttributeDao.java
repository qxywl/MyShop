/*
 * 文 件 名:  ProductAttributeDAO.java
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
import com.zhaidou.product.model.ProductAttributeModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttributeDao extends IDao
{
    /**
     * 根据商品ID 获取该商品下所有属性项
     *
     * @param productSkuModel
     * @return
     */
    public List<ProductAttributeModel> getAttributeByProductId(Long productId);
    
    /**
     * 根据属性项编号 删除 该属性项
     *
     * @param productSkuModel
     * @return
     */
    public void deleteByAttrCode(String attrCode)throws Exception;
}
