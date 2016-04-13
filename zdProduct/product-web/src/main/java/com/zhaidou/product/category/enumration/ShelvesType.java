package com.zhaidou.product.category.enumration;

/**
 * 商品上下价状态
 * @author lois siau
 *
 */
public enum ShelvesType {
	/**
	 * 下架
	 */
	DOWN(0),
	
	/**
	 * 上架
	 */
	UP(1);
	private Integer value;

	private ShelvesType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
