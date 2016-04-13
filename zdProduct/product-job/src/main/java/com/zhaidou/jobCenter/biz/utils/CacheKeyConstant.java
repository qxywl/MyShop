package com.zhaidou.jobCenter.biz.utils;

/**
 * Cache key常量
 * 
 * @author kaili
 * 
 */
public class CacheKeyConstant {

	/**
	 * 基本分类key
	 */
	public static final String BASE_CLASS_KEY_PRE = "goods_basiccat_";
	
	/**
	 * 管理分类key
	 */
	public static final String MGR_CLASS_KEY_PRE = "goods_mgrcat_";
	
	/**
	 * 品牌key
	 */
	public static final String BRAND_KEY_PRE = "goods_brand_10154_";
	
	/**
	 * 商品key
	 */
	public static final String PRODUCT_KEY_PRE = "goods_prod_10154_";
	
	/**
	 * 商品SKUkey
	 */
	public static final String SKU_KEY_PRE = "goods_item_10154_";

	/**
	 * 价格key
	 */
	public static final String PRICE_KEY_PRE = "goods_price_10154_";

	/**
	 * 上下架key
	 */
	public static final String ONSALE_KEY_PRE = "goods_onsale_10154_";

	/**
	 * 运营分类key
	 */
	public static final String SALE_CLASS_KEY_PRE = "goods_salecat_10154_";

	/**
	 * 运营分类树key
	 */
	public static final String SALE_CLASS_TREE_KEY_PRE = "goods_salecattree_10154_";

	/**
	 * 运营分类树与平台商key
	 */
	public static final String SALE_CLASS_TREE_PF_KEY_PRE = "goods_salecattreepf_10154_";

	/**
	 * 运营分类与运营分类面包屑key
	 */
	public static final String SALE_CLASS_PATH_KEY_PRE = "goods_salecatpath_10154_";

	/**
	 * 商品与运营分类面包屑key
	 */
	public static final String SALE_CLASS_PRD_PATH_KEY_PRE = "goods_saleprocatpath_10154_";

	public static String cacheVersion;
	
	/**
	 *jexl列表
	 */
	public static final String SALE_CLASS_JEXL_List = "jexl_list";

	public static String getsaleClassCacheKey(String id) {
		return SALE_CLASS_KEY_PRE + id + "_" + cacheVersion;
	}

	public static String getsaleClassTreeCacheKey(String id) {
		return SALE_CLASS_TREE_KEY_PRE + id + "_" + cacheVersion;
	}

	public static String getsaleClassTreePfCacheKey(String id) {
		return SALE_CLASS_TREE_PF_KEY_PRE + id + "_" + cacheVersion;
	}

	public static String getsaleClassPathCacheKey(String saleId) {
		return SALE_CLASS_PATH_KEY_PRE + saleId + "_" + cacheVersion;
	}

	public static String getsaleProClassPathCacheKey(String catentryId) {
		return SALE_CLASS_PRD_PATH_KEY_PRE + catentryId + "_" + cacheVersion;
	}

	public void setCacheVersion(String cacheVersion) {
		CacheKeyConstant.cacheVersion = cacheVersion;
	}

	public static String getOnsaleCacheKey(String catentryId) {
		return ONSALE_KEY_PRE + catentryId + "_" + cacheVersion;
	}

	public static String getPriceCacheKey(String catentryId) {
		return PRICE_KEY_PRE + catentryId + "_" + cacheVersion;
	}

	public static String getBaseClassCacheKey(String code) {
		return BASE_CLASS_KEY_PRE + code + "_" + cacheVersion;
	}

	public static String getMgrClassCacheKey(String code) {
		return MGR_CLASS_KEY_PRE + code + "_" + cacheVersion;
	}

	public static String getBrandCacheKey(String code) {
		return BRAND_KEY_PRE + code + "_" + cacheVersion;
	}

	public static String getProductCacheKey(String catentryId) {
		return PRODUCT_KEY_PRE + catentryId + "_" + cacheVersion;
	}

	public static String getSkuCacheKey(String code) {
		return SKU_KEY_PRE + code + "_" + cacheVersion;
	}

	public static String getSaleClassJexlList() {
		return SALE_CLASS_JEXL_List;
	}

}
