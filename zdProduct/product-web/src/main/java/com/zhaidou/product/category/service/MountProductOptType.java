package com.zhaidou.product.category.service;

/***
 * 产品挂载操作类型
 * @author lois siau
 *
 */
public enum MountProductOptType {
	/**
	 * 更新挂载的产品
	 */
	UPDATE(1), 
	
	/**
	 * 删除挂载的产品
	 */
	DELETE(2);
	private Integer value;

	private MountProductOptType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
