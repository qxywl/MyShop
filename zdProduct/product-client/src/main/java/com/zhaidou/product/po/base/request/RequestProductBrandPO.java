package com.zhaidou.product.po.base.request;

import java.io.Serializable;

import com.zhaidou.framework.util.page.Page;

public class RequestProductBrandPO implements Serializable{
	
	private static final long serialVersionUID = -6781901899555402574L;
	
	private String    brandCode; //品牌编号
	private String    brandName; //品牌名称
	
	//默认每页记录数
	public static final int DEFAULT_PAGE_SIZE = 15;
	
	protected int     pageNum = 1; //原始页码
	protected int     numPerPage = DEFAULT_PAGE_SIZE;
	
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	
	
	

	   
	    
}
