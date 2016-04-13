/*
 * 文 件 名:  ProductOperateDTO.java
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
public class ProductOperateModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productOperateId;

    public Long getProductOperateId()
    {
        return productOperateId;
    }

    public void setProductOperateId(Long productOperateId)
    {
        this.productOperateId = productOperateId;
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
    private String        productShortName;

    public String getProductShortName()
    {
        return productShortName;
    }

    public void setProductShortName(String productShortName)
    {
        this.productShortName = productShortName;
    }
    private String        productPrefix;

    public String getProductPrefix()
    {
        return productPrefix;
    }

    public void setProductPrefix(String productPrefix)
    {
        this.productPrefix = productPrefix;
    }
    private String        productSuffix;

    public String getProductSuffix()
    {
        return productSuffix;
    }

    public void setProductSuffix(String productSuffix)
    {
        this.productSuffix = productSuffix;
    }
    private String        productKeyword;

    public String getProductKeyword()
    {
        return productKeyword;
    }

    public void setProductKeyword(String productKeyword)
    {
        this.productKeyword = productKeyword;
    }
    private Long        productDownShow;

    public Long getProductDownShow()
    {
        return productDownShow;
    }

    public void setProductDownShow(Long productDownShow)
    {
        this.productDownShow = productDownShow;
    }
    private Long        productAutoUp;

    public Long getProductAutoUp()
    {
        return productAutoUp;
    }

    public void setProductAutoUp(Long productAutoUp)
    {
        this.productAutoUp = productAutoUp;
    }
    private String        productSizeCompare;

    public String getProductSizeCompare()
    {
        return productSizeCompare;
    }

    public void setProductSizeCompare(String productSizeCompare)
    {
        this.productSizeCompare = productSizeCompare;
    }
    private String productCatCode;
    private String productCatName;
    private Double productPriceRate;

    public Double getProductPriceRate() {
        return productPriceRate;
    }

    public void setProductPriceRate(Double productPriceRate) {
        this.productPriceRate = productPriceRate;
    }

    public String getProductCatCode() {
        return productCatCode;
    }

    public void setProductCatCode(String productCatCode) {
        this.productCatCode = productCatCode;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }
    
    private String productVideoUrl;             //视频路径
    private String productThumbnail;            //缩略图路径
    private Integer isExpensive;                //是否支持贵就赔  1  支持   2   不支持

    public String getProductVideoUrl() {
        return productVideoUrl;
    }

    public void setProductVideoUrl(String productVideoUrl) {
        this.productVideoUrl = productVideoUrl;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public Integer getIsExpensive() {
        return isExpensive;
    }

    public void setIsExpensive(Integer isExpensive) {
        this.isExpensive = isExpensive;
    }
}
