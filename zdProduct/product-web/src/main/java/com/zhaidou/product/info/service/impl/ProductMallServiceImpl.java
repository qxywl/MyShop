/*
 * 文 件 名:  ProductMallServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductMallDao;
import com.zhaidou.product.info.model.ProductMallModel;
import com.zhaidou.product.info.service.ProductMallService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productMallService")
public class ProductMallServiceImpl implements ProductMallService
{
    private static final Log logger = LogFactory.getLog(ProductMallServiceImpl.class);

    @Resource
    private ProductMallDao  productMallDao;

    public void addProductMall(ProductMallModel productMallModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel);
        }
        productMallDao.insert(productMallModel);
    }

    public void updateProductMall(ProductMallModel productMallModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel);
        }
        productMallDao.update(productMallModel);
    }

    public ProductMallModel getProductMallById(ProductMallModel productMallModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel.getProductMallId());
        }    
        ProductMallModel result = productMallDao.queryOne(productMallModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductMallCount(ProductMallModel productMallModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel);
        }
        long result = productMallDao.countListPage(productMallModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductMallModel> getProductMall(ProductMallModel productMallModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel);
        }
        long count = productMallDao.countListPage(productMallModel);
        List<ProductMallModel> result=null;
        if (count > 0) {
        result= productMallDao.queryListPage(productMallModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductMallModel productMallModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productMallModel.getProductMallId());
        }
        productMallDao.delete(productMallModel);
    }

    @Override
    public List<ProductMallModel> getProductMallListByProductId(List<Long> productIdList) throws Exception {
        
        return productMallDao.getProductMallListByProductId(productIdList);
    }

}
