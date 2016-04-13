package com.zhaidou.product.Util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class UserHelper {
	
	private static Logger logger = Logger.getLogger(UserHelper.class);
	
	 /**
     * 获取登录用户名
     * */
    public static String getUserName(HttpServletRequest request){
    	Map<String,String> userMap = getUserMap(request);
    	if(userMap != null){
    		return userMap.get("userName");
    	}
    	return null;
    }
    
    /**
     * 获取登录用户ID
     * @return
     */
    public static String getUserId(HttpServletRequest request){
    	Map<String, String> userMap = getUserMap(request);
    	if(userMap != null){
    		return userMap.get("userId");
    	}
    	return null;
    }
    
    /**
     *获取 登录用户的信息 
     * */
    @SuppressWarnings("unchecked")
	public static Map<String,String> getUserMap(HttpServletRequest request){
    	Map<String,String> userMap = (Map<String,String>)request.getSession().getAttribute("user");
    	if(userMap == null){
    		logger.error("获取用户信息失败！！！");
    	}
    	return userMap;
    }
}
