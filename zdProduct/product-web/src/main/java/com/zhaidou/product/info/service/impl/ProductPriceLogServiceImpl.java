/*
 * 文 件 名:  ProductPriceLogServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductPriceLogDao;
import com.zhaidou.product.info.model.ProductPriceLogModel;
import com.zhaidou.product.info.service.ProductPriceLogService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceLogService")
public class ProductPriceLogServiceImpl implements ProductPriceLogService
{
    private static final Log logger = LogFactory.getLog(ProductPriceLogServiceImpl.class);

    @Resource
    private ProductPriceLogDao  productPriceLogDao;

    public void addProductPriceLog(ProductPriceLogModel productPriceLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }
        productPriceLogDao.insert(productPriceLogModel);
    }

    public void updateProductPriceLog(ProductPriceLogModel productPriceLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }
        productPriceLogDao.update(productPriceLogModel);
    }

    public ProductPriceLogModel getProductPriceLogById(ProductPriceLogModel productPriceLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }  
        ProductPriceLogModel result = productPriceLogDao.queryOne(productPriceLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductPriceLogCount(ProductPriceLogModel productPriceLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }
        long result = productPriceLogDao.countListPage(productPriceLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductPriceLogModel> getProductPriceLog(ProductPriceLogModel productPriceLogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }
        long count = productPriceLogDao.countListPage(productPriceLogModel);
        page.setTotalCount(count);
        List<ProductPriceLogModel> result=null;
        if (count > 0) {
        result= productPriceLogDao.queryListPage(productPriceLogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductPriceLogModel productPriceLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceLogModel);
        }
        productPriceLogDao.delete(productPriceLogModel);
    }

}
