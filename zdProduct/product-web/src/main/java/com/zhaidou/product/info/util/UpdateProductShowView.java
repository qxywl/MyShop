package com.zhaidou.product.info.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UpdateProductShowView implements Serializable{
    
    private String attrName;        //字段名称
    private String attrChinaName;   //字段描述
    private String oldValue;        //旧值
    private String newValue;        //新值
    private Integer type;           //类型         1  基本内容，2  商品信息，3  商品属性  4  销售属性    5 大字段描述
    
  
    public String getAttrName() {
        return attrName;
    }
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    public String getAttrChinaName() {
        return attrChinaName;
    }
    public void setAttrChinaName(String attrChinaName) {
        this.attrChinaName = attrChinaName;
    }
    public String getOldValue() {
        return oldValue;
    }
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    
    public UpdateProductShowView() {
        super();
    }
    public UpdateProductShowView(String attrName, String attrChinaName, String oldValue, String newValue, Integer type) {
        super();
        this.attrName = attrName;
        this.attrChinaName = attrChinaName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = type;
    }
    
}
