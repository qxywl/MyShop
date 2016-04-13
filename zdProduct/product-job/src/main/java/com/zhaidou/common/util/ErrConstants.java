package com.zhaidou.common.util;

/**
 * <p>
 * Title: ErrConstants.java
 * </p>
 * 
 * @Description:
 * @Author:JERRY
 * @version:1.0
 * @DATE:2013-7-15上午11:11:27
 * @see
 */
public final class ErrConstants {
    public final static String GENERAL_UNKNOWN_ERR_MSG = "未知错误";
    public final static String GENERAL_PARAMS_ERR_MSG = "参数不合法";

    // == 000000：默认是全局成功状态，这里不要占用。
    // 异常定义规则：

    // AABBCC
    // AA：系统名称 : 00:全局 ,01:CAS,02原料
    // BB：子模块代码 00：全局
    // CC：具体异常代码 01：DB异常
    public class GeneralErrorCode {
        // public final static String BUSI_PORTAL_ERR = "10000"; // 系统业务异常
        // public final static String BUSI = "10003"; //非授权操作
        // 全局DB异常
        public final static String BUSI_GENERAL_DB_ERROR = "000001";
        // 数据已存在
        public final static String BUSI_GENERAL_DATA_EXSIT = "000002";
        // 数据不存在
        public final static String BUSI_GENERAL_DATA_NOEXSIT = "000003";
        // 操作不合法，不是当前用户的数据
        public final static String BUSI_GENERAL_DATA_NOUSER = "000004";
        // 入库单已经入库完成
        public final static String BUSI_GENERAL_ORDER_IC = "000005";
        // 入库单没有新增原料信息
        public final static String BUSI_GENERAL_MATERIALS_NULL = "000006";
        
        // 入库单没有新增原料信息
        public final static String BUSI_GENERAL_GOODS_STOCK_NULL = "000007";

    }

    public class HttpErrorCode {
        // httpsatus
        public final static String HTTP_INTERNAL_SERVER_ERROR = "80500";
        // 错误且没信息
        public final static String HTTP_NOMSG_SERVER_ERROR = "80510";
        // 错误且脚本报错
        public final static String HTTP_SCRIPT_ERROR_SERVER_ERROR = "80511";
        // 错误且没有数据返回
        public final static String HTTP_NO_DATA_SERVER_ERROR = "80512";
        // 路径不存在
        public final static String HTTP_NOTFOUND_ERROR = "80404";
        // 错误请求
        public final static String HTTP_BADREQUEST_ERROR = "80400";
        // 未实现的功能
        public final static String HTTP_NOTIMPLEMENTED_ERROR = "80501";
        // 非应用异常，可能是服务器自身异常
        public final static String HTTP_NOT_APP_ERROR = "80500#001";
    }

    public class BusiErrorCode {
        // public final static String BUSI_PORTAL_ERR = "10000"; // 系统业务异常
        // public final static String BUSI = "10003"; //非授权操作
        // 条码生成异常
        public final static String BUSI_BAR_CODE_ERROR = "020001";
    }

}
