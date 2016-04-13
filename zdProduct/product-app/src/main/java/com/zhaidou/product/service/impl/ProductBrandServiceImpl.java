/**
 * Copyright © 2014 Teshehui Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of Teshehui Corp.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from Teshehui Corp or an authorized sublicensor.
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductBrandDao;
import com.zhaidou.product.enumration.BrandStatusEnum;
import com.zhaidou.product.model.base.ProductBrandModel;
import com.zhaidou.product.po.base.ProductBrandPO;
import com.zhaidou.product.service.CountryService;
import com.zhaidou.product.service.ProductBrandService;

/**
 * TODO liq: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * liq	      1.0           2015-1-8     Created
 *
 * </pre>
 * @since 1.
 */
@Service("productBrandServiceV001")
public class ProductBrandServiceImpl implements ProductBrandService {
	

    private static final Log logger = LogFactory.getLog(ProductBrandServiceImpl.class);

    @Resource
    private ProductBrandDao  productBrandDao;
    @Resource
    private CountryService  countryService;
  
   
	/*@Resource(name="productBrandChangeSender")
    private JmsMessageSender productBrandChangeSender;*/

    @Value("#{propertyConfigurerForProject2['cache_pc_time']}")
    private Integer cachePcTime;
    @Value("#{propertyConfigurerForProject2['cache_android_time']}")
    private Integer cacheAndroidTime;
    @Value("#{propertyConfigurerForProject2['cache_ios_time']}")
    private Integer cacheIosTime;
    @Value("#{propertyConfigurerForProject2['cache_wap_time']}")
    private Integer cacheWapTime;

    @Override
    public List<ProductBrandPO> getFlowerProductBrandList(String categoryCode, String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

   
    @Override
    public ProductBrandPO getFlowerProductBrand(String brandCode, String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /******************  商品品牌接口  *************************************/
    
    @Override
	public ProductBrandModel getMallEnableBrandByCode(String brandCode)
			throws Exception {
    	ProductBrandModel ProductBrandModel = null;
    	ProductBrandModel = productBrandDao.getEnableBrandByCode(brandCode);
    	long2DateStringFormat(ProductBrandModel);
		return ProductBrandModel;
	}
    

	@Override
	public ProductBrandModel getMallEnableBrandByName(String brandName)
			throws Exception {
		ProductBrandModel ProductBrandModel = null;
       	ProductBrandModel = productBrandDao.getEnableBrandByName(brandName);
       	long2DateStringFormat(ProductBrandModel);
   		return ProductBrandModel;
	}


	@Override
	public List<ProductBrandModel> getMallEnableProductBrandListPage(
			ProductBrandModel productBrandModel, Page page) throws Exception {
       
		productBrandModel.setBrandStatus(BrandStatusEnum.enable.getInt());
        long count = productBrandDao.countListPage(productBrandModel);
        List<ProductBrandModel> result=null;
        if (count > 0) {
        	page.setTotalCount(count);//设置分页信息
        	result= productBrandDao.queryListPage(productBrandModel, page.getPageNum(), page.getNumPerPage());
        }
        
        return result;
	}

	
	@Override
	public List<ProductBrandModel> getAllMallEnableProductBrandList() throws Exception {
		List<ProductBrandModel>  list = null;
		list = productBrandDao.getEnableBrandAllList();
		return list;
	}
	
	
    /**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(ProductBrandModel productBrandModel){
    	
    	if(productBrandModel != null)
    	{
    		Long createTime = productBrandModel.getCreateTime();
        	Long updateTime = productBrandModel.getUpdateTime();
        	
        	if(createTime !=null && !"".equals(createTime)){
        		
        		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd hh:mm:ss");
        		productBrandModel.setCreateTimes(createTimes);
        		
        	}
        	
        	if(updateTime !=null && !"".equals(updateTime)){
        		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd hh:mm:ss");
        		productBrandModel.setUpdateTimes(updateTimes);
        	}
    	}
    }



    

}
