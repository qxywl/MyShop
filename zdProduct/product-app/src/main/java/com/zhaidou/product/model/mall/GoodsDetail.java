/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.model.mall;

import java.util.List;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-22     Created
 *
 * </pre>
 * @since 1.
 */

public class GoodsDetail {


    public int status;
    public GoodsInfoData data;
    public String error_msg;
    public static class GoodsInfoData {
        public String goods_id;
        public String store_id;
        //public String type;
        public String goods_name;
        public String cate_id;
        public String cate_name;
        public String spec_qty;   //规格的种数 0 1 2
        public String spec_name_1;
        public String spec_name_2;
        //public String if_show;
        //public String closed;
//        public String add_time;
//        public String recommended;
        public double marketing_price;// 市场价格
        public double price;// 价格
        public int sales;// 商品销量
        public long points;// 特币
//        public int rec_id;// 记录ID
//        public int spec_id;// 规格ID
//        public String specification;// 规格
//        public int quantity;// 数量

//        public String small_goods_image;// 购物车和订单商品小图片
//        public String middle_goods_image;// 购物车和订单商品中图片
        public String default_image; //商品显示的图片
//        public String thumbnail_small;// 商品列表小图片
//        public String thumbnail_middle;// 商品列表图片
        public String store_name;// 商铺名称
//        public double subtotal;// 小计
//        public long subtotal_points;// 特币
//        public String stock;
        public List<Images> _images;// 图片
        public List<Specs> _specs;// 规格
        public String brand = "";// 品牌
        public String description = "";// 描述
        public String ret_url="";//商品url
        public String pass_time="";
        public String second_pass_time="";
        public String unpass_time="";
        public String if_show_time="";
        public Integer views=0;
        // public String carts;
        // public int cate_id;
        // public int cate_id_2;
        public int default_spec;// 默认选中的规格ID
        // public String cate_name;
        // public String collects;
        public int comments;// 评论数
        public SpecData spec_data;// 规格
        public String store_tel;
        public int favorited; //是否已收藏 0:否  1:是
        public int applaud;// 是否已赞过 0:否  1:是
        public String evaluation; //商品评价分数
        public String evaluation_count; //评论数量
//        public String[] tags;
    }

    // 规格
    public static class SpecData {
        public String name;
        public String name2;
        public List<SpecItem> items;
    }

    // 规格item通用类型
    public static class SpecItem {
        public String name;
        public int spec_id;
        public List<SpecItem> items;
    }

    // 规格详情
    public static class Specs {
        public String color_rgb;// 颜色值
        public Long goods_id;// 商品ID
        public String marketing_price;// 市场价
        public Long points;// 特币
        public String price;// 价格
        public String cost_price;// 价格
        public String price_rate;//
        public String sku;
        public Long spec_id;// 规格ID
        public Integer stock;// 库存
        public String spec_1;
        public String spec_2;
    }

    // 图片
    public static class Images {
        public Long file_id;
        public Long goods_id;// 商品ID
        public Long image_id;// 图片ID
        public String image_url;// 大图片
        public Long sort_order;
        public String thumbnail;// 小图片
        public String thumbnail_small;
        public String thumbnail_middle;
        public String thumbnail_large;
    }
}
