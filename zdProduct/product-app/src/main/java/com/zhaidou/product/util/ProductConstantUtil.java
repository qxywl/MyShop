package com.zhaidou.product.util;

import com.zhaidou.framework.common.BusinessConstants;
import com.zhaidou.framework.util.properties.PropertiesUtil;

public class ProductConstantUtil {
    
    public static String CONFIG_PROPERTIES_FILE_NAME = "config.properties";
    
    /** 商城*/
    public static final String BIZ_PRODUCT_MALL_CODE=BusinessConstants.BUSINESS_TYPE_MALL;
    /** 酒店 */
    public static final String BIZ_PRODUCT_HOTEL_CODE=BusinessConstants.BUSINESS_TYPE_HOTEL;
    /** 机票 */
    public static final String BIZ_PRODUCT_AIRFLIGHT_CODE=BusinessConstants.BUSINESS_TYPE_TICKETS;
    /** 鲜花 */
    public static final String BIZ_PRODUCT_FLOWER_CODE=BusinessConstants.BUSINESS_TYPE_FLOWER;
    /** 团购 */
    public static final String BIZ_PRODUCT_TUANGOU_CODE=BusinessConstants.BUSINESS_TYPE_TUAN;
    /** php的请求结果 */
    public static final int PHP_REQUEST_SUCCESS=200;
    /** 处理请求结果。 */
    public static final int HANDEL_SUCCESS=0;
    
