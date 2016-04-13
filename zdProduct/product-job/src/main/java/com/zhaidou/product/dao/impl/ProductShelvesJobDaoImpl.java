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
package com.zhaidou.product.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductShelvesJobDao;
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
@Repository("productShelvesJobDao")
public class ProductShelvesJobDaoImpl extends BaseDao  implements ProductShelvesJobDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }
    
    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateStatusList",map);
    }

    @Override
    public void addShelvesJobList(List<ProductShelvesJobModel> shelvesJobList) throws Exception {
        getSqlSession().insert(this.getNameSpace()+".addShelvesJobList",shelvesJobList);
    }

    @Override
    public void deleteByIds(List<Long> jobIds) throws Exception {
        getSqlSession().delete(this.getNameSpace()+".deleteByIds",jobIds);
    }
}
