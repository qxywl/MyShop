package com.zhaidou.product.model;

import java.sql.Timestamp;

/**
 * 服务器节点信息
 * 
 * @author kaili
 * 
 */
public class ServerNode extends BaseObject {

	private static final long serialVersionUID = -4512718218470199175L;
	private Long id;

	/**
	 * 节点序号
	 */
	private Integer nodeNo;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String module;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(Integer nodeNo) {
		this.nodeNo = nodeNo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}