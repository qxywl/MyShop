/*
 * 文 件 名:  ProductAttrValueDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.model;

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
public class ProductAttrValueModel extends AbstractBaseModel
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

	public static final String PRE_ATTRVALUE_CODE = "AV";

    private Long    attrId;      //属性id
    private Long    attrValueId; //属性值id
    private String     attrValueCode; //属性值编码
    private String     attrCode; //属性编码
    private String     attrValue; //属性值
    private Integer    attrValueNum; //属性值排序号
    private String     updateUser; //最后修改者
    private String     creator; //creator
    private Integer attrValueStatus;////属性值状态 1:启用 2:禁用 3：删除
    public ProductAttrValueModel(){
   
    }
  
    public Long getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getAttrValueCode()
    {
        return attrValueCode;
    }

    public void setAttrValueCode(String attrValueCode)
    {
        this.attrValueCode = attrValueCode;
    }

    public String getAttrCode()
    {
        return attrCode;
    }

    public void setAttrCode(String attrCode)
    {
        this.attrCode = attrCode;
    }

    public String getAttrValue()
    {
        return attrValue;
    }

    public void setAttrValue(String attrValue)
    {
        this.attrValue = attrValue;
    }

	public Integer getAttrValueNum() {
		return attrValueNum;
	}

	public void setAttrValueNum(Integer attrValueNum) {
		this.attrValueNum = attrValueNum;
	}

	public Integer getAttrValueStatus() {
		return attrValueStatus;
	}

	public void setAttrValueStatus(Integer attrValueStatus) {
		this.attrValueStatus = attrValueStatus;
	}

    
}
