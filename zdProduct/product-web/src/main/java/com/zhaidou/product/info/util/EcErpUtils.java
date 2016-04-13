package com.zhaidou.product.info.util;


import com.google.common.base.Joiner;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: donnie
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-20
 * Time: 下午6:43
 * desc:
 */

public class EcErpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcErpUtils.class);

    public static final String URL = "http://121.199.173.254:30015/gxkjcgerpbz/data.dpk";

    private EcErpUtils(){}

    public static Document getDocument(Object obj,String method) {

        Map<String, String> map =  EcErpUtils.transBean2Map(obj);
        map.put("method", method);
        map.put("appkey", "2E2A40DB5DB44B86992454152952E7DD");

        String params = createLinkString(map);

        return getXMLDocumentByUrl(URL + "?" + params);
    }

    /**
     * 根据url 发送 httpRequest取得xml document
     *
     * @param url
     * @return
     */
    public static Document getXMLDocumentByUrl(String url) {
        SAXReader reader = new SAXReader();
        try {
            return reader.read(new URL(url).openStream());
        } catch (DocumentException e) {
            LOGGER.error("根据url发送httpRequest取得xml异常 url ="+url , e);
        } catch (IOException e) {
            LOGGER.error("根据url发送httpRequest取得xml异常 url ="+url , e);
        }
        return null;
    }



    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            try {
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("erp params encode error ",e);
            }
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }


    /**
     * been转map
     * @param obj
     * @return
     */
    public static Map<String, String> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();

                    Object value = getter.invoke(obj);
                    if (value != null) {
                        if (value instanceof String[]) {
                            String[] values =(String[]) value;
                            if (values.length > 0) {
                                map.put(key, Joiner.on(",").join(values));
                            }
                        } else {
                            map.put(key, value.toString());
                        }
                    }

                }

            }
        } catch (Exception e) {
            LOGGER.error("transBean2Map Error ", e);
        }

        return map;

    }




}
