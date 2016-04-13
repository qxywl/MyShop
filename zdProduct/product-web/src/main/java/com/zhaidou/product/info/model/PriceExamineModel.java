package com.zhaidou.product.info.model;

import java.io.Serializable;

import com.zhaidou.framework.model.BasePO;

/**
 * 商品价格审核
 * TODO Administrator: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2015年3月23日     Created
 *
 * </pre>
 * @since 1.
 */
@SuppressWarnings("serial")
public class PriceExamineModel extends BasePO implements Serializable {
//    t_product_price_list_id     int 主键
//    t_product_price_id  产品价格 ID int 
//    t_product_sku_id    产品SKU ID    int 
//    costprice   成本价 double  
//    tshtprice   销售价 double  
//    marketprice 市场价 double  
//    tb  特币  int 
//    status  状态  int 
    private Integer productPriceListId;
    private Integer productPriceId;
    private Integer productSkuId;
    private Double costPrice;
    private Double tshPrice;
    private Double marketPrice;
    private Integer tb;
    private Integer status;
    
    public Integer getProductPriceListId() {
        return productPriceListId;
    }
    public void setProductPriceListId(Integer productPriceListId) {
        this.productPriceListId = productPriceListId;
    }
    public Integer getProductPriceId() {
        return productPriceId;
    }
    public void setProductPriceId(Integer productPriceId) {
        this.productPriceId = productPriceId;
    }
    public Integer getProductSkuId() {
        return productSkuId;
    }
    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
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
    public Double getMarketPrice() {
        return marketPrice;
    }
    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }
    public Integer getTb() {
        return tb;
    }
    public void setTb(Integer tb) {
        this.tb = tb;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
