/*
 * 文 件 名:  ProductAttributeDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
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
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductAttributeModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productAttributeId;

    public Long getProductAttributeId()
    {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId)
    {
        this.productAttributeId = productAttributeId;
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
    private String        productAttributeName;

    public String getProductAttributeName()
    {
        return productAttributeName;
    }

    public void setProductAttributeName(String productAttributeName)
    {
        this.productAttributeName = productAttributeName;
    }
    private String        productAttributeCode;

    public String getProductAttributeCode()
    {
        return productAttributeCode;
    }

    public void setProductAttributeCode(String productAttributeCode)
    {
        this.productAttributeCode = productAttributeCode;
    }
    private String        productAttributeValue;

    public String getProductAttributeValue()
    {
        return productAttributeValue;
    }

    public void setProductAttributeValue(String productAttributeValue)
    {
        this.productAttributeValue = productAttributeValue;
    }
    private String        type;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    private Integer isRequired;

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }
}
