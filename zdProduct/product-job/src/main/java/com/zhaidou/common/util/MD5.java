package com.zhaidou.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
*<p>Title: MD5.java </p>
*@Description: MD5:message-digest algorithm 5 （信息-摘要算法）
*@Author:JERRY
*@version:1.0
*@DATE:2013-8-18上午10:15:17
*@see
*/
public final class MD5 {

	/**
	 * 数字数组序列
	 */
	private final static String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 字符数组转换到序列字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte[] byteValue) {
		StringBuilder resultSb = new StringBuilder();
		for (byte b : byteValue) {
			resultSb.append(byteToHexString(b));
		}
		return resultSb.toString();
	}

	/**
	 * 字符转换到字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b < 0 ? (b + 256) : b;
		int d1 = n >> 4; // 相当于 n/16
		int d2 = n % 16;
		return HEXDIGITS[d1] + HEXDIGITS[d2];
	}

	/**
	 * md5编码
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		return byteArrayToHexString(md.digest(password.getBytes()));
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(md5Encode("123456"));

		//		MessageDigest md = MessageDigest.getInstance("MD5");
		//		System.out.println((new BASE64Encoder()).encodeBuffer(md.digest()));

		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update("123456".getBytes());
		System.out.println(byteArrayToHexString(md.digest()));
	}
}