package com.zhaidou.product.category.service;

public enum MountType {
	AUTO(1), MANUAL(2);
	private Integer value;

	private MountType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
