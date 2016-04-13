package com.zhaidou.jobCenter.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class SystemInit {
	
	/**
	 * 定义servlet上下文对象
	 */
	private static ServletContext servletContext;

	private Map<String, String> systemInitParam;

	public Map<String, String> getSystemInitParam() {
		return systemInitParam;
	}

	public void setSystemInitParam(Map<String, String> systemInitParam) {
		this.systemInitParam = systemInitParam;
	}

	/**
	 * Initialization System Param Log4j中变量初始化用
	 */
	public void init() throws ServletException {
		for (String key : systemInitParam.keySet()) {
			System.setProperty(key, systemInitParam.get(key));
		}
		
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		SystemInit.servletContext = servletContext;
	}
}
