package com.zhaidou.product.manager;

import com.zhaidou.product.model.ProductObjBufCondiction;

public interface ProductShelvesManager {
	
	public void doJob(ProductObjBufCondiction configuration);
	
    public void sendMemcache(ProductObjBufCondiction configuration);
	
	public void sendMq(ProductObjBufCondiction configuration);
	
	public void createView(ProductObjBufCondiction configuration);
	
	
	public void imoprt(ProductObjBufCondiction configuration);
	
	
	public void createJob(ProductObjBufCondiction configuration);

}
