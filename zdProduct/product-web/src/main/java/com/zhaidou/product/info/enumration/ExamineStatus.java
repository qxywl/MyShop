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
public class ExamineStatus {
    
    /**
     * 用户证件类型
     */
    public static enum STATUS_ENUM {
        
        WAIT_EXAMINE("1", "待审核"), EXAMINE_SUCCESS("2", "审核成功"), WAIT_SYNCHRONIZ("3", "待同步"), SYNCHRONIZ_SUCCESS(
                "4", "同步成功"), EXAMINE_ERROR("9", "审核失败");

        private String key;

        private String value;

        private STATUS_ENUM(String key, String value) {
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
            for (STATUS_ENUM element : STATUS_ENUM.values()) {
                resultMap.put(element.getKey(), element.getValue());
            }
            return resultMap;
        }
        
        public static STATUS_ENUM getInstanceValue(String key) {
            for (STATUS_ENUM one : STATUS_ENUM.values()) {
                if (one.key.equals(key))
                    return one;
            }
            return null;
        }
        
        public static STATUS_ENUM getInstanceKey(String value) {
            for (STATUS_ENUM one : STATUS_ENUM.values()) {
                if (one.value.equals(value))
                    return one;
            }
            return null;
        }
    }   
    public static void main(String[] args) {
        System.out.println(ExamineStatus.STATUS_ENUM.getInstanceKey("身份证").getKey());
    }
}
