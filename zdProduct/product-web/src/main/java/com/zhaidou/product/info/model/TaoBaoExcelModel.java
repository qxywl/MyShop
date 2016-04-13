package com.zhaidou.product.info.model;

public class TaoBaoExcelModel {
    
    private Integer rownum;  //excel的第几行

    private String taobaoProductName;   // 宝贝名称

    private String taobaoDescription;   // 宝贝描述

    private String taobaoPic;           // 新图片

    private String taobaoSkuGroup;      // 销售属性组合

    private String taobaoGroupAlias;    // 销售属性别名

    private String taobaoCpvMemo;       // 属性值备注

    private String taobaoInputCustomCpv; // 自定义属性值

    private String taobaoCostPrice;     // 供货价格

    
    public String getTaobaoProductName() {
        return taobaoProductName;
    }

    
    public void setTaobaoProductName(String taobaoProductName) {
        this.taobaoProductName = taobaoProductName;
    }

    
    public String getTaobaoDescription() {
        return taobaoDescription;
    }

    
    public void setTaobaoDescription(String taobaoDescription) {
        this.taobaoDescription = taobaoDescription;
    }

    
    public String getTaobaoPic() {
        return taobaoPic;
    }

    
    public void setTaobaoPic(String taobaoPic) {
        this.taobaoPic = taobaoPic;
    }

    
    public String getTaobaoSkuGroup() {
        return taobaoSkuGroup;
    }

    
    public void setTaobaoSkuGroup(String taobaoSkuGroup) {
        this.taobaoSkuGroup = taobaoSkuGroup;
    }

    
    public String getTaobaoGroupAlias() {
        return taobaoGroupAlias;
    }

    
    public void setTaobaoGroupAlias(String taobaoGroupAlias) {
        this.taobaoGroupAlias = taobaoGroupAlias;
    }

    
    public String getTaobaoCpvMemo() {
        return taobaoCpvMemo;
    }

    
    public void setTaobaoCpvMemo(String taobaoCpvMemo) {
        this.taobaoCpvMemo = taobaoCpvMemo;
    }

    
    public String getTaobaoInputCustomCpv() {
        return taobaoInputCustomCpv;
    }

    
    public void setTaobaoInputCustomCpv(String taobaoInputCustomCpv) {
        this.taobaoInputCustomCpv = taobaoInputCustomCpv;
    }

    
    public String getTaobaoCostPrice() {
        return taobaoCostPrice;
    }

    
    public void setTaobaoCostPrice(String taobaoCostPrice) {
        this.taobaoCostPrice = taobaoCostPrice;
    }


    @Override
    public String toString() {
        return "TaoBaoExcelModel [taobaoProductName=" + taobaoProductName + ", taobaoDescription=" + taobaoDescription
               + ", taobaoPic=" + taobaoPic + ", taobaoSkuGroup=" + taobaoSkuGroup + ", taobaoGroupAlias="
               + taobaoGroupAlias + ", taobaoCpvMemo=" + taobaoCpvMemo + ", taobaoInputCustomCpv="
               + taobaoInputCustomCpv + ", taobaoCostPrice=" + taobaoCostPrice + "]";
    }


    public Integer getRownum() {
        return rownum;
    }


    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    
    
}
