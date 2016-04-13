package com.zhaidou.product.info.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductShelvesTmpModel implements Serializable{
    
    private Integer productShelvesTmpId;
    private String productCode;
    private Integer skuTotalNum;
    private Integer skuPassNum;
    private String totalSkuCode;
    private String passSkuCode;
    private String productName;
    private Integer status;
    
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getTotalSkuCode() {
        return totalSkuCode;
    }
    public void setTotalSkuCode(String totalSkuCode) {
        this.totalSkuCode = totalSkuCode;
    }
    public String getPassSkuCode() {
        return passSkuCode;
    }
    public void setPassSkuCode(String passSkuCode) {
        this.passSkuCode = passSkuCode;
    }
    public Integer getProductShelvesTmpId() {
        return productShelvesTmpId;
    }
    public void setProductShelvesTmpId(Integer productShelvesTmpId) {
        this.productShelvesTmpId = productShelvesTmpId;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public Integer getSkuTotalNum() {
        return skuTotalNum;
    }
    public void setSkuTotalNum(Integer skuTotalNum) {
        this.skuTotalNum = skuTotalNum;
    }
    public Integer getSkuPassNum() {
        return skuPassNum;
    }
    public void setSkuPassNum(Integer skuPassNum) {
        this.skuPassNum = skuPassNum;
    }
}
