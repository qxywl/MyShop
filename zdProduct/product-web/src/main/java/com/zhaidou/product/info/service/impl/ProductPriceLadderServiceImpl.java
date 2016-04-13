/*
 * 文 件 名:  ProductPriceLadderServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  kaili
 * 修改时间:  2015-08-27
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

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductOperateDao;
import com.zhaidou.product.info.dao.ProductPriceLadderDao;
import com.zhaidou.product.info.model.ProductPriceLadderModel;
import com.zhaidou.product.info.service.ProductPriceLadderService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  mingbao
 * @version  [版本号, 2015-08-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceLadderService")
public class ProductPriceLadderServiceImpl implements ProductPriceLadderService
{
    private static final Log logger = LogFactory.getLog(ProductPriceLadderServiceImpl.class);

    @Resource
    private ProductPriceLadderDao  productPriceLadderDao;
    
    @Resource
    private ProductOperateDao productOperateDao;

    public void addProductPriceLadder(ProductPriceLadderModel productPriceLadderModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addProductPriceLadder Params-->" + JSON.toJSONString(productPriceLadderModel));
        }
        productPriceLadderDao.insert(productPriceLadderModel);
        
//      //修改ProductOperateModel表数据
//		ProductOperateModel productOperateModel = new ProductOperateModel();
//		productOperateModel.setProductId(productPriceLadderModel
//				.getProductId());
//		Double priceRate = (double) productPriceLadderModel
//				.getPriceLevel1().intValue();
//		productOperateModel.setProductPriceRate(priceRate / 10);		
//		productOperateDao.updateByProductId(productOperateModel);
        
        
    }

    public void updateProductPriceLadder(ProductPriceLadderModel productPriceLadderModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--addProductPriceLadder  Params-->" + JSON.toJSONString(productPriceLadderModel));
        }       
        productPriceLadderDao.update(productPriceLadderModel);
        
    }    

	public ProductPriceLadderModel getProductPriceLadderById(ProductPriceLadderModel productPriceLadderModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadderById Params-->" + JSON.toJSONString(productPriceLadderModel));
        }  
        ProductPriceLadderModel result = productPriceLadderDao.queryOne(productPriceLadderModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadderById result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public long getProductPriceLadderCount(ProductPriceLadderModel productPriceLadderModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadderCount Params-->" + JSON.toJSONString(productPriceLadderModel));
        }
        long result = productPriceLadderDao.countListPage(productPriceLadderModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadderCount result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public List<ProductPriceLadderModel> getProductPriceLadder(ProductPriceLadderModel productPriceLadderModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadder Params-->" + JSON.toJSONString(productPriceLadderModel));
        }
        long count = productPriceLadderDao.countListPage(productPriceLadderModel);
        List<ProductPriceLadderModel> result=null;
        if (count > 0) {
        	 page.setTotalCount(count);
        result= productPriceLadderDao.queryListPage(productPriceLadderModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--getProductPriceLadder result-->" + JSON.toJSONString(result));
        }
        return result;
    }

    public void deleteById(ProductPriceLadderModel productPriceLadderModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--deleteById Params-->" + JSON.toJSONString(productPriceLadderModel));
        }
        productPriceLadderDao.delete(productPriceLadderModel);
    }
    
    @Override
	public void deleteByIds(List<Long> ids) throws Exception {
		if (logger.isDebugEnabled())
        {
            logger.debug("--deleteByIds Params-->" + JSON.toJSONString(ids));
        }
		productPriceLadderDao.deleteByIds(ids); 
		
	}

	@Override
	public ProductPriceLadderModel getProductPriceLadderByProductCode(
			ProductPriceLadderModel productPriceLadderModel) throws Exception {
		 if (logger.isDebugEnabled())
	        {
	            logger.debug("--getProductPriceLadderByProductCode Params-->" + JSON.toJSONString(productPriceLadderModel));
	        }  
	        ProductPriceLadderModel result = productPriceLadderDao.queryOneByProductCode(productPriceLadderModel);
	        if (logger.isDebugEnabled())
	        {
	            logger.debug("--getProductPriceLadderByProductCode result-->" + JSON.toJSONString(result));
	        }
	        return result;
	}

	@Override
	public List<ProductPriceLadderModel> getProductPriceLadderByIdList(
			List<Long> ids) throws Exception {
		 if (logger.isDebugEnabled())
	        {
	            logger.debug("--getProductPriceLadder Params-->" + JSON.toJSONString(ids));
	        }
	     
	        List<ProductPriceLadderModel> result=null;
	       
	        	
	        result= productPriceLadderDao.getProductPriceLadderByIdList(ids);
	        
	        if (logger.isDebugEnabled())
	        {
	            logger.debug("--getProductPriceLadder result-->" + JSON.toJSONString(result));
	        }
	        return result;
	}

}
