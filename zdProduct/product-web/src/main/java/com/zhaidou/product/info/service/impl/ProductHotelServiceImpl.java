/*
 * 文 件 名:  ProductHotelServiceImpl.java
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
import com.zhaidou.product.info.dao.ProductHotelDao;
import com.zhaidou.product.info.model.ProductHotelModel;
import com.zhaidou.product.info.service.ProductHotelService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productHotelService")
public class ProductHotelServiceImpl implements ProductHotelService
{
    private static final Log logger = LogFactory.getLog(ProductHotelServiceImpl.class);

    @Resource
    private ProductHotelDao  productHotelDao;

    public void addProductHotel(ProductHotelModel productHotelModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel);
        }
        productHotelDao.insert(productHotelModel);
    }

    public void updateProductHotel(ProductHotelModel productHotelModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel);
        }
        productHotelDao.update(productHotelModel);
    }

    public ProductHotelModel getProductHotelById(ProductHotelModel productHotelModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel.getProductHotelId());
        }
        ProductHotelModel result = productHotelDao.queryOne(productHotelModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductHotelCount(ProductHotelModel productHotelModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel);
        }
        long result = productHotelDao.countListPage(productHotelModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductHotelModel> getProductHotel(ProductHotelModel productHotelModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel);
        }
        long count = productHotelDao.countListPage(productHotelModel);
        List<ProductHotelModel> result=null;
        if (count > 0) {
        result= productHotelDao.queryListPage(productHotelModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductHotelModel productHotelModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productHotelModel.getProductHotelId());
        }
        productHotelDao.delete(productHotelModel);
    }

}
