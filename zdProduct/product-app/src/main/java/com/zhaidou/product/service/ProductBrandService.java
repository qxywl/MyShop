/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.base.ProductBrandModel;
import com.zhaidou.product.po.base.ProductBrandPO;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-7     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductBrandService {
   
	/**
     * 获取鲜花品牌列表
     * @param categoryCode 所属类别编号
     * @param clientType 客户端类型
     * @return List<ProductBrand> 鲜花品牌列表
     */
    public List<ProductBrandPO> getFlowerProductBrandList(String categoryCode,String clientType);
  
    
    /**
     * 获取鲜花品牌
     * @param brandCode 鲜花品牌编码
     * @param clientType 客户端类型
     * @return
     */
    public ProductBrandPO getFlowerProductBrand(String brandCode,String clientType);
   
    
    /**
     * 通过品牌编码获取可用状态的商品品牌
     * 
     * @param brandCode 品牌编号
     * @return ProductBrandModel
     * @throws Exception
     * */
    public ProductBrandModel getMallEnableBrandByCode(String brandCode) throws Exception ;
    
    
    /**
     * 通过名称获取可用状态的商品品牌
     * 
     * @param brandName 品牌名称
     * @return ProductBrandModel
     * @throws Exception
     * */
    public  ProductBrandModel getMallEnableBrandByName(String brandName) throws Exception;
   
    
    /**
     * 查询启用的商品品牌列表分页
     * @param productBrandModel
   	 * @param Page
   	 * @throws Exception 
     * */
    public List<ProductBrandModel> getMallEnableProductBrandListPage(ProductBrandModel productBrandModel,Page page)throws Exception;
   
    
    /**
     * 查询启用状态的所有商品品牌列表
     * @param productBrandModel
   	 * @param Page
   	 * @throws Exception 
     * */
    public List<ProductBrandModel> getAllMallEnableProductBrandList()throws Exception;
   
}
