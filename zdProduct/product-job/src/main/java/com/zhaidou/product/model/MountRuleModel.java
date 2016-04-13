package com.zhaidou.product.model;

import java.io.Serializable;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * 
 * @Title: MountRuleModel.java 
 *
 * @Package com.teshehui.product.model 
 *
 * @Description:   自动挂载规则对象
 *
 * @author lvshuding 
 *
 * @date 2015年6月1日 下午3:31:42 
 *
 * @version V1.0
 */
public class MountRuleModel extends AbstractBaseModel implements Serializable{

	//这个字段在abstract中已经有定义
	//private Long id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5923322963915631738L;

	private Long categoryId;//运营分类ID
	
	private String categoryCode;//运营分类编号
	
	private Integer deleteFlag;//删除标识，1:删除；0：未删除；默认值为0
	
	private String brandCodeList;//品牌编号列表，多个之间用;分隔
	
	private String baseCategoryCodeList;//基础分类编号列表，多个之间用;分隔
	
	private String productPrefix;//商品前缀
	
	private String productSuffix;//商品后缀
	
	private String productSearchTags;//搜索关键词 ；可以有多个，多个之间有;分隔
	
	private String productName;//商品名称

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

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getBrandCodeList() {
		return brandCodeList;
	}

	public void setBrandCodeList(String brandCodeList) {
		this.brandCodeList = brandCodeList;
	}

	public String getBaseCategoryCodeList() {
		return baseCategoryCodeList;
	}

	public void setBaseCategoryCodeList(String baseCategoryCodeList) {
		this.baseCategoryCodeList = baseCategoryCodeList;
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	public String getProductSuffix() {
		return productSuffix;
	}

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}

	public String getProductSearchTags() {
		return productSearchTags;
	}

	public void setProductSearchTags(String productSearchTags) {
		this.productSearchTags = productSearchTags;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
