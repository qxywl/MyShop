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
package com.zhaidou.product.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.dao.ProductDao;
import com.zhaidou.product.info.dao.ProductShelvesDao;
import com.zhaidou.product.info.dao.ProductShelvesLogDao;
import com.zhaidou.product.info.dao.ProductShelvesTmpDao;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductShelvesLogModel;
import com.zhaidou.product.info.model.ProductShelvesModel;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;
import com.zhaidou.product.info.service.ProductShelvesLogService;
import com.zhaidou.product.info.service.ProductShelvesService;


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
    @Resource
    private ProductDao productDao;
    @Resource
    private ProductShelvesTmpDao productShelvesTmpDao;
    

    public void addProductShelves(List<ProductShelvesModel> listShelves,Map<String,String> map)throws Exception
    {
        if(listShelves!=null && listShelves.size()>0){
            for(ProductShelvesModel productShelvesModel:listShelves){
                try {
                    productShelvesModel.setCreateBy(Long.parseLong(map.get("userId")));
                    productShelvesModel.setCreateTime(new Date().getTime()/1000);
                    productShelvesModel.setCreateUserName(map.get("userName"));
                    
                    ProductShelvesTmpModel shelvesTmpModel = new ProductShelvesTmpModel();
                    shelvesTmpModel.setProductCode(productShelvesModel.getProductCode());
                    shelvesTmpModel = productShelvesTmpDao.queryOne(shelvesTmpModel);
                    if(shelvesTmpModel!=null && shelvesTmpModel.getStatus()==2){
                        shelvesTmpModel.setStatus(3);
                        productShelvesTmpDao.update(shelvesTmpModel);
                    }
                    
                    productShelvesDao.insert(productShelvesModel);
                } catch (Exception e) {
                    logger.error("上下架系统错误!",e);
                    throw new ZhaidouRuntimeException("上下架系统错误!");
                }
            }
        }
    }

    public void updateProductShelves(ProductShelvesModel productShelvesModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
        }
        
        productShelvesDao.update(productShelvesModel);
        
        //审核通过  判断是否为第一次上架 或 下架
        if(productShelvesModel.getStatus()==2){
            productShelvesModel = productShelvesDao.queryOne(productShelvesModel);
            ProductModel productModel = new ProductModel(productShelvesModel.getProductId());
            productModel = productDao.queryOne(productModel);
            
            //上架
            if(productShelvesModel.getShelvesStatus()==1){
                if(productModel!=null && productModel.getFirstShelves()==null  &&
                        productModel.getFirstShelvesName()==null && productModel.getFirstShelvesTime()==null){
                    productModel.setFirstShelvesTime(new Date().getTime()/1000);
                    productModel.setProductShelves((long)productShelvesModel.getShelvesStatus());
                    //临时值
                    productModel.setFirstShelves((long)123);
                    productModel.setFirstShelvesName("wanghang");
                }
            }else{
                //下架
                if(productModel!=null && productModel.getLastShelves()==null &&
                        productModel.getLastShelvesName()==null && productModel.getLastShelvesTime()==null){
                    productModel.setLastShelvesTime(new Date().getTime()/1000);
                    productModel.setProductShelves((long)productShelvesModel.getShelvesStatus());
                    //临时值
                    productModel.setLastShelves((long)123);
                    productModel.setLastShelvesName("wanghang");
                }
            }
            
            try {
                productDao.update(productModel);
            } catch (Exception e) {
                new ZhaidouRuntimeException("没有关联的商品");
            }
            
        }
        
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

}
