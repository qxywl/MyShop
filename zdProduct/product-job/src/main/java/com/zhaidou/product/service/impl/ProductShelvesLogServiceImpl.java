/*
 * 文 件 名:  ProductShelvesLogServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductShelvesLogDao;
import com.zhaidou.product.model.ProductShelvesLogModel;
import com.zhaidou.product.service.ProductShelvesLogService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productShelvesLogService")
public class ProductShelvesLogServiceImpl implements ProductShelvesLogService
{
    private static final Log logger = LogFactory.getLog(ProductShelvesLogServiceImpl.class);

    @Resource
    private ProductShelvesLogDao  productShelvesLogDao;

    public void addProductShelvesLog(ProductShelvesLogModel productShelvesLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }
        productShelvesLogDao.insert(productShelvesLogModel);
    }

    public void updateProductShelvesLog(ProductShelvesLogModel productShelvesLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }
        productShelvesLogDao.update(productShelvesLogModel);
    }

    public ProductShelvesLogModel getProductShelvesLogById(ProductShelvesLogModel productShelvesLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }  
        ProductShelvesLogModel result = productShelvesLogDao.queryOne(productShelvesLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductShelvesLogCount(ProductShelvesLogModel productShelvesLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }
        long result = productShelvesLogDao.countListPage(productShelvesLogModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductShelvesLogModel> getProductShelvesLog(ProductShelvesLogModel productShelvesLogModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }
        long count = productShelvesLogDao.countListPage(productShelvesLogModel);
        List<ProductShelvesLogModel> result=null;
        if (count > 0) {
        page.setTotalCount(count);
        result= productShelvesLogDao.queryListPage(productShelvesLogModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductShelvesLogModel productShelvesLogModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesLogModel);
        }
        productShelvesLogDao.delete(productShelvesLogModel);
    }

    @Override
    public void addShelvesLogList(List<ProductShelvesLogModel> shelvesLogList) throws Exception {
        productShelvesLogDao.addShelvesLogList(shelvesLogList);
    }

}
