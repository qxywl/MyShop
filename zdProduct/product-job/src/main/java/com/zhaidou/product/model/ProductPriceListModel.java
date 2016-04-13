/*
 * 文 件 名:  ProductPriceListDTO.java
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
public class ProductPriceListModel extends AbstractBaseModel implements Serializable
{


	 /**
    * 注释内容
    */
   private static final long serialVersionUID = 1L;

   private Long        productPriceList;

   public Long getProductPriceList()
   {
       return productPriceList;
   }

   public void setProductPriceList(Long productPriceList)
   {
       this.productPriceList = productPriceList;
   }
   private String        productSkuCode;

   public String getProductSkuCode()
   {
       return productSkuCode;
   }

   public void setProductSkuCode(String productSkuCode)
   {
       this.productSkuCode = productSkuCode;
   }
   private Double        costPrice;

   public Double getCostPrice()
   {
       return costPrice;
   }

   public void setCostPrice(Double costPrice)
   {
       this.costPrice = costPrice;
   }
   private Double        tshPrice;

   public Double getTshPrice()
   {
       return tshPrice;
   }

   public void setTshPrice(Double tshPrice)
   {
       this.tshPrice = tshPrice;
   }
   private Long        tb;

   public Long getTb()
   {
       return tb;
   }

   public void setTb(Long tb)
   {
       this.tb = tb;
   }
   private Double        marketPrice;

   public Double getMarketPrice()
   {
       return marketPrice;
   }

   public void setMarketPrice(Double marketPrice)
   {
       this.marketPrice = marketPrice;
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
   private Integer        status;

   public Integer getStatus()
   {
       return status;
   }

   public void setStatus(Integer status)
   {
       this.status = status;
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
   private Double        oldCostPrice;

   public Double getOldCostPrice()
   {
       return oldCostPrice;
   }

   public void setOldCostPrice(Double oldCostPrice)
   {
       this.oldCostPrice = oldCostPrice;
   }
   private Double        oldTshPrice;

   public Double getOldTshPrice()
   {
       return oldTshPrice;
   }

   public void setOldTshPrice(Double oldTshPrice)
   {
       this.oldTshPrice = oldTshPrice;
   }
   private Long        oldTb;

   public Long getOldTb()
   {
       return oldTb;
   }

   public void setOldTb(Long oldTb)
   {
       this.oldTb = oldTb;
   }
   private Double        oldMarketPrice;

   public Double getOldMarketPrice()
   {
       return oldMarketPrice;
   }

   public void setOldMarketPrice(Double oldMarketPrice)
   {
       this.oldMarketPrice = oldMarketPrice;
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
