package com.zhaidou.product.solr.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @Title: GoodsSolrModel.java 
 *
 * @Package com.teshehui.product.solr.model
 *
 * @Description:   商品Schema对应的Model
 *
 * @author lvshuding 
 *
 * @date 2015年1月5日 上午11:36:53 
 *
 * @version V1.0
 */
public class GoodsSolrModel {
	
	@Field("id")
	private Integer id;//商品ID
	
	@Field("goodsName")
	private String goodsName;//商品名称
	
	@Field("goodsCode")
	private String goodsCode;//商品编码
	
	@Field("brandName")
	private String brandName;//品牌名称
	
	@Field("brandCode")
	private String brandCode;//品牌编码
	
	@Field("baseCateCodes")
	private String[] baseCateCodes;//基础分类Codes,从一级到当前级别
	
	@Field("price")
	private Float price;//价格

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String[] getBaseCateCodes() {
		return baseCateCodes;
	}

	public void setBaseCateCodes(String[] baseCateCodes) {
		this.baseCateCodes = baseCateCodes;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	

	
	
}
