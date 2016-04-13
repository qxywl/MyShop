/*
 * 文 件 名:  ProductPriceDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductPriceDao;
import com.zhaidou.product.model.ProductPriceModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productPriceDao")
public class ProductPriceDaoImpl extends BaseDao  implements ProductPriceDao
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
	public void createSkuView(ProductPriceModel productPriceModel) {
		try {
            getSqlSession().update(getNameSpace() + ".createSkuView", productPriceModel);
        } catch (Exception e) {
            throw new DaoException(e);
        }
		
	}
}
