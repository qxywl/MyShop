package com.zhaidou.product.info.controller;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.BarCodeUtils;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.info.config.StaticDefaultData;
import com.zhaidou.product.info.manager.*;
import com.zhaidou.product.info.manager.impl.UploadImageManagerImpl;
import com.zhaidou.product.info.model.*;
import com.zhaidou.product.info.service.SupplierService;
import com.zhaidou.product.info.util.ExcelUtil;
import com.zhaidou.product.info.util.FileUtil;
import com.zhaidou.product.info.util.InfoUtil;
import com.zhaidou.product.info.util.UploadExcelUtil;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping(value = "/info/product/")
public class ProductInfoController {


    private static final Log       logger        = LogFactory.getLog(ProductInfoController.class);

    public static String           PRODUCTLIST   = "info/product/product_list";
    public static String           EXAMINELIST   = "info/product/product_examine";
    public static String           PRODUCTADD    = "info/product/add_product";
    public static String           PRODCUTUPDATE = "info/product/update_product";
    public static String           INFOPRODUCT   = "info/product/info_product";
    public static String           INFOEXAMINE   = "info/product/info_examine";
    public static String           PRODCUTLOOK   = "info/product/view_product";

    @Resource
    private ProductManager         productManager;
    @Resource
    private ProductCategoryManager productCategoryManager;
    @Resource
    private ProductInfoAuthManager productInfoAuthManager;
    @Resource
    private ProductExcelManager    productExcelManager;
    @Resource
    private ProductSkuManager productSkuManager;
    @Resource
    private SupplierService   supplierService;

    @Resource
    private ProductShelvesManager productShelvesManager;
    
    @Value("#{propertyConfigurerForProject2['img_tmp_dir']}")
	private String imgTmpDir;

    @Value("#{propertyConfigurerForProject2['export_num']}")
    private Integer exportNum;

    /**
     * 获取 登录用户的信息
     */
    private Map<String, Object> getUserMap(HttpServletRequest request) {
        Map<String, Object> userMap = (Map<String, Object>) request.getSession().getAttribute("user");
//        if (userMap == null)// 测试需要
//        {
//            userMap = new HashMap<String, Object>();
//            userMap.put("userId", "-1");
//            userMap.put("userName", "userName");
//
//        }
         return userMap;
    }

