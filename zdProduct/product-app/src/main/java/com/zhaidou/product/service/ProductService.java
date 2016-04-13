/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service;

import java.util.List;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.po.request.RequestProductInfoPO;
import com.zhaidou.product.po.request.RequestProductListPO;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-19     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductService {
    
    
	/**
     * 通过skuCode type 获取商品信息
     * @param skuCode 
     * @param type 类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getProductMallViewBySkuCode(String skuCode,String type) throws Exception;
    
    
    /**
     * 通过productId, type  获取商城信息
     * @param productId  productId 商品id
     * @param type 类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return List<ProductMallViewModel>
     * @throws Exception
     * */
    public ProductMallViewModel getProductMallViewByProductId(Long productId,String type) throws Exception;
    
    
    /**
     * 通过productId List 获取鲜花信息
     * @param List<Long> productIdList  productId集合
     * @param type 类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return List<ProductMallViewModel>
     * @throws Exception
     * */
    public List<ProductMallViewModel> getProductMallViewByProductIdList(List<Long> productIdList,String type) throws Exception;
    
    
    /**
     * 通过productCode List 获取鲜花信息
     * @param List<Long> productIdList  productId集合
     * @param type 类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购
     * @return List<ProductMallViewModel>
     * @throws Exception
     * */
    public List<ProductMallViewModel> getProductMallViewByProductCodeList(List<String> productCodeList,String type) throws Exception;
    
    
    /**
     * 获取商城信息列表
     * @param mallView  横表对象模型
     * @param page 分页信息
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public List<ProductMallViewModel> getMallProductMallViewList(ProductMallViewModel mallView,Page page) throws Exception;
    
    
    /**
     * 通过skuCode 获取商城信息
     * @param skuCode
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getMallProductMallViewBySkuCode(String skuCode) throws Exception;
    
    
    /**
     * 通过skuCode 集合 获取商城信息
     * @param skuCodeList sku集合
     * @return List<ProductMallViewModel>
     * @throws Exception
     * */
    public List<ProductMallViewModel> getMallProductMallViewBySkuCodeList(List<String> skuCodeList) throws Exception;
    
    
    /**
     * 通过productCode 获取商城信息
     * @param skuCode
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getMallProductMallViewByProductCode(String productCode) throws Exception;

    
    /**
     * 通过productId 获取商城信息
     * @param productId 商城Id
     * @return ProductMallViewModel
     * @throws Exception
     * */
    public ProductMallViewModel getMallProductMallViewByProductId(Long productId) throws Exception;
    
    
    /**
     * 通过productCode 获取商城信息
     * @RequestObject requestObj 必须包含productCode 参数
     * @ResponseObject 返回值
     * */
    public ResponseObject getMallProductInfoDetail(RequestObject requestObj);
    
    
    /**
     * 获取商品详情
     * @param requestObj
     * @return
     */
	ResponseObject getMallProductInfoBySKUCode(RequestObject requestObj);
	
	
	/**
     * 通过productCodes获取商品列表详情
     * @param requestObj
     * @returnResponseObject
     */
	public ResponseObject getMallProductInfoByCodes(RequestObject requestObj);
	
	
	/**
     * 获取商品列表
     * @param requestObj   ResultSetModel<ProductInfoPO<MallProductListInfoPO>>
     * @return
     */
    public ResponseObject getMallProductInfoList(RequestProductListPO requestObj);
    
    
    /**
     * 通过productCode 获取商品的上下架信息
     * @param productCode  商品code
     * @return Long 
     * @throws Exception
     * */
    public Long getProductShelvesFiledByProductCode(String productCode) throws Exception;
}
