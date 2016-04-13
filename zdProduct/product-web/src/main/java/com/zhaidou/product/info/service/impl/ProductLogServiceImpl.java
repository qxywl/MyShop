/*
 * 文 件 名:  ProductLogServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductLogDao;
import com.zhaidou.product.info.model.ProductLogModel;
import com.zhaidou.product.info.service.ProductLogService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productLogService")
public class ProductLogServiceImpl implements ProductLogService
{
    private static final Log logger = LogFactory.getLog(ProductLogServiceImpl.class);

    @Resource
    private ProductLogDao  productLogDao;

    public void addProductLog(ProductLogModel productLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel);
        }
        productLogDao.insert(productLogModel);
    }

    public void updateProductLog(ProductLogModel productLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel);
        }
        productLogDao.update(productLogModel);
    }

    public ProductLogModel getProductLogById(ProductLogModel productLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel.getProductLogId());
        }     
        ProductLogModel result = productLogDao.queryOne(productLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductLogCount(ProductLogModel productLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel);
        }
        long result = productLogDao.countListPage(productLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductLogModel> getProductLog(ProductLogModel productLogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel);
        }
        long count = productLogDao.countListPage(productLogModel);
        List<ProductLogModel> result=null;
        if (count > 0) {
        result= productLogDao.queryListPage(productLogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductLogModel productLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productLogModel.getProductLogId());
        }
        productLogDao.delete(productLogModel);
    }

}