    public static final int HANDEL_FAIL = Integer.parseInt(PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "handel_fail", "-1"));
    
    /** 从php服务中获取鲜花信息失败。 */
    public static final String BIZ_FLOWER_ERROR_CODE001=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_request_param_business_type_error_code","10104001");
    /** 处理鲜花产品详情中系统异常 */
    public static final String BIZ_FLOWER_ERROR_CODE002=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_request_param_business_type_error_code","10104002");
    /** 机票请求缺少参数 */
    public static final String BIZ_TICKET_ERROR_CODE001=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_ticket_error_code001","10102003");
    /** 获得远程服务结果失败 */
    public static final String BIZ_TICKET_ERROR_CODE002=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_ticket_error_code002","10102004");
    /**获得远程服务结果失败 */
    public static final String BIZ_TICKET_ERROR_CODE003=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_ticket_error_code003","10102005");
    /** 机票sku查询参数不够 */
    public static final String BIZ_TICKET_ERROR_CODE004=PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_ticket_error_code004","10102006");
    
    
    /** 请求参数业务类型无法匹配*/
    public static String BIZ_REQUEST_PARAM_BUSINESS_TYPE_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_request_param_business_type_error_code","20100007");
    /** 酒店服务接口异常 */
    public static String BIZ_OLD_HOTEL_SERVICE_ILLEGAL_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_old_hotel_service_illegal_error_code","40103008");
    /** 酒店服务接口返回数据不合法*/
    public static String BIZ_OLD_HOTEL_SERVICE_DATA_ILLEGAL_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_old_hotel_service_data_illegal_error_code","40103009");
    /** 酒店服务接口返回业务请求异常*/
    public static String BIZ_OLD_HOTEL_SERVICE_REQUEST_ILLEGAL_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_old_hotel_service_request_illegal_error_code","20103010");
    /** 请求参数分页数据不合法 */
    public static String BIZ_REQUEST_PARAM_PAGE_DATA_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_request_param_page_data_error_code","20103011");
    /** 请求参数对象转换失败 */
    public static String BIZ_REQUEST_PARAM_CONVERT_ERROR_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_request_param_convert_error_code","20106012");
    
    /*==========类别获取==========*/
    /**类别查询参数不全*/
    public static String BIZ_CATEGORY_ILLEGAL_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_category_illegal_error_code001","20106013");
    /**鲜花类别未从php端获取到数据*/
    public static String BIZ_FLOWER_CATEGORY_DATA_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_flower_category_data_error_code001","20103014");
    /**鲜花类别php接口返回值为null*/
    public static String BIZ_FLOWER_CATEGORY_DATA_ERROR_CODE002 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_flower_category_data_error_code002","20103015");
    /**鲜花类别Json解析失败*/
    public static String BIZ_FLOWER_CATEGORY_DATA_ERROR_CODE003 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_flower_category_data_error_code003","20103016");
    /**鲜花类别数字转换失败*/
    public static String BIZ_FLOWER_CATEGORY_DATA_ERROR_CODE004 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_flower_category_data_error_code004","20103017");
    /**商城商品类别未从php端获取到数据*/
    public static String BIZ_MALL_CATEGORY_DATA_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_category_data_error_code001","20101018");
    /**商城商品类别php接口返回值为null*/
    public static String BIZ_MALL_CATEGORY_DATA_ERROR_CODE002 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_category_data_error_code002","20101019");
    /**商城商品类别Json解析失败*/
    public static String BIZ_MALL_CATEGORY_DATA_ERROR_CODE003 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_category_data_error_code003","20101020");
    /**商城商品类别数字转换失败*/
    public static String BIZ_MALL_CATEGORY_DATA_ERROR_CODE004 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_category_data_error_code004","20101021");
    
    /*=========商城商品获取==========*/
    /**商城商品列表请求参数错误*/
    public static String BIZ_MALL_LIST_DATA_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_list_data_error_code001","20101022");
    /**商城商品列表php接口返回错误*/
    public static String BIZ_MALL_LIST_DATA_ERROR_CODE002 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_list_data_error_code002","20101023");
    /**商城商品列表php接口访问错误*/
    public static String BIZ_MALL_LIST_DATA_ERROR_CODE003 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_list_data_error_code003","20101024");
    /**获取商城商品列表错误*/
    public static String BIZ_MALL_LIST_DATA_ERROR_CODE004 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_list_data_error_code004","20101025");
    /**获取商城商品详情请求参数错误*/
    public static String BIZ_MALL_DETAIL_DATA_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_detail_data_error_code001","20101026");
    /**商城商品详情php接口返回错误*/
    public static String BIZ_MALL_DETAIL_DATA_ERROR_CODE002 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_detail_data_error_code002","20101027");
    /**商城商品详情php接口访问错误*/
    public static String BIZ_MALL_DETAIL_DATA_ERROR_CODE003 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_detail_data_error_code003","20101028");
    /**获取商城商品详情错误*/
    public static String BIZ_MALL_DETAIL_DATA_ERROR_CODE004 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_detail_data_error_code004","20101029");
    /**请求参数版本号错误*/
    public static String BIZ_REQUEST_VERSION_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_detail_data_error_code004","20101035");
    
    /*=========店铺查询========*/
    /**获取店铺列表请求参数错误*/
    public static String BIZ_STORES_LIST_DATA_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_stores_list_data_error_code001","20101030");
    /**获取店铺php接口返回错误*/
    public static String BIZ_STORES_LIST_DATA_ERROR_CODE002 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_stores_list_data_error_code002","20101031");
    /**获取店铺json未解析到数据*/
    public static String BIZ_STORES_LIST_DATA_ERROR_CODE003 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_stores_list_data_error_code003","20101032");
    /**获取店铺数据错误*/
    public static String BIZ_STORES_LIST_DATA_ERROR_CODE004 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_stores_list_data_error_code004","20101033");
    
    /*=========获取商品库存========*/
    /**获取商城商品库存请求参数错误*/
    public static String BIZ_MALL_STOCK_ERROR_CODE001 = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_mall_stock_error_code001","20101034");
    
    /** 春舞枝 url*/
    public static String BIZ_CHUNWUZHI_URL = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_chunwuzhi_url","http://112.124.12.1:8003/product/getProductInfo");
    /** 春舞枝 第三方 帐号*/
    public static String BIZ_CHUNWUZHI_EX_KEY = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_chunwuzhi_ex_key","6542DC2CE5E24F938F4B8F5E4A1BAE86");
    /** 春舞枝 第三方 密码*/
    public static String BIZ_CHUNWUZHI_EX_CODE = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "biz_chunwuzhi_ex_code","bannixing");
    
    /** 酒店SKU属性1的名称 */
    public static String HOTEL_SKU_ATTRIBUTE1_NAME = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "hotel_sku_attribute1_name", "供应商");
    /** 酒店SKU属性2的名称 */
    public static String HOTEL_SKU_ATTRIBUTE2_NAME = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME, "hotel_sku_attribute2_name", "房型产品名");
    
    /** 机票返回价格排序 */
    public static String TICKET_LIST_PRICE_ORDERBY = "11";
    /** 机票返回时间排序 */
    public static String TICKET_LIST_TIME_ORDERBY = "12";
    /** 机票返回升序排序 */
    public static String TICKET_LIST_ORDER_ASM = "0";
    /** 机票返回降序排序 */
    public static String TICKET_LIST_ORDER_DESM = "1";
    
    /** 商品描述前缀 */
    public static String MALL_PRODUCT_DESCRIPTION_PREFIX = PropertiesUtil.getValue(CONFIG_PROPERTIES_FILE_NAME,"mall_product_description_prefix","");

    
}
