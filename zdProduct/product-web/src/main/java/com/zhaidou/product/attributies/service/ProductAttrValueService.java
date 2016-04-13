/*
 * 文 件 名:  ProductAttrValueService.java
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
import com.zhaidou.product.attributies.model.ProductAttrValueModel;


/**
 * 属性值对象模型类
 * 用来操作属性值的增删查改
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductAttrValueService
{
	
	/**
	 * 添加属性值
	 * 
	 * @param productAttrValueModel 属性值模型
	 * @throws Exception
	 * @return null
	 * */
    public void addProductAttrValue(ProductAttrValueModel productAttrValueModel)throws Exception;

    
    /**
	 * 更新属性值
	 * 
	 * @param productAttrValueModel 属性值模型
	 * @throws Exception
	 * @return null
	 * */
    public void updateProductAttrValue(ProductAttrValueModel productAttrValueModel)throws Exception;

    
    /**
	 * 通过id获取属性值
	 * 
	 * @param id 属性值id
	 * @throws Exception
	 * @return ProductAttrValueModel
	 * */
    public ProductAttrValueModel getProductAttrValueById(Long id)throws Exception;

    
    /**
   	 * 获取属性值数量
   	 * 
   	 * @param productAttrValueModel 属性值模型
   	 * @throws Exception 
   	 * @return long 属性值总数
   	 * */
    public long getProductAttrValueCount(ProductAttrValueModel productAttrValueModel)throws Exception;
    
    
    /**
   	 * 获取属性值分页列表
   	 * 
   	 * @param productAttrValueModel 属性值模型
   	 * @throws Exception 
   	 * @return List<ProductAttrValueModel> 属性值集合
   	 * */
    public List<ProductAttrValueModel> getProductAttrValueListPage(ProductAttrValueModel productAttrValueModel,Page page)throws Exception;

    
    /**
	 * 通过id删除属性值
	 * 
	 * @param id 属性值id
	 * @throws Exception
	 * @return 
	 * */
    public void deleteById(Long id)throws Exception;

    /**
	 * 批量添加属性值
	 * 
	 * @param productAttrValueModelList 属性值模型
	 * @throws Exception
	 * @return null
	 * */
    public void addProductAttrValue(List<ProductAttrValueModel> productAttrValueModelList)throws Exception;

    /**
     * 通过属性项编码 分页查询属性值列表 
     * @param attrCode
     * @return
     * @throws Exception
     */
    public List<ProductAttrValueModel> getProductAttrValueListPageByAttrCode(String attrCode,Page page) throws Exception;
    /**
	 * 通过code获取属性值
	 * 
	 * @param id 属性值id
	 * @throws Exception
	 * @return ProductAttrValueModel
	 * */
    public ProductAttrValueModel getProductAttrValueByCode(String code)throws Exception;

    /**
     * 批量删除
     * 
     * @param list
     * @throws Exeption
     */
    public void deleteByAttrValueIds(List<Long> list) throws Exception; 
    
	/**
	 * 
	 * @return 
	 * @throws Exception
	 */
	public Long getMaxId() throws Exception;
}
