/*
 * 文 件 名:  ProductPriceJobDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
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
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class ProductPriceJobModel extends AbstractBaseModel implements Serializable
{

	 /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productPriceJob;

    public Long getProductPriceJob()
    {
        return productPriceJob;
    }

    public void setProductPriceJob(Long productPriceJob)
    {
        this.productPriceJob = productPriceJob;
    }
    
    private Long        productPriceList;

    public Long getProductPriceList()
    {
        return productPriceList;
    }

    public void setProductPriceList(Long productPriceList)
    {
        this.productPriceList = productPriceList;
    }
    
    private Long        productPriceId;

    public Long getProductPriceId()
    {
        return productPriceId;
    }

    public void setProductPriceId(Long productPriceId)
    {
        this.productPriceId = productPriceId;
    }
    private String        productSkuCode;

    public String getProductSkuCode()
    {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode)
    {
        this.productSkuCode = productSkuCode;
    }
    private Long        productSkuId;

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
    private Integer        staus;

    public Integer getStaus()
    {
        return staus;
    }

    public void setStaus(Integer staus)
    {
        this.staus = staus;
    }
    
    private Long timeStart;

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }
}
