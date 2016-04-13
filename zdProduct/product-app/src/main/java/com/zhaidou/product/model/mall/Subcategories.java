package com.zhaidou.product.model.mall;

import java.util.List;

public class Subcategories {
	// 类别ID
	public int cate_id = -1;
	// 类别名称
	public String cate_name;
	// 类别图片地址
	public String gcategory_logo;
	// 父类别
	public int parent_id;

	public String description;

	public String pic;
	
	public List<Subcategories> subcategories;
}
