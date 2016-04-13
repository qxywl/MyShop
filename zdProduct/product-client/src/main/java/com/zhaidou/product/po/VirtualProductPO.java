/*
 * 文 件 名:  VirtualProductDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.po;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-10-01]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class VirtualProductPO  implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /*产品ID*/    
    private Long productId;
    
    /*商品名称*/    
    private String productName;
    
    /*sku编码*/    
    private String skuCode;
    
    /*商品描述*/    
    private String productDesc;
    
    /*商品上下架状态 1  上架  0  下架*/    
    private Long productShelves;
    
    /**/    
    private String createUserName;
    
    /**/    
    private String updateUserName;
    
    /*成本价*/    
    private Double costPrice;
    
    /*销售价*/    
    private Double tshPrice;
    
    /*特币*/    
    private Long tb;
    
    /*市场价*/    
    private Double marketPrice;
    
    /*基础品牌编码*/    
    private String brandCode;
    
    /*基础品牌名称*/    
    private String brandName;
    
    /*基础分类编码*/    
    private String catCode;
    
    /*基础分类名称*/    
    private String catName;
    
    /*类型 01:商城;02:机票;03:酒店;04:鲜花;05:团购*/    
    private String type;
    
    /*主图*/    
    private String mainPic;
    
    /**/    
    private Long supplierId;
    
    /**/    
    private Long shopId;
    
    
    

    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getSkuCode()
    {
        return skuCode;
    }

    public void setSkuCode(String skuCode)
    {
        this.skuCode = skuCode;
    }

    public String getProductDesc()
    {
        return productDesc;
    }

    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }

    public Long getProductShelves()
    {
        return productShelves;
    }

    public void setProductShelves(Long productShelves)
    {
        this.productShelves = productShelves;
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

    public Double getCostPrice()
    {
        return costPrice;
    }

    public void setCostPrice(Double costPrice)
    {
        this.costPrice = costPrice;
    }

    public Double getTshPrice()
    {
        return tshPrice;
    }

    public void setTshPrice(Double tshPrice)
    {
        this.tshPrice = tshPrice;
    }

    public Long getTb()
    {
        return tb;
    }

    public void setTb(Long tb)
    {
        this.tb = tb;
    }

    public Double getMarketPrice()
    {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice)
    {
        this.marketPrice = marketPrice;
    }

    public String getBrandCode()
    {
        return brandCode;
    }

    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getCatCode()
    {
        return catCode;
    }

    public void setCatCode(String catCode)
    {
        this.catCode = catCode;
    }

    public String getCatName()
    {
        return catName;
    }

    public void setCatName(String catName)
    {
        this.catName = catName;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getMainPic()
    {
        return mainPic;
    }

    public void setMainPic(String mainPic)
    {
        this.mainPic = mainPic;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getShopId()
    {
        return shopId;
    }

    public void setShopId(Long shopId)
    {
        this.shopId = shopId;
    }
}
