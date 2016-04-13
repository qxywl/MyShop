/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.manager;


import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;

/**品牌管理
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-6     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductBrandManager {
    
    /**
     * 通过品牌编码获取启用状态的商品品牌
     * 
     * @param requestObj 请求对象
     * @return ResponseObject
     * */
    public ResponseObject getEnableBrandByCode(RequestObject requestObj) ;
    
    
    /**
     * 通过名称获启用状态的商品品牌
     * 
     * @param requestObj 请求对象
     * @return ResponseObject
     * */
    public  ResponseObject getEnableBrandByName(RequestObject requestObj) ;
   
    
    /**
     * 查询启用状态的商品品牌列表分页
     * @param requestObj 请求对象
     * @return ResponseObject
     * */
    public  ResponseObject getEnableProductBrandListPage(RequestObject requestObj);
    
    
    /**
     * 查询启用状态的所有商品品牌列表
     * @param requestObj 请求对象
     * @return ResponseObject
     * */
    public  ResponseObject getAllEnableProductBrandList(RequestObject requestObj);
}
