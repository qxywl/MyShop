/*
 * 文 件 名:  ProductAttrServiceImpl.java
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
import com.zhaidou.product.attributies.dao.ProductAttrDao;
import com.zhaidou.product.attributies.dao.RelationGroupAttrDao;
import com.zhaidou.product.attributies.model.ProductAttrModel;
import com.zhaidou.product.attributies.model.ProductAttrValueModel;
import com.zhaidou.product.attributies.service.ProductAttrService;
import com.zhaidou.product.attributies.service.ProductAttrValueService;
import com.zhaidou.product.info.model.ProductOtherJobModel;
import com.zhaidou.product.info.service.ProductOtherJobService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  hu.tongqing
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productAttrService")
public class ProductAttrServiceImpl extends BaseServiceImpl implements ProductAttrService
{
    private static final Log logger = LogFactory.getLog(ProductAttrServiceImpl.class);

    @Resource
    private ProductAttrDao  productAttrDao;

    @Resource
    private ProductOtherJobService otherJobService;
    @Resource
    private ProductAttrValueService  attrValueService;
    @Resource
    private RelationGroupAttrDao relationGroupAttrDao;
    @Value("#{propertyConfigurerForProject2['cache_pc_time']}")
    private Integer cachePcTime;
    
    public void addProductAttr(ProductAttrModel productAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrModel);
        }
        productAttrDao.insert(productAttrModel);
        cacheObject(productAttrModel);
    }

    
    public void updateProductAttr(ProductAttrModel productAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrModel);
        }
        productAttrDao.update(productAttrModel);
        productAttrModel = getProductAttrById(productAttrModel.getAttrId());
        cacheObject(productAttrModel);
        addOtherJobModel(productAttrModel,1L);
    }


	private void addOtherJobModel(ProductAttrModel productAttrModel,Long opration)
			throws Exception {
		ProductOtherJobModel jobModel = new ProductOtherJobModel();
        jobModel.setCreateTime(System.currentTimeMillis());
        jobModel.setCreateUserName(productAttrModel.getCreateUserName());
        jobModel.setOtherCode(productAttrModel.getAttrCode());
        jobModel.setType(3L);
        jobModel.setStatus(3L);
        if(opration.equals(1L))
        {
        	jobModel.setReason("修改属性");
        }
        else if(opration.equals(2L))
        {
        	jobModel.setReason("删除属性");
        }
        jobModel.setCreateBy(productAttrModel.getCreateBy());
        jobModel.setOperate(opration);
        otherJobService.addProductOtherJob(jobModel);
	}

    
    public ProductAttrModel getProductAttrById(Long id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        ProductAttrModel  productAttrModel =new ProductAttrModel();      
        productAttrModel.setAttrId(id);
        ProductAttrModel result = productAttrDao.queryOne(productAttrModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public long getProductAttrCount(ProductAttrModel productAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrModel);
        }
        long result = productAttrDao.countListPage(productAttrModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public List<ProductAttrModel> getProductAttrListPage(ProductAttrModel productAttrModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrModel);
        }
        
        long count = productAttrDao.countListPage(productAttrModel);
        List<ProductAttrModel> result=null;
       
        if (count > 0) 
        {
        	page.setTotalCount(count);
        	result= productAttrDao.queryListPage(productAttrModel, page.getPageNum(), page.getNumPerPage());
        	long2DateList(result);
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
        productAttrDao.delete(id);
        
    }

    
    /**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(ProductAttrModel productAttrModel){
    	
    	Long createTime = productAttrModel.getCreateTime();
    	Long updateTime = productAttrModel.getUpdateTime();
    	
    	if(createTime !=null && !"".equals(createTime))
    	{
    		
    		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrModel.setCreateTimes(createTimes);
    		
    	}
    	
    	if(updateTime !=null && !"".equals(updateTime))
    	{
    		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrModel.setUpdateTimes(updateTimes);
    	}
    }

    
    /**
     * 统一处long转到date
     * */
    public void long2DateList(List<ProductAttrModel> productAttrGModelList)
    {
    	if(productAttrGModelList != null)
    	{
    		for (ProductAttrModel productAttrModel : productAttrGModelList) 
    		{
    			long2DateStringFormat(productAttrModel);
			}
    	}
    }


	@Override
	public List<ProductAttrModel> queryAttrListPageByGroupCode(String groupCode,Page page) throws Exception
	{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + groupCode);
        }
        
        long count = productAttrDao.countListPageByGroupCode(groupCode);
        List<ProductAttrModel> result=null;
       
        if (count > 0) 
        {
        	page.setTotalCount(count);
        	result= productAttrDao.queryAttrListByGroupCode(groupCode, page.getPageNum(), page.getNumPerPage());
        	//long2DateList(result);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
	}


	@Override
	public List<ProductAttrModel> queryAttrListByAttrName(String attrName)throws Exception 
	{
		List<ProductAttrModel> list = productAttrDao.queryAttrListByAttrName(attrName);
		
		return list;
	}


	@Override
	public List<ProductAttrModel> queryGroupRelationAttrList(ProductAttrModel model, Page page) throws Exception {
		
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + model);
        }
        
        long count = productAttrDao.countQueryGroupRelationAttrList(model);
        List<ProductAttrModel> result=null;
       
        if (count > 0) 
        {
        	page.setTotalCount(count);
        	result= productAttrDao.queryGroupRelationAttrList(model, page.getPageNum(), page.getNumPerPage());
        	//long2DateList(result);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
	}


	@Override
	public List<ProductAttrModel> queryGroupRelationAttrNotBundList(
			ProductAttrModel model, Page page) throws Exception {
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + model);
        }
        
        long count = productAttrDao.countQueryGroupRelationAttrNotBundList(model);
        List<ProductAttrModel> result=null;
       
        if (count > 0) 
        {
        	page.setTotalCount(count);
        	result= productAttrDao.queryGroupRelationAttrNotBundList(model, page.getPageNum(), page.getNumPerPage());
        	//long2DateList(result);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
	}


	@Override
	public ProductAttrModel getProductAttrByCode(String code) throws Exception {
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + code);
        }
        ProductAttrModel  productAttrModel =new ProductAttrModel();      
        productAttrModel.setAttrCode(code);
        ProductAttrModel result = productAttrDao.queryOne(productAttrModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
	}


	@Override
	public boolean deleteByAttrIds(String[] attrIds) throws Exception
	{
		if(attrIds == null || attrIds.length == 0)
		{
			return Boolean.FALSE;
		}
		
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + attrIds);
        }
		
		/*
		 * 删除属性，若该属性项下面挂有属性值 则不删除该项
		 */
		Long id = null;
		boolean isDeleteFailed = Boolean.TRUE;
		for(String attrId : attrIds)
		{
			id = Long.parseLong(attrId);
			
			ProductAttrValueModel valueModel = new ProductAttrValueModel();
			valueModel.setAttrId(id);
			Long count = attrValueService.getProductAttrValueCount(valueModel);
			
			/*
			 * 删除数据,并通知赋哥
			 */
			if(count == 0)
			{
				ProductAttrModel model = getProductAttrById(id);
				try {
					relationGroupAttrDao.deleteByAttrId(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deleteById(id);//通过属性Id  删除属性
				
				//relationGroupService.deleteByAttrId(id);//删除关联表中的数据
				
				releaseObject(model.getAttrCode());
				
				addOtherJobModel(model,2L);
			}
			else
			{
				isDeleteFailed = Boolean.FALSE;
			}
		}
		
		return isDeleteFailed;
	}

	/**
	 * 缓存数据
	 * @param model
	 * @return
	 */
	private  boolean cacheObject(ProductAttrModel model)
	{
		boolean isSuccess = this.getCachedClient().cacheObject("attr_"+model.getAttrCode(), model, cachePcTime);
		
		return isSuccess;
	}
	
	/**
	 * 清除缓存
	 * @param code
	 * @return
	 */
	private boolean releaseObject(String code)
	{
		
		boolean isSuccess = this.getCachedClient().releaseObject("attr_"+code);
		
		return isSuccess;
	}


	@Override
	public Long getMaxId() throws Exception 
	{
		
		return productAttrDao.getMaxId();
	}


	@Override
	public Boolean isExistThisName( String attrName ) throws Exception {

		ProductAttrModel model = productAttrDao.selectByAttrName(attrName);
		if(model != null)
		{
			return true;
		}
		return false;
	}
	
}
