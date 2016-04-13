/*
 * 文 件 名:  ProductInfoJobDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao.impl;


import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.info.dao.ProductInfoJobDao;
 

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productInfoJobDao")
public class ProductInfoJobDaoImpl extends BaseDao  implements ProductInfoJobDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
}
