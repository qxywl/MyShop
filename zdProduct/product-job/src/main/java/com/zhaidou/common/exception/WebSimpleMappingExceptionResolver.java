package com.zhaidou.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
*<p>Title: WebSimpleMappingExceptionResolver.java </p>
*@Description: Spring全局异常控制
*@Author:JERRY
*@version:1.0
*@DATE:2013-8-6下午03:15:40
*@see
*/
public class WebSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView model = super.doResolveException(request, response, handler, ex);
		return model;
	}

}
