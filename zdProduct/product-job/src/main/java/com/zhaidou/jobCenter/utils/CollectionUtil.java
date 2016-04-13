package com.zhaidou.jobCenter.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合工具类
 * 
 * @author kaili
 * 
 */
public class CollectionUtil {

	/**
	 * 数组转换成Set
	 * 
	 * @param <T>
	 * @param arr
	 * @return
	 */
	public static <T> Set<T> arrayToSet(T[] arr) {
		HashSet<T> s = new HashSet<T>(arr.length);
		for (T t : arr) {
			s.add(t);
		}
		return s;
	}

	/**
	 * 集合是否相等（集合里面的元素是否相同）
	 * 
	 * @param src
	 * @param desc
	 * @return
	 */
	public static boolean equals(List<?> src, List<?> desc) {
		int len = src.size();
		int len1 = desc.size();
		if (len == len1) {
			for (int i = 0; i < len; i++) {
				if (!src.get(i).equals(desc.get(i))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}
