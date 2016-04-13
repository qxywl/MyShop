/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhaidou.product.po.base.JointImagePO;
import com.zhaidou.product.po.base.ProductImagePO;

/**商品sku信息
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-16     Created
 *
 * </pre>
 * @since 1.
 */

public class ProductSKUPO implements Serializable{
    private static final long serialVersionUID = -2781531297910528844L;
    /**
     * 商品编码
     */
    String productCode;
    /**
     * SkuId
     */
    Integer skuId;
    /**
     * Sku标识
     */
    String skuCode;
    /**
     * sku属性值1 Code
     */
    String skuAttributeValueCode1;
    /**
     * sku属性值1 
     */
    String skuAttributeValue1;
    /**
     * 是否主sku  1是  0否
     * */
    Long        mainSku; 
    
    /**
     * sku属性值2 Code
     */
    String skuAttributeValueCode2;
    /**
     * sku属性值2 
     */
    String skuAttributeValue2;
    /**
     * 币种
     */
    String currency;
    /**
     * 市场价
     */
    String markerPrice;
    /**
     * 定价
     */
    String fixPrice;
    /**
     * 售价
     */
    String salesPrice;
    /**
     * 特币
     */
    double points;
    /**
     * 售价类型
     */
    Integer priceType;
    /**
     * 折扣率
     */
    String discount;
    /**
     * 活动价
     */
    String activePrice;
    /**
     * 活动开始时间
     */
    Date activeStartTime;
    /**
     * 活动结束时间
     */
    Date activeEndTime;
    /**
     * sku可售数量
     */
    Integer quantity;
    /**
     * Sku属性列表
     */
    List<AttributePO> attributeList=new ArrayList<AttributePO>();
    /**
     * 特殊属性对象
     */
    Object specialAttrObj;
    
    /**
     * 图片信息
     * */
    List<ProductImagePO> imageOPList ; 
    
    /**
     * 对接图片
     * */
    List<JointImagePO> jointImagePOList;
    
    /**
     * sku可售数量
     */
    Integer stock;
    
    
    public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void addAttributePo(Long id,String attributeName,String attributeValue,Integer attributeLevel){
        attributeList.add(new AttributePO(id,attributeName,attributeValue,attributeLevel));
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getMarkerPrice() {
        return markerPrice;
    }
    public void setMarkerPrice(String markerPrice) {
        this.markerPrice = markerPrice;
    }
    public String getFixPrice() {
        return fixPrice;
    }
    public void setFixPrice(String fixPrice) {
        this.fixPrice = fixPrice;
    }
    public String getSalesPrice() {
        return salesPrice;
    }
    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }
    public Integer getPriceType() {
        return priceType;
    }
    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getActivePrice() {
        return activePrice;
    }
    public void setActivePrice(String activePrice) {
        this.activePrice = activePrice;
    }
    public Date getActiveStartTime() {
        return activeStartTime;
    }
    public void setActiveStartTime(Date activeStartTime) {
        this.activeStartTime = activeStartTime;
    }
    public Date getActiveEndTime() {
        return activeEndTime;
    }
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public List<AttributePO> getAttributeList() {
        return attributeList;
    }
    public void setAttributeList(List<AttributePO> skuAttributeList) {
        this.attributeList = skuAttributeList;
    }
    public String getSkuAttributeValueCode1() {
        return skuAttributeValueCode1;
    }
    public void setSkuAttributeValueCode1(String skuAttributeValueCode1) {
        this.skuAttributeValueCode1 = skuAttributeValueCode1;
    }
    public String getSkuAttributeValueCode2() {
        return skuAttributeValueCode2;
    }
    public void setSkuAttributeValueCode2(String skuAttributeValueCode2) {
        this.skuAttributeValueCode2 = skuAttributeValueCode2;
    }
    public String getSkuAttributeValue1() {
        return skuAttributeValue1;
    }
    public void setSkuAttributeValue1(String skuAttributeValue1) {
        this.skuAttributeValue1 = skuAttributeValue1;
    }
    public String getSkuAttributeValue2() {
        return skuAttributeValue2;
    }
    public void setSkuAttributeValue2(String skuAttributeValue2) {
        this.skuAttributeValue2 = skuAttributeValue2;
    }
    public Integer getSkuId() {
        return skuId;
    }
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
    public double getPoints() {
        return points;
    }
    public void setPoints(double points) {
        this.points = points;
    }
    public Object getSpecialAttrObj() {
        return specialAttrObj;
    }
    public void setSpecialAttrObj(Object specialAttrObj) {
        this.specialAttrObj = specialAttrObj;
    }
	public List<ProductImagePO> getImageOPList() {
		return imageOPList;
	}
	public void setImageOPList(List<ProductImagePO> imageOPList) {
		this.imageOPList = imageOPList;
	}
	public List<JointImagePO> getJointImagePOList() {
		return jointImagePOList;
	}
	public void setJointImagePOList(List<JointImagePO> jointImagePOList) {
		this.jointImagePOList = jointImagePOList;
	}
	public Long getMainSku() {
		return mainSku;
	}
	public void setMainSku(Long mainSku) {
		this.mainSku = mainSku;
	}
	
	
    
}
