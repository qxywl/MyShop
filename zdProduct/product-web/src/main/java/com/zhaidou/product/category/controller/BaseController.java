package com.zhaidou.product.category.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 
 * @Title: BaseController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   本模块的基础Controller
 *
 * @author lvshuding 
 *
 * @date 2015年3月25日 上午11:30:09 
 *
 * @version V1.0
 */
@Controller
public class BaseController{
	
	private static final String STR_EMPTY = "";
	private static final String DIR_SEPARATOR = "/";
	private static final String COLON = ":";
	
	//对一级分类有操作权限的默认用户名
	private static final String DEFAULT_USER_NAME="admin";
	
	@Value("#{propertyConfigurerForProject2['category_insert_delete_users']}")
	private String allowInsertOrDeleteUsers;
	
	@ModelAttribute("baseUrl")
	public String getBaseUrl(HttpServletRequest request){
		String requestUrl = request.getScheme()
				  + COLON + DIR_SEPARATOR + DIR_SEPARATOR
				  +	request.getServerName()
				  + (request.getServerPort()==80?STR_EMPTY:(COLON+request.getServerPort()))
				  + request.getContextPath();
				 ;
		return requestUrl;
	}
	
	/**
	 * @Description:  判断当前用户名是否有添加、删除一级分类的权限
	 * @param currentUser
	 * @return  boolean
	 */
	protected boolean allowOperateLevel1(String currentUser){
		if(StringUtils.isEmpty(currentUser)){
			return false;
		}
		String[] usrList;
		if(StringUtils.isEmpty(allowInsertOrDeleteUsers)){
			usrList = new String[]{DEFAULT_USER_NAME};
		}
		usrList = allowInsertOrDeleteUsers.split(",");
		for(String usr:usrList){
			if(usr.equalsIgnoreCase(currentUser)){
				return true;
			}
		}
		return true;
	}
	
    protected final static String ERROR = "error";
    protected final static String SUCCESS = "success";
    protected final static String LOGIN = "login";
    @ModelAttribute()
    public String getPath(){
    	return "";
    }
}
