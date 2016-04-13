/*
 * 文 件 名:  ProductStockLogServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductStockLogDao;
import com.zhaidou.product.info.model.ProductStockLogModel;
import com.zhaidou.product.info.service.ProductStockLogService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productStockLogService")
public class ProductStockLogServiceImpl implements ProductStockLogService
{
    private static final Log logger = LogFactory.getLog(ProductStockLogServiceImpl.class);

    @Resource
    private ProductStockLogDao  productStockLogDao;

    public void addProductStockLog(ProductStockLogModel productStockLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }
        productStockLogDao.insert(productStockLogModel);
    }

    public void updateProductStockLog(ProductStockLogModel productStockLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }
        productStockLogDao.update(productStockLogModel);
    }

    public ProductStockLogModel getProductStockLogById(ProductStockLogModel productStockLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }  
        ProductStockLogModel result = productStockLogDao.queryOne(productStockLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductStockLogCount(ProductStockLogModel productStockLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }
        long result = productStockLogDao.countListPage(productStockLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductStockLogModel> getProductStockLog(ProductStockLogModel productStockLogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }
        long count = productStockLogDao.countListPage(productStockLogModel);
        List<ProductStockLogModel> result=null;
        if (count > 0) {
        result= productStockLogDao.queryListPage(productStockLogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductStockLogModel productStockLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productStockLogModel);
        }
        productStockLogDao.delete(productStockLogModel);
    }

}
