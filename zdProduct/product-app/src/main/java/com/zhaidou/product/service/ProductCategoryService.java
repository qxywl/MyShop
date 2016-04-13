/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service;

import com.zhaidou.framework.model.ResponseObject;

/**商品类别服务
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-12     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductCategoryService {
    /**
     * 商城商品类别服务
     * @param parentCategoryCode 父类别code
     * @return
     */
    public ResponseObject getMallProductCategoryList(String parentCategoryCode);
    /**
     * 鲜花商品类别服务
     * @param parentCategoryCode 父类别code
     * @return
     */
    public ResponseObject getFlowerProductCategoryList(String parentCategoryCode);
}
