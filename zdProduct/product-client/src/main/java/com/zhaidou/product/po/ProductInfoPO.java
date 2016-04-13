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

import com.zhaidou.product.po.base.ProductImagePO;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-14     Created
 *
 * </pre>
 * @since 1.
 */

public class ProductInfoPO<T> implements Serializable{
    private static final long serialVersionUID = 8335011357799819150L;
    /**
     * 业务类别  1=商城，2=机票，3=酒店，4=鲜花，5=团购
     */
    String businessType;
    /**
     * 商品名称
     */
    String productName;
    /**
     * 商品上下架状态1 上架，0下架
     * */
    String productShelves;
    /**
     * 商品别名
     */
    String productEnName;
    /**
     * 商品id
     */
    Long productId;
    /**
     * 商品编号
     */
    String productCode;
    /**
     * 商铺id
     */
    Long storeId;
    /**
     * 商铺编号
     */
    String storeCode;
    /**
     * 商铺名称
     */
    String storeName;
    
    String customerServicePhone;//客户电话
   
    String productDesc;//商品描述
    
    String integrityDesc;//商品描述
    
    /**
     * 是否上架 0否 1是
     */
    Integer onSale;
    /**
     * 品牌编号
     */
    String brandCode;
    /**
     * 品牌名称
     */
    String brandName;
    /**
     * 品牌别名
     */
    String brandEnName;
    /**
     * 分类编号
     */
    String categoryCode;
    /**
     * 分类名称
     */
    String categoryName;
    /**
     * 产地id
     */
    String builtPlaceId;
    /**
     * 产地编号
     */
    String builtPlaceCode;
    /**
     * 产地
     */
    String builtPlaceName;
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
     * 售价类型
     */
    Integer priceType;
    /**
     * 特币数
     */
    double tb;
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
     * sku属性名1 ID
     */
    String skuAttributeNameCode1;
    /**
     * sku属性名1 ID
     */
    String skuAttributeName1;
    /**
     * sku属性名2 ID
     */
    String skuAttributeNameCode2;
    /**
     * sku属性名2 ID
     */
    String skuAttributeName2;
    /**
     * sku排序字段
     */
    String sortSkuAttributeNameId;
    /**
     * sku排序规则
     */
    List<String> skuAttrSort;
    /**
     * 商品sku集合
     */
    List<ProductSKUPO> skuList=new ArrayList<ProductSKUPO>();
    /**
     * 商品扩展信息
     */
    T detailDataBean;
    /**
     * 商品属性集合
     */
    List<AttributePO> attributeList=new ArrayList<AttributePO>();
    
    List<ProductImagePO> imageList;
    
    private Integer  userMaxNum;//用户可以购买数据特卖SKU
    
    private Integer  userMaxType;//用户可以购买数据特卖SKU时间段

    private Integer zeroMaxCount;//
    
    private String productShortName;//产品短名称
    
    private String productPrefix;//前缀
   
    private String productSuffix;//后缀
    
    
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
	public String getIntegrityDesc() {
		return integrityDesc;
	}
	public void setIntegrityDesc(String integrityDesc) {
		this.integrityDesc = integrityDesc;
	}
	public Integer getUserMaxNum() {
		return userMaxNum;
	}
	public void setUserMaxNum(Integer userMaxNum) {
		this.userMaxNum = userMaxNum;
	}
	public Integer getUserMaxType() {
		return userMaxType;
	}
	public void setUserMaxType(Integer userMaxType) {
		this.userMaxType = userMaxType;
	}
	public Integer getZeroMaxCount() {
		return zeroMaxCount;
	}
	public void setZeroMaxCount(Integer zeroMaxCount) {
		this.zeroMaxCount = zeroMaxCount;
	}
	public void addAttributePo(Long id,String attributeName,String attributeValue,Integer attributeLevel){
        attributeList.add(new AttributePO(id,attributeName,attributeValue,attributeLevel));
    }
    public void addProductSKUPO(ProductSKUPO sku){
        skuList.add(sku);
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductEnName() {
        return productEnName;
    }
    public void setProductEnName(String productEnName) {
        this.productEnName = productEnName;
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
    public Long getStoreId() {
        return storeId;
    }
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public Integer getOnSale() {
        return onSale;
    }
    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
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
    public String getBrandEnName() {
        return brandEnName;
    }
    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getBuiltPlaceName() {
        return builtPlaceName;
    }
    public void setBuiltPlaceName(String builtPlaceName) {
        this.builtPlaceName = builtPlaceName;
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
    public T getDetailDataBean() {
        return detailDataBean;
    }
    public void setDetailDataBean(T detailDataBean) {
        this.detailDataBean = detailDataBean;
    }
    public List<AttributePO> getAttributeList() {
        return attributeList;
    }
    public void setAttributeList(List<AttributePO> attributeList) {
        this.attributeList = attributeList;
    }
    public List<ProductSKUPO> getSkuList() {
        return skuList;
    }
    public void setSkuList(List<ProductSKUPO> skuList) {
        this.skuList = skuList;
    }
    public String getBuiltPlaceId() {
        return builtPlaceId;
    }
    public void setBuiltPlaceId(String builtPlaceId) {
        this.builtPlaceId = builtPlaceId;
    }
    public String getStoreCode() {
        return storeCode;
    }
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    public String getBuiltPlaceCode() {
        return builtPlaceCode;
    }
    public void setBuiltPlaceCode(String builtPlaceCode) {
        this.builtPlaceCode = builtPlaceCode;
    }
    public String getSkuAttributeNameCode1() {
        return skuAttributeNameCode1;
    }
    public void setSkuAttributeNameCode1(String skuAttributeNameCode1) {
        this.skuAttributeNameCode1 = skuAttributeNameCode1;
    }
    public String getSkuAttributeNameCode2() {
        return skuAttributeNameCode2;
    }
    public void setSkuAttributeNameCode2(String skuAttributeNameCode2) {
        this.skuAttributeNameCode2 = skuAttributeNameCode2;
    }
    public String getSkuAttributeName1() {
        return skuAttributeName1;
    }
    public void setSkuAttributeName1(String skuAttributeName1) {
        this.skuAttributeName1 = skuAttributeName1;
    }
    public String getSkuAttributeName2() {
        return skuAttributeName2;
    }
    public void setSkuAttributeName2(String skuAttributeName2) {
        this.skuAttributeName2 = skuAttributeName2;
    }
    public List<String> getSkuAttrSort() {
        return skuAttrSort;
    }
    public void setSkuAttrSort(List<String> skuAttrSort) {
        this.skuAttrSort = skuAttrSort;
    }
    public double getTb() {
        return tb;
    }
    public void setTb(double tb) {
        this.tb = tb;
    }
    public String getSortSkuAttributeNameId() {
        return sortSkuAttributeNameId;
    }
    public void setSortSkuAttributeNameId(String sortSkuAttributeNameId) {
        this.sortSkuAttributeNameId = sortSkuAttributeNameId;
    }
	public String getCustomerServicePhone() {
		return customerServicePhone;
	}
	public void setCustomerServicePhone(String customerServicePhone) {
		this.customerServicePhone = customerServicePhone;
	}
	public List<ProductImagePO> getImageList() {
		return imageList;
	}
	public void setImageList(List<ProductImagePO> imageList) {
		this.imageList = imageList;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductShelves() {
		return productShelves;
	}
	public void setProductShelves(String productShelves) {
		this.productShelves = productShelves;
	}

	
}
