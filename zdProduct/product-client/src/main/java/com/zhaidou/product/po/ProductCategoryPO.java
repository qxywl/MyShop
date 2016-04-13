/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po;

import java.io.Serializable;

/**商品类别
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-6     Created
 *
 * </pre>
 * @since 1.
 */

public class ProductCategoryPO implements Serializable {
	private static final long serialVersionUID = 3017156034948321732L;
	/**
     * 类别id
     */
    Long categoryId;
    /**
     * 类别编号
     */
    String categoryCode;
    /**
     * 父类别id
     */
    String categoryParentId;
    /**
     * 父类别编号
     */
    String categoryParentCode;
    /**
     * 类别名称
     */
    String categoryName;
    /**
     * 类别别名
     */
    String categoryEname;
    /**
     * 类别排序号
     */
    Integer categorySortNum;
    /**
     * 类别适配图 小
     */
    String categorySmallImageUrl;
    /**
     * 类别适配图 中
     */
    String categoryMidImageUrl;
    /**
     * 类别适配图 大
     */
    String categoryLargeImageUrl;
    /**
     * 类别说明
     */
    String categoryComment;
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryParentId() {
        return categoryParentId;
    }
    public void setCategoryParentId(String categoryParentId) {
        this.categoryParentId = categoryParentId;
    }
    public String getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    public String getCategoryParentCode() {
        return categoryParentCode;
    }
    public void setCategoryParentCode(String categoryParentCode) {
        this.categoryParentCode = categoryParentCode;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryEname() {
        return categoryEname;
    }
    public void setCategoryEname(String categoryEname) {
        this.categoryEname = categoryEname;
    }
    public Integer getCategorySortNum() {
        return categorySortNum;
    }
    public void setCategorySortNum(Integer categorySortNum) {
        this.categorySortNum = categorySortNum;
    }
    public String getCategorySmallImageUrl() {
        return categorySmallImageUrl;
    }
    public void setCategorySmallImageUrl(String categorySmallImageUrl) {
        this.categorySmallImageUrl = categorySmallImageUrl;
    }
    public String getCategoryMidImageUrl() {
        return categoryMidImageUrl;
    }
    public void setCategoryMidImageUrl(String categoryMidImageUrl) {
        this.categoryMidImageUrl = categoryMidImageUrl;
    }
    public String getCategoryLargeImageUrl() {
        return categoryLargeImageUrl;
    }
    public void setCategoryLargeImageUrl(String categoryLargeImageUrl) {
        this.categoryLargeImageUrl = categoryLargeImageUrl;
    }
    public String getCategoryComment() {
        return categoryComment;
    }
    public void setCategoryComment(String categoryComment) {
        this.categoryComment = categoryComment;
    }
}
