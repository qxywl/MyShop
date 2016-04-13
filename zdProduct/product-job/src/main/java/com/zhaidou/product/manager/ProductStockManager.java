package com.zhaidou.product.manager;

import com.zhaidou.product.model.ProductObjBufCondiction;
import com.zhaidou.product.model.ProductStockQueueModel;

public interface ProductStockManager {
	
	public void changeStock(ProductStockQueueModel productStockQueueModel);
	
	public void doJob(ProductObjBufCondiction configuration); 

}
