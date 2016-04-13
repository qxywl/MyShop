/*
 * 文 件 名:  ProductBrandService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.brand.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.brand.model.CountryModel;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.model.ProductBrandPO;




/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductBrandService
{
  
	/**
	 * 添加商品品牌
	 * 
	 * @param productBrandModel 品牌模型
	 * @param file 图片文件
	 * @throws Exception 
	 * */
	public void addProductBrand(ProductBrandModel productBrandModel,MultipartFile file)throws ZhaidouRuntimeException,Exception;
	
	
	/**
	 * 更新商品品牌
	 * 
	 * @param productBrandModel 品牌模型
	 * @param userMap 用户map
	 * @param operateType 操作类型:1更新 2删除
	 * @param String reason 原因
	 * @return String 返回操作结果
	 * @throws Exception 
	 * */
    public String updateProductBrand(ProductBrandModel productBrandModel,Map<String,String> userMap,Long operateType,String reason,MultipartFile file)throws Exception;

    
    /**
	 * 更新商品品牌
	 * 
	 * @param id 品牌id
	 * @throws Exception 
	 * */
    public ProductBrandModel getProductBrandById(Long id)throws Exception;

  
    /**
   	 * 查询品牌总数
   	 * 
   	 * @param productBrandModel 品牌模型
   	 * @throws Exception 
   	 * */
    public long getProductBrandCount(ProductBrandModel productBrandModel)throws Exception;

    
    /**
   	 * 查询品牌分页列表
   	 * 
   	 * @param productBrandModel
   	 * @param Page
   	 * @throws Exception 
   	 * */
    public List<ProductBrandModel> getProductBrandListPage(ProductBrandModel productBrandModel,Page page)throws Exception;

    
    /**
     * 查询启用的品牌列表分页
     * @param productBrandModel
   	 * @param Page
   	 * @throws Exception 
     * */
    public List<ProductBrandModel> getEnableProductBrandListPage(ProductBrandModel productBrandModel,Page page)throws Exception;
    
    
    /**
     * 获取所有品牌
     * */
    public List<ProductBrandModel> getBrandAllList() throws Exception;
    
    
    /**
   	 * 删除品牌
   	 * 
   	 * @param id 品牌id
   	 * @throws Exception 
   	 * */
    public void deleteById(Integer id)throws Exception;
    
    
    /**
     * 读取导入品牌Excel文件
     *
     * @param filename 文件名
     * @param byte[] buf 文件字节数组
     * @param titleList Excel表格文件头数据格式列表
     * @param fieldList 要导入的对象属性字段列表
     * @param operator 操作者 
     * @throws java.io.IOException
     * @return String
     */
    public String importdBrand(String filename,  byte[] buf,List<String> titleList,List<String> fieldList,String operator)throws ZhaidouRuntimeException,Exception;
    
    
    /**
     * 
     * 通过品牌编码获取品牌
     * @param brandCode 品牌编号
     * @return ProductBrandModel
     * @throws Exception
     * */
    ProductBrandModel getBrandByCode(String brandCode) throws Exception ;
    
    
    /**
     * 通过品牌编码获取可用状态的品牌
     * 
     * @param brandCode 品牌编号
     * @return ProductBrandModel
     * @throws Exception
     * */
    ProductBrandModel getEnableBrandByCode(String brandCode) throws Exception ;
    
    
    /**
     * 
     * 通过名称获取品牌
     * @param brandName 品牌名称
     * @return ProductBrandModel
     * @throws Exception
     * */
    ProductBrandModel getBrandByName(String brandName) throws Exception;
    
    
    /**
     * 通过名称获取可用状态的品牌
     * 
     * @param brandName 品牌名称
     * @return ProductBrandModel
     * @throws Exception
     * */
    ProductBrandModel getEnableBrandByName(String brandName) throws Exception;
    
    
    /**
     * 通过品牌名称判断品牌是否已经存在了
     * @param brandName 品牌名称
     * @return boolean
     * */
    public boolean checkExistByName(String brandName)throws Exception;   
    
    
    /**
     * 格式化品牌输出格式
     * @param brandList 品牌集合
     * @param countryList 国家列表
     * @return List<ProductBrandPO>
     * @throws Exception 
     * */
    public List<ProductBrandPO> formatBrandExport(List<ProductBrandModel> brandList,List<CountryModel> countryList) throws Exception;
    
    
    /**
     * 格式化品牌输出格式
     * @return List<ProductBrandPO>
     * @throws Exception 
     * */
    public List<ProductBrandPO> exportBrand() throws Exception;
    


    
}
