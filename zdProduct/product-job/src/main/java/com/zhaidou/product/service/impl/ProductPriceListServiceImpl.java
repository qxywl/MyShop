/*
 * 文 件 名:  ProductPriceListServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductPriceJobDao;
import com.zhaidou.product.dao.ProductPriceListDao;
import com.zhaidou.product.dao.ProductPriceLogDao;
import com.zhaidou.product.model.ProductPriceListModel;
import com.zhaidou.product.model.ProductPriceLogModel;
import com.zhaidou.product.model.ProductPriceModel;
import com.zhaidou.product.service.ProductPriceListService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productPriceListService")
public class ProductPriceListServiceImpl implements ProductPriceListService
{
    private static final Log logger = LogFactory.getLog(ProductPriceListServiceImpl.class);

    @Resource
    private ProductPriceListDao  productPriceListDao;
    
    @Resource
    private ProductPriceJobDao  productPriceJobDao;
    
    @Resource
    private ProductPriceLogDao  productPriceLogDao;
    
    

    public void addProductPriceList(List<List<String>> list)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + list);
        }
        if (list != null) {
			for (int i = 1; i < list.size(); i++) {
				    List<String> cellList = list.get(i);
					ProductPriceListModel productPriceListModel = new ProductPriceListModel();
					productPriceListModel.setCostPrice(Double.valueOf(cellList.get(1)));
					productPriceListModel.setCreateBy(1l);
					productPriceListModel.setCreateTime(new Date().getTime()/1000);
					productPriceListModel.setCreateUserName("xxxx");
					productPriceListModel.setMarketPrice(Double.valueOf(cellList.get(4)));
					productPriceListModel.setProductSkuCode(cellList.get(0));
					productPriceListModel.setTb( Long.valueOf(cellList.get(3).split("\\.")[0]));
					productPriceListModel.setTshPrice(Double.valueOf(cellList.get(2)));
					productPriceListModel.setReason(cellList.get(5));
					productPriceListDao.insert(productPriceListModel);
			}
		}
        //插入新增任务log
        ProductPriceLogModel productPriceLogModel=new ProductPriceLogModel();
//        productPriceLogDao.insert(productPriceLogModel);
        
    }

    public void updateProductPriceList(ProductPriceListModel productPriceListModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel);
        }
		productPriceListDao.update(productPriceListModel);
    }

    public ProductPriceListModel getProductPriceListById(ProductPriceListModel productPriceListModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel.getProductPriceList());
        }    
        ProductPriceListModel result = productPriceListDao.queryOne(productPriceListModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductPriceListCount(ProductPriceListModel productPriceListModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel);
        }
        long result = productPriceListDao.countListPage(productPriceListModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductPriceListModel> getProductPriceList(ProductPriceListModel productPriceListModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel);
        }
        long count = productPriceListDao.countListPage(productPriceListModel);
        List<ProductPriceListModel> result=null;
        if (count > 0) {
        page.setTotalCount(count);
        result= productPriceListDao.queryListPage(productPriceListModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductPriceListModel productPriceListModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceListModel.getProductPriceList());
        }
        productPriceListDao.delete(productPriceListModel);
    }

	@Override
	public ProductPriceModel getProductPriceById(ProductPriceModel productPriceModel) throws Exception {
		
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productPriceModel.getProductSkuCode());
        }    
		ProductPriceModel result=productPriceListDao.getProductPriceById(productPriceModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
	}
	
	/**
     * 根据SKU编号 查询价格审核记录
     *
     * @param productPriceListModel
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductPriceListModel> getPriceListBySkuCode(String productSkuCode)
            throws Exception {
        ProductPriceListModel productPriceListModel = new ProductPriceListModel();
        productPriceListModel.setProductSkuCode(productSkuCode);
        return productPriceListDao.getPriceListBySkuCode(productPriceListModel);
    }

    @Override
    public void updateStatusList(Map<String,Object> statusMap) throws Exception {
        productPriceListDao.updateStatusList(statusMap);
    }

    @Override
    public void updateStatusPriceList(List<Long> ids) throws Exception {
        productPriceListDao.updateStatusPriceList(ids);
    }

    @Override
    public void updateOldStatusList(List<String> skuCodes) throws Exception {
        productPriceListDao.updateOldStatusList(skuCodes);
    }

}
