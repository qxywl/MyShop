package com.zhaidou.product.model;

import com.zhaidou.jobCenter.utils.Paging;


/**
 * 查询条件
 * 
 * @author caizhan
 * 
 */
public class BaseModelCondiction extends BaseObject {

	private static final long serialVersionUID = 1191993639127496965L;

	private Paging paging;

	private Integer currentNode;

	private Integer totalNode;

	public Paging getPaging() {
		if (this.paging == null) {
			this.paging = new Paging();
		}
		return this.paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public Integer getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Integer currentNode) {
		this.currentNode = currentNode;
	}

	public Integer getTotalNode() {
		return totalNode;
	}

	public void setTotalNode(Integer totalNode) {
		this.totalNode = totalNode;
	}

}
