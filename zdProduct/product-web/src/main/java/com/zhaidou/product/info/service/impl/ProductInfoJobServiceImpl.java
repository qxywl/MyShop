/*
 * 文 件 名:  ProductInfoJobServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductInfoJobDao;
import com.zhaidou.product.info.model.ProductInfoJobModel;
import com.zhaidou.product.info.service.ProductInfoJobService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoJobService")
public class ProductInfoJobServiceImpl implements ProductInfoJobService
{
    private static final Log logger = LogFactory.getLog(ProductInfoJobServiceImpl.class);

    @Resource
    private ProductInfoJobDao  productInfoJobDao;

    public void addProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }
        productInfoJobDao.insert(productInfoJobModel);
    }

    public void updateProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }
        productInfoJobDao.update(productInfoJobModel);
    }

    public ProductInfoJobModel getProductInfoJobById(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }  
        ProductInfoJobModel result = productInfoJobDao.queryOne(productInfoJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductInfoJobCount(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }
        long result = productInfoJobDao.countListPage(productInfoJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductInfoJobModel> getProductInfoJob(ProductInfoJobModel productInfoJobModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }
        long count = productInfoJobDao.countListPage(productInfoJobModel);
        List<ProductInfoJobModel> result=null;
        if (count > 0) {
        result= productInfoJobDao.queryListPage(productInfoJobModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoJobModel);
        }
        productInfoJobDao.delete(productInfoJobModel);
    }

}
