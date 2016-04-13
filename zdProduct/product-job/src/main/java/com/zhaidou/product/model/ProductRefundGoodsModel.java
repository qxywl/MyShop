package com.zhaidou.product.model;

import java.io.Serializable;
import java.util.Date;

public class ProductRefundGoodsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 编号ID
	 */
	private Long id;
	/**
	 * 退货单id
	 */
	private Long refundId;
	/**
	 * 单据编号
	 */
	private String djbh;
	/**
	 * 原单据号
	 */
	private String ydjh;
	/**
	 * 订单单据编号
	 */
	private String dddjbh;
	/**
	 * 数量
	 */
	private Integer sl;
	/**
	 * 实际金额
	 */
	private Double sjje;
	/**
	 * 入库人
	 */
	private String cwr;
	/**
	 * 入库日期
	 */
	private Date cwrq;
	/**
	 * 会员代码
	 */
	private String hydm;
	/**
	 * 退回仓库代码
	 */
	private String ckdm;
	/**
	 * 退回仓库名称
	 */
	private String ckmc;
	/**
	 * 发货成本价
	 */
	private Double fhcbj;
	/**
	 * 商品代码
	 */
	private String spdm;
	/**
	 * 规格代码
	 */
	private String skudm;
	/**
	 * 是否同步库存 0:否 1是
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更改时间
	 */
	private Date updateTime;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDjbh() {
		return djbh;
	}
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}
	public String getYdjh() {
		return ydjh;
	}
	public void setYdjh(String ydjh) {
		this.ydjh = ydjh;
	}
	public String getDddjbh() {
		return dddjbh;
	}
	public void setDddjbh(String dddjbh) {
		this.dddjbh = dddjbh;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public Double getSjje() {
		return sjje;
	}
	public void setSjje(Double sjje) {
		this.sjje = sjje;
	}
	public String getCwr() {
		return cwr;
	}
	public void setCwr(String cwr) {
		this.cwr = cwr;
	}
	public Date getCwrq() {
		return cwrq;
	}
	public void setCwrq(Date cwrq) {
		this.cwrq = cwrq;
	}
	public String getHydm() {
		return hydm;
	}
	public void setHydm(String hydm) {
		this.hydm = hydm;
	}
	public String getCkdm() {
		return ckdm;
	}
	public void setCkdm(String ckdm) {
		this.ckdm = ckdm;
	}
	public String getCkmc() {
		return ckmc;
	}
	public void setCkmc(String ckmc) {
		this.ckmc = ckmc;
	}
	public Double getFhcbj() {
		return fhcbj;
	}
	public void setFhcbj(Double fhcbj) {
		this.fhcbj = fhcbj;
	}
	public String getSpdm() {
		return spdm;
	}
	public void setSpdm(String spdm) {
		this.spdm = spdm;
	}
	public String getSkudm() {
		return skudm;
	}
	public void setSkudm(String skudm) {
		this.skudm = skudm;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getRefundId() {
		return refundId;
	}
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

}