    // 商品列表集合 获取 前端参数
    private void getProductListParameter(ProductModel productModel, HttpServletRequest req,Page page) {
        
        String categoryCode1 = "";
        String categoryCode2 = "";
        String categoryCode3 = "";
        String startPrice = "";
        String endPrice = "";
        String startTime = "";
        String endTime = "";
        String startUpTime = "";
        String endUpTime = "";
        String startDownTime = "";
        String endDownTime = "";
        if(req.getParameter("kk")!=null && req.getParameter("kk").equals("1")){
            categoryCode1 = (String)req.getSession().getAttribute("categoryCode1");
            categoryCode2 = (String)req.getSession().getAttribute("categoryCode2");
            categoryCode3 = (String)req.getSession().getAttribute("categoryCode3");
            startPrice = (String)req.getSession().getAttribute("startPrice");
            endPrice = (String)req.getSession().getAttribute("endPrice");
            
            String brandName = (String)req.getSession().getAttribute("brandName");
            String type = (String)req.getSession().getAttribute("type");
            String createTimes = (String)req.getSession().getAttribute("createTimes");
            String  productShelves = (String)req.getSession().getAttribute("productShelves");
            String productCode = (String)req.getSession().getAttribute("productCode");
            String productName = (String)req.getSession().getAttribute("productName");
            String supplierName = (String)req.getSession().getAttribute("supplierName");

            Page page1 = (Page)req.getSession().getAttribute("page");
            if(null!=page1){
            page.setTotalCount(page1.getTotalCount());
            page.setPageNum(page1.getPageNum());
            }
            productModel.setBrandName(brandName);
            productModel.setType(type);
            productModel.setCreateTimes(createTimes);
            productModel.setProductCode(productCode);
            productModel.setProductName(productName);
            productModel.setSupplierName(supplierName);
            if(productShelves!=null && !"".equals(productShelves)){
                productModel.setProductShelves(Long.parseLong(productShelves));
            }
        }else{
         // 按基础类型查询
            categoryCode1 = req.getParameter("categoryName1");
            categoryCode2 = req.getParameter("categoryName2");
            categoryCode3 = req.getParameter("categoryName3");
            startPrice = req.getParameter("startPrice");
            endPrice = req.getParameter("endPrice");
            startTime = req.getParameter("createStartTime");
            endTime = req.getParameter("endTime1");
            startUpTime = req.getParameter("startUpTime");
            endUpTime = req.getParameter("endUpTime");
            startDownTime = req.getParameter("startDownTime");
            endDownTime = req.getParameter("endDownTime");
        }

        if (startPrice != null && !"".equals(startPrice.trim())) {
            productModel.setCostPrice(Double.parseDouble(startPrice));
        }
        if (endPrice != null && !"".equals(endPrice.trim())) {
            productModel.setTshPrice(Double.parseDouble(endPrice));
        }
        if(startTime != null && !"".equals(startTime.trim())){
            productModel.setCreateTime(DatetimeUtil.stringToDate(startTime + " 00:00:01").getTime() / 1000);
        }
        if(endTime != null && !"".equals(endTime.trim())){
            productModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime + " 23:59:59").getTime() / 1000));
        }
        
        List<ProductModel> productList = productManager.getProductList(page, productModel);
        if(productList!=null && productList.size()>0){
            for(int i=0;i<productList.size();i++){
                if(productList.get(i).getLastShelvesTime()!=null){
                     if(startUpTime != null && !"".equals(startUpTime.trim())){
                            productModel.setLastStartUpTime(Long.toString(DatetimeUtil.stringToDate(startUpTime + " 00:00:01").getTime() / 1000));
                        }
                     if(endUpTime != null && !"".equals(endUpTime.trim())){
                            productModel.setLastEndUpTime(Long.toString(DatetimeUtil.stringToDate(endUpTime + " 23:59:59").getTime() / 1000));
                        }
                }
                else{
                     if(startUpTime != null && !"".equals(startUpTime.trim())){
                            productModel.setFirstStartUpTime(Long.toString(DatetimeUtil.stringToDate(startUpTime + " 00:00:01").getTime() / 1000));
                        }
                     if(endUpTime != null && !"".equals(endUpTime.trim())){
                            productModel.setFirstEndUpTime(Long.toString(DatetimeUtil.stringToDate(endUpTime + " 23:59:59").getTime() / 1000));
                        }
                }
                
            }
        }
        
        if(startDownTime != null && !"".equals(startDownTime.trim())){
            productModel.setStartDownTime(Long.toString(DatetimeUtil.stringToDate(startDownTime + " 00:00:01").getTime() / 1000));
        }
        if(endDownTime != null && !"".equals(endDownTime.trim())){
            productModel.setEndDownTime(Long.toString(DatetimeUtil.stringToDate(endDownTime + " 23:59:59").getTime() / 1000));
        }
        String catCode = "";
        if (categoryCode3 == null || "".equals(categoryCode3) || "选择分类".equals(categoryCode3)) {
            if (categoryCode2 == null || "".equals(categoryCode2) || "选择分类".equals(categoryCode2)) {
                catCode = categoryCode1;
            } else {
                catCode = categoryCode2;
            }
        } else {
            catCode = categoryCode3;
        }
        if (catCode != null && !"".equals(catCode)) {
            productModel.setCatCode(catCode);
        }
        // 按时间查询 设置起始时间
        
        req.setAttribute("categoryName1", categoryCode1);
        req.setAttribute("categoryName2", categoryCode2);
        req.setAttribute("categoryName3", categoryCode3);
        
        req.setAttribute("categoryCode1", categoryCode1);
        req.setAttribute("categoryCode2", categoryCode2);
        req.setAttribute("categoryCode3", categoryCode3);
        req.setAttribute("startPrice", startPrice);
        req.setAttribute("endPrice", endPrice);
        req.setAttribute("createStartTime", startTime);
        req.setAttribute("endTime1", endTime);
        
        req.setAttribute("startUpTime", startUpTime);
        req.setAttribute("endUpTime", endUpTime);
        req.setAttribute("startDownTime", startDownTime);
        req.setAttribute("endDownTime", endDownTime);
        
        req.getSession().setAttribute("categoryCode1", categoryCode1);
        req.getSession().setAttribute("categoryCode2", categoryCode2);
        req.getSession().setAttribute("categoryCode3", categoryCode3);
        req.getSession().setAttribute("startPrice", startPrice);
        req.getSession().setAttribute("endPrice", endPrice);
        req.getSession().setAttribute("brandName", productModel.getBrandName());
        req.getSession().setAttribute("type", productModel.getType());
        req.getSession().setAttribute("createTimes", productModel.getCreateTimes());
        req.getSession().setAttribute("startTime", startTime);
        req.getSession().setAttribute("endTime1", endTime);

        req.getSession().setAttribute("startUpTime", startUpTime);
        req.getSession().setAttribute("endUpTime", endUpTime);
        req.getSession().setAttribute("startDownTime", startDownTime);
        req.getSession().setAttribute("endDownTime", endDownTime);
        req.getSession().setAttribute("supplierName", productModel.getSupplierName());

        if(productModel.getProductShelves()!=null){
            req.getSession().setAttribute("productShelves",String.valueOf( productModel.getProductShelves()));
        }
        req.getSession().setAttribute("productCode", productModel.getProductCode());
        req.getSession().setAttribute("productName", productModel.getProductName());
        req.getSession().setAttribute("page", page);
    }

    /**
     * 转至商品列表页面
     * 
     * @param page
     * @param productModel
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String productList(Page page, ProductModel productModel, HttpServletRequest req) {
        try {
            getProductListParameter(productModel, req, page);

            List<ProductModel> productList = productManager.getProductList(page, productModel);
            req.setAttribute("productList", productList);
            // 获取目录
            List<CategoryPO> listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
            req.setAttribute("listCategory", listCategory);

            return PRODUCTLIST;
        } catch (Exception e) {
            logger.error("list product error :", e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
    }

    /**
     * 转至商品审核页面
     * 
     * @param page
     * @param productInfoAuthModel
     * @param req
     * @return
     */
    @RequestMapping(value = "/examine_list", method = { RequestMethod.GET, RequestMethod.POST })
    public String productExamineList(Page page, ProductInfoAuthModel productInfoAuthModel, HttpServletRequest req) {

        // 按时间查询 设置起始时间
        
    	String startTime = req.getParameter("infoAuthStartTime");
        String endTime =req.getParameter("infoAuthEndTime");
        String startTime2 =req.getParameter("startTime2");
        String endTime2 =req.getParameter("endTime2");
        if(startTime!=null && !"".equals(startTime)){
            productInfoAuthModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("infoAuthStartTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
            productInfoAuthModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("infoAuthEndTime", endTime);
        }
        if(startTime2!=null && !"".equals(startTime2)){
        	productInfoAuthModel.setUpdateTime(DatetimeUtil.stringToDate(startTime2+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime2", startTime2);
        }
        if(endTime2!=null && !"".equals(endTime2)){
        	productInfoAuthModel.setEndTime2(Long.toString(DatetimeUtil.stringToDate(endTime2+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime2", endTime2);
        }

        List<ProductInfoAuthModel> listInfoAuth = null;
        List<ProductInfoAuthModel> listInfoAuth1 = new ArrayList<ProductInfoAuthModel>();
        try {
            listInfoAuth = productInfoAuthManager.getProductInfoAuthList(page,productInfoAuthModel);
            if(listInfoAuth!=null){
                for(ProductInfoAuthModel productInfoAuth:listInfoAuth){
                    productInfoAuthManager.getProductInfoAuthTwo(productInfoAuth);
                    
                    listInfoAuth1.add(productInfoAuth);
                }
            }
            
        } catch (Exception e) {
            logger.error("转至商品审核页面 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("listInfoAuth", listInfoAuth);
        return EXAMINELIST;
    }

    /**
     * 转至商品审核详情页面
     * 
     * @param id
     * @param req
     * @param pageType
     * @return
     */
    @RequestMapping(value = "/info_examine/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public String toProductExamineInfo(@PathVariable Long id, HttpServletRequest req,String pageType) {
        ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
        productInfoAuthModel.setProductInfoAuthId(id);
        
        Map<String, Object> map = null;
        List<ProductSkuModel> skuList = null;
        try {
            map = productInfoAuthManager.getProductInfoAuth(productInfoAuthModel);

                ProductModel productModel = (ProductModel) map.get("productModel");
   
                if (productModel != null) {
                    List<ProductCateAttrGroupModel> listAttr = productAttrbuteManager.getAttrByCategory(productModel.getCatCode());
                    List<ProductAttributeModel> listAttr2 = productModel.getProductAttributeList();
   
                    req.setAttribute("listAttr", JSONUtil.toString(listAttr));
                    if (listAttr2 != null && listAttr2.size() > 0) {
                        req.setAttribute("listAttr2", JSONUtil.toString(listAttr2));
                    } else {
                        req.setAttribute("listAttr2", " ");
                    }
                    InfoUtil.getCategoryByCatCode(productModel, productCategoryManager);
                    
                    req.setAttribute("listSkuJson", JSONUtil.toString(productModel.getProductSkuList()));
                    req.setAttribute("productModel", productModel);
                }
            skuList = (List<ProductSkuModel>) map.get("insertListSku");
            if(skuList!=null){
                for(ProductSkuModel sku:skuList){
                    req.setAttribute("colorName", sku.getAttrColorName());
                    req.setAttribute("specName", sku.getAttrSpecName());
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("转至商品审核详情页面 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("infoAuthId", id);
        req.setAttribute("oldProduct", (ProductModel) map.get("oldProduct"));
        req.setAttribute("newProduct", (ProductModel) map.get("newProduct"));
        req.setAttribute("insertListSku", skuList);
        req.setAttribute("status", (String) map.get("status"));

        String type = (String) map.get("type");
        
        if(pageType!=null && pageType.equals("1")){
            if(type.equals("2")){
                ProductModel productModel = (ProductModel) map.get("newProduct");
                if(productModel!=null){
                    if(productModel.getProductSkuList()!=null && productModel.getProductSkuList().size()>0){
                        for(ProductSkuModel skuModel :skuList){
                            
                            productModel.getProductSkuList().add(skuModel);
                        }
                    }else{
                        productModel.setProductSkuList(skuList);
                    }
                }
                for(ProductSkuModel sku:productModel.getProductSkuList()){
                    req.setAttribute("colorName", sku.getAttrColorName());
                    req.setAttribute("specName", sku.getAttrSpecName());
                    break;
                }
                
                InfoUtil.getCategoryByCatCode(productModel, productCategoryManager);
                
                List<ProductCateAttrGroupModel> listAttr = productAttrbuteManager.getAttrByCategory(productModel.getCatCode());
                List<ProductAttributeModel> listAttr2 = productModel.getProductAttributeList();

                req.setAttribute("listAttr", JSONUtil.toString(listAttr));
                if (listAttr2 != null && listAttr2.size() > 0) {
                    req.setAttribute("listAttr2", JSONUtil.toString(listAttr2));
                } else {
                    req.setAttribute("listAttr2", " ");
                }
                req.setAttribute("listSkuJson", JSONUtil.toString(productModel.getProductSkuList()));
                req.setAttribute("productModel", productModel);
            }
            return "info/product/examine/add_examine_info";
        }
        if ("1".equals(type)) {
            return "info/product/examine/add_examine_info";
        } else if ("2".equals(type)) {
            return "info/product/examine/update_examine_info";
        } else {
            return INFOEXAMINE;
        }

    }

    /**
     * 转至添加 选择分类页面
     * 
     * @return
     */
    @RequestMapping(value = "/to_add_category", method = { RequestMethod.GET })
    public String toAddCategory(HttpServletRequest req) {

        List<CategoryPO> listCategory = (List<CategoryPO>) productCategoryManager.getCategoBaseryList(new CategoryPO());

        req.setAttribute("listCategory", listCategory);

        return "info/product/add_category_product";
    }

    /**
     * 转至添加页面
     * 
     * @return
     */
    @RequestMapping(value = "/to_add_product", method = { RequestMethod.GET })
    public String toAddProduct(HttpServletRequest req, ProductModel productModel, String catNameGroup) {
        try {
            // 获取运营分类
            CategoryPO categoryPo = new CategoryPO();
            categoryPo.setCategoryCode(productModel.getCatCode());
            categoryPo = productCategoryManager.getCategory(categoryPo);
            
            String sixCatName = "";
            if (categoryPo != null) {
                req.setAttribute("attrColorName", categoryPo.getColorName());
                req.setAttribute("attrSpecName", categoryPo.getSizeName());
                sixCatName = categoryPo.getCategoryName();
            }
            catNameGroup = "您选择目录是:";
            if(productModel.getCatCode().length()!=6){
                return "error/category_error";
            }else{
                String prefix = productModel.getCatCode().substring(0, 2);
                categoryPo = new CategoryPO();
                categoryPo.setCategoryCode(prefix);
                categoryPo = productCategoryManager.getCategory(categoryPo);
                catNameGroup = catNameGroup + categoryPo.getCategoryName()+">";
                
                categoryPo = new CategoryPO();
                prefix = productModel.getCatCode().substring(0, 4);
                categoryPo.setCategoryCode(prefix);
                categoryPo = productCategoryManager.getCategory(categoryPo);
                catNameGroup = catNameGroup + categoryPo.getCategoryName()+">"+sixCatName;
            }
            
            List<ProductCateAttrGroupModel> listAttr = productAttrbuteManager.getAttrByCategory(productModel.getCatCode());
            req.setAttribute("catCode", productModel.getCatCode());
            req.setAttribute("catName", sixCatName);
            req.setAttribute("listAttr", JSONUtil.toString(listAttr));
            req.setAttribute("catNameGroup", catNameGroup);

            ProductModel productMod = new ProductModel();
            productManager.getProductCode(productMod);
            String prefix = "";
            if (productModel.getCatCode().length() == 2) {
                prefix = "00" + productModel.getCatCode();
            } else {
                prefix = productModel.getCatCode().substring(0, 4);
            }
            String productCode = BarCodeUtils.buildBarCode(prefix, String.valueOf(productMod.getProductId()));

            req.setAttribute("productCode", productCode);
            List<SupplierModel> shopList= supplierService.getSupplier(null, null);
            req.setAttribute("shopList", shopList);
            
        } catch (Exception e) {
            logger.error("转至添加页面 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }

        req.setAttribute("color1", InfoUtil.getProductColor("1"));
        req.setAttribute("color2", InfoUtil.getProductColor("2"));
        req.setAttribute("color3", InfoUtil.getProductColor("3"));
        req.setAttribute("color4", InfoUtil.getProductColor("4"));
        req.setAttribute("color5", InfoUtil.getProductColor("5"));

        req.setAttribute("size1", InfoUtil.getProductSize("1"));
        req.setAttribute("size2", InfoUtil.getProductSize("2"));
        req.setAttribute("size3", InfoUtil.getProductSize("3"));
        req.setAttribute("size4", InfoUtil.getProductSize("4"));
        req.setAttribute("size5", InfoUtil.getProductSize("5"));

        req.setAttribute("supplierId", "1");
        req.setAttribute("shopId", "1");
        return PRODUCTADD;
    }

    @Resource
    private UploadImageManager uploadImageManager;

    /**
     * 商品添加操作 =
     * 
     * @return
     */
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String addProduct(@RequestParam MultipartFile[] thumbnailFile,String thumbnailFileName, String[] fileNames, ProductModel productModel,
                             HttpServletRequest req) {
        Map<String, Object> map = getUserMap(req);
        if (map != null) {
//            if (file != null && file.length > 0) {
//                uploadImageManager.uploadImage(file,fileNames, req, productModel.getProductCode(),1);
//            }
            if (thumbnailFile != null && thumbnailFile.length > 0) {
                String[] thumbnailFileNames = null;
                if (thumbnailFileName != null && !"".equals(thumbnailFileName)) {
                    thumbnailFileNames = new String[] { thumbnailFileName };
                }
                uploadImageManager.uploadImage(thumbnailFile,thumbnailFileNames, req, productModel.getProductCode(),2);
                if(thumbnailFileNames!=null && !"".equals(thumbnailFileName)){
                  thumbnailFileName = thumbnailFileNames[0];
                }
            }

            if (productModel.getProductDesc() != null && productModel.getProductDesc().length() > 1000) {
                return AjaxObject.newError("豆豆点评不能超过999个字符串").toString();
            }

            // sku 颜色 尺码 名称
            String skuColorName = req.getParameter("attrColorName");
            String skuSpecName = req.getParameter("attrSpecName");

            String brandName = req.getParameter("productModel.brandName");
            String brandCode = req.getParameter("productModel.brandCode");
            productModel.setBrandCode(brandCode);
            productModel.setBrandName(brandName);
            // sku 属性值
            String[] attrColorValues = req.getParameterValues("skuColorValue");
            String[] attrSpecValues = req.getParameterValues("skuSpecValue");
            String[] skuMainSkus = req.getParameterValues("skuMainSku");
            String[] skuColorValueAlias = req.getParameterValues("skuColorValueAlias");
            String[] skuSpecValueAlias = req.getParameterValues("skuSpecValueAlias");
            String[] skuSupplierSkuCode = req.getParameterValues("skuSupplierSkuCode");
            // 价格
//            String[] skuCostPrices = req.getParameterValues("skuCostPrice");
            String[] skuMarketPrices = req.getParameterValues("skuMarketPrice");
            String[] skuTshPrices = req.getParameterValues("skuTshPrice");
//            String[] skuTb = req.getParameterValues("skuTb");
            // 库存
            String[] skuVirtualStock = req.getParameterValues("skuVirtualStock");

            // 弹性域值
            String[] attrbuteNames = req.getParameterValues("attrbuteName");
            String[] attrbuteCodes = req.getParameterValues("attrbuteCode");
            String[] attrbuteTypes = req.getParameterValues("attrbuteType");
            String[] attrbuteRequired = req.getParameterValues("attrbuteRequired");

            List<ProductSkuModel> listSku = null;
            // 弹性域
            List<ProductAttributeModel> listAttribute = null;
            try {
                listSku = new ArrayList<ProductSkuModel>();
                ProductSkuModel productSkuModel = null;
                if (attrColorValues != null && attrColorValues.length > 0) {
                    for (int i = 0; i < attrColorValues.length; i++) {
                        productSkuModel = new ProductSkuModel();
                        // 颜色 名称
                        productSkuModel.setAttrColorName(skuColorName);
                        // 尺码 名称
                        productSkuModel.setAttrSpecName(skuSpecName);
                        // 颜色值
                        productSkuModel.setAttrColorValue(attrColorValues[i]);
                        // 尺码 值
                        if (attrSpecValues != null && attrSpecValues.length > 0) {
                            productSkuModel.setAttrSpecValue(attrSpecValues[i]);
                        }
                        if (skuMainSkus != null && skuMainSkus.length > 0) {
                            productSkuModel.setMainSku(Long.parseLong(skuMainSkus[i]));
                        }
                        if (skuColorValueAlias != null && skuColorValueAlias.length > 0) {
                            productSkuModel.setColorValueAlias(skuColorValueAlias[i]);
                        }
                        if (skuSpecValueAlias != null && skuSpecValueAlias.length > 0) {
                            productSkuModel.setSpecValueAlias(skuSpecValueAlias[i]);
                        }
                        if (skuSupplierSkuCode != null && skuSupplierSkuCode.length > 0) {
                            if(!"".equals(skuSupplierSkuCode[i])){
                                productSkuModel.setSupplierSkuCode(skuSupplierSkuCode[i]);
                            }
                        }

                        // 价格
                        ProductPriceModel productPriceModel = new ProductPriceModel();
                        productPriceModel.setCostPrice(0.0);
                        productPriceModel.setTb(0l);
                        if (skuMarketPrices != null && skuMarketPrices.length > 0) {
                            productPriceModel.setMarketPrice(Double.parseDouble(skuMarketPrices[i]));
                        }
                        if (skuTshPrices != null && skuTshPrices.length > 0) {
                            productPriceModel.setTshPrice(Double.parseDouble(skuTshPrices[i]));
                        }
                        // 库存
                        ProductStockModel productStockModel = new ProductStockModel();
                        if (skuVirtualStock != null && skuVirtualStock.length > 0) {
//                            productStockModel.setVirtualStock(Double.parseDouble(skuVirtualStock[i]));
                            productStockModel.setEntityStock(Double.parseDouble(skuVirtualStock[i]));
                        }

                        productSkuModel.setProductStockModel(productStockModel);
                        productSkuModel.setProductPriceModel(productPriceModel);
                        listSku.add(productSkuModel);
                    }
                } else {
                    return AjaxObject.newError("请添加SKU").toString();
                }

                listAttribute = new ArrayList<ProductAttributeModel>();
                ProductAttributeModel productAttributeModle = new ProductAttributeModel();
                if (attrbuteNames != null && attrbuteNames.length > 0) {
                    for (int j = 0; j < attrbuteNames.length; j++) {
                        productAttributeModle = new ProductAttributeModel();
                        productAttributeModle.setProductAttributeName(attrbuteNames[j]);

                        if (attrbuteCodes != null && attrbuteCodes.length > 0) {
                            productAttributeModle.setProductAttributeCode(attrbuteCodes[j]);
                        }
                        if (attrbuteTypes != null && attrbuteTypes.length > 0) {
                            productAttributeModle.setType(attrbuteTypes[j]);
                        }
                        if (attrbuteRequired != null && attrbuteRequired.length > 0) {
                            productAttributeModle.setIsRequired(Integer.parseInt(attrbuteRequired[j]));
                        }
                        String[] attrbuteValues = req.getParameterValues("attrbuteValue" + j);
                        String value = "";
                        if (attrbuteValues != null && attrbuteValues.length > 0) {
                            value = attrbuteValues[0];
                            for (int i = 1; i < attrbuteValues.length; i++) {
                                value = value + "," + attrbuteValues[i];
                            }
                        }
                        productAttributeModle.setProductAttributeValue(value);

                        listAttribute.add(productAttributeModle);
                    }
                }
            } catch (Exception e) {
                logger.error("",e);
                return AjaxObject.newError("系统错误,请稍等！").toString();
            }
            productModel.setProductSkuList(listSku);
            productModel.setShopId(productModel.getSupplierId());
            productModel.setProductAttributeList(listAttribute);
            String result = productManager.addProduct(thumbnailFileName,productModel, map, fileNames);
            String str = "";
            if ("".equals(result)) {
                str = AjaxObject.newOkForward("添加成功", "info/product/list").toString();
            } else {
                str = AjaxObject.newError("系统错误,请稍等！").toString();
            }
            return str;
        } else {
            return AjaxObject.newError("请先登录").toString();
        }
    }

    /**
     * 商品编辑操作
     * @RequestParam MultipartFile[] file,
     * @return
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String updateProduct(@RequestParam MultipartFile[] thumbnailFile,String thumbnailFileName, String[] fileNames, ProductModel productModel,
                                HttpServletRequest req) {
        String str = "";
        Map<String, Object> map = (Map<String, Object>) getUserMap(req);
        if (map != null) {

//            if (file != null && file.length > 0) {
//                uploadImageManager.uploadImage(file, fileNames,req, productModel.getProductCode(),1);
//            }
            if (thumbnailFile != null && thumbnailFile.length > 0) { 
                String[] thumbnailFileNames = null;
                if (thumbnailFileName != null && !"".equals(thumbnailFileName)) {
                    thumbnailFileNames = new String[] { thumbnailFileName };
                }
                uploadImageManager.uploadImage(thumbnailFile,thumbnailFileNames,req, productModel.getProductCode(),2);
                if(thumbnailFileNames!=null && !"".equals(thumbnailFileName)){
                    thumbnailFileName = thumbnailFileNames[0];
                  }
            }

            if (productModel.getProductDesc() != null && productModel.getProductDesc().length() > 1000) {
                return AjaxObject.newError("豆豆点评不能超过999个字符串").toString();
            }

            String brandName = req.getParameter("productModel.brandName");
            String brandCode = req.getParameter("productModel.brandCode");
            productModel.setBrandCode(brandCode);
            productModel.setBrandName(brandName);
            
            // sku 颜色 尺码 名称
            String skuColorName = req.getParameter("attrColorName");
            String skuSpecName = req.getParameter("attrSpecName");
            
            /*
             * 修改
             */
            // sku
            String[] priceProductSkuCode = req.getParameterValues("priceProductSkuCode");
            String[] priceColorValues = req.getParameterValues("priceColorValue");
            String[] priceColorValueAlias = req.getParameterValues("priceColorValueAlias");
            String[] priceSpecValues = req.getParameterValues("priceSpecValue");
            String[] priceSpecValueAlias = req.getParameterValues("priceSpecValueAlias");
            String[] priceProductSkuId = req.getParameterValues("priceProductSkuId");
            String[] priceMainSkus = req.getParameterValues("priceMainSku");
            String[] priceSupplierSkuCode = req.getParameterValues("priceSupplierSkuCode");
            // 价格
            String[] priceId = req.getParameterValues("priceId");
//            String[] priceCostPrices = req.getParameterValues("priceCostPrice");
            String[] priceMarketPrices = req.getParameterValues("priceMarketPrice");
            String[] priceTshPrices = req.getParameterValues("priceTshPrice");
//            String[] priceTb = req.getParameterValues("priceTb");
            // 库存
            String[] priceVirtualStock = req.getParameterValues("priceVirtualStock");

            /*
             * 新增
             */
            String[] skuColorValues = req.getParameterValues("skuColorValue");
            String[] skuSpecValues = req.getParameterValues("skuSpecValue");
            String[] skuMainSkus = req.getParameterValues("skuMainSku");
            String[] skuColorValueAlias = req.getParameterValues("skuColorValueAlias");
            String[] skuSpecValueAlias = req.getParameterValues("skuSpecValueAlias");
            String[] skuSupplierSkuCode = req.getParameterValues("skuSupplierSkuCode");
            // 价格
//            String[] skuCostPrices = req.getParameterValues("skuCostPrice");
            String[] skuMarketPrices = req.getParameterValues("skuMarketPrice");
            String[] skuTshPrices = req.getParameterValues("skuTshPrice");
//            String[] skuTb = req.getParameterValues("skuTb");
            // 库存
            String[] skuVirtualStock = req.getParameterValues("skuVirtualStock");

            // 弹性域值
            String[] attrbuteNames = req.getParameterValues("attrbuteName");
            String[] attrbuteCodes = req.getParameterValues("attrbuteCode");
            String[] attrbuteTypes = req.getParameterValues("attrbuteType");
            String[] attrbuteId = req.getParameterValues("productAttributeId");
            String[] attrbuteRequired = req.getParameterValues("attrbuteRequired");

            // 弹性域
            // 存放需修改的SKU 对象
            List<ProductSkuModel> listUpdateSku = null;
            // 存放需插入的SKU 对象
            List<ProductSkuModel> listInsertSku = null;
            try {
                List<ProductAttributeModel> listAttribute = new ArrayList<ProductAttributeModel>();
                ProductAttributeModel productAttributeModle = new ProductAttributeModel();
                if (attrbuteNames != null && attrbuteNames.length > 0) {
                    for (int j = 0; j < attrbuteNames.length; j++) {
                        productAttributeModle = new ProductAttributeModel();
                        productAttributeModle.setProductAttributeName(attrbuteNames[j]);

                        if (attrbuteCodes != null && attrbuteCodes.length > 0) {
                            productAttributeModle.setProductAttributeCode(attrbuteCodes[j]);
                        }
                        if (attrbuteId != null && attrbuteId.length > 0) {
                            if (attrbuteId[j] != null && !"".equals(attrbuteId[j])) {
                                productAttributeModle.setProductAttributeId(Long.parseLong(attrbuteId[j]));
                            } else {
                                productAttributeModle.setProductAttributeId(null);
                            }
                        }
                        if (attrbuteTypes != null && attrbuteTypes.length > 0) {
                            productAttributeModle.setType(attrbuteTypes[j]);
                        }
                        if (attrbuteRequired != null && attrbuteRequired.length > 0) {
                            productAttributeModle.setIsRequired(Integer.parseInt(attrbuteRequired[j]));
                        }
                        String[] attrbuteValues = req.getParameterValues("attrbuteValue" + j);
                        String value = "";
                        if (attrbuteValues != null && attrbuteValues.length > 0) {
                            value = attrbuteValues[0];
                            for (int i = 1; i < attrbuteValues.length; i++) {
                                value = value + "," + attrbuteValues[i];
                            }
                        }
                        productAttributeModle.setProductAttributeValue(value);

                        listAttribute.add(productAttributeModle);
                    }
                }
                productModel.setProductAttributeList(listAttribute);

                ProductSkuModel productSkuModel = null;
                ProductPriceModel productPriceModel = null;
                ProductStockModel productStockModel = null;
                listUpdateSku = new ArrayList<ProductSkuModel>();
                listInsertSku = new ArrayList<ProductSkuModel>();

                // 修改的Sku
                if (priceProductSkuCode != null && priceProductSkuCode.length > 0) {
                    for (int i = 0; i < priceProductSkuCode.length; i++) {
                        productSkuModel = new ProductSkuModel();
                        
                        // 颜色 名称
                        productSkuModel.setAttrColorName(skuColorName);
                        // 尺码 名称
                        productSkuModel.setAttrSpecName(skuSpecName);
                        productSkuModel.setProductSkuId(Long.parseLong(priceProductSkuId[i]));
                        productSkuModel.setMainSku(Long.parseLong(priceMainSkus[i]));
                        productSkuModel.setProductSkuCode(priceProductSkuCode[i]);
                        
                        if(priceSupplierSkuCode!=null && priceSupplierSkuCode.length>0){
                            if(priceSupplierSkuCode[i]!=null && !"".equals(priceSupplierSkuCode[i])){
                                productSkuModel.setSupplierSkuCode(priceSupplierSkuCode[i]);
                            }
                        }
                        
                        productSkuModel.setAttrColorValue(priceColorValues[i]);
                        if (priceColorValueAlias.length > 0) {
                            productSkuModel.setColorValueAlias(priceColorValueAlias[i]);
                        }
                        if (priceSpecValueAlias.length > 0) {
                            productSkuModel.setSpecValueAlias(priceSpecValueAlias[i]);
                        }
                        productSkuModel.setAttrSpecValue(priceSpecValues[i]);

                        // 价格
                        productPriceModel = new ProductPriceModel();
                        productPriceModel.setProductPriceId(Long.parseLong(priceId[i]));
                        productPriceModel.setCostPrice(0.0);
                        productPriceModel.setMarketPrice(Double.parseDouble(priceMarketPrices[i]));
                        productPriceModel.setTshPrice(Double.parseDouble(priceTshPrices[i]));
                        productPriceModel.setTb(0l);
                        productSkuModel.setProductPriceModel(productPriceModel);

                        // 库存
                        productStockModel = new ProductStockModel();
                        productStockModel.setVirtualStock(Double.parseDouble(priceVirtualStock[i]));
                        productSkuModel.setProductStockModel(productStockModel);

                        listUpdateSku.add(productSkuModel);
                    }
                }

                // 添加的SKU
                if (skuMainSkus != null && skuMainSkus.length > 0) {
                    for (int j = 0; j < skuMainSkus.length; j++) {
                        productSkuModel = new ProductSkuModel();
                        
                        // 颜色 名称
                        productSkuModel.setAttrColorName(skuColorName);
                        // 尺码 名称
                        productSkuModel.setAttrSpecName(skuSpecName);
                        // SKU
                        productSkuModel.setProductId(productModel.getProductId());
                        productSkuModel.setMainSku(Long.parseLong(skuMainSkus[j]));
                        productSkuModel.setAttrColorValue(skuColorValues[j]);
                        if(skuSupplierSkuCode!=null && skuSupplierSkuCode.length>0){
                            if(skuSupplierSkuCode[j]!=null && !"".equals(skuSupplierSkuCode[j])){
                                productSkuModel.setSupplierSkuCode(skuSupplierSkuCode[j]);
                            }
                        }
                        
                        if (skuColorValueAlias.length > 0) {
                            productSkuModel.setColorValueAlias(skuColorValueAlias[j]);
                        }
                        if (skuSpecValueAlias.length > 0) {
                            productSkuModel.setSpecValueAlias(skuSpecValueAlias[j]);
                        }
                        productSkuModel.setAttrSpecValue(skuSpecValues[j]);

                        // 价格
                        productPriceModel = new ProductPriceModel();
                        productPriceModel.setCostPrice(0.0);
                        productPriceModel.setMarketPrice(Double.parseDouble(skuMarketPrices[j]));
                        productPriceModel.setTshPrice(Double.parseDouble(skuTshPrices[j]));
                        productPriceModel.setTb(0l);

                        // 库存
                        productStockModel = new ProductStockModel();
                        productStockModel.setVirtualStock(Double.parseDouble(skuVirtualStock[j]));

                        productSkuModel.setProductStockModel(productStockModel);
                        productSkuModel.setProductPriceModel(productPriceModel);
                        listInsertSku.add(productSkuModel);
                    }
                }
            } catch (Exception e1) {
                logger.error("商品编辑操作 error",e1);
                return AjaxObject.newError("系统错误,请稍等！").toString();
            }
            String resultStr = "";
            try {
            	productModel.setShopId(productModel.getSupplierId());
                productManager.updateProduct(productModel, listUpdateSku, listInsertSku, map, fileNames,thumbnailFileName);
            } catch (Exception e) {
                logger.error("商品编辑操作 fail",e);
                resultStr = "编辑商品失败";
            }
            
            if ("".equals(resultStr)) {
                
                str = AjaxObject.newOkForward("修改成功", "info/product/list?kk=1").toString();
                
//                str = AjaxObject.newOk("修改成功").setRel("brank").toString();
            } else {
                str = AjaxObject.newError(resultStr).toString();
            }
        } else {
            str = AjaxObject.newError("请先登录").toString();
        }

        return str;
    }

    /**
     * 查看商品详情
     * 
     * @return
     */
    @RequestMapping(value = "/to_info/{id}", method = { RequestMethod.POST, RequestMethod.GET })
    public String getInfo(@PathVariable String id, HttpServletRequest req) {
        if (getUserMap(req) == null) {
            return "index";
        }
       ProductModel productModel = null;
        if(id.length()>=12){
            if(id.length()>12){
                id = id.substring(0, 12);
            }
            try {
                productModel = productManager.getProductByCode(id);
            } catch (Exception e1) {
                logger.error("",e1);
                req.setAttribute("errorMsg", e1.getMessage());
                return InfoUtil.ERROR_500JSP;
            }
        }else{
            long productId = Long.parseLong(id);
            productModel = new ProductModel(productId);
        }
        Map<String, Object> map = null;
        List<ProductSkuModel> listSku1 = null;
        List<ProductCateAttrGroupModel> listAttr = null;
        List<ProductAttributeModel> listAttr2 = null;
        
            map = productManager.getProductInfo(productModel);
            productModel = (ProductModel)map.get("productModel");
            logger.info("商品详细信息："+JSONUtil.toString(productModel));
            
            if(productModel.getProductOperateModel()!=null && productModel.getProductOperateModel().getProductPriceRate()!=0){
                req.setAttribute("priceRate", productModel.getProductOperateModel().getProductPriceRate());
            }else{
                req.setAttribute("priceRate", 10);
            }
            
            InfoUtil.map.put(productModel.getProductId()+"", productModel);
            
            List<ProductSkuModel> listSku = new ArrayList<ProductSkuModel>();
            listSku1 = new ArrayList<ProductSkuModel>();
            if (productModel.getProductSkuList() != null && productModel.getProductSkuList().size() > 0) {
                listSku = (List<ProductSkuModel>) productModel.getProductSkuList();
                for (ProductSkuModel sku : listSku) {
                    if (sku.getProductPriceModel() != null) {
                        if(sku.getIsAvailable()!=null && sku.getIsAvailable()==1){
                            listSku1.add(sku);
                        }
                    }
                }
            }

            List<CategoryPO> listCategory = (List<CategoryPO>)productCategoryManager.getCategoBaseryList(new CategoryPO());
            
            req.setAttribute("listCategory",listCategory);
            
            listAttr = productAttrbuteManager.getAttrByCategory(productModel.getCatCode());
            listAttr2 = productModel.getProductAttributeList();
            
            InfoUtil.getCategoryByCatCode(productModel, productCategoryManager);
        
        req.setAttribute("productModel", productModel);
        req.setAttribute("attrColorName", (String) map.get("attrColorName"));
        req.setAttribute("attrSpecName", (String) map.get("attrSpecName"));
        req.setAttribute("listSku", listSku1);
        req.setAttribute("listSkuJson", JSONUtil.toString(listSku1));
        req.setAttribute("productFirstShelvesTime", map.get("productFirstShelvesTime"));
        req.setAttribute("productLastShelvesTime", map.get("productLastShelvesTime"));
        req.setAttribute("productDownShelvesTime", map.get("productDownShelvesTime"));

        req.setAttribute("listAttr", JSONUtil.toString(listAttr));
        if (listAttr2 != null && listAttr2.size() > 0) {
            req.setAttribute("listAttr2", JSONUtil.toString(listAttr2));
        } else {
            req.setAttribute("listAttr2", " ");
        }
      
		try {
			  List<SupplierModel> shopList = supplierService.getSupplier(null, null);
			  req.setAttribute("shopList", shopList);
		} catch (Exception e) {
			 logger.error("",e);
             req.setAttribute("errorMsg", e.getMessage());
             return InfoUtil.ERROR_500JSP;
		}
       
		
		req.setAttribute("color1", InfoUtil.getProductColor("1"));
        req.setAttribute("color2", InfoUtil.getProductColor("2"));
        req.setAttribute("color3", InfoUtil.getProductColor("3"));
        req.setAttribute("color4", InfoUtil.getProductColor("4"));
        req.setAttribute("color5", InfoUtil.getProductColor("5"));
        req.setAttribute("size1", InfoUtil.getProductSize("1"));
        req.setAttribute("size2", InfoUtil.getProductSize("2"));
        req.setAttribute("size3", InfoUtil.getProductSize("3"));
        req.setAttribute("size4", InfoUtil.getProductSize("4"));
        req.setAttribute("size5", InfoUtil.getProductSize("5"));
        
        String waitShelvesInfo = req.getParameter("waitShelvesInfo");
        if(waitShelvesInfo!=null && "1".equals(waitShelvesInfo)){
            return "info/product/examine/add_examine_info";
        }
        return "info/product/info_product";
    }

    /**
     * 添加商品 获取属性组 和 颜色 尺码属性名称
     * 
     * @param req
     * @return
     */
    @Resource
    ProductAttrbuteManager productAttrbuteManager;

    @RequestMapping(value = "/class", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String getFoundationClass(HttpServletRequest req, HttpServletResponse resp) {
        // 获取 颜色 尺码 属性名称
        String categoryCode = req.getParameter("categoryCode");
        CategoryPO categoryPo = new CategoryPO();
        categoryPo.setCategoryCode(categoryCode);
        categoryPo = productCategoryManager.getCategory(categoryPo);

        // 获取属性项
        List<ProductCateAttrGroupModel> listAttr = productAttrbuteManager.getAttrByCategory(categoryCode);

        String sb = "{\"attrColorName\":\"" + categoryPo.getColorName() + "\",\"attrSpecName\":\""
                    + categoryPo.getSizeName() + "\",\"list\":" + JSONUtil.toString(listAttr) + "}";
        return sb;
    }

    /**
     * 转至新增 导入 页面
     * 
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "/to_upload_add", method = { RequestMethod.POST, RequestMethod.GET })
    public String uploadAdd(HttpServletRequest req) {
        List<CategoryPO> listCategory = null;
        try {
            listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("listCategory", listCategory);
        return "info/product/product_upload_add";
    }

    @RequestMapping(value = "/to_upload_taobao_add", method = { RequestMethod.POST, RequestMethod.GET })
    public String uploadTaobaoExcelAdd(HttpServletRequest req) {
        List<CategoryPO> listCategory = null;
        try {
            listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("listCategory", listCategory);
        return "info/product/product_upload_taobao_add";
    }

    /**
     * 转至编辑 导入 页面
     * 
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "/to_upload_update", method = { RequestMethod.POST, RequestMethod.GET })
    public String uploadUpdate(HttpServletRequest req) {
        List<CategoryPO> listCategory = null;
        try {
            listCategory = productCategoryManager.getCategoBaseryList(new CategoryPO());
        } catch (Exception e) {
            logger.error("转至编辑 导入 页面 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("listCategory", listCategory);
        return "info/product/product_upload_update";
    }
    
    @Resource
    private UploadImageManagerImpl uploadImageManagerImpl;
    /**
     * 新增导入操作
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/upload_excel_add/{excelType}", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String uploadProductAddExcel(@PathVariable Long excelType, @RequestParam("file") MultipartFile file,
                                        String catCode, HttpServletRequest req) {
        Map<String, Object> map = getUserMap(req);
        map.put("excelType", excelType.toString()); // excelType=excel类型：1是新增、2是淘宝模版、3是更新模版
        List<ProductCateAttrGroupModel> listAttr = null;
        CategoryPO categoryPo = new CategoryPO();
        
        if (catCode != null && !"".equals(catCode)) {
            // 获取分类
            categoryPo.setCategoryCode(catCode);
            categoryPo = productCategoryManager.getCategory(categoryPo);
            // 获取属性组
            listAttr = productAttrbuteManager.getAttrByCategory(catCode);
        } else {
            return AjaxObject.newError("请选择分类目录").toString();
        }
        UploadExcelUtil poi = new UploadExcelUtil();
        List<List<String>> list = null;
        try {
            list = poi.read(file.getOriginalFilename(), file.getInputStream());
            if (list != null) {
                String result = productExcelManager.uploadProductAddExcel(list, listAttr, categoryPo, map);
                if (result != null && !"".equals(result)) {
                    return AjaxObject.newError(result).toString();
                }
                uploadImageManagerImpl.uploadExcelToBak(file, req);
            } else {
                return AjaxObject.newError("上传文件格式不正确！").toString();
            }
        } catch (Exception e) {
            logger.error("新增导入操作 error",e);
            return AjaxObject.newError("上传添加失败").toString();
        }
        
        return AjaxObject.newOk().toString();
    }

    /**
     * 编辑导入操作
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/upload_excel_update", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String uploadProductUpdateExcel(@RequestParam("file") MultipartFile file, String catCode,
                                           HttpServletRequest req) {

        Map<String, Object> map = getUserMap(req);
        map.put("excelType", "3"); // excel类型 3是更新

        List<ProductCateAttrGroupModel> listAttr = null;
        CategoryPO categoryPo = new CategoryPO();

        if (catCode != null && !"".equals(catCode)) {
            // 获取分类
            categoryPo.setCategoryCode(catCode);
            categoryPo = productCategoryManager.getCategory(categoryPo);

            // 获取属性组
            listAttr = productAttrbuteManager.getAttrByCategory(catCode);
        } /*else {
            return AjaxObject.newError("请选择分类目录").toString();
        }*/

        UploadExcelUtil poi = new UploadExcelUtil();
        List<List<String>> list = null;
        try {
            list = poi.read(file.getOriginalFilename(), file.getInputStream());
            if (list != null) {
                String result = productExcelManager.uploadProductAddExcel(list, listAttr, categoryPo, map);
                if (result != null && !"".equals(result)) {
                    return AjaxObject.newError(result).toString();
                }
                uploadImageManagerImpl.uploadExcelToBak(file, req);
            } else {
                return AjaxObject.newError("上传文件格式不正确").toString();
            }
        } catch (Exception e) {
            return AjaxObject.newError("上传添加失败").toString();
        }

        return AjaxObject.newOk().toString();
    }

    /**
     * 商品新增模版下载操作
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/export_product_template", method = { RequestMethod.POST, RequestMethod.GET })
    public String exportProductTemplateExcel(String catCode, HttpServletResponse response,HttpServletRequest req) {
        List<ProductCateAttrGroupModel> listAttr = null;
        CategoryPO categoryPo = new CategoryPO();

        try {
            if (catCode != null && !"".equals(catCode)) {
                // 获取分类
                categoryPo.setCategoryCode(catCode);
                categoryPo = productCategoryManager.getCategory(categoryPo);

                // 获取属性组
                listAttr = productAttrbuteManager.getAttrByCategory(catCode);
            }
            productExcelManager.exportProductTemplateExcel(listAttr, categoryPo, response);
        } catch (Exception e) {
            logger.error("商品新增模版下载操作 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        return null;
    }

    /**
     * 商品编辑模版下载操作
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/export_product_template_update", method = { RequestMethod.POST, RequestMethod.GET })
    public String exportProductTemplateExcelUpdate(String catCode, HttpServletResponse response,HttpServletRequest req) {
        List<ProductCateAttrGroupModel> listAttr = null;
        CategoryPO categoryPo = new CategoryPO();

        try {
            if (catCode != null && !"".equals(catCode)) {
                // 获取分类
                categoryPo.setCategoryCode(catCode);
                categoryPo = productCategoryManager.getCategory(categoryPo);

                // 获取属性组
                listAttr = productAttrbuteManager.getAttrByCategory(catCode);
            }
            productExcelManager.exportProductTemplateUpdateExcel(listAttr, categoryPo, response);
        } catch (Exception e) {
            logger.error("商品编辑模版下载操作 error",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        return null;
    }



    /**
     * 商品级别 导出
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/exportProduct_product", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public String exportProductExcel(HttpServletResponse response,
            ProductSkuExportModel productSkuExportModel, Page page,
            HttpServletRequest req) {
            try {
                String startTime = req.getParameter("createStartTime");
                String endTime = req.getParameter("endTime1");
                
                if (startTime != null && !"".equals(startTime)) {
                      if (!"".equals(startTime.trim())) {
                          productSkuExportModel.setStartTime(DatetimeUtil.stringToDate(startTime + " 00:00:01").getTime() / 1000);
                      }
                  }
                if (endTime != null && !"".equals(endTime)) {
                    if (!"".equals(endTime.trim())) {
                        productSkuExportModel.setEndTime(DatetimeUtil.stringToDate(endTime + " 23:59:59").getTime() / 1000);
                    }

                }
                
                String categoryCode1 = req.getParameter("categoryName1");
                String categoryCode2 = req.getParameter("categoryName2");
                String categoryCode3 = req.getParameter("categoryName3");
                
                String catCode = "";
                if (categoryCode3 == null || "".equals(categoryCode3) || "选择分类".equals(categoryCode3)) {
                    if (categoryCode2 == null || "".equals(categoryCode2) || "选择分类".equals(categoryCode2)) {
                        catCode = categoryCode1;
                    } else {
                        catCode = categoryCode2;
                    }
                } else {
                    catCode = categoryCode3;
                }
                if (catCode != null && !"".equals(catCode)) {
                    productSkuExportModel.setCatCode(catCode);
                }
            Long count=0l;
                count = productManager.exportPrdouctCount(productSkuExportModel);
            
            String page1 = req.getParameter("page");
            Integer pageNum = 1;
            pageNum = Integer.parseInt(page1);
            page.setPageNum(pageNum);
            page.setNumPerPage(exportNum);
            page.setTotalCount(count);
            
            List<ProductSkuExportModel> productList = null;
                productList = productManager.exportPrdouct(productSkuExportModel, page);
            
            // 查询所有的二级目录        
            List<CategoryPO> categoryPO2List;
            
                categoryPO2List = productCategoryManager.getAllByLevel(2);
            
        
            // 查询所有的一级目录
            List<CategoryPO> categoryPO1List = new ArrayList<CategoryPO>();
            categoryPO1List = productCategoryManager.getAllByLevel(1);

            String categoryPO1 = null;
            String categoryPO2 = null;
            String categoryPO3 = null;

            for (ProductSkuExportModel p : productList) {
            	
            	if(p==null || (p!=null && p.getCatCode()==null))
            	{
            		continue;            		
            	}
                categoryPO3 = p.getCatCode();
                categoryPO1 = categoryPO3.substring(0, 2);
                categoryPO2 = categoryPO3.substring(0, 4);

                for (CategoryPO c2 : categoryPO2List) // 插入二级目录的name和code
                {
                    if (categoryPO2.equals(c2.getCategoryCode())) {
                        p.setCatCode2(c2.getCategoryCode());
                        p.setCatName2(c2.getCategoryName());
                        break;
                    }

                }
                for (CategoryPO c1 : categoryPO1List)// 插入一级目录的name和code
                {
                    if (categoryPO1.equals(c1.getCategoryCode())) {
                        p.setCatCode1(c1.getCategoryCode());
                        p.setCatName1(c1.getCategoryName());
                        break;
                    }
                }
            }
            
                exportProductExcel(response, productList,"config/excel/productTemplate.xls", req,"productList.xls");
            

            } catch (Exception e) {
                logger.error("商品级别 导出 error",e);
            }
        return null;
    }
    
    
    /**
     * SKU 级别 导出
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/exportProduct_SKU", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public String exportProductExcelSKU(HttpServletResponse response,
            ProductSkuExportModel productSkuExportModel, Page page,
            HttpServletRequest req) {
            try {
                
                String startTime = req.getParameter("createStartTime");
                String endTime = req.getParameter("endTime1");
                
                if (startTime != null && !"".equals(startTime)) {
                      if (!"".equals(startTime.trim())) {
                          productSkuExportModel.setStartTime(DatetimeUtil.stringToDate(startTime + " 00:00:01").getTime() / 1000);
                      }
                  }
                if (endTime != null && !"".equals(endTime)) {
                    if (!"".equals(endTime.trim())) {
                        productSkuExportModel.setEndTime(DatetimeUtil.stringToDate(endTime + " 23:59:59").getTime() / 1000);
                    }

                }
                
                String categoryCode1 = req.getParameter("categoryName1");
                String categoryCode2 = req.getParameter("categoryName2");
                String categoryCode3 = req.getParameter("categoryName3");
                
                String catCode = "";
                if (categoryCode3 == null || "".equals(categoryCode3) || "选择分类".equals(categoryCode3)) {
                    if (categoryCode2 == null || "".equals(categoryCode2) || "选择分类".equals(categoryCode2)) {
                        catCode = categoryCode1;
                    } else {
                        catCode = categoryCode2;
                    }
                } else {
                    catCode = categoryCode3;
                }
                if (catCode != null && !"".equals(catCode)) {
                    productSkuExportModel.setCatCode(catCode);
                }
            Long count=0l;
                count= productSkuManager.queryProductSkuExportCount(productSkuExportModel);
            
            String page1 = req.getParameter("page");
            Integer pageNum = 1;
            pageNum = Integer.parseInt(page1);
            page.setPageNum(pageNum);
            page.setNumPerPage(exportNum);
            page.setTotalCount(count);
            
            List<ProductSkuExportModel> productList = null;
                productList = productSkuManager
                        .getProductSkuExportModel(page, productSkuExportModel);
            
            // 查询所有的二级目录        
            List<CategoryPO> categoryPO2List;
            
                categoryPO2List = productCategoryManager.getAllByLevel(2);
            
        
            // 查询所有的一级目录
            List<CategoryPO> categoryPO1List = new ArrayList<CategoryPO>();
            categoryPO1List = productCategoryManager.getAllByLevel(1);

            String categoryPO1 = null;
            String categoryPO2 = null;
            String categoryPO3 = null;

            for (ProductSkuExportModel p : productList) {
                
                if(p==null || (p!=null && p.getCatCode()==null))
                {
                    continue;                   
                }
                categoryPO3 = p.getCatCode();
                categoryPO1 = categoryPO3.substring(0, 2);
                categoryPO2 = categoryPO3.substring(0, 4);

                for (CategoryPO c2 : categoryPO2List) // 插入二级目录的name和code
                {
                    if (categoryPO2.equals(c2.getCategoryCode())) {
                        p.setCatCode2(c2.getCategoryCode());
                        p.setCatName2(c2.getCategoryName());
                        break;
                    }

                }
                for (CategoryPO c1 : categoryPO1List)// 插入一级目录的name和code
                {
                    if (categoryPO1.equals(c1.getCategoryCode())) {
                        p.setCatCode1(c1.getCategoryCode());
                        p.setCatName1(c1.getCategoryName());
                        break;
                    }
                }
            }
            
                exportProductExcel(response, productList,"config/excel/productSkuTemplate.xls", req,"productSkuList.xls");
            

            } catch (Exception e) {
                logger.error("SKU 级别 导出 error",e);
            }
        return null;
    }
    
    /**
     * 根据excel模版 ,modelList,response,require,目标文件后缀名 导出excel报表
     * 
     * @param response
     * @param list
     * @param templateFileName
     * @param req
     * @param filename
     * @return
     */
    public String exportProductExcel(HttpServletResponse response,
            List<?> list, String templateFileName, HttpServletRequest req,
            String filename) {

        String resultFileName = "config/excel/" + new Random(10000).toString()
                + filename;
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.createExcel(templateFileName, list, resultFileName);

        try {
            XLSTransformer transformer = new XLSTransformer();
            Workbook wb = null;
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename="
                    + filename);// 组装附件名称和格式

            URL url = this.getClass().getClassLoader().getResource("");
            // 得到模板文件路徑
            // String srcFilePath = url.getPath() + templateFileName;
            Map<String, Object> beanParams = new HashMap<String, Object>();
            beanParams.put("list", list);
            String destFilePath = url.getPath() + resultFileName;

            try {
                wb = transformer.transformXLS(
                        new FileInputStream(destFilePath), beanParams);
            } catch (ParsePropertyException e) {
                logger.error("",e);
            } catch (InvalidFormatException e) {
                logger.error("",e);
            }

            try {
                wb.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } finally {
                outputStream.close();
            }

        } catch (IOException e) {
            logger.error("",e);
        }
        return null;
    }
    

    
    /**
     * 商品审核操作
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/product_examine", method = { RequestMethod.POST })
    @ResponseBody
    public String productExamine(String[] ids,ProductInfoAuthModel productInfoAuthModel,ProductModel productModel,HttpServletRequest req,Integer submitType,Integer info)
    {
       
        Map<String,Object> map = getUserMap(req);
        if(map!=null){
            String result = "";
            String resultStr = "";
            for(String infoAuthId:ids){
                try {
                    ProductInfoAuthModel InfoAuthMod = new ProductInfoAuthModel();
                    InfoAuthMod.setProductInfoAuthId(Long.parseLong(infoAuthId));
                    InfoAuthMod = productInfoAuthManager.getProductInfoAuthById(InfoAuthMod);
                    logger.info(JSONUtil.toString(InfoAuthMod));
                    if(InfoAuthMod!=null && InfoAuthMod.getStaus().longValue()!=1){
                        return AjaxObject.newError(InfoAuthMod.getProductCode()+"该数据已审核或已处理,请刷新后再查看").toString();
                    }
                } catch (Exception e1) {
                    logger.error("",e1);
                    return AjaxObject.newError("系统错误").toString();
                }
                
                ProductInfoAuthModel productInfoAuthMod = new ProductInfoAuthModel();
                if(productInfoAuthModel.getStaus()==2){
                    productInfoAuthModel.setReason("");
                }
                
                productInfoAuthMod.setProductInfoAuthId(Long.parseLong(infoAuthId));
                productInfoAuthMod.setStaus(productInfoAuthModel.getStaus());
               
                productInfoAuthMod.setReason(productInfoAuthModel.getReason());
                productInfoAuthMod.setUpdateBy(Long.parseLong(map.get("userId").toString()));
                productInfoAuthMod.setUpdateUserName(map.get("userName").toString());
                productInfoAuthMod.setUpdateTime(new Date().getTime()/1000);
                try {
                    Map<String,Object> map1 = productInfoAuthManager.getProductInfoAuth(productInfoAuthMod);
                    if("1".equals(map1.get("type"))){
                        if(productInfoAuthMod.getStaus()==2){
                            ProductModel product = (ProductModel)map1.get("productModel");
                            ProductShelvesTmpModel productShelvesTmpModel = new ProductShelvesTmpModel();
                            productShelvesTmpModel.setProductCode(product.getProductCode());
                            productShelvesTmpModel.setProductName(product.getProductName());
                            productShelvesTmpModel.setStatus(1);
                            String skuCode = "";
                            if(product.getProductSkuList()!=null && product.getProductSkuList().size()>0){
                                for(ProductSkuModel sku:product.getProductSkuList()){
                                    if(sku.getMainSku().longValue()==1){
                                        skuCode = skuCode + sku.getProductSkuCode()+",";
                                    }
                                }
                                if(skuCode.indexOf(",")>0){
                                    skuCode = skuCode.substring(0, skuCode.lastIndexOf(","));
                                }
                                productShelvesTmpModel.setTotalSkuCode(skuCode);
                            }
                            
                            productShelvesManager.addShelvesTmp(productShelvesTmpModel);
                        }
                    }else{
                        if(productInfoAuthMod.getStaus()==2){
                            ProductModel product = (ProductModel)map1.get("newProduct");
                            ProductShelvesTmpModel productShelvesTmpModel = productShelvesManager.getShelvesTmpByProductCode(product.getProductCode());
                            if(productShelvesTmpModel!=null && productShelvesTmpModel.getStatus()==1){
                                String skuCode = "";
                                List<ProductSkuModel> listInsert = (List<ProductSkuModel>)map1.get("insertListSku");
                                if(product.getProductSkuList()!=null && product.getProductSkuList().size()>0){
                                    for(ProductSkuModel sku:product.getProductSkuList()){
                                        if(sku.getMainSku().longValue()==1){
                                            skuCode = skuCode + sku.getProductSkuCode()+",";
                                        }
                                    }
                                }
                                if(listInsert!=null && listInsert.size()>0){
                                    for(ProductSkuModel sku:listInsert){
                                        if(sku.getMainSku().longValue()==1){
                                            skuCode = skuCode + sku.getProductSkuCode()+",";
                                        }
                                    }
                                }
                                if(skuCode.indexOf(",")>0){
                                    skuCode = skuCode.substring(0, skuCode.lastIndexOf(","));
                                }
                                productShelvesTmpModel.setTotalSkuCode(skuCode);
                                productShelvesManager.updateShelvesTmp(productShelvesTmpModel);
                            }
                        }
                    }
                    productInfoAuthManager.updateProductInfo(productInfoAuthMod);
                } catch (Exception e) {
                    logger.error("",e);
                    resultStr = e.getMessage();
                }
            }
            if(submitType==1){
                if ("".equals(resultStr)) {
                    result = new AjaxObject().newOkCallbackType("操作成功").toString();
                }
               else
               {
                    result = new AjaxObject().newError(resultStr).toString();
                }
            }else{
                if ("".equals(resultStr)) {
                    result = AjaxObject.newOk("操作成功").toString();
                }
               else
               {
                    result = AjaxObject.newError(resultStr).toString();

                }
            }
            return result;
            
        }else{
            return AjaxObject.newError("登录超时,请重新登录！").toString();
        }
        
    }
    
    /**
     * 商品预览
     *
     * @return
     */
    @RequestMapping(value="/to_look/{id}",method={RequestMethod.POST,RequestMethod.GET})
    public String getLook(@PathVariable String id,HttpServletRequest req){
    	 if (getUserMap(req) == null) {
             //return "index";
         }
         ProductModel productModel = null;
         if(id.length()>=12){
             if(id.length()>12){
                 id = id.substring(0, 12);
             }
             try {
                 productModel = productManager.getProductByCode(id);
             } catch (Exception e1) {
                 logger.error("根据productCode查询商品 error ",e1);
                 req.setAttribute("errorMsg", e1.getMessage());
                 return InfoUtil.ERROR_500JSP;
             }
         }else{
             long productId = Long.parseLong(id);
             productModel = new ProductModel(productId);
         }
         Map<String, Object> map = null;
         List<ProductSkuModel> listSku1 = null;
         List<ProductCateAttrGroupModel> listAttr = null;
         List<ProductAttributeModel> listAttr2 = null;
         
             map = productManager.getProductInfo(productModel);
             productModel = (ProductModel)map.get("productModel");
             
             if(productModel.getProductOperateModel()!=null && productModel.getProductOperateModel().getProductPriceRate()!=0){
                 req.setAttribute("priceRate", productModel.getProductOperateModel().getProductPriceRate());
             }else{
                 req.setAttribute("priceRate", 10);
             }
             
             InfoUtil.map.put(productModel.getProductId()+"", productModel);
             
             List<ProductSkuModel> listSku = new ArrayList<ProductSkuModel>();
             listSku1 = new ArrayList<ProductSkuModel>();
             if (productModel.getProductSkuList() != null && productModel.getProductSkuList().size() > 0) {
                 listSku = (List<ProductSkuModel>) productModel.getProductSkuList();
                 for (ProductSkuModel sku : listSku) {
                     if (sku.getProductPriceModel() != null) {
                         if(sku.getIsAvailable()!=null && sku.getIsAvailable()==1){
                             listSku1.add(sku);
                         }
                     }
                 }
             }

             List<CategoryPO> listCategory = (List<CategoryPO>)productCategoryManager.getCategoBaseryList(new CategoryPO());
             
             req.setAttribute("listCategory",listCategory);
             
             listAttr = productAttrbuteManager.getAttrByCategory(productModel.getCatCode());
             listAttr2 = productModel.getProductAttributeList();

         req.setAttribute("productModel", productModel);
         req.setAttribute("attrColorName", (String) map.get("attrColorName"));
         req.setAttribute("attrSpecName", (String) map.get("attrSpecName"));
         req.setAttribute("listSku", listSku1);
         req.setAttribute("listSkuJson", JSONUtil.toString(listSku1));
         req.setAttribute("productFirstShelvesTime", map.get("productFirstShelvesTime"));
         req.setAttribute("productLastShelvesTime", map.get("productLastShelvesTime"));
         req.setAttribute("productDownShelvesTime", map.get("productDownShelvesTime"));

         req.setAttribute("listAttr", JSONUtil.toString(listAttr));
         if (listAttr2 != null && listAttr2.size() > 0) {
             req.setAttribute("listAttr2", JSONUtil.toString(listAttr2));
         } else {
             req.setAttribute("listAttr2", " ");
         }
         String yanse="";
         String chima="";
         String ycDate="";
         String ys="";
         String cm="";
         Map<String,String> yanseMap= new HashMap<String, String>();
         Map<String,String> chimaMap= new HashMap<String, String>();
         Map<String,Object> imageMap = new HashMap<String,Object>();
        if(null!=listSku1&&listSku1.size()>0){
        	for (int j = 0; j < listSku1.size(); j++) {
        		ProductSkuModel  pp=listSku1.get(j);
        		ys=pp.getAttrColorName();
        		cm=pp.getAttrSpecName();
        		if(pp.getProductImagerList()!=null && pp.getProductImagerList().size()>0){
        		    yanseMap.put(pp.getColorValueAlias(),pp.getProductImagerList().get(0).getImage());
        		}else{
        		    yanseMap.put(pp.getColorValueAlias(),productModel.getMainPic());
        		}
        		chimaMap.put(pp.getSpecValueAlias(), pp.getSpecValueAlias()); 
        		ycDate=ycDate+"'"+pp.getColorValueAlias()+";"+pp.getSpecValueAlias()+"':{price:"+pp.getProductPriceModel().getTshPrice()+",count:"+pp.getProductStockModel().getVirtualStock()+",tb:"+pp.getProductPriceModel().getTb()+"},";
			}
        	
        	for(String dataKey : yanseMap.keySet())   {  
        	    yanse=yanse+"'"+dataKey+"',";
        	}
        	
        	for(String dataKey : chimaMap.keySet())   {  
        	    chima=chima+"'"+dataKey+"',";
        	}
        	if(yanse.length()>0){
        		yanse=yanse.substring(0, yanse.length()-1);
        		chima=chima.substring(0, chima.length()-1);
        		ycDate=ycDate.substring(0, ycDate.length()-1);
        	}
        }
         req.setAttribute("imglist", listSku1.get(0).getProductImagerList());
         req.setAttribute("imageMap", JSONUtil.toString(listSku1));
         req.setAttribute("ys", ys);
         req.setAttribute("cm", cm);
         req.setAttribute("yanse", yanse);
         req.setAttribute("chima", chima);
         req.setAttribute("keys", "[["+yanse+"],["+chima+"]]");
         req.setAttribute("yanseMap", yanseMap);
         req.setAttribute("chimaMap", chimaMap);
         req.setAttribute("ycDate","{"+ycDate+"}");
         req.setAttribute("listColor", StaticDefaultData.productColor);
         req.setAttribute("size1", InfoUtil.getProductSize("1"));
         req.setAttribute("size2", InfoUtil.getProductSize("2"));
         req.setAttribute("size3", InfoUtil.getProductSize("3"));
         req.setAttribute("size4", InfoUtil.getProductSize("4"));
         req.setAttribute("size5", InfoUtil.getProductSize("5"));
         req.setAttribute("size6", InfoUtil.getProductSize("6"));
        return PRODCUTLOOK;
    }
     
    
    /**
     * 商品审核预览
     *
     * @return
     */
    @RequestMapping(value="/to_examine_look/{id}",method={RequestMethod.POST,RequestMethod.GET})
    public String getExamineLook(@PathVariable String id,HttpServletRequest req){
        
        ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
        productInfoAuthModel.setProductInfoAuthId(Long.parseLong(id));
        Map<String, Object> map = null;
        try {
            map = productInfoAuthManager.getProductInfoAuth(productInfoAuthModel);
        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        String type = (String)map.get("type");
        
        List<ProductSkuModel> skuList = null;
        ProductModel productModel = null;
        if("1".equals(type)){
            productModel = (ProductModel)map.get("productModel");
        }else{
            productModel = (ProductModel)map.get("newProduct");
            skuList = (List<ProductSkuModel>) map.get("insertListSku");
            if(productModel.getProductSkuList()!=null && productModel.getProductSkuList().size()>0){
                if(skuList!=null && skuList.size()>0){
                    for(ProductSkuModel sku:skuList){
                        productModel.getProductSkuList().add(sku);
                    }
                }
            }else{
                productModel.setProductSkuList(skuList);
            }
        }
        
        String yanse="";
        String chima="";
        String ycDate="";
        String ys="";
        String cm="";
        Map<String,String> yanseMap= new HashMap<String, String>();
        Map<String,String> chimaMap= new HashMap<String, String>();
        skuList = (List<ProductSkuModel>)productModel.getProductSkuList();
       if(null!=skuList&&skuList.size()>0){
           for (int j = 0; j < skuList.size(); j++) {
               ProductSkuModel  pp = skuList.get(j);
               ys=pp.getAttrColorName();
               cm=pp.getAttrSpecName();
               if(pp.getProductImagerList()!=null && pp.getProductImagerList().size()>0){
                   yanseMap.put(pp.getColorValueAlias(),pp.getProductImagerList().get(0).getImage());
               }else{
                   yanseMap.put(pp.getColorValueAlias(),productModel.getMainPic());
               }
               
               chimaMap.put(pp.getSpecValueAlias(), pp.getSpecValueAlias()); 
               ycDate=ycDate+"'"+pp.getColorValueAlias()+";"+pp.getSpecValueAlias()+"':{price:"+pp.getProductPriceModel().getTshPrice()+",count:"+pp.getProductStockModel().getVirtualStock()+",tb:"+pp.getProductPriceModel().getTb()+"},";
               
           }
           
           for(String dataKey : yanseMap.keySet())   {  
               yanse=yanse+"'"+dataKey+"',";
           }
           
           for(String dataKey : chimaMap.keySet())   {  
               chima=chima+"'"+dataKey+"',";
           }
           if(yanse.length()>0){
               yanse=yanse.substring(0, yanse.length()-1);
               chima=chima.substring(0, chima.length()-1);
               ycDate=ycDate.substring(0, ycDate.length()-1);
           }
       }
           
        req.setAttribute("imageMap", JSONUtil.toString(skuList));
        req.setAttribute("imglist", skuList.get(0).getProductImagerList());
        req.setAttribute("productModel", productModel);
        req.setAttribute("ys", ys);
        req.setAttribute("cm", cm);
        req.setAttribute("yanse", yanse);
        req.setAttribute("chima", chima);
        req.setAttribute("keys", "[["+yanse+"],["+chima+"]]");
        req.setAttribute("yanseMap", yanseMap);
        req.setAttribute("chimaMap", chimaMap);
        req.setAttribute("ycDate","{"+ycDate+"}");
        
        return "info/product/view_product";
    }
    
    @Value("#{propertyConfigurerForProject2['info_examine_export_num']}")
    private Integer infoExamineExportNum;

    /**
     * 商品 导出
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "/examine/export", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    public String exportExamineExcel(HttpServletResponse response,
            ProductInfoAuthModel productInfoAuthModel, Page page,
            HttpServletRequest req,String exportType) {
        
        String startTime = req.getParameter("infoAuthStartTime");
        String endTime =req.getParameter("infoAuthEndTime");
        String startTime2 =req.getParameter("startTime2");
        String endTime2 =req.getParameter("endTime2");
        if(startTime!=null && !"".equals(startTime)){
            productInfoAuthModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("infoAuthStartTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
            productInfoAuthModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("infoAuthEndTime", endTime);
        }
        if(startTime2!=null && !"".equals(startTime2)){
            productInfoAuthModel.setUpdateTime(DatetimeUtil.stringToDate(startTime2+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime2", startTime2);
        }
        if(endTime2!=null && !"".equals(endTime2)){
            productInfoAuthModel.setEndTime2(Long.toString(DatetimeUtil.stringToDate(endTime2+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime2", endTime2);
        }

        
//        String page1 = req.getParameter("page");
        Integer pageNum = 1;
//        pageNum = Integer.parseInt(page1);
        page.setPageNum(pageNum);
        page.setNumPerPage(infoExamineExportNum);
        
        List<ProductInfoAuthModel> listInfoAuth = null;
//        List<ProductInfoAuthModel> listInfoAuth1 = new ArrayList<ProductInfoAuthModel>();
        try {
            listInfoAuth = productInfoAuthManager.getProductInfoAuthList(page,productInfoAuthModel);
            if(listInfoAuth!=null){
                for(ProductInfoAuthModel productInfoAuth:listInfoAuth){
                    productInfoAuthManager.getProductInfoAuthTwo(productInfoAuth);
                }
            }
            
         // 查询所有的一级目录
            List<CategoryPO> categoryPO1List = new ArrayList<CategoryPO>();
            categoryPO1List = productCategoryManager.getAllByLevel(1);
            
            // 查询所有的二级目录        
            List<CategoryPO> categoryPO2List;
            
                categoryPO2List = productCategoryManager.getAllByLevel(2);

            String categoryPO1 = null;
            String categoryPO2 = null;
            String categoryPO3 = null;

            for (ProductInfoAuthModel p : listInfoAuth) {
            	
            	if(p==null || (p!=null && p.getCatCode()==null))
            	{
            		continue;            		
            	}
                
                categoryPO3 = p.getCatCode();                
                categoryPO1 = categoryPO3.substring(0, 2);
                categoryPO2 = categoryPO3.substring(0, 4);

                for (CategoryPO c2 : categoryPO2List) // 插入二级目录的name和code
                {
                    if (categoryPO2.equals(c2.getCategoryCode())) {
                        p.setCatCode2(c2.getCategoryCode());
                        p.setCatName2(c2.getCategoryName());
                        break;
                    }

                }
                for (CategoryPO c1 : categoryPO1List)// 插入一级目录的name和code
                {
                    if (categoryPO1.equals(c1.getCategoryCode())) {
                        p.setCatCode1(c1.getCategoryCode());
                        p.setCatName1(c1.getCategoryName());
                        break;
                    }
                }
            }
            exportProductExcel(response, listInfoAuth,"config/excel/infoExamine.xls", req,"infoExamine.xls");
        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        return null;
    }
    
    @Resource
    private ImagePathManager imagePathManager;
    
    @RequestMapping(value = "/delete_img", method = { RequestMethod.POST,
            RequestMethod.GET })
    @ResponseBody
    public String deleteImage(String productCode,String productSkuCode,Integer level){
        ResponseObject responseObject = FileUtil.deleteImage(productCode, productSkuCode, level, imagePathManager);
        
        String result = "";
        if(responseObject.getCode()==0){
            result = "success";
        }else{
            result = "error";
        }
        return result;
    }
    
    /**
	 * 新增导入操作
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/upload_image", method = { RequestMethod.POST, RequestMethod.GET })
	public String uploadProductAddTaobaoExcel(HttpServletRequest req) {
		return "info/product/upload_taobao_image";
	}
	
	/**
	 * 编辑器 图片上传操作
	 * 
	 * @param file
	 * @param response
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/taobaoImagesUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public String taobaoImagesUpload(@RequestParam MultipartFile[] file, HttpServletResponse response,HttpServletRequest req) {
		try {
			StringBuffer msg =new StringBuffer();
			for (MultipartFile filedata : file) {
					ProductModel pp=new ProductModel();
					String fileName = filedata.getOriginalFilename().substring(0,filedata.getOriginalFilename().lastIndexOf("."));
					msg.append(fileName).append(",");
					if(fileName.length()==17){
						String productCode = fileName.substring(0, 12);
						String productSkuCode = fileName.substring(0, 15);
						String level = fileName.substring(16, 17);
						// 获取根目录
						String targetDirectory = imgTmpDir+ "/" + productCode;
						// 路径不存在 则创建
						File saveDir = new File(targetDirectory);
						if (!saveDir.exists()) {
							saveDir.mkdirs();
						}
						String fileAbsoluteName = "";
						try {
							InputStream in = filedata.getInputStream();
							fileAbsoluteName = targetDirectory+ "/"+ filedata.getOriginalFilename();
							FileOutputStream fos = new FileOutputStream(fileAbsoluteName);
							byte[] b = new byte[(int) filedata.getSize()];
							in.read(b);
							fos.write(b);
							in.close();
							fos.close();
						} catch (IOException e) {
							logger.error("批量上传图片异常", e);
						}
						List<Image> listImg = new ArrayList<Image>();
						Image imageManager = null;
						imageManager = new Image();
						imageManager.setImageIndex(Integer.valueOf(level));
						imageManager.setImageName(filedata.getOriginalFilename());
						listImg.add(imageManager);
						RequestObject requestObjest = new RequestObject();
						UploadImageRequestPO requestPo = new UploadImageRequestPO();
						requestPo.setImages(listImg);
						requestPo.setRelationCode(productCode);
						requestPo.setRelationSubCode(productSkuCode);
						requestPo.setTmpPath("product/"+ productCode);
						requestPo.setRelationType(1);
						requestPo.setNeedlevel(1);
						requestObjest.setRequestParams(requestPo);
						ResponseObject responseObject = imagePathManager.uploadImage(requestObjest);
						if (responseObject.getCode() == 0) {
							UploadImageResponsePO responsePo = (UploadImageResponsePO) responseObject.getData();
							listImg = responsePo.getImages();
			//				ProductModel product = new ProductModel();
			//				product.setMainPic(imageModel.getImageUrl());
			//				product.setProductCode(p.getProductCode());
			//				product.setIntegrityDesc("品牌");
			//				productDao.update(product);
							logger.debug("请求参数(图片服务器)ImagesUpload:"+ JSONUtil.toString(requestObjest));
							logger.debug("返回参数(图片服务器)ImagesUpload:"+ JSONUtil.toString(responsePo));
							msg.append("1");
						} else {
							logger.error("图片hessain接口异常:" + responseObject .getMsg());
							pp.setReason("图片hessain接口异常");
					    }
					} else{
                        logger.error("图片命名长度不对 fileName =" + fileName);
                        msg.append("2");
				    }
			}
			    response.getWriter().write(msg.toString());
		} catch (Exception e) {
			logger.error("图片上传错误", e);
			try {
				response.getWriter().write("false");
			} catch (IOException e1) {

			}
		}
		return null;
	}
}
