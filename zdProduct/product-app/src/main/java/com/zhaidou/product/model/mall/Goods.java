package com.zhaidou.product.model.mall;

import java.util.List;


/**
 * 商品
 * 
 */
public class Goods {

	public int status;
	public GoodsData data;
	public String error_msg;

	public static class GoodsData {
		public PageInfo page_info;
		public List<GoodsData> result;
	}

	public static class PageInfo {
		public int curr_page;
		public int item_count;
		// public int pageper;
	}
}
