/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.model.mall;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-14     Created
 *
 * </pre>
 * @since 1.
 */

public class SubMallProductCategory{

    /**
     * 类别id
     */
    Long cate_id;
    /**
     * 类别名称
     */
    String cate_name;
    public Long getCate_id() {
        return cate_id;
    }
    public void setCate_id(Long cate_id) {
        this.cate_id = cate_id;
    }
    public String getCate_name() {
        return cate_name;
    }
    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
}
