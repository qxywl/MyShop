package com.zhaidou.product.model.base;

import java.util.ArrayList;
import java.util.List;

import com.zhaidou.product.model.AbstractBaseModel;
import com.zhaidou.product.po.AttributePO;

public class ProductMallViewModel  extends AbstractBaseModel{

   
    private static final long serialVersionUID = -7288072103587850815L;
    
   
	private Long productId;//产品ID
	
    private String productName;//商品名称
    
    private String productCode;//商品编码
   
    private String productDesc;//商品描述
   
    private Long productShelves;//商品上下架状态 1  上架  0  下架
    
    private Long createTime ;//创建时间
    
    private Long createBy;//创建人
    
    private String createUserName;//创建人姓名
    
    private Long updateTime;//最后修改时间
    
    private Long updateBy;//修改人
    
    private String updateUserName;//修改人姓名
    
    private String firstShelves;//第一次上架人ID
   
    private String firstShelvesName;//第一次上架 人名称
    
    private long firstShelvesTime;//第一次上架 人名称
   
    private String lastShelves;//最后上架人ID
  
    private long lastShelvesTime;//最后上架 人名称
   
    private String lastShelvesName;//最后上架 人名称
   
    private String downShelves;//最后下架人ID
  
    private long downShelvesTime;//最后下架 人名称
   
    private String downShelvesName;//最后下架 人名称
  
    private double costPrice;//成本价
    
    private double tshPrice;//销售价
   
    private long tb;//特币
    
    private double marketPrice;//市场价
   
    private String brandCode; //基础品牌编码  
   
    private String brandName;//基础品牌名称
    
    private String catCode;//基础分类编码
   
    private String catName;//基础分类名称
    
    private String type;//业务类型
   
    private int status;//状态  审核状态0 待处理，1待审核 2审核通过 3 待同步 4，同步成功，9处理失败
   
    private String mainPic;//主图
   
    private int supplierId;//提供商id
    
    private Long shopId;//店铺id
   
    private int integrity;//完整性 
    
    private String integrityDesc;//完整性描述
    
    
    /**
     * ProductOperateModel 对应的字段
     * */
    private String productShortName;//产品短名称
   
    private String productPrefix;//前缀
   
    private String productSuffix;//后缀

    //TODO 多个关键字还是一个（分隔符）
    private String productKeyword;//关键字
   
    private Integer productDownShow;//是否下架显示 1是 2否
   
    private Integer productAutoUp;//是否自动上架 1是 2否
   
    //TODO 多个还是一个（分隔符）
    private String productSizeCompare;//尺码对照表
   
    private String productCatCode;//运营分类编码
    
    private String productCatName;//运营分类名称
   
    private Integer productLevel; //商品排名
    
    private double productPriceRate;//价格浮动比例
    
    
    /**
     * ProductMallModel 字段对象字段
     * */
    private double productWeight;//重量
    private double productLong;//长
    private double productWidth;//宽
    
    private double productHeight;//高
    private String productProducer;//产地
    private double productDensity;//密度
    private String productAtrNumber;//货号
    
    /** 商品视频URL */
    private String productVideoUrl;
    /** 商品视频缩略图 */
    private String productVideoThumbnail;
    /** 商品是否支持贵就赔 */
    private Integer isSupportExpensive;
    
    private  String column3;//扩展字段3
    private String column4;//扩展字段4
  
    private List<Long> productIdList;//product id 集合
    private List<String> productCodeList;//product id 集合
    
    private Integer  userMaxNum;//用户可以购买数据特卖SKU
    
    private Integer  userMaxType;//用户可以购买数据特卖SKU时间段

    private Integer zeroMaxCount;//
    
    
    private List<AttributePO> attributeList=new ArrayList<AttributePO>();
    
  	public List<AttributePO> getAttributeList() {
  		return attributeList;
  	}

  	public void setAttributeList(List<AttributePO> attributeList) {
  		this.attributeList = attributeList;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Long getProductShelves() {
		return productShelves;
	}

	public void setProductShelves(Long productShelves) {
		this.productShelves = productShelves;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getFirstShelves() {
		return firstShelves;
	}

	public void setFirstShelves(String firstShelves) {
		this.firstShelves = firstShelves;
	}

	public String getFirstShelvesName() {
		return firstShelvesName;
	}

	public void setFirstShelvesName(String firstShelvesName) {
		this.firstShelvesName = firstShelvesName;
	}

	public long getFirstShelvesTime() {
		return firstShelvesTime;
	}

	public void setFirstShelvesTime(long firstShelvesTime) {
		this.firstShelvesTime = firstShelvesTime;
	}

	public String getLastShelves() {
		return lastShelves;
	}

	public void setLastShelves(String lastShelves) {
		this.lastShelves = lastShelves;
	}

	public long getLastShelvesTime() {
		return lastShelvesTime;
	}

	public void setLastShelvesTime(long lastShelvesTime) {
		this.lastShelvesTime = lastShelvesTime;
	}

	public String getLastShelvesName() {
		return lastShelvesName;
	}

	public void setLastShelvesName(String lastShelvesName) {
		this.lastShelvesName = lastShelvesName;
	}

	public String getDownShelves() {
		return downShelves;
	}

	public void setDownShelves(String downShelves) {
		this.downShelves = downShelves;
	}

	public long getDownShelvesTime() {
		return downShelvesTime;
	}

	public void setDownShelvesTime(long downShelvesTime) {
		this.downShelvesTime = downShelvesTime;
	}

	public String getDownShelvesName() {
		return downShelvesName;
	}

	public void setDownShelvesName(String downShelvesName) {
		this.downShelvesName = downShelvesName;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getTshPrice() {
		return tshPrice;
	}

	public void setTshPrice(double tshPrice) {
		this.tshPrice = tshPrice;
	}

	public long getTb() {
		return tb;
	}

	public void setTb(long tb) {
		this.tb = tb;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public int getIntegrity() {
		return integrity;
	}

	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}

	public String getIntegrityDesc() {
		return integrityDesc;
	}

	public void setIntegrityDesc(String integrityDesc) {
		this.integrityDesc = integrityDesc;
	}

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

	public String getProductKeyword() {
		return productKeyword;
	}

	public void setProductKeyword(String productKeyword) {
		this.productKeyword = productKeyword;
	}

	public Integer getProductDownShow() {
		return productDownShow;
	}

	public void setProductDownShow(Integer productDownShow) {
		this.productDownShow = productDownShow;
	}

	public Integer getProductAutoUp() {
		return productAutoUp;
	}

	public void setProductAutoUp(Integer productAutoUp) {
		this.productAutoUp = productAutoUp;
	}

	public String getProductSizeCompare() {
		return productSizeCompare;
	}

	public void setProductSizeCompare(String productSizeCompare) {
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

	public Integer getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(Integer productLevel) {
		this.productLevel = productLevel;
	}

	public double getProductPriceRate() {
		return productPriceRate;
	}

	public void setProductPriceRate(double productPriceRate) {
		this.productPriceRate = productPriceRate;
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

	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}

	public List<String> getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
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

	public String getProductAtrNumber() {
		return productAtrNumber;
	}

	public void setProductAtrNumber(String productAtrNumber) {
		this.productAtrNumber = productAtrNumber;
	}
    
    

    
	

}
