package com.zhaidou.product.category.service;

/**
 * @Description 分类类型操作类型
 * @author lois siau
 *
 */

public enum CategoryOptType {
	/**
	 * 添加分类操作
	 */
	APPEND(1),

	/**
	 * 修改分类操作
	 */
	UPDATE(2),

	/**
	 * 删除分类操作
	 */
	DELETE(3),
	
	/**
	 * 删除子类列表操作
	 */
	DEL_CHILD(4),

	/**
	 * 显示分类操作
	 */
	SHOW(5),
	
	/**
	 * 隐藏分类操作
	 */
	HIDDEN(6);
	
	
	
	private Integer value;

	private CategoryOptType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
