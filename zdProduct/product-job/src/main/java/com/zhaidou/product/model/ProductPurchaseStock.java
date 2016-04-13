package com.zhaidou.product.model;
/**
 * 
 * @author donnie
 * @version 1.0.0
 *
 */
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class ProductPurchaseStock implements Serializable {
	
	private static final long serialVersionUID = -8065032860299296508L;

    /**  */
    private Long id ;

    /** 采购单id */
    private Long purchaseId ;
    
    /** 采购类型 0 采购入库 1 采购退货 */
    private Integer type;

    /** 单据编号 */
    private String djbh ;

    /** 是否审核 ( 0-否,1-是 ) */
    private Integer sh ;

    /** 审核人 */
    private String purchaseShr ;

    /** 审核日期 */
    private java.util.Date shrq ;

    /** 商品代码 */
    private String spdm ;

    /** 商品名称 */
    private String spmc ;

    /** 规格代码 */
    private String skudm ;

    /** 规格名称 */
    private String skumc ;

    /** 数量 */
    private Integer sl ;

    /** 是否同步库存 0:否 1是 */
    private Integer status ;

    /** 系统分配给实库数量 */
    private Integer realStock;
    
    /** 分配百分比 */
    private Integer assignPercent;
    
    /** 公共库存，分配给淘宝或美丽说 */
    private Integer publicStock;
    
    /** 分配共公库存状态 0:未分配 1：已分配 */
    private Integer assignStatus;
    
    /** 分配人*/
    private String assignBy;
    
    /** 修改时间 */
    private java.util.Date createTime ;

    /** 修改用户ID */
    private Integer createBy ;

    /**  */
    private java.util.Date updateTime ;

    /**  */
    private Integer updateBy ;

    private Integer remainder;
    private Integer total;

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long  getId(){
        return id ;
    }
    public ProductPurchaseStock setId(Long id){
        this.id = id ;
        return this;
    }

    public Long  getPurchaseId(){
        return purchaseId ;
    }
    public ProductPurchaseStock setPurchaseId(Long purchaseId){
        this.purchaseId = purchaseId ;
        return this;
    }

    public String  getDjbh(){
        return djbh ;
    }
    public ProductPurchaseStock setDjbh(String djbh){
        this.djbh = djbh ;
        return this;
    }

    public Integer  getSh(){
        return sh ;
    }
    public ProductPurchaseStock setSh(Integer sh){
        this.sh = sh ;
        return this;
    }

    public String getPurchaseShr() {
        return purchaseShr;
    }

    public void setPurchaseShr(String purchaseShr) {
        this.purchaseShr = purchaseShr;
    }

    public java.util.Date  getShrq(){
        return shrq ;
    }
    public ProductPurchaseStock setShrq(java.util.Date shrq){
        this.shrq = shrq ;
        return this;
    }

    public String  getSpdm(){
        return spdm ;
    }
    public ProductPurchaseStock setSpdm(String spdm){
        this.spdm = spdm ;
        return this;
    }

    public String  getSpmc(){
        return spmc ;
    }
    public ProductPurchaseStock setSpmc(String spmc){
        this.spmc = spmc ;
        return this;
    }

    public String  getSkudm(){
        return skudm ;
    }
    public ProductPurchaseStock setSkudm(String skudm){
        this.skudm = skudm ;
        return this;
    }

    public String  getSkumc(){
        return skumc ;
    }
    public ProductPurchaseStock setSkumc(String skumc){
        this.skumc = skumc ;
        return this;
    }

    public Integer  getSl(){
        return sl ;
    }
    public ProductPurchaseStock setSl(Integer sl){
        this.sl = sl ;
        return this;
    }

    public Integer  getStatus(){
        return status ;
    }
    public ProductPurchaseStock setStatus(Integer status){
        this.status = status ;
        return this;
    }

    public java.util.Date  getCreateTime(){
        return createTime ;
    }
    public ProductPurchaseStock setCreateTime(java.util.Date createTime){
        this.createTime = createTime ;
        return this;
    }

    public Integer  getCreateBy(){
        return createBy ;
    }
    public ProductPurchaseStock setCreateBy(Integer createBy){
        this.createBy = createBy ;
        return this;
    }

    public java.util.Date  getUpdateTime(){
        return updateTime ;
    }
    public ProductPurchaseStock setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime ;
        return this;
    }

    public Integer  getUpdateBy(){
        return updateBy ;
    }
    public ProductPurchaseStock setUpdateBy(Integer updateBy){
        this.updateBy = updateBy ;
        return this;
    }

    public Integer getRealStock() {
		return realStock;
	}

	public void setRealStock(Integer realStock) {
		this.realStock = realStock;
	}

	public Integer getAssignPercent() {
		return assignPercent;
	}

	public void setAssignPercent(Integer assignPercent) {
		this.assignPercent = assignPercent;
	}

	public Integer getPublicStock() {
		return publicStock;
	}

	public void setPublicStock(Integer publicStock) {
		this.publicStock = publicStock;
	}
	
	public Integer getAssignStatus() {
		return assignStatus;
	}

	public void setAssignStatus(Integer assignStatus) {
		this.assignStatus = assignStatus;
	}

	public String getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
