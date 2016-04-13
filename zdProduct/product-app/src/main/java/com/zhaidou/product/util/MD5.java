package com.zhaidou.product.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**MD5 算法
 * @author Johney.lee liq
 *
 * 2014-6-4
 */
public class MD5 {
    
    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5() {
    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
//        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**MD5加密，手写
     * @param strObj
     * @return
     */
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    // MD5加密,携程提供
    public static String MD5Encoding(String source) throws NoSuchAlgorithmException
    {
              MessageDigest mdInst = MessageDigest.getInstance("MD5");
              byte [] input = source.getBytes();
              mdInst.update(input);
              byte [] output = mdInst.digest();
        int i = 0;
        StringBuffer buf = new StringBuffer(""); 
        for (int offset = 0; offset < output.length; offset++) 
        {
             i = output[offset];
            if(i<0)
            {
            i+= 256; 
            }
            if(i<16)
            {
             buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        MD5 getMD5 = new MD5();
        System.out.println(getMD5.GetMD5Code("abcdefg123456").toUpperCase());
    }
}
