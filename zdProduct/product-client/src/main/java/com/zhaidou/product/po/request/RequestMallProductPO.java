package com.zhaidou.product.po.request;

import java.io.Serializable;
import java.util.List;

public class RequestMallProductPO implements Serializable{
	
	private static final long serialVersionUID = 5099568158883008385L;
	
	private Long productId; //商品Id
	private String productCode; //商品编号
	
	private List<Long> productSKUIds; //skuId编号列表
	private List<String> productSKUCodes; //sku编号列表
	
	private  Integer page = 1; //查询页码
	private Integer pageSize = 15;//每页数量
	
	
	public RequestMallProductPO() {
		
	}
	
	
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
	
	public List<Long> getProductSKUIds() {
		return productSKUIds;
	}

	public void setProductSKUIds(List<Long> productSKUIds) {
		this.productSKUIds = productSKUIds;
	}

	public List<String> getProductSKUCodes() {
		return productSKUCodes;
	}
	public void setProductSKUCodes(List<String> productSKUCodes) {
		this.productSKUCodes = productSKUCodes;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "RequestMallProductPO [productCode=" + productCode
				+ ", productSKUCodes=" + productSKUCodes + ", page=" + page
				+ ", pageSize=" + pageSize + "]";
	}


}
