/*
 * 文 件 名:  ProductInfoAuthDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model;

import java.io.Serializable;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class ProductInfoAuthModel extends AbstractBaseModel implements Serializable
{

    /**
     * 注释内容
     */

    private Long        productInfoAuthId;
    

    private Long        productSkuId;
    
    
    private String productSkuCode;

   
    private String        productCode;

	private Long        type;

    
    private String        newValue;


    private Long        staus;

    
    private String        attrName;

   
    private Integer sourceType;

	
	private Long synTime;

	
	private String reason;

   
    private String oldValue;

    
	public Long getProductInfoAuthId() {
		return productInfoAuthId;
	}


	public void setProductInfoAuthId(Long productInfoAuthId) {
		this.productInfoAuthId = productInfoAuthId;
	}


	public Long getProductSkuId() {
		return productSkuId;
	}


	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}


	public String getProductSkuCode() {
		return productSkuCode;
	}


	public void setProductSkuCode(String productSkuCode) {
		this.productSkuCode = productSkuCode;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public Long getType() {
		return type;
	}


	public void setType(Long type) {
		this.type = type;
	}


	public String getNewValue() {
		return newValue;
	}


	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}


	public Long getStaus() {
		return staus;
	}


	public void setStaus(Long staus) {
		this.staus = staus;
	}


	public String getAttrName() {
		return attrName;
	}


	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}


	public Integer getSourceType() {
		return sourceType;
	}


	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}


	public Long getSynTime() {
		return synTime;
	}


	public void setSynTime(Long synTime) {
		this.synTime = synTime;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getOldValue() {
		return oldValue;
	}


	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

    
    
}
