/*
 * 文 件 名:  ProductInfoLogServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductInfoLogDao;
import com.zhaidou.product.info.model.ProductInfoLogModel;
import com.zhaidou.product.info.service.ProductInfoLogService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoLogService")
public class ProductInfoLogServiceImpl implements ProductInfoLogService
{
    private static final Log logger = LogFactory.getLog(ProductInfoLogServiceImpl.class);

    @Resource
    private ProductInfoLogDao  productInfoLogDao;

    public void addProductInfoLog(ProductInfoLogModel productInfoLogModel)throws Exception
    {   
        
        productInfoLogDao.insert(productInfoLogModel);
    }

    public void updateProductInfoLog(ProductInfoLogModel productInfoLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoLogModel);
        }
        productInfoLogDao.update(productInfoLogModel);
    }

    public ProductInfoLogModel getProductInfoLogById(ProductInfoLogModel productInfoLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoLogModel);
        }  
        ProductInfoLogModel result = productInfoLogDao.queryOne(productInfoLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductInfoLogCount(ProductInfoLogModel productInfoLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoLogModel);
        }
        long result = productInfoLogDao.countListPage(productInfoLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductInfoLogModel> getProductInfoLog(ProductInfoLogModel productInfoLogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoLogModel);
        }
        long count = productInfoLogDao.countListPage(productInfoLogModel);
        List<ProductInfoLogModel> result=null;
        if (count > 0) {
            page.setTotalCount(count);
        result= productInfoLogDao.queryListPage(productInfoLogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductInfoLogModel productInfoLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoLogModel);
        }
        productInfoLogDao.delete(productInfoLogModel);
    }

}
