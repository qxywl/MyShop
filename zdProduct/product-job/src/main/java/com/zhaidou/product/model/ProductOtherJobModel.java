/*
 * 文 件 名:  ProductOtherJobDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-20
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
 * @version  [版本号, 2015-04-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductOtherJobModel extends AbstractBaseModel
{


	 /**
    * 注释内容
    */
   private static final long serialVersionUID = 1L;

   private Long        productOtherId;

   public Long getProductOtherId()
   {
       return productOtherId;
   }

   public void setProductOtherId(Long productOtherId)
   {
       this.productOtherId = productOtherId;
   }
   private String        otherCode;

   public String getOtherCode()
   {
       return otherCode;
   }

   public void setOtherCode(String otherCode)
   {
       this.otherCode = otherCode;
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
   private Long        status;

   public Long getStatus()
   {
       return status;
   }

   public void setStatus(Long status)
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
   private Long        operate;

   public Long getOperate()
   {
       return operate;
   }

   public void setOperate(Long operate)
   {
       this.operate = operate;
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
}
