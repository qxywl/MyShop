package com.zhaidou.product.brand.model;

import com.zhaidou.common.model.AbstractBaseModel;

public class CountryModel extends AbstractBaseModel {
	
	private String countryCode; //国家编号
	private String countryAbbr; //国家地域编号
	private String countryName; //国家名称
	private String countryEname;//英文名称
	private String countryPhoneCode;//电话编号
	private String countryCurrencyCode;//货币编号
	private String countryCurrencyName;//货币名称
	private String countryCurrencySign;//货币符号
	
	private Integer countryTimezone; // 时区差
	private Integer countryRank;    // 排序
	private Integer countryFavorites; // 是否常用 
	
	
	public CountryModel() {
		
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryAbbr() {
		return countryAbbr;
	}
	public void setCountryAbbr(String countryAbbr) {
		this.countryAbbr = countryAbbr;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryEname() {
		return countryEname;
	}
	public void setCountryEname(String countryEname) {
		this.countryEname = countryEname;
	}
	public String getCountryPhoneCode() {
		return countryPhoneCode;
	}
	public void setCountryPhoneCode(String countryPhoneCode) {
		this.countryPhoneCode = countryPhoneCode;
	}
	public String getCountryCurrencyCode() {
		return countryCurrencyCode;
	}
	public void setCountryCurrencyCode(String countryCurrencyCode) {
		this.countryCurrencyCode = countryCurrencyCode;
	}
	public String getCountryCurrencyName() {
		return countryCurrencyName;
	}
	public void setCountryCurrencyName(String countryCurrencyName) {
		this.countryCurrencyName = countryCurrencyName;
	}
	public String getCountryCurrencySign() {
		return countryCurrencySign;
	}
	public void setCountryCurrencySign(String countryCurrencySign) {
		this.countryCurrencySign = countryCurrencySign;
	}
	public Integer getCountryTimezone() {
		return countryTimezone;
	}
	public void setCountryTimezone(Integer countryTimezone) {
		this.countryTimezone = countryTimezone;
	}
	public Integer getCountryRank() {
		return countryRank;
	}
	public void setCountryRank(Integer countryRank) {
		this.countryRank = countryRank;
	}
	public Integer getCountryFavorites() {
		return countryFavorites;
	}
	public void setCountryFavorites(Integer countryFavorites) {
		this.countryFavorites = countryFavorites;
	}
	
	
	
	

}
