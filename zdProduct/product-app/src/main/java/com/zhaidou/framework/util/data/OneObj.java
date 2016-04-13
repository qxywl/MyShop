package com.zhaidou.framework.util.data;

public class OneObj implements Obj {
    private static final long serialVersionUID = 8674680303711378760L;
    private Object            one;

    public Object getOne() {
        return one;
    }

    public void setOne(Object one) {
        this.one = one;
    }

    public String toString() {
        return one == null ? null : one.toString();
    }
}