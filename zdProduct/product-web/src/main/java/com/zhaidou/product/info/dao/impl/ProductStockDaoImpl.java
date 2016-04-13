/*
 * 文 件 名:  ProductStockDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductStockDao;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.model.ProductStockModel;
/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productStockDao")
public class ProductStockDaoImpl extends BaseDao  implements ProductStockDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

    /**
     * 批量删除
     *
     * @param listStr
     * @throws Exception
     */
    @Override
    public void deleteByIds(Map<String,Object> map) {
       
        try {
           
            getSqlSession().selectList(getNameSpace() + ".deleteByIds", map);
            
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ProductStockModel> getProductStockListByProductId(List<Long> productIdList) throws DaoException {
        List<ProductStockModel> list = null;
        
        list = getSqlSession().selectList(getNameSpace() + ".getProductStockListByProductId", productIdList);
        
        return list;
    }
}
