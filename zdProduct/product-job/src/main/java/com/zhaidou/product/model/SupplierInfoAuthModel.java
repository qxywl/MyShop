package com.zhaidou.product.model;

import java.io.Serializable;

import com.zhaidou.common.model.AbstractBaseModel;

@SuppressWarnings("serial")
public class SupplierInfoAuthModel  extends AbstractBaseModel implements Serializable {
       
    private Long infoAuthBackupId;
    private String newValue;
    private String oldValue;
    private Long type;
    private Long status;
    private String productCode;
    private Integer sourceType;
    
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
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public Long getInfoAuthBackupId() {
        return infoAuthBackupId;
    }
    public void setInfoAuthBackupId(Long infoAuthBackupId) {
        this.infoAuthBackupId = infoAuthBackupId;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    public Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public Long getStatus() {
        return status;
    }
    public void setStatus(Long status) {
        this.status = status;
    }
    
}
