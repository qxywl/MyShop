package com.zhaidou.product.attributies.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaidou.product.attributies.dao.RelationCateGroupDao;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.RelationCateGroupModel;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.attributies.service.RelationCateGroupService;
import com.zhaidou.product.category.dao.BaseCategoryDao;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;



/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("relationCateGroupService")
public class RelationCateGroupServiceImpl implements RelationCateGroupService
{
    private static final Log logger = LogFactory.getLog(RelationCateGroupServiceImpl.class);

    @Resource
    private RelationCateGroupDao  relationCateGroupDao;
    
    @Autowired
	private CategoryService categoryService;

    @Resource
    private ProductAttrGroupService groupService;
    
    @Resource
    private BaseCategoryDao categoryDao;
    public void addRelationCateGroup(RelationCateGroupModel relationCateGroupModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationCateGroupModel);
        }
        
        relationCateGroupDao.insert(relationCateGroupModel);
    }

    
    public void updateRelationCateGroup(RelationCateGroupModel relationCateGroupModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + relationCateGroupModel);
        }
        relationCateGroupDao.update(relationCateGroupModel);
    }

    
    public void deleteById(Long id)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + id);
        }
        relationCateGroupDao.delete(id);
    }


	@Override
	public void deleteByGroupId(Long group) throws Exception 
	{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + group);
        }
		relationCateGroupDao.deleteByGroupId(group);
		
	}


	@Override
	public List<CategoryPO> getGroupRelateCateByGroupId(Long groupId)
			throws Exception {
		
		List<RelationCateGroupModel> relateList = null;//属性组关联的属性组关联分类列表
		List<CategoryPO> cateList = null;  //所有的基础分类列表
		
		List<CategoryPO> returnCateList = new ArrayList<CategoryPO>();
		//设置查询条件
		RelationCateGroupModel relationCateGroupModel = new RelationCateGroupModel();
		relationCateGroupModel.setGroupId(groupId);
		
		relateList = relationCateGroupDao.queryConditionList(relationCateGroupModel);
		cateList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
		
		//循环获取已经关联的
		if(relateList !=null && relateList.size() != 0 && cateList !=null && cateList.size() != 0 )
		{
			for (RelationCateGroupModel relateModel : relateList) 
			{
				for(CategoryPO cate : cateList)
				{
					if(relateModel.getCategoryId().equals(cate.getId()))//如果相等则表示已经关联
					{
						returnCateList.add(cate);
					}
				}
				
			}
		}
		return returnCateList;
	}

	
	@Override
	public List<CategoryPO> getGroupUnRelateCateByGroupId(Long groupId)
			throws Exception {
		
		List<RelationCateGroupModel> relateList = null;//属性组关联分类列表
		List<CategoryPO> cateList = null;  //所有的基础分类列表
		
		//设置查询条件
		RelationCateGroupModel relationCateGroupModel = new RelationCateGroupModel();
		relationCateGroupModel.setGroupId(groupId);
		
		relateList = relationCateGroupDao.queryConditionList(relationCateGroupModel);
		cateList = categoryService.queryAll(Category_Type.BASE_CATEGORY);
		
		
		List<CategoryPO> indexList = new ArrayList<CategoryPO>();//记录关联的下标集合
		
		//循环获取已经关联的
		if(relateList !=null && relateList.size() != 0 && cateList !=null && cateList.size() != 0 )
		{
			for (RelationCateGroupModel relateModel : relateList) 
			{
				for(int i=0; i<cateList.size();i++ )
				{
					CategoryPO cate = cateList.get(i);
					if(relateModel.getCategoryId().equals(cate.getId())) //如果相等则表示已经关联
					{
						
						indexList.add(cate);
						
					}
					
				}
			
			}
		}
		
		//删除已经关联的
		for(int i=0;i<indexList.size();i++){
			cateList.remove(indexList.get(i));
		}
		
		return cateList;
		
	}


	@Override
	public void saveRelationCate(Long id, List<Long> addCateIds,List<Long> deleteCateIds) throws Exception 
	{
		/**
		 * 新增关联关系
		 */
		ProductAttrGroupModel groupModel = groupService.getProductAttrGroupById(id);
		RelationCateGroupModel model = null;
		if(addCateIds != null && addCateIds.size() > 0)
		{
			List<CategoryPO> categoryPo = categoryDao.selectByIds(addCateIds);
			for(CategoryPO po : categoryPo)
			{
				model = new RelationCateGroupModel();
				model.setCategoryCode(po.getCategoryCode());
				model.setCategoryId(po.getId());
				model.setGroupCode(groupModel.getGroupCode());
				model.setGroupId(groupModel.getGroupId());
				relationCateGroupDao.insert(model);
			}
		}
		
		/**
		 * 删除关联关系
		 */
		for(Long deleteId : deleteCateIds)
		{
			model = new RelationCateGroupModel();
			model.setGroupId(id);
			model.setCategoryId(deleteId);
			relationCateGroupDao.deleteModel(model);
		}
	}
	
	

}
