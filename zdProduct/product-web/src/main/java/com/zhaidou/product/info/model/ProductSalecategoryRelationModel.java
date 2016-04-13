/*
 * 文 件 名:  ProductSalecategoryRelationDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-09
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

import java.io.Serializable;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-04-09]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProductSalecategoryRelationModel implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String productCode;
	private String categoryCodes;

	public String getCategoryCodes() {
		return categoryCodes;
	}

	public Long getId() {
		return id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setCategoryCodes(String categoryCodes) {
		this.categoryCodes = categoryCodes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
