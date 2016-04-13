/*
 * 文 件 名:  ProductMallDTO.java
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
public class ProductMallModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productMallId;

    public Long getProductMallId()
    {
        return productMallId;
    }

    public void setProductMallId(Long productMallId)
    {
        this.productMallId = productMallId;
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
    
    private Double productWeight;
    private Double productLong;
    private Double productWidth;
    private Double productHeight;
    private Double productDensity;
    private String productProducer;
    private String productAtrNumber;

    public String getProductAtrNumber() {
        return productAtrNumber;
    }

    public void setProductAtrNumber(String productAtrNumber) {
        this.productAtrNumber = productAtrNumber;
    }

    public Double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Double productWeight) {
        this.productWeight = productWeight;
    }

    public Double getProductLong() {
        return productLong;
    }

    public void setProductLong(Double productLong) {
        this.productLong = productLong;
    }

    public Double getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Double productWidth) {
        this.productWidth = productWidth;
    }

    public Double getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Double productHeight) {
        this.productHeight = productHeight;
    }

    public Double getProductDensity() {
        return productDensity;
    }

    public void setProductDensity(Double productDensity) {
        this.productDensity = productDensity;
    }

    public String getProductProducer() {
        return productProducer;
    }

    public void setProductProducer(String productProducer) {
        this.productProducer = productProducer;
    }

    public ProductMallModel() {
        super();
    }

    public ProductMallModel(Long productId) {
        super();
        this.productId = productId;
    }
    
}
