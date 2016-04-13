/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po;

import java.io.Serializable;

/**店铺
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-26     Created
 *
 * </pre>
 * @since 1.
 */

public class ProductStoresPO implements Serializable {
	private static final long serialVersionUID = 3899179882743926986L;
	/**
     * 店铺id
     */
    Long storeId;
    /**
     * 店铺名称
     */
    String storeName;
    /**
     * 店主名称
     */
    String ownerName;
    /**
     * 店铺关键字
     */
    String keyword;
    /**
     * 店铺描述
     */
    String description;
    /**
     * 店铺图片地址
     */
    String img;
    /**
     * 店铺图片缩略图地址
     */
    String thumb;
    public Long getStoreId() {
        return storeId;
    }
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getThumb() {
        return thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
