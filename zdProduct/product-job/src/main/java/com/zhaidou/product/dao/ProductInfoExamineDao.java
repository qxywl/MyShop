package com.zhaidou.product.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.model.ProductInfoExamineModel;

public interface ProductInfoExamineDao extends IDao{
	public void add(ProductInfoExamineModel productInfoExamineModel)throws Exception;
}
