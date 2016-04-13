/*
 * 文 件 名:  ProductOtherJobDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-20
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao.impl;
import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductOtherJobDao;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productOtherJobDao")
public class ProductOtherJobDaoImpl extends BaseDao  implements ProductOtherJobDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
}
