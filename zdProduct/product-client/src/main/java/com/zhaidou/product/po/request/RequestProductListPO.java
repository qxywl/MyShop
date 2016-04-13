/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po.request;

import java.io.Serializable;
import java.util.List;

import com.zhaidou.framework.util.reflect.EmptyShowAnnotation;


/**产品列表请求对象
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-19     Created
 *
 * </pre>
 * @since 1.
 */

public class RequestProductListPO implements Serializable{
    private static final long serialVersionUID = -1420389092306809640L;
    /**
     * 查询页码
     */
    @EmptyShowAnnotation
    Integer page;
    /**
     * 每页数量
     */
    @EmptyShowAnnotation
    Integer pageSize;
    /**
     * 品牌code 支持多品牌查询 并集
     */
    List<String> brandCode;
    /**
     * 分类级别
     */
    String categoryLevel;
    /**
     * 分类code
     */
    @EmptyShowAnnotation
    String categoryCode;
    /**
     * 地区
     */
    String origin;
    /**
     * 店铺编码
     */
    String storeCode;
    /**
     * 最低价
     */
    String lowestPrice;
    /**
     * 最高价
     */
    String highestPrice;
    /**
     * 排序字段
     */
    String sort;
    /**
     * 升降排序:desc/asc
     */
    String orderBy;
    /**
     * 关键字 支持多关键字查询 交集
     */
    List<String> keyword;
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public List<String> getBrandCode() {
        return brandCode;
    }
    public void setBrandCode(List<String> brandCode) {
        this.brandCode = brandCode;
    }
    public String getCategoryLevel() {
        return categoryLevel;
    }
    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getStoreCode() {
        return storeCode;
    }
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    public String getLowestPrice() {
        return lowestPrice;
    }
    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }
    public String getHighestPrice() {
        return highestPrice;
    }
    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }
    public List<String> getKeyword() {
        return keyword;
    }
    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
