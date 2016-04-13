package com.zhaidou.product.manager;

import com.zhaidou.jobCenter.schedule.ScheduleConfiguration;
import com.zhaidou.product.model.ProductObjBufCondiction;

public interface ProductPriceManager {
	
	public void doJob(ProductObjBufCondiction configuration);
	
	public void priceToSupplier(ProductObjBufCondiction configuration);
	
    public void sendMemcache(ProductObjBufCondiction configuration);
	
	public void sendMq(ProductObjBufCondiction configuration);
	
	public void imoprt(ProductObjBufCondiction configuration);
	
	public void createJob(ProductObjBufCondiction configuration);
	
	

}
