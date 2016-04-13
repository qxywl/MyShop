package com.zhaidou.product.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractBaseModel implements Serializable
{
    private Long              id;
    private String            sid;
    private String            createTimes;
    private Long              createTime;
    private String            updateTimes;
    private Long              updateTime;
    private Long              createBy;
    private Long              updateBy;
    private String            createUserName;
    private String            updateUserName;
    private Long              optTimes;
    private Timestamp         optTime;
    private Long              endTime;
    
    public Long getCreateBy() {
        return createBy;
    }
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public Long getUpdateBy() {
        return updateBy;
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
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    public Long getId() {
            return id;
    }
    public void setId(Long id) {
            this.id = id;
    }
    public String getSid() {
            return sid;
    }
    public void setSid(String sid) {
            this.sid = sid;
    }
    
    public String getCreateTimes() {
		return createTimes;
	}
	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}
	public String getUpdateTimes() {
		return updateTimes;
	}
	public void setUpdateTimes(String updateTimes) {
		this.updateTimes = updateTimes;
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
    public Long getOptTimes() {
            return optTimes;
    }
    public void setOptTimes(Long optTimes) {
            this.optTimes = optTimes;
    }
    public Timestamp getOptTime() {
            return optTime;
    }
    public void setOptTime(Timestamp optTime) {
            this.optTime = optTime;
    }
}
