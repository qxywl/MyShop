/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po.mall;

import java.io.Serializable;

/**
 * 商城商品库存查询返回对象
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */

public class MallProductStockPO implements Serializable {
	private static final long serialVersionUID = 3508368276728291660L;
	/**
     * sku编码
     */
    String skuCode;
    /**
     * 商品编码
     */
    String productCode;
    /**
     * 库存数
     */
    Integer stock;
    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
