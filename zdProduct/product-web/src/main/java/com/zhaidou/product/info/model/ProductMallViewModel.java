/*
 * 文 件 名:  ProductDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

import java.io.Serializable;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProductMallViewModel implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;
	private String productName;
	private String productCode;
	private String productDesc;
	private Integer productShelves;
	private Long createTime;
	private Long createBy;
	private String createUserName;
	private Long updateTime;
	private Long updateBy;
	private String updateUserName;
	private Long firstShelves;
	private String firstShelvesName;
	private Long firstShelvesTime;
	private Long lastShelves;
	private String lastShelvesName;
	private Long lastShelvesTime;
	private Long downShelves;
	private Long downShelvesTime;
	private String downShelvesName;
	private Double costPrice;
	private Double tshPrice;
	private Long tb;
	private Double marketPrice;
	private String brandCode;
	private String brandName;
	private String catCode;
	private String catName;
	private String type;
	private Long status;
	private String mainPic;
	private Long supplierId;
	private Long shopId;
	private Integer integrity;
	private String integrityDesc;
	private String productShortName;
	private String productPrefix;
	private String productSuffix;
	private String productKeyword;
	private Integer productDownShow;
	private Integer productAutoUp;
	private String productSizeCompare;
	private String productCatCode;
	private String productCatName;
	private Integer productLevel;
	private Double productPriceRate;
	private Double productWeight;
	private Double productLong;
	private Double productWidth;
	private Double productHeight;
	private Double productDensity;
	private String productProducer;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductShelves() {
		return productShelves;
	}

	public void setProductShelves(Integer productShelves) {
		this.productShelves = productShelves;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Long getFirstShelves() {
		return firstShelves;
	}

	public void setFirstShelves(Long firstShelves) {
		this.firstShelves = firstShelves;
	}

	public String getFirstShelvesName() {
		return firstShelvesName;
	}

	public void setFirstShelvesName(String firstShelvesName) {
		this.firstShelvesName = firstShelvesName;
	}

	public Long getFirstShelvesTime() {
		return firstShelvesTime;
	}

	public void setFirstShelvesTime(Long firstShelvesTime) {
		this.firstShelvesTime = firstShelvesTime;
	}

	public Long getLastShelves() {
		return lastShelves;
	}

	public void setLastShelves(Long lastShelves) {
		this.lastShelves = lastShelves;
	}

	public String getLastShelvesName() {
		return lastShelvesName;
	}

	public void setLastShelvesName(String lastShelvesName) {
		this.lastShelvesName = lastShelvesName;
	}

	public Long getLastShelvesTime() {
		return lastShelvesTime;
	}

	public void setLastShelvesTime(Long lastShelvesTime) {
		this.lastShelvesTime = lastShelvesTime;
	}

	public Long getDownShelves() {
		return downShelves;
	}

	public void setDownShelves(Long downShelves) {
		this.downShelves = downShelves;
	}

	public Long getDownShelvesTime() {
		return downShelvesTime;
	}

	public void setDownShelvesTime(Long downShelvesTime) {
		this.downShelvesTime = downShelvesTime;
	}

	public String getDownShelvesName() {
		return downShelvesName;
	}

	public void setDownShelvesName(String downShelvesName) {
		this.downShelvesName = downShelvesName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getTshPrice() {
		return tshPrice;
	}

	public void setTshPrice(Double tshPrice) {
		this.tshPrice = tshPrice;
	}

	public Long getTb() {
		return tb;
	}

	public void setTb(Long tb) {
		this.tb = tb;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getIntegrity() {
		return integrity;
	}

	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}

	public String getIntegrityDesc() {
		return integrityDesc;
	}

	public void setIntegrityDesc(String integrityDesc) {
		this.integrityDesc = integrityDesc;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	public String getProductSuffix() {
		return productSuffix;
	}

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}

	public String getProductKeyword() {
		return productKeyword;
	}

	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}

	public Integer getProductDownShow() {
		return productDownShow;
	}

	public void setProductDownShow(Integer productDownShow) {
		this.productDownShow = productDownShow;
	}

	public Integer getProductAutoUp() {
		return productAutoUp;
	}

	public void setProductAutoUp(Integer productAutoUp) {
		this.productAutoUp = productAutoUp;
	}

	public String getProductSizeCompare() {
		return productSizeCompare;
	}

	public void setProductSizeCompare(String productSizeCompare) {
		this.productSizeCompare = productSizeCompare;
	}

	public String getProductCatCode() {
		return productCatCode;
	}

	public void setProductCatCode(String productCatCode) {
		this.productCatCode = productCatCode;
	}

	public String getProductCatName() {
		return productCatName;
	}

	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}

	public Integer getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(Integer productLevel) {
		this.productLevel = productLevel;
	}

	public Double getProductPriceRate() {
		return productPriceRate;
	}

	public void setProductPriceRate(Double productPriceRate) {
		this.productPriceRate = productPriceRate;
	}

	public Double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}

	public Double getProductLong() {
		return productLong;
	}

	public void setProductLong(Double productLong) {
		this.productLong = productLong;
	}

	public Double getProductWidth() {
		return productWidth;
	}

	public void setProductWidth(Double productWidth) {
		this.productWidth = productWidth;
	}

	public Double getProductHeight() {
		return productHeight;
	}

	public void setProductHeight(Double productHeight) {
		this.productHeight = productHeight;
	}

	public Double getProductDensity() {
		return productDensity;
	}

	public void setProductDensity(Double productDensity) {
		this.productDensity = productDensity;
	}

	public String getProductProducer() {
		return productProducer;
	}

	public void setProductProducer(String productProducer) {
		this.productProducer = productProducer;
	}

	public ProductMallViewModel() {
	}

	public ProductMallViewModel(Long productId) {
		super();
		this.productId = productId;
	}

}
