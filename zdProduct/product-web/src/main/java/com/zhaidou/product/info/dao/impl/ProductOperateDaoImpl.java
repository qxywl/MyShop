/*
 * 文 件 名:  ProductOperateDAO.java
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
import com.zhaidou.product.info.dao.ProductOperateDao;
import com.zhaidou.product.info.model.ProductMallModel;
import com.zhaidou.product.info.model.ProductOperateModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productOperateDao")
public class ProductOperateDaoImpl extends BaseDao  implements ProductOperateDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

    @Override
    public List<ProductOperateModel> getProductOperateListByProductId(List<Long> productIdList) throws DaoException {
        List<ProductOperateModel> list = null;
        
        list = getSqlSession().selectList(getNameSpace() + ".getProductOperateListByProductId", productIdList);
        
        return list;
    }

    @Override
    public void updateByProductId(ProductOperateModel productOperateModel) throws DaoException {
        
        getSqlSession().update(getNameSpace() + ".updateByProductId", productOperateModel);
    }
}
