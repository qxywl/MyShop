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
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.model.RelationGroupAttrModel;


/**
 * 属性组和属性关联
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RelationGroupAttrService
{
    
	/**
	 * 添加商品属性组和属性关联
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return null
	 * */
	public void addRelationGroupAttr(RelationGroupAttrModel relationGroupAttrModel)throws Exception;

	
	/**
	 * 更新商品属性组和属性关联
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return null
	 * */
    public void updateRelationGroupAttr(RelationGroupAttrModel relationGroupAttrModel)throws Exception;

    
    /**
	 * 通过商品属性组和属性关联id 获取详细
	 * 
	 * @param id 属性组关联属性id
	 * @throws Exception
	 * @return RelationGroupAttrModel
	 * */
    public RelationGroupAttrModel getRelationGroupAttrById(Long id)throws Exception;

    
    /**
	 * 获取商品属性组和属性关联总数
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return long 属性组和属性关联总数
	 * */
    public long getRelationGroupAttrCount(RelationGroupAttrModel relationGroupAttrModel)throws Exception;

    
    /**
   	 * 获取商品属性组和属性关联的集合
   	 * 
   	 * @param relationGroupAttrModel 属性组关联属性模型
   	 * @param page 分页信息
   	 * @throws Exception
   	 * @return List<RelationGroupAttrModel> 属性组和属性关联集合
   	 * */
    public List<RelationGroupAttrModel> getRelationGroupAttrListPage(RelationGroupAttrModel relationGroupAttrModel, Page page)throws Exception;

    
    /**
   	 * 通过id删除
   	 * 
   	 * @param id 
   	 * @throws Exception
   	 * @return null
   	 * */
    public void deleteById(Long id)throws Exception;
    
    /**
   	 * 通过attrId删除
   	 * 
   	 * @param id 
   	 * @throws Exception
   	 * @return null
   	 * */
    public void deleteByAttrId(Long attrId)throws Exception;

    /**
	 * 批量添加商品属性组和属性关联
	 * 
	 * @param relationGroupAttrModel 属性组关联属性模型
	 * @throws Exception
	 * @return null
	 * */
	public void addRelationGroupAttr(List<RelationGroupAttrModel> relationGroupAttrModelList)throws Exception;

	/**
   	 * 获取商品属性组和属性关联
   	 * 
   	 * @param groupId 
   	 * @throws Exception
   	 * @return List<RelationGroupAttrModel> 属性组和属性关联集合
   	 * */
    public List<RelationGroupAttrModel> getRelationGroupAttrListByGroupId(Long groupId)throws Exception;

    
    /**
     * 取消关联
     * @param groupId
     * @param attrIds
     * @throws Exception
     */
    public void deleteByAttrIdAndGroupId(Map<String,Object> map)throws Exception;


    /**
     * 根据groupId和attrIds 插入数据到关系表中
     * @param groupId
     * @param ids
     * @throws Exception 
     */
	public void addRelationGroupAttr(Long groupId, String[] attrIds) throws Exception;
}
