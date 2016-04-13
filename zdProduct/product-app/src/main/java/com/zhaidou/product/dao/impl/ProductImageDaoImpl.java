/*
 * 文 件 名:  ProductImageDAO.java
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
import com.zhaidou.product.dao.ProductImageDao;
import com.zhaidou.product.model.base.ProductImageModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @since  [产品/模块版本]
 */
@Repository("productImageDao")
public class ProductImageDaoImpl extends BaseDao  implements ProductImageDao
{
    @Override
    public String getNameSpace() {
            return this.getClass().getName();
    }
    
    
    /**
     * 根据SKU ID获取该 SKU下所有图片
     *
     * @param productImageModel
     * @return
     */
    @Override
    public List<ProductImageModel> getImageBySkuId(ProductImageModel productImageModel)  throws Exception  {
        	
        List<ProductImageModel> list = null;
        list = getSqlSession().selectList(getNameSpace() + ".getImageBySkuId", productImageModel);
        return list;
    }


	@Override
	public List<ProductImageModel> getImageBySkuIdList(List<Long> skuList)
			throws Exception {
		    List<ProductImageModel> list = null;
		    ProductImageModel queryModel = new ProductImageModel();
		    if(skuList != null && skuList.size()>0){
		    	queryModel.setSkuIdList(skuList);
		    }
		    
	        list = getSqlSession().selectList(getNameSpace() + ".getImageBySkuIdList", queryModel);
	        return list;
	}
}
