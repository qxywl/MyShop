/*
 * 文 件 名:  SalecategoryProductRelationDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.category.model;

import java.io.Serializable;
import java.util.Date;

import com.zhaidou.product.info.model.ProductMallViewModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MountProduct implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long relationId;

	private Long productId;

	private String productCode;

	private Long categoryId;

	private String categoryCode;

	private Long orderValue;

	private Integer mountType;

	private Integer optType;

	private Date mountTime;

	private String mountUser;

	private Date lastUpdateTime;

	private String lastUpdateUser;

	private ProductMallViewModel productMallViewModel;
	
	private CategoryPO saleCategoryModel;
	
	public ProductMallViewModel getProductMallViewModel() {
		return productMallViewModel;
	}

	public void setProductMallViewModel(ProductMallViewModel productMallViewModel) {
		this.productMallViewModel = productMallViewModel;
	}

	public CategoryPO getSaleCategoryModel() {
		return saleCategoryModel;
	}

	public void setSaleCategoryModel(CategoryPO saleCategoryModel) {
		this.saleCategoryModel = saleCategoryModel;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public Date getMountTime() {
		return mountTime;
	}

	public Integer getMountType() {
		return mountType;
	}

	public String getMountUser() {
		return mountUser;
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

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public void setMountTime(Date mountTime) {
		this.mountTime = mountTime;
	}

	public void setMountType(Integer integer) {
		this.mountType = integer;
	}

	public void setMountUser(String mountUser) {
		this.mountUser = mountUser;
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
