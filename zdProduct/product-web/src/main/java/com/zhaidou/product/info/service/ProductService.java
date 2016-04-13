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
package com.zhaidou.product.info.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author wanghongtao
 * @version [版本号, 2015-03-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ProductService {
    public String addProduct(List<ProductModel> listProduct,String[] fileNames)throws Exception;

	public void updateProduct(ProductModel productModel, List<ProductSkuModel> listUpdate,List<ProductSkuModel> listInsert,String[] fileNames) throws Exception;

	public ProductModel getProductById(ProductModel productModel) throws Exception;

	public long getProductCount(ProductModel productModel) throws Exception;

	public List<ProductModel> getProduct(ProductModel productModel, Page page) throws Exception;

	public void deleteById(ProductModel productModel) throws Exception;

	public Map<String, Long> getMapByProductCodes(List<String> productCodeList) throws Exception;
	
	public void excelUpdate(List<ProductModel> listProduct) throws Exception;
	
	/**
	 * 根据 品牌编码 查询商品
	 *
	 * @param productModel
	 * @return
	 * @throws Exception
	 */
	public Integer getProductByBrandCode(ProductModel productModel) throws Exception;
	
	public ProductModel getProductByCode(String productCode) throws DaoException;
	
	public Integer getProductCode(ProductModel productModel) throws DaoException;
	
    public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel, int pageNo,
            int pageSize) throws Exception;

    public Long exportPrdouctCount(ProductSkuExportModel productSkuExportModel) throws Exception ;

	public List<ProductModel> getProductListAndMarkUpRate(
			ProductModel productModel, Page page) throws Exception;
}
