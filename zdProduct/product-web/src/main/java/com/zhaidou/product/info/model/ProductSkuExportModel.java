package com.zhaidou.product.info.model;

public class ProductSkuExportModel {
    
    private Long productId;
	private Long productSkuId;
	private String catName;
	private String catCode;
	private String catName1;  //一级目录名字
	private String catCode1;   // 一级目录编码
	private String catName2;  //二级目录名字
	private String catCode2;
	private Long shopId;
	private String shopName;
	private String supplierName;
	private String brandCode;
	private String brandName;
	private String productSkuCode;
	private String productCode;
	private String productName;
	private String type;
	private String marketPrice;
	private Double tshPrice;
	private Double maxTshPrice;
	private Double minTshPrice;
	private String tb;
	private String costPrice;
	private String status;
	private String productShelves;
	private String createTime;
	private String firstShelvesTime;
	private String virtualStock;
	private String productWeight;
	private Long supplierId;
	private String createTimes;
	private Long endTime;
	private Long startTime;
	private Double priceRate;
	
	public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Double priceRate) {
        this.priceRate = priceRate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

	public String getCatName1() {
		return catName1;
	}

	public void setCatName1(String catName1) {
		this.catName1 = catName1;
	}

	public String getCatCode1() {
		return catCode1;
	}

	public void setCatCode1(String catCode1) {
		this.catCode1 = catCode1;
	}

	public String getCatName2() {
		return catName2;
	}

	public void setCatName2(String catName2) {
		this.catName2 = catName2;
	}

	public String getCatCode2() {
		return catCode2;
	}

	public void setCatCode2(String catCode2) {
		this.catCode2 = catCode2;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Double getTshPrice() {
		return tshPrice;
	}

	public void setTshPrice(Double tshPrice) {
		this.tshPrice = tshPrice;
	}

	public Double getMaxTshPrice() {
		return maxTshPrice;
	}

	public void setMaxTshPrice(Double maxTshPrice) {
		this.maxTshPrice = maxTshPrice;
	}

	public Double getMinTshPrice() {
		return minTshPrice;
	}

	public void setMinTshPrice(Double minTshPrice) {
		this.minTshPrice = minTshPrice;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductSkuCode() {
		return productSkuCode;
	}

	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getTb() {
		return tb;
	}

	public void setTb(String tb) {
		this.tb = tb;
	}

	public String getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFirstShelvesTime() {
		return firstShelvesTime;
	}

	public void setFirstShelvesTime(String firstShelvesTime) {
		this.firstShelvesTime = firstShelvesTime;
	}

	public String getVirtualStock() {
		return virtualStock;
	}

	public void setVirtualStock(String virtualStock) {
		this.virtualStock = virtualStock;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public Long getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProductShelves() {
		return productShelves;
	}

	public void setProductShelves(String productShelves) {
		this.productShelves = productShelves;
	}

}
