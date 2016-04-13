/*
 * 文 件 名:  ProductPriceLadderDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-27
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model;
import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductPriceLadderModel extends AbstractBase2Model implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long        productId;
    
    private String        productCode;
    
    private String        productName;
    
    private Long        supplierId;
    
    private Long        priceLevel1;
    
    private Long        priceLevel2;
    
    private Long        priceLevel3;
    
    private Long        priceLevel4;
    
    private Long        priceLevel5;
    
    private String        createUserName;
    
    private String        updateUserName;
    
    private Long        isDeleted;
    
    
    

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getPriceLevel1()
    {
        return priceLevel1;
    }

    public void setPriceLevel1(Long priceLevel1)
    {
        this.priceLevel1 = priceLevel1;
    }

    public Long getPriceLevel2()
    {
        return priceLevel2;
    }

    public void setPriceLevel2(Long priceLevel2)
    {
        this.priceLevel2 = priceLevel2;
    }

    public Long getPriceLevel3()
    {
        return priceLevel3;
    }

    public void setPriceLevel3(Long priceLevel3)
    {
        this.priceLevel3 = priceLevel3;
    }

    public Long getPriceLevel4()
    {
        return priceLevel4;
    }

    public void setPriceLevel4(Long priceLevel4)
    {
        this.priceLevel4 = priceLevel4;
    }

    public Long getPriceLevel5()
    {
        return priceLevel5;
    }

    public void setPriceLevel5(Long priceLevel5)
    {
        this.priceLevel5 = priceLevel5;
    }

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName()
    {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName)
    {
        this.updateUserName = updateUserName;
    }

    public Long getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted)
    {
        this.isDeleted = isDeleted;
    }
}
