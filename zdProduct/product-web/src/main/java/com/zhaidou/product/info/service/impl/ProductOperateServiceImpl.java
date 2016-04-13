/*
 * 文 件 名:  ProductOperateServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductOperateDao;
import com.zhaidou.product.info.model.ProductOperateModel;
import com.zhaidou.product.info.service.ProductOperateService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productOperateService")
public class ProductOperateServiceImpl implements ProductOperateService
{
    private static final Log logger = LogFactory.getLog(ProductOperateServiceImpl.class);

    @Resource
    private ProductOperateDao  productOperateDao;

    public void addProductOperate(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel);
        }
        productOperateDao.insert(productOperateModel);
    }

    public void updateProductOperate(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel);
        }
        productOperateDao.update(productOperateModel);
    }
    
    
    public void updateProductOperateByProductId(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel);
        }
        productOperateDao.updateByProductId(productOperateModel);
    }

    public ProductOperateModel getProductOperateById(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel.getProductId());
        }   
        ProductOperateModel result = productOperateDao.queryOne(productOperateModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductOperateCount(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel);
        }
        long result = productOperateDao.countListPage(productOperateModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductOperateModel> getProductOperate(ProductOperateModel productOperateModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel);
        }
        long count = productOperateDao.countListPage(productOperateModel);
        List<ProductOperateModel> result=null;
        if (count > 0) {
        result= productOperateDao.queryListPage(productOperateModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductOperateModel productOperateModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productOperateModel.getProductOperateId());
        }
        productOperateDao.delete(productOperateModel);
    }

    @Override
    public List<ProductOperateModel> getProductOperateListByProductId(List<Long> productIdList) throws Exception {
       
        return productOperateDao.getProductOperateListByProductId(productIdList);
    }

}
