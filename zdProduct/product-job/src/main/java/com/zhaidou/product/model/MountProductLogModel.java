/*
 * 文 件 名:  SalecategoryProductRelationLogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MountProductLogModel implements Serializable{

	private static final long serialVersionUID = 1814926981433194260L;
	private Long id;
	private Long relationId;
	private Long categoryId;
	private String categoryCode;
	private Long productId;
	private String productCode;
	private Integer mountType;
	private Long orderValue;
	private Integer optType;
	private String description;
	private String operateUser;
	private Date operateTime;
	private Date logDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateTo;

 	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLogDateFrom() {
		return logDateFrom;
	}

	public void setLogDateFrom(Date logDateFrom) {
		this.logDateFrom = logDateFrom;
	}

	public Date getLogDateTo() {
		return logDateTo;
	}

	public void setLogDateTo(Date logDateTo) {
		this.logDateTo = logDateTo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getDescription() {
		return description;
	}

	public Date getLogDate() {
		return logDate;
	}

	public Integer getMountType() {
		return mountType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public Integer getOptType() {
		return optType;
	}

	public Long getOrderValue() {
		return orderValue;
	}

	public String getProductCode() {
		return productCode;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public void setMountType(Integer mountType) {
		this.mountType = mountType;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	public void setOrderValue(Long orderValue) {
		this.orderValue = orderValue;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
}
