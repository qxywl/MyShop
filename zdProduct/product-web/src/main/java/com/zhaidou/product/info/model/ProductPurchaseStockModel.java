package com.zhaidou.product.info.model;
/**
 * 
 * @author donnie
 * @version 1.0.0
 *
 */
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class ProductPurchaseStockModel implements Serializable {
	
	private static final long serialVersionUID = -8065032860299296508L;

    /**  */
    private Long id ;

    /** 采购单id */
    private Long purchaseId ;
    
    /** 采购类型  0采购入库 1采购退货 */
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

    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}


	public String getDjbh() {
		return djbh;
	}


	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}


	public Integer getSh() {
		return sh;
	}


	public void setSh(Integer sh) {
		this.sh = sh;
	}


	public String getPurchaseShr() {
		return purchaseShr;
	}


	public void setPurchaseShr(String purchaseShr) {
		this.purchaseShr = purchaseShr;
	}


	public java.util.Date getShrq() {
		return shrq;
	}


	public void setShrq(java.util.Date shrq) {
		this.shrq = shrq;
	}


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


	public String getSkudm() {
		return skudm;
	}


	public void setSkudm(String skudm) {
		this.skudm = skudm;
	}


	public String getSkumc() {
		return skumc;
	}


	public void setSkumc(String skumc) {
		this.skumc = skumc;
	}


	public Integer getSl() {
		return sl;
	}


	public void setSl(Integer sl) {
		this.sl = sl;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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


	public java.util.Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}


	public Integer getCreateBy() {
		return createBy;
	}


	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}


	public java.util.Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getUpdateBy() {
		return updateBy;
	}


	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}


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
