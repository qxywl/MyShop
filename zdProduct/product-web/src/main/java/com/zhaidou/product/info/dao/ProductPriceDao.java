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
package com.zhaidou.product.info.dao;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.ProductPriceModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductPriceDao extends IDao
{
    /**
     * 批量删除
     *
     * @param listStr
     * @throws Exception
     */
    public void deleteByIds(Map<String,Object> map);
    
    public List<ProductPriceModel> getProductPriceListByProductId(List<Long> productIdList )throws DaoException;
}
