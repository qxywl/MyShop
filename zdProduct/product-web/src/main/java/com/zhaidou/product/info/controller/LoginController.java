package com.zhaidou.product.info.controller;

import com.zhaidou.framework.manager.impl.BaseManagerImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;


@Controller
//@RequestMapping("/login")
public class LoginController extends BaseManagerImpl{
    public static String LOGIN_INDEX="index";
    public static String LOGIN_INDEX_LOCAL="indexLocal";
    
    private static final Log       logger        = LogFactory.getLog(LoginController.class);
    
    @RequestMapping(value = "/login",method = { RequestMethod.GET, RequestMethod.POST })
    public String login(HttpServletRequest request){
    	String url=request.getParameter("url");
    	String token=request.getParameter("token");
    	request.getSession().setAttribute("url", url);
    	request.getSession().setAttribute("token", token);
    	if(null!=token){
    	request.getSession().setAttribute("user", this.getCachedClient().getObject(token));
    	}
    	 Map<String, Object> userMap = (Map<String, Object>) request.getSession().getAttribute("user");
    	 logger.debug(userMap);
        return LOGIN_INDEX;
    }
    
    
    @RequestMapping(value = "/login2",method = { RequestMethod.GET, RequestMethod.POST })
    public String loginLocal(HttpServletRequest request){
    	Map<String,String>  map2=new HashMap<String,String>();
        map2.put("userId", "1");
        map2.put("userName", "admin");
        map2.put("email","test@qq.com");
        map2.put("phone", "163777722222");
        map2.put("userType","1");
        map2.put("contactName", "999");	
    	request.getSession().setAttribute("user", map2);
        return LOGIN_INDEX_LOCAL;
    }

}
