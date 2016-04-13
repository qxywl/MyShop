package com.zhaidou.product.po;

import java.io.Serializable;
import java.util.List;

import com.zhaidou.product.po.base.ProductImagePO;


public class ProductMallSkuPO implements Serializable{
	

	private static final long serialVersionUID = -1498800550393504262L;
	
	private Long        productSkuId; //sku id编号
    private Long        productId; //产品ID
    private String      productSkuCode; //sku code
   
    private String      attrColorName; //颜色名称
    private String      attrColorValue; //颜色值
    
    private String      attrSpecName; //规格名称
    private String      attrSpecValue; //规格值
    private Long        mainSku; //是否主sku  1是  0否
    private Long        ifShow ;   //是否显示 1显示，2不显示
	
    private Double marketPrice;//市场价
	private Double costPrice; //成本价
	private Double tshPrice; //销售价
	private Long tb; //特币
	
	private Double zeroPrice;
	
	private Long zeroMaxCount;

	
	public Double getZeroPrice() {
		return zeroPrice;
	}
	public void setZeroPrice(Double zeroPrice) {
		this.zeroPrice = zeroPrice;
	}
	public Long getZeroMaxCount() {
		return zeroMaxCount;
	}
	public void setZeroMaxCount(Long zeroMaxCount) {
		this.zeroMaxCount = zeroMaxCount;
	}
	private List<ProductImagePO> imagePOList;//图片集合
	
	public Long getProductSkuId() {
		return productSkuId;
	}
	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductSkuCode() {
		return productSkuCode;
	}
	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}
	public String getAttrColorName() {
		return attrColorName;
	}
	public void setAttrColorName(String attrColorName) {
		this.attrColorName = attrColorName;
	}
	public String getAttrColorValue() {
		return attrColorValue;
	}
	public void setAttrColorValue(String attrColorValue) {
		this.attrColorValue = attrColorValue;
	}
	public String getAttrSpecName() {
		return attrSpecName;
	}
	public void setAttrSpecName(String attrSpecName) {
		this.attrSpecName = attrSpecName;
	}
	public String getAttrSpecValue() {
		return attrSpecValue;
	}
	public void setAttrSpecValue(String attrSpecValue) {
		this.attrSpecValue = attrSpecValue;
	}
	public Long getMainSku() {
		return mainSku;
	}
	public void setMainSku(Long mainSku) {
		this.mainSku = mainSku;
	}
	public Long getIfShow() {
		return ifShow;
	}
	public void setIfShow(Long ifShow) {
		this.ifShow = ifShow;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getTshPrice() {
		return tshPrice;
	}
	public void setTshPrice(Double tshPrice) {
		this.tshPrice = tshPrice;
	}
	public Long getTb() {
		return tb;
	}
	public void setTb(Long tb) {
		this.tb = tb;
	}
	public List<ProductImagePO> getImagePOList() {
		return imagePOList;
	}
	public void setImagePOList(List<ProductImagePO> imagePOList) {
		this.imagePOList = imagePOList;
	}
	
	

}
