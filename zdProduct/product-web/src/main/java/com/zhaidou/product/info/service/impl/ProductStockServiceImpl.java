/*
 * 文 件 名:  ProductStockServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
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
import com.zhaidou.product.info.dao.ProductStockDao;
import com.zhaidou.product.info.model.ProductStockModel;
import com.zhaidou.product.info.service.ProductStockService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productStockService")
public class ProductStockServiceImpl implements ProductStockService
{
    private static final Log logger = LogFactory.getLog(ProductStockServiceImpl.class);

    @Resource
    private ProductStockDao  productStockDao;

    public void addProductStock(ProductStockModel productStockModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }
        productStockDao.insert(productStockModel);
    }

    public void updateProductStock(ProductStockModel productStockModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }
        productStockDao.update(productStockModel);
    }

    public ProductStockModel getProductStockById(ProductStockModel productStockModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }  
        ProductStockModel result = productStockDao.queryOne(productStockModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductStockCount(ProductStockModel productStockModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }
        long result = productStockDao.countListPage(productStockModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductStockModel> getProductStock(ProductStockModel productStockModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }
        long count = productStockDao.countListPage(productStockModel);
        List<ProductStockModel> result=null;
        if (count > 0) {
        result= productStockDao.queryListPage(productStockModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductStockModel productStockModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockModel);
        }
        productStockDao.delete(productStockModel);
    }

    @Override
    public List<ProductStockModel> getProductStockListByProductId(List<Long> productIdList) throws Exception {
        
        return productStockDao.getProductStockListByProductId(productIdList);
    }

}
