/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.po.mall;

import java.io.Serializable;

/**
 * TODO liq: Change to the actual description of this class
 * 
 * @version Revision History
 * 
 *          <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-20     Created
 * 
 * </pre>
 * @since 1.
 */

public class MallProductInfoPO implements Serializable {
	private static final long serialVersionUID = 729743659974090869L;
	
	private String productVideoUrl;
	private String productVideoThumbnail;
	private String isSupportExpensive;
	
    String  defaultImage;   // 商品显示的图片
    String  thumbnailSmall; // 商品列表小图片
    String  thumbnailMiddle; // 商品列表图片
  
    int     sales;           // 商品销量
    int     comments;        // 评论数
    int  views;           // 浏览量
    int collects; //收藏次数
    int carts;//加入购物车次数
    int orders;//下单次数
    int  applaudCount;   // 点赞数量
    String  praiseRate;     // 好评率
    
    Long favorCount; //喜欢数
    
    String  imqq;           //店铺qq
    String  imWw;           //店铺旺旺
    String  type;            // 商品类别 material实体，虚拟
    String  cateId1;
    String  cateId2;
    String  cateId3;
    String  cateId4;
    String  if_show;         // 是否显示
    String  addTime;        //
    String  recommended;     // 是否推荐 0否 1是
    String  auditStatus;    // 商品审核状态 0未 1已审核 2拒绝
    String  goodsType;      //
    String  colorRgb;       // 标题颜色
    String  priceRate;      // 浮动比例
    String  creditValue;    // 店铺好评率 1-5
    String  sgrade;          //
    String  optionset;       // 标记猜你喜欢类别或更多商品类别
    String  newIndex;       // 每周新品排序号，越大越前面
    String  hotIndex;       // 热门推荐排序号，同上
    String  guessIndex;     // 猜你喜欢的排序号，同上
    String  moreIndex;      // 更多商品的排序好，同上
    
    String  passTime;       // 一次审核通过时间
    String  secondPassTime; // 二次审核
    String  unpassTime;     // 审核拒绝时间
    String  yitaoUrl;       // 一淘地址
    String  commodityCode;  //
    String  pvs;             //
    
    String  creditImage;
    String  gradeName;
    String  imQqVal;
    String  imAlwwVal;
    
    private double productWeight;//重量
    private double productLong;//长
    private double productWidth;//宽
    
    private double productHeight;//高
    private String productProducer;//产地
    private double productDensity;//密度
    private String productAtrNumber;//货号
    
    
    private String column3;//扩展字段3
    private String column4;//扩展字段4
    
    private Integer  userMaxNum;//用户可以购买数据特卖SKU
    
    private Integer  userMaxType;//用户可以购买数据特卖SKU时间段

    private Integer zeroMaxCount;//
    
    private String productShortName;//产品短名称
    
    private String productPrefix;//前缀
   
