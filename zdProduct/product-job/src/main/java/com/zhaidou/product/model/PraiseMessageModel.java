package com.zhaidou.product.model;

import java.io.Serializable;

import com.zhaidou.common.model.AbstractBaseModel;

@SuppressWarnings("serial")
public class PraiseMessageModel extends AbstractBaseModel implements Serializable{
    
    private Long id;                                
    private Long productId;
    private String productCode;                 //商品编号
    private String productName;                 //商品名称
    private Integer praiseAccount;              //现点赞数
    private Integer sendGrade;                  //现点赞等级
    private Integer sendStatus;                 //发送状态
    private Integer retryNum;                   //重试次数
    private Integer lastSendAccount;            //上次发送数
    private Long lastSendTime;               //上次发送时间
    private Integer lastSendGrade;              //上次发送点赞等级
    private Long createTime;
    private Long updateTime;
    private String supplierPhone;
    private String operatorPhone;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Integer getPraiseAccount() {
        return praiseAccount;
    }
    public void setPraiseAccount(Integer praiseAccount) {
        this.praiseAccount = praiseAccount;
    }
    public Integer getSendGrade() {
        return sendGrade;
    }
    public void setSendGrade(Integer sendGrade) {
        this.sendGrade = sendGrade;
    }
    public Integer getSendStatus() {
        return sendStatus;
    }
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }
    public Integer getRetryNum() {
        return retryNum;
    }
    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }
    public Integer getLastSendAccount() {
        return lastSendAccount;
    }
    public void setLastSendAccount(Integer lastSendAccount) {
        this.lastSendAccount = lastSendAccount;
    }
    public Long getLastSendTime() {
        return lastSendTime;
    }
    public void setLastSendTime(Long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }
    public Integer getLastSendGrade() {
        return lastSendGrade;
    }
    public void setLastSendGrade(Integer lastSendGrade) {
        this.lastSendGrade = lastSendGrade;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
    public String getSupplierPhone() {
        return supplierPhone;
    }
    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }
    public String getOperatorPhone() {
        return operatorPhone;
    }
    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }
}
