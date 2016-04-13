package com.zhaidou.product.model;

import java.sql.Timestamp;

/**
 * 服务器节点信息
 * 
 * @author kaili
 * 
 */
public class ServerJobList extends BaseObject {

	private static final long serialVersionUID = -4512718218470199175L;
	private Long id;

	/**
	 * 节点序号
	 */
	private String  name;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String jobList;

	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobList() {
		return jobList;
	}

	public void setJobList(String jobList) {
		this.jobList = jobList;
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