/*
 * 文 件 名:  ProductInfoJobServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-30
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.dao.ProductInfoJobDao;
import com.zhaidou.product.model.ProductInfoJobModel;
import com.zhaidou.product.service.ProductInfoJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productInfoJobService")
public class ProductInfoJobServiceImpl implements ProductInfoJobService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoJobServiceImpl.class);

    @Resource
    private ProductInfoJobDao  productInfoJobDao;

    public void addProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception
    {

        try {
            productInfoJobDao.insert(productInfoJobModel);
            LOGGER.info("insert productInfoJobDao fail productInfoJobModel =" + productInfoJobModel);
        } catch (Exception e) {
            LOGGER.error("insert productInfoJobDao fail productInfoJobModel =" + productInfoJobModel, e);
            throw new ZhaidouRuntimeException("insert productInfoJobDao fail");
        }
    }

    public void updateProductInfoJob(ProductInfoJobModel productInfoJobModel)throws Exception
    {
        try {
            productInfoJobDao.update(productInfoJobModel);
            LOGGER.info("update productInfoJobDao fail productInfoJobModel =" + productInfoJobModel);
        } catch (Exception e) {
            LOGGER.error("update productInfoJobDao fail productInfoJobModel =" + productInfoJobModel, e);
            throw new ZhaidouRuntimeException("update productInfoJobDao fail");
        }

    }

    public ProductInfoJobModel getProductInfoJobById(ProductInfoJobModel productInfoJobModel)throws Exception
    {

        ProductInfoJobModel result = productInfoJobDao.queryOne(productInfoJobModel);

        return result;
    }

    public long getProductInfoJobCount(ProductInfoJobModel productInfoJobModel)throws Exception
    {

        long result = productInfoJobDao.countListPage(productInfoJobModel);

        return result;
    }

    public List<ProductInfoJobModel> getProductInfoJob(ProductInfoJobModel productInfoJobModel, Page page)throws Exception
    {

        List<ProductInfoJobModel> result=null;
        long count = productInfoJobDao.countListPage(productInfoJobModel);
        if (count > 0) {
        page.setTotalCount(count);
        result= productInfoJobDao.queryListPage(productInfoJobModel, page.getPageNum(), page.getNumPerPage());
        }

        return result;
    }

    public void deleteById(ProductInfoJobModel productInfoJobModel)throws Exception
    {

        try {
            productInfoJobDao.delete(productInfoJobModel);
            LOGGER.info("delete productInfoJobDao fail productInfoJobModel =" + productInfoJobModel);
        } catch (Exception e) {
            LOGGER.error("delete productInfoJobDao fail productInfoJobModel =" + productInfoJobModel, e);
            throw new ZhaidouRuntimeException("delete productInfoJobDao fail");
        }
    }

    @Override
    public void updateStatusList(Map<String, Object> map) throws Exception {
        productInfoJobDao.updateStatusList(map);
    }

    @Override
    public void deleteByIds(List<Long> ids) throws Exception {
        productInfoJobDao.deleteByIds(ids);
    }

    @Override
    public void addInfoJobList(List<ProductInfoJobModel> infoJobList) throws Exception {
        productInfoJobDao.addInfoJobList(infoJobList);
    }

}
