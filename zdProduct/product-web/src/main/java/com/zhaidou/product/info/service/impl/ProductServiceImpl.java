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
package com.zhaidou.product.info.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.info.dao.ProductAttributeDao;
import com.zhaidou.product.info.dao.ProductDao;
import com.zhaidou.product.info.dao.ProductImageDao;
import com.zhaidou.product.info.dao.ProductInfoAuthDao;
import com.zhaidou.product.info.dao.ProductInfoDao;
import com.zhaidou.product.info.dao.ProductInfoExamineDao;
import com.zhaidou.product.info.dao.ProductMallDao;
import com.zhaidou.product.info.dao.ProductOperateDao;
import com.zhaidou.product.info.dao.ProductPriceDao;
import com.zhaidou.product.info.dao.ProductPriceListDao;
import com.zhaidou.product.info.dao.ProductSkuDao;
import com.zhaidou.product.info.dao.ProductStockDao;
import com.zhaidou.product.info.model.InfoAuthJsonStringModel;
import com.zhaidou.product.info.model.ProductAttributeModel;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.model.ProductInfoAuthModel;
import com.zhaidou.product.info.model.ProductInfoExamineModel;
import com.zhaidou.product.info.model.ProductInfoModel;
import com.zhaidou.product.info.model.ProductMallModel;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductOperateModel;
import com.zhaidou.product.info.model.ProductPriceListModel;
import com.zhaidou.product.info.model.ProductPriceModel;
import com.zhaidou.product.info.model.ProductSkuExportModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.model.ProductStockModel;
import com.zhaidou.product.info.service.ProductService;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.request.ProductStockRequestPO;

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
    private ProductMallDao productMallDao;
    @Resource
    private ProductPriceListDao productPriceListDao;
    @Resource
    private ProductInfoExamineDao productInfoExamineDao;
    @Resource
    private ProductStockManager productStockManager;
    @Value("#{propertyConfigurerForProject2['img_tmp_dir_rel']}")
    private String imgTmpDirRel;
    /**
     * 新增商品
     *
     * @param productModel
     * @return
     */
    public String addProduct(List<ProductModel> listProduct,String[] fileNames)throws Exception
    {
        try {
            String productCode = "";
            if(listProduct!=null && listProduct.size()>0){
                for(ProductModel productModel:listProduct){
                    //插入商品基本信息  
                    
                    productModel.setProductShelves((long)0);
                    productModel.setStaus((long)2);
                    productModel.setType(productModel.getType());
                    productDao.insert(productModel);
                    long productId = productModel.getProductId();
                    productCode = productModel.getProductCode();


                    ProductModel productModelCode = new ProductModel();
                    productModelCode.setProductId(productModel.getProductId());
                    productModelCode.setProductCode(productCode);
                    productModel.setProductCode(productCode);
                    //插入运营属性
                    ProductOperateModel productOperateModel = productModel.getProductOperateModel();
                    if(productOperateModel!=null){
                        productOperateModel.setProductOperateId(null);
                        productOperateModel.setProductId(productId);
                        productOperateDao.insert(productOperateModel);
                    }

                    //插入 productInfo 大字段描述
                    ProductInfoModel productInfoModel = productModel.getProductInfoModel();
                    if(productInfoModel!=null){
                        productInfoModel.setProductInfoId(null);
                        productInfoModel.setProductId(productId);
                        productInfoDao.insert(productInfoModel);
                    }
                    //插入 商城 扩展字段
                    ProductMallModel productMallModel = productModel.getProductMallModel();
                    if(productMallModel!=null){
                        productMallModel.setProductMallId(null);
                        productMallModel.setProductId(productId);
                        productMallDao.insert(productMallModel);
                    }else{
                        productMallModel = new ProductMallModel();
                        productMallModel.setProductId(productId);
                        productMallDao.insert(productMallModel);
                    }


                    List<ProductAttributeModel> listAttribute = productModel.getProductAttributeList();
                    if(listAttribute!= null && listAttribute.size()>0){
                        //插入弹性域
                        for(ProductAttributeModel productAttribute:listAttribute){
                            productAttribute.setProductAttributeId(null);
                            productAttribute.setProductId(productId);
                            productAttributeDao.insert(productAttribute);
                        }
                    }

                    List<ProductSkuModel> listSku = (List<ProductSkuModel>)productModel.getProductSkuList();
                    if(listSku!=null && listSku.size()>0){
                        for(ProductSkuModel sku:listSku){
                            //插入 SKU 返回 skuID 生成 skuCode
                            sku.setProductId(productId);

                            String skuCode = sku.getProductSkuCode();
                            sku.setProductSkuId(null);
                            
                            if(sku.getColorValueAlias()==null||sku.getColorValueAlias().length()<1){
                            	sku.setColorValueAlias(sku.getAttrColorValue());
                            }
                            if(sku.getSpecValueAlias()==null||sku.getSpecValueAlias().length()<1){
                            	sku.setSpecValueAlias(sku.getAttrSpecValue());
                            }
                            sku.setIsAvailable(1);
                            productSkuDao.insert(sku);
                            long productSkuId = sku.getProductSkuId();

                            //插入 price
                            ProductPriceModel productPriceModel = sku.getProductPriceModel();
                            if(productPriceModel!=null){
                                productPriceModel.setProductSkuId(productSkuId);
                                productPriceModel.setProductSkuCode(skuCode);
                                productPriceModel.setProductId(productId);
                                productPriceModel.setProductPriceId(null);
                               
                                
                                if(sku.getMainSku().longValue()==1){
                                    //如果是主 SKU 价格集成到 product 表
//                                    productModelCode.setCostPrice(productPriceModel.getCostPrice());
//                                    productModelCode.setTshPrice(productPriceModel.getTshPrice());
//                                    productModelCode.setMarketPrice(productPriceModel.getMarketPrice());
//                                    productModelCode.setTb(productPriceModel.getTb());

                                    //如果是主 SKU 价格集成到 product 表
                                    productModel.setCostPrice(productPriceModel.getCostPrice());
                                    productModel.setTshPrice(productPriceModel.getTshPrice());
                                    productModel.setMarketPrice(productPriceModel.getMarketPrice());
                                    productModel.setTb(productPriceModel.getTb());
                                }
                                //新增商品SKU 插入价格审核
                                ProductPriceListModel priceListModel = new ProductPriceListModel();
                                priceListModel.setProductSkuCode(skuCode);
                                priceListModel.setCostPrice(productPriceModel.getCostPrice());
                                priceListModel.setMarketPrice(productPriceModel.getMarketPrice());
                                priceListModel.setTshPrice(productPriceModel.getTshPrice());
                                priceListModel.setTb(productPriceModel.getTb());
                                priceListModel.setStatus(0);
                                priceListModel.setSourceType(1);
                                priceListModel.setReason("");
                                priceListModel.setCreateBy(productModel.getCreateBy());
                                priceListModel.setCreateTime(productModel.getCreateTime());
                                priceListModel.setCreateUserName(productModel.getCreateUserName());
                                productPriceListDao.insert(priceListModel);
                                
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
                                productStockModel.setStockId(null);
                                productStockDao.insert(productStockModel);
                                
                                //调用库存系统，通知新增商品 库存信息
                                RequestObject requestObject = new RequestObject();
                                ProductStockRequestPO stockPo = new ProductStockRequestPO();
                                stockPo.setProductCode(productCode);
                                stockPo.setSkuCode(skuCode);
                                stockPo.setFlowCode("asdf234124");
                                stockPo.setChangeCount(productStockModel.getEntityStock());
                                stockPo.setChangeSource(3);
                                stockPo.setChangeReason("web 新增");
                                stockPo.setChangeStockType(3);
                                stockPo.setOpration(2);
                                stockPo.setChangeUser(productModel.getCreateUserName());
                                stockPo.setBrandCode(productModel.getBrandCode());
                                stockPo.setCategoryCode(productModel.getCatCode());

                                requestObject.setRequestParams(stockPo);
                                logger.info("web 新增SKU,新增库存,请求参数："+JSONUtil.toString(stockPo));
                                ResponseObject responseObject = productStockManager.changeStock(requestObject);
                                logger.info("web 新增SKU,返回值："+JSONUtil.toString(responseObject));
                                if(responseObject.getCode()!=0){
                                    throw new ZhaidouRuntimeException("库存 hessaion 接口错误！"+responseObject.getMsg());
                                }
                            }
                            
                            String skuKey = sku.getColorValueAlias()+sku.getSpecValueAlias();
                            if(fileNames!=null){
                                List<ProductImageModel> listImage = uploadImage(productCode,skuCode,fileNames,skuKey);
                                
                                //插入 图片
                                if(listImage!=null && listImage.size()>0){
                                    
                                    for(ProductImageModel productImageModel:listImage){
                                        if(sku.getMainSku().longValue()==1 && productImageModel.getLevel().longValue()==1){
                                            productModelCode.setMainPic(productImageModel.getImage());
                                            productModel.setMainPic(productImageModel.getImage());
                                        }
                                    }
                                }
                            }
                            
                    }
                    //修改 商品编号，价格
                    productIntegrity(productModel,productModelCode);
                    productDao.update(productModelCode);
                }
                
            }
            }
            return productCode;
        } catch (Exception e) {
            logger.error("",e);
            throw new ZhaidouRuntimeException("添加商品系统异常");
        }
    }
    
    /**
     * 修改商品
     *
     * @param productModel
     * @return
     */
    public void updateProduct(ProductModel productModel,List<ProductSkuModel> listUpdate,List<ProductSkuModel> listInsert,String[] fileNames)throws Exception
    {
        try {
            String supplierProductCode = "";
            if(productModel.getProductCode()!=null){
                supplierProductCode = productModel.getProductCode();
            }
            List<Long> listStr = new ArrayList<Long>();
            if(listUpdate!=null && listUpdate.size()>0){
                for(ProductSkuModel sku:listUpdate){
                    //修改SKU
                    productSkuDao.update(sku);
                    listStr.add(sku.getProductSkuId());
                    if(sku.getProductStockModel()!=null){
                        //修改库存
                        if(sku.getProductStockModel().getVirtualStock()!=null){
                            //调用库存系统，通知新增商品 库存信息
                            RequestObject requestObject = new RequestObject();
                            ProductStockRequestPO stockPo = new ProductStockRequestPO();
                            stockPo.setProductCode(supplierProductCode);
                            stockPo.setSkuCode(sku.getProductSkuCode());
                            stockPo.setFlowCode("asdf234124");
                            stockPo.setChangeCount(sku.getProductStockModel().getVirtualStock());
                            stockPo.setChangeSource(3);
                            stockPo.setChangeReason("web 编辑");
                            stockPo.setChangeStockType(3);
                            stockPo.setOpration(2);
                            stockPo.setChangeUser(productModel.getUpdateUserName());
                            stockPo.setBrandCode(productModel.getBrandCode());
                            stockPo.setCategoryCode(productModel.getCatCode());
                            requestObject.setRequestParams(stockPo);
                            
                            logger.info("web 编辑修改SKU,修改库存,请求参数："+JSONUtil.toString(stockPo));
                            ResponseObject responseObject = productStockManager.changeStock(requestObject);
                            logger.info("web 编辑修改SKU,返回值："+JSONUtil.toString(responseObject));
                            if(responseObject.getCode()!=0){
                                throw new ZhaidouRuntimeException("库存 hessaion 接口错误！"+responseObject.getMsg());
                            }
                        }
//                        productStockDao.update(sku.getProductStockModel());
                    }
                    if(sku.getProductPriceModel()!=null){
                        ProductPriceModel productPriceModel = sku.getProductPriceModel();
                        
                        if(sku.getMainSku()==1){
                            //如果是主 SKU 价格集成到 product 表
                            productModel.setCostPrice(productPriceModel.getCostPrice());
                            productModel.setTshPrice(productPriceModel.getTshPrice());
                            productModel.setMarketPrice(productPriceModel.getMarketPrice());
                            productModel.setTb(productPriceModel.getTb());
                        }
                        //修改价格
//                        productPriceDao.update(productPriceModel);
                    }
                    
                    String skuKey = sku.getColorValueAlias()+sku.getSpecValueAlias();
                    List<ProductImageModel> listImage = uploadImage(productModel.getProductCode(),sku.getProductSkuCode(),fileNames,skuKey);
                    //插入 图片
                    if(listImage!=null && listImage.size()>0){
                        for(ProductImageModel productImageModel:listImage){
                            if(sku.getMainSku().longValue()==1 && productImageModel.getLevel().longValue()==1){
                                productModel.setMainPic(productImageModel.getImage());
                            } 
                        }
                    }
                }
            }
            
            //批量逻辑删除SKU操作
            Map<String,Object> mapDelete = new HashMap<String,Object>();
            if(listStr.size()==0){
                listStr.add(0l);
            }
            mapDelete.put("list", listStr);
            mapDelete.put("productId", productModel.getProductId());
            mapDelete.put("isAvailable", 2);
            int updateNum = 0;
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
                    stockPo.setChangeReason("web 编辑删除");
                    stockPo.setChangeStockType(2);
                    stockPo.setOpration(2);
                    stockPo.setCategoryCode(productModel.getCatCode());
                    stockPo.setBrandCode(productModel.getBrandCode());
                    stockPo.setChangeUser(productModel.getUpdateUserName());
                    
                    requestObject.setRequestParams(stockPo);
                    logger.info("job 编辑删除SKU,清除SKU库存,请求参数："+JSONUtil.toString(stockPo));
                    ResponseObject responseObject = productStockManager.changeStock(requestObject);
                    logger.info("job 编辑删除SKU,返回值："+JSONUtil.toString(responseObject));
                    if(responseObject.getCode()!=0){
                        logger.error("调用库存借口错误！"+responseObject.getMsg());
                        throw new ZhaidouRuntimeException("调用库存借口错误！"+responseObject.getMsg());
                    } 
                }
            }
            
            if(listInsert!=null && listInsert.size()>0){
                for(ProductSkuModel sku:listInsert){
                    String skuCode = sku.getProductSkuCode();
                    sku.setProductId(productModel.getProductId());
                    
                    sku.setIsAvailable(1);
                    //插入Sku
                    
                    productSkuDao.insert(sku);
                    
                    if(sku.getProductStockModel()!=null){
                        sku.getProductStockModel().setSkuId(sku.getProductSkuId());
                        sku.getProductPriceModel().setProductSkuCode(skuCode);
                        sku.getProductPriceModel().setProductId(productModel.getProductId());
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
                            stockPo.setChangeReason("web 编辑新增");
                            stockPo.setChangeStockType(2);
                            stockPo.setOpration(2);
                            stockPo.setChangeUser(productModel.getUpdateUserName());
                            stockPo.setBrandCode(productModel.getBrandCode());
                            stockPo.setCategoryCode(productModel.getCatCode());
                            requestObject.setRequestParams(stockPo);
                            logger.info("web 编辑新增SKU,新增库存,请求参数："+JSONUtil.toString(stockPo));
                            ResponseObject responseObject = productStockManager.changeStock(requestObject);
                            logger.info("web 编辑新增SKU,返回值："+JSONUtil.toString(responseObject));
                            if(responseObject.getCode()!=0){
                                throw new ZhaidouRuntimeException("库存 hessaion 接口错误！"+responseObject.getMsg());
                            } 
                        }
                        productStockDao.insert(sku.getProductStockModel());
                    }
                    if(sku.getProductPriceModel()!=null){
                        ProductPriceModel productPriceModel = sku.getProductPriceModel();
                        productPriceModel.setProductSkuId(sku.getProductSkuId());
                        productPriceModel.setProductSkuCode(skuCode);
                        productPriceModel.setProductId(productModel.getProductId());
                        
                        if(sku.getMainSku()==1){
                            //如果是主 SKU 价格集成到 product 表
                            productModel.setCostPrice(productPriceModel.getCostPrice());
                            productModel.setTshPrice(productPriceModel.getTshPrice());
                            productModel.setMarketPrice(productPriceModel.getMarketPrice());
                            productModel.setTb(productPriceModel.getTb());
                        }
                        //新增商品SKU 插入价格审核
                        ProductPriceListModel priceListModel = new ProductPriceListModel();
                        priceListModel.setProductSkuCode(skuCode);
                        priceListModel.setCostPrice(productPriceModel.getCostPrice());
                        priceListModel.setMarketPrice(productPriceModel.getMarketPrice());
                        priceListModel.setTshPrice(productPriceModel.getTshPrice());
                        priceListModel.setTb(productPriceModel.getTb());
                        priceListModel.setStatus(0);
                        priceListModel.setSourceType(1);
                        priceListModel.setReason("");
                        priceListModel.setCreateBy(productModel.getUpdateBy());
                        priceListModel.setCreateTime(productModel.getUpdateTime());
                        priceListModel.setCreateUserName(productModel.getUpdateUserName());
                        productPriceListDao.insert(priceListModel);
                        
                        productPriceModel.setCostPrice(0d);
                        productPriceModel.setMarketPrice(0d);
                        productPriceModel.setTshPrice(0d);
                        productPriceModel.setTb(0l);
                        
                        //插入价格
                        productPriceDao.insert(sku.getProductPriceModel());
                        

                        String skuKey = sku.getColorValueAlias()+sku.getSpecValueAlias();
                        List<ProductImageModel> listImage = uploadImage(productModel.getProductCode(),sku.getProductSkuCode(),fileNames,skuKey);
                        
                        //插入 图片
                        if(listImage!=null && listImage.size()>0){
                            for(ProductImageModel productImageModel:listImage){
                                if(sku.getMainSku().longValue()==1 && productImageModel.getLevel().longValue()==1){
                                    productModel.setMainPic(productImageModel.getImage());
                                }
                            }
                        }
                        
                        if(sku.getProductImagerList()!=null){
                            for(ProductImageModel imgModel:sku.getProductImagerList()){
                                imgModel.setProductSkuId(productModel.getProductId());
                                productImageDao.insert(imgModel);
                            }
                        }
                    }
                }
            }
            
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
                  //否则  就修改弹性域属性
                    if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                        for(ProductAttributeModel productAttributeMod:productModel.getProductAttributeList()){
                        	if(productAttributeMod.getProductAttributeId()!=null){
                        		productAttributeDao.update(productAttributeMod);
                        	}else{
                        	    productAttributeMod.setProductId(productModel.getProductId());
                        		productAttributeDao.insert(productAttributeMod);
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
            //计算商品信息完整度
            productIntegrity(productModel,null);
            
            productModel.setCostPrice(null);
            productModel.setMarketPrice(null);
            productModel.setTb(null);
            productModel.setTshPrice(null);
            productDao.update(productModel);
            
        } catch (Exception e) {
            logger.error("",e);
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
        ProductModel result = productDao.queryOne(productModel);
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
        long result = productDao.countListPage(productModel);
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
        long count = productDao.countListPage(productModel);
        List<ProductModel> result=null;
        if (count > 0) {
            page.setTotalCount(count);
        result= productDao.queryListPage(productModel, page.getPageNum(), page.getNumPerPage());
        }
        return result;
    }

    public void deleteById(ProductModel productModel)throws Exception
    {
        productDao.delete(productModel);
    }

    /**
     * @Description: 根据产品编号列表返回  Map<productCode,productId>
     */
	@Override
	public Map<String, Long> getMapByProductCodes(List<String> productCodeList) throws Exception{
		if (logger.isDebugEnabled())
        {
            logger.debug("--Params-->" + JSONUtil.toString(productCodeList));
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
        if(productModel.getMarketPrice()!=null && productModel.getMarketPrice()>0){
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
        String integrityDesc="";
        if(sb.length()>0){
               integrityDesc = sb.toString().substring(0, sb.toString().lastIndexOf(","));
        }
        productModel.setIntegrityDesc(integrityDesc);
        productModel.setIntegrity(integrity);
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
    public ProductModel getProductByCode(String productCode) throws DaoException {
        // TODO Auto-generated method stub
        return productDao.getProductByCode(productCode);
    }
    
    @Resource
    private ImagePathManager imagePathManager;
    
    private List<ProductImageModel>  uploadImage(String productCode,String skuCode,String[] fileNames,String skuKey){
        logger.info("上传图片：productCode="+productCode+", skuCode="+skuCode+", fileNames="+JSONUtil.toString(fileNames));
        List<ProductImageModel> listImage = new ArrayList<ProductImageModel>();
        ProductImageModel imageModel = null;
        
        List<Image> listImg = new ArrayList<Image>();
        Image image = null;
        ResponseObject responseObject = null;
        if(fileNames!=null && fileNames.length>0){
            for(String str:fileNames){
                    if(str!=null && !"".equals(str)){
                        String[] str1 = str.split("#-#");
                        if(str1[0].equals(skuKey)){
                            String fileName = "";
                            fileName = str1[1];
                        image = new Image();
                        image.setImageIndex(Integer.parseInt(str1[2]));
                        image.setImageName(fileName);
                        listImg.add(image);
                        }
                        
                    }
            }
            if(listImg.size()>0){
                RequestObject requestObjest = new RequestObject();
                UploadImageRequestPO requestPo = new UploadImageRequestPO();
                requestPo.setImages(listImg);
                requestPo.setRelationCode(productCode);
                requestPo.setRelationSubCode(skuCode);
                requestPo.setTmpPath(imgTmpDirRel+"/"+productCode);
                requestPo.setRelationType(1);
                requestPo.setNeedlevel(1);
                requestObjest.setRequestParams(requestPo);
                logger.info("图片服务,上传图片,请求参数："+JSONUtil.toString(requestObjest));
                responseObject = imagePathManager.uploadImage(requestObjest);
                logger.info("图片服务,返回值："+JSONUtil.toString(responseObject));
                if(responseObject.getCode()==0){
                    UploadImageResponsePO responsePo = (UploadImageResponsePO)responseObject.getData();
                    listImg = responsePo.getImages();
                    for(Image img :listImg){
                        imageModel = new ProductImageModel();
                        long level = Long.parseLong(String.valueOf(img.getImageIndex()));
                        
                        imageModel.setLevel(level);
                        imageModel.setImage(responsePo.getImagePath()+"/"+img.getImageName()+responsePo.getImageType());
                        imageModel.setType(1l);
                        listImage.add(imageModel);
                    }
                }else{
                    logger.error("图片hessain 接口异常");
                    throw new ZhaidouRuntimeException("图片 hessaion 接口异常");
                }
            }
        }
        return listImage;
    }

    @Override
    public Integer getProductCode(ProductModel productModel) throws DaoException {
        // TODO Auto-generated method stub
        return productDao.getProductCode(productModel);
    }
    
    @Resource
    private ProductInfoAuthDao productInfoAuthDao;
    
    /**
     * 导入编辑 修改商品
     *
     * @param listProduct
     * @throws Exception
     */
    @Override
    public void excelUpdate(List<ProductModel> listProduct) throws Exception {
        if(listProduct!=null && listProduct.size()>0){
            for(ProductModel productModel:listProduct){
                productDao.update(productModel);
                
                if(productModel.getProductOperateModel()!=null){
                    productOperateDao.updateByProductId(productModel.getProductOperateModel());
                    //如果修改了加价率,则修改价格
                    if(productModel.getProductOperateModel().getProductPriceRate()>0){
                        List<Long> productIdList = new ArrayList<Long>();
                        productIdList.add(productModel.getProductId());
                        List<ProductPriceModel> priceList = productPriceDao.getProductPriceListByProductId(productIdList);
                        if(priceList!=null && priceList.size()>0){
                            for(ProductPriceModel priceModel:priceList){
                                if(priceModel.getCostPrice()>0 && priceModel.getMarketPrice()>0){
                                    Double tshPrice= Math.ceil(priceModel.getCostPrice()*(1+productModel.getProductOperateModel().getProductPriceRate()/100));
                                    Long tb=(long) Math.ceil(priceModel.getMarketPrice()-tshPrice);
                                    
                                    //新增商品SKU 插入价格审核
                                    ProductPriceListModel priceListModel = new ProductPriceListModel();
                                    priceListModel.setProductSkuCode(priceModel.getProductSkuCode());
                                    priceListModel.setCostPrice(priceModel.getCostPrice());
                                    priceListModel.setMarketPrice(priceModel.getMarketPrice());
                                    priceListModel.setTshPrice(tshPrice);
                                    priceListModel.setTb(tb);
                                    
                                    priceListModel.setOldCostPrice(priceModel.getCostPrice());
                                    priceListModel.setOldMarketPrice(priceModel.getMarketPrice());
                                    priceListModel.setOldTshPrice(priceModel.getTshPrice());
                                    priceListModel.setOldTb(priceModel.getTb());
                                    priceListModel.setStatus(0);
                                    priceListModel.setSourceType(1);
                                    priceListModel.setReason("导入修改加价率改变价格");
                                    priceListModel.setCreateBy(productModel.getUpdateBy());
                                    priceListModel.setCreateTime(productModel.getUpdateTime());
                                    priceListModel.setCreateUserName(productModel.getUpdateUserName());
                                    productPriceListDao.insert(priceListModel);
                                }
                            }
                        }
                    }
                }
                if(productModel.getProductMallModel()!=null){
                    productMallDao.updateByProductId(productModel.getProductMallModel());
                }
                if(productModel.getProductInfoModel()!=null){
                    productInfoDao.updateByProductId(productModel.getProductInfoModel());
                }
                
                if(productModel.getCatCode()!=null && !"".equals(productModel.getCatCode())){
                    ProductModel productMod = productDao.getProductByCode(productModel.getProductCode());
                    //如果商品基础类型有修改
                    if(productMod!=null && !productMod.getCatCode().equals(productModel.getCatCode())){
                        //则删除之前的弹性域
                        ProductAttributeModel productAttributeModel = new ProductAttributeModel();
                        productAttributeModel.setProductId(productModel.getProductId());
                        productAttributeDao.delete(productAttributeModel);
                        
                        //在插入现有弹性域
                        if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                            for(ProductAttributeModel productAttributeMod:productModel.getProductAttributeList()){
                            	productAttributeMod.setProductId(productModel.getProductId());
                                productAttributeDao.insert(productAttributeMod);
                            }
                        }
                    }else{
                      //否则  就修改弹性域属性
                        if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                        	 //则删除之前的弹性域
                            ProductAttributeModel productAttributeModel = new ProductAttributeModel();
                            productAttributeModel.setProductId(productModel.getProductId());
                            productAttributeDao.delete(productAttributeModel);
                            
                            //在插入现有弹性域
                            if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                                for(ProductAttributeModel productAttributeMod:productModel.getProductAttributeList()){
                                	productAttributeMod.setProductId(productModel.getProductId());
                                    productAttributeDao.insert(productAttributeMod);
                                }
                            }
                        }
                    }
                }else{
                    //否则  就修改弹性域属性
                    if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                    	 //则删除之前的弹性域
                        ProductAttributeModel productAttributeModel = new ProductAttributeModel();
                        productAttributeModel.setProductId(productModel.getProductId());
                        productAttributeDao.delete(productAttributeModel);
                        
                        //在插入现有弹性域
                        if(productModel.getProductAttributeList()!=null && productModel.getProductAttributeList().size()>0){
                            for(ProductAttributeModel productAttributeMod:productModel.getProductAttributeList()){
                            	productAttributeModel.setProductId(productModel.getProductId());
                                productAttributeDao.insert(productAttributeMod);
                            }
                        }
                    }
                }
                
                //插入 审核列表
                ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
                productInfoAuthModel.setCreateTime(productModel.getUpdateTime());
                productInfoAuthModel.setCreateBy(productModel.getUpdateBy());
                productInfoAuthModel.setCreateUserName(productModel.getUpdateUserName());
                productInfoAuthModel.setUpdateBy(productModel.getUpdateBy());
                productInfoAuthModel.setUpdateTime(productModel.getUpdateTime());
                productInfoAuthModel.setUpdateUserName(productModel.getUpdateUserName());
                productInfoAuthModel.setType((long)2);
                productInfoAuthModel.setStaus((long)2);
                productInfoAuthModel.setSourceType(1);
                
                
                ProductInfoExamineModel examineModel=new ProductInfoExamineModel();
                
                examineModel.setProductName(productModel.getProductName());
                examineModel.setProductCode(productModel.getProductCode());
                examineModel.setCatCode(productModel.getCatCode());
                examineModel.setCatName(productModel.getCatName());
                examineModel.setBrandCode(productModel.getBrandCode());
                examineModel.setBrandName(productModel.getBrandName());
                examineModel.setSupplierId(productModel.getSupplierId());
                examineModel.setProductDesc(productModel.getProductDesc());
                examineModel.setProductShelves(productModel.getProductShelves());
                examineModel.setProductId(productModel.getProductId());
                if(productModel.getProductMallModel()!=null){
                    examineModel.setProductWeight(productModel.getProductMallModel().getProductWeight());
                    examineModel.setProductLong(productModel.getProductMallModel().getProductLong());
                    examineModel.setProductWidth(productModel.getProductMallModel().getProductWidth());
                    examineModel.setProductHeight(productModel.getProductMallModel().getProductHeight());
                    examineModel.setProductProducer(productModel.getProductMallModel().getProductProducer());
                    examineModel.setProductDensity(productModel.getProductMallModel().getProductDensity());
                    examineModel.setProductAtrNumber(productModel.getProductMallModel().getProductAtrNumber());
                }
                if(productModel.getProductOperateModel()!=null){
                    examineModel.setProductShortName(productModel.getProductOperateModel().getProductShortName());
                    examineModel.setProductPrefix(productModel.getProductOperateModel().getProductPrefix());
                    examineModel.setProductSuffix(productModel.getProductOperateModel().getProductSuffix());
                    examineModel.setProductKeyword(productModel.getProductOperateModel().getProductKeyword());
                    examineModel.setProductDownShow(productModel.getProductOperateModel().getProductDownShow());
                    examineModel.setProductPriceRate(productModel.getProductOperateModel().getProductPriceRate());
                    examineModel.setProductVideoUrl(productModel.getProductOperateModel().getProductVideoUrl());
                }
                
                ProductModel p = productDao.getProductByCode(productModel.getProductCode());
                if(productModel.getProductName()==null){
                    productModel.setProductName(p.getProductName());
                    examineModel.setProductName(p.getProductName());
                }else{
                    examineModel.setProductName(productModel.getProductName());
                }
                if(productModel.getCatCode()==null){
                    productModel.setCatCode(p.getCatCode());
                    productModel.setCatName(p.getCatName());
                    examineModel.setCatCode(p.getCatCode());
                    examineModel.setCatName(p.getCatName());
                }else{
                    examineModel.setCatCode(productModel.getCatCode());
                    examineModel.setCatName(productModel.getCatName());
                }
                if(productModel.getBrandCode()==null){
                    productModel.setBrandCode(p.getBrandCode());
                    productModel.setBrandName(p.getBrandName());
                    examineModel.setBrandCode(p.getBrandCode());
                    examineModel.setBrandName(p.getBrandName());
                }else{
                    examineModel.setBrandCode(productModel.getBrandCode());
                    examineModel.setBrandName(productModel.getBrandName());
                }
                InfoAuthJsonStringModel jsonStringModel = new InfoAuthJsonStringModel();
                jsonStringModel.setUpdate(JSONUtil.toString(productModel));
                productInfoAuthModel.setNewValue(JSONUtil.toString(jsonStringModel));
                productInfoAuthModel.setReason("后台excel导入修改无需审核");
                productInfoAuthModel.setProductCode(productModel.getProductCode());
                
                productInfoAuthDao.insert(productInfoAuthModel);
                
                
                
                examineModel.setSupplierId(p.getSupplierId());
                examineModel.setProductInfoAuthId(productInfoAuthModel.getProductInfoAuthId());
                
                productInfoExamineDao.add(examineModel);
                
                
            }
        }
    }

    @Override
    public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel, int pageNo,
            int pageSize) throws Exception {
        return productDao.exportPrdouct(productSkuExportModel, pageNo, pageSize);
    }

    @Override
    public Long exportPrdouctCount(ProductSkuExportModel productSkuExportModel) throws Exception {
        return productDao.exportPrdouctCount(productSkuExportModel);
    }

	@Override
	public List<ProductModel> getProductListAndMarkUpRate(
			ProductModel productModel, Page page) throws Exception {
		 long count = productDao.countListPageAndMarkUpRate(productModel);
	        List<ProductModel> result=null;
	        if (count > 0) {
	            page.setTotalCount(count);
	        result= productDao.queryListPageAndMarkUpRate(productModel, page.getPageNum(), page.getNumPerPage());
	        }
	        return result;
	}
}
