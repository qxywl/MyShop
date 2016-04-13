package com.zhaidou.product.info.manager;

import java.util.List;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;

public interface ProductSkuManager {
    
    /**
     * 修改SKU
     *
     * @param productSkuModel
     * @return
     */
    public Boolean updateProductSku(ProductSkuModel productSkuModel);
    
    /**
     * 根据SKU ID 获取 SKU
     *
     * @param productSkuModel
     * @return
     */
    public ProductSkuModel getProductSku(ProductSkuModel productSkuModel);

	List<ProductSkuExportModel> getProductSkuExportModel(Page page,
			ProductSkuExportModel productSkuExportModel);
	
	public Long queryProductSkuExportCount(ProductSkuExportModel productSkuExportModel);
}
