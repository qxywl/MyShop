/*
 * 文 件 名:  ProductDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;
import java.io.Serializable;


import java.util.Collection;
import java.util.List;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long          productId;  
    private String        productName;  
    private String        productCode;   
    private String        productDesc;   
    private Long          productShelves; 
    private Long          firstShelves;  
    private String        firstShelvesName;
    
    private Long          firstShelvesTime;
    private Long          lastShelvesTime;
    private Long          downShelvesTime;
    private String        firstShelvesTimes;
    private String        lastShelvesTimes;
    private String        downShelvesTimes;
    
    private Long          lastShelves;   
    private String        lastShelvesName;   
    private Long          markUpRate; 
    private String        lastStartUpTime;
    private String        lastEndUpTime;
    private String        firstStartUpTime;
    private String        firstEndUpTime;
    private String        startDownTime;
    private String        endDownTime;
    private Long          downShelves;
   
    private String        downShelvesName;  
    private Double        costPrice;
    private Double        tshPrice;  
    private Long          tb; 
    private Double        marketPrice;
    private String        brandCode;
    private String        brandName;
    private String        catCode;  
    private String        catName; 
    private String        type;
    private Long          staus;
    private String        reason; 
    private String        mainPic;
    private ProductOperateModel productOperateModel;
    private ProductShelvesModel productShelvesModel;
    private Collection<ProductSkuModel> productSkuList;
    private List<ProductAttributeModel> productAttributeList;
    private ProductInfoModel productInfoModel;
    private ProductAirModel productAirModel;
    private ProductHotelModel productHotelModel;
    private ProductFlowerModel productFlowerModel;
    private ProductMallModel productMallModel;
    private Long supplierId; 
    private Long shopId;
    private Integer integrity;
    private String integrityDesc; 
    private Double priceRate;  
    private String supplierName;
    private String catName1;
	private String catName2;
	
	
   
	public Long getFirstShelvesTime() {
		return firstShelvesTime;
	}
	public void setFirstShelvesTime(Long firstShelvesTime) {
		this.firstShelvesTime = firstShelvesTime;
	}
	public Long getLastShelvesTime() {
		return lastShelvesTime;
	}
	public void setLastShelvesTime(Long lastShelvesTime) {
		this.lastShelvesTime = lastShelvesTime;
	}
	public Long getDownShelvesTime() {
		return downShelvesTime;
	}
	public void setDownShelvesTime(Long downShelvesTime) {
		this.downShelvesTime = downShelvesTime;
	}
	public String getFirstShelvesTimes() {
		return firstShelvesTimes;
	}
	public void setFirstShelvesTimes(String firstShelvesTimes) {
		this.firstShelvesTimes = firstShelvesTimes;
	}
	public String getLastShelvesTimes() {
		return lastShelvesTimes;
	}
	public void setLastShelvesTimes(String lastShelvesTimes) {
		this.lastShelvesTimes = lastShelvesTimes;
	}
	public String getDownShelvesTimes() {
		return downShelvesTimes;
	}
	public void setDownShelvesTimes(String downShelvesTimes) {
		this.downShelvesTimes = downShelvesTimes;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public Long getProductShelves() {
		return productShelves;
	}
	public void setProductShelves(Long productShelves) {
		this.productShelves = productShelves;
	}
	public Long getFirstShelves() {
		return firstShelves;
	}
	public void setFirstShelves(Long firstShelves) {
		this.firstShelves = firstShelves;
	}
	public String getFirstShelvesName() {
		return firstShelvesName;
	}
	public void setFirstShelvesName(String firstShelvesName) {
		this.firstShelvesName = firstShelvesName;
	}

	public Long getLastShelves() {
		return lastShelves;
	}
	public void setLastShelves(Long lastShelves) {
		this.lastShelves = lastShelves;
	}
	public String getLastShelvesName() {
		return lastShelvesName;
	}
	public void setLastShelvesName(String lastShelvesName) {
		this.lastShelvesName = lastShelvesName;
	}
	public Long getMarkUpRate() {
		return markUpRate;
	}
	public void setMarkUpRate(Long markUpRate) {
		this.markUpRate = markUpRate;
	}
	public String getLastStartUpTime() {
		return lastStartUpTime;
	}
	public void setLastStartUpTime(String lastStartUpTime) {
		this.lastStartUpTime = lastStartUpTime;
	}
	public String getLastEndUpTime() {
		return lastEndUpTime;
	}
	public void setLastEndUpTime(String lastEndUpTime) {
		this.lastEndUpTime = lastEndUpTime;
	}
	public String getFirstStartUpTime() {
		return firstStartUpTime;
	}
	public void setFirstStartUpTime(String firstStartUpTime) {
		this.firstStartUpTime = firstStartUpTime;
	}
	public String getFirstEndUpTime() {
		return firstEndUpTime;
	}
	public void setFirstEndUpTime(String firstEndUpTime) {
		this.firstEndUpTime = firstEndUpTime;
	}
	public String getStartDownTime() {
		return startDownTime;
	}
	public void setStartDownTime(String startDownTime) {
		this.startDownTime = startDownTime;
	}
	public String getEndDownTime() {
		return endDownTime;
	}
	public void setEndDownTime(String endDownTime) {
		this.endDownTime = endDownTime;
	}
	public Long getDownShelves() {
		return downShelves;
	}
	public void setDownShelves(Long downShelves) {
		this.downShelves = downShelves;
	}
	
	public String getDownShelvesName() {
		return downShelvesName;
	}
	public void setDownShelvesName(String downShelvesName) {
		this.downShelvesName = downShelvesName;
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
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
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
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getStaus() {
		return staus;
	}
	public void setStaus(Long staus) {
		this.staus = staus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public ProductOperateModel getProductOperateModel() {
		return productOperateModel;
	}
	public void setProductOperateModel(ProductOperateModel productOperateModel) {
		this.productOperateModel = productOperateModel;
	}
	public ProductShelvesModel getProductShelvesModel() {
		return productShelvesModel;
	}
	public void setProductShelvesModel(ProductShelvesModel productShelvesModel) {
		this.productShelvesModel = productShelvesModel;
	}
	public Collection<ProductSkuModel> getProductSkuList() {
		return productSkuList;
	}
	public void setProductSkuList(Collection<ProductSkuModel> productSkuList) {
		this.productSkuList = productSkuList;
	}
	public List<ProductAttributeModel> getProductAttributeList() {
		return productAttributeList;
	}
	public void setProductAttributeList(
			List<ProductAttributeModel> productAttributeList) {
		this.productAttributeList = productAttributeList;
	}
	public ProductInfoModel getProductInfoModel() {
		return productInfoModel;
	}
	public void setProductInfoModel(ProductInfoModel productInfoModel) {
		this.productInfoModel = productInfoModel;
	}
	public ProductAirModel getProductAirModel() {
		return productAirModel;
	}
	public void setProductAirModel(ProductAirModel productAirModel) {
		this.productAirModel = productAirModel;
	}
	public ProductHotelModel getProductHotelModel() {
		return productHotelModel;
	}
	public void setProductHotelModel(ProductHotelModel productHotelModel) {
		this.productHotelModel = productHotelModel;
	}
	public ProductFlowerModel getProductFlowerModel() {
		return productFlowerModel;
	}
	public void setProductFlowerModel(ProductFlowerModel productFlowerModel) {
		this.productFlowerModel = productFlowerModel;
	}
	public ProductMallModel getProductMallModel() {
		return productMallModel;
	}
	public void setProductMallModel(ProductMallModel productMallModel) {
		this.productMallModel = productMallModel;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Integer getIntegrity() {
		return integrity;
	}
	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}
	public String getIntegrityDesc() {
		return integrityDesc;
	}
	public void setIntegrityDesc(String integrityDesc) {
		this.integrityDesc = integrityDesc;
	}
	public Double getPriceRate() {
		return priceRate;
	}
	public void setPriceRate(Double priceRate) {
		this.priceRate = priceRate;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCatName1() {
		return catName1;
	}
	public void setCatName1(String catName1) {
		this.catName1 = catName1;
	}
	public String getCatName2() {
		return catName2;
	}
	public void setCatName2(String catName2) {
		this.catName2 = catName2;
	}
	public ProductModel(){}
    public ProductModel(Long productId) {
        super();
        this.productId = productId;
    }
	

}
