package com.zhaidou.product.info.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductSkuManager;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.service.ProductSkuService;

@Service("productSkuManager")
public class ProductSkuManagerImpl implements ProductSkuManager {
    private static Logger logger = Logger.getLogger(ProductSkuManagerImpl.class);
    @Resource
    private ProductSkuService productSkuService;
    /**
     * 修改SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public Boolean updateProductSku(ProductSkuModel productSkuModel) {
        boolean flag = true;
        try {
            productSkuService.updateProductSku(productSkuModel);
        } catch (Exception e) {
            logger.error("",e);
            flag = false;
        }
        return flag;
    }
    
    /**
     * 根据SKU ID 获取 SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public ProductSkuModel getProductSku(ProductSkuModel productSkuModel) {
        try {
            productSkuModel = productSkuService.getProductSkuById(productSkuModel);
        } catch (Exception e) {
            logger.error("",e);
            productSkuModel = null;
        }
        return productSkuModel;
    }
    
    @Override
	public List<ProductSkuExportModel> getProductSkuExportModel(Page page,
			ProductSkuExportModel productSkuExportModel) {
		 List<ProductSkuExportModel> skuList = null;
	        try {
	            skuList = productSkuService.getProductSkuExportModel(productSkuExportModel, page);
	        } catch (Exception e) {
	            logger.error("",e);
	        }
	        return skuList;
	}

    @Override
    public Long queryProductSkuExportCount(ProductSkuExportModel productSkuExportModel){
        try {
            return productSkuService.queryProductSkuExportCount(productSkuExportModel);
        } catch (Exception e) {
            logger.error("",e);
        }
        return null;
    }

}
