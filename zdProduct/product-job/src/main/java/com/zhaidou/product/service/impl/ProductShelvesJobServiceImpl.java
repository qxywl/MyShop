/*
 * 文 件 名:  ProductShelvesJobServiceImpl.java
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
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductShelvesJobDao;
import com.zhaidou.product.model.ProductShelvesJobModel;
import com.zhaidou.product.service.ProductShelvesJobService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productShelvesJobService")
public class ProductShelvesJobServiceImpl implements ProductShelvesJobService
{
    private static final Log logger = LogFactory.getLog(ProductShelvesJobServiceImpl.class);

    @Resource
    private ProductShelvesJobDao  productShelvesJobDao;

    public void addProductShelvesJob(ProductShelvesJobModel productShelvesJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }
        productShelvesJobDao.insert(productShelvesJobModel);
    }

    public void updateProductShelvesJob(ProductShelvesJobModel productShelvesJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }
        productShelvesJobDao.update(productShelvesJobModel);
    }

    public ProductShelvesJobModel getProductShelvesJobById(ProductShelvesJobModel productShelvesJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }  
        ProductShelvesJobModel result = productShelvesJobDao.queryOne(productShelvesJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductShelvesJobCount(ProductShelvesJobModel productShelvesJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }
        long result = productShelvesJobDao.countListPage(productShelvesJobModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductShelvesJobModel> getProductShelvesJob(ProductShelvesJobModel productShelvesJobModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }
        List<ProductShelvesJobModel> result=null;
        result= productShelvesJobDao.queryListPage(productShelvesJobModel, page.getPageNum(), page.getNumPerPage());
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductShelvesJobModel productShelvesJobModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesJobModel);
        }
        productShelvesJobDao.delete(productShelvesJobModel);
    }
    
    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        productShelvesJobDao.updateStatusList(map);
    }

    @Override
    public void addShelvesJobList(List<ProductShelvesJobModel> shelvesJobList) throws Exception {
        productShelvesJobDao.addShelvesJobList(shelvesJobList);
    }

    @Override
    public void deleteByIds(List<Long> jobIds) throws Exception {
        productShelvesJobDao.deleteByIds(jobIds);
    }
}
