/*
 * 文 件 名:  ProductSkuService.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;

import java.util.List;
import java.util.Map;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ProductSkuService
{
    public void addProductSku(ProductSkuModel productSkuModel)throws Exception;

    public void updateProductSku(ProductSkuModel productSkuModel)throws Exception;

    public ProductSkuModel getProductSkuById(ProductSkuModel productSkuModel)throws Exception;

    public long getProductSkuCount(ProductSkuModel productSkuModel)throws Exception;

    public List<ProductSkuModel> getProductSku(ProductSkuModel productSkuModel, Page page)throws Exception;

    public void deleteById(ProductSkuModel productSkuModel)throws Exception;
    
    
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    public Map<String, Object> getSkuByProductId(ProductSkuModel productSkuModel)throws Exception;
    
    public List<ProductSkuModel> getProductSkuListByProductId(List<Long> productIdList )throws Exception;

	List<ProductSkuExportModel> getProductSkuExportModel(
			ProductSkuExportModel productSkuExportModel, Page page)
			throws Exception;

	 public Long queryProductSkuExportCount(ProductSkuExportModel productSkuExportModel) throws Exception;
}
