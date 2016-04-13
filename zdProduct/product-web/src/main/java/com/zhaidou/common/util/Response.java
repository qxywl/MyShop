package com.zhaidou.common.util;

import java.io.Serializable;

/**
*<p>Title: Response.java </p>
*@Description:
*@Author:JERRY
*@version:1.0
*@DATE:2013-8-18下午04:00:06
*@see
*/
public class Response implements Serializable {

	private static final long serialVersionUID = 5678233403213825925L;
	
	private boolean status = false;
	private String code;
	private String msg; 
	private Object model;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

}
