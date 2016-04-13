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
package com.zhaidou.product.attributies.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
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
public interface ProductAttrValueDao extends IDao
{
    
	/**
	 * 根据属性值ID集合  查询属性对象值集合
	 * @param attrCode
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrValueModel> queryAttrValueByValueIds(List<Long> valueIds) throws Exception;
	
	/**
	 * 通过attrValueId 批量删除
	 * 
	 * @param listids
	 * @throws Exception
	 */
	public void deleteByAttrValueIds(List<Long> valueIds)throws Exception;
	
	/**
	 * 获取最大Id
	 * @return
	 * @throws Exception
	 */
	public Long getMaxId() throws Exception;
}
