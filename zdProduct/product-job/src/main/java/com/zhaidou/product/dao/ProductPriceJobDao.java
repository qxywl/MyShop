/*
 * 文 件 名:  ProductPriceJobDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.dao;


import java.util.List;
import java.util.Map;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ProductPriceJobModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductPriceJobDao extends IDao
{
    /**
     * 批量插入价格调度
     *
     * @param priceJobList
     * @throws Exception
     */
    public void addPriceJobList(List<ProductPriceJobModel> priceJobList)throws Exception;
    
    /**
     * 批量修改状态
     *
     * @param map
     * @throws Exception
     */
    public void updateStatusList(Map<String,Object> map)throws Exception;
    
    /**
     * 批量删除
     *
     * @param ids
     * @throws Exception
     */
    public void deleteByIds(List<Long> ids)throws Exception;
}
