package com.zhaidou.product.solr.enums;

public enum GoodsIndexFieldEnum {
	
	/**
	 * 商品名称
	 */
	GOODS_NAME("goodsName"),
	
	/**
	 * 品牌编号
	 */
	BRAND_CODE("brandCode"),
	
	/**
	 * 基础分类编号
	 */
	BASE_CATEGORY_CODE("baseCateCodes"),
	
	/**
	 * 商品标签
	 */
	ITEM_TAGS("itemTags"),
	
	/**
	 * 商品前缀
	 */
	GOODS_PREFIX("goodsPrefix"),
	
	/**
	 * 商品后缀
	 */
	GOODS_SUFFIX("goodsSuffix");
	
	
	private String fieldName;
	
	GoodsIndexFieldEnum(String fieldName){
		this.fieldName = fieldName;
	}
	
	public String fieldName() {
		return fieldName;
	}
}
