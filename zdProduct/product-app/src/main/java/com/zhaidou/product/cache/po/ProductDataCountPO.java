package com.zhaidou.product.cache.po;

import java.io.Serializable;

import com.zhaidou.framework.util.date.DatetimeFormat;
import com.zhaidou.framework.util.date.DatetimeUtil;

public class ProductDataCountPO implements Serializable{
    private static final long serialVersionUID = -2957552373330639974L;
    /** 唯一标识 */
    private Long id;
    /** 业务类型 */
    private String businessType;
    /** 商品标识 */
    private String productId;
    /** 统计的数据类型,如10浏览量，20收藏统计数，30加入购物车统计数，40下单统计数，50销量，60评论统计数，70点赞统计数,80喜欢统计数等 */
    private String countType;
    /** 统计数据值 */
    private Long countValue;
    /** 创建时间 */
    private String createdTime;
    /** 更新时间 */
    private String updatedTime;
    public ProductDataCountPO() {
    }
    public ProductDataCountPO(String businessType, String productId, String countType) {
        this.businessType = businessType;
        this.productId = productId;
        this.countType = countType;
        this.createdTime = DatetimeUtil.longToDateTimeString(System.currentTimeMillis(), DatetimeFormat.STANDARED_DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "ProductDataCountModel [id=" + id + ", businessType=" + businessType + ", productId=" + productId
                + ", countType=" + countType + ", countValue=" + countValue + ", createdTime=" + createdTime
                + ", updatedTime=" + updatedTime + "]";
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getCountType() {
        return countType;
    }
    public void setCountType(String countType) {
        this.countType = countType;
    }
    public Long getCountValue() {
        return countValue;
    }
    public void setCountValue(Long countValue) {
        this.countValue = countValue;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    public String getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
    
}
