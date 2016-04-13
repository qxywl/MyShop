package com.zhaidou.product.info.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.product.attributies.service.ProductAttrGroupService;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.info.manager.ProductAttrbuteManager;
import com.zhaidou.product.info.manager.ProductInfoAuthManager;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.model.ProductInfoAuthModel;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductSkuModel;
import com.zhaidou.product.info.service.ProductAttributeService;
import com.zhaidou.product.info.service.ProductInfoAuthService;
import com.zhaidou.product.info.service.ProductInfoService;
import com.zhaidou.product.info.service.ProductOperateService;
import com.zhaidou.product.info.service.ProductService;
import com.zhaidou.product.info.service.SupplierService;
import com.zhaidou.product.info.util.FileUtil;
import com.zhaidou.product.info.util.InfoUtil;

@Service("productInfoAuthManager")
public class ProductInfoAuthManagerImpl implements ProductInfoAuthManager {
    private static final Log logger = LogFactory.getLog(ProductInfoAuthManagerImpl.class);
    @Resource
    private ProductInfoAuthService productInfoAuthService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductOperateService productOperateService;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ProductAttributeService productAttributeService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductAttrGroupService productAttrGroupService;
    @Resource
    private ProductAttrbuteManager productAttrbuteManager;
    @Resource
    private ProductManager productManager;
    @Resource
    private SupplierService supplierService;
    /**
     * 获取商品 审核列表
     *
     * @param page
     * @return
     */
    @Override
    public List<ProductInfoAuthModel> getProductInfoAuthList(Page page,ProductInfoAuthModel productInfoAuthModel) {
        
        List<ProductInfoAuthModel> listProductInfoAuth = null;
        try {
            listProductInfoAuth = productInfoAuthService.getProductInfoAuth(page,productInfoAuthModel);
            if(listProductInfoAuth!=null && listProductInfoAuth.size()>0){
                for(ProductInfoAuthModel productInfoAuthMod:listProductInfoAuth){
                    if(productInfoAuthMod.getCreateTime()!=null){
                        productInfoAuthMod.setCreateTimes(InfoUtil.dateLongToString(productInfoAuthMod.getCreateTime(), InfoUtil.dateString));
                    }
                    if(productInfoAuthMod.getUpdateTime()!=null){
                        productInfoAuthMod.setUpdateTimes(InfoUtil.dateLongToString(productInfoAuthMod.getUpdateTime(), InfoUtil.dateString));
                    }
                }
            }
                return listProductInfoAuth;
            
        } catch (Exception e) {
            logger.error("获取商品审核列表错误",e);
           throw new ZhaidouRuntimeException("获取商品审核列表错误,请稍后在试！");
        }
    }
    
    /**
     * 修改商品审核对象
     *
     * @param page
     * @return
     */
    @Override
    public boolean updateProductInfo(ProductInfoAuthModel productInfoAuthModel) {
        boolean flag = true;
        try {
            productInfoAuthService.updateProductInfoAuth(productInfoAuthModel);
        } catch (Exception e) {
            logger.error("修改商品审核对象错误",e);
            flag = false;
            throw new ZhaidouRuntimeException("修改商品审核对象错误!");
        }
        return flag;
    }
    
