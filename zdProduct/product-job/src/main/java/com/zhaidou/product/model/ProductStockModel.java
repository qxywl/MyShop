/*
 * 文 件 名:  ProductStockDTO.java
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
public class ProductStockModel extends AbstractBaseModel implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        stockId;

    public Long getStockId()
    {
        return stockId;
    }

    public void setStockId(Long stockId)
    {
        this.stockId = stockId;
    }
    private Long        skuId;
    
    private String skuCode;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getSkuId()
    {
        return skuId;
    }

    public void setSkuId(Long skuId)
    {
        this.skuId = skuId;
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
    private Double        virtualStock;

    public Double getVirtualStock()
    {
        return virtualStock;
    }

    public void setVirtualStock(Double virtualStock)
    {
        this.virtualStock = virtualStock;
    }
    private Double        manualStock;

    public Double getManualStock()
    {
        return manualStock;
    }

    public void setManualStock(Double manualStock)
    {
        this.manualStock = manualStock;
    }
    private Double        entityStock;

    public Double getEntityStock()
    {
        return entityStock;
    }

    public void setEntityStock(Double entityStock)
    {
        this.entityStock = entityStock;
    }
    private Long        stockType;

    public Long getStockType()
    {
        return stockType;
    }

    public void setStockType(Long stockType)
    {
        this.stockType = stockType;
    }
    private Double        lockStock;

    public Double getLockStock()
    {
        return lockStock;
    }

    public void setLockStock(Double lockStock)
    {
        this.lockStock = lockStock;
    }
}
