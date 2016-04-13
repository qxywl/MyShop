/*
 * 文 件 名:  RelationGroupAttrDTO.java
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
 * 基础分类与属性组的关系Model，对应表t_relation_cate_group
 * <功能详细描述>
 * 
 * @author  hutongqing
 * @version  [版本号, 2015-04-07]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RelationCateGroupModel extends AbstractBaseModel
{

   
    private static final long serialVersionUID = 1L;

    private Long categoryId; //属性组id
    private String  categoryCode; //属性组编码
    private Long groupId; //属性id
    private String  groupCode; //属性编码
	
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

	@Override
	public String toString() {
		return "RelationCateGroupModel [categoryId=" + categoryId
				+ ", categoryCode=" + categoryCode + ", groupId=" + groupId
				+ ", groupCode=" + groupCode + "]";
	}
	
}
