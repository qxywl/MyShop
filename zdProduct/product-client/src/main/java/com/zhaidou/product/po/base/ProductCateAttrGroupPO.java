/*
 * 文 件 名:  ProductBrandDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.po.base;

import java.io.Serializable;
import java.util.List;


/**
 * 对ProductBrandModel 做一个中间层的数据处理以显示出来
 * 
 */
public class ProductCateAttrGroupPO  implements Serializable
{
	
	private static final long serialVersionUID = -2723584764639655755L;
	
	private Long    categoryId;//类型Id
    private String  categoryCode;//类型code
    private Long    groupId; //属性组id
    private String  groupCode; //属性组编号
    private String  groupName; //属性组名称
    private Long    attrId; //属性id
    private String  attrCode; //属性编号
    private String  attrName; //属性名称
    private String  attrEname; //属性别名
    private Long    attrValueId; //属性值id
    private String  attrValueCode; //属性值编码
    private String  attrValue; //属性值
    private Integer attrValueNum; //属性值排序号
    private Integer attrDesign;//是否预设项
    private Integer required;//0=否 1=是
    
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getAttrId() {
		return attrId;
	}
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
	public String getAttrCode() {
		return attrCode;
	}
	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrEname() {
		return attrEname;
	}
	public void setAttrEname(String attrEname) {
		this.attrEname = attrEname;
	}
	public Long getAttrValueId() {
		return attrValueId;
	}
	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}
	public String getAttrValueCode() {
		return attrValueCode;
	}
	public void setAttrValueCode(String attrValueCode) {
		this.attrValueCode = attrValueCode;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public Integer getAttrValueNum() {
		return attrValueNum;
	}
	public void setAttrValueNum(Integer attrValueNum) {
		this.attrValueNum = attrValueNum;
	}
	public Integer getAttrDesign() {
		return attrDesign;
	}
	public void setAttrDesign(Integer attrDesign) {
		this.attrDesign = attrDesign;
	}
	public Integer getRequired() {
		return required;
	}
	public void setRequired(Integer required) {
		this.required = required;
	}
    
  
}
