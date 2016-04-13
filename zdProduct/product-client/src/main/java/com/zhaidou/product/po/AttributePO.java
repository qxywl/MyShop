/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po;

import java.io.Serializable;

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

public class AttributePO implements Serializable{
    private static final long serialVersionUID = 400205814275217042L;
    /**
     * 商品属性id
     */
    Long id;
    /**
     * 商品id
     */
    Long productId;
    /**
     * 属性名称
     */
    String attributeName;
    /**
     * 属性值
     */
    String attributeValue;
    /**
     * 属性级别（越高越重要）
     */
    Integer attributeLevel;
    public AttributePO(){}
    public AttributePO(Long id,String attributeName,String attributeValue,Integer attributeLevel){
        this.id=id;
        this.attributeName=attributeName;
        this.attributeValue=attributeValue;
        this.attributeLevel=attributeLevel;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAttributeName() {
        return attributeName;
    }
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public String getAttributeValue() {
        return attributeValue;
    }
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    public Integer getAttributeLevel() {
        return attributeLevel;
    }
    public void setAttributeLevel(Integer attributeLevel) {
        this.attributeLevel = attributeLevel;
    }
}
