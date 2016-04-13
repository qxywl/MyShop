/*
 * 文 件 名:  ProductShelvesServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
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
import com.zhaidou.product.dao.ProductShelvesDao;
import com.zhaidou.product.dao.ProductShelvesLogDao;
import com.zhaidou.product.model.ProductShelvesModel;
import com.zhaidou.product.service.ProductShelvesService;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productShelvesService")
public class ProductShelvesServiceImpl implements ProductShelvesService
{
    private static final Log logger = LogFactory.getLog(ProductShelvesServiceImpl.class);

    @Resource
    private ProductShelvesDao  productShelvesDao;
    @Resource
    private ProductShelvesLogDao productShelvesLogDao;
    

    public void addProductShelves(ProductShelvesModel productShelvesModel)throws Exception
    {
    	 if (logger.isDebugEnabled())
         {
             logger.debug("--Params-->" + productShelvesModel);
         }
         productShelvesDao.insert(productShelvesModel);
    }

    public void updateProductShelves(ProductShelvesModel productShelvesModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel);
        }
        productShelvesDao.update(productShelvesModel);
    }

    public ProductShelvesModel getProductShelvesById(ProductShelvesModel productShelvesModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel.getProductShelvesId());
        }     
        ProductShelvesModel result = productShelvesDao.queryOne(productShelvesModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductShelvesCount(ProductShelvesModel productShelvesModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel);
        }
        long result = productShelvesDao.countListPage(productShelvesModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductShelvesModel> getProductShelves(ProductShelvesModel productShelvesModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel);
        }
        long count = productShelvesDao.countListPage(productShelvesModel);
        List<ProductShelvesModel> result=null;
        if (count > 0) {
        page.setTotalCount(count);
        result= productShelvesDao.queryListPage(productShelvesModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductShelvesModel productShelvesModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel.getProductShelvesId());
        }
        productShelvesDao.delete(productShelvesModel);
    }

	@Override
	public boolean getProductIntegrity(ProductShelvesModel productShelvesModel)
			throws Exception {
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productShelvesModel.getProductShelvesId());
        }
        return productShelvesDao.getProductIntegrity(productShelvesModel);
	}

    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        productShelvesDao.updateStatusList(map);
    }
}
