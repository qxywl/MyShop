package com.zhaidou.product.category.model;

import java.util.Date;

/**
 * 
 * @Title: FilterMountPO.java 
 *
 * @Package com.teshehui.product.category.model 
 *
 * @Description:   运营分类属性项挂载
 *
 * @author lvshuding 
 *
 * @date 2015年4月8日 下午2:31:46 
 *
 * @version V1.0
 */
public class FilterMountPO {

	private Long id;//主键列
	
	private Long categoryId;//运营分类ID
	
	private String categoryCode;//运营分类编号
	
	private Integer attrId;//属性项ID
	
	private String attrCode;//属性项编号 
	
	private Integer attrValId;//属性值ID
	
	private String attrValCode;//属性值编号
	
	private Integer deleteFlag;//是否已删除 0：未删除；1：已删除，默认为0
	
	private Integer orderNumber;//属性项排序值
	
	private String createUser; //创建人
	
	private String updateUser;//最后修改人
	
	private Date createDate;//创建时间
	
	private Date updateDate;////最后修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getAttrId() {
		return attrId;
	}

	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getAttrValId() {
		return attrValId;
	}

	public void setAttrValId(Integer attrValId) {
		this.attrValId = attrValId;
	}

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public String getAttrValCode() {
		return attrValCode;
	}

	public void setAttrValCode(String attrValCode) {
		this.attrValCode = attrValCode;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
