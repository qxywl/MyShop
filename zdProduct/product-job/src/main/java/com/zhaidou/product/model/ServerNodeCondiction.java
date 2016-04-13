package com.zhaidou.product.model;

import java.sql.Timestamp;

/**
 * 服务器节点信息
 * 
 * @author caizhan
 * 
 */
public class ServerNodeCondiction extends BaseModelCondiction {

	private static final long serialVersionUID = -4512718218470199175L;
	private Long id;
	private Timestamp updateTimeStart;
	private Timestamp updateTimeEnd;
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

	public Timestamp getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Timestamp updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Timestamp getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Timestamp updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

}