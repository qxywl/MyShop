package com.zhaidou.product.info.enumration;

import java.util.Map;

import com.google.common.collect.Maps;

/**   
 * @Title: 
 * @Description: 审核状态 常量定义
 *
 * @author wanghang
 * @date 2015年4月20日 下午4:46:51
 * @version V1.0.0
 */
public class ProductType {
    
    /**
     * 用户证件类型
     */
    public static enum PRODUCTTYPE_ENUM {
        
        AIR("01", "特卖"), HOTEL("02", "零元购"), FLOWER("03", "鲜花"), MALL("04", "商城"), TUANGOU("05", "团购");

        private String key;

        private String value;

        private PRODUCTTYPE_ENUM(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        Map<String, String> resultMap = Maps.newHashMap();

        public Map<String, String> getMap() {
            for (PRODUCTTYPE_ENUM element : PRODUCTTYPE_ENUM.values()) {
                resultMap.put(element.getKey(), element.getValue());
            }
            return resultMap;
        }
        
        public static PRODUCTTYPE_ENUM getInstanceValue(String key) {
            for (PRODUCTTYPE_ENUM one : PRODUCTTYPE_ENUM.values()) {
                if (one.key.equals(key))
                    return one;
            }
            return null;
        }
        
        public static PRODUCTTYPE_ENUM getInstanceKey(String value) {
            for (PRODUCTTYPE_ENUM one : PRODUCTTYPE_ENUM.values()) {
                if (one.value.equals(value))
                    return one;
            }
            return null;
        }
    }   
    public static void main(String[] args) {
        System.out.println(ProductType.PRODUCTTYPE_ENUM.getInstanceKey("机票1"));
    }
}
