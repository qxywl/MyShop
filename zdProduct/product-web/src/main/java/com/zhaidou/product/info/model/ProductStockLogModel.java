/*
 * 文 件 名:  ProductStockLogDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.model;

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
public class ProductStockLogModel extends AbstractBaseModel implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

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
    private String        productName;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
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
    private String        oldvalue;

    public String getOldvalue()
    {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue)
    {
        this.oldvalue = oldvalue;
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
    private Long        createby;

    public Long getCreateby()
    {
        return createby;
    }

    public void setCreateby(Long createby)
    {
        this.createby = createby;
    }
    private String        userName;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
