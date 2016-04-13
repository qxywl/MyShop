/*
 * 文 件 名:  ProductInfoAuthServiceImpl.java
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
import com.zhaidou.product.dao.ProductInfoAuthDao;
import com.zhaidou.product.model.ProductInfoAuthModel;
import com.zhaidou.product.service.ProductInfoAuthService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoAuthService")
public class ProductInfoAuthServiceImpl implements ProductInfoAuthService
{
    private static final Log logger = LogFactory.getLog(ProductInfoAuthServiceImpl.class);

    @Resource
    private ProductInfoAuthDao  productInfoAuthDao;

    public void addProductInfoAuth(ProductInfoAuthModel productInfoAuthModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }
        productInfoAuthDao.insert(productInfoAuthModel);
    }

    public void updateProductInfoAuth(ProductInfoAuthModel productInfoAuthModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }
        productInfoAuthDao.update(productInfoAuthModel);
    }

    public ProductInfoAuthModel getProductInfoAuthById(ProductInfoAuthModel productInfoAuthModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }  
        ProductInfoAuthModel result = productInfoAuthDao.queryOne(productInfoAuthModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductInfoAuthCount(ProductInfoAuthModel productInfoAuthModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }
        long result = productInfoAuthDao.countListPage(productInfoAuthModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductInfoAuthModel> getProductInfoAuth(ProductInfoAuthModel productInfoAuthModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }
        long count = productInfoAuthDao.countListPage(productInfoAuthModel);
        List<ProductInfoAuthModel> result=null;
        if (count > 0) {
        page.setTotalCount(count);
        result= productInfoAuthDao.queryListPage(productInfoAuthModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductInfoAuthModel productInfoAuthModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productInfoAuthModel);
        }
        productInfoAuthDao.delete(productInfoAuthModel);
    }
    
    /**
     * 根据商品编号获取 审核记录
     *
     * @param productInfoAuthModel
     * @return
     */
    @Override
    public List<ProductInfoAuthModel> getInfoAuthByProductCode(String productCode)throws Exception {
        ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
        productInfoAuthModel.setProductCode(productCode);
        return productInfoAuthDao.getInfoAuthByProductCode(productInfoAuthModel);
    }

    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        productInfoAuthDao.updateStatusList(map);
    }

}
