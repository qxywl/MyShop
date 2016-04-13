package com.zhaidou.product.po.request;

import java.io.Serializable;
import java.util.List;

public class RequestProductMallSkuPO implements Serializable {

	private static final long serialVersionUID = 130012265267694273L;
	
	private Long productId;//商品id
	private List<String> skuList;//sku集合
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public List<String> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<String> skuList) {
		this.skuList = skuList;
	}
	
	
	

}
