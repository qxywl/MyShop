package com.zhaidou.ecerp.params;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: 黄健平
 * email:jphuang@zhaidou.com
 * company: 深圳雅革科技
 * Date: 15-11-23
 * Time: 上午10:43
 * desc: 修改商品请求参数
 */

public class GoodsModify {

    /**
     * 商品代码
     */
    private String spdm	;

    /**
     * 商品名称
     */
    private String spmc ;

    /**
     * 重量
      */
    private String zl;

    /**
     * 供应商代码
     */
    private String gys_dm;

    /**
     * 标准售价
     */
    private String bzsj	;

    /**
     * 标准进价
     */
    private String bzjj;

    /**
     * 备注
     */
    private String bz;

    /**
     * 商品描述
     */
    private String desc;

    /**
     * 供应商货号
     */
    private String gyshh;

    /**
     * 商品店存上限数量
     */
    private String num;

    /**
     * 是否自动条码
     */
    private String auto_barcode;

    /**
     * 规格代码
     */
    private String[] skudms;

    /**
     * 规格名称
     */
    private String[] skumcs;

    /**
     * 规格重量
     */
    private String[] skuzls;

    /**
     * 规格备注
     */
    private String[] skuggbzs;

    /**
     * 规格标准售价
     */
    private String[] skubzsjs;

    /**
     * 规格标准进价
     */
    private String[] skubzjjs;

    /**
     * 供应商货号
     */
    private String[] gyshhs;

    /**
     * 规格店存数量
     */
    private String[] skunums;

    /**
     * 商品简称
     */
    private String spdm2;

    /**
     * 备注2
     */
    private String bz2;

    /**
     * 备注3
     */
    private String bz3;

    /**
     * 单位
     */
    private String danwei;

    /**
     * 是否启用批次
     */
    private String IsBatchManagement;


    public String getSpdm() {
        return spdm;
    }

    public void setSpdm(String spdm) {
        this.spdm = spdm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getGys_dm() {
        return gys_dm;
    }

    public void setGys_dm(String gys_dm) {
        this.gys_dm = gys_dm;
    }

    public String getBzsj() {
        return bzsj;
    }

    public void setBzsj(String bzsj) {
        this.bzsj = bzsj;
    }

    public String getBzjj() {
        return bzjj;
    }

    public void setBzjj(String bzjj) {
        this.bzjj = bzjj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGyshh() {
        return gyshh;
    }

    public void setGyshh(String gyshh) {
        this.gyshh = gyshh;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAuto_barcode() {
        return auto_barcode;
    }

    public void setAuto_barcode(String auto_barcode) {
        this.auto_barcode = auto_barcode;
    }

    public String[] getSkudms() {
        return skudms;
    }

    public void setSkudms(String[] skudms) {
        this.skudms = skudms;
    }

    public String[] getSkumcs() {
        return skumcs;
    }

    public void setSkumcs(String[] skumcs) {
        this.skumcs = skumcs;
    }

    public String[] getSkuzls() {
        return skuzls;
    }

    public void setSkuzls(String[] skuzls) {
        this.skuzls = skuzls;
    }

    public String[] getSkuggbzs() {
        return skuggbzs;
    }

    public void setSkuggbzs(String[] skuggbzs) {
        this.skuggbzs = skuggbzs;
    }

    public String[] getSkubzsjs() {
        return skubzsjs;
    }

    public void setSkubzsjs(String[] skubzsjs) {
        this.skubzsjs = skubzsjs;
    }

    public String[] getSkubzjjs() {
        return skubzjjs;
    }

    public void setSkubzjjs(String[] skubzjjs) {
        this.skubzjjs = skubzjjs;
    }

    public String[] getGyshhs() {
        return gyshhs;
    }

    public void setGyshhs(String[] gyshhs) {
        this.gyshhs = gyshhs;
    }

    public String[] getSkunums() {
        return skunums;
    }

    public void setSkunums(String[] skunums) {
        this.skunums = skunums;
    }

    public String getSpdm2() {
        return spdm2;
    }

    public void setSpdm2(String spdm2) {
        this.spdm2 = spdm2;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getIsBatchManagement() {
        return IsBatchManagement;
    }

    public void setIsBatchManagement(String isBatchManagement) {
        IsBatchManagement = isBatchManagement;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
