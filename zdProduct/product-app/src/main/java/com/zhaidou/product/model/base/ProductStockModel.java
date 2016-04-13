/*
 * 文 件 名:  ProductStockDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-14
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model.base;

import java.io.Serializable;
import java.util.List;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductStockModel implements Serializable 
{


	private static final long serialVersionUID = 1L;
	
	private Long        stockId;  //库存记录id
    private String      stockCode;  //库存编号
    private Long        skuId;  //sku id
    private String      skuCode;  //sku 编码
    private Long        productId;  //商品id
    private String      productCode;  //商品编号
    private String      productName;  //商品名称
    private String      productEname;  //商品别名
    private Long        brandId;  //品牌id
    private String      brandCode;  //品牌编码
    private String      brandName;  //品牌名称
    private String      categoryCode;  //分类编号
    private Long        categoryId;  //分类id
    private String      categoryName;  //分类名称数
    private Integer     stockType;  //虚拟库类型：1：手工库；2：对接库
    private Double      virtualStock;  //对接库存数
    private Double      manualStock;  //手工虚拟库
    private Double      entityStock;  //实库数
    
    
    private List<String> skuCodeList;
    
    public Long getStockId()
    {
        return stockId;
    }

    public void setStockId(Long stockId)
    {
        this.stockId = stockId;
    }
  
    public String getStockCode()
    {
        return stockCode;
    }

    public void setStockCode(String stockCode)
    {
        this.stockCode = stockCode;
    }

    public Long getSkuId()
    {
        return skuId;
    }

    public void setSkuId(Long skuId)
    {
        this.skuId = skuId;
    }

    public String getSkuCode()
    {
        return skuCode;
    }

    public void setSkuCode(String skuCode)
    {
        this.skuCode = skuCode;
    }

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

    public String getProductEname()
    {
        return productEname;
    }

    public void setProductEname(String productEname)
    {
        this.productEname = productEname;
    }

    public Long getBrandId()
    {
        return brandId;
    }

    public void setBrandId(Long brandId)
    {
        this.brandId = brandId;
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
   

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryCode()
    {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
   
    public Double getVirtualStock()
    {
        return virtualStock;
    }

    public void setVirtualStock(Double virtualStock)
    {
        this.virtualStock = virtualStock;
    }

    public Double getManualStock()
    {
        return manualStock;
    }

    public void setManualStock(Double manualStock)
    {
        this.manualStock = manualStock;
    }
    
    public Double getEntityStock()
    {
        return entityStock;
    }

    public void setEntityStock(Double entityStock)
    {
        this.entityStock = entityStock;
    }

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public List<String> getSkuCodeList() {
		return skuCodeList;
	}

	public void setSkuCodeList(List<String> skuCodeList) {
		this.skuCodeList = skuCodeList;
	}
    
	
}
