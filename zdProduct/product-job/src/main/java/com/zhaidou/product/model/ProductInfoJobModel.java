/*
 * 文 件 名:  ProductInfoJobDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model;

import com.zhaidou.common.model.AbstractBaseModel;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

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
public class ProductInfoJobModel extends AbstractBaseModel implements Serializable
{

    /**
     * 注释内容
     */

    private Long        productLogId;

    public Long getProductLogId()
    {
        return productLogId;
    }

    public void setProductLogId(Long productLogId)
    {
        this.productLogId = productLogId;
    }
    private Long        productSkuId;

    public Long getProductSkuId()
    {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId)
    {
        this.productSkuId = productSkuId;
    }
    private Long        productId;

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }
    private String        productCode;

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }
    private Long        type;

    public Long getType()
    {
        return type;
    }

    public void setType(Long type)
    {
        this.type = type;
    }
    private String        attrName;

    public String getAttrName()
    {
        return attrName;
    }

    public void setAttrName(String attrName)
    {
        this.attrName = attrName;
    }
    private String        newvalue;

    public String getNewvalue()
    {
        return newvalue;
    }

    public void setNewvalue(String newvalue)
    {
        this.newvalue = newvalue;
    }
    private Long        createtime;

    public Long getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Long createtime)
    {
        this.createtime = createtime;
    }
    
    private Long productInfoAuthId;

	public Long getProductInfoAuthId() {
		return productInfoAuthId;
	}

	public void setProductInfoAuthId(Long productInfoAuthId) {
		this.productInfoAuthId = productInfoAuthId;
	}
	
	private Integer        status;
	private Integer sourceType;

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    private Integer retryNum;

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
