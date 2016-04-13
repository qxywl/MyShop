/*
 * 文 件 名:  ProductPriceDTO.java
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
public class ProductPriceModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productPriceId;

    public Long getProductPriceId()
    {
        return productPriceId;
    }

    public void setProductPriceId(Long productPriceId)
    {
        this.productPriceId = productPriceId;
    }
    private Long        productSkuId;
    private Long productId;
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
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
    private Double        costPrice;

    public Double getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(Double costPrice)
    {
        this.costPrice = costPrice;
    }
    private Double        tshPrice;

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
    private Double        marketPrice;

    public Double getMarketPrice()
    {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice)
    {
        this.marketPrice = marketPrice;
    }
    
    private Double  zeroPrice;

	public Double getZeroPrice() {
		return zeroPrice;
	}

	public void setZeroPrice(Double zeroPrice) {
		this.zeroPrice = zeroPrice;
	}
	
}
