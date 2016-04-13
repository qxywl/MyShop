/*
 * 文 件 名:  ProductMallDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model.mall;

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
public class ProductMallModel  implements Serializable 
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productMallId; //id
    private Long        productId;//产品id
    private double      productWeight;//重
    private double      productLong;//长
    private double      productWidth;//宽
    private double      productHeight;//高
    private double      productDensity;//密度
    private String      productProducer;//产地
    private String      productAtrNumber;//货号
    private String      Column3;
    private String      Column4;
    
	public Long getProductMallId() {
		return productMallId;
	}
	public void setProductMallId(Long productMallId) {
		this.productMallId = productMallId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public double getProductDensity() {
		return productDensity;
	}
	public void setProductDensity(double productDensity) {
		this.productDensity = productDensity;
	}
	public String getProductProducer() {
		return productProducer;
	}
	public void setProductProducer(String productProducer) {
		this.productProducer = productProducer;
	}
	public String getColumn3() {
		return Column3;
	}
	public void setColumn3(String column3) {
		Column3 = column3;
	}
	public String getColumn4() {
		return Column4;
	}
	public void setColumn4(String column4) {
		Column4 = column4;
	}
	public String getProductAtrNumber() {
		return productAtrNumber;
	}
	public void setProductAtrNumber(String productAtrNumber) {
		this.productAtrNumber = productAtrNumber;
	}
	
	
  
    
}
