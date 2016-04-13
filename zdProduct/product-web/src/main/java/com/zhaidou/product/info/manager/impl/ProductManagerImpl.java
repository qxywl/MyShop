package com.zhaidou.product.info.manager.impl;

import com.zhaidou.common.exception.DaoException;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.service.ProductBrandService;
import com.zhaidou.product.info.dao.ProductSkuDao;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.model.*;
import com.zhaidou.product.info.service.*;
import com.zhaidou.product.info.util.FileUtil;
import com.zhaidou.product.info.util.InfoUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("productManager")
public class ProductManagerImpl implements ProductManager {
    private static Logger logger = Logger.getLogger(ProductManagerImpl.class);
    @Resource
    private ProductService productService;
    
    @Resource
    private ProductOperateService productOperateService;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ProductSkuService productSkuService;
    @Resource
    private ProductAttributeService productAttributeService;
    @Resource
    private ProductMallService productMallService;
    @Resource
    private ProductInfoAuthService productInfoAuthService;
    @Resource
    private ProductBrandService productBrandService;
    @Resource
    private ProductPriceListService productPriceListService;
    @Resource
    private ProductInfoExamineService productInfoExamineService;
    @Value("#{propertyConfigurerForProject2['img_upload_local']}")
    private String imgUploadLocal;     //图片临时目录路径
    @Resource
    private ProductColorService productColorService;
    @Resource
    private ProductSizeService productSizeService;
    
    
    /**
     * 获取商品详情
     * @param productModel
     * @return
     */
    @Override
    public Map<String, Object> getProductInfo(ProductModel productModel) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //基本属�?
            productModel = productService.getProductById(productModel);
            if(productModel==null){
                throw new ZhaidouRuntimeException("商品错误：该商品没有与之关联的供应商");
            }
//            categoryService.queryBaseCategoryColorAndSizeByCode(productModel)

            //运营属�?
            ProductOperateModel productOperateModel = new ProductOperateModel();
            productOperateModel.setProductId(productModel.getProductId());
            productOperateModel = productOperateService.getProductOperateById(productOperateModel);
            
            //sku属�?
            ProductSkuModel productSkuModel = new ProductSkuModel();
            productSkuModel.setProductId(productModel.getProductId());
            Map<String,Object> mapSku = productSkuService.getSkuByProductId(productSkuModel);
            
            //属性组
            List<ProductAttributeModel> listAttribute= productAttributeService.getAttributeByProductId(productModel.getProductId());
            
            //商品 prodcutInfo
            ProductInfoModel productInfoModel = new ProductInfoModel();
            productInfoModel.setProductId(productModel.getProductId());
            productInfoModel = productInfoService.getProductInfoById(productInfoModel);
            
            
            //商城扩展属�?
            ProductMallModel productMallModel = new ProductMallModel();
            productMallModel.setProductId(productModel.getProductId());
            productMallModel = productMallService.getProductMallById(productMallModel);
            
            
            if(productModel.getCreateTime()!=null && productModel.getCreateTime()>0){
                productModel.setCreateTimes(InfoUtil.dateLongToString(productModel.getCreateTime(), InfoUtil.dateString));
            }
            if(productModel.getUpdateTime()!=null && productModel.getUpdateTime()>0){
                productModel.setUpdateTimes(InfoUtil.dateLongToString(productModel.getUpdateTime(), InfoUtil.dateString));
            }
            if(productModel.getFirstShelvesTime()!=null && productModel.getFirstShelvesTime()>0){
                String str = InfoUtil.dateLongToString(productModel.getFirstShelvesTime(), InfoUtil.dateString);
                map.put("productFirstShelvesTime", str);
            }
            if(productModel.getLastShelvesTime()!=null && productModel.getLastShelvesTime()>0){
                String str = InfoUtil.dateLongToString(productModel.getLastShelvesTime(), InfoUtil.dateString);
                map.put("productLastShelvesTime", str);
            }
            if(productModel.getDownShelvesTime()!=null && productModel.getDownShelvesTime()>0){
                String str = InfoUtil.dateLongToString(productModel.getDownShelvesTime(), InfoUtil.dateString);
                map.put("productDownShelvesTime", str);
            }
            
            productModel.setProductOperateModel(productOperateModel);
            productModel.setProductSkuList((List<ProductSkuModel>)mapSku.get("productSkuList"));
            productModel.setProductAttributeList(listAttribute);
            productModel.setProductInfoModel(productInfoModel);
            productModel.setProductMallModel(productMallModel);
            
