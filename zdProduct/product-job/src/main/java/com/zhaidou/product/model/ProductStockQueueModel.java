/*
 * 文 件 名:  ProductStockDTO.java
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
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProductStockQueueModel extends AbstractBaseModel implements
		Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long stockQueueId;

	private String skuCode;
	
	private String productCode;

	private Double virtualStock;

	private Double manualStock;

	private Double entityStock;

	private Double stock;
	
	private int upType;

	public Long getStockQueueId() {
		return stockQueueId;
	}

	public void setStockQueueId(Long stockQueueId) {
		this.stockQueueId = stockQueueId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Double getVirtualStock() {
		return virtualStock;
	}

	public void setVirtualStock(Double virtualStock) {
		this.virtualStock = virtualStock;
	}

	public Double getManualStock() {
		return manualStock;
	}

	public void setManualStock(Double manualStock) {
		this.manualStock = manualStock;
	}

	public Double getEntityStock() {
		return entityStock;
	}

	public void setEntityStock(Double entityStock) {
		this.entityStock = entityStock;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public int getUpType() {
		return upType;
	}

	public void setUpType(int upType) {
		this.upType = upType;
	}
	
	
	

}
