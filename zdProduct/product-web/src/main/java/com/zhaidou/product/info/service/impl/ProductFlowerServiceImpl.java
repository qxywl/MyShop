/*
 * 文 件 名:  ProductFlowerServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductFlowerDao;
import com.zhaidou.product.info.model.ProductFlowerModel;
import com.zhaidou.product.info.service.ProductFlowerService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productFlowerService")
public class ProductFlowerServiceImpl implements ProductFlowerService
{
    private static final Log logger = LogFactory.getLog(ProductFlowerServiceImpl.class);

    @Resource
    private ProductFlowerDao  productFlowerDao;

    public void addProductFlower(ProductFlowerModel productFlowerModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel);
        }
        productFlowerDao.insert(productFlowerModel);
    }

    public void updateProductFlower(ProductFlowerModel productFlowerModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel);
        }
        productFlowerDao.update(productFlowerModel);
    }

    public ProductFlowerModel getProductFlowerById(ProductFlowerModel productFlowerModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel.getProductFlowerId());
        }    
        ProductFlowerModel result = productFlowerDao.queryOne(productFlowerModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductFlowerCount(ProductFlowerModel productFlowerModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel);
        }
        long result = productFlowerDao.countListPage(productFlowerModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductFlowerModel> getProductFlower(ProductFlowerModel productFlowerModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel);
        }
        long count = productFlowerDao.countListPage(productFlowerModel);
        List<ProductFlowerModel> result=null;
        if (count > 0) {
        result= productFlowerDao.queryListPage(productFlowerModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductFlowerModel productFlowerModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productFlowerModel.getProductFlowerId());
        }
        productFlowerDao.delete(productFlowerModel);
    }

}
