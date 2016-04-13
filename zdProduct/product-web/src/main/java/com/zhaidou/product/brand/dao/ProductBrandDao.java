/*
 * 文 件 名:  ProductBrandDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.brand.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.brand.model.ProductBrandModel;






/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductBrandDao extends IDao
{
	/**
	 * 获取所有品牌列表
	 * */
    List<ProductBrandModel> getBrandAllList();
    
    
    /**
     * 通过名称获取的品牌
     * @param brandCode 品牌编号
     * @return ProductBrandModel
     * */
    ProductBrandModel getBrandByCode(String brandCode);
    
    
    
    /**
     * 通过名称获取可用状态的品牌
     * @param brandCode 品牌编号
     * @return ProductBrandModel
     * */
    ProductBrandModel getEnableBrandByCode(String brandCode);
    
    
    /**
     * 通过名称获取品牌
     * @param brandName 品牌名称
     * @return ProductBrandModel
     * */
    ProductBrandModel getBrandByName(String brandName);
    
    
    /**
     * 通过名称获取可用状态的品牌
     * @param brandName 品牌名称
     * @return ProductBrandModel
     * */
    ProductBrandModel getEnableBrandByName(String brandName);
    
    
    /**
     * 获取品牌的最大Id
     * @return Long
     * */
    Long getMaxBrandId();
}
