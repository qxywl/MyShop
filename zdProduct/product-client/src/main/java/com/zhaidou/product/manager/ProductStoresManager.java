/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.manager;

import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.product.po.request.RequestProductBaseObject;

/**商城店铺查询
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */

public interface ProductStoresManager {
    /**
     * 获取店铺
     * @param requestObj RequestProductListPO
     * @return
     */
    public ResponseObject getProductStoresList(RequestProductBaseObject requestObj);
}