            map.put("attrColorName", mapSku.get("attrColorName"));
            map.put("attrSpecName",  mapSku.get("attrSpecName"));
        } catch (Exception e) {
            logger.error("获取 商品详情 异常",e);
            throw new ZhaidouRuntimeException(e.getMessage());
        }
        map.put("productModel", productModel);
        return map;
    }
    
    /**
     * 获取商品列表
     * @param productModel
     * @return
     */
    @Override
    public List<ProductModel> getProductList(Page page,ProductModel productModel) {
          List<ProductModel> productList = null;
          try {
              productList = productService.getProduct(productModel, page);
          } catch (Exception e) {
              logger.error("获取 商品列表 异常",e);
              throw new ZhaidouRuntimeException("获取 商品列表 异常");
          }
          if(productList != null && productList.size()>0){
              for(ProductModel productMod:productList){
                  if(productMod.getCreateTime()!=null){
                      productMod.setCreateTimes(InfoUtil.dateLongToString(productMod.getCreateTime(), InfoUtil.dateString));
                  }
                  
                  if(productMod.getLastShelvesTime()!=null){
                      productMod.setLastShelvesTimes(InfoUtil.dateLongToString(productMod.getLastShelvesTime(), InfoUtil.dateString));
                  }else{
                      if(productMod.getFirstShelvesTime()!=null){
                          productMod.setLastShelvesTimes(InfoUtil.dateLongToString(productMod.getFirstShelvesTime(),InfoUtil.dateString));
                      }
                  }
                  if(productMod.getDownShelvesTime()!=null){
                      productMod.setDownShelvesTimes(InfoUtil.dateLongToString(productMod.getDownShelvesTime(), InfoUtil.dateString));
                  }
                  
              }
          }
         
        return productList;
    }
    
    /**
     * 获取价格   product-web
     * @param productModel
     * @return
     */
    private void getPrice(ProductModel productModel,List<ProductSkuModel> listSku){
        try {
            if(listSku!=null && listSku.size()>0){
                for(ProductSkuModel sku : listSku){
                    if(sku.getMainSku().longValue()==1){
                        if(sku.getProductPriceModel()!=null){
                            ProductPriceModel productPriceModel = sku.getProductPriceModel();
                            productModel.setCostPrice(productPriceModel.getCostPrice());
                            productModel.setMarketPrice(productPriceModel.getMarketPrice());
                            productModel.setTshPrice(productPriceModel.getTshPrice());
                            productModel.setTb(productPriceModel.getTb());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取价格错误 ",e);
            throw new ZhaidouRuntimeException("获取价格错误");
        }
    }
    
    /**
     * 获取商品
     * @param productModel
     * @return
     */
    @Override
    public Map<String, Object> getProductById(ProductModel productModel) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            productModel = productService.getProductById(productModel);
        } catch (Exception e) {
            logger.error("获取 商品 异常",e);
            new ZhaidouRuntimeException("获取 商品 异常");
        }
        map.put("productModel", productModel);
        return map;
    }
    
    @Resource
    private ImagePathManager imagePathManager;
    @Value("#{propertyConfigurerForProject2['img_tmp_dir_rel']}")
    private String imgTmp;
    /**
     * 新增商品
     * @param productModel
     * @return
     */
    @Override
    public String addProduct(String thumbnailFileName,ProductModel productModel,Map<String,Object> map,String[] fileNames) {
        long userId = Long.parseLong(map.get("userId").toString());
        String userName = map.get("userName").toString();
        long time = new Date().getTime()/1000; 
        
        productModel.setCreateBy(userId);
        productModel.setCreateUserName(userName);
        productModel.setCreateTime(time);
        productModel.setUpdateBy(userId);
        productModel.setUpdateTime(time);
        productModel.setUpdateUserName(userName);
        
        String result = "";
        try {
        	 if(thumbnailFileName!=null && thumbnailFileName.length() > 2){
                 String thumbnail = FileUtil.uploadThumbnailImage(productModel.getProductCode(), thumbnailFileName, imgTmp, imagePathManager);
                 productModel.getProductOperateModel().setProductThumbnail(thumbnail);
             }
		} catch (Exception e) {
		    logger.error("",e);
			result = "上传图片失败,请稍等！";
			return result;
		}
        
        List<ProductSkuModel> listSku = (List<ProductSkuModel>)productModel.getProductSkuList();
        int i=1;
        if(listSku!=null && listSku.size()>0){
            for(ProductSkuModel sku:listSku){
                //插入 SKU 返回 skuID 生成 skuCode
                String skuCode=""; 
                if(i>=10 && i<100){
                    skuCode = productModel.getProductCode()+"0"+i;
                }else if(i>=100){
                    skuCode = productModel.getProductCode()+i;
                }else{
                    skuCode = productModel.getProductCode()+"00"+i;
                }

                sku.setProductSkuCode(skuCode);
                i++;
            }
        }
        ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
        productInfoAuthModel.setCreateTime(time);
        productInfoAuthModel.setCreateBy(userId);
        productInfoAuthModel.setCreateUserName(userName);
        productInfoAuthModel.setUpdateBy(userId);
        productInfoAuthModel.setUpdateTime(time);
        productInfoAuthModel.setUpdateUserName(userName);
        productInfoAuthModel.setType((long)1);
        productInfoAuthModel.setStaus((long)2);
        productInfoAuthModel.setSourceType(1);
        productInfoAuthModel.setReason("后台新增无需审核");
        try {
            
            productInfoAuthModel.setNewValue(JSONUtil.toString(productModel));
            //后台新增 插入商品审核
            productInfoAuthModel.setProductCode(productModel.getProductCode());
            productInfoAuthService.addProductInfoAuth(productInfoAuthModel);
            
            ProductInfoExamineModel examineModel=new ProductInfoExamineModel();
            examineModel.setProductInfoAuthId(productInfoAuthModel.getProductInfoAuthId());
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
            examineModel.setProductWeight(productModel.getProductMallModel().getProductWeight());
            examineModel.setProductLong(productModel.getProductMallModel().getProductLong());
            examineModel.setProductWidth(productModel.getProductMallModel().getProductWidth());
            examineModel.setProductHeight(productModel.getProductMallModel().getProductHeight());
            examineModel.setProductProducer(productModel.getProductMallModel().getProductProducer());
            examineModel.setProductDensity(productModel.getProductMallModel().getProductDensity());
            examineModel.setProductAtrNumber(productModel.getProductMallModel().getProductAtrNumber());
            
            examineModel.setProductShortName(productModel.getProductOperateModel().getProductShortName());
            examineModel.setProductPrefix(productModel.getProductOperateModel().getProductPrefix());
            examineModel.setProductSuffix(productModel.getProductOperateModel().getProductSuffix());
            examineModel.setProductKeyword(productModel.getProductOperateModel().getProductKeyword());
            examineModel.setProductDownShow(productModel.getProductOperateModel().getProductDownShow());
            examineModel.setProductPriceRate(productModel.getProductOperateModel().getProductPriceRate());
            examineModel.setProductVideoUrl(productModel.getProductOperateModel().getProductVideoUrl());
            productInfoExamineService.add(examineModel);
            
            //后台新增 插入日志
//            ProductInfoLogModel productInfoLogModel = new ProductInfoLogModel();
//            productInfoLogModel.setCreateBy(productModel.getCreateBy());
//            productInfoLogModel.setCreateTime(productModel.getCreateTime());
//            productInfoLogModel.setCreateUserName(productModel.getCreateUserName());
//            productInfoLogModel.setUpdateBy(productModel.getUpdateBy());
//            productInfoLogModel.setUpdateTime(productModel.getUpdateTime());
//            productInfoLogModel.setUpdateUserName(productModel.getUpdateUserName());
//            productInfoLogModel.setNewValue(productInfoAuthModel.getNewValue());
//            productInfoLogModel.setOldValue(productInfoAuthModel.getOldValue());
//            productInfoLogModel.setProductCode(productInfoAuthModel.getProductCode());
//            productInfoLogModel.setSourceType(productInfoAuthModel.getSourceType());
//            productInfoLogModel.setStatus(4);
//            productInfoLogModel.setReason(productInfoAuthModel.getReason());
//            productInfoLogModel.setType(productInfoAuthModel.getType());
//            productInfoLogService.addProductInfoLog(productInfoLogModel);
            
            List<ProductModel> listProduct = new ArrayList<ProductModel>();
            listProduct.add(productModel);
            productService.addProduct(listProduct,fileNames);
            
        } catch (Exception e) {
            logger.error("",e);
            result = e.getMessage();
        }
        return result;
    }
    
    /**
     * 删除商品
     * @param productModel
     * @return
     */
    @Override
    public Boolean deleteProduct(ProductModel productModel) {
        boolean flag = true;
        try {
            productService.deleteById(productModel);
        } catch (Exception e) {
            logger.error("",e);
            flag = false;
        }
        return flag;
    }
    
    @Resource
    private ProductSkuDao productSkuDao;
    
    /**
     * 修改商品
     *
     * @param productModel
     * @return
     */
    @Override
    public void updateProduct(ProductModel productModel,List<ProductSkuModel> listUpdate,
                              List<ProductSkuModel> listInsert,Map<String,Object> map,
                              String[] fileNames,String thumbnailFileName) {
        long userId = Long.parseLong(map.get("userId").toString());
        String userName = map.get("userName").toString();
        long time = new Date().getTime()/1000; 
        
        productModel.setUpdateBy(userId);
        productModel.setUpdateTime(time);
        productModel.setUpdateUserName(userName);
        
        //判断是否改动价格
        ProductModel oldProduct = null;
        try {
            oldProduct = (ProductModel)InfoUtil.map.get(productModel.getProductId()+"");
            List<ProductPriceListModel> priceList = InfoUtil.comparisonSku(oldProduct, listUpdate);
            if(priceList!=null && priceList.size()>0){
                for(ProductPriceListModel priceListModel:priceList){
                    priceListModel.setCreateBy(userId);
                    priceListModel.setCreateTime(time);
                    priceListModel.setCreateUserName(userName);
                    priceListModel.setStatus(0);
                    priceListModel.setSourceType(1);
                    priceListModel.setReason("");
                    logger.info("商品价格变动,插入价格审核记录："+JSONUtil.toString(priceListModel));
                    productPriceListService.addProductPriceList(priceListModel);
                }
            }
        } catch (Exception e2) {
            logger.error("修改插入价格审核表异",e2);
            throw new ZhaidouRuntimeException("修改插入价格审核表异");
        }
        
        if(thumbnailFileName!=null){
            String thumbnail = FileUtil.uploadThumbnailImage(productModel.getProductCode(), thumbnailFileName, imgTmp, imagePathManager);
            productModel.getProductOperateModel().setProductThumbnail(thumbnail);
        }
        ProductModel changeProduct = new ProductModel();
        productModel.setProductSkuList(listUpdate);
        boolean flag = InfoUtil.comparisonProduct(oldProduct,productModel,changeProduct);
        
        
        try {
            int updateNum = 0;
            ProductSkuModel skuModel = new ProductSkuModel();
            skuModel.setProductId(productModel.getProductId());
            List<ProductSkuModel> aa = productSkuDao.getSkuByProductId(skuModel);
            if(aa!=null){
                updateNum = aa.size();
            }
            int i = 1;
            if(listInsert!=null && listInsert.size()>0){
                for(ProductSkuModel sku:listInsert){
                    String skuCode = "";
                    if(updateNum+i>=10 && updateNum+i<100){
                        skuCode=productModel.getProductCode()+("0"+(updateNum+i));
                    }else if(updateNum+i>=100){
                        skuCode=productModel.getProductCode()+(updateNum+i);
                    }else{
                        skuCode=productModel.getProductCode()+("00"+(updateNum+i));
                    }
                    sku.setProductSkuCode(skuCode);
                    i++;
                }
            }
            
            
            //插入 审核?
            ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
            productInfoAuthModel.setCreateTime(time);
            productInfoAuthModel.setCreateBy(userId);
            productInfoAuthModel.setCreateUserName(userName);
            productInfoAuthModel.setUpdateBy(userId);
            productInfoAuthModel.setUpdateTime(time);
            productInfoAuthModel.setUpdateUserName(userName);
            productInfoAuthModel.setType((long)2);  //2表示编辑
            productInfoAuthModel.setStaus((long)2);
            productInfoAuthModel.setSourceType(1); //1表示后台
            productInfoAuthModel.setReason("后台修改无需审核");
            productInfoAuthModel.setProductCode(productModel.getProductCode());
            if(JSONUtil.toString(oldProduct).length()<65400){
                productInfoAuthModel.setOldValue(JSONUtil.toString(oldProduct));
            }
            
            InfoAuthJsonStringModel jsonStringModel = new InfoAuthJsonStringModel();
            
            jsonStringModel.setUpdate(JSONUtil.toString(productModel));
            jsonStringModel.setInsert(JSONUtil.toString(listInsert));
            productInfoAuthModel.setNewValue(JSONUtil.toString(jsonStringModel));
        
            productInfoAuthService.addProductInfoAuth(productInfoAuthModel);
            
            ProductInfoExamineModel examineModel=new ProductInfoExamineModel();
            examineModel.setProductInfoAuthId(productInfoAuthModel.getProductInfoAuthId());
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
            examineModel.setProductWeight(productModel.getProductMallModel().getProductWeight());
            examineModel.setProductLong(productModel.getProductMallModel().getProductLong());
            examineModel.setProductWidth(productModel.getProductMallModel().getProductWidth());
            examineModel.setProductHeight(productModel.getProductMallModel().getProductHeight());
            examineModel.setProductProducer(productModel.getProductMallModel().getProductProducer());
            examineModel.setProductDensity(productModel.getProductMallModel().getProductDensity());
            examineModel.setProductAtrNumber(productModel.getProductMallModel().getProductAtrNumber());
            
            examineModel.setProductShortName(productModel.getProductOperateModel().getProductShortName());
            examineModel.setProductPrefix(productModel.getProductOperateModel().getProductPrefix());
            examineModel.setProductSuffix(productModel.getProductOperateModel().getProductSuffix());
            examineModel.setProductKeyword(productModel.getProductOperateModel().getProductKeyword());
            examineModel.setProductDownShow(productModel.getProductOperateModel().getProductDownShow());
            examineModel.setProductPriceRate(productModel.getProductOperateModel().getProductPriceRate());
            examineModel.setProductVideoUrl(productModel.getProductOperateModel().getProductVideoUrl());
            productInfoExamineService.add(examineModel);
            
            //后台新增 插入日志
//            ProductInfoLogModel productInfoLogModel = new ProductInfoLogModel();
//            productInfoLogModel.setCreateBy(productModel.getCreateBy());
//            productInfoLogModel.setCreateTime(productModel.getCreateTime());
//            productInfoLogModel.setCreateUserName(productModel.getCreateUserName());
//            productInfoLogModel.setUpdateBy(productModel.getUpdateBy());
//            productInfoLogModel.setUpdateTime(productModel.getUpdateTime());
//            productInfoLogModel.setUpdateUserName(productModel.getUpdateUserName());
//            productInfoLogModel.setNewValue(productInfoAuthModel.getNewValue());
//            productInfoLogModel.setOldValue(productInfoAuthModel.getOldValue());
//            productInfoLogModel.setProductCode(productInfoAuthModel.getProductCode());
//            productInfoLogModel.setSourceType(productInfoAuthModel.getSourceType());
//            productInfoLogModel.setStatus(4);
//            productInfoLogModel.setReason(productInfoAuthModel.getReason());
//            productInfoLogModel.setType(productInfoAuthModel.getType());
//            productInfoLogService.addProductInfoLog(productInfoLogModel);
            
            productService.updateProduct(productModel,listUpdate,listInsert,fileNames);
        } catch (Exception e) {
            logger.error("",e);
            throw new ZhaidouRuntimeException("修改插入审核表异");
        }
    }
    
    /**
     * 获取 品牌列表
     * @param productBrandModel
     * @return
     */
    @Override
    public List<ProductBrandModel> getBrand(ProductBrandModel productBrandModel,Page page) {
        List<ProductBrandModel> listBrand = null;
        try {
            //获取品牌
            listBrand = productBrandService.getEnableProductBrandListPage(productBrandModel, page);
            
        } catch (Exception e) {
            logger.error("",e);
        }
        return listBrand;
    }
    

    /**
     * 获取 所有颜色尺码
     * @return
     */
    @Override
    public Map<String, Object> getColorAndSize() {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            List<ProductColorModel> listColor = productColorService.getAllColor();
            List<ProductSizeModel> listSize = productSizeService.getAllSize();
            map.put("listColor", listColor);
            map.put("listSize", listSize);
        } catch (Exception e) {
            logger.error("获取颜色尺码异常",e);
            throw new ZhaidouRuntimeException("获取颜色尺码异常");
        }
        return map;
    }

    @Override
    public Integer getProductCode(ProductModel productModel) throws DaoException {
        return productService.getProductCode(productModel);
    }

    @Override
    public ProductModel getProductByCode(String productCode) throws Exception {
        return productService.getProductByCode(productCode);
    }

    @Override
    public List<ProductSkuExportModel> exportPrdouct(ProductSkuExportModel productSkuExportModel, Page page) throws Exception {
        return productService.exportPrdouct(productSkuExportModel, page.getPageNum(), page.getNumPerPage());
    }

    @Override
    public Long exportPrdouctCount(ProductSkuExportModel productSkuExportModel) throws Exception {
        return productService.exportPrdouctCount(productSkuExportModel);
    }

	@Override
	public List<ProductModel> getProductListAndMarkUpRate(Page page,
			ProductModel productModel) throws Exception {
		List<ProductModel> productList = null;    
            productList = productService.getProductListAndMarkUpRate(productModel, page);
      return productList;
	}
}
