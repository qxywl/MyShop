/*
 * 文 件 名:  ProductOperateDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model.base;

import java.io.Serializable;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductOperateModel  implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productOperateId; //
    private Long        productId; //产品ID
    private String      productShortName; //产品短名称
    private String      productPrefix; //前缀
    private String      productSuffix; //后缀
    private String      productKeyword; //关键字
    private Integer     productDownShow; //是否下架显示 1是 2否
    private Integer     productAutoUp; //是否自动上架 1是 2否
    private String      productSizeCompare; //尺码对照表
    private String      productCatCode; //运营分类编码
    private String      productCatName; //运营分类名称
    private double      productPriceRate; //价格浮动比例
    private Integer     productLevel; //商品排序
    private Integer  userMaxNum;//用户可以购买数据特卖SKU
    private Integer  userMaxType;//用户可以购买数据特卖SKU时间段
    private Integer zeroMaxCount;//
    
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

	/** 商品视频URL */
    private String productVideoUrl;
    /** 商品视频缩略图 */
    private String productVideoThumbnail;
    /** 商品是否支持贵就赔 */
    private Integer isSupportExpensive;
    
    public Long getProductOperateId()
    {
        return productOperateId;
    }

    public void setProductOperateId(Long productOperateId)
    {
        this.productOperateId = productOperateId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductShortName()
    {
        return productShortName;
    }

    public void setProductShortName(String productShortName)
    {
        this.productShortName = productShortName;
    }

    public String getProductPrefix()
    {
        return productPrefix;
    }

    public void setProductPrefix(String productPrefix)
    {
        this.productPrefix = productPrefix;
    }

    public String getProductSuffix()
    {
        return productSuffix;
    }

    public void setProductSuffix(String productSuffix)
    {
        this.productSuffix = productSuffix;
    }

    public String getProductKeyword()
    {
        return productKeyword;
    }

    public void setProductKeyword(String productKeyword)
    {
        this.productKeyword = productKeyword;
    }

    public Integer getProductDownShow()
    {
        return productDownShow;
    }

    public void setProductDownShow(Integer productDownShow)
    {
        this.productDownShow = productDownShow;
    }

    public Integer getProductAutoUp()
    {
        return productAutoUp;
    }

    public void setProductAutoUp(Integer productAutoUp)
    {
        this.productAutoUp = productAutoUp;
    }

    public String getProductSizeCompare()
    {
        return productSizeCompare;
    }

    public void setProductSizeCompare(String productSizeCompare)
    {
        this.productSizeCompare = productSizeCompare;
    }

    public String getProductCatCode() {
        return productCatCode;
    }

    public void setProductCatCode(String productCatCode) {
        this.productCatCode = productCatCode;
    }

    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    public double getProductPriceRate() {
        return productPriceRate;
    }

    public void setProductPriceRate(double productPriceRate) {
        this.productPriceRate = productPriceRate;
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
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

    public Integer getIsSupportExpensive() {
        return isSupportExpensive;
    }

    public void setIsSupportExpensive(Integer isSupportExpensive) {
        this.isSupportExpensive = isSupportExpensive;
    }
    
}
