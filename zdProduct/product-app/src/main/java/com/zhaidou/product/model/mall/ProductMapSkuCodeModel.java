package com.zhaidou.product.model.mall;

import java.io.Serializable;
import java.util.List;


/**
 * 商品对应所拥有的skuCode集合
 * */
public class ProductMapSkuCodeModel implements Serializable{

	private Long productId;//产品ID
	
    private String productCode;//商品编码
    
    private List<String>     skuCodeList; //sku code

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getSkuCodeList() {
		return skuCodeList;
	}

	public void setSkuCodeList(List<String> skuCodeList) {
		this.skuCodeList = skuCodeList;
	}
    
    

}
