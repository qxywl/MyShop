/*
 * 文 件 名:  ProductPriceLogDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductPriceLogDao;
import com.zhaidou.product.model.ProductPriceLogModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productPriceLogDao")
public class ProductPriceLogDaoImpl extends BaseDao  implements ProductPriceLogDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

    @Override
    public void addPriceLogList(List<ProductPriceLogModel> priceLogList) throws Exception {
        getSqlSession().insert(this.getNameSpace()+".addPriceLogList",priceLogList);
    }
}
