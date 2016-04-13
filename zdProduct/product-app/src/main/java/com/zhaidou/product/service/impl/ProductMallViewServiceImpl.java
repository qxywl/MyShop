/*
 * 文 件 名:  ProductMallViewServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  mingbao
 * 修改时间:  2015-10-01
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductMallViewDao;
import com.zhaidou.product.model.base.ProductMallViewModel;
import com.zhaidou.product.model.mall.ProductModel;
import com.zhaidou.product.service.ProductMallViewService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-10-01]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productMallViewService")
public class ProductMallViewServiceImpl implements ProductMallViewService
{
    private static final Log logger = LogFactory.getLog(ProductMallViewServiceImpl.class);

    @Resource
    private ProductMallViewDao  productMallViewDao;

    public void addProductMallView(ProductMallViewModel productMallViewModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addProductMallView Params-->" + JSON.toJSONString(productMallViewModel));
        }
        productMallViewDao.insert(productMallViewModel);
    }

    public void updateProductMallViewById(ProductMallViewModel productMallViewModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addProductMallView  Params-->" + JSON.toJSONString(productMallViewModel));
        }
        productMallViewDao.update(productMallViewModel);
    }

    public ProductMallViewModel getProductMallViewById(ProductMallViewModel productMallViewModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallViewById Params-->" + JSON.toJSONString(productMallViewModel));
        }  
        ProductMallViewModel result = productMallViewDao.queryOne(productMallViewModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallViewById result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public long getProductMallViewCount(ProductMallViewModel productMallViewModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallViewCount Params-->" + JSON.toJSONString(productMallViewModel));
        }
        long result = productMallViewDao.countListPage(productMallViewModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallViewCount result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public List<ProductMallViewModel> getProductMallView(ProductMallViewModel productMallViewModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallView Params-->" + JSON.toJSONString(productMallViewModel));
        }
        long count = productMallViewDao.countListPage(productMallViewModel);
        List<ProductMallViewModel> result=null;
        if (count > 0) {
        	 page.setTotalCount(count);
        result= productMallViewDao.queryListPage(productMallViewModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductMallView result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public void deleteById(ProductMallViewModel productMallViewModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--deleteById Params-->" + JSON.toJSONString(productMallViewModel));
        }
        productMallViewDao.delete(productMallViewModel);
    }

	@Override
	public List<ProductMallViewModel> getProductMallViewByProductCodes(
			List<String> productCodeList) throws Exception {
		// TODO Auto-generated method stub
		 ProductMallViewModel queryModel = new ProductMallViewModel();
	        if (productCodeList != null && productCodeList.size() > 0) {
	            queryModel.setProductCodeList(productCodeList);
	        }	        
	        List<ProductMallViewModel> resultList = null;	       
	        resultList = productMallViewDao.getProductMallViewByProductCodeList(queryModel);
	        return resultList;
	}   
    

}
