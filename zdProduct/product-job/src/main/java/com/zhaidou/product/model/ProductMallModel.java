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
package com.zhaidou.product.model;

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
    
    private double productWeight;
    private double productLong;
    private double productWidth;
    private double productHeight;
    private double productDensity;
    private String productProducer;
    private String productAtrNumber;

    public String getProductAtrNumber() {
        return productAtrNumber;
    }

    public void setProductAtrNumber(String productAtrNumber) {
        this.productAtrNumber = productAtrNumber;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public double getProductLong() {
        return productLong;
    }

    public void setProductLong(double productLong) {
        this.productLong = productLong;
    }

    public double getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(double productWidth) {
        this.productWidth = productWidth;
    }

    public double getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(double productHeight) {
        this.productHeight = productHeight;
    }

    public double getProductDensity() {
        return productDensity;
    }

    public void setProductDensity(double productDensity) {
        this.productDensity = productDensity;
    }

    public String getProductProducer() {
        return productProducer;
    }

    public void setProductProducer(String productProducer) {
        this.productProducer = productProducer;
    }
    
}
