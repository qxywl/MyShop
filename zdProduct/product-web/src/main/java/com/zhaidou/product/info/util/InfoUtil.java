package com.zhaidou.product.info.util;

import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.info.config.StaticDefaultData;
import com.zhaidou.product.info.manager.ProductCategoryManager;
import com.zhaidou.product.info.model.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoUtil {
    
    public static final String ERROR_500JSP = "error/500";
    
    public static final String ERROR_403JSP = "error/403";
    
    public static Map<String,Object> map = new HashMap<String, Object>();
    
    public static String dateString = "yyyy-MM-dd HH:mm:ss";
    
    public static String dateLongToString(Long dateLong,String format){
        if(String.valueOf(dateLong).length()<=10){
            dateLong = dateLong*1000;
        }
        return DatetimeUtil.longToDateTimeString(dateLong, format);
    };
    
    public static String getStringToUtf(String str){
        if(str!=null){
            str = new String(str.getBytes(Charset.forName("ISO-8859-1")),Charset.forName("UTF-8"));
        }
        return str;
    }
    /**
     * 获取尺码  1 为上衣通用尺码，2 为男裤尺码，3 为女裤尺码，4 为女鞋尺码，5 为男鞋尺码，6 为童鞋尺码
     *
     * @param sizeKey
     * @return
     */
    public static List<String> getProductSize(String sizeKey){
        List<String> listSize = new ArrayList<String>();
        for(String key :StaticDefaultData.productSize.keySet()){
            if(sizeKey.equals(key)){
                String[] str = StaticDefaultData.productSize.get(key).split(",");
                for(int i=0;i<str.length;i++){
                    listSize.add(str[i]);
                }
            }
        }
        return listSize;
    }
    
    
    public static List<String> getProductColor(String colorKey){
        List<String> listColor = new ArrayList<String>();
        for(String key :StaticDefaultData.productColor.keySet()){
            if(colorKey.equals(key)){
                String[] str = StaticDefaultData.productColor.get(key).split(",");
                for(int i=0;i<str.length;i++){
                	listColor.add(str[i]);
                }
            }
        }
        return listColor;
    }
    
    /**
     * 生成编码
     *
     * @param codeHeader
     * @return
     */
    public static String newOrdercode(String codeHeader) {
        String[] code=new String[]{"A","B","C","D","E","F","G","H","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","1","2","3","4","5","6","7","8","9","0"};
        String rtn=codeHeader+code[DatetimeUtil.getYear()-2013]+code[DatetimeUtil.getMonth()]+code[DatetimeUtil.getDay()]+code[DatetimeUtil.getHour()]+code[DatetimeUtil.getHour()]+DatetimeUtil.getMinAndSec()+(int)(Math.random()*10000);
        return rtn;
    }
    
    /**
     * 对比 SKU 属性
     *
     * @param oldProduct
     */
    public static List<ProductPriceListModel> comparisonSku(ProductModel oldProduct,List<ProductSkuModel> listUpdate){
        
        List<ProductPriceListModel> priceList = new ArrayList<ProductPriceListModel>();
        ProductPriceListModel priceListModel = null;
        if(oldProduct.getProductSkuList()!=null && listUpdate!=null && 
                oldProduct.getProductSkuList().size()>0 && listUpdate.size()>0){
            for(ProductSkuModel oldSku:oldProduct.getProductSkuList()){
                for(ProductSkuModel newSku:listUpdate){
                    
                    //判断是不是同一个Sku
                    if(oldSku.getProductSkuId().longValue()==newSku.getProductSkuId().longValue()){
                       
                        //判断价格
                        if(oldSku.getProductPriceModel()!=null && newSku.getProductPriceModel()!=null){
                            ProductPriceModel oldPriceModel = oldSku.getProductPriceModel();
                            ProductPriceModel newPriceModel = newSku.getProductPriceModel();
                            
                            boolean skuFlag = false;
                            //供货价
                            if(oldPriceModel.getCostPrice().doubleValue()!=newPriceModel.getCostPrice().doubleValue()){
                                
                                skuFlag = true;
                            }
                            //市场价
                            if(oldPriceModel.getMarketPrice().doubleValue()!=newPriceModel.getMarketPrice().doubleValue()){
                                
                                skuFlag = true;
                            }
                            //销售价
                            if(oldPriceModel.getTshPrice().doubleValue()!=newPriceModel.getTshPrice().doubleValue()){
                                
                                skuFlag = true;
                            }
                            //换购特币
                            if(oldPriceModel.getTb().longValue()!=newPriceModel.getTb().longValue()){
                                
                                skuFlag = true;
                            }
                            if(skuFlag){
                                priceListModel = new ProductPriceListModel();
                                priceListModel.setCostPrice(newPriceModel.getCostPrice());
                                priceListModel.setOldCostPrice(oldPriceModel.getCostPrice());
                                priceListModel.setMarketPrice(newPriceModel.getMarketPrice());
                                priceListModel.setOldMarketPrice(oldPriceModel.getMarketPrice());
                                priceListModel.setTshPrice(newPriceModel.getTshPrice());
                                priceListModel.setOldTshPrice(oldPriceModel.getTshPrice());
                                priceListModel.setTb(newPriceModel.getTb());
                                priceListModel.setOldTb(oldPriceModel.getTb());
                                priceListModel.setProductSkuCode(oldSku.getProductSkuCode());
                                priceList.add(priceListModel);
                            }
                        }
                    }
                }
            }
        }
        return priceList;
    }
    
    /**
     * 商品 比较，判断哪些字段有修改
     *
     * @param oldProduct
     * @param newProduct
     * @return
     */
    public static boolean comparisonProduct(ProductModel oldProduct,ProductModel newProduct,ProductModel changeProduct){
         boolean productFlag = true;
        
        changeProduct.setProductId(oldProduct.getProductId());
        changeProduct.setProductCode(oldProduct.getProductCode());
        //名称
        if(!oldProduct.getProductName().equals(newProduct.getProductName())){
            changeProduct.setProductName(newProduct.getProductName());
            productFlag = false;
        }
        //简介
        if(oldProduct.getProductDesc()!=null){
            if(!oldProduct.getProductDesc().equals(newProduct.getProductDesc())){
                changeProduct.setProductDesc(newProduct.getProductDesc());
                productFlag = false;
            }
        }else if(!"".equals(newProduct.getProductDesc())){
            changeProduct.setProductDesc(newProduct.getProductDesc());
            productFlag = false;
        }
        
        //品牌
        if(oldProduct.getBrandCode()!=null){
            if(!oldProduct.getBrandCode().equals(newProduct.getBrandCode())){
                changeProduct.setBrandName(newProduct.getBrandName());
                productFlag = false;
            }
        }else{
            changeProduct.setBrandName(newProduct.getBrandName());
            productFlag = false;
        }

        boolean flag = true;
        //对比 productInfo 大字段信息
        if(oldProduct.getProductInfoModel()!=null && newProduct.getProductInfoModel()!=null){
            ProductInfoModel changeInfoModel = new ProductInfoModel();

            String newPcProductInfo = newProduct.getProductInfoModel().getPcProductInfo();
            if(newPcProductInfo !=null && !"".equals(newPcProductInfo)){
                String oldPcProductInfo = oldProduct.getProductInfoModel().getPcProductInfo();
                if(oldPcProductInfo == null || !newPcProductInfo.equals(oldPcProductInfo)){
                    changeInfoModel.setPcProductInfo(newPcProductInfo);
                    flag = false;
                }
            }
            if(newProduct.getProductInfoModel().getAppProductInfo()!=null  && !"".equals(newPcProductInfo)){
                if(!newProduct.getProductInfoModel().getAppProductInfo().equals(oldProduct.getProductInfoModel().getAppProductInfo())){
                    changeInfoModel.setAppProductInfo(newProduct.getProductInfoModel().getAppProductInfo());
                    flag = false;
                }
            }
            if(!flag){
                changeProduct.setProductInfoModel(changeInfoModel);
                productFlag = false;
            }
        }else{
            changeProduct.setProductInfoModel(newProduct.getProductInfoModel());
            productFlag = false;
        }
        
        //对比运营属性   对比商城扩展属性   对比弹性域属性  对比 SKU属性
        if(comparisonOperate(oldProduct,newProduct,changeProduct) && comparisonMall(oldProduct,newProduct,changeProduct) &&
                comparisonAttribute(oldProduct,newProduct,changeProduct) && comparisonSku(oldProduct,newProduct,changeProduct)){
            
        }else{
            productFlag = false;
        }
       
        return productFlag;
    }
    
    /**
     * 对比 SKU
     *
     * @param oldProduct
     * @param newProduct
     * @param changeProduct
     */
    public static boolean comparisonSku(ProductModel oldProduct,ProductModel newProduct,ProductModel changeProduct){
        boolean skuFlag = true;
        
        List<ProductSkuModel> skuList = new ArrayList<ProductSkuModel>();
        ProductSkuModel changeSku = null;
        if(oldProduct.getProductSkuList()!=null && newProduct.getProductSkuList()!=null && 
                oldProduct.getProductSkuList().size()>0 && newProduct.getProductSkuList().size()>0){
            for(ProductSkuModel oldSku:oldProduct.getProductSkuList()){
                for(ProductSkuModel newSku:newProduct.getProductSkuList()){
                    skuFlag = true;
                    //判断是不是同一个Sku
                    if(oldSku.getProductSkuId().longValue()==newSku.getProductSkuId().longValue()){
                        
                        changeSku = new ProductSkuModel();
                        changeSku.setProductId(newSku.getProductId());
                        changeSku.setProductSkuCode(newSku.getProductSkuCode());
                        changeSku.setProductSkuId(newSku.getProductSkuId());
                        
                        if(!oldSku.getAttrColorValue().equals(newSku.getAttrColorValue())){
                            changeSku.setAttrColorValue(newSku.getAttrColorValue());
                            skuFlag = false;
                        }
                        if(!oldSku.getAttrSpecValue().equals(newSku.getAttrSpecValue())){
                            changeSku.setAttrSpecValue(newSku.getAttrSpecValue());
                            skuFlag = false;
                        }
                        if(!oldSku.getColorValueAlias().equals(newSku.getColorValueAlias())){
                            changeSku.setColorValueAlias(newSku.getColorValueAlias());
                            skuFlag = false;
                        }
                        if(!oldSku.getSpecValueAlias().equals(newSku.getSpecValueAlias())){
                            changeSku.setSpecValueAlias(newSku.getSpecValueAlias());
                            skuFlag = false;
                        }
                        if(oldSku.getMainSku().longValue()!=newSku.getMainSku().longValue()){
                            changeSku.setMainSku(newSku.getMainSku());
                            skuFlag = false;
                        }
                        if(!comparisonValues(oldSku.getSupplierSkuCode(),newSku.getSupplierSkuCode())){
                            changeSku.setSupplierSkuCode(newSku.getSupplierSkuCode());
                            skuFlag = false;
                        }
                        if(!skuFlag){
                            skuList.add(changeSku);
                        }
                        break;
                    }
                }
            }
        }
        changeProduct.setProductSkuList(skuList);
        return skuFlag;
    }
    
    /**
     * 对比 运营属性
     *
     * @param oldProduct
     * @param newProduct
     * @param changeProduct
     */
    private static boolean comparisonOperate(ProductModel oldProduct,ProductModel newProduct,ProductModel changeProduct){
        boolean flag = true;
        
        ProductOperateModel productOperateMod = oldProduct.getProductOperateModel();
        ProductOperateModel productOperateModel = newProduct.getProductOperateModel();
        ProductOperateModel changeOperateModel = new ProductOperateModel();
        
        if(productOperateMod!=null && productOperateModel!=null){
            changeOperateModel.setProductOperateId(productOperateMod.getProductOperateId());
            changeOperateModel.setProductId(productOperateMod.getProductId());
            //短名称
            if(!comparisonValues(productOperateMod.getProductShortName(),productOperateModel.getProductShortName())){
                changeOperateModel.setProductShortName(productOperateModel.getProductShortName());
                flag = false;
            }
            //前缀
            if(!comparisonValues(productOperateMod.getProductPrefix(),productOperateModel.getProductPrefix())){
                changeOperateModel.setProductPrefix(productOperateModel.getProductPrefix());
                flag = false;
            }
            //后缀
            if(!comparisonValues(productOperateMod.getProductSuffix(),productOperateModel.getProductSuffix())){
                changeOperateModel.setProductSuffix(productOperateModel.getProductSuffix());
                flag = false;
            }
            //关键字
            if(!comparisonValues(productOperateMod.getProductKeyword(),productOperateModel.getProductKeyword())){
                changeOperateModel.setProductKeyword(productOperateModel.getProductKeyword());
                flag = false;
            }
            //是否下架显示
            if(productOperateMod.getProductDownShow()!=null){
                if(productOperateMod.getProductDownShow().longValue()!=productOperateModel.getProductDownShow().longValue()){
                    changeOperateModel.setProductDownShow(productOperateModel.getProductDownShow());
                    flag = false;
                }
            }else{
                flag = false;
            }
            
            //加价率
            if(!comparisonValues(productOperateMod.getProductPriceRate(),productOperateModel.getProductPriceRate())){
                changeOperateModel.setProductPriceRate(productOperateModel.getProductPriceRate());
                flag = false;
            }
            //视频路径
            if(!comparisonValues(productOperateMod.getProductVideoUrl(),productOperateModel.getProductVideoUrl())){
                changeOperateModel.setProductVideoUrl(productOperateModel.getProductVideoUrl());
                flag = false;
            }
            //缩略图路径
            if(!comparisonValues(productOperateMod.getProductThumbnail(),productOperateModel.getProductThumbnail())){
                changeOperateModel.setProductThumbnail(productOperateModel.getProductThumbnail());
                flag = false;
            }
            changeProduct.setProductOperateModel(changeOperateModel);
        }else{
            changeProduct.setProductOperateModel(productOperateModel);
            flag = false;
        }
        return flag;
    }
    
    /**
     * 对比 商城扩展属性
     *
     * @param oldProduct
     * @param newProduct
     * @param changeProduct
     */
    private static boolean comparisonMall(ProductModel oldProduct,ProductModel newProduct,ProductModel changeProduct){
        boolean flag = true;
        
        ProductMallModel productMallMod = oldProduct.getProductMallModel();
        ProductMallModel productMallModel = newProduct.getProductMallModel();
        ProductMallModel changeMallModel = new ProductMallModel();
        if(productMallMod!=null && productMallModel!=null){
            changeMallModel.setProductMallId(productMallMod.getProductMallId());
            changeMallModel.setProductId(productMallMod.getProductId());
            //货号
            if(!comparisonValues(productMallMod.getProductAtrNumber(),productMallModel.getProductAtrNumber())){
                changeMallModel.setProductAtrNumber(productMallModel.getProductAtrNumber());
                flag = false;
            }
            //商城扩展属性     包装长
            if(!comparisonValues(productMallMod.getProductLong(),productMallModel.getProductLong())){
                changeMallModel.setProductLong(productMallModel.getProductLong());
                flag = false;
            }
            //包装 宽
            if(!comparisonValues(productMallMod.getProductWidth(),productMallModel.getProductWidth())){
                changeMallModel.setProductWidth(productMallModel.getProductWidth());
                flag = false;
            }
            //包装 高
            if(!comparisonValues(productMallMod.getProductHeight(),productMallModel.getProductHeight())){
                changeMallModel.setProductHeight(productMallModel.getProductHeight());
                flag = false;
            }
            // 重量
            if(!comparisonValues(productMallMod.getProductWeight(),productMallModel.getProductWeight())){
                changeMallModel.setProductWeight(productMallModel.getProductWeight());
                flag = false;
            }
            // 密度
            if(!comparisonValues(productMallMod.getProductDensity(),productMallModel.getProductDensity())){
                changeMallModel.setProductDensity(productMallModel.getProductDensity());
                flag = false;
            }
            //产地
            if(!comparisonValues(productMallMod.getProductProducer(),productMallModel.getProductProducer())){
                changeMallModel.setProductProducer(productMallModel.getProductProducer());
                flag = false;
            }
            changeProduct.setProductMallModel(changeMallModel);
        }else{
            changeProduct.setProductMallModel(newProduct.getProductMallModel());
            flag = false;
        }
        return flag;
    }
    
    
    
    /**
     * 对比 弹性域 属性
     *
     * @param oldProduct
     * @param newProduct
     * @param changeProduct
     */
    private static boolean comparisonAttribute(ProductModel oldProduct,ProductModel newProduct,ProductModel changeProduct){
        boolean flag = true;
        List<ProductAttributeModel> changeAttrList = new ArrayList<ProductAttributeModel>();
        ProductAttributeModel changeAttrModel = null;
        if(oldProduct.getProductAttributeList()!=null && oldProduct.getProductAttributeList().size()>0 &&
                newProduct.getProductAttributeList() != null && newProduct.getProductAttributeList().size()>0){
            
            for(ProductAttributeModel oldAttrModel:oldProduct.getProductAttributeList()){
                for(ProductAttributeModel newAttrModel:newProduct.getProductAttributeList()){
                    if(newAttrModel.getProductAttributeId()!=null){
                      //判断是否同一个属性项
                        if(oldAttrModel.getProductAttributeId().longValue()==newAttrModel.getProductAttributeId().longValue()){
                            
                            changeAttrModel = new ProductAttributeModel();
                            changeAttrModel.setProductId(oldAttrModel.getProductId());
                            if(!oldAttrModel.getProductAttributeValue().equals(newAttrModel.getProductAttributeValue())){
                                changeAttrModel.setProductAttributeValue(newAttrModel.getProductAttributeValue());
                                changeAttrModel.setProductAttributeId(oldAttrModel.getProductAttributeId());
                                changeAttrList.add(changeAttrModel);
                                flag = false;
                            }
                            break;
                        }
                    }else{
                        flag = false;
                    }
                }
            }
            changeProduct.setProductAttributeList(changeAttrList);
        }else{
            if(newProduct.getProductAttributeList() != null && newProduct.getProductAttributeList().size()>0){
                changeProduct.setProductAttributeList(newProduct.getProductAttributeList());
                flag = false;
            }
        }
        return flag;
    }
    
    /**
     * 比较两个值 是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    private static boolean comparisonValues(Object obj1,Object obj2){
        boolean valueFlag = true;
        boolean flag = true;
        
        if(obj1!=null && obj2!=null){
            double d1 = 0d;
            double d2 = 0d;
            try {
                Double.parseDouble(String.valueOf(obj1));
                Double.parseDouble(String.valueOf(obj2));
            } catch (NumberFormatException e) {
                flag = false;
            }
            if(flag){
                if(d1!=d2){
                    valueFlag = false;
                }
            }else{
                String s1 = (String)obj1;
                String s2 = (String)obj2;
                if(!s1.equals(s2)){
                    valueFlag = false;
                }
            }
        }else{
            valueFlag = true;
        }
        return valueFlag;
    }
    
    /**
     * 根据三级类目 过去 一，二急类目
     *
     * @param productModel
     * @param productCategoryManager
     */
    public static void getCategoryByCatCode(ProductModel productModel,ProductCategoryManager productCategoryManager){
        String catCode3 = productModel.getCatCode();
        if(catCode3.length()==6){
            
            String catCode1 = catCode3.substring(0, 2);
            CategoryPO categoryPo1 = new CategoryPO();
            categoryPo1.setCategoryCode(catCode1);
            categoryPo1 = productCategoryManager.getCategory(categoryPo1);
            productModel.setCatName1(categoryPo1.getCategoryName());
            
            CategoryPO categoryPo2 = new CategoryPO();
            String catCode2 = catCode3.substring(0, 4);
            categoryPo2.setCategoryCode(catCode2);
            categoryPo2 = productCategoryManager.getCategory(categoryPo2);
            productModel.setCatName2(categoryPo2.getCategoryName());
            
        }
    }
}


