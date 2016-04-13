package com.zhaidou.product.po;

import java.io.Serializable;
import java.util.Date;

public class ProductPriceLadderPO implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long productId;

	private String productCode;

	private String productName;

	private Long supplierId;

	private Long priceLevel1;

	private Long priceLevel2;

	private Long priceLevel3;

	private Long priceLevel4;

	private Long priceLevel5;

	private String createUserName;

	private String updateUserName;

	private Long isDeleted;

	private Date createTime;

	private Date updateTime;

	private Long createBy;
	
	private Long updateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getPriceLevel1() {
		return priceLevel1;
	}

	public void setPriceLevel1(Long priceLevel1) {
		this.priceLevel1 = priceLevel1;
	}

	public Long getPriceLevel2() {
		return priceLevel2;
	}

	public void setPriceLevel2(Long priceLevel2) {
		this.priceLevel2 = priceLevel2;
	}

	public Long getPriceLevel3() {
		return priceLevel3;
	}

	public void setPriceLevel3(Long priceLevel3) {
		this.priceLevel3 = priceLevel3;
	}

	public Long getPriceLevel4() {
		return priceLevel4;
	}

	public void setPriceLevel4(Long priceLevel4) {
		this.priceLevel4 = priceLevel4;
	}

	public Long getPriceLevel5() {
		return priceLevel5;
	}

	public void setPriceLevel5(Long priceLevel5) {
		this.priceLevel5 = priceLevel5;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

}
