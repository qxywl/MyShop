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
package com.zhaidou.product.dao.impl;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductImageDao;
import com.zhaidou.product.model.ProductImageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productImageDao")
public class ProductImageDaoImpl extends BaseDao  implements ProductImageDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
    
    /**
     * 根据SKU ID获取该 SKU下所有图片
     *
     * @param productImageModel
     * @return
     */
    @Override
    public List<ProductImageModel> getImageBySkuId(ProductImageModel productImageModel) throws DaoException  {
        try {
            List<ProductImageModel> list = null;
           
            list = getSqlSession().selectList(getNameSpace() + ".getImageBySkuId", productImageModel);
            
            return list;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
