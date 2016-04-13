package com.zhaidou.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * <p>
 * Title: AuthorizationInterceptor.java
 * </p>
 * 
 * @Description: 权限拦截验证器
 * @Author:JERRY
 * @version:1.0
 * @DATE:2013-8-6下午03:15:40
 * @see
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 用于区分mvc:resources, 正常的Controller请求
        if (handler == null || !HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            // return true;
        }

        // 用户请求URL
        String requestURL = request.getRequestURI();
        if (StringUtils.isEmpty(requestURL)) {
            return true;
        }

        if (requestURL.equals("/login") || requestURL.equals("/index")) {
            // 不拦截
            return true;
        }
        else {
            Object user = request.getSession().getAttribute("");
            if (user == null) {
                request.getRequestDispatcher("/login").forward(request, response);
                return false;
            }
            else {
                return true;
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
