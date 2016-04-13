/*
 * 文 件 名:  SalecategoryFilterRelationLogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-21
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FilterMountLog implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long relationId;

	private Long categoryId;

	private String categoryCode;

	private Integer attrId;

	private String attrCode;

	private Integer attrvalueId;

	private String attrvalueCode;

	private Integer optType;

	private Integer orderNumber;

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

	public String getAttrCode() {
		return attrCode;
	}

	public Integer getAttrId() {
		return attrId;
	}

	public String getAttrvalueCode() {
		return attrvalueCode;
	}

	public Integer getAttrvalueId() {
		return attrvalueId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Date getLogDate() {
		return logDate;
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

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public void setAttrvalueCode(String attrvalueCode) {
		this.attrvalueCode = attrvalueCode;
	}

	public void setAttrvalueId(Integer attrvalueId) {
		this.attrvalueId = attrvalueId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
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

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
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
}
