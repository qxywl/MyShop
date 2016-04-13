package com.zhaidou.product.info.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProductInfoDetailModel implements Serializable {
    private Long prdouctInfoDetailId;
    private String arrtName;
    private String chinaAttrName;
    private String newValue;
    
    public Long getPrdouctInfoDetailId() {
        return prdouctInfoDetailId;
    }
    public void setPrdouctInfoDetailId(Long prdouctInfoDetailId) {
        this.prdouctInfoDetailId = prdouctInfoDetailId;
    }
    public String getArrtName() {
        return arrtName;
    }
    public void setArrtName(String arrtName) {
        this.arrtName = arrtName;
    }
    public String getChinaAttrName() {
        return chinaAttrName;
    }
    public void setChinaAttrName(String chinaAttrName) {
        this.chinaAttrName = chinaAttrName;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    
}
