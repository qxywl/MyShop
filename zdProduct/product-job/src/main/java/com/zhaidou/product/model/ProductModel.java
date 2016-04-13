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
package com.zhaidou.product.model;
import com.zhaidou.common.model.AbstractBaseModel;
import com.zhaidou.framework.util.json.JSONUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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

    /**
     * 商品id
     */
    private Long        productId;

    /**
     * 商品名称
     */
    private String        productName;

    /**
     * 商品编码
     */
    private String        productCode;

    /**
     * 商品描述
     */
    private String        productDesc;

    private Long        productShelves;

    private Long        firstShelves;

    private Double        marketPrice;

    private Double        costPrice;

    private String firstShelvesName;
    private Long        firstShelvesTime;
    private Long        lastShelves;
    private String lastShelvesName;
    private Long        lastShelvesTime;
    private Long        downShelves;
    private Long        downShelvesTime;
    private String downShelvesName;
    private Double        tshPrice;
    private String        brandCode;
    private String        brandName;
    private String        catCode;
    private String        catName;

    private String        type;
    private Long        staus;
    private String        mainPic;
    private ProductOperateModel productOperateModel;
    private ProductShelvesModel productShelvesModel;
    private Collection<ProductSkuModel> productSkuList;
    private List<ProductAttributeModel> productAttributeList;
    private ProductInfoModel productInfoModel;
