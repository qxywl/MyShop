package com.zhaidou.common.util;

public class StatusContants {

    /**
     * 入库单状态
     * 
     * @author yu.zhang
     * 
     */
    public enum InboundOrder {

        DR("DR", "草稿"),
        IC("IC", "入库完成");

        private InboundOrder(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;


        public String getKey() {
            return key;
        }


        public void setKey(String key) {
            this.key = key;
        }


        public String getValue() {
            return value;
        }


        public void setValue(String value) {
            this.value = value;
        }

    }

}
