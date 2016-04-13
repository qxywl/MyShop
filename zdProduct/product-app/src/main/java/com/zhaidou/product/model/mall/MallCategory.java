/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.model.mall;

import java.util.List;


/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-14     Created
 *
 * </pre>
 * @since 1.
 */

public class MallCategory{
    /**
     * 类别id
     */
    Long cate_id;
    /**
     * 父类别id
     */
    Long parent_id;
    /**
     * 类别名称
     */
    String cate_name;
    /**
     * 分类图标地址
     */
    String gcategory_logo;
    /**
     * 最小尺寸的缩略图
     */
    String thumbnail_small;
    /**
     * 一般尺寸的缩略图
     */
    String thumbnail_middle;
    /**
     * 正方形图片
     */
    String thumbnail_tetragonal;
    /**
     * 正方形图片2
     */
    String thumbnail_tetragonal2;
    /**
     * 摘要说明
     */
    String brief;
    /**
     * 子类别
     */
    List<SubMallProductCategory> subcategories;
    public List<SubMallProductCategory> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(List<SubMallProductCategory> subcategories) {
        this.subcategories = subcategories;
    }
    public Long getCate_id() {
        return cate_id;
    }
    public void setCate_id(Long cate_id) {
        this.cate_id = cate_id;
    }
    public Long getParent_id() {
        return parent_id;
    }
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
    public String getCate_name() {
        return cate_name;
    }
    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
    public String getGcategory_logo() {
        return gcategory_logo;
    }
    public void setGcategory_logo(String gcategory_logo) {
        this.gcategory_logo = gcategory_logo;
    }
    public String getThumbnail_small() {
        return thumbnail_small;
    }
    public void setThumbnail_small(String thumbnail_small) {
        this.thumbnail_small = thumbnail_small;
    }
    public String getThumbnail_middle() {
        return thumbnail_middle;
    }
    public void setThumbnail_middle(String thumbnail_middle) {
        this.thumbnail_middle = thumbnail_middle;
    }
    public String getThumbnail_tetragonal() {
        return thumbnail_tetragonal;
    }
    public void setThumbnail_tetragonal(String thumbnail_tetragonal) {
        this.thumbnail_tetragonal = thumbnail_tetragonal;
    }
    public String getThumbnail_tetragonal2() {
        return thumbnail_tetragonal2;
    }
    public void setThumbnail_tetragonal2(String thumbnail_tetragonal2) {
        this.thumbnail_tetragonal2 = thumbnail_tetragonal2;
    }
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
}