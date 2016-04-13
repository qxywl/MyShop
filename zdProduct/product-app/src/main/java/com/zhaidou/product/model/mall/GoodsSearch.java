package com.zhaidou.product.model.mall;

import java.util.List;

import com.zhaidou.product.model.mall.Goods.GoodsData;

/**
 * 商品搜索返回
 * 
 */
public class GoodsSearch {

	public int status;
	public SearchData data;
	public String error_msg;
	public static class SearchData {
		public PageInfo page_info;
		public List<GoodsData> goods_list;
	}

	public static class PageInfo {
		public int curr_page;
		public int item_count;
		public int pageper;
	}

}
