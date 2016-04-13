package com.zhaidou.product.category.service;

/**
 * @Description 筛选项挂载操作类型
 * @author lois siau
 *
 */
public enum FilterMountOptType {
	/**
	 * 添加筛选项
	 */
	APPEND(1),

	/**
	 * 修改筛选项
	 */
	UPDATE(2),

	/**
	 * 删除筛选项
	 */
	DELETE(3);

	private Integer value;

	private FilterMountOptType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
