/*
 * 文 件 名:  ProductMallDAO.java
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

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.ProductInfoModel;
import com.zhaidou.product.info.model.ProductMallModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductMallDao extends IDao
{
    public List<ProductMallModel> getProductMallListByProductId(List<Long> productIdList )throws DaoException;
    
    public void updateByProductId(ProductMallModel productMallModel) throws DaoException;
}
