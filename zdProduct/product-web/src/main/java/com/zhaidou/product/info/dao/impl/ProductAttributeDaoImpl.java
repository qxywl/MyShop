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
package com.zhaidou.product.info.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductAttributeDao;
import com.zhaidou.product.info.model.ProductAttributeModel;
import com.zhaidou.product.info.model.ProductSkuModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productAttributeDao")
public class ProductAttributeDaoImpl extends BaseDao  implements ProductAttributeDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public List<ProductAttributeModel> getAttributeByProductId(Long productId) throws DaoException  {
        List<ProductAttributeModel> list = null;
        
        list = getSqlSession().selectList(getNameSpace() + ".getAttributeByProductId", productId);
        
        return list;
    }

    @Override
    public List<ProductAttributeModel> getAttributeListByProductId(List<Long> productIdList) {
        List<ProductAttributeModel> list = null;
        
        list = getSqlSession().selectList(getNameSpace() + ".getAttributeListByProductId", productIdList);
        
        return list;
    }
}
