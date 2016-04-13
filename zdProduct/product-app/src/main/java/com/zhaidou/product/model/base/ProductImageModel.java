/*
 * 文 件 名:  ProductImageDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
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
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductImageModel implements Serializable 
{

    private static final long serialVersionUID = 1L;

    private Long        productImageId;//唯一标识
    private Long        productSkuId;//sku id

    private String      image;//图片地址
    private Long        level;//第几张
    private Long        type;//图的类型
   
    private List<Long> skuIdList;//skuList
    
    public Long getProductImageId()
    {
        return productImageId;
    }

    public void setProductImageId(Long productImageId)
    {
        this.productImageId = productImageId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Long getLevel()
    {
        return level;
    }

    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getType()
    {
        return type;
    }

    public void setType(Long type)
    {
        this.type = type;
    }

	public List<Long> getSkuIdList() {
		return skuIdList;
	}

	public void setSkuIdList(List<Long> skuIdList) {
		this.skuIdList = skuIdList;
	}
    
    
}
