/*
 * 文 件 名:  ProductOperateDTO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.model.base;

import java.io.Serializable;
import java.util.List;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ProductCountModel  implements Serializable 
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long      productId; //商品ID
    private Long      views; //浏览次数
    private Long      collects; //收藏次数
    private Long      carts; //加购物车次数
    private Long      orders; //下单次数
    private Long      sales; //销售数量
    private Long      comments; //评论次数
    private Long      applaudCount; //点赞次数
    private Long      favorCount;// 喜欢统计数
    
    private List<Long> productIdList;  //product id 集合用于查询
    
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public Long getCollects() {
		return collects;
	}
	public void setCollects(Long collects) {
		this.collects = collects;
	}
	public Long getCarts() {
		return carts;
	}
	public void setCarts(Long carts) {
		this.carts = carts;
	}
	public Long getOrders() {
		return orders;
	}
	public void setOrders(Long orders) {
		this.orders = orders;
	}
	public Long getSales() {
		return sales;
	}
	public void setSales(Long sales) {
		this.sales = sales;
	}
	public Long getComments() {
		return comments;
	}
	public void setComments(Long comments) {
		this.comments = comments;
	}
	public Long getApplaudCount() {
		return applaudCount;
	}
	public void setApplaudCount(Long applaudCount) {
		this.applaudCount = applaudCount;
	}
	public List<Long> getProductIdList() {
		return productIdList;
	}
	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}
    public Long getFavorCount() {
        return favorCount;
    }
    public void setFavorCount(Long favorCount) {
        this.favorCount = favorCount;
    }
    
}
