/*
 * 文 件 名:  ProductInfoAuthDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

import java.io.Serializable;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class ProductInfoAuthModel extends AbstractBaseModel implements Serializable
{

    /**
     * 注释内容
     */

    private Long   productInfoAuthId;
    private Long   productSkuId;
    private String productSkuCode; 
    private Long   type;  
    private Long   staus;
    private String productCode;
    private String productName;
    private String catCode;
    private String catName;
    private String brandName;
    private String shopName;
    private String typeName;  
    private String catName1;  //一级目录名字
    private String catCode1;   // 一级目录编码
    private String catName2;  //二级目录名字
    private String catCode2;
    private String statusName;
    private String reason;
    private String newValue;
    private String oldValue;
    private Integer sourceType;
    
	public Long getProductInfoAuthId() {
		return productInfoAuthId;
	}
	public void setProductInfoAuthId(Long productInfoAuthId) {
		this.productInfoAuthId = productInfoAuthId;
	}
	public Long getProductSkuId() {
		return productSkuId;
	}
	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}
	public String getProductSkuCode() {
		return productSkuCode;
	}
	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getStaus() {
		return staus;
	}
	public void setStaus(Long staus) {
		this.staus = staus;
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
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
 
}
