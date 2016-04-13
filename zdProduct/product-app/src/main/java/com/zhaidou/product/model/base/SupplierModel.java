package com.zhaidou.product.model.base;

import com.zhaidou.product.model.AbstractBaseModel;

public class SupplierModel extends AbstractBaseModel {
	
	private String name; //供应商名称
	private String userName; //供应商登录名称
	private Long userId; //店铺负责人ID
	private String password; // 密码
	private String summary; //商家简介
	private String detailedIntroduction; //商家简介
	
	private String imageUrls;//图片地址列表 以逗号分隔
	private String manageName;//负责人名字
	private String phone;//负责人移动电话号码
	private String telephone;//负责人固定电话号码
	private String email;//负责人email
	private Integer status; //1 激活 2 未激活 3作废
	
	private Integer isUpdated;//1 已经被供应商更新 需要重新审核 2 审核通过 或者没有被供应商更新
	private Long shopId; //店铺Id
	private String shopName; //店铺名称
	private String shopMainAccount; //
	private String companyName; //公司名字
	private String province; //公司所在省份
	private String city; //公司所在城市
	private String area; //公司所在地区
	private String companyAddress; //公司详细地址
	private String merchantsDirector; //招商专员
	private Long merchantsDirectorId; //招商专员ID
	private String merchantsDirectorArea; //招商人员区域
	private String merchantsDirectorEmail; //招商人员邮箱
	private String contactsPhone; //联系人手机
	
	private String contactsEmail; //联系人email
	private String bankAccountName; //开户名
	private String bankAccount; //开户银行帐号
	private String branchBankName; //开户行支行名称
	private String branchBankCode; //开户行支行联行号
	private String branchBankLocation; //
	private String permitForOpeningBankAccount; //银行开户许可证电子版 图片链接
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetailedIntroduction() {
		return detailedIntroduction;
	}
	public void setDetailedIntroduction(String detailedIntroduction) {
		this.detailedIntroduction = detailedIntroduction;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(Integer isUpdated) {
		this.isUpdated = isUpdated;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopMainAccount() {
		return shopMainAccount;
	}
	public void setShopMainAccount(String shopMainAccount) {
		this.shopMainAccount = shopMainAccount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getMerchantsDirector() {
		return merchantsDirector;
	}
	public void setMerchantsDirector(String merchantsDirector) {
		this.merchantsDirector = merchantsDirector;
	}
	public Long getMerchantsDirectorId() {
		return merchantsDirectorId;
	}
	public void setMerchantsDirectorId(Long merchantsDirectorId) {
		this.merchantsDirectorId = merchantsDirectorId;
	}
	public String getMerchantsDirectorArea() {
		return merchantsDirectorArea;
	}
	public void setMerchantsDirectorArea(String merchantsDirectorArea) {
		this.merchantsDirectorArea = merchantsDirectorArea;
	}
	public String getMerchantsDirectorEmail() {
		return merchantsDirectorEmail;
	}
	public void setMerchantsDirectorEmail(String merchantsDirectorEmail) {
		this.merchantsDirectorEmail = merchantsDirectorEmail;
	}
	public String getContactsPhone() {
		return contactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	public String getContactsEmail() {
		return contactsEmail;
	}
	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBranchBankName() {
		return branchBankName;
	}
	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}
	public String getBranchBankCode() {
		return branchBankCode;
	}
	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
	}
	public String getBranchBankLocation() {
		return branchBankLocation;
	}
	public void setBranchBankLocation(String branchBankLocation) {
		this.branchBankLocation = branchBankLocation;
	}
	public String getPermitForOpeningBankAccount() {
		return permitForOpeningBankAccount;
	}
	public void setPermitForOpeningBankAccount(String permitForOpeningBankAccount) {
		this.permitForOpeningBankAccount = permitForOpeningBankAccount;
	}
	
	
	
	

}
