/*
 * 文 件 名:  ProductAttrService.java
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

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.attributies.model.ProductAttrModel;


/**
 * 商品属性类
 * 增,删,查,改,分页列表查询等操作
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttrService
{
	/**
	 * 添加商品属性
	 * 
	 * @param productAttrModel 商品属性模型
	 * @throws Exception
	 * */
    public void addProductAttr(ProductAttrModel productAttrModel)throws Exception;

    
    /**
	 * 更新商品属性
	 * 
	 * @param productAttrModel 商品属性模型
	 * @throws Exception
	 * */
    public void updateProductAttr(ProductAttrModel productAttrModel)throws Exception;

    
    /**
   	 * 通过id查询商品属性信息
   	 * 
   	 * @param id 商品属性id
   	 * @throws Exception
   	 * */
    public ProductAttrModel getProductAttrById(Long id)throws Exception;

    
    /**
   	 * 查询商品属性总数
   	 * 
   	 * @param productAttrModeld 商品属性模型
   	 * @throws Exception
   	 * */
    public long getProductAttrCount(ProductAttrModel productAttrModel)throws Exception;

    
    /**
   	 * 查询品牌属性分页列表
   	 * 
   	 * @param productAttrModel 商品属性模型
   	 * @param Page 分页
   	 * @throws Exception 
   	 * */
    public List<ProductAttrModel> getProductAttrListPage(ProductAttrModel productAttrModel, Page page)throws Exception;

    
    /**
   	 * 删除品牌
   	 * 
   	 * @param id 品牌属性id
   	 * @throws Exception 
   	 * */
    public void deleteById(Long id)throws Exception;
    
    /**
	 * 根据属性组编码 查询相关的属性项
	 * 
	 * @param groupCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryAttrListPageByGroupCode(String groupCode, Page page) throws Exception;

	
	/**
	 * 根据属性项名 模糊查询属性项列表
	 * @param attrName
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryAttrListByAttrName(String attrName) throws Exception;
	
	/**
	 * 查询与属性组相关联的属性
	 * @param model 不代表一个Model,且用作传递数据 ----》其中的attrId为传入的groupId的值
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryGroupRelationAttrList(ProductAttrModel model,Page page)throws Exception;
	
	/**
	 * 查询与属性组未相关联的属性
	 * @param model 不代表一个Model,且用作传递数据 ----》其中的attrId为传入的groupId的值
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ProductAttrModel> queryGroupRelationAttrNotBundList(ProductAttrModel model,Page page)throws Exception;
	
	/**
   	 * 通过code查询商品属性信息
   	 * 
   	 * @param id 商品属性id
   	 * @throws Exception
   	 * */
    public ProductAttrModel getProductAttrByCode(String code)throws Exception;

    /**
     * 批量删除属性
     * @param attrIds
     * @return 
     * @throws Exception 
     */
	public boolean deleteByAttrIds(String[] attrIds) throws Exception;


	/**
	 * 
	 * @return 
	 * @throws Exception
	 */
	public Long getMaxId() throws Exception;

	/**
	 * 判断当前属性名是否存在
	 * @param attrName
	 * @return
	 * @throws Exception
	 */
	public Boolean isExistThisName( String attrName ) throws Exception;
}
