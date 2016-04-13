/*
 * 文 件 名:  ProductAttributeServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductAttributeDao;
import com.zhaidou.product.info.model.ProductAttributeModel;
import com.zhaidou.product.info.service.ProductAttributeService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl implements ProductAttributeService
{
    private static final Log logger = LogFactory.getLog(ProductAttributeServiceImpl.class);

    @Resource
    private ProductAttributeDao  productAttributeDao;

    public void addProductAttribute(ProductAttributeModel productAttributeModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttributeModel);
        }
        productAttributeDao.insert(productAttributeModel);
    }

    public void updateProductAttribute(ProductAttributeModel productAttributeModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttributeModel);
        }
        productAttributeDao.update(productAttributeModel);
    }

    public ProductAttributeModel getProductAttributeById(ProductAttributeModel productAttributeModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttributeModel.getProductAttributeId());
        }     
        ProductAttributeModel result = productAttributeDao.queryOne(productAttributeModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductAttributeCount(ProductAttributeModel productAttributeModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttributeModel);
        }
        long result = productAttributeDao.countListPage(productAttributeModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductAttributeModel> getProductAttribute(ProductAttributeModel productAttributeModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAttributeModel);
        }
        long count = productAttributeDao.countListPage(productAttributeModel);
        List<ProductAttributeModel> result=null;
        if (count > 0) {
        result= productAttributeDao.queryListPage(productAttributeModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductAttributeModel productAttributeModel)throws Exception
    {
        productAttributeDao.delete(productAttributeModel);
    }

    @Override
    public List<ProductAttributeModel> getAttributeByProductId(Long productId) throws Exception {
       
        return productAttributeDao.getAttributeByProductId(productId);
    }

    @Override
    public List<ProductAttributeModel> getAttributeListByProductId(List<Long> productIdList) throws Exception {
        
        return productAttributeDao.getAttributeListByProductId(productIdList);
    }


}
