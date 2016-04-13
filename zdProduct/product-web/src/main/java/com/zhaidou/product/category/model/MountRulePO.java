package com.zhaidou.product.category.model;

import java.util.Date;
import java.util.List;

import com.zhaidou.product.brand.model.ProductBrandModel;

/**
 * @Title: MountRulePO.java 
 *
 * @Package com.teshehui.product.category.model 
 *
 * @Description:   
 *
 * @author lvshuding 
 *
 * @date 2015年4月9日 上午10:21:27 
 *
 * @version V1.0
 */
public class MountRulePO {

	private Long id;
	
	private String mountRule;//根据各条件生成的规则表达式
	
	private Long categoryId;//运营分类ID
	
	private String categoryCode;//运营分类编号
	
	private Integer deleteFlag;//删除标识，1:删除；0：未删除；默认值为0
	
	private String brandCodeList;//品牌编号列表，多个之间用;分隔
	
	private String categoryCodeList;//运营分类编号列表，多个之间用;分隔
	
	private String productPrefix;//商品前缀
	
	private String productSuffix;//商品后缀
	
	private String productSearchTags;//搜索关键词 ；可以有多个，多个之间有;分隔
	
	private String productName;//商品名称
	
	private Date createDate;
	
	private String createUser;
	
	private Date updateDate;
	
	private String updateUser;
	
	
	private List<ProductBrandModel> brandList;//品牌列表，修改时展示列表时使用
	
	private List<CategoryPO> cateList;//分类列表，修改时展示列表时使用

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMountRule() {
		return mountRule;
	}

	public void setMountRule(String mountRule) {
		this.mountRule = mountRule;
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

	public String getCategoryCodeList() {
		return categoryCodeList;
	}

	public void setCategoryCodeList(String categoryCodeList) {
		this.categoryCodeList = categoryCodeList;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<ProductBrandModel> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<ProductBrandModel> brandList) {
		this.brandList = brandList;
	}

	public List<CategoryPO> getCateList() {
		return cateList;
	}

	public void setCateList(List<CategoryPO> cateList) {
		this.cateList = cateList;
	}
	
	public int getBrandListSize(){
		if(getBrandList()==null){
			return 0;
		}
		return getBrandList().size();
	}
	
	public int getCateListSize(){
		if(getCateList()==null){
			return 0;
		}
		return getCateList().size();
	}
	
}
