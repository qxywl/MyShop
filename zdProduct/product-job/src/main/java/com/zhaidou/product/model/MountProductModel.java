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
package com.zhaidou.product.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MountProductModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972982673271762907L;

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

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public Long getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Long orderValue) {
		this.orderValue = orderValue;
	}

	public Integer getMountType() {
		return mountType;
	}

	public void setMountType(Integer mountType) {
		this.mountType = mountType;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	public Date getMountTime() {
		return mountTime;
	}

	public void setMountTime(Date mountTime) {
		this.mountTime = mountTime;
	}

	public String getMountUser() {
		return mountUser;
	}

	public void setMountUser(String mountUser) {
		this.mountUser = mountUser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}
