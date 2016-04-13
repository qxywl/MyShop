/*
 * 文 件 名:  ProductSkuServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.product.info.dao.ProductImageDao;
import com.zhaidou.product.info.dao.ProductPriceDao;
import com.zhaidou.product.info.dao.ProductSkuDao;
import com.zhaidou.product.info.dao.ProductStockDao;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.model.ProductPriceModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.model.ProductStockModel;
import com.zhaidou.product.info.service.ProductSkuService;
import com.zhaidou.product.info.util.FileUtil;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.request.GetStockRequestPO;
import com.zhaidou.product.stock.po.response.ProductStockRemainResponse;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productSkuService")
public class ProductSkuServiceImpl implements ProductSkuService
{
    private static final Log logger = LogFactory.getLog(ProductSkuServiceImpl.class);

    @Resource
    private ProductSkuDao  productSkuDao;
    
    @Resource
    private ProductPriceDao productPriceDao;
    @Resource
    private ProductStockDao productStockDao;
    @Resource
    private ProductImageDao productImageDao;
    @Resource
    private ImageSearchManager imageSearchManager;
    @Resource
    private ProductStockManager productStockManager;

    public void addProductSku(ProductSkuModel productSkuModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel);
        }
        productSkuDao.insert(productSkuModel);
    }

    public void updateProductSku(ProductSkuModel productSkuModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel);
        }
        productSkuDao.update(productSkuModel);
    }

    public ProductSkuModel getProductSkuById(ProductSkuModel productSkuModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel.getProductSkuId());
        }    
        ProductSkuModel result = productSkuDao.queryOne(productSkuModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public long getProductSkuCount(ProductSkuModel productSkuModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel);
        }
        long result = productSkuDao.countListPage(productSkuModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public List<ProductSkuModel> getProductSku(ProductSkuModel productSkuModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel);
        }
        long count = productSkuDao.countListPage(productSkuModel);
        List<ProductSkuModel> result=null;
        if (count > 0) {
            page.setTotalCount(count);
        result= productSkuDao.queryListPage(productSkuModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }

    public void deleteById(ProductSkuModel productSkuModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel.getProductSkuId());
        }
        productSkuDao.delete(productSkuModel);
    }
    
    /**
     * 根据商品ID 获取该商品下所有SKU
     *
     * @param productSkuModel
     * @return
     */
    @Override
    public Map<String, Object> getSkuByProductId(ProductSkuModel productSkuModel) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productSkuModel.getProductId());
        }
        long count = productSkuDao.countListPage(productSkuModel);
        List<ProductSkuModel> productSkuList=null;
        List<ProductSkuModel> productSkuList1 = new ArrayList<ProductSkuModel>();
        ProductPriceModel productPriceModel = null;
        if (count > 0) {
            productSkuList = productSkuDao.getSkuByProductId(productSkuModel);
            
            if(productSkuList!=null && productSkuList.size()>0){
                map.put("attrColorName", productSkuList.get(0).getAttrColorName());
                map.put("attrSpecName",  productSkuList.get(0).getAttrSpecName());
                for(ProductSkuModel productSku:productSkuList){
                    if(productSku.getIsAvailable()!=null && productSku.getIsAvailable()==1){
                      //价格
                        productPriceModel = new ProductPriceModel();
                        productPriceModel.setProductSkuId(productSku.getProductSkuId());
                        productPriceModel = productPriceDao.queryOne(productPriceModel);
                        productSku.setProductPriceModel(productPriceModel);
                        
                        //库存
                        GetStockRequestPO requestPo = new GetStockRequestPO();
                        requestPo.setSkuCode(productSku.getProductSkuCode());
                        RequestObject requestObject = new RequestObject();
                        requestObject.setRequestParams(requestPo);
                        logger.info("获取库存,请求参数："+JSONUtil.toString(requestObject));
                        ResponseObject responseObject = productStockManager.getStock(requestObject);
                        logger.info("获取库存,返回值："+JSONUtil.toString(responseObject));
                        
                        if(responseObject.getCode()==0){
                            ProductStockRemainResponse stockResponse = (ProductStockRemainResponse)responseObject.getData();
                            if(stockResponse!=null){
                                ProductStockModel stockModel = new ProductStockModel();
                                if(stockResponse.getStockType()==1){
                                	  stockModel.setVirtualStock(stockResponse.getManualStock());
                                }else if(stockResponse.getStockType()==2){
                                	  stockModel.setVirtualStock(stockResponse.getVirtualStock());
                                }else{
                                	  stockModel.setVirtualStock(stockResponse.getEntityStock());
                                }
                                productSku.setProductStockModel(stockModel);
                            }else{
                                ProductStockModel stockModel = new ProductStockModel();
                                stockModel.setVirtualStock(0d);
                                productSku.setProductStockModel(stockModel);
                            }
                        }else{
                            throw new ZhaidouRuntimeException(productSku.getProductSkuCode()+" ,获取库存错误！"+responseObject.getMsg());
                        }
                        
                        //图片
                        List<ProductImageModel> ProductImageList = FileUtil.imageList(productSku.getProductSkuCode(),imageSearchManager);
                        productSku.setProductImagerList(ProductImageList);
                        
                        productSkuList1.add(productSku);
                    }
                }
                
            }
        }
        map.put("productSkuList", productSkuList1);
        return map;
    }
    

    @Override
    public List<ProductSkuModel> getProductSkuListByProductId(List<Long> productIdList) throws Exception {
        
        return productSkuDao.getProductSkuListByProductId(productIdList);
    }
    
    
    /**
     * sku导出excel 全部导出
     */
    @Override
    public List<ProductSkuExportModel> getProductSkuExportModel(ProductSkuExportModel productSkuExportModel,Page page) throws Exception {
//        Integer size = productSkuWebDao.getProductSkuExportModel(productSkuExportModel, pageNo, pageSize)
//        if(size>0){
//            page.setTotalCount(size);
//        }
        return productSkuDao.getProductSkuExportModel(productSkuExportModel, page.getPageNum(), page.getNumPerPage());
    }

    @Override
    public Long queryProductSkuExportCount(ProductSkuExportModel productSkuExportModel) throws Exception {
        // TODO Auto-generated method stub
        return productSkuDao.queryProductSkuExportCount(productSkuExportModel);
    }
     
}
