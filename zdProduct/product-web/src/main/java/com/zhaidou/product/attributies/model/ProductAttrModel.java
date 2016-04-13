/*
 * 文 件 名:  ProductAttrDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.model;

import java.util.List;

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
public class ProductAttrModel extends AbstractBaseModel
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 编码前缀
     */
    public static final String PRE_ATTR_CODE = "AT";
    
    private Long  attrId; //属性id
    private String   attrCode; //属性编号
    private String   attrName; //属性名称
    private String   attrEname; //属性别名
    private String   attrInfo; //属性说明
    private Integer  attrDesign; //是否预设项 1:是;2:否;
    private Integer  attrNum;  //属性排序号
    private String   updateUser; //最后修改者
    private String   creator; //creator
    private Integer  attrStatus;//属性状态 1:启用 2:禁用 3：删除
    private Integer  required;//是否必须  1：是    2否
    
    private List<ProductAttrValueModel> attrValueList;

    

	public List<ProductAttrValueModel> getAttrValueList() {
		return attrValueList;
	}

	public void setAttrValueList(List<ProductAttrValueModel> attrValueList) {
		this.attrValueList = attrValueList;
	}

	public ProductAttrModel(){
    	
    }
    
    public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrCode()
    {
        return attrCode;
    }

    public void setAttrCode(String attrCode)
    {
        this.attrCode = attrCode;
    }

    public String getAttrName()
    {
        return attrName;
    }

    public void setAttrName(String attrName)
    {
        this.attrName = attrName;
    }

    public String getAttrEname()
    {
        return attrEname;
    }

    public void setAttrEname(String attrEname)
    {
        this.attrEname = attrEname;
    }

    public String getAttrInfo()
    {
        return attrInfo;
    }

    public void setAttrInfo(String attrInfo)
    {
        this.attrInfo = attrInfo;
    }
    
    public Integer getAttrDesign() {
		return attrDesign;
	}

	public void setAttrDesign(Integer attrDesign) {
		this.attrDesign = attrDesign;
	}

	public Integer getAttrNum() {
		return attrNum;
	}

	public void setAttrNum(Integer attrNum) {
		this.attrNum = attrNum;
	}

	public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

	public Integer getAttrStatus() 
	{
		return attrStatus;
	}

	public void setAttrStatus(Integer attrStatus) 
	{
		this.attrStatus = attrStatus;
	}

	
	public Integer getRequired() 
	{
	
		return required;
	}

	
	public void setRequired( Integer required )
	{
	
		this.required = required;
	}
    
	
}
