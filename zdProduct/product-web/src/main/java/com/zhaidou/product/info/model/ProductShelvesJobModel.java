/*
 * 文 件 名:  ProductShelvesJobDTO.java
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
public class ProductShelvesJobModel extends AbstractBaseModel implements Serializable
{

	   /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productShelvesJob;

    public Long getProductShelvesJob()
    {
        return productShelvesJob;
    }

    public void setProductShelvesJob(Long productShelvesJob)
    {
        this.productShelvesJob = productShelvesJob;
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
    private Integer        status;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
    private Long        shelvesStatus;

    public Long getShelvesStatus()
    {
        return shelvesStatus;
    }

    public void setShelvesStatus(Long shelvesStatus)
    {
        this.shelvesStatus = shelvesStatus;
    }
    
    private Long timeStart;

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }
}
