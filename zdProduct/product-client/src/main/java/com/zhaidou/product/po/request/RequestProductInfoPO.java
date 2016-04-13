/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po.request;

import java.io.Serializable;
import java.util.List;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.util.reflect.EmptyShowAnnotation;


/**商品详情请求参数对象
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-19     Created
 *
 * </pre>
 * @since 1.
 */

public class RequestProductInfoPO implements Serializable{
    private static final long serialVersionUID = -6398838409678438642L;
    /**
     * 商品编号
     */
    @EmptyShowAnnotation
    private String productCode;
    private Long productId; //商品Id

    private List<String> productCodes;
    /**
     * sku编号列表
     */
    private  List<String> productSKUCodes;
	
	private List<Long> productSKUIds; //skuId编号列表
    
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public List<String> getProductSKUCodes() {
        return productSKUCodes;
    }
    public void setProductSKUCodes(List<String> productSKUCodes) {
        this.productSKUCodes = productSKUCodes;
    }
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public List<Long> getProductSKUIds() {
		return productSKUIds;
	}
	public void setProductSKUIds(List<Long> productSKUIds) {
		this.productSKUIds = productSKUIds;
	}
	public List<String> getProductCodes() {
		return productCodes;
	}
	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}
    
	
    
}
