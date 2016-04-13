/*
 * 文 件 名:  ProductBrandService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.CountryModel;
import com.zhaidou.product.model.base.ProductInfoModel;
import com.zhaidou.product.model.base.SupplierShopModel;





/**
 * 处理的是productInfo
 * 商品的信息描述，分app端和pc端
 * */
public interface ProductInfoService
{
	/**
     * 根据productId 获取ProductInfoModel
     * @param productId 商品Id
     * @return
     */
    public ProductInfoModel getProInfoByProductId(Long productId) throws Exception ;

}
