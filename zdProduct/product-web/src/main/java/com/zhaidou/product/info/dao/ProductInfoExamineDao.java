package com.zhaidou.product.info.dao;

import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.ProductInfoExamineModel;

public interface ProductInfoExamineDao extends IDao{
	public void add(ProductInfoExamineModel productInfoExamineModel)throws Exception;
}
