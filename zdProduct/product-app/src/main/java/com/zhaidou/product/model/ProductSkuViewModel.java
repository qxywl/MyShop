package com.zhaidou.product.model;

import java.io.Serializable;
import java.util.List;

import com.zhaidou.product.model.base.ProductImageModel;

public class ProductSkuViewModel implements Serializable{

	private static final long serialVersionUID = -1737601079503549459L;
	
    private Long        productSkuId; //sku id编号
    private Long        productId; //产品ID
    private String      productSkuCode; //sku code
   
    private String      attrColorName; //颜色名称
    private String      attrColorValue; //颜色值
    
    private String      attrSpecName; //规格名称
    private String      attrSpecValue; //规格值
    private String      colorValueAlias; //颜色值别名
    private String      specValueAlias; //尺码值别名
    
    
    private Long        mainSku; //是否主sku  1是  0否
    private Long        ifShow ;   //是否显示 1显示，2不显示
    private Long        isAvailable;//是否可用  1 为可用  2  为不可用  默认为1
	
    private Double marketPrice;//市场价
	private Double costPrice; //成本价
	private Double tshPrice; //销售价
	private Long tb; //特币
	
	// 辅助字段
	private Double virtualStock;  //对接库存数
	private Double manualStock;  //手工虚拟库
	private Double entityStock;  //实库数
	private List<ProductImageModel> imageModelList;//图片集合
	
	
	public ProductSkuViewModel() {
		
	}


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


	public String getColorValueAlias() {
		return colorValueAlias;
	}


	public void setColorValueAlias(String colorValueAlias) {
		this.colorValueAlias = colorValueAlias;
	}


	public String getSpecValueAlias() {
		return specValueAlias;
	}


	public void setSpecValueAlias(String specValueAlias) {
		this.specValueAlias = specValueAlias;
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

	public Double getVirtualStock() {
		return virtualStock;
	}

	public void setVirtualStock(Double virtualStock) {
		this.virtualStock = virtualStock;
	}

	public Double getManualStock() {
		return manualStock;
	}

	public void setManualStock(Double manualStock) {
		this.manualStock = manualStock;
	}

	public Double getEntityStock() {
		return entityStock;
	}

	public void setEntityStock(Double entityStock) {
		this.entityStock = entityStock;
	}


	public List<ProductImageModel> getImageModelList() {
		return imageModelList;
	}


	public void setImageModelList(List<ProductImageModel> imageModelList) {
		this.imageModelList = imageModelList;
	}


	public Long getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(Long isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	

}
