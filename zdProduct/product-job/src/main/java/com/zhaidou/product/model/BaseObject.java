package com.zhaidou.product.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = -5829190001096486247L;

	public String toString() {
		try {
			return ToStringBuilder.reflectionToString(this,
					ToStringStyle.SHORT_PREFIX_STYLE);
		} catch (Exception e) {
		}
		return super.toString();
	}

	@Override
	public boolean equals(Object arg0) {
		StringBuffer methodName = new StringBuffer();
		Field[] srcfields = this.getClass().getDeclaredFields();
		Method method = null;
		Method method1 = null;
		for (Field field : srcfields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			try {
				methodName.delete(0, methodName.length());
				methodName.append("get");
				methodName.append(field.getName().substring(0, 1).toUpperCase());
				methodName.append(field.getName().substring(1));
			    method = this.getClass().getMethod(methodName.toString());
				method1 = arg0.getClass().getMethod(methodName.toString());
				
				if(!method.getClass().equals(method1.getClass()))
					return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
