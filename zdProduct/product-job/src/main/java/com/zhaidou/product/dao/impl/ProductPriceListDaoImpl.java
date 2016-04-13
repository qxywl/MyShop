/*
 * 文 件 名:  ProductPriceListDAO.java
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
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.framework.dao.mybatis.impl.BaseDao;
import com.zhaidou.product.dao.ProductPriceListDao;
import com.zhaidou.product.model.ProductPriceListModel;
import com.zhaidou.product.model.ProductPriceModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("productPriceListDao")
public class ProductPriceListDaoImpl extends BaseDao  implements ProductPriceListDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getSimpleName();
    }

	@Override
	public ProductPriceModel getProductPriceById(
			ProductPriceModel productPriceModel) throws Exception {
		try {
			ProductPriceModel t = (ProductPriceModel) getSqlSession().selectOne(getNameSpace() + ".queryPriceOne", productPriceModel);
			return t;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
     * 根据SKU编号 查询价格审核记录
     *
     * @param productPriceListModel
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductPriceListModel> getPriceListBySkuCode(ProductPriceListModel productPriceListModel)
            throws Exception {
        return getSqlSession().selectList(getNameSpace() + ".getPriceListBySkuCode", productPriceListModel);
    }
    
    /**
     * 批量修改状态
     *
     * @param listIds
     * @throws Exception
     */
    @Override
    public void updateStatusList(Map<String,Object> statusMap) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateStatusList",statusMap);
    }

    @Override
    public void updateStatusPriceList(List<Long> ids) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateStatusPriceList",ids);
    }

    @Override
    public void updateOldStatusList(List<String> skuCodes) throws Exception {
        getSqlSession().update(this.getNameSpace()+".updateOldStatusList",skuCodes);
    }
}
