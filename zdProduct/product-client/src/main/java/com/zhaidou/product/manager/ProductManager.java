/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.manager;

import java.util.HashMap;
import java.util.Map;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;


/**商品管理
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-14     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductManager {
    
    
    /**
     * 获取商品列表
     * @param requestObj 请求对象 RequestProductListPO
     * @return
     */
    public ResponseObject getProductList(RequestObject requestObj);
    
    
    /**
     * 获取商品详情
     * @param requestObj 请求对象 RequestProductInfoPO
     * @return
     */
    public ResponseObject getProductInfoDetail(RequestObject requestObj);
   
    
    /**
     * 通过sku获取商品详情
     * @param skuCode sku编码 RequestProductInfoPO
     * @return
     */
    public ResponseObject getProductInfoForSKU(RequestObject requestObj);

    
    /**
     * 通过productCodes获取多个商品
     * @param  requestObj
     * @return  ResponseObject
     * */
    public ResponseObject getProductInfoByCodes(RequestObject requestObj);
    
    /**
     * 通过productCodes获取商品加价率
     * @param   requestObj.requestParams中是商品编码的list，List《productCode》-List《String》，示例:{"requestParams":["150100000001","1111"]}
     * @return  ResponseObject.data中是 Map《productCode,ProductPriceLadderPO》-Map《String, ProductPriceLadderPO》 ; 
     * 如果查询不到内容，则返回一个空对象(new ProductPriceLadderPO())
     * */
    public ResponseObject getProductPriceLadderByCodes(RequestObject requestObj); 
  
    /**
     * 根据ProductCodeList 查询是否支持贵就赔
     * 是否支持贵就赔  1  支持   2   不支持
     * @param requestObj
     * @return Map[ProductCode, IsPayForExpensive] IsPayForExpensiveMap
     */
    public ResponseObject getProductIsPayForExpensiveByCodes(RequestObject requestObj);
}
