/*
 * 文 件 名:  ProductAttrValueServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.service.impl.BaseServiceImpl;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.dao.ProductAttrValueDao;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;
import com.zhaidou.product.attributies.service.ProductAttrValueService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends BaseServiceImpl implements ProductAttrValueService
{
    private static final Log logger = LogFactory.getLog(ProductAttrValueServiceImpl.class);

    @Resource
    private ProductAttrValueDao  productAttrValueDao;

    @Value("#{propertyConfigurerForProject2['cache_pc_time']}")
    private Integer cachePcTime;
    
    public void addProductAttrValue(ProductAttrValueModel productAttrValueModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrValueModel);
        }
        productAttrValueDao.insert(productAttrValueModel);
        
        this.cacheObject(productAttrValueModel);
    }

    
    public void updateProductAttrValue(ProductAttrValueModel productAttrValueModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrValueModel);
        }
        
        productAttrValueDao.update(productAttrValueModel);
    }

    
    public ProductAttrValueModel getProductAttrValueById(Long id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        ProductAttrValueModel  productAttrValueModel =new ProductAttrValueModel();      
        productAttrValueModel.setAttrValueId(id);
        ProductAttrValueModel result = productAttrValueDao.queryOne(productAttrValueModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public long getProductAttrValueCount(ProductAttrValueModel productAttrValueModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrValueModel);
        }
        long result = productAttrValueDao.countListPage(productAttrValueModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public List<ProductAttrValueModel> getProductAttrValueListPage(ProductAttrValueModel productAttrValueModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrValueModel);
        }
        
        long count = productAttrValueDao.countListPage(productAttrValueModel);
        page.setTotalCount(count);
        List<ProductAttrValueModel> result=null;
        
        if (count > 0) {
        	 result= productAttrValueDao.queryListPage(productAttrValueModel, page.getPageNum(), page.getNumPerPage());
        }
        
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        
        return result;
    }

    
    public void deleteById(Long id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        productAttrValueDao.delete(id);
    }


	@Override
	public void addProductAttrValue(List<ProductAttrValueModel> productAttrValueModelList)
			throws Exception 
	{
		
		if(productAttrValueModelList == null)
		{
			return;
		}
		
		for(ProductAttrValueModel model : productAttrValueModelList)
		{
			addProductAttrValue(model);
		}
	}


	@Override
	public List<ProductAttrValueModel> getProductAttrValueListPageByAttrCode(String attrCode,Page page) throws Exception 
	{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + attrCode);
        }
		ProductAttrValueModel model = new ProductAttrValueModel();
		model.setAttrCode(attrCode);
		
		List<ProductAttrValueModel> list = getProductAttrValueListPage(model,page);
		
		long2DateList(list);
		return list;
	}


	@Override
	public ProductAttrValueModel getProductAttrValueByCode(String code)
			throws Exception {
		 if (logger.isDebugEnabled())
	        {
	            logger.debug("--Params-->" + code);
	        }
	        ProductAttrValueModel  productAttrValueModel =new ProductAttrValueModel();      
	        productAttrValueModel.setAttrValueCode(code);
	        ProductAttrValueModel result = productAttrValueDao.queryOne(productAttrValueModel);
	        if (logger.isDebugEnabled())
	        {
	            logger.debug("--result-->" + result);
	        }
	        return result;
	}

	/**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(ProductAttrValueModel productAttrValueModel){
    	
    	Long createTime = productAttrValueModel.getCreateTime();
    	Long updateTime = productAttrValueModel.getUpdateTime();
    	
    	if(createTime !=null && !"".equals(createTime)){
    		
    		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrValueModel.setCreateTimes(createTimes);
    		
    	}
    	
    	if(updateTime !=null && !"".equals(updateTime)){
    		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrValueModel.setUpdateTimes(updateTimes);
    	}
    }
    
    /**
     * 统一处long转到date
     * */
    public void long2DateList(List<ProductAttrValueModel> productAttrGroupModelList){
    	if(productAttrGroupModelList != null){
    		for (ProductAttrValueModel productAttrGroupModel : productAttrGroupModelList) {
    			long2DateStringFormat(productAttrGroupModel);
			}
    	}
    }


	@Override
	public void deleteByAttrValueIds(List<Long> list) throws Exception
	{
		//查询出所有需要删除的属性值对象，以便删除缓存提供必要的数据
		List<ProductAttrValueModel> modelList= productAttrValueDao.queryAttrValueByValueIds(list);
		
		//删除数据库数据
		productAttrValueDao.deleteByAttrValueIds(list);
		
		/*
		 * 删除缓存数据
		 */
		for(ProductAttrValueModel model : modelList)
		{
			this.releaseObject(model);
		}
	}
	
	/**
	 * 缓存数据
	 * @param model
	 * @return
	 */
	private  boolean cacheObject(ProductAttrValueModel model)
	{
		String key = "attrvalue_"+model.getAttrCode()+"_"+model.getAttrValueId();
		
		boolean isSuccess = this.getCachedClient().cacheObject(key, model, cachePcTime);
		
		return isSuccess;
	}
	
	/**
	 * 清除缓存
	 * @param code
	 * @return
	 */
	private boolean releaseObject(ProductAttrValueModel model)
	{
		String key = "attrvalue_"+model.getAttrCode()+"_"+model.getAttrValueId();
		
		boolean isSuccess = this.getCachedClient().releaseObject(key);
		
		return isSuccess;
	}


	@Override
	public Long getMaxId() throws Exception 
	{
		
		return productAttrValueDao.getMaxId();
	}
}
