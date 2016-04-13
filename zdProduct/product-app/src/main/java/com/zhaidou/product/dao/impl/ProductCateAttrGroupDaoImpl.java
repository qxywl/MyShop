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
package com.zhaidou.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductCateAttrGroupDao;
import com.zhaidou.product.model.base.ProductCateAttrGroupModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productCateAttrGroupDao")
public class ProductCateAttrGroupDaoImpl extends BaseDao  implements ProductCateAttrGroupDao
{
    @Override
    public String getNameSpace() {
    	return this.getClass().getName();
    }

	@Override
	public List<ProductCateAttrGroupModel> queryGroupAndAttrValueByCate(String categoryCode) throws Exception{
		
		List<ProductCateAttrGroupModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryGroupAndAttrValueByCate", categoryCode);
		return list;
	}

	@Override
	public List<ProductCateAttrGroupModel> queryProductAttrValueByCate(
			String categoryCode) throws Exception {
		List<ProductCateAttrGroupModel> list = null;
		list = getSqlSession().selectList(getNameSpace() + ".queryProductAttrValueByCate", categoryCode);
		return list;
	}
}
