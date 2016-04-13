package com.zhaidou.product.model.mall;

import java.util.List;

/**
 * 商品详情
 * 
 */
public class GoodsInfo {
    public double       marketing_price;    // 市场价格
    public double       price;              // 价格
    public int          sales;              // 商品销量
    public long         points;             // 特币
//    public int          rec_id;             // 记录ID
    public int          spec_id;            // 规格ID
//    public String       specification;      // 规格
//    public int          quantity;           // 数量

//    public String       small_goods_image;  // 购物车和订单商品小图片
//    public String       middle_goods_image; // 购物车和订单商品中图片
    public String       default_image;     // 商品显示的图片
    public String       thumbnail_small;    // 商品列表小图片
    public String       thumbnail_middle;   // 商品列表图片
    public String       store_name;         // 商铺名称
//    public double       subtotal;           // 小计
//    public long         subtotal_points;    // 特币
    public Integer       stock;             //可售数量
    public List<Images> _images;            // 图片
    public List<Specs>  _specs;             // 规格
    public String       brand       = "";   // 品牌
//    public String       description = "";   // 描述
//    public String       ret_url     = "";   // 商品url
//    public int          default_spec;       // 默认选中的规格ID
    public int          comments;           // 评论数
    public SpecData     spec_data;          // 规格
//    public String       store_tel;
//    public int          favorited;         // 是否已收藏 0:否 1:是
//    public int          applaud;            // 是否已赞过 0:否 1:是
//    public String       evaluation;        // 商品评价分数
//    public String       evaluation_count;  // 评论数量
    
    public String       praise_rate;    //好评率
    public String       im_qq;
    public String       im_ww;
    public String       imqq;
    public String       imalww;
    public String       goods_id;
    public String       store_id;
    public String       type;   //商品类别 material实体，虚拟
    public String       goods_name;
    public String       cate_id;
    public String       cate_id_1;
    public String       cate_id_2;
    public String       cate_id_3;
    public String       cate_id_4;
    public String       cate_name;
//    public String       brand;
    public String       spec_qty;   //规格的种数 0 1 2
    public String       spec_name_1;    //第一种规格名称       sku信息
    public String       spec_name_2;    //第二种规格名称       sku信息
    public String       if_show;    //是否显示
    public Integer       closed;     //是否关闭购买
    public String       add_time;   //
    public String       recommended;    //是否推荐 0否 1是
//    public String       default_image;
    public String       audit_status;   //商品审核状态 0未 1已审核 2拒绝
    public String       goods_type; //
//    public String       spec_id;
    public String       spec_1;     //第一种规格值       sku信息
    public String       spec_2;   //第二种规格值       sku信息
    public String       color_rgb;  //标题颜色
//    public String       marketing_price;
    public String       cost_price; //供货价
    public String       price_rate; //浮动比例
//    public String       stock;
//    public String       store_name;
    public String       region_id;  //卖家所在地区id
    public String       region_name;    //卖家所在地区
    public String       credit_value;   //店铺好评率 1-5
    public String       sgrade; //
    public String       optionset;  //标记猜你喜欢类别或更多商品类别
    public String       new_index;  //每周新品排序号，越大越前面
    public String       hot_index;  //热门推荐排序号，同上
    public String       guess_index;    //猜你喜欢的排序号，同上
    public String       more_index; //更多商品的排序好，同上
    public String       applaud_count;  //点赞数量
    public String       pass_time;  //一次审核通过时间
    public String       second_pass_time;   //二次审核
    public String       unpass_time;    //审核拒绝时间
    public String       yitao_url;  //一淘地址
    public String       commodity_code; //
    public String       pvs;    //
    public String       views;  //浏览量
//    public String       sales;
//    public String       comments;
//    public String       price;
//    public String       points;
    public String       credit_image;   
    public String       grade_name;
    public String       im_qq_val;
    public String       im_alww_val;
    // 规格
    public static class SpecData {
        public String         name;
        public String         name2;
        public List<SpecItem> items;
    }

    // 规格item通用类型
    public static class SpecItem {
        public String         name;
        public int            spec_id;
        public List<SpecItem> items;
    }

    // 规格详情
    public static class Specs {
        public double marketing_price; // 市场价
        public long   points;          // 特币
        public double price;           // 价格
        public int    spec_id;         // 规格ID
        public int    stock;           // 库存
    }

    // 图片
//    public static class Images {
//        public int    goods_id;         // 商品ID
//        public String image_url;        // 大图片
//        public int    sort_order;
//        public String thumbnail;        // 小图片
//        public String thumbnail_middle;
//    }
    // 图片
    public static class Images {
        public String image_id;         // 图片ID
        public String image_url;        // 大图片
        public String thumbnail;        // 小图片
        public String thumbnail_small;
        public String thumbnail_middle;
    }

}
