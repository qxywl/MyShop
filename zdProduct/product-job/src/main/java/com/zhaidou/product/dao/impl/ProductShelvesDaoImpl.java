/*
 * 文 件 名:  ProductShelvesDAO.java
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

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductShelvesDao;
import com.zhaidou.product.model.ProductShelvesModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productShelvesDao")
public class ProductShelvesDaoImpl extends BaseDao  implements ProductShelvesDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

	@Override
	public boolean getProductIntegrity(ProductShelvesModel productShelvesModel)
			throws Exception {
		try {
			
			Long count = (Long) getSqlSession().selectOne(getNameSpace() + ".getProductIntegrity", productShelvesModel);
			if(count>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	 @Override
	    public void updateStatusList(Map<String, Object> map) throws Exception {
	        getSqlSession().update(this.getNameSpace()+".updateStatusList",map);
	    }
}
