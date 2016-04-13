/*
 * 文 件 名:  BasecategoryOperateLogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-03-31
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
 * @version [版本号, 2015-03-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseCategoryOperateLog implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long categoryId;

	private String categoryName;

	private String categoryCode;

	private Long parentId;

	private String parentCode;

	private Integer operateType;

	private String operateUser;

	private Date operateTime;

	private Date logDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateFrom;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateTo;

	public String getCategoryCode() {
		return categoryCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Date getLogDate() {
		return logDate;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public String getParentCode() {
		return parentCode;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

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
}
