package com.zhaidou.common.util;

/**
 * <p>
 * Title: Enum.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright 2.0 (c) 2012   author:JERRY
 * 
 * @version:1.0 创建时间:2012-10-3下午10:10:49
 * @see
 */
public class Enum {

	public static enum StatusEnum {
		ENABLE(0), DISABLE(-1);
		private int key;

		StatusEnum(int key) {
			this.key = key;
		}

		public int getKey() {
			return key;
		}
	}

}
