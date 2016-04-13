/*
 * 文 件 名:  SupplierDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-05-12
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * 供货商实体类
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-05-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SupplierModel extends AbstractBaseModel
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Integer supplierCode;
    private String name;
    private String userName;
    private Integer userId;
    private String passWord;
    private String summary;
    private String detailedIntroduction;
    private String imageUrls;
    private String manageName;
    private String phone;
    private String telephone;
    private String email;
    private Integer departmentType;
    private Integer status;
    private Integer isUpdated;
    private Integer shopId;
    private String shopName;
    private String shopMainAccount;
    private String companyName;
    private String province;
    private String city;
    private String area;
    private String companyAddress;
    private String merchantsDirector;
    private Integer merchantsDirectorId;
    private String merchantsDirectorArea;
    private String merchantsDirectorEmail;
    private String contactsPhone;
    private String contactsEmail;
    private String bankAccountName;
    private String bankAccount;
    private String branchBankName;
    private String branchBankCode;
    private String branchBankLocation;
    private String permitForOpeningBankAccount;
    private Double markUpRate;
    private Integer isDeleted;

    
    
    public Integer getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(Integer supplierCode) {
		this.supplierCode = supplierCode;
	}

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
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

    public Integer getMerchantsDirectorId() {
        return merchantsDirectorId;
    }

    public void setMerchantsDirectorId(Integer merchantsDirectorId) {
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

    public Double getMarkUpRate() {
        return markUpRate;
    }

    public void setMarkUpRate(Double markUpRate) {
        this.markUpRate = markUpRate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
