/*
 * 文 件 名:  ProductSkuDTO.java
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
public class ProductSkuModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productSkuId;
    private String productSkuCode;


    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public Long getProductSkuId()
    {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId)
    {
        this.productSkuId = productSkuId;
    }
    private Long        productId;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    private String        attrColorName;

    public String getAttrColorName()
    {
        return attrColorName;
    }

    public void setAttrColorName(String attrColorName)
    {
        this.attrColorName = attrColorName;
    }
    private String        attrColorValue;

    public String getAttrColorValue()
    {
        return attrColorValue;
    }

    public void setAttrColorValue(String attrColorValue)
    {
        this.attrColorValue = attrColorValue;
    }
    private String        attrSpecName;

    public String getAttrSpecName()
    {
        return attrSpecName;
    }

    public void setAttrSpecName(String attrSpecName)
    {
        this.attrSpecName = attrSpecName;
    }
    private String        attrSpecValue;

    public String getAttrSpecValue()
    {
        return attrSpecValue;
    }

    public void setAttrSpecValue(String attrSpecValue)
    {
        this.attrSpecValue = attrSpecValue;
    }
    private Long        mainSku;

    public Long getMainSku()
    {
        return mainSku;
    }

    public void setMainSku(Long mainSku)
    {
        this.mainSku = mainSku;
    }
    
    private List<ProductImageModel> productImagerList;
    
    private ProductPriceModel productPriceModel;
    
    private ProductStockModel productStockModel;
    
    public ProductStockModel getProductStockModel() {
        return productStockModel;
    }

    public void setProductStockModel(ProductStockModel productStockModel) {
        this.productStockModel = productStockModel;
    }

    public ProductPriceModel getProductPriceModel() {
        return productPriceModel;
    }

    public void setProductPriceModel(ProductPriceModel productPriceModel) {
        this.productPriceModel = productPriceModel;
    }

    public List<ProductImageModel> getProductImagerList() {
        return productImagerList;
    }

    public void setProductImagerList(List<ProductImageModel> productImagerList) {
        this.productImagerList = productImagerList;
    }
    
    private String colorValueAlias;
    private String specValueAlias;
    
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
    
    private Integer status;
    private String reason;
    private Integer isAvailable;
    private String supplierSkuCode;

    public String getSupplierSkuCode() {
        return supplierSkuCode;
    }

    public void setSupplierSkuCode(String supplierSkuCode) {
        this.supplierSkuCode = supplierSkuCode;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ProductSkuModel(){}
    public ProductSkuModel(Long productSkuId) {
        super();
        this.productSkuId = productSkuId;
    }
    
    
}
