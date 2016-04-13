package com.zhaidou.product.category.util;

/**
 * 	图片用途关联类型 0：其它，1：商品sku，2：商品详情，3：品牌，4:品牌说明5：基础类型，6：运营类型，7：属性，8：活动，9：活动类别，10：广告，11：CMS
 * @author lois siau
 *
 */
public enum ImgOwnerEnum {	
	/**
	 * 活动
	 */
//	ACTIVITY("02"),
	
	OT(0),
	/**
	 * 
	 */
	SKU(1),
	/**
	 * 
	 */
	SK_DETAIL(2),
	/**
	 * 
	 */
	BRAND(3),
	/**
	 * 
	 */
	BRAND_DETAIL(4),
	/**
	 * 
	 */
	BASE_CATEGORY(5),
	/**
	 * 
	 */
	SALE_CATEGORY(6),
	/**
	 * 
	 */
	PROPERTY(7),
	/**
	 * 
	 */
	ACTIVITY(8),

	/**
	 * 
	 */
	ACTIVITY_TYPE(9),

	/**
	 * 
	 */
	AD(10),

	/**
	 * 
	 */
	CMS(11);
	
	private Integer value;
	private ImgOwnerEnum(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

}
