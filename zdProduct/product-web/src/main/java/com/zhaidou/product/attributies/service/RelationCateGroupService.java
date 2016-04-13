/*
 * 文 件 名:  RelationGroupAttrService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.attributies.service;

import java.util.List;

import com.zhaidou.product.attributies.model.RelationCateGroupModel;
import com.zhaidou.product.category.model.CategoryPO;


/**
 * 属性组和基础分类关联的Service
 * <功能详细描述>
 * 
 * @author  hutongqing
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RelationCateGroupService
{
    
	/**
	 * 添加属性组和基础分类关联
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return null
	 * */
	public void addRelationCateGroup(RelationCateGroupModel relationCateGroupModel)throws Exception;

	
	/**
	 * 更新属性组和基础分类的关联
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return null
	 * */
    public void updateRelationCateGroup(RelationCateGroupModel relationCateGroupModel)throws Exception;

    
    
    /**
   	 * 通过id删除
   	 * 
   	 * @param id 
   	 * @throws Exception
   	 * @return null
   	 * */
    public void deleteById(Long id)throws Exception;
    
    
    /**
   	 * 通过groupId删除
   	 * 
   	 * @param id 
   	 * @throws Exception
   	 * @return null
   	 * */
    public void deleteByGroupId(Long groupId)throws Exception;


    /**
     * 通过groupId获取 组性组关联的CategoryPO集合
     * @param groupId 属性组Id
     * @return List<CategoryPO>  基础分类集合
     * */
    public List<CategoryPO> getGroupRelateCateByGroupId(Long groupId)throws Exception;
    
    
    /**
     * 通过groupId获取 组性组未关联的CategoryPO集合
     * @param groupId 属性组Id
     * @return List<CategoryPO>  基础分类集合
     * */
    public List<CategoryPO> getGroupUnRelateCateByGroupId(Long groupId)throws Exception;


    /**
     * 保存属性组与分类的关联树
     * @param id 属性组Id
     * @param addCateIds 新增关联的分类ID
     * @param deleteCateIds 需要删除的分类ID
     * @throws Exception
     */
	public void saveRelationCate(Long id, List<Long> addCateIds,List<Long> deleteCateIds)throws Exception;
}
