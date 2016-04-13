package com.zhaidou.product.model.base;

import com.zhaidou.product.model.AbstractBaseModel;


/**
 * 对商品信息的描述
 * 有app 端和pc端的，
 * 会根据业务需求查找想对应的描述信息
 * */
public class ProductInfoModel extends AbstractBaseModel {

	private static final long serialVersionUID = -4702410194605308498L;

	private Long productInfoId; //描述详情id
	private Long productId; //商品id
	private String pcProductInfo; //pc端信息描述
	private String appProductInfo; // app端信息描述
	
	public Long getProductInfoId() {
		return productInfoId;
	}
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getPcProductInfo() {
		return pcProductInfo;
	}
	public void setPcProductInfo(String pcProductInfo) {
		this.pcProductInfo = pcProductInfo;
	}
	public String getAppProductInfo() {
		return appProductInfo;
	}
	public void setAppProductInfo(String appProductInfo) {
		this.appProductInfo = appProductInfo;
	}

}
