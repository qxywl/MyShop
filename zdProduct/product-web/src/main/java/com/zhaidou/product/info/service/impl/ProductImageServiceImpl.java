/*
 * 文 件 名:  ProductImageServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductImageDao;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.service.ProductImageService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService
{
    private static final Log logger = LogFactory.getLog(ProductImageServiceImpl.class);

    @Resource
    private ProductImageDao  productImageDao;

    public void addProductImage(ProductImageModel productImageModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel);
        }
        productImageDao.insert(productImageModel);
    }

    public void updateProductImage(ProductImageModel productImageModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel);
        }
        productImageDao.update(productImageModel);
    }

    public ProductImageModel getProductImageById(ProductImageModel productImageModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel.getProductImageId());
        }      
        ProductImageModel result = productImageDao.queryOne(productImageModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductImageCount(ProductImageModel productImageModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel);
        }
        long result = productImageDao.countListPage(productImageModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductImageModel> getProductImage(ProductImageModel productImageModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel);
        }
        long count = productImageDao.countListPage(productImageModel);
        List<ProductImageModel> result=null;
        if (count > 0) {
        result= productImageDao.queryListPage(productImageModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductImageModel productImageModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel.getProductImageId());
        }
        productImageDao.delete(productImageModel);
    }
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public List<ProductImageModel> getImageBySkuId(ProductImageModel productImageModel) throws Exception {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productImageModel.getProductSkuId());
        }
        long count = productImageDao.countListPage(productImageModel);
        List<ProductImageModel> result=null;
        if (count > 0) {
        result= productImageDao.getImageBySkuId(productImageModel);
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

}
