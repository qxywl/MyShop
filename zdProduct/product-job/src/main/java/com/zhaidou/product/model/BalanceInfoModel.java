package com.zhaidou.product.model;

import com.zhaidou.common.model.AbstractBaseModel;

/**
 * 
 * @Title: BalanceInfoModel.java 
 *
 * @Package com.teshehui.product.model 
 *
 * @Description:   挂载规则查询数据库时使用的对象
 *
 * @author lvshuding 
 *
 * @date 2015年6月9日 上午10:55:14 
 *
 * @version V1.0
 */
public class BalanceInfoModel extends AbstractBaseModel{

	private static final long serialVersionUID = -2859175613423542617L;

	private int start;//开始位置
	
	private int rows;//查询记录条数
	
	private String dateStr;//根据日期加载数据是使用的字段

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
}
