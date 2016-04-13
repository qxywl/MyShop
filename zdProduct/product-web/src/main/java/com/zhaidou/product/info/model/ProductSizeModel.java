/*
 * 文 件 名:  ProductAirDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
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
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class ProductSizeModel extends AbstractBaseModel implements Serializable 
{
    private Integer productSizeId;
    private String sizeValue;
    private Integer sortNum;
    
    public Integer getProductSizeId() {
        return productSizeId;
    }
    public void setProductSizeId(Integer productSizeId) {
        this.productSizeId = productSizeId;
    }
    public String getSizeValue() {
        return sizeValue;
    }
    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }
    public Integer getSortNum() {
        return sortNum;
    }
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
   
}
