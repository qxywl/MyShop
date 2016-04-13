/*
 * 文 件 名:  ProductAttrGroupService.java
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

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.model.ProductAttrGroupModel;
import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttrGroupService
{
	
	/**
	 * 添加属性组
	 * 
	 * @param productAttrGroupModel 属性组模型
	 * @throws Exception
	 * */
    public void addProductAttrGroup(ProductAttrGroupModel productAttrGroupModel)throws Exception;

    
    /**
	 * 添加属性组
	 * 
	 * @param productAttrGroupModel 属性组模型
	 * @throws Exception
	 * */
    public void updateProductAttrGroup(ProductAttrGroupModel productAttrGroupModel)throws Exception;

    
    /**
	 * 通过id查询属性组
	 * 
	 * @param Long 属性组id
	 * @throws Exception
	 * */
    public ProductAttrGroupModel getProductAttrGroupById(Long id)throws Exception;

    
    /**
	 * 查询商品属性组总数
	 * 
	 * @param productAttrGroupModel 属性组模型
	 * @throws Exception
	 * @return long
	 * */
    public long getProductAttrGroupCount(ProductAttrGroupModel productAttrGroupModel)throws Exception;

    
    /**
	 * 获取属性组分页集合
	 * 
	 * @param productAttrGroupModel 属性组模型
	 * @throws Exception
	 * @return List<ProductAttrGroupModel>
	 * */
    public List<ProductAttrGroupModel> getProductAttrGroupListPage(ProductAttrGroupModel productAttrGroupModel, Page page)throws Exception;

    
    /**
	 * 通过id删除属性组
	 * 
	 * @param productAttrGroupModel 属性组模型
	 * @throws Exception
	 * @return null
	 * */
    public void deleteById(Long id)throws Exception;
    
    /**
   	 * 获取属性组所有集合
   	 * 
   	 * @param productAttrGroupModel 属性组模型
   	 * @throws Exception
   	 * @return List<ProductAttrGroupModel>
   	 * */
    public List<ProductAttrGroupModel> getProductAttrGroupList(ProductAttrGroupModel productAttrGroupModel)throws Exception;

    /**
	 * 通过分类来获取分类下的属性
	 * 
	 * @param cateCode 分类编码
	 * @return List  包含分类下的组、属性、属性值 
	 * @throws Exception
	 */
    public List<ProductCateAttrGroupModel> queryCateAttrGroup(String cateCode) throws Exception;
   
   
    /**
  	 * 通过分类来获取分类下的属性,主要给商品用
  	 * 
  	 * @param cateCode 分类编码
  	 * @return List  包含分类下的组、属性、属性值 
  	 * @throws Exception
  	 */
     public List<ProductCateAttrGroupModel> getProductAttrValByCateCode(String cateCode) throws Exception;
   
   
    /**
	 * 根据属性组的名称，模糊查询属性组列表
	 * @param groupName 属性组名
	 * @return List<ProductAttrGroupModel>
	 * @throws Exception
	 * 
	 * @author hutongqing
	 */
	public List<ProductAttrGroupModel> queryAttrGroupListByName(String groupName) throws Exception;
	
   /**
	 * 获取所有属性组列表
	 * @return List<ProductAttrGroupModel>
	 * @throws Exception
	 * 
	 * @author hutongqing
	 */
	public List<ProductAttrGroupModel> getAttrGroupList(Page page) throws Exception;

	/**
	 * 
	 * @return 
	 * @throws Exception
	 */
	public Long getMaxId() throws Exception;

	/**
	 * 根据基础分类ID查询关联启用的属性组列表
	 * @param categoryId
	 * @return
	 * @throws ZhaidouRuntimeException
	 */
	public List<ProductAttrGroupModel> getAttrGroupListByCategoryId(Long categoryId) throws ZhaidouRuntimeException;
}
