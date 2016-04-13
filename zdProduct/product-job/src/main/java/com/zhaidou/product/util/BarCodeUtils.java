package com.zhaidou.product.util;

import org.apache.commons.collections.functors.ExceptionFactory;
import org.apache.commons.lang.StringUtils;

/**
 * 条码处理类
 * @author Administrator
 *
 */
public class BarCodeUtils {

    // 条码位数
    private static final String Lenh_BarCode = "00000000";


    public static String buildBarCode(String prefix, String suffix) throws Exception {

        if (StringUtils.isEmpty(suffix)) {
        	throw new Exception();
        }

        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(Lenh_BarCode);
        sb.replace(sb.length() - suffix.length(), sb.length(), suffix);
        return sb.toString();
    }


    public static void main(String[] args) {
        try {
            String s = buildBarCode("0101", "1");
            System.out.println(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}