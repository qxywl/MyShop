package com.zhaidou.common.util;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: PageRequest.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright 2.0 (c) 2012 author:JERRY
 * 
 * @version:1.0 创建时间:2013-4-8上午09:37:39
 * @see
 */
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 5801320141473027855L;
    private Date gmtBegin;
    private Date gmtEnd;

    public Date getGmtBegin() {
        return gmtBegin;
    }

    public void setGmtBegin(Date gmtBegin) {
        this.gmtBegin = gmtBegin;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }
}
