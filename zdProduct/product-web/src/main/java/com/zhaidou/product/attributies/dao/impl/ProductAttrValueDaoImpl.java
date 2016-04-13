/*
 * 文 件 名:  ProductAttrValueDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.attributies.dao.ProductAttrValueDao;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productAttrValueDao")
public class ProductAttrValueDaoImpl extends BaseDao  implements ProductAttrValueDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }


	@Override
	public void deleteByAttrValueIds(List<Long> listids) throws Exception
	{
		Map<String,List<Long>> map = new HashMap<String,List<Long>>();
		
		map.put("listids", listids);
		
		getSqlSession().delete(getNameSpace() + ".deleteByAttrValueIds", map);
		
	}


	@Override
	public List<ProductAttrValueModel> queryAttrValueByValueIds(List<Long> valueIds) throws Exception 
	{
		Map<String,List<Long>> map = new HashMap<String,List<Long>>();
		
		map.put("valueIds", valueIds);
		
		return getSqlSession().selectList(getNameSpace() + ".queryAttrValueByValueIds", map);
		
	}
	
	@Override
	public Long getMaxId() throws Exception {
		
		return getSqlSession().selectOne(getNameSpace() + ".getMaxId");
	}
}
