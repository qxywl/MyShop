/*
 * 文 件 名:  ProductShelvesJobDAO.java
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
import com.zhaidou.product.model.ProductShelvesJobModel;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductShelvesJobDao extends IDao
{
    public void updateStatusList(Map<String,Object> map)throws Exception;
    
    public void addShelvesJobList(List<ProductShelvesJobModel> shelvesJobList)throws Exception;
    
    public void deleteByIds(List<Long> jobIds)throws Exception;
}
