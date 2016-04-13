package com.zhaidou.product.model;

public class ProductDetailModel {
    //业务类型
    private int businessType;
    //商品名称
    private String productName;
    //商品别名
    private String productEnName;
    //商品编号
    private String productCode;
    //是否上架
    private int onSale;
    //品牌编号
    private String brandCode;
    //品牌名称
    private String brandName;
    //品牌别名
    private String brandEnName;
    //分类编号
    private String classCode;
    //分类名称
    private String className;
    //产地
    private String builtPlaceName;
    //币种
    private String currency;
    //市场价
    private String markerPrice;
    //定价
    private String fixPrice;
    //售价
    private String salesPrice;
    //售价类型
    private String priceType;
    //折扣率
    private float discount;
    //活动价
    private String activePrice;
    //活动开始时间
    private String activeStartTime;
    //活动结束时间
    private String activeEndTime;
    //商品扩展信息
    private String detailDataBean;
    //商品属性集合
    private String attributeList;
    //商品sku集合
    private String skuList;

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getOnSale() {
        return onSale;
    }

    public void setOnSale(int onSale) {
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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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

    public String getActivePrice() {
        return activePrice;
    }

    public void setActivePrice(String activePrice) {
        this.activePrice = activePrice;
    }

    public String getActiveStartTime() {
        return activeStartTime;
    }

    public void setActiveStartTime(String activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    public String getActiveEndTime() {
        return activeEndTime;
    }

    public void setActiveEndTime(String activeEndTime) {
        this.activeEndTime = activeEndTime;
    }

    public String getDetailDataBean() {
        return detailDataBean;
    }

    public void setDetailDataBean(String detailDataBean) {
        this.detailDataBean = detailDataBean;
    }

    public String getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(String attributeList) {
        this.attributeList = attributeList;
    }

    public String getSkuList() {
        return skuList;
    }

    public void setSkuList(String skuList) {
        this.skuList = skuList;
    }
}
