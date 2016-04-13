/*
 * 文 件 名:  ProductOtherJobServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-04-20
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductOtherJobDao;
import com.zhaidou.product.info.model.ProductOtherJobModel;
import com.zhaidou.product.info.service.ProductOtherJobService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-04-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productOtherJobService")
public class ProductOtherJobServiceImpl implements ProductOtherJobService
{
    private static final Log logger = LogFactory.getLog(ProductOtherJobServiceImpl.class);

    @Resource
    private ProductOtherJobDao  productOtherJobDao;

    public void addProductOtherJob(ProductOtherJobModel productOtherJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }
        productOtherJobDao.insert(productOtherJobModel);
    }

    public void updateProductOtherJob(ProductOtherJobModel productOtherJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }
        productOtherJobDao.update(productOtherJobModel);
    }

    public ProductOtherJobModel getProductOtherJobById(ProductOtherJobModel productOtherJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }  
        ProductOtherJobModel result = productOtherJobDao.queryOne(productOtherJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductOtherJobCount(ProductOtherJobModel productOtherJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }
        long result = productOtherJobDao.countListPage(productOtherJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductOtherJobModel> getProductOtherJob(ProductOtherJobModel productOtherJobModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }
        long count = productOtherJobDao.countListPage(productOtherJobModel);
        List<ProductOtherJobModel> result=null;
        if (count > 0) {
        result= productOtherJobDao.queryListPage(productOtherJobModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductOtherJobModel productOtherJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOtherJobModel);
        }
        productOtherJobDao.delete(productOtherJobModel);
    }

}
