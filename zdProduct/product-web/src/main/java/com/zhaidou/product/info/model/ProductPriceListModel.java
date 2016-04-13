/*
 * 文 件 名:  ProductPriceListDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

import com.zhaidou.common.model.AbstractBaseModel;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductPriceListModel extends AbstractBaseModel implements Serializable
{

	 /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    private Long        productPriceList; 
    private String        productSkuCode; 
    private Double        costPrice;
    private Double        tshPrice;  
    private Long        tb;  
    private Double        marketPrice;
    private String        reason;  
    private Integer        status;  
    private String        createUserName; 
    private String        updateUserName;
    private Double        oldCostPrice;  
    private Double        oldTshPrice;
    private Long        oldTb;  
    private Double        oldMarketPrice;  
    private String categoryCode;
	private String brandCode;
	private Long supplierId;
	private Double priceRate;
	private String productName;
	private String catCode;
	private String catName;
	private String catName1;  //一级目录名字
    private String catCode1;   // 一级目录编码
    private String catName2;  //二级目录名字
    private String catCode2;
	private String brandName;
	private String shopName;
	private String statusName;
	private Double floatPrice;
	private Long timeStart;
    private Integer sourceType;
	public Long getProductPriceList() {
		return productPriceList;
	}
	public void setProductPriceList(Long productPriceList) {
		this.productPriceList = productPriceList;
	}
	public String getProductSkuCode() {
		return productSkuCode;
	}
	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Double getOldCostPrice() {
		return oldCostPrice;
	}
	public void setOldCostPrice(Double oldCostPrice) {
		this.oldCostPrice = oldCostPrice;
	}
	public Double getOldTshPrice() {
		return oldTshPrice;
	}
	public void setOldTshPrice(Double oldTshPrice) {
		this.oldTshPrice = oldTshPrice;
	}
	public Long getOldTb() {
		return oldTb;
	}
	public void setOldTb(Long oldTb) {
		this.oldTb = oldTb;
	}
	public Double getOldMarketPrice() {
		return oldMarketPrice;
	}
	public void setOldMarketPrice(Double oldMarketPrice) {
		this.oldMarketPrice = oldMarketPrice;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Double getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(Double priceRate) {
		this.priceRate = priceRate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getCatName1() {
		return catName1;
	}
	public void setCatName1(String catName1) {
		this.catName1 = catName1;
	}
	public String getCatCode1() {
		return catCode1;
	}
	public void setCatCode1(String catCode1) {
		this.catCode1 = catCode1;
	}
	public String getCatName2() {
		return catName2;
	}
	public void setCatName2(String catName2) {
		this.catName2 = catName2;
	}
	public String getCatCode2() {
		return catCode2;
	}
	public void setCatCode2(String catCode2) {
		this.catCode2 = catCode2;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Double getFloatPrice() {
		return floatPrice;
	}
	public void setFloatPrice(Double floatPrice) {
		this.floatPrice = floatPrice;
	}
	public Long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Long timeStart) {
		this.timeStart = timeStart;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
