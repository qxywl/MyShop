/*
 * 文 件 名:  ProductPriceServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductPriceDao;
import com.zhaidou.product.info.model.ProductPriceModel;
import com.zhaidou.product.info.service.ProductPriceService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService
{
    private static final Log logger = LogFactory.getLog(ProductPriceServiceImpl.class);

    @Resource
    private ProductPriceDao  productPriceDao;

    public void addProductPrice(ProductPriceModel productPriceModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel);
        }
        productPriceDao.insert(productPriceModel);
    }

    public void updateProductPrice(ProductPriceModel productPriceModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel);
        }
        productPriceDao.update(productPriceModel);
    }

    public ProductPriceModel getProductPriceById(ProductPriceModel productPriceModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel.getProductPriceId());
        }     
        ProductPriceModel result = productPriceDao.queryOne(productPriceModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductPriceCount(ProductPriceModel productPriceModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel);
        }
        long result = productPriceDao.countListPage(productPriceModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductPriceModel> getProductPrice(ProductPriceModel productPriceModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel);
        }
        long count = productPriceDao.countListPage(productPriceModel);
        List<ProductPriceModel> result=null;
        if (count > 0) {
        result= productPriceDao.queryListPage(productPriceModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductPriceModel productPriceModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel.getProductPriceId());
        }
        productPriceDao.delete(productPriceModel);
    }

    @Override
    public List<ProductPriceModel> getProductPriceListByProductId(List<Long> productIdList) throws Exception {
        
        return productPriceDao.getProductPriceListByProductId(productIdList);
    }

}
