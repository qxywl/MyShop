/*
 * 文 件 名:  ProductAttrGroupServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.dao.ProductAttrGroupDao;
import com.zhaidou.product.attributies.dao.ProductCateAttrGroupDao;
import com.zhaidou.product.attributies.dao.RelationCateGroupDao;
import com.zhaidou.product.attributies.dao.RelationGroupAttrDao;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.attributies.model.RelationGroupAttrModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.info.model.ProductOtherJobModel;
import com.zhaidou.product.info.service.ProductOtherJobService;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productAttrGroupService")
public class ProductAttrGroupServiceImpl implements ProductAttrGroupService
{
    private static final Log logger = LogFactory.getLog(ProductAttrGroupServiceImpl.class);

    @Resource
    private ProductOtherJobService otherJobService;
    @Resource
    private ProductAttrGroupDao  productAttrGroupDao;
    @Resource
    private RelationGroupAttrDao relationGroupAttrDao;
    @Resource
    private ProductCateAttrGroupDao cateAttrGroupDao;
    @Resource
    private RelationCateGroupDao relationCateGroupDao;
    
    public void addProductAttrGroup(ProductAttrGroupModel productAttrGroupModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrGroupModel);
        }
        //保存属性组信息
        productAttrGroupDao.insert(productAttrGroupModel);
        
        /*
         *	保存属性组与分类的关联关系 
         */
       // RelationCateGroupModel model = new RelationCateGroupModel();
        //model.setCategoryCode(categoryPo.getCategoryCode());
       // model.setCategoryId(categoryPo.getId());
       // model.setGroupCode(productAttrGroupModel.getGroupCode());
       // model.setGroupId(productAttrGroupModel.getGroupId());
       // relationCateGroupDao.insert(model);
    }

    public void updateProductAttrGroup(ProductAttrGroupModel productAttrGroupModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrGroupModel);
        }
        productAttrGroupDao.update(productAttrGroupModel);
        productAttrGroupModel = getProductAttrGroupById(productAttrGroupModel.getGroupId());
        addOtherJobModel(productAttrGroupModel,1L);
    }

    private void addOtherJobModel(ProductAttrGroupModel productAttrGroupModel,Long opration)
			throws Exception {
		ProductOtherJobModel jobModel = new ProductOtherJobModel();
        jobModel.setCreateTime(System.currentTimeMillis());
        jobModel.setCreateUserName(productAttrGroupModel.getCreateUserName());
        jobModel.setOtherCode(productAttrGroupModel.getGroupCode());
        jobModel.setType(2L);
        jobModel.setStatus(3L);
        if(opration.equals(1L))
        {
        	jobModel.setReason("修改属性组");
        }
        else if(opration.equals(2L))
        {
        	jobModel.setReason("删除属性组");
        }
        jobModel.setCreateBy(productAttrGroupModel.getCreateBy());
        jobModel.setOperate(opration);
        otherJobService.addProductOtherJob(jobModel);
	}
    
    public ProductAttrGroupModel getProductAttrGroupById(Long id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        ProductAttrGroupModel  productAttrGroupModel =new ProductAttrGroupModel();     
        productAttrGroupModel.setGroupId(id);
        
        ProductAttrGroupModel result = productAttrGroupDao.queryOne(productAttrGroupModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductAttrGroupCount(ProductAttrGroupModel productAttrGroupModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrGroupModel);
        }
        long result = productAttrGroupDao.countListPage(productAttrGroupModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductAttrGroupModel> getProductAttrGroupListPage(ProductAttrGroupModel productAttrGroupModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttrGroupModel);
        }
        
        long count = productAttrGroupDao.countListPage(productAttrGroupModel);
        
        List<ProductAttrGroupModel> result=null;
        
        if (count > 0) 
        {
        	page.setTotalCount(count);
        	result= productAttrGroupDao.queryListPage(productAttrGroupModel, page.getPageNum(), page.getNumPerPage());
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
        
        /*
         * 查询当前属性组下是否有关联属性项
         * 若有则删除失败，否则成功
         */
        RelationGroupAttrModel relationGroupAttrModel = new RelationGroupAttrModel();
        relationGroupAttrModel.setGroupId(id);
        Long attrCount = relationGroupAttrDao.countListPage(relationGroupAttrModel);
        if(attrCount == 0)
        {
        	relationCateGroupDao.deleteByGroupId(id);
        	ProductAttrGroupModel productAttrGroupModel = getProductAttrGroupById(id);
        	productAttrGroupDao.delete(id);
        	addOtherJobModel(productAttrGroupModel,2L);
        }
        else
        {
        	throw new Exception("该属性组关联了属性项，不能删除");
        }
    }

    
    @Override
	public List<ProductAttrGroupModel> getProductAttrGroupList(ProductAttrGroupModel productAttrGroupModel) throws Exception {
		
    	 if (logger.isDebugEnabled())
         {
             logger.debug("--Params-->" + productAttrGroupModel);
         }
         
    	 long count = productAttrGroupDao.countListPage(productAttrGroupModel);
         List<ProductAttrGroupModel> result=productAttrGroupDao.queryListPage(productAttrGroupModel, 1, (int)count);
         
         if (logger.isDebugEnabled())
         {
             logger.debug("--result-->" + result);
         }
         return result;
	}
    
    /**
     * 处理时间的转换
     * */
    public void long2DateStringFormat(ProductAttrGroupModel productAttrGroupModel){
    	
    	Long createTime = productAttrGroupModel.getCreateTime();
    	Long updateTime = productAttrGroupModel.getUpdateTime();
    	
    	if(createTime !=null && !"".equals(createTime)){
    		
    		String createTimes = DatetimeUtil.longToDateTimeString(createTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrGroupModel.setCreateTimes(createTimes);
    		
    	}
    	
    	if(updateTime !=null && !"".equals(updateTime)){
    		String updateTimes = DatetimeUtil.longToDateTimeString(updateTime, "yyyy-MM-dd HH:mm:ss");
    		productAttrGroupModel.setUpdateTimes(updateTimes);
    	}
    }

    
    /**
     * 统一处long转到date
     * */
    public void long2DateList(List<ProductAttrGroupModel> productAttrGroupModelList){
    	if(productAttrGroupModelList != null){
    		for (ProductAttrGroupModel productAttrGroupModel : productAttrGroupModelList) {
    			long2DateStringFormat(productAttrGroupModel);
			}
    	}
    }

	@Override
	public List<ProductCateAttrGroupModel> queryCateAttrGroup(String cateCode) throws Exception 
	{
		 if (logger.isDebugEnabled())
         {
             logger.debug("--Params-->" + cateCode);
         }
		 
		if(cateCode == null)
		{
			throw new Exception("cateCode值为空");
		}
		
		List<ProductCateAttrGroupModel> list = getCateAttrGroupByCateCode(cateCode);
		
		
		return list;
	}

	/**
	 * 通过cateCode查询分类下的所有属性组及属性值
	 * 查询规则 先查询当前级分类下的所有数据，再查询父级分类下的数据，直至查到第一级数据结束查询
	 * @param cateCode
	 * @return
	 * @throws Exception
	 */
	private List<ProductCateAttrGroupModel> getCateAttrGroupByCateCode(String cateCode) throws Exception
	{
		List<ProductCateAttrGroupModel> cateGroupAttrList = new ArrayList<ProductCateAttrGroupModel>();
		List<ProductCateAttrGroupModel> list = null;
		while(cateCode.length() > 2)
		{
			cateCode = cateCode.substring(0, cateCode.length() - 2);
			
			list = cateAttrGroupDao.queryGroupAndAttrValueByCate(cateCode);
			
			cateGroupAttrList.addAll(list);
		}
		
		return cateGroupAttrList;
	}

	
	/**
	 * 查询当前级，如果有数据，则直接返回当前数据，
	 * 如果当前没有数据则查询上一级，上一级有数据就直接返回
	 * 如果上一级还没有数据，就一直往上，
	 * 直到到一级，如果一级还没有则返回空
	 * 
	 * 
	 * */
	@Override
	public List<ProductCateAttrGroupModel> getProductAttrValByCateCode(
			String cateCode) throws Exception {
		List<ProductCateAttrGroupModel> cateGroupAttrList = new ArrayList<ProductCateAttrGroupModel>();
		List<ProductCateAttrGroupModel> list = null;
		while(cateCode.length() >= 2)
		{
			list = cateAttrGroupDao.queryProductAttrValueByCate(cateCode);
			
			if(list != null && list.size()!=0 ){
				cateGroupAttrList.addAll(list);
				return list;
			}
			
			cateCode = cateCode.substring(0, cateCode.length() - 2);
		}
		
		return cateGroupAttrList;
	}
	
	@Override
	public List<ProductAttrGroupModel> queryAttrGroupListByName(String groupName)throws Exception 
	{
		 if (logger.isDebugEnabled())
         {
             logger.debug("--Params-->" + groupName);
         }
		 
		if( null == groupName)
		{
			throw new Exception("groupName值为null");
		}
		
		List<ProductAttrGroupModel> list = productAttrGroupDao.queryAttrGroupByName(groupName);
		
		return list;
	}

	@Override
	public List<ProductAttrGroupModel> getAttrGroupList(Page page) throws Exception 
	{
		ProductAttrGroupModel model = new ProductAttrGroupModel();
		model.setGroupStatus(1);
		
		List<ProductAttrGroupModel> list = productAttrGroupDao.queryListPage
				(model, page.getPageNum(), page.getNumPerPage());
		
		return list;
	}

	@Override
	public Long getMaxId() throws Exception {
		
		return productAttrGroupDao.getMaxId();
	}


	/**
	 * 根据基础分类ID查询关联启用的属性组列表
	 */
	@Override
	public List<ProductAttrGroupModel> getAttrGroupListByCategoryId(Long categoryId) throws ZhaidouRuntimeException {
		if( null == categoryId)
		{
			throw new ZhaidouRuntimeException("categoryId值为null");
		}
		try{
			return this.productAttrGroupDao.queryByCategoryId(categoryId);
		}catch(DaoException e){
			throw new ZhaidouRuntimeException(e.getMessage(), e);
		}
	}

	
}
