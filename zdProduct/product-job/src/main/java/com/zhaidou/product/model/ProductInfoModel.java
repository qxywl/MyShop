/*
 * 文 件 名:  ProductInfoDTO.java
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
public class ProductInfoModel extends AbstractBaseModel implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productInfoId;

    public Long getProductInfoId()
    {
        return productInfoId;
    }

    public void setProductInfoId(Long productInfoId)
    {
        this.productInfoId = productInfoId;
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
    private String        pcProductInfo;

    public String getPcProductInfo() {
        return pcProductInfo;
    }

    public void setPcProductInfo(String pcProductInfo) {
        this.pcProductInfo = pcProductInfo;
    }
    private String appProductInfo;

    public String getAppProductInfo() {
        return appProductInfo;
    }

    public void setAppProductInfo(String appProductInfo) {
        this.appProductInfo = appProductInfo;
    }
    
}
