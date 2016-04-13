package com.zhaidou.product.category.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title: FilterMountShowPO.java 
 *
 * @Package com.teshehui.product.category.model 
 *
 * @Description:   运营分类属性项挂载修改时展示用的对象
 *
 * @author lvshuding 
 *
 * @date 2015年4月15日 下午5:54:07 
 *
 * @version V1.0
 */
public class FilterMountShowPO {

	private String id;//主键列  categoryCode_attrCode
	
	private String attName;//属性项名称
	
	private List<String> attrValNameList = new ArrayList<String>();//属性值名称列表
	
	private Integer orderNumber;//属性项排序值
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public List<String> getAttrValNameList() {
		return attrValNameList;
	}

	public void setAttrValNameList(List<String> attrValNameList) {
		this.attrValNameList = attrValNameList;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
