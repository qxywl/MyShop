/*
 * 文 件 名:  ProductService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.model.ProductModel;
import com.zhaidou.product.model.ProductSkuModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ProductService {
    public ProductModel addProduct(ProductModel productModel)throws Exception;

	public ProductModel updateProduct(ProductModel productModel,List<ProductSkuModel> listInsert,Long type) throws Exception;
	//修改状态
	public void updateProduct(ProductModel productModel) throws Exception;
	
	public ProductModel getProductById(ProductModel productModel) throws Exception;

	public long getProductCount(ProductModel productModel) throws Exception;

	public List<ProductModel> getProduct(ProductModel productModel, Page page) throws Exception;

	public void deleteById(ProductModel productModel) throws Exception;

	public Map<String, Long> getMapByProductCodes(List<String> productCodeList) throws Exception;
	
	/**
	 * 根据 品牌编码 查询商品
	 *
	 * @param productModel
	 * @return
	 * @throws Exception
	 */
	public Integer getProductByBrandCode(ProductModel productModel) throws Exception;

}
