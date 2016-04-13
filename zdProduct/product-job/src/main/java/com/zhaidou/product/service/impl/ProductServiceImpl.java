/*
 * 文 件 名:  ProductServiceImpl.java
 * 版   权: Copyright www.teshehui.com Corporation 2015 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-03-25
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.zhaidou.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.product.dao.ProductAttributeDao;
import com.zhaidou.product.dao.ProductDao;
import com.zhaidou.product.dao.ProductImageDao;
import com.zhaidou.product.dao.ProductInfoAuthDao;
import com.zhaidou.product.dao.ProductInfoDao;
import com.zhaidou.product.dao.ProductMallDao;
import com.zhaidou.product.dao.ProductOperateDao;
import com.zhaidou.product.dao.ProductPriceDao;
import com.zhaidou.product.dao.ProductPriceListDao;
import com.zhaidou.product.dao.ProductSkuDao;
import com.zhaidou.product.dao.ProductStockDao;
import com.zhaidou.product.dao.ProductSupplierDao;
import com.zhaidou.product.model.ProductAttributeModel;
import com.zhaidou.product.model.ProductImageModel;
import com.zhaidou.product.model.ProductInfoModel;
import com.zhaidou.product.model.ProductMallModel;
import com.zhaidou.product.model.ProductModel;
import com.zhaidou.product.model.ProductOperateModel;
import com.zhaidou.product.model.ProductPriceListModel;
import com.zhaidou.product.model.ProductPriceModel;
import com.zhaidou.product.model.ProductSkuModel;
import com.zhaidou.product.model.ProductStockModel;
import com.zhaidou.product.service.ProductService;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.request.ProductStockRequestPO;
import com.zhaidou.product.util.ProductUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  wanghongtao
 * @version  [版本号, 2015-03-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("productService")
public class ProductServiceImpl implements ProductService
{
    private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);

    @Resource
    private ProductDao  productDao;
    @Resource
    private ProductPriceDao productPriceDao;
    @Resource
    private ProductSkuDao productSkuDao;
    @Resource
    private ProductStockDao productStockDao;
    @Resource
    private ProductOperateDao productOperateDao;
    @Resource
    private ProductAttributeDao productAttributeDao;
    @Resource
    private ProductImageDao productImageDao;
    @Resource
    private ProductInfoDao productInfoDao;
    @Resource
    private ProductStockManager productStockManager;
    @Resource
    private ProductMallDao productMallDao;
    @Resource
    private ProductPriceListDao productPriceListDao;
    
    @Resource
    private ProductSupplierDao productSupplierDao;
    @Resource
    private ProductInfoAuthDao productInfoAuthDao;
    @Resource
    private ImageSearchManager imageSearchManager;
    
    
    
    
    /**
     * 新增商品
     *
     * @param productModel
     * @return
     */
    public ProductModel addProduct(ProductModel productModel)throws Exception
    {
        try {
            if(productModel!=null){
                productModel.setProductShelves((long)0);
                productModel.setStaus((long)2);
                
                //商品基本属性新增
                productDao.insert(productModel);
                
                long productId = productModel.getProductId();
                String productCode = productModel.getProductCode();
                ProductModel productModelCode = new ProductModel();
                productModelCode.setProductId(productModel.getProductId());
                
                //插入运营属性
                ProductOperateModel productOperateModel = productModel.getProductOperateModel();
                if(productOperateModel!=null){
                    productOperateModel.setProductId(productId);
                    if(productOperateModel.getIsExpensive()==null){
                        productOperateModel.setIsExpensive(1);
                    }
                    productOperateDao.insert(productOperateModel);
                }
                
                //插入 productInfo 大字段描述
                ProductInfoModel productInfoModel = productModel.getProductInfoModel();
                if(productInfoModel!=null){
                    productInfoModel.setProductId(productId);
                    productInfoDao.insert(productInfoModel);
                }
                
                //插入 商城 扩展字段
                ProductMallModel productMallModel = productModel.getProductMallModel();
                if(productMallModel!=null){
                    productMallModel.setProductId(productId);
                    productMallDao.insert(productMallModel);
                }else{
                    productMallModel = new ProductMallModel();
                    productMallModel.setProductId(productId);
                    productMallDao.insert(productMallModel);
                }
                
                //插入弹性域
                List<ProductAttributeModel> listAttribute = productModel.getProductAttributeList();
                if(listAttribute!= null && listAttribute.size()>0){
                    for(ProductAttributeModel productAttribute:listAttribute){
                        productAttribute.setProductId(productId);
                        productAttributeDao.insert(productAttribute);
                    }
                }
                
                //sku
                List<ProductSkuModel> listSku = (List<ProductSkuModel>)productModel.getProductSkuList();
                if(listSku!=null && listSku.size()>0){
                    for(ProductSkuModel sku:listSku){
                        //插入 SKU 返回 skuID 生成 skuCode
                        sku.setProductId(productId);
                        sku.setIsAvailable(1);
                        String skuCode= sku.getProductSkuCode();
                        productSkuDao.insert(sku);
                        
                        long productSkuId = sku.getProductSkuId();
                        
                        //插入 price
                        ProductPriceModel productPriceModel = sku.getProductPriceModel();
                        if(productPriceModel!=null){
                            
                            //新增商品SKU 插入价格审核
                            ProductPriceListModel priceListModel = new ProductPriceListModel();
                            priceListModel.setProductSkuCode(skuCode);
                            priceListModel.setCostPrice(productPriceModel.getCostPrice());
                            priceListModel.setMarketPrice(productPriceModel.getMarketPrice());
                            priceListModel.setTshPrice(productPriceModel.getTshPrice());
                            priceListModel.setTb(productPriceModel.getTb());
                            priceListModel.setStatus(0);

                            priceListModel.setCreateBy(productModel.getUpdateBy());
                            priceListModel.setCreateTime(productModel.getUpdateTime());
                            priceListModel.setCreateUserName(productModel.getUpdateUserName());
                            productPriceListDao.insert(priceListModel);
                            
                            productPriceModel.setProductSkuId(productSkuId);
                            productPriceModel.setProductSkuCode(skuCode);
                            productPriceModel.setProductId(productId);
                            productPriceModel.setCostPrice(0d);
                            productPriceModel.setMarketPrice(0d);
                            productPriceModel.setTshPrice(0d);
                            productPriceModel.setTb(0l);
                            productPriceDao.insert(productPriceModel);
                            
                        }
                        
                        //插入 stock
                        ProductStockModel productStockModel = sku.getProductStockModel();
                        if(productStockModel!=null){
                            productStockModel.setProductId(productId);
                            productStockModel.setSkuId(productSkuId);
                            productStockModel.setSkuCode(skuCode);
                            productStockDao.insert(productStockModel);
                            
                            //调用库存系统，通知新增商品 库存信息
                            RequestObject requestObject = new RequestObject();
                            ProductStockRequestPO stockPo = new ProductStockRequestPO();
                            stockPo.setProductCode(productCode);
                            stockPo.setSkuCode(skuCode);
                            stockPo.setFlowCode("asdf234124");
                            stockPo.setChangeCount(productStockModel.getVirtualStock());
                            stockPo.setChangeSource(3);
                            stockPo.setChangeReason("job 新增");
                            stockPo.setChangeStockType(2);
                            stockPo.setOpration(2);
                            stockPo.setCategoryCode(productModel.getCatCode());
                            stockPo.setBrandCode(productModel.getBrandCode());
                            stockPo.setChangeUser(productModel.getCreateUserName());
                            
                            requestObject.setRequestParams(stockPo);
                            logger.info("job 新增SKU,新增库存,请求参数："+JSONUtil.toString(stockPo));
                            ResponseObject responseObject = productStockManager.changeStock(requestObject);
                            logger.info("job 新增SKU,新增库存,返回值："+JSONUtil.toString(responseObject));
                            if(responseObject.getCode()!=0){
                                logger.error("调用库存借口错误！"+responseObject.getMsg());
                                throw new ZhaidouRuntimeException("调用库存借口错误！"+responseObject.getMsg());
                            }
                        }
                        
                        if(sku.getMainSku().longValue()==1){
                            List<ProductImageModel> ProductImageList = ProductUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                            if(ProductImageList!=null && ProductImageList.size()>0){
                                for(ProductImageModel productImageModel:ProductImageList){
                                    if(productImageModel.getLevel().longValue()==1){
                                        productModel.setMainPic(productImageModel.getImage());
                                        productModelCode.setMainPic(productImageModel.getImage());
                                    }
                                }
                            }
                        }
                        
                    }
                    
                    //判断 并 插入 商品完整度  
                    productIntegrity(productModel,productModelCode);
                    
                    //修改 商品编号，价格
                    productDao.update(productModelCode);
                    
                    // 同步到 supplier-web 供应商平台
                    ProductModel supplierPrdouctModel = new ProductModel(productModel.getProductId());
                    supplierPrdouctModel.setProductCode(productCode);
                    supplierPrdouctModel.setStaus(2l);
                    supplierPrdouctModel.setReason("");
                    productSupplierDao.updateSupplierProductByProductCode(supplierPrdouctModel);
                    return productModel;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加商品系统异常"+e.toString());
            throw new ZhaidouRuntimeException("添加商品系统异常"+e.toString());
        }
        return null;
    }
    
    /**
     * 修改商品
     *
     * @param productModel
     * @return
     */
    public ProductModel updateProduct(ProductModel productModel,List<ProductSkuModel> listInsert,Long type)throws Exception
    {
        try {
            String supplierProductCode  = productModel.getProductCode();
            List<Long> listStr = new ArrayList<Long>();
            List<ProductSkuModel> listUpdate = (List<ProductSkuModel>)productModel.getProductSkuList();
            if(listUpdate!=null && listUpdate.size()>0){
                for(ProductSkuModel sku:listUpdate){
                    //修改SKU
                    productSkuDao.update(sku);
                    listStr.add(sku.getProductSkuId());
//                    if(sku.getProductStockModel()!=null){
//                        //修改库存
//                        if(sku.getProductStockModel().getVirtualStock()!=null){
//                            //调用库存系统，通知新增商品 库存信息
//                            RequestObject requestObject = new RequestObject();
//                            ProductStockRequestPO stockPo = new ProductStockRequestPO();
//                            stockPo.setProductCode(supplierProductCode);
//                            stockPo.setSkuCode(sku.getProductSkuCode());
//                            stockPo.setFlowCode("adminjob");
//                            stockPo.setChangeCount(sku.getProductStockModel().getVirtualStock());
//                            stockPo.setChangeSource(3);
//                            stockPo.setChangeReason("job 编辑");
//                            stockPo.setChangeStockType(2);
//                            stockPo.setOpration(2);
//                            stockPo.setCategoryCode(productModel.getCatCode());
//                            stockPo.setBrandCode(productModel.getBrandCode());
//                            stockPo.setChangeUser(productModel.getUpdateUserName());
//                            
//                            requestObject.setRequestParams(stockPo);
//                            logger.info("job 编辑修改SKU,修改库存,请求参数："+JSONUtil.toString(stockPo));
//                            ResponseObject responseObject = productStockManager.changeStock(requestObject);
//                            logger.info("job 编辑修改SKU,修改库存,返回值："+JSONUtil.toString(responseObject));
//                            if(responseObject.getCode()!=0){
//                                logger.error("调用库存借口错误！"+responseObject.getMsg());
//                                throw new TeshehuiRuntimeException("调用库存借口错误！"+responseObject.getMsg());
//                            }
//                        }
//                    }
                    //主图
                    if(sku.getMainSku().longValue()==1){
                        List<ProductImageModel> ProductImageList = ProductUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                        if(ProductImageList!=null && ProductImageList.size()>0){
                            for(ProductImageModel productImageModel:ProductImageList){
                                if(productImageModel.getLevel().longValue()==1){
                                    productModel.setMainPic(productImageModel.getImage());
                                }
                            }
                        }
                    }
                }
            }
            
            //批量逻辑删除SKU操作
            Map<String,Object> mapDelete = new HashMap<String,Object>();
            if(type.longValue()==2 && listStr.size()==0){
                listStr.add(0l);
            }
            mapDelete.put("list", listStr);
            mapDelete.put("productId", productModel.getProductId());
            mapDelete.put("isAvailable", 2);
            if(listStr!=null && listStr.size()>0){
                productSkuDao.updateByIds(mapDelete);
            }
            
            List<ProductSkuModel> delSku = productSkuDao.getDelSku(productModel.getProductId());
            if(delSku!=null && delSku.size()>0){
                for(ProductSkuModel sku :delSku){
                    RequestObject requestObject = new RequestObject();
                    ProductStockRequestPO stockPo = new ProductStockRequestPO();
                    stockPo.setProductCode(supplierProductCode);
                    stockPo.setSkuCode(sku.getProductSkuCode());
                    stockPo.setFlowCode("asdf234124");
                    stockPo.setChangeCount(0d);
                    stockPo.setChangeSource(3);
                    stockPo.setChangeReason("job 编辑删除");
                    stockPo.setChangeStockType(2);
                    stockPo.setOpration(2);
                    stockPo.setCategoryCode(productModel.getCatCode());
                    stockPo.setBrandCode(productModel.getBrandCode());
                    stockPo.setChangeUser(productModel.getUpdateUserName());
                    
                    requestObject.setRequestParams(stockPo);
                    logger.info("job 编辑删除SKU,清除SKU库存,请求参数："+JSONUtil.toString(stockPo));
                    ResponseObject responseObject = productStockManager.changeStock(requestObject);
                    logger.info("job 编辑删除SKU,清除SKU库存,返回值："+JSONUtil.toString(responseObject));
                    if(responseObject.getCode()!=0){
                        logger.error("调用库存借口错误！"+responseObject.getMsg());
                        throw new ZhaidouRuntimeException("调用库存借口错误！"+responseObject.getMsg());
                    } 
                }
            }
            
            if(listInsert!=null && listInsert.size()>0){
               
                for(ProductSkuModel sku:listInsert){
                    
                    sku.setProductId(productModel.getProductId());
                    String skuCode = sku.getProductSkuCode();
                    //插入Sku
                    sku.setIsAvailable(1);
                    productSkuDao.insert(sku);
                    listStr.add(sku.getProductSkuId());
                    
                    if(sku.getProductStockModel()!=null){
                        sku.getProductStockModel().setSkuId(sku.getProductSkuId());
                        sku.getProductStockModel().setSkuCode(skuCode);
                        sku.getProductStockModel().setProductId(productModel.getProductId());
                        //插入库存
                        if(sku.getProductStockModel().getVirtualStock()!=null){
                            //调用库存系统，通知新增商品 库存信息
                            RequestObject requestObject = new RequestObject();
                            ProductStockRequestPO stockPo = new ProductStockRequestPO();
                            stockPo.setProductCode(supplierProductCode);
                            stockPo.setSkuCode(skuCode);
                            stockPo.setFlowCode("asdf234124");
                            stockPo.setChangeCount(sku.getProductStockModel().getVirtualStock());
                            stockPo.setChangeSource(3);
                            stockPo.setChangeReason("job 编辑新增");
                            stockPo.setChangeStockType(2);
                            stockPo.setOpration(2);
                            stockPo.setCategoryCode(productModel.getCatCode());
                            stockPo.setBrandCode(productModel.getBrandCode());
                            stockPo.setChangeUser(productModel.getUpdateUserName());
                            
                            requestObject.setRequestParams(stockPo);
                            logger.info("job 编辑新增SKU,增加库存,请求参数："+JSONUtil.toString(stockPo));
                            ResponseObject responseObject = productStockManager.changeStock(requestObject);
                            logger.info("job 编辑新增SKU,增加库存,返回值："+JSONUtil.toString(responseObject));
                            if(responseObject.getCode()!=0){
                                logger.error("调用库存借口错误！"+responseObject.getMsg());
                                throw new ZhaidouRuntimeException("调用库存借口错误！"+responseObject.getMsg());
                            } 
                        }
                        productStockDao.insert(sku.getProductStockModel());
                    }
                    if(sku.getProductPriceModel()!=null){
                        ProductPriceModel productPriceModel = sku.getProductPriceModel();
                        productPriceModel.setProductSkuId(sku.getProductSkuId());
                        productPriceModel.setProductSkuCode(skuCode);
                        productPriceModel.setProductId(productModel.getProductId());
                        
                        //新增商品SKU 插入价格审核
                        ProductPriceListModel priceListModel = new ProductPriceListModel();
                        priceListModel.setProductSkuCode(skuCode);
                        priceListModel.setCostPrice(productPriceModel.getCostPrice());
                        priceListModel.setMarketPrice(productPriceModel.getMarketPrice());
                        priceListModel.setTshPrice(productPriceModel.getTshPrice());
                        priceListModel.setTb(productPriceModel.getTb());
                        priceListModel.setStatus(0);
                        priceListModel.setCreateBy(productModel.getUpdateBy());
                        priceListModel.setCreateTime(productModel.getUpdateTime());
                        priceListModel.setCreateUserName(productModel.getUpdateUserName());
                        productPriceListDao.insert(priceListModel);
                        
                        //插入价格
                        productPriceModel.setCostPrice(0d);
                        productPriceModel.setTshPrice(0d);
                        productPriceModel.setMarketPrice(0d);
                        productPriceModel.setTb(0l);
                        
                        productPriceDao.insert(sku.getProductPriceModel());
                        
                    }
                    //主图
                    if(sku.getMainSku().longValue()==1){
                        List<ProductImageModel> ProductImageList = ProductUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                        if(ProductImageList!=null && ProductImageList.size()>0){
                            for(ProductImageModel productImageModel:ProductImageList){
                                if(productImageModel.getLevel().longValue()==1){
                                    productModel.setMainPic(productImageModel.getImage());
                                }
                            }
                        }
                    }
                    
                    productModel.getProductSkuList().add(sku);
                }
            }
            //弹性域
            ProductModel productMod = productDao.queryOne(productModel);
            //如果商品基础类型有修改
            if(productMod!=null && !productMod.getCatCode().equals(productModel.getCatCode())){
                //则删除之前的弹性域
                ProductAttributeModel productAttributeModel = new ProductAttributeModel();
                productAttributeModel.setProductId(productMod.getProductId());
                productAttributeDao.delete(productAttributeModel);
                
                //在插入现有弹性域
                if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                    for(ProductAttributeModel productAttributeMod:productModel.getProductAttributeList()){
                        productAttributeMod.setProductId(productModel.getProductId());
                        productAttributeDao.insert(productAttributeMod);
                    }
                }
            }else{
                List<ProductAttributeModel> listAttr = productAttributeDao.getAttributeByProductId(productMod.getProductId());
                //否则  就修改弹性域属性
                if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                    if(listAttr!=null && listAttr.size()>0){
                        for(ProductAttributeModel productAttributeModel:productModel.getProductAttributeList()){
                            boolean flag = false;
                            for(ProductAttributeModel productAttributeMod:listAttr){
                                if(productAttributeModel.getProductAttributeCode().equals(productAttributeMod.getProductAttributeCode())){
                                    flag = true;
                                }
                            }
                            if(flag){
                                if(productAttributeModel.getProductAttributeId()!=null && productAttributeModel.getProductAttributeId()>0){
                                    productAttributeDao.update(productAttributeModel);
                                }else{
                                    productAttributeDao.deleteByAttrCode(productAttributeModel.getProductAttributeCode());
                                    productAttributeModel.setProductId(productModel.getProductId());
                                    productAttributeDao.insert(productAttributeModel);
                                }
                                
                            }else{
                                productAttributeModel.setProductId(productModel.getProductId());
                                productAttributeDao.insert(productAttributeModel);
                            }
                        }
                    }else{
                        for(ProductAttributeModel productAttributeModel:productModel.getProductAttributeList()){
                            productAttributeModel.setProductId(productModel.getProductId());
                            productAttributeDao.insert(productAttributeModel);
                        }
                    }
                }
            }
            
            //修改运营属性
            if(productModel.getProductOperateModel()!=null){
                productOperateDao.update(productModel.getProductOperateModel());
            }
            //修改商城扩展属性
            if(productModel.getProductMallModel()!=null){
                productMallDao.update(productModel.getProductMallModel());
            }
            
            //修改 productInfo
            if(productModel.getProductInfoModel()!=null){
                productInfoDao.update(productModel.getProductInfoModel());
            }
            
            productModel.setStaus((long)2);
            //判断 并 插入 商品完整度  
//            productIntegrity(productModel,null);
            
            //修改基本属性
            productDao.updateByCode(productModel);
            
            //同步到 supplier-web 供应商平台
            ProductModel supplierPrdouctModel = new ProductModel();
            supplierPrdouctModel.setProductCode(supplierProductCode);
            supplierPrdouctModel.setStaus((long)2);
            supplierPrdouctModel.setReason("");
            productSupplierDao.updateSupplierProductByProductCode(supplierPrdouctModel);
            
            return productModel;
        } catch (Exception e) {
            logger.error(productModel.getProductCode(),e);
            throw new ZhaidouRuntimeException("修改商品系统异常");
        }
    }

    /**
     * 根据ID 获取商品
     *
     * @param productModel
     * @return
     */
    public ProductModel getProductById(ProductModel productModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productModel.getProductId());
        }     
        ProductModel result = productDao.queryOne(productModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }
    
    /**
     * 根据条件获取总记录数
     *
     * @param productModel
     * @return
     */
    public long getProductCount(ProductModel productModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productModel);
        }
        long result = productDao.countListPage(productModel);
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        return result;
    }
    
    /**
     * 根据条件 分页获取商品
     *
     * @param productModel
     * @return
     */
    public List<ProductModel> getProduct(ProductModel productModel, Page page)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productModel);
        }
        long count = productDao.countListPage(productModel);
        List<ProductModel> result=null;
        if (count > 0) {
            page.setTotalCount(count);
        result= productDao.queryListPage(productModel, page.getPageNum(), page.getNumPerPage());
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("--result-->" + result);
        }
        
        return result;
    }

    public void deleteById(ProductModel productModel)throws Exception
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productModel.getProductId());
        }
        productDao.delete(productModel);
    }

    /**
     * @Description: 根据产品编号列表返回  Map<productCode,productId>
     */
	@Override
	public Map<String, Long> getMapByProductCodes(List<String> productCodeList) throws Exception{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + productCodeList.toString());
        }
		
		List<ProductModel> productList =  this.productDao.getListByProductCodes(productCodeList);
		if (CollectionUtils.isNotEmpty(productList)) {
			Map<String, Long> map = new HashMap<String, Long>();
			for (ProductModel item : productList) {
				map.put(item.getProductCode(), item.getProductId());
			}
			return map;
		}
		return null;
	}
	
	/**
     * 计算信息完整度
     *
     * @param productModel
     * @return
     */
    private void productIntegrity(ProductModel productModel,ProductModel changeProduct){
        StringBuffer sb = new StringBuffer();
        int integrity = 0;
        if(productModel.getProductName()!=null && !"".equals(productModel.getProductName())){
            integrity = integrity+10;
        }else{
            sb.append("商品名称,");
        }
        if(productModel.getCatName()!=null && !"".equals(productModel.getCatName())){
            integrity = integrity+15;
        }else{
            sb.append("基础分类,");
        }
        if(productModel.getProductInfoModel()!=null){
            if(productModel.getProductInfoModel().getAppProductInfo()!=null && !"".equals(productModel.getProductInfoModel().getAppProductInfo())){
                integrity = integrity+15;
            }else{
                sb.append("商品描述,");
            }
        }else{
            sb.append("商品描述,");
        }
        if(productModel.getBrandName()!=null && !"".equals(productModel.getBrandName())){
            integrity = integrity+15;
        }else{
            sb.append("品牌,");
        }
        if(productModel.getCostPrice()!=null && productModel.getCostPrice()>0){
            integrity = integrity+15;
        }else{
            sb.append("价格,");
        }
        if(productModel.getMainPic()!=null && !"".equals(productModel.getMainPic())){
            integrity = integrity+15;
        }else{
            ProductModel productMod = null;
            try {
                productMod = productDao.queryOne(productModel);
            } catch (Exception e) {
            }
            if(productMod!=null){
                if(productMod.getMainPic()!=null && !"".equals(productMod.getMainPic())){
                    integrity = integrity+15;
                }else{
                    sb.append("主图,");
                }
            }
        }
        boolean flag = true;
        if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
            for(ProductAttributeModel productAttributeModel:productModel.getProductAttributeList()){
                if(productAttributeModel.getIsRequired()==1){
                    if(productAttributeModel.getProductAttributeValue()!=null && !"".equals(productAttributeModel.getProductAttributeValue())){
                        
                    }else{
                        flag = false;
                        break;
                    }
                }
            }
        }else{
            flag = true;
        }
        if(flag){
            integrity = integrity+15;
        }else{
            sb.append("弹性域,");
        }
        String integrityDesc = "";
        if(sb.length()>0){
           integrityDesc = sb.toString().substring(0, sb.toString().lastIndexOf(","));
        }
        productModel.setIntegrity(integrity);
        productModel.setIntegrityDesc(integrityDesc);
        if(changeProduct!=null){
            changeProduct.setIntegrityDesc(integrityDesc);
            changeProduct.setIntegrity(integrity);
        }
    }
    
    /**
     * 根据 品牌编码 查询商品
     *
     * @param productModel
     * @return
     * @throws Exception
     */
    @Override
    public Integer getProductByBrandCode(ProductModel productModel) throws Exception {
        
        return productDao.getProductByBrandCode(productModel);
    }

	@Override
	public void updateProduct(ProductModel productModel) throws Exception {
		
		
		productDao.update(productModel);
	}
}
