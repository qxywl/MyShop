package com.zhaidou.common.util;

/**
 * <p>
 * Title: BusiConstants.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright 2.0 (c) 2012 author:JERRY
 * 
 * @version:1.0 创建时间:2013-4-7下午09:06:39
 * @see
 */
public final class Constants {

    public final static String SYS_ERROR = "系统异常";
    public final static String SYS_SUCCESS = "系统操作成功";

    // admin模块
    public final static String ADMIN_USER_IS_EXSIT = "用户名已存在";

    public final static String EXP_BASE_USER_DISABLED = "用户被禁用";
    public final static String EXP_BASE_PWD_ERORR = "密码不正确";
    public final static String EXP_BASE_PARAMS_ERROR = "参数不完整";
    public final static String EXP_BASE_USER_EXIST = "用户已存在";

    // session维护
    public final static String SESSION_USER = "session_user";
    
    public final static String SESSION_DOMAIN = "session_user_domain";
    
    public final static String SESSION_MENU = "session_user_menu";

    public final static String DEFAULT_PWD = "123456";

    public final static String DEFUALT_SPLIT = "-";
    
    //erp库存Job类型
    public static final class ERP_JOB_TYPE{
		public static final String PURCHASESTOCK = "1";
		public static final String REFUNDSTOCK = "2";
	}
}
