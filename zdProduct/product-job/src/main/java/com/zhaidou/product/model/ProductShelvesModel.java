/*
 * 文 件 名:  ProductShelvesDTO.java
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
public class ProductShelvesModel extends AbstractBaseModel implements Serializable
{

	/**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productShelvesId;

    public Long getProductShelvesId()
    {
        return productShelvesId;
    }

    public void setProductShelvesId(Long productShelvesId)
    {
        this.productShelvesId = productShelvesId;
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
    private String        productName;

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    private Integer        status;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    private String        reason;

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
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
    private String        createUserName;

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }
    private String        updateUserName;

    public String getUpdateUserName()
    {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName)
    {
        this.updateUserName = updateUserName;
    }
    private Integer        shelvesStatus;

    public Integer getShelvesStatus()
    {
        return shelvesStatus;
    }

    public void setShelvesStatus(Integer shelvesStatus)
    {
        this.shelvesStatus = shelvesStatus;
    }
    
    private Integer sourceType;

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	private Long synTime;

	public Long getSynTime() {
		return synTime;
	}

	public void setSynTime(Long synTime) {
		this.synTime = synTime;
	}
	
	private Long timeStart;

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }
    
}
