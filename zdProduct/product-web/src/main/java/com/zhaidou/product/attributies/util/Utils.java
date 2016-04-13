package com.zhaidou.product.attributies.util;

import com.zhaidou.product.category.model.CategoryPO;

import java.util.List;

public class Utils {

	/**
	 * @desc  
	 * 
	 * @Description: 根据分类列表构造zTree格式树
	 * @param poList
	 * return String
	 */
	public static String buildJsonFormTree(List<CategoryPO> poList,List<Long> selectCateIds){
		if(poList == null || poList.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(CategoryPO po:poList){
			sb.append("{").append("\"id\":").append(po.getId()).append(",")
			  .append("\"pId\":").append(po.getParentId()==null?"0":po.getParentId()).append(",")
			  .append("\"name\":\"").append(po.getCategoryName()).append("\"");
			if(selectCateIds != null && selectCateIds.contains(po.getId())){
				sb.append(",checked:true, open:true");
			}
			
			if(po.getCategoryCode().length() <= 4 )
			{
				//sb.append(",chkDisabled:true");
			}
			sb.append("}").append(",");
		}
		return sb.substring(0,sb.length()-1);
		
	}
}
