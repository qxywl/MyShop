package com.zhaidou.product.model.mall;

import java.util.List;

public class GoodList {
	public int status;
	public Data data;
	public String error_msg;
	
	public class Data {
		public List<GoodDatas> goods_list; 
		public List<Subcategories> subcategories;
	}
}
