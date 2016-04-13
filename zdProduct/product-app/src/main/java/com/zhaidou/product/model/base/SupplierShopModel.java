package com.zhaidou.product.model.base;

import java.util.List;

import com.zhaidou.product.model.AbstractBaseModel;

public class SupplierShopModel extends AbstractBaseModel {
	
	private String name; //店铺名称
	private String url; //二级域名
	private Integer type; //店铺类型
	private Integer status; //1 开启 2 关闭 3 删除
	private Integer isRealName; //是否实名认证 1 是 2 否
	
	private Integer isEntityShop; //是否实体店铺认证  1 是 2  否
	private Long shopValidTill; //店铺有效期至
	private Long supplierId; //供应商名称
	private String supplierName; //供应商名称
	private String mainAccount; //供应商名称
	
	private String manageName; //供应商名称
	private String managePhone; //供应商名称
	private String manageEmail; //供应商名称
	private String manageTelephone; //供应商名称
	private String manageQQ; //供应商名称
	
	private String businessContactsName; //供应商名称
	private String businessContactsPhone; //供应商名称
	private String businessContactsEmail; //供应商名称
	private String businessContactsTelephone; //供应商名称
	private String businessContactsQQ; //供应商名称
	
	private String customerServiceName; //供应商名称
	private String customerServicePhone; //供应商名称
	private String customerServiceEmail; //供应商名称
	private String customerServiceTelephone; //供应商名称
	private String customerServiceQQ; //供应商名称
	
	private String financialName; //供应商名称
	private String financialPhone; //供应商名称
	private String financialEmail; //供应商名称
	private String financialTelephone; //供应商名称
	private String financialQQ; //供应商名称
	
	
	/*******辅助字段 **********/
	private List<Long> idList;//id 集合
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsRealName() {
		return isRealName;
	}
	public void setIsRealName(Integer isRealName) {
		this.isRealName = isRealName;
	}
	public Integer getIsEntityShop() {
		return isEntityShop;
	}
	public void setIsEntityShop(Integer isEntityShop) {
		this.isEntityShop = isEntityShop;
	}
	public Long getShopValidTill() {
		return shopValidTill;
	}
	public void setShopValidTill(Long shopValidTill) {
		this.shopValidTill = shopValidTill;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMainAccount() {
		return mainAccount;
	}
	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	public String getManagePhone() {
		return managePhone;
	}
	public void setManagePhone(String managePhone) {
		this.managePhone = managePhone;
	}
	public String getManageEmail() {
		return manageEmail;
	}
	public void setManageEmail(String manageEmail) {
		this.manageEmail = manageEmail;
	}
	public String getManageTelephone() {
		return manageTelephone;
	}
	public void setManageTelephone(String manageTelephone) {
		this.manageTelephone = manageTelephone;
	}
	public String getManageQQ() {
		return manageQQ;
	}
	public void setManageQQ(String manageQQ) {
		this.manageQQ = manageQQ;
	}
	public String getBusinessContactsName() {
		return businessContactsName;
	}
	public void setBusinessContactsName(String businessContactsName) {
		this.businessContactsName = businessContactsName;
	}
	public String getBusinessContactsPhone() {
		return businessContactsPhone;
	}
	public void setBusinessContactsPhone(String businessContactsPhone) {
		this.businessContactsPhone = businessContactsPhone;
	}
	public String getBusinessContactsEmail() {
		return businessContactsEmail;
	}
	public void setBusinessContactsEmail(String businessContactsEmail) {
		this.businessContactsEmail = businessContactsEmail;
	}
	public String getBusinessContactsTelephone() {
		return businessContactsTelephone;
	}
	public void setBusinessContactsTelephone(String businessContactsTelephone) {
		this.businessContactsTelephone = businessContactsTelephone;
	}
	public String getBusinessContactsQQ() {
		return businessContactsQQ;
	}
	public void setBusinessContactsQQ(String businessContactsQQ) {
		this.businessContactsQQ = businessContactsQQ;
	}
	public String getCustomerServiceName() {
		return customerServiceName;
	}
	public void setCustomerServiceName(String customerServiceName) {
		this.customerServiceName = customerServiceName;
	}
	public String getCustomerServicePhone() {
		return customerServicePhone;
	}
	public void setCustomerServicePhone(String customerServicePhone) {
		this.customerServicePhone = customerServicePhone;
	}
	public String getCustomerServiceEmail() {
		return customerServiceEmail;
	}
	public void setCustomerServiceEmail(String customerServiceEmail) {
		this.customerServiceEmail = customerServiceEmail;
	}
	public String getCustomerServiceTelephone() {
		return customerServiceTelephone;
	}
	public void setCustomerServiceTelephone(String customerServiceTelephone) {
		this.customerServiceTelephone = customerServiceTelephone;
	}
	public String getCustomerServiceQQ() {
		return customerServiceQQ;
	}
	public void setCustomerServiceQQ(String customerServiceQQ) {
		this.customerServiceQQ = customerServiceQQ;
	}
	public String getFinancialName() {
		return financialName;
	}
	public void setFinancialName(String financialName) {
		this.financialName = financialName;
	}
	public String getFinancialPhone() {
		return financialPhone;
	}
	public void setFinancialPhone(String financialPhone) {
		this.financialPhone = financialPhone;
	}
	public String getFinancialEmail() {
		return financialEmail;
	}
	public void setFinancialEmail(String financialEmail) {
		this.financialEmail = financialEmail;
	}
	public String getFinancialTelephone() {
		return financialTelephone;
	}
	public void setFinancialTelephone(String financialTelephone) {
		this.financialTelephone = financialTelephone;
	}
	public String getFinancialQQ() {
		return financialQQ;
	}
	public void setFinancialQQ(String financialQQ) {
		this.financialQQ = financialQQ;
	}
	public List<Long> getIdList() {
		return idList;
	}
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
	
	
	
	

}
