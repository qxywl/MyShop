package com.zhaidou.product.category.service;

/**
 * @Description 筛选项挂载操作类型
 * @author lois siau
 *
 */
public enum MountRuleOptType {
	/**
	 * 添加筛选项挂载
	 */
	APPEND(1),

	/**
	 * 修改筛选项挂载
	 */
	UPDATE(2),

	/**
	 * 删除筛选项挂载
	 */
	DELETE(3);

	private Integer value;

	private MountRuleOptType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
