package com.zhaidou.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class AbstractBase2Model implements Serializable
{
    private Long              id;
    private String            sid;
    private String            createTimes;
    private Date              createTime;
    private String            updateTimes;
    private Date              updateTime;
    private Long              createBy;
    private Long              updateBy;

    private Long              optTimes;
    private Timestamp         optTime;
    private Long              endTime;
    
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
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public Long getUpdateBy() {
        return updateBy;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
