package com.zhaidou.product.po.base;

import java.io.Serializable;
import java.util.Date;

import com.zhaidou.framework.model.BasePO;

/**
 * 
 * @Title: CategoryPO.java 
 *
 * @Package com.teshehui.product.po.base 
 *
 * @Description:   分类对象(基础分类、运营分类)
 *
 * @author lvshuding 
 *
 * @date 2015年5月6日 上午11:07:01 
 *
 * @version V1.0
 */
public class CategoryPO extends BasePO implements Serializable{

	private static final long serialVersionUID = 8512347963544036728L;

	private Long id; //主键
	
	private String categoryName;//分类名称
	
	private String categoryCode;//分类编号
	
	private Long parentId;//父分类ID
	
	private String parentCode;//父分类编号
	
	private String imageUrl;//图片
	
	private Integer showFlag;//显示状态 1：显示；0：不显示； 默认值为1
	
	private Integer deleteFlag;//删除状态  0：未删除；1：已删除；默认值为0
	
	private String colorName;//颜色名称
	
	private String sizeName;//尺码名称
	
	private String createUser;//创建用户名
	
	private Date createDate;//创建时间
	
	private String updateUser;//最后修改用户名
	
	private Date updateDate;//最后修改时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
