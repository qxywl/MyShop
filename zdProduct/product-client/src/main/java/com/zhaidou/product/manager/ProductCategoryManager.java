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

/**产品类别管理
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-12     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductCategoryManager {
    /**
     * 获取商品类别列表
     * @param requestObj RequestProductBaseObject
     * @return
     */
//    public ResponseObject getProductCategoryList(RequestProductBaseObject requestObj);
	public ResponseObject getProductCategoryList(RequestObject requestObj);
}
