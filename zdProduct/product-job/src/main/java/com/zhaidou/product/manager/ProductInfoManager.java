package com.zhaidou.product.manager;

import com.zhaidou.product.model.ProductInfoJobModel;
import com.zhaidou.product.model.ProductObjBufCondiction;

public interface ProductInfoManager {
	
	public void doJob(ProductObjBufCondiction configuration);
	
	public void sendMemcache(ProductObjBufCondiction configuration);
	
	public void sendMq(ProductObjBufCondiction configuration);
	
	public void createView(ProductObjBufCondiction configuration);
	
	public void imoprt(ProductObjBufCondiction configuration);
	
	public void createJob(ProductObjBufCondiction configuration);
	
	public void productToExamine(ProductObjBufCondiction configuration);
	
	public void productAuditFailure(ProductObjBufCondiction configuration);
	public void changeRetryNum(ProductInfoJobModel infoJobModel);
}