//    private ProductAirModel productAirModel;
//    private ProductHotelModel productHotelModel;
//    private ProductFlowerModel productFlowerModel;
    private ProductMallModel productMallModel;
    private Long supplierId;
    private Long shopId;
    private Integer integrity;
    private String integrityDesc;
    private String reason;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }


    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductDesc()
    {
        return productDesc;
    }

    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }


    public Long getProductShelves()
    {
        return productShelves;
    }

    public void setProductShelves(Long productShelves)
    {
        this.productShelves = productShelves;
    }


    public Long getFirstShelves()
    {
        return firstShelves;
    }

    public void setFirstShelves(Long firstShelves)
    {
        this.firstShelves = firstShelves;
    }

    
    public String getFirstShelvesName() {
        return firstShelvesName;
    }

    public void setFirstShelvesName(String firstShelvesName) {
        this.firstShelvesName = firstShelvesName;
    }

    
    public Long getFirstShelvesTime()
    {
        return firstShelvesTime;
    }

    public void setFirstShelvesTime(Long firstShelvesTime)
    {
        this.firstShelvesTime = firstShelvesTime;
    }


    public Long getLastShelves()
    {
        return lastShelves;
    }

    public void setLastShelves(Long lastShelves)
    {
        this.lastShelves = lastShelves;
    }

    
    public String getLastShelvesName() {
        return lastShelvesName;
    }

    public void setLastShelvesName(String lastShelvesName) {
        this.lastShelvesName = lastShelvesName;
    }


    public Long getLastShelvesTime()
    {
        return lastShelvesTime;
    }

    public void setLastShelvesTime(Long lastShelvesTime)
    {
        this.lastShelvesTime = lastShelvesTime;
    }

    
    public Long getDownShelves() {
        return downShelves;
    }

    public void setDownShelves(Long downShelves) {
        this.downShelves = downShelves;
    }

    public Long getDownShelvesTime() {
        return downShelvesTime;
    }

    public void setDownShelvesTime(Long downShelvesTime) {
        this.downShelvesTime = downShelvesTime;
    }

    public String getDownShelvesName() {
        return downShelvesName;
    }

    public void setDownShelvesName(String downShelvesName) {
        this.downShelvesName = downShelvesName;
    }


    public Double getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(Double costPrice)
    {
        this.costPrice = costPrice;
    }

    public Double getTshPrice()
    {
        return tshPrice;
    }

    public void setTshPrice(Double tshPrice)
    {
        this.tshPrice = tshPrice;
    }
    private Long        tb;

    public Long getTb()
    {
        return tb;
    }

    public void setTb(Long tb)
    {
        this.tb = tb;
    }


    public Double getMarketPrice()
    {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice)
    {
        this.marketPrice = marketPrice;
    }



    public String getBrandCode()
    {
        return brandCode;
    }

    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }



    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }



    public String getCatCode()
    {
        return catCode;
    }

    public void setCatCode(String catCode)
    {
        this.catCode = catCode;
    }


    public String getCatName()
    {
        return catName;
    }

    public void setCatName(String catName)
    {
        this.catName = catName;
    }


    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Long getStaus()
    {
        return staus;
    }

    public void setStaus(Long staus)
    {
        this.staus = staus;
    }


    public String getMainPic()
    {
        return mainPic;
    }

    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }

    
    public ProductInfoModel getProductInfoModel() {
        return productInfoModel;
    }

    public void setProductInfoModel(ProductInfoModel productInfoModel) {
        this.productInfoModel = productInfoModel;
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

    public void setProductSkuList(Collection<ProductSkuModel> productSkuList) {
        this.productSkuList = productSkuList;
    }
    
    public Collection<ProductSkuModel> getProductSkuList() {
        return productSkuList;
    }

    public List<ProductAttributeModel> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttributeModel> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

//    public ProductAirModel getProductAirModel() {
//        return productAirModel;
//    }
//
//    public void setProductAirModel(ProductAirModel productAirModel) {
//        this.productAirModel = productAirModel;
//    }
//
//    public ProductHotelModel getProductHotelModel() {
//        return productHotelModel;
//    }
//
//    public void setProductHotelModel(ProductHotelModel productHotelModel) {
//        this.productHotelModel = productHotelModel;
//    }
//
//    public ProductFlowerModel getProductFlowerModel() {
//        return productFlowerModel;
//    }
//
//    public void setProductFlowerModel(ProductFlowerModel productFlowerModel) {
//        this.productFlowerModel = productFlowerModel;
//    }

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

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public ProductModel(){}
    public ProductModel(Long productId) {
        super();
        this.productId = productId;
    }
    
  public ProductModel(String productCode) {
        super();
        this.productCode = productCode;
    }

public static void main(String[] args) {
      String str = "{\"productName\":\"好看内衣文胸\",\"productDesc\":\"好看内衣文胸\",\"costPrice\":50.0,\"marketPrice\":90.0,\"brandCode\":\"CN04135\",\"brandName\":\"55度\",\"catCode\":\"320102\",\"catName\":\"å¤§ç æè¸\",\"productSkuList\":[{\"attrColorValue\":\"\",\"attrSpecValue\":\"\",\"productPriceModel\":{\"costPrice\":40.0,\"marketPrice\":80.0},\"productStockModel\":{\"entityStock\":100.0}},{\"attrColorValue\":\"\",\"attrSpecValue\":\"\",\"productPriceModel\":{\"costPrice\":30.0,\"marketPrice\":70.0},\"productStockModel\":{\"entityStock\":50.0}}],\"productAttributeList\":[{\"productAttributeName\":\"颜色444\",\"productAttributeCode\":\"ACETU57183147\",\"productAttributeValue\":\"红,白,绿,紫,df\",\"type\":\"1\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"222\",\"productAttributeCode\":\"ACETU57415914\",\"productAttributeValue\":\"\",\"type\":\"2\"},{\"productAttributeName\":\"111444\",\"productAttributeCode\":\"ACETU58111099\",\"productAttributeValue\":\"超重,很重\",\"type\":\"1\"}],\"productInfoModel\":{\"pcProductInfo\":\"阿萨德发送到Afghanistan\\t\\t\\t\\t\\t\\t\\t\",\"appProductInfo\":\"阿道夫广东佛山个斯蒂芬股市大幅敢死队\\t\\t\\t\\t\\t\\t\"}}";
    ProductModel productModel = JSONUtil.toModel(str, ProductModel.class);
    System.out.println(productModel.getProductSkuList().iterator().next().getProductPriceModel().getCostPrice());
  }
}
