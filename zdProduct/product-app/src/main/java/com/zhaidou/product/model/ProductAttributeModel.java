/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.model;

/**商品属性
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-6     Created
 *
 * </pre>
 * @since 1.
 */

public class ProductAttributeModel {
    /**
     * 商品属性id
     */
    Long id;
    /**
     * 商品id
     */
    Long product_id;
    /**
     * 商品属性名称
     */
    String attribute_name;
    /**
     * 商品属性值
     */
    String attribute_value;
    /**
     * 属性级别（越高越重要）
     */
    Integer attribute_level;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public String getAttribute_name() {
        return attribute_name;
    }
    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }
    public String getAttribute_value() {
        return attribute_value;
    }
    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
    }
    public Integer getAttribute_level() {
        return attribute_level;
    }
    public void setAttribute_level(Integer attribute_level) {
        this.attribute_level = attribute_level;
    }
}
