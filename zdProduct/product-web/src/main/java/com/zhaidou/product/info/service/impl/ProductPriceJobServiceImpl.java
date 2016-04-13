/*
 * 文 件 名:  ProductPriceJobServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductPriceJobDao;
import com.zhaidou.product.info.model.ProductPriceJobModel;
import com.zhaidou.product.info.service.ProductPriceJobService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceJobService")
public class ProductPriceJobServiceImpl implements ProductPriceJobService
{
    private static final Log logger = LogFactory.getLog(ProductPriceJobServiceImpl.class);

    @Resource
    private ProductPriceJobDao  productPriceJobDao;

    public void addProductPriceJob(ProductPriceJobModel productPriceJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }
        productPriceJobDao.insert(productPriceJobModel);
    }

    public void updateProductPriceJob(ProductPriceJobModel productPriceJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }
        productPriceJobDao.update(productPriceJobModel);
    }

    public ProductPriceJobModel getProductPriceJobById(ProductPriceJobModel productPriceJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }  
        ProductPriceJobModel result = productPriceJobDao.queryOne(productPriceJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductPriceJobCount(ProductPriceJobModel productPriceJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }
        long result = productPriceJobDao.countListPage(productPriceJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductPriceJobModel> getProductPriceJob(ProductPriceJobModel productPriceJobModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }
        long count = productPriceJobDao.countListPage(productPriceJobModel);
        List<ProductPriceJobModel> result=null;
        if (count > 0) {
        result= productPriceJobDao.queryListPage(productPriceJobModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductPriceJobModel productPriceJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceJobModel);
        }
        productPriceJobDao.delete(productPriceJobModel);
    }

}
