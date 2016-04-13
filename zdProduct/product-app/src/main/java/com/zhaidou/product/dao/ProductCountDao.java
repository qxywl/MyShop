package com.zhaidou.product.dao;

import java.util.List;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.base.ProductCountModel;

/**
 * 商品的浏览次数，点击，收藏次数，加购物车次数，
 * 下单次数，销售数量，评论次数，点赞次数 的统计
 * 
 * */
public interface ProductCountDao  extends IDao{
	

	/**
	 * 通过productID 集合获取product 集合 的浏览次数，点击，收藏次数 。。。 信息
	 * */
	public List<ProductCountModel> queryByProductIdList(ProductCountModel queryModel);
	
	/**
	 * 通过productCode 更新productCountModel
	 * */
	public void updateByProductCode(ProductCountModel updateModel);
}
