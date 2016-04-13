package com.zhaidou.product.po.mall;

import java.io.Serializable;

public class MallProductListInfoPO implements Serializable {
	private static final long serialVersionUID = -6971517099006414106L;
	public String praise_rate;
	public String type;
	public String spec_qty;
	public String ifShow;
	public String closed;
	public String addTime;
	public String recommended;
	public int sales;// 商品销量
	public long points;// 特币
	public int recId;// 记录ID

	public String smallGoodsImage;// 购物车和订单商品小图片
	public String middleGoodsImage;// 购物车和订单商品中图片
	public String defaultImage; //商品显示的图片
	public String thumbnailSmall;// 商品列表小图片
	public String thumbnailMiddle;// 商品列表图片
	public double subtotal;// 小计
	public long subtotalPoints;// 特币
	public String stock;
	public String getPraise_rate() {
		return praise_rate;
	}
	public void setPraise_rate(String praise_rate) {
		this.praise_rate = praise_rate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpec_qty() {
		return spec_qty;
	}
	public void setSpec_qty(String spec_qty) {
		this.spec_qty = spec_qty;
	}
	public String getIfShow() {
		return ifShow;
	}
	public void setIfShow(String ifShow) {
		this.ifShow = ifShow;
	}
	public String getClosed() {
		return closed;
	}
	public void setClosed(String closed) {
		this.closed = closed;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getRecommended() {
		return recommended;
	}
	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getSmallGoodsImage() {
		return smallGoodsImage;
	}
	public void setSmallGoodsImage(String smallGoodsImage) {
		this.smallGoodsImage = smallGoodsImage;
	}
	public String getMiddleGoodsImage() {
		return middleGoodsImage;
	}
	public void setMiddleGoodsImage(String middleGoodsImage) {
		this.middleGoodsImage = middleGoodsImage;
	}
	public String getDefaultImage() {
		return defaultImage;
	}
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}
	public String getThumbnailSmall() {
		return thumbnailSmall;
	}
	public void setThumbnailSmall(String thumbnailSmall) {
		this.thumbnailSmall = thumbnailSmall;
	}
	public String getThumbnailMiddle() {
		return thumbnailMiddle;
	}
	public void setThumbnailMiddle(String thumbnailMiddle) {
		this.thumbnailMiddle = thumbnailMiddle;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public long getSubtotalPoints() {
		return subtotalPoints;
	}
	public void setSubtotalPoints(long subtotalPoints) {
		this.subtotalPoints = subtotalPoints;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
}