    private String productSuffix;//后缀
    
    
	public String getProductShortName() {
		return productShortName;
	}
	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}
	public String getProductPrefix() {
		return productPrefix;
	}
	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}
	public String getProductSuffix() {
		return productSuffix;
	}
	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}
	public Integer getUserMaxNum() {
		return userMaxNum;
	}
	public void setUserMaxNum(Integer userMaxNum) {
		this.userMaxNum = userMaxNum;
	}
	public Integer getUserMaxType() {
		return userMaxType;
	}
	public void setUserMaxType(Integer userMaxType) {
		this.userMaxType = userMaxType;
	}
	public Integer getZeroMaxCount() {
		return zeroMaxCount;
	}
	public void setZeroMaxCount(Integer zeroMaxCount) {
		this.zeroMaxCount = zeroMaxCount;
	}
	public int getSales() {
        return sales;
    }
    public void setSales(int sales) {
        this.sales = sales;
    }
    public String getDefaultImage() {
        return defaultImage;
    }
    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }
    public String getThumbnailSmall() {
        return thumbnailSmall;
    }
    public void setThumbnailSmall(String thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }
    public String getThumbnailMiddle() {
        return thumbnailMiddle;
    }
    public void setThumbnailMiddle(String thumbnailMiddle) {
        this.thumbnailMiddle = thumbnailMiddle;
    }
    public int getComments() {
        return comments;
    }
    public void setComments(int comments) {
        this.comments = comments;
    }
    
    public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getCollects() {
		return collects;
	}
	public void setCollects(int collects) {
		this.collects = collects;
	}
	public int getCarts() {
		return carts;
	}
	public void setCarts(int carts) {
		this.carts = carts;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getApplaudCount() {
		return applaudCount;
	}
	public void setApplaudCount(int applaudCount) {
		this.applaudCount = applaudCount;
	}
	public String getPraiseRate() {
        return praiseRate;
    }
    public void setPraiseRate(String praiseRate) {
        this.praiseRate = praiseRate;
    }
    public String getImqq() {
        return imqq;
    }
    public void setImqq(String imqq) {
        this.imqq = imqq;
    }
    public String getImWw() {
        return imWw;
    }
    public void setImWw(String imWw) {
        this.imWw = imWw;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCateId1() {
        return cateId1;
    }
    public void setCateId1(String cateId1) {
        this.cateId1 = cateId1;
    }
    public String getCateId2() {
        return cateId2;
    }
    public void setCateId2(String cateId2) {
        this.cateId2 = cateId2;
    }
    public String getCateId3() {
        return cateId3;
    }
    public void setCateId3(String cateId3) {
        this.cateId3 = cateId3;
    }
    public String getCateId4() {
        return cateId4;
    }
    public void setCateId4(String cateId4) {
        this.cateId4 = cateId4;
    }
    public String getIf_show() {
        return if_show;
    }
    public void setIf_show(String if_show) {
        this.if_show = if_show;
    }
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
    public String getRecommended() {
        return recommended;
    }
    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }
    public String getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public String getColorRgb() {
        return colorRgb;
    }
    public void setColorRgb(String colorRgb) {
        this.colorRgb = colorRgb;
    }
    public String getPriceRate() {
        return priceRate;
    }
    public void setPriceRate(String priceRate) {
        this.priceRate = priceRate;
    }
    public String getCreditValue() {
        return creditValue;
    }
    public void setCreditValue(String creditValue) {
        this.creditValue = creditValue;
    }
    public String getSgrade() {
        return sgrade;
    }
    public void setSgrade(String sgrade) {
        this.sgrade = sgrade;
    }
    public String getOptionset() {
        return optionset;
    }
    public void setOptionset(String optionset) {
        this.optionset = optionset;
    }
    public String getNewIndex() {
        return newIndex;
    }
    public void setNewIndex(String newIndex) {
        this.newIndex = newIndex;
    }
    public String getHotIndex() {
        return hotIndex;
    }
    public void setHotIndex(String hotIndex) {
        this.hotIndex = hotIndex;
    }
    public String getGuessIndex() {
        return guessIndex;
    }
    public void setGuessIndex(String guessIndex) {
        this.guessIndex = guessIndex;
    }
    public String getMoreIndex() {
        return moreIndex;
    }
    public void setMoreIndex(String moreIndex) {
        this.moreIndex = moreIndex;
    }
   
    public String getPassTime() {
        return passTime;
    }
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }
    public String getSecondPassTime() {
        return secondPassTime;
    }
    public void setSecondPassTime(String secondPassTime) {
        this.secondPassTime = secondPassTime;
    }
    public String getUnpassTime() {
        return unpassTime;
    }
    public void setUnpassTime(String unpassTime) {
        this.unpassTime = unpassTime;
    }
    public String getYitaoUrl() {
        return yitaoUrl;
    }
    public void setYitaoUrl(String yitaoUrl) {
        this.yitaoUrl = yitaoUrl;
    }
    public String getCommodityCode() {
        return commodityCode;
    }
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }
    public String getPvs() {
        return pvs;
    }
    public void setPvs(String pvs) {
        this.pvs = pvs;
    }
   
    public String getCreditImage() {
        return creditImage;
    }
    public void setCreditImage(String creditImage) {
        this.creditImage = creditImage;
    }
    public String getGradeName() {
        return gradeName;
    }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    public String getImQqVal() {
        return imQqVal;
    }
    public void setImQqVal(String imQqVal) {
        this.imQqVal = imQqVal;
    }
    public String getImAlwwVal() {
        return imAlwwVal;
    }
    public void setImAlwwVal(String imAlwwVal) {
        this.imAlwwVal = imAlwwVal;
    }
    public String getProductVideoUrl() {
        return productVideoUrl;
    }
    public void setProductVideoUrl(String productVideoUrl) {
        this.productVideoUrl = productVideoUrl;
    }
    public String getProductVideoThumbnail() {
        return productVideoThumbnail;
    }
    public void setProductVideoThumbnail(String productVideoThumbnail) {
        this.productVideoThumbnail = productVideoThumbnail;
    }
    public String getIsSupportExpensive() {
        return isSupportExpensive;
    }
    public void setIsSupportExpensive(String isSupportExpensive) {
        this.isSupportExpensive = isSupportExpensive;
    }
	public double getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}
	public double getProductLong() {
		return productLong;
	}
	public void setProductLong(double productLong) {
		this.productLong = productLong;
	}
	public double getProductWidth() {
		return productWidth;
	}
	public void setProductWidth(double productWidth) {
		this.productWidth = productWidth;
	}
	public double getProductHeight() {
		return productHeight;
	}
	public void setProductHeight(double productHeight) {
		this.productHeight = productHeight;
	}
	public String getProductProducer() {
		return productProducer;
	}
	public void setProductProducer(String productProducer) {
		this.productProducer = productProducer;
	}
	public double getProductDensity() {
		return productDensity;
	}
	public void setProductDensity(double productDensity) {
		this.productDensity = productDensity;
	}
	public String getProductAtrNumber() {
		return productAtrNumber;
	}
	public void setProductAtrNumber(String productAtrNumber) {
		this.productAtrNumber = productAtrNumber;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn4() {
		return column4;
	}
	public void setColumn4(String column4) {
		this.column4 = column4;
	}
    public Long getFavorCount() {
        return favorCount;
    }
    public void setFavorCount(Long favorCount) {
        this.favorCount = favorCount;
    }
    
}