    /**
     * 查看 商品审核 详情
     *
     * @param page
     * @return
     */
    @Override
    public Map<String,Object> getProductInfoAuth(ProductInfoAuthModel productInfoAuthModel) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            productInfoAuthModel = productInfoAuthService.getProductInfoAuthById(productInfoAuthModel);
            getProductInfo(productInfoAuthModel,map);
            
        } catch (Exception e) {
            logger.error("ProductInfoAuthManagerImpl==>> 获取商品审核详情异常",e);
            throw new ZhaidouRuntimeException("ProductInfoAuthManagerImpl==>> 获取商品审核详情异常");
        }
        return map;
    }
    
    @Override
    public void getProductInfoAuthTwo(ProductInfoAuthModel productInfoAuthModel)throws Exception {
        
        Map<String,Object> map = new HashMap<String, Object>();
        ProductModel productModel = null;
        if(productInfoAuthModel!=null){
            if(productInfoAuthModel.getType().longValue()==1){
                productInfoAuthModel.setTypeName("新增商品");
                if(productInfoAuthModel.getNewValue()!=null && !"".equals(productInfoAuthModel.getNewValue())){
                    productModel = JSONUtil.toModel(productInfoAuthModel.getNewValue(), ProductModel.class);
                }
            }else if(productInfoAuthModel.getType().longValue()==2){
                productInfoAuthModel.setTypeName("页面修改");
                if(productInfoAuthModel.getNewValue()==null || "".equals(productInfoAuthModel.getNewValue())){
                    map.put("type", "3");
                }else{
                    
                    Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(productInfoAuthModel.getNewValue(), String.class);
                    
                    productModel = (ProductModel)JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
                }
            }
        }
    }
    
    @Resource
    private ImageSearchManager imageSearchManager;
    
    private ProductModel getProductInfo(ProductInfoAuthModel productInfoAuthModel,Map<String,Object> map){
        if(productInfoAuthModel!=null){
            map.put("status", productInfoAuthModel.getStaus()+"");
            map.put("type", productInfoAuthModel.getType()+"");
            if(productInfoAuthModel.getType().longValue()==1){
                ProductModel productModel = JSONUtil.toModel(productInfoAuthModel.getNewValue(), ProductModel.class);
                if(productModel!=null){
                    if(productModel.getProductSkuList()!=null && productModel.getProductSkuList().size()>0){
                        for(ProductSkuModel sku : productModel.getProductSkuList()){
                            List<ProductImageModel> imageList =  FileUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                            sku.setProductImagerList(imageList);
                        }
                    }
                    map.put("productModel", productModel);
                }
//                else{
//                    logger.error("ProductInfoAuthManagerImpl==>> 转换 ProductModel 异常");
//                    throw new TeshehuiRuntimeException("ProductInfoAuthManagerImpl==>> 转换 ProductModel 异常");
//                }
                return productModel;
            }else if(productInfoAuthModel.getType().longValue()==2){
                if(productInfoAuthModel.getNewValue()==null || "".equals(productInfoAuthModel.getNewValue())){
                    map.put("type", "3");
                }else{
                    Map<String, String> mapValue = (Map<String, String>) JSONUtil.toMap(productInfoAuthModel.getNewValue(), String.class);
                    
                    ProductModel newProduct = (ProductModel)JSONUtil.toModel(mapValue.get("update"), ProductModel.class);
                    List<ProductSkuModel> insertListSku = (List<ProductSkuModel>)JSONUtil.toCollection(mapValue.get("insert"), ProductSkuModel.class);
                    //新增SKU 调用获取图片 hessian 接口
                    if(insertListSku!=null && insertListSku.size()>0){
                        for(ProductSkuModel sku : insertListSku){
                            List<ProductImageModel> imageList =  FileUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                            sku.setProductImagerList(imageList);
                        }
                    }
                    //修改SKU 调用获取图片 hessian 接口
                    if(newProduct.getProductSkuList()!=null && newProduct.getProductSkuList().size()>0){
                        for(ProductSkuModel sku : newProduct.getProductSkuList()){
                            List<ProductImageModel> imageList =  FileUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                            sku.setProductImagerList(imageList);
                        }
                    }
                    
                    ProductModel oldProduct = (ProductModel)JSONUtil.toModel(productInfoAuthModel.getOldValue(), ProductModel.class);
                    if(oldProduct==null){
                        Map<String, Object> mapInfo = productManager.getProductInfo(newProduct);
                        oldProduct = (ProductModel)mapInfo.get("productModel");
                    }
                    //修改SKU 调用获取图片 hessian 接口
                    if(oldProduct.getProductSkuList()!=null && oldProduct.getProductSkuList().size()>0){
                        for(ProductSkuModel sku : oldProduct.getProductSkuList()){
                            List<ProductImageModel> imageList =  FileUtil.imageList(sku.getProductSkuCode(),imageSearchManager);
                            sku.setProductImagerList(imageList);
                        }
                    }
                    map.put("oldProduct", oldProduct);
                    map.put("newProduct", newProduct);
                    map.put("insertListSku", insertListSku);
                    return newProduct;
                }
            }
        }
        return null;
    }

    @Override
    public ProductInfoAuthModel getProductInfoAuthById(ProductInfoAuthModel productInfoAuthModel) throws Exception {
        // TODO Auto-generated method stub
        return productInfoAuthService.getProductInfoAuthById(productInfoAuthModel);
    }
}
