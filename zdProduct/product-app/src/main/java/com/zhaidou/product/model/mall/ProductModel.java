package com.zhaidou.product.model.mall;

import java.util.ArrayList;
import java.util.List;

import com.zhaidou.product.model.AbstractBaseModel;
import com.zhaidou.product.po.AttributePO;

public class ProductModel  extends AbstractBaseModel{

   
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
    
    private Long stopId;//店铺id
   
    private int integrity;//完整性 
    
    private String integrityDesc;//完整性描述
    
    private List<AttributePO> attributeList=new ArrayList<AttributePO>();
  
	public List<AttributePO> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<AttributePO> attributeList) {
		this.attributeList = attributeList;
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

	public Long getStopId() {
		return stopId;
	}

	public void setStopId(Long stopId) {
		this.stopId = stopId;
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

    
}
