/*
 * 文 件 名:  ProductDAO.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.dao;

import java.util.List;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.dao.IDao;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductDao extends IDao
{

	public List<ProductModel> getListByProductCodes(List<String> productCodeList) throws DaoException;
	
	public Integer getProductByBrandCode(ProductModel productModel) throws DaoException;
	
	public ProductModel getProductByCode(String productCode) throws DaoException;
	
	public Integer getProductCode(ProductModel productModel) throws DaoException;
	
	/**
	 * 导出商品级别
	 *
	 * @param productSkuExportModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	 public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel,int pageNo, int pageSize) throws Exception;

	 public <T> Long exportPrdouctCount(T t) throws Exception;

	public long countListPageAndMarkUpRate(ProductModel productModel) throws Exception;

	public List<ProductModel> queryListPageAndMarkUpRate(
			ProductModel productModel, int pageNum, int numPerPage) throws Exception;
    
}
