/*
 * 文 件 名:  SalecategoryProductMountRuleLogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-15
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
 * @version [版本号, 2015-04-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MountRuleLog implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long mountId;
	private Long categoryId;
	private String categoryCode;
	private String productName;
	private String productPrefix;
	private String productSuffix;
	private String searchTag;
	private String brandCodes;
	private String categoryCodes;
	private Integer optType;
	private String operateUser;
	private Date operateTime;
	private Date logDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logDateTo;

	public String getBrandCodes() {
		return brandCodes;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryCodes() {
		return categoryCodes;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Long getId() {
		return id;
	}

	public Date getLogDate() {
		return logDate;
	}

	public Date getLogDateFrom() {
		return logDateFrom;
	}

	public Date getLogDateTo() {
		return logDateTo;
	}

	public Long getMountId() {
		return mountId;
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

	public String getProductName() {
		return productName;
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public String getProductSuffix() {
		return productSuffix;
	}

	public String getSearchTag() {
		return searchTag;
	}

	public void setBrandCodes(String brandCodes) {
		this.brandCodes = brandCodes;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryCodes(String categoryCodes) {
		this.categoryCodes = categoryCodes;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public void setLogDateFrom(Date logDateFrom) {
		this.logDateFrom = logDateFrom;
	}

	public void setLogDateTo(Date logDateTo) {
		this.logDateTo = logDateTo;
	}

	public void setMountId(Long mountId) {
		this.mountId = mountId;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}
}
