package com.zhaidou.product.solr.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @Title: GoodsSolrModel.java 
 *
 * @Package com.teshehui.product.solr.model 
 *
 * @Description:  商品Schema对应的Model 
 *
 * @author lvshuding 
 *
 * @date 2015年6月1日 下午3:10:15 
 *
 * @version V1.0
 */
public class GoodsSolrModel {
	
	@Field("id")
	private Integer id;//商品ID
	
	@Field("goodsCode")
	private String goodsCode;//商品编码

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
}
