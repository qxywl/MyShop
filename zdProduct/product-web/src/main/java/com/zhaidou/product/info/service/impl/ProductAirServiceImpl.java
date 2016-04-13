/*
 * 文 件 名:  ProductAirServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductAirDao;
import com.zhaidou.product.info.model.ProductAirModel;
import com.zhaidou.product.info.service.ProductAirService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productAirService")
public class ProductAirServiceImpl implements ProductAirService
{
    private static final Log logger = LogFactory.getLog(ProductAirServiceImpl.class);

    @Resource
    private ProductAirDao  productAirDao;

    public void addProductAir(ProductAirModel productAirModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel);
        }
        productAirDao.insert(productAirModel);
    }

    public void updateProductAir(ProductAirModel productAirModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel);
        }
        productAirDao.update(productAirModel);
    }

    public ProductAirModel getProductAirById(ProductAirModel productAirModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel.getProductAirId());
        }    
        ProductAirModel result = productAirDao.queryOne(productAirModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductAirCount(ProductAirModel productAirModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel);
        }
        long result = productAirDao.countListPage(productAirModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductAirModel> getProductAir(ProductAirModel productAirModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel);
        }
        long count = productAirDao.countListPage(productAirModel);
        List<ProductAirModel> result=null;
        if (count > 0) {
        result= productAirDao.queryListPage(productAirModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductAirModel productAirModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productAirModel.getProductAirId());
        }
        productAirDao.delete(productAirModel);
    }

}
