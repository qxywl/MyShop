package com.zhaidou.framework.util.data;

import java.io.Serializable;

public class MapObj implements Obj {
    private static final long serialVersionUID = 1441399777086566824L;
    private MapOneObj[]       array            = new MapOneObj[] {};

    public MapOneObj[] getArray() {
        return array;
    }

    public void setArray(MapOneObj[] array) {
        this.array = array;
    }

    public String toString() {
        String startTag = "{";
        String endTag = "}";
        String separatorTag = ",";
        String keyValueSeparatorTag = "=";
        StringBuffer sb = new StringBuffer();
        sb.append(startTag);
        if (array != null) {
            int i = 0;
            for (MapOneObj mapOneObj : array) {
                if (i > 0) {
                    sb.append(separatorTag);
                }
                sb.append(mapOneObj.getKey()).append(keyValueSeparatorTag).append(mapOneObj.getValue().toString());
                i++;
            }
        }
        sb.append(endTag);
        return sb.toString();
    }

    public static class MapOneObj implements Serializable {
        private static final long serialVersionUID = 3857351039851427493L;
        private String            key;
        private Obj               value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Obj getValue() {
            return value;
        }

        public void setValue(Obj value) {
            this.value = value;
        }

    }
}