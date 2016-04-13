package com.zhaidou.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
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
    private String            endTime1;
    private String            endTime2;

	
    public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	public String getEndTime2() {
		return endTime2;
	}
	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
	private String orderField;
    private String orderDirection;
    
    public String getOrderDirection() {
        return orderDirection;
    }
    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
    public Long getCreateBy() {
        return createBy;
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
    public String getOrderField() {
        return orderField;
    }
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
    
}
