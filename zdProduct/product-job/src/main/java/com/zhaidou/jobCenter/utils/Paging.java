package com.zhaidou.jobCenter.utils;

import com.zhaidou.product.model.BaseObject;

/**
 * 分页实体
 * 
 * @author kaili
 * 
 */
public class Paging extends BaseObject {

	private static final long serialVersionUID = 1191993639127496965L;
	/**
	 * 页号
	 */
	private int pageNo;
	/**
	 * 每一页数量
	 */
	private int pageSize;
	/**
	 * 数据库起始下标（包含）
	 */
	private int startRow;
	/**
	 * 数据库结束下标（包含）
	 */
	private int endRow;

	/**
	 * 默认不分页
	 */
	public Paging() {
		this.pageNo = 1;
		this.pageSize = Integer.MAX_VALUE;
		cal();
	}

	/**
	 * 指定页号和数量分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 */
	public Paging(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		cal();
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		cal();
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		cal();
	}

	private void cal() {
		if ((this.pageNo > 0) && (this.pageSize > 0)) {
			int i = this.pageNo * this.pageSize;
			this.startRow = (i - this.pageSize + 1);
			this.endRow = i;
		}
	}

	public int getStartRow() {
		return this.startRow;
	}

	public int getEndRow() {
		return this.endRow;
	}

	public static void main(String[] args) {
		Paging paging = new Paging();
		System.out.println(paging);
		paging.setPageNo(2);
		paging.setPageSize(20);
		System.out.println(paging);
	}
}
