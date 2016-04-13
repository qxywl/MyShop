/*
 * 文 件 名:  RelationGroupAttrServiceImpl.java
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
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.dao.ProductAttrDao;
import com.zhaidou.product.attributies.dao.ProductAttrGroupDao;
import com.zhaidou.product.attributies.dao.RelationGroupAttrDao;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.ProductAttrModel;
import com.zhaidou.product.attributies.model.RelationGroupAttrModel;
import com.zhaidou.product.attributies.service.RelationGroupAttrService;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("relationGroupAttrService")
public class RelationGroupAttrServiceImpl implements RelationGroupAttrService
{
    private static final Log logger = LogFactory.getLog(RelationGroupAttrServiceImpl.class);

    @Resource
    private RelationGroupAttrDao  relationGroupAttrDao;

    @Resource
    private ProductAttrGroupDao productAttrGroupDao;
    
    @Resource
    private ProductAttrDao productAttrDao;
    public void addRelationGroupAttr(RelationGroupAttrModel relationGroupAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationGroupAttrModel);
        }
        
        relationGroupAttrDao.insert(relationGroupAttrModel);
    }

    
    public void updateRelationGroupAttr(RelationGroupAttrModel relationGroupAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationGroupAttrModel);
        }
        relationGroupAttrDao.update(relationGroupAttrModel);
    }

    
    public RelationGroupAttrModel getRelationGroupAttrById(Integer id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        RelationGroupAttrModel  relationGroupAttrModel =new RelationGroupAttrModel();      
        relationGroupAttrModel.setId(new Long(id));
        RelationGroupAttrModel result = relationGroupAttrDao.queryOne(relationGroupAttrModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public long getRelationGroupAttrCount(RelationGroupAttrModel relationGroupAttrModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationGroupAttrModel);
        }
        long result = relationGroupAttrDao.countListPage(relationGroupAttrModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }
    
    
    public List<RelationGroupAttrModel> getRelationGroupAttrListPage(RelationGroupAttrModel relationGroupAttrModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationGroupAttrModel);
        }
        long count = relationGroupAttrDao.countListPage(relationGroupAttrModel);
        List<RelationGroupAttrModel> result=null;
        if (count > 0) {
        result= relationGroupAttrDao.queryListPage(relationGroupAttrModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    
    public void deleteById(Integer id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        relationGroupAttrDao.delete(id);
    }


	@Override
	public void deleteByAttrId(Long attrId) throws Exception 
	{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + attrId);
        }
        relationGroupAttrDao.deleteByAttrId(attrId);
		
	}


	@Override
	public void addRelationGroupAttr(List<RelationGroupAttrModel> relationGroupAttrModelList)
			throws Exception 
	{
		if(relationGroupAttrModelList == null)
		{
			return;
		}
		
		for(RelationGroupAttrModel model : relationGroupAttrModelList)
		{
			addRelationGroupAttr(model);
		}
		
	}


	@Override
	public List<RelationGroupAttrModel> getRelationGroupAttrListByGroupId(Long groupId) throws Exception 
	{
		
		RelationGroupAttrModel model = new RelationGroupAttrModel();
		model.setGroupId(groupId);
		
		List<RelationGroupAttrModel> list = relationGroupAttrDao.queryRelationGroupAttrList(model);
		
		return list;
	}


	@Override
	public void deleteByAttrIdAndGroupId(Map<String, Object> map)throws Exception 
	{
		if(map == null || map.size() ==0)
		{
			return;
		}
		
		relationGroupAttrDao.deleteByAttrIdAndGroupId(map);
	}


	@Override
	public void addRelationGroupAttr(Long groupId, String[] attrIds) throws Exception
	{
		if(attrIds == null || attrIds.length == 0)
		{
			return;
		}
		
		/*
		 * 获取groupId对应的Model信息
		 */
		ProductAttrGroupModel groupModel = new ProductAttrGroupModel();
		groupModel.setGroupId(groupId);
		groupModel = productAttrGroupDao.queryOne(groupModel);
		String groupCode = groupModel.getGroupCode();
		
		RelationGroupAttrModel model = null;
		ProductAttrModel attrModel = null;
		for(String attrId : attrIds)
		{
			/*
			 * 查询出attrId对应的model,为了获取code
			 */
			Long id = Long.valueOf(attrId);
			attrModel = new ProductAttrModel();
			attrModel.setAttrId(id);
			attrModel = productAttrDao.queryOne(attrModel);
			
			/*
			 * 设置RelationGroupAttrModel的信息，并插入表中
			 */
			model = new RelationGroupAttrModel();
			model.setAttrCode(attrModel.getAttrCode());
			model.setAttrId(id);
			model.setGroupCode(groupCode);
			model.setGroupId(groupId);
			
			relationGroupAttrDao.insert(model);
			
		}
		
	}


	@Override
	public RelationGroupAttrModel getRelationGroupAttrById(Long id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}




}
