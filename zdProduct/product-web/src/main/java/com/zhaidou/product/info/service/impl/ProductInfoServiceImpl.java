/*
 * 文 件 名:  ProductInfoServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductInfoDao;
import com.zhaidou.product.info.model.ProductInfoModel;
import com.zhaidou.product.info.service.ProductInfoService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService
{
    private static final Log logger = LogFactory.getLog(ProductInfoServiceImpl.class);

    @Resource
    private ProductInfoDao  productInfoDao;

    public void addProductInfo(ProductInfoModel productInfoModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel);
        }
        productInfoDao.insert(productInfoModel);
    }

    public void updateProductInfo(ProductInfoModel productInfoModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel);
        }
        productInfoDao.update(productInfoModel);
    }

    public ProductInfoModel getProductInfoById(ProductInfoModel productInfoModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel.getProductInfoId());
        }    
        ProductInfoModel result = productInfoDao.queryOne(productInfoModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductInfoCount(ProductInfoModel productInfoModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel);
        }
        long result = productInfoDao.countListPage(productInfoModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductInfoModel> getProductInfo(ProductInfoModel productInfoModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel);
        }
        long count = productInfoDao.countListPage(productInfoModel);
        List<ProductInfoModel> result=null;
        if (count > 0) {
        result= productInfoDao.queryListPage(productInfoModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductInfoModel productInfoModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoModel.getProductInfoId());
        }
        productInfoDao.delete(productInfoModel);
    }

    @Override
    public List<ProductInfoModel> getProductInfoListByProductId(List<Long> productIdList) throws Exception {
        
        return productInfoDao.getProductInfoListByProductId(productIdList);
    }

}
