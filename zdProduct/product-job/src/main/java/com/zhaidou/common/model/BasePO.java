package com.zhaidou.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
*<p>Title: BasePO.java </p>
*@Description: 持久层对象
*@Author:kaili
*@version:1.0
*@DATE:2013-8-21上午11:09:35
*@see
*/
public class BasePO {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
