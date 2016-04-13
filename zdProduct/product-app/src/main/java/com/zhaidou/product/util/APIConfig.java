package com.zhaidou.product.util;

import java.nio.charset.Charset;

import com.zhaidou.framework.util.properties.PropertiesUtil;
import com.zhaidou.framework.util.string.StringUtil;


/**接口配置
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-12     Created
 *
 * </pre>
 * @since 1.
 */

public class APIConfig {
    
	public static String configFileNameGeneral = "config.properties";
	public static String apiKey=StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "api_key","40287ae447680a6b0147680a6b580000"),String.class);//"40287ae447680a6b0147680a6b580000";
	public static String ticketKey=StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "ticket_key","278cc203f59fc079dc01283533b44c4f"),String.class);//"40287ae447680a6b0147680a6b580000";
	public static String token = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "token","fe358f83f404f7215aa482f94db88007"),String.class);//"fe358f83f404f7215aa482f94db88007";
	public static String flowerCagegoryUrl=StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "flower_cagegory_url","http://portal.flower.beta.teshehui.com/v2/api/products/SubCategories"),String.class);
	public static String mallCategoryUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "mall_category_url","http://portal.beta.teshehui.com/v2/api/goods/category"),String.class);
	public static String mallProductSearchUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "mall_product_search_url","http://portal.beta.teshehui.com/v2/api/goods/search"),String.class);
	public static String mallProductDetailSearchUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "mall_product_detail_search_url","http://portal.beta.teshehui.com/v2/api/goods/info"),String.class);
    public static String mallProductSKUUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "mall_product_sku_url","http://portal.beta.teshehui.com/v2/api/goods/goods_detail"),String.class);
	public static String flowerProductListUrl=StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "flower_product_list_url","http://portal.flower.beta.teshehui.com/v2/api/products/Lists"),String.class);
	public static String flowerProductDetailUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "flower_product_detail_search_url","http://portal.flower.beta.teshehui.com/v2/api/products/Item"),String.class);
	
	public static String ticketProductsUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "ticket_product_detail_search_url","http://air-app.teshehui.com/SearchAirFlightCache"),String.class);
	public static String ticketProductsStockJinriUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "ticketProductsStockJinriUrl","http://portal.air.t.teshehui.com/v3/api/flight/cabin"),String.class);
	public static String ticketProductsStockCtripUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "ticketProductsStockCtripUrl","http://air-app.teshehui.com/FlightCabinServlet"),String.class);
    
	public static String productStoresUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "product_stores_url","http://portal.beta.teshehui.com/v2/api/stores"),String.class);
	    
    /** 鲜花商品库存查询接口，直接调用鲜花供应商的接口 */
    public static String FLOWER_PRODUCT_STOCK_URL = PropertiesUtil.getValue(configFileNameGeneral, "flower_product_stock_url","http://112.124.12.1:8003/product/getProductInfo");
    
    public static Charset HOTEL_REQUEST_ENCODING_VALUE = Charset.forName(PropertiesUtil.getValue(configFileNameGeneral, "hotel_request_encoding_value","UTF-8"));
    public static Charset HOTEL_RESPONSE_ENCODING_VALUE = Charset.forName(PropertiesUtil.getValue(configFileNameGeneral, "hotel_response_encoding_value","UTF-8"));
    
    public static Integer HOTEL_RESPONSE_CODE_SUCCESS_VALUE = Integer.parseInt(PropertiesUtil.getValue(configFileNameGeneral, "hotel_response_code_success_value", "200")); 
    
    public static String HOTEL_SERVICE_HOTEL_LIST_HTTP_URL = PropertiesUtil.getValue(configFileNameGeneral, "hotel_service_hotel_list_http_url", "http://hotel-app.teshehui.com/hotelList.action");
    public static String HOTEL_SERVICE_HOTEL_PRODUCT_HTTP_URL = PropertiesUtil.getValue(configFileNameGeneral, "hotel_service_hotel_product_http_url", "http://hotel-app.teshehui.com/hotelProductList.action");
    public static String HOTEL_SERVICE_HOTEL_PRODUCT_INFO_HTTP_URL = PropertiesUtil.getValue(configFileNameGeneral, "hotel_service_hotel_product_info_http_url", "http://hotel-app.teshehui.com/hotelProductInfo.action");
    public static String HOTEL_SERVICE_HOTEL_ORDER_CREATE_CHECK_HTTP_URL = PropertiesUtil.getValue(configFileNameGeneral, "hotel_service_hotel_order_create_check_http_url", "http://portal.t.teshehui.com:48080/hotelOrderCreateCheck.action");
    public static String ticketRemainNumUrl = StringUtil.getValueFromString(PropertiesUtil.getValue(configFileNameGeneral, "ticket_remain_num_url","http://air-app.teshehui.com/RemainFlightTicket"),String.class);
}
