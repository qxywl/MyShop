package com.zhaidou.framework.util.data;

public class ListObj implements Obj {
    private static final long serialVersionUID = 8468686488310183161L;
    private Obj[]             array            = new Obj[] {};

    public Obj[] getArray() {
        return array;
    }

    public void setArray(Obj[] array) {
        this.array = array;
    }

    public String toString() {
        String startTag = "[";
        String endTag = "]";
        String separatorTag = ",";
        StringBuffer sb = new StringBuffer();
        sb.append(startTag);
        if (array != null) {
            int i = 0;
            for (Obj obj : array) {
                if (i > 0) {
                    sb.append(separatorTag);
                }
                sb.append(obj.toString());
                i++;
            }
        }
        sb.append(endTag);
        return sb.toString();
    }
}