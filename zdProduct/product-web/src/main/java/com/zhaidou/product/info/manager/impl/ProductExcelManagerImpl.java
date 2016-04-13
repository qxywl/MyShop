package com.zhaidou.product.info.manager.impl;

import com.google.common.base.Joiner;
import com.zhaidou.common.util.BarCodeUtils;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.string.StringUtil;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.brand.service.ProductBrandService;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.info.config.StaticDefaultData;
import com.zhaidou.product.info.enumration.ProductType;
import com.zhaidou.product.info.manager.ProductAttrbuteManager;
import com.zhaidou.product.info.manager.ProductExcelManager;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.model.*;
import com.zhaidou.product.info.service.*;
import com.zhaidou.product.info.util.InfoUtil;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

@Service("productExcelManager")
public class ProductExcelManagerImpl implements ProductExcelManager {
    
    private static Logger logger = Logger.getLogger(ProductAttrbuteManagerImpl.class);
    
    @Resource
    private ProductService productService;
    @Resource
    private ProductManager productManager;
    @Resource
    ProductAttrbuteManager productAttrbuteManager;
    
    @Resource
    private ProductOperateService productOperateService;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ProductPriceService productPriceService;
    @Resource
    private ProductStockService productStockService;
    @Resource
    private ProductSkuService productSkuService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private ProductAttributeService productAttributeService;
    @Resource
    private ProductAirService productAirService;
    @Resource
    private ProductHotelService productHotelService;
    @Resource
    private ProductFlowerService productFlowerService;
    @Resource
    private ProductMallService productMallService;
    @Resource
    private ProductInfoAuthService productInfoAuthService;
    @Resource
    private ProductBrandService productBrandService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductInfoLogService productInfoLogService;
    @Resource
    private ProductPriceListService productPriceListService;
    @Resource
    private SupplierService supplierService;
    
    @Value("#{propertyConfigurerForProject2['product_export_model_title']}")
    private String productExportModelTitle;
    
    @Value("#{propertyConfigurerForProject2['upload_img_tmp_dir']}")
    private String uploadImgTmpDir;
    
    /**
     * 商品模版 导出 操作
     *
     * @param productModel
     * @return
     */
    @Override
    public String exportProductTemplateExcel(List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,HttpServletResponse response) {
        int totalSize = 0;
        if(listAttr!=null){
            totalSize = StaticDefaultData.addProductImport.size()+listAttr.size();
        }else{
            totalSize = StaticDefaultData.addProductImport.size();
        }
         
        String[] titlesName = new String[totalSize]; 
        
        int index =0;
        for(String key:StaticDefaultData.addProductImport.keySet()){
            titlesName[index] = StaticDefaultData.addProductImport.get(key);
            index++;
        }
        //记录弹性域属性项对象 在导出excel中的索引位置以及对应的value值
        Map<String,String> indexMap = new HashMap<String, String>(); 
        
        index = StaticDefaultData.addProductImport.size();
        if(listAttr!=null && listAttr.size()>0){
            for(int y=0;y<listAttr.size();y++){
                ProductCateAttrGroupModel attrGroupModel = listAttr.get(y);
                titlesName[index] = attrGroupModel.getAttrName();
                if(attrGroupModel.getAttrDesign()!=null && attrGroupModel.getAttrDesign()==1){
                    indexMap.put(""+index, attrGroupModel.getAttrValue());
                }
                index++;
            }
        }
        logger.debug("商品模版 导出 开始····！");
        try {
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String((InfoUtil.getStringToUtf(productExportModelTitle)).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
             
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(InfoUtil.getStringToUtf(productExportModelTitle));  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            XSSFCell cell = null; 
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);
            cell = headRow.createCell(0);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("zdadd 1.0.0");
            cell = headRow.createCell(1);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("add");
            
            headRow = sheet.createRow(1);  
             
            for (int i = 0; i < titlesName.length; i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle); 
                cell.setCellValue(titlesName[i]);  
            }
            XSSFRow bodyRow = sheet.createRow(2);
            for (int i = 0; i < titlesName.length; i++)  
            {  
                if(i==4){
                    cell = bodyRow.createCell(i);  
                    cell.setCellStyle(bodyStyle); 
                    cell.setCellValue(categoryPo.getCategoryCode());
                }
                if(i==5){
                    cell = bodyRow.createCell(i);  
                    cell.setCellStyle(bodyStyle); 
                    cell.setCellValue(categoryPo.getCategoryName());
                }
                for(String indexStr:indexMap.keySet()){
                    if(i==Integer.parseInt(indexStr)){
                        cell = bodyRow.createCell(i);  
                        cell.setCellStyle(bodyStyle); 
                        cell.setCellValue(indexMap.get(indexStr));
                    }
                }
            }
            
            sheet = workBook.createSheet("模版示例");
            sheet.setColumnWidth(0, "颜色值:颜色值|尺码值:尺码值".length()*256*2);
            sheet.setColumnWidth(1, "颜色值:尺码值:是否主SKU:销售价:市场价:手工库存|....".length()*256*2);
            //sheet.setColumnWidth(2, "图片绝对路径:所属第几个SKU:是该SKU的第几张图片|....".length()*256*2);
            exportUtil = new ExportUtil(workBook, sheet);  
            headStyle = exportUtil.getHeadStyle();  
            bodyStyle = exportUtil.getBodyStyle();  
            cell = null; 
            // 构建表头  
            headRow = sheet.createRow(0);
            cell = headRow.createCell(0);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("销售属性");
            
            cell = headRow.createCell(1);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("SKU属性组合");
            
            cell = headRow.createCell(2);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("图片组合"); 
            
            bodyRow = sheet.createRow(1);
            cell = bodyRow.createCell(0); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue("颜色值#颜色值&尺码值#尺码值");
            
            cell = bodyRow.createCell(1);  
            cell.setCellStyle(bodyStyle); 
            cell.setCellValue("颜色值#尺码值#是否主SKU#销售价#市场价#手工库存&....");
            
           /* cell = bodyRow.createCell(2);  
            cell.setCellStyle(bodyStyle); 
            cell.setCellValue("图片本地绝对路径:所属第几个SKU:是该SKU的第几张图片|....");*/
            try  
            {  
                workBook.write(outputStream);  
                outputStream.flush();  
                outputStream.close();  
            } finally  
            {  
                outputStream.close();  
            }
        } catch (Exception e) {
            logger.error("商品新增模版 导出 失败！"+e.toString());
        }  
        logger.debug("商品新增模版 导出 成功！");
        return null;
    }
    
    @Value("#{propertyConfigurerForProject2['product_update_model_title']}")
    private String productUpdateModelTitle;
    
    
    /**
     * 商品 编辑模版下载
     *
     * @return
     */
    @Override
    public String exportProductTemplateUpdateExcel(List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,HttpServletResponse response) {
        int totalSize = 0;
        if(listAttr!=null){
            totalSize = StaticDefaultData.updateProductImport.size()+listAttr.size();
        }else{
            totalSize = StaticDefaultData.updateProductImport.size();
        }
         
        String[] titlesName = new String[totalSize]; 
        
        int index =0;
        for(String key:StaticDefaultData.updateProductImport.keySet()){
            titlesName[index] = StaticDefaultData.updateProductImport.get(key);
            index++;
        }
        //记录弹性域属性项对象 在导出excel中的索引位置以及对应的value值
        Map<String,String> indexMap = new HashMap<String, String>(); 
        
        index = StaticDefaultData.updateProductImport.size();
        if(listAttr!=null && listAttr.size()>0){
            for(int y=0;y<listAttr.size();y++){
                ProductCateAttrGroupModel attrGroupModel = listAttr.get(y);
                titlesName[index] = attrGroupModel.getAttrName();
                if(attrGroupModel.getAttrDesign()!=null && attrGroupModel.getAttrDesign()==1){
                    indexMap.put(""+index, attrGroupModel.getAttrValue());
                }
                index++;
            }
        }
        logger.debug("商品模版 导出 开始····！");
        try {
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String((InfoUtil.getStringToUtf(productUpdateModelTitle)).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
             
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(InfoUtil.getStringToUtf(productUpdateModelTitle));  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            XSSFCell cell = null; 
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);
            cell = headRow.createCell(0);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("tshupdate 1.0.0");
            cell = headRow.createCell(1);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("update");
            
            headRow = sheet.createRow(1);  
             
            for (int i = 0; i < titlesName.length; i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle); 
                cell.setCellValue(titlesName[i]);  
            }
            XSSFRow bodyRow = sheet.createRow(2);
            for (int i = 0; i < titlesName.length; i++)  
            {  
                if(i==5){
                    cell = bodyRow.createCell(i);  
                    cell.setCellStyle(bodyStyle); 
                    cell.setCellValue(categoryPo.getCategoryCode());
                }
                if(i==6){
                    cell = bodyRow.createCell(i);  
                    cell.setCellStyle(bodyStyle); 
                    cell.setCellValue(categoryPo.getCategoryName());
                }
                for(String indexStr:indexMap.keySet()){
                    if(i==Integer.parseInt(indexStr)){
                        cell = bodyRow.createCell(i);  
                        cell.setCellStyle(bodyStyle); 
                        cell.setCellValue(indexMap.get(indexStr));
                    }
                }
            }
            try  
            {  
                workBook.write(outputStream);  
                outputStream.flush();  
                outputStream.close();  
            } finally  
            {  
                outputStream.close();  
            }
        } catch (Exception e) {
            logger.error("商品编辑模版  导出 失败！"+e.toString());
        }  
        logger.debug("商品编辑模版  导出 成功！");
        return null;
    }
    
    /**
     * 商品 导入 操作
     *
     * @param productModel
     * @return
     * @throws Exception 
     */
    @Override
    public String uploadProductAddExcel(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,Map<String,Object> map) throws Exception {
        String result = "";      
        Map<String,Object> index = new HashMap<String,Object>();
        if (list != null) {            
            int excelType=Integer.parseInt(map.get("excelType").toString()); //获取excel的类型 excel类型  1是新增excel商品信息list 2是淘宝模版 3是更新模版
            if(excelType==1){
                result = uploadImportTsh(list,listAttr,categoryPo,map,index);
            }else if(excelType==3){
                result = uploadImportUpdateTsh(list,listAttr,categoryPo,map,index);
            }else if(excelType==2){
                uploadTaobao(list, index,map,categoryPo);
            }else{
                result = "导入Excel数据入口错误";
            }
        }
        return result;
    }
    

  

    @Value("#{propertyConfigurerForProject2['product_code']}")
    private String productCode;     //商品名称
    @Value("#{propertyConfigurerForProject2['product_name']}")
    private String productName;     //商品名称
    @Value("#{propertyConfigurerForProject2['product_desc']}")
    private String productDesc;     //商品简介
    @Value("#{propertyConfigurerForProject2['brand_code']}")
    private String brandCode;       //品牌编码
    @Value("#{propertyConfigurerForProject2['brand_name']}")
    private String brandName;       //品牌名称
    @Value("#{propertyConfigurerForProject2['cat_code']}")
    private String catCode;         //分类编码
    @Value("#{propertyConfigurerForProject2['cat_name']}")
    private String catName;         //分类名称
    @Value("#{propertyConfigurerForProject2['type']}")
    private String type;            //类型
    @Value("#{propertyConfigurerForProject2['rate_price']}")
    private String ratePrice;            //加价率
    @Value("#{propertyConfigurerForProject2['supplier_id']}")
    private String supplierId;    //供应商名称
    @Value("#{propertyConfigurerForProject2['shop_id']}")
    private String shopId;        //店铺名称
    @Value("#{propertyConfigurerForProject2['sku_attr']}")
    private String skuAttr;         //销售属性
    @Value("#{propertyConfigurerForProject2['sku_attr_list']}")
    private String skuAttrList;     //SKU属性组合 
    @Value("#{propertyConfigurerForProject2['pic_list']}")
    private String picList;         //图片组合
    @Value("#{propertyConfigurerForProject2['product_info_pc']}")
    private String productInfoPc;         //商品描述PC
    @Value("#{propertyConfigurerForProject2['product_info_app']}")
    private String productInfoApp;         //商品描述APP
    @Value("#{propertyConfigurerForProject2['product_short_name']}")
    private String productShortName;    //商品别名
    @Value("#{propertyConfigurerForProject2['product_prefix']}")
    private String productPrefix;       //商品前缀
    @Value("#{propertyConfigurerForProject2['product_suffix']}")
    private String productSuffix;       //商品后缀
    @Value("#{propertyConfigurerForProject2['product_down_show']}")
    private String productDownShow;     //是否下架显示
    @Value("#{propertyConfigurerForProject2['product_auto_up']}")
    private String productAutoUp;       //是否自动上架
    @Value("#{propertyConfigurerForProject2['product_keyword']}")
    private String productKeyword;      //关键字 
    @Value("#{propertyConfigurerForProject2['operate_cat_name']}")
    private String operateCatName;      //运营分类名称
    @Value("#{propertyConfigurerForProject2['product_weight']}")
    private String productWeight;       //单品重量
    @Value("#{propertyConfigurerForProject2['product_long']}")
    private String productLong;         //（包装）长
    @Value("#{propertyConfigurerForProject2['product_width']}")
    private String productWidth;        //（包装）宽
    @Value("#{propertyConfigurerForProject2['product_height']}")
    private String productHeight;        //（包装）高
    @Value("#{propertyConfigurerForProject2['product_producer']}")
    private String productProducer;        //产地
    @Value("#{propertyConfigurerForProject2['product_density']}")
    private String productDensity;        //密度
    
    
    /**
     * 编辑导入 tsh 模版 封装
     *
     * @param productModel
     * @return
     */
    private String uploadImportUpdateTsh(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,Map<String,Object> map,Map<String,Object> index){
       
        List<String> cellList = list.get(1);
        String resultStr = "";
        for(int i=0;i<cellList.size();i++){
            String titleName =  cellList.get(i).trim();
            if(i==0){
                if(titleName.equals(InfoUtil.getStringToUtf(productCode))){
                    index.put(String.valueOf(i), InfoUtil.getStringToUtf(productCode));
                }else{
                    throw new ZhaidouRuntimeException("请上传规定excel模版！第一列必须是商品编号");
                }
            }
            if(titleName.equals(InfoUtil.getStringToUtf(productCode))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productName))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDesc))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDesc));
            }else if(titleName.equals(InfoUtil.getStringToUtf(brandCode))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(brandCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(brandName))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(brandName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(catCode))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(catCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(catName))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(catName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(type))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(type));
            }else if(titleName.equals(InfoUtil.getStringToUtf(ratePrice))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(ratePrice));
            }else if(titleName.equals(InfoUtil.getStringToUtf(supplierId))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(supplierId));
            }else if(titleName.equals(InfoUtil.getStringToUtf(shopId))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(shopId));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productInfoPc))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productInfoPc));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productInfoApp))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productInfoApp));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productShortName))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productShortName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productPrefix))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productPrefix));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productSuffix))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productSuffix));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDownShow))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDownShow));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productAutoUp))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productAutoUp));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productKeyword))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productKeyword));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productWeight))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productWeight));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productLong))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productLong));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productWidth))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productWidth));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productHeight))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productHeight));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productProducer))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productProducer));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDensity))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDensity));
            }else{
                if(listAttr!=null && listAttr.size()>0){
                    for(ProductCateAttrGroupModel attrGroupModel:listAttr){
                        if(titleName.equals(attrGroupModel.getAttrName())){
                            index.put(String.valueOf(i), attrGroupModel.getAttrName());
                        }
                    }
                }
            }
        }
        
        /*String lackField = judgeField(null,null,null,catCodeFlag,catNameFlag,supplierFlag,shopFlag);
        if(lackField!=null){
            resultStr = lackField.toString().substring(0, lackField.toString().lastIndexOf(","));
            return resultStr;
        }*/
        try {
            List<ProductModel> listProduct = uploadUpdateProductExcelUtil(list,listAttr,categoryPo,index);
            if(listProduct!=null && listProduct.size()>0){
                for(ProductModel productMod:listProduct){
                    productMod.setUpdateBy(Long.parseLong(map.get("userId").toString()));
                    productMod.setUpdateTime(new Date().getTime()/1000);
                    productMod.setUpdateUserName(map.get("userName").toString());
                }
            }
            productService.excelUpdate(listProduct);
        } catch (Exception e) {
            logger.error("",e);
            resultStr = e.getMessage();
            return e.getMessage();
        }
        return null;
    }
    
    /**
     * 新增导入 tsh 模版 封装
     *
     * @param productModel
     * @return
     */
    private String uploadImportTsh(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,Map<String,Object> map, Map<String,Object> index){
        String resultStr="";
        List<String> cellList = list.get(1);
        boolean nameFlag = false;
        boolean brandCodeFlag = false;
        boolean brandNameFlag = false;
        boolean catCodeFlag = false;
        boolean catNameFlag = false;
        boolean supplierFlag = false;
        boolean shopFlag = false;

        for(int i=0;i<cellList.size();i++){
            String titleName =  cellList.get(i).trim();
            if(titleName.equals(InfoUtil.getStringToUtf(productCode))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productName))){
                nameFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDesc))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDesc));
            }else if(titleName.equals(InfoUtil.getStringToUtf(brandCode))){
                brandCodeFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(brandCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(brandName))){
                brandNameFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(brandName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(catCode))){
                catCodeFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(catCode));
            }else if(titleName.equals(InfoUtil.getStringToUtf(catName))){
                catNameFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(catName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(type))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(type));
            }else if(titleName.equals(InfoUtil.getStringToUtf(ratePrice))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(ratePrice));
            }else if(titleName.equals(InfoUtil.getStringToUtf(supplierId))){
                supplierFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(supplierId));
            }else if(titleName.equals(InfoUtil.getStringToUtf(shopId))){
                shopFlag = true;
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(shopId));
            }else if(titleName.equals(InfoUtil.getStringToUtf(skuAttr))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(skuAttr));
            }else if(titleName.equals(InfoUtil.getStringToUtf(skuAttrList))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(skuAttrList));
            }else if(titleName.equals(InfoUtil.getStringToUtf(picList))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(picList));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productInfoPc))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productInfoPc));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productInfoApp))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productInfoApp));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productShortName))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productShortName));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productPrefix))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productPrefix));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productSuffix))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productSuffix));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDownShow))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDownShow));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productAutoUp))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productAutoUp));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productKeyword))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productKeyword));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productWeight))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productWeight));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productLong))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productLong));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productWidth))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productWidth));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productHeight))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productHeight));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productProducer))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productProducer));
            }else if(titleName.equals(InfoUtil.getStringToUtf(productDensity))){
                index.put(String.valueOf(i), InfoUtil.getStringToUtf(productDensity));
            }else{
                if(listAttr!=null && listAttr.size()>0){
                    for(ProductCateAttrGroupModel attrGroupModel:listAttr){
                        if(titleName.equals(attrGroupModel.getAttrName())){
                            index.put(String.valueOf(i), attrGroupModel.getAttrName());
                        }
                    }
                }
            }
        }
        
        String lackField = judgeField(nameFlag,brandCodeFlag,brandNameFlag,catCodeFlag,catNameFlag,supplierFlag,shopFlag);
        if(lackField!=null){
            resultStr = lackField.toString().substring(0, lackField.toString().lastIndexOf(","));
            return resultStr;
        }
        try {
            List<ProductModel> listProduct = uploadAddProductExcelUtil(list,listAttr,categoryPo,index);
            if(listProduct!=null && listProduct.size()>0){
                for(ProductModel productMod:listProduct){

                    productMod.setCreateBy(Long.parseLong(map.get("userId").toString()));
                    productMod.setCreateTime(new Date().getTime()/1000);
                    productMod.setCreateUserName(map.get("userName").toString());
                    productMod.setUpdateBy(Long.parseLong(map.get("userId").toString()));

                    productMod.setUpdateTime(new Date().getTime()/1000);
                    productMod.setUpdateUserName(map.get("userName").toString());
                }
            }
            productService.addProduct(listProduct,null);
            
            //商品插入成功后  插入商品审核表
            for(ProductModel productModel:listProduct){
                ProductInfoAuthModel productInfoAuthModel = new ProductInfoAuthModel();
                productInfoAuthModel.setCreateTime(productModel.getUpdateTime());
                productInfoAuthModel.setCreateBy(productModel.getUpdateBy());
                productInfoAuthModel.setCreateUserName(productModel.getUpdateUserName());
                productInfoAuthModel.setUpdateBy(productModel.getUpdateBy());
                productInfoAuthModel.setUpdateTime(productModel.getUpdateTime());
                productInfoAuthModel.setUpdateUserName(productModel.getUpdateUserName());
                productInfoAuthModel.setType((long)1);
                productInfoAuthModel.setStaus((long)2);
                productInfoAuthModel.setSourceType(1);
                productInfoAuthModel.setReason("后台excel导入新增无需审核");
                productInfoAuthModel.setProductCode(productModel.getProductCode());
                
                productInfoAuthService.addProductInfoAuth(productInfoAuthModel);
            }
        } catch (Exception e) {
            logger.error("",e);
            resultStr = e.getMessage();
            return e.getMessage();
        }
        return resultStr;
    }
    
    @Value("#{propertyConfigurerForProject2['taobao_product_name']}")
    private String taobaoProductName;        //宝贝名称
    @Value("#{propertyConfigurerForProject2['taobao_description']}")
    private String taobaoDescription;        //宝贝描述
    @Value("#{propertyConfigurerForProject2['taobao_pic']}")
    private String taobaoPic;        //新图片
    @Value("#{propertyConfigurerForProject2['taobao_sku_group']}")
    private String taobaoSkuGroup;        //销售属性组合
    @Value("#{propertyConfigurerForProject2['taobao_group_alias']}")
    private String taobaoGroupAlias;        //销售属性别名
    @Value("#{propertyConfigurerForProject2['taobao_cpv_memo']}")
    private String taobaoCpvMemo;        //属性值备注
    @Value("#{propertyConfigurerForProject2['taobao_input_custom_cpv']}")
    private String taobaoInputCustomCpv;        //自定义属性值
    @Value("#{propertyConfigurerForProject2['taobao_cost_price']}")
    private String taobaoCostPrice;        //供货价格
    
    /**
     * 将淘宝excel转成model list
     *
     * @param map 
     * @param categoryPo 
     * @param productModel
     * @return
     * @throws Exception 
     */
    private String uploadTaobao(List<List<String>> list,Map<String,Object> index, Map<String, Object> map, CategoryPO categoryPo) throws Exception{
        String columNametaobaoProductName=InfoUtil.getStringToUtf(taobaoProductName);
        String columNametaobaoDescription=InfoUtil.getStringToUtf(taobaoDescription);
        String columNametaobaoCostPrice=InfoUtil.getStringToUtf(taobaoCostPrice);
        String columNametaobaoPic=InfoUtil.getStringToUtf(taobaoPic);
        String columNametaobaoSkuGroup=InfoUtil.getStringToUtf(taobaoSkuGroup);
        String columNametaobaoGroupAlias=InfoUtil.getStringToUtf(taobaoGroupAlias);
        String columNametaobaoCpvMemo=InfoUtil.getStringToUtf(taobaoCpvMemo);
        String columNametaobaoInputCustomCpv=InfoUtil.getStringToUtf(taobaoInputCustomCpv);
        List<String> cellList = null;
        cellList = list.get(2);//得到淘宝excel的中文列标题 的行
        for(int i=0;i<cellList.size();i++){
            String titleName =  cellList.get(i).trim();         
            if(titleName.equals(columNametaobaoProductName)){
                index.put(String.valueOf(i), columNametaobaoProductName);
            }
            else if(titleName.equals(columNametaobaoDescription)){
                index.put(String.valueOf(i), columNametaobaoDescription);
            }
            else if(titleName.equals(columNametaobaoCostPrice)){
                index.put(String.valueOf(i), columNametaobaoCostPrice);
            }
            else if(titleName.equals(columNametaobaoPic)){
                index.put(String.valueOf(i), columNametaobaoPic);
            }
            else if(titleName.equals(columNametaobaoSkuGroup)){
                index.put(String.valueOf(i),columNametaobaoSkuGroup);
            }
            else if(titleName.equals(columNametaobaoGroupAlias)){
                index.put(String.valueOf(i), columNametaobaoGroupAlias);
            }
            else if(titleName.equals(columNametaobaoCpvMemo)){
                index.put(String.valueOf(i), columNametaobaoCpvMemo);
            }
            else if(titleName.equals(columNametaobaoInputCustomCpv)){
                index.put(String.valueOf(i), columNametaobaoInputCustomCpv);
            }
        } 
        List<TaoBaoExcelModel> taoBaoExcelModelList=new ArrayList<TaoBaoExcelModel>();
        for (int i = 3; i < list.size(); i++) {
            cellList = list.get(i);
            TaoBaoExcelModel taoBaoExcelModel=new TaoBaoExcelModel();
            taoBaoExcelModel.setRownum(i+1);
            for (int j = 0; j < cellList.size(); j++) {               
                for (String key : index.keySet()) {
                    if (j == Integer.parseInt(key) && cellList!=null && cellList.get(j)!=null && !cellList.get(j).equals("")) {
                        if (index.get(key).equals(columNametaobaoProductName)) {
                            taoBaoExcelModel.setTaobaoProductName(cellList.get(j));

                        } else if (index.get(key).equals(columNametaobaoDescription)) {
                            taoBaoExcelModel.setTaobaoDescription(cellList.get(j));

                        } else if (index.get(key).equals(columNametaobaoCostPrice)) {
                            taoBaoExcelModel.setTaobaoCostPrice(cellList.get(j));

                        } else if (index.get(key).equals(columNametaobaoPic)) {
                            taoBaoExcelModel.setTaobaoPic(cellList.get(j));
                            
                        } else if (index.get(key).equals(columNametaobaoSkuGroup)) {
                            taoBaoExcelModel.setTaobaoSkuGroup(cellList.get(j));
                            
                        } else if(index.get(key).equals(columNametaobaoGroupAlias)){
                            taoBaoExcelModel.setTaobaoGroupAlias(cellList.get(j));
                            
                        }else if (index.get(key).equals(columNametaobaoCpvMemo)) {
                            taoBaoExcelModel.setTaobaoCpvMemo(cellList.get(j));

                        } else if (index.get(key).equals(columNametaobaoInputCustomCpv)) {
                            taoBaoExcelModel.setTaobaoInputCustomCpv(columNametaobaoInputCustomCpv);
                        }
                    }
                }
            }
           
            if (taoBaoExcelModel.getTaobaoCostPrice() == null && taoBaoExcelModel.getTaobaoCpvMemo() == null
                && taoBaoExcelModel.getTaobaoDescription() == null && taoBaoExcelModel.getTaobaoGroupAlias() == null
                && taoBaoExcelModel.getTaobaoInputCustomCpv() == null && taoBaoExcelModel.getTaobaoPic() == null
                && taoBaoExcelModel.getTaobaoProductName() == null && taoBaoExcelModel.getTaobaoSkuGroup() == null) // 如果对象所有属性都是空值
                                                                                                                // 就不添加
            {
                uploadTaobaoModellist(taoBaoExcelModelList,map,categoryPo);
                return null;

            }
            taoBaoExcelModelList.add(taoBaoExcelModel);
           
        }
       // uploadTaobaoAdd(list,index);
        uploadTaobaoModellist(taoBaoExcelModelList,map,categoryPo);
        return null;
    }
    
    private void uploadTaobaoModellist(
            List<TaoBaoExcelModel> taoBaoExcelModelList,Map<String, Object> map ,
            CategoryPO categoryPo) throws Exception {

        ProductModel productModel = null;

        List<ProductImageModel> imageList = null;

        List<ProductSkuModel> skuList = null;

        List<ProductModel> productModelList = new ArrayList<ProductModel>();
        for (TaoBaoExcelModel taoBaoExcelModel : taoBaoExcelModelList) {
            imageList = new ArrayList<ProductImageModel>();
            ProductMallModel productMallModel = new ProductMallModel();
            ProductOperateModel productOperateModel = new ProductOperateModel();
            ProductInfoModel productInfoModel = new ProductInfoModel();
            ProductStockModel productStockModel = new ProductStockModel();

            skuList = new ArrayList<ProductSkuModel>();
            productModel = new ProductModel();
            // 验证产品名称长度不能大于50 和非空
            if (taoBaoExcelModel.getTaobaoProductName() == null
                    || taoBaoExcelModel.getTaobaoProductName().equals("")) {
                logger.error("第" + taoBaoExcelModel.getRownum()
                        + "行,导入商品名称不能为空,请确认！");
                throw new ZhaidouRuntimeException("第"
                        + taoBaoExcelModel.getRownum() + "行,导入商品名称不能为空,请确认！");

            } else if (taoBaoExcelModel.getTaobaoProductName().toString()
                    .length() > 50) {
                logger.error("第" + taoBaoExcelModel.getRownum()
                        + "行,导入商品名称长度过长,请确认！长度不能大于50个中文。");
                throw new ZhaidouRuntimeException("第"
                        + taoBaoExcelModel.getRownum()
                        + "行,导入商品名称长度过长,请确认！长度不能大于50个中文。");
            } else {
                productModel.setProductName(taoBaoExcelModel
                        .getTaobaoProductName());

            }

            if (taoBaoExcelModel.getTaobaoDescription() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoDescription())) {
                productInfoModel.setPcProductInfo(taoBaoExcelModel
                        .getTaobaoDescription());
                productInfoModel.setAppProductInfo(taoBaoExcelModel
                        .getTaobaoDescription());
            }

            if (taoBaoExcelModel.getTaobaoCostPrice() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoCostPrice())) {
                try {
                    productModel
                            .setCostPrice(Double.parseDouble(taoBaoExcelModel
                                    .getTaobaoCostPrice()));
                } catch (Exception e) {
                    logger.error("供货价不是有效数字", e);
                    throw new ZhaidouRuntimeException("第"
                            + taoBaoExcelModel.getRownum() + "行,商品供货价格不是有效数字,请确认！");
                }
            } else {
                logger.error("第" + taoBaoExcelModel.getRownum()
                        + "行,商品供货价格不能为空,请确认！");
                throw new ZhaidouRuntimeException("第"
                        + taoBaoExcelModel.getRownum() + "行,商品供货价格不能为空,请确认！");

            }

            // 淘宝 新图片
            if (taoBaoExcelModel.getTaobaoPic() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoPic())) {
                String[] array = taoBaoExcelModel.getTaobaoPic().split(";");
                for (String onePic : array) {
                    ProductImageModel productImageModel = new ProductImageModel();
                    String[] arrayAttr = onePic.split("http");
                    String[] arrayAttr1 = arrayAttr[0].split(":");
                    arrayAttr1[arrayAttr1.length - 1] = arrayAttr1[arrayAttr1.length - 1]
                            .substring(
                                    0,
                                    arrayAttr1[arrayAttr1.length - 1].length() - 1);
                    String colorCode = "";
                    // 27e4673453fa04a75fefc6e9bea03c6c, 1, 0,
                    if (arrayAttr1.length == 4) {
                        for (int y = 0; y < arrayAttr1.length; y++) {
                            if (y == 0) {
                                if (arrayAttr1[y] != null
                                        && !"".equals(arrayAttr1[y])) {
                                    productImageModel.setImage(arrayAttr1[y]);
                                } else {
                                    break;
                                }
                            } else if (y == 2) {
                                if (arrayAttr1[y] != null
                                        && !"".equals(arrayAttr1[y])) {
                                    productImageModel.setLevel(Long
                                            .parseLong(arrayAttr1[y]));
                                }
                            }
                        }
                        imageList.add(productImageModel);

                    } else {
                        for (int y = 0; y < arrayAttr1.length; y++) {
                            if (y == 0) {
                                if (arrayAttr1[y] != null
                                        && !"".equals(arrayAttr1[y])) {
                                    productImageModel.setImage(arrayAttr1[y]);
                                } else {
                                    break;
                                }
                            } else if (y == 2) {
                                if (arrayAttr1[y] != null
                                        && !"".equals(arrayAttr1[y])) {
                                    productImageModel.setLevel(Long
                                            .parseLong(arrayAttr1[y]));
                                }
                            } else if (y == 4) {
                                if (arrayAttr1[y] != null
                                        && !"".equals(arrayAttr1[y].trim())) {
                                    colorCode = arrayAttr1[y - 1] + ":"
                                            + arrayAttr1[y];
                                }
                                productImageModel.setProductSkuCode(colorCode);
                            }
                        }
                        imageList.add(productImageModel);

                    }
                }
            }

            if (taoBaoExcelModel.getTaobaoCostPrice() == null
                    || taoBaoExcelModel.getTaobaoCostPrice() == "") {
                logger.error("第" + taoBaoExcelModel.getRownum()
                        + "行,供货价格不能为空,请确认是否有供货价格！");
                throw new ZhaidouRuntimeException("第"
                        + taoBaoExcelModel.getRownum()
                        + "行,供货价格不能为空,请确认是否有供货价格！");

            }
            // 688:132:ttt0041:1627207:3232478;20549:672896047;
            // 淘宝 SKU 销售属性组合
            if (taoBaoExcelModel.getTaobaoSkuGroup() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoSkuGroup())) {
                taobaoSkuGroupChecked(taoBaoExcelModel.getTaobaoSkuGroup(),
                        Double.parseDouble(taoBaoExcelModel
                                .getTaobaoCostPrice()), skuList,
                        taoBaoExcelModel.getRownum(), categoryPo);
            } else {
                logger.error("第" + taoBaoExcelModel.getRownum()
                        + "行,销售属性组合不能为空,请确认是否有销售属性组合！");
                /*
                 * throw new TeshehuiRuntimeException("第" +
                 * taoBaoExcelModel.getRownum() + "行,销售属性组合不能为空,请确认是否有销售属性组合！");
                 */
            }

            if (skuList.size() == 0) {
                // 如果没有销售属性组合 则初始化一个主SKU
                ProductSkuModel skuModel = new ProductSkuModel();
                skuModel.setAttrColorName("颜色");
                skuModel.setAttrSpecName("尺码");
                skuModel.setAttrColorValue("无");
                skuModel.setAttrSpecValue("无");
                skuModel.setColorValueAlias("无");
                skuModel.setSpecValueAlias("无");
                skuModel.setMainSku(1l);
                skuModel.setIsAvailable(1);

                ProductPriceModel priceModel = new ProductPriceModel();
                priceModel.setCostPrice(productModel.getCostPrice());
                java.text.DecimalFormat df = new java.text.DecimalFormat(
                        "#0.##");
                priceModel.setTshPrice(Double.parseDouble(df.format(priceModel
                        .getCostPrice() * (1 + 10 / 100))));
                priceModel.setMarketPrice(0d);
                priceModel.setTb(0l);

                ProductStockModel stockModel = new ProductStockModel();
                stockModel.setEntityStock(0d);
                List<ProductImageModel> skuImagList = new ArrayList<ProductImageModel>();

                skuModel.setProductPriceModel(priceModel);
                skuModel.setProductStockModel(stockModel);
                skuModel.setProductImagerList(skuImagList);
                skuList.add(skuModel);
            }

            // 找到SKU 对应的图片
            List<ProductImageModel> imageList1 = null; // 非主sku
            List<ProductImageModel> imageList2 = new ArrayList<ProductImageModel>();// 主sku
                                                                                    // image
            if (skuList.size() > 0 && imageList.size() > 0) {
                for (ProductImageModel image : imageList) {
                    if (image.getProductSkuCode() != null) {
                        imageList1 = new ArrayList<ProductImageModel>();
                        for (ProductSkuModel sku : skuList) {
                            if (sku.getAttrColorValue().equals(
                                    image.getProductSkuCode())) {
                                imageList1.add(image);
                                sku.setProductImagerList(imageList1);
                            }
                        }
                    } else {
                        imageList2.add(image);
                    }
                }
            }

            if (skuList != null && skuList.size() > 0) {
                if (skuList.get(0).getProductImagerList() != null
                        && skuList.get(0).getProductImagerList().size() > 0) {
                    for (ProductImageModel image : skuList.get(0)
                            .getProductImagerList()) {
                        imageList2.add(image);
                    }
                }
                skuList.get(0).setProductImagerList(imageList2);// skuList.get(0)
                                                                // 主SKU
                                                                // 默认第一个是主sku
                skuList.get(0).setMainSku((long) 1);
            }

            // set 销售属性值
            if (taoBaoExcelModel.getTaobaoGroupAlias() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoGroupAlias())) {
                // 1627207:28332:8-9mm 43厘米;1627207:3232481:9-10mm 43厘米;
                String[] ww = taoBaoExcelModel.getTaobaoGroupAlias().split(";");

                for (ProductSkuModel sku : skuList) {
                    String colorKey = sku.getAttrColorValue();
                    String specKey = sku.getAttrSpecValue();
                    boolean colorFlag = false;
                    boolean specFlag = false;
                    for (String array : ww) {
                        String[] hh = array.split(":");

                        if (hh.length == 3) {
                            String skuKey = hh[0] + ":" + hh[1];
                            if (!colorFlag) {
                                if (skuKey.equals(colorKey)) {
                                    sku.setAttrColorValue(hh[2]);
                                    sku.setColorValueAlias(hh[2]);
                                    colorFlag = true;
                                } else {
                                    sku.setAttrColorValue("无");
                                    sku.setColorValueAlias("无");
                                }
                            }
                            if (!specFlag) {
                                if (skuKey.equals(specKey)) {
                                    sku.setAttrSpecValue(hh[2]);
                                    sku.setSpecValueAlias(hh[2]);
                                    specFlag = true;
                                } else {
                                    sku.setAttrSpecValue("无");
                                    sku.setSpecValueAlias("无");
                                }
                            }
                        }
                    }
                }
            }

            // 淘宝 属性值备注
            // 1627207:3232478:深灰色1
            if (taoBaoExcelModel.getTaobaoCpvMemo() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoCpvMemo())) {
                String[] array = taoBaoExcelModel.getTaobaoCpvMemo().split(";");
                for (String oneArray : array) {
                    String[] arrayAttr = oneArray.split(":");
                    if (arrayAttr.length == 3) {
                        String skuKey = arrayAttr[0] + ":" + arrayAttr[1];
                        for (ProductSkuModel sku : skuList) {
                            if (skuKey.equals(sku.getAttrColorValue())) {
                                sku.setAttrColorValue(arrayAttr[2]);
                            } else {
                                sku.setAttrColorValue("无");
                                sku.setColorValueAlias("无");
                            }
                            if (skuKey.equals(sku.getAttrSpecValue())) {
                                sku.setAttrSpecValue(arrayAttr[2]);
                            } else {
                                sku.setAttrSpecValue("无");
                                sku.setSpecValueAlias("无");
                            }
                        }
                    }
                }
            }

            // 淘宝 自定义属性值
            // 1627207:914828945:xxx色;
            if (taoBaoExcelModel.getTaobaoInputCustomCpv() != null
                    && !"".equals(taoBaoExcelModel.getTaobaoInputCustomCpv())) {
                String[] array = taoBaoExcelModel.getTaobaoInputCustomCpv()
                        .split(";");
                for (String oneArray : array) {
                    String[] arrayAttr = oneArray.split(":");
                    if (arrayAttr.length == 3) {
                        String skuKey = arrayAttr[0] + ":" + arrayAttr[1];
                        for (ProductSkuModel sku : skuList) {
                            if (skuKey.equals(sku.getAttrColorValue())) {
                                sku.setAttrColorValue(arrayAttr[2]);
                            } else {
                                sku.setAttrColorValue("无");
                                sku.setColorValueAlias("无");
                            }
                            if (skuKey.equals(sku.getAttrSpecValue())) {
                                sku.setAttrSpecValue(arrayAttr[2]);
                            } else {
                                sku.setAttrSpecValue("无");
                                sku.setSpecValueAlias("无");
                            }
                        }
                    }
                }
            }

            productModel.setProductInfoModel(productInfoModel);

            for (ProductSkuModel sku : skuList) {

                if (sku.getAttrColorValue() == null) {
                    sku.setAttrColorValue("其他");
                }
                if (sku.getAttrSpecValue() == null) {
                    sku.setAttrSpecValue("其他");
                }

                if (sku.getMainSku() == null) {
                    sku.setMainSku((long) 0);

                }

            }
            
            productOperateModel.setProductDownShow(2l);
            
            productModel.setProductSkuList(skuList);
            productModel.setProductMallModel(productMallModel);
            productModel.setProductOperateModel(productOperateModel);

            productModel.setCatCode(categoryPo.getCategoryCode());
            productModel.setCatName(categoryPo.getCategoryName());

            ProductModel productMod = new ProductModel();
            productManager.getProductCode(productMod);

            String str = "";
            if (categoryPo.getCategoryCode().length() == 2) {
                str = "00" + categoryPo.getCategoryCode();
            } else {
                str = categoryPo.getCategoryCode().substring(0, 4);
            }
            String code = BarCodeUtils.buildBarCode(str,
                    String.valueOf(productMod.getProductId()));

            productModel.setProductCode(code);
            productModel.setSupplierId(1l);
            productModel.setShopId(1l);
            productModelList.add(productModel);
        }
        for (ProductModel product : productModelList) {
            productManager.addProduct(null,product,map,null);
        }
    }
    
//    /**
//     * taobao 导入验证 封装  王航版本 作废
//     *
//     * @param productModel
//     * @return
//     */
//    private List<ProductModel> uploadTaobaoAdd(List<List<String>> list,Map<String,Object> index){
//        
//        ProductModel productModel = null;
//        
//        productMallModel = new ProductMallModel();
//        productOperateModel = new ProductOperateModel();
//        productInfoModel = new ProductInfoModel();
//        productStockModel = new ProductStockModel();
//        List<ProductImageModel> imageList = new ArrayList<ProductImageModel>();
//        
//        List<ProductSkuModel> skuList = new ArrayList<ProductSkuModel>();
//        
//        
//        if(index.size()>0 && list.size()>1){
//            for(int i=3;i<list.size();i++){ //从第三行开始
//                List<String> cellList = list.get(i);
//                
//                productModel = new ProductModel();
//                Double costPrice = 0d;
//                for(int j=0;j<cellList.size();j++){
//                    
//                    for(String key:index.keySet()){
//                        
//                        if(j==Integer.parseInt(key)){
//                            if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoProductName))){
//                                //淘宝 商品名称
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    if(cellList.get(j).length()<50){
//                                        productModel.setProductName(cellList.get(j));
//                                    }else{
//                                        throw new TeshehuiRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,导入商品名称长度过长,请确认！");
//                                    }
//                                }else{
//                                    throw new TeshehuiRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,导入商品名称不能为空,请确认！");
//                                }
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoDescription))){
//                                //淘宝 商品描述
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    productInfoModel.setPcProductInfo(cellList.get(j));
//                                }
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoCostPrice))){
//                                //淘宝 供货价格
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    costPrice = Double.parseDouble(cellList.get(j));
//                                }else{
//                                    throw new TeshehuiRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品供货价格不能为空,请确认！");
//                                }
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoPic))){
//                                //淘宝 新图片
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    String[] array = cellList.get(j).split(";");
//                                    for(int x=0;x<array.length;x++){
//                                        productImageModel = new ProductImageModel();
//                                        String[] arrayAttr = array[x].split("|");
//                                        String[] arrayAttr1 = arrayAttr[0].split(":");
//                                        String colorCode = "";
//                                        for(int y=0;y<arrayAttr1.length;y++){
//                                            if(y==0){
//                                                if(arrayAttr[y]!=null && !"".equals(arrayAttr[y])){
//                                                    productImageModel.setImage(arrayAttr[y]);
//                                                }else{
//                                                    throw new TeshehuiRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品SKU库存不能为空,请确认！");
//                                                }
//                                            }else if(y==2){
//                                                if(arrayAttr[y]!=null && !"".equals(arrayAttr[y])){
//                                                    productImageModel.setLevel(Long.parseLong(arrayAttr[y]));
//                                                }
//                                            }else if(y==3){
//                                                if(arrayAttr[y]!=null && !"".equals(arrayAttr[y])){
//                                                    colorCode=arrayAttr[y];
//                                                }
//                                            }else if(y==4){
//                                                if(arrayAttr[y]!=null && !"".equals(arrayAttr[y])){
//                                                    colorCode=colorCode+":"+arrayAttr[y];
//                                                }
//                                                productImageModel.setProductSkuCode(colorCode);
//                                            }
//                                        }
//                                        imageList.add(productImageModel); 
//                                    }
//                                }
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoSkuGroup))){
//                                //688:132:ttt0041:1627207:3232478;20549:672896047;
//                                //淘宝 SKU 销售属性组合
//                                taobaoSkuGroupChecked(cellList.get(j),costPrice,skuList);
//                                
//                                //找到SKU 对应的图片
//                                List<ProductImageModel> listImage = null;
//                                List<ProductImageModel> listImage1 = new ArrayList<ProductImageModel>();
//                                if(skuList.size()>0 && imageList.size()>0){
//                                    for(ProductSkuModel sku:skuList){
//                                        listImage = new ArrayList<ProductImageModel>();
//                                        for(ProductImageModel image:imageList){
//                                            if(image.getProductSkuCode()!=null){
//                                                if(sku.getAttrColorValue().equals(image.getProductSkuCode())){
//                                                    listImage.add(image);
//                                                }
//                                            }else{
//                                                listImage1.add(image);
//                                            }
//                                           
//                                        }
//                                        sku.setProductImagerList(listImage);
//                                    }
//                                }
//                                if(skuList.get(0).getProductImagerList()!=null && skuList.get(0).getProductImagerList().size()>0){
//                                    for(ProductImageModel image:skuList.get(0).getProductImagerList()){
//                                        listImage1.add(image);
//                                    }
//                                }
//                                skuList.get(0).setProductImagerList(listImage1);
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoCpvMemo))){
//                                //淘宝 属性值备注
////                                1627207:3232478:深灰色1
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    String[] array = cellList.get(j).split(";");
//                                    for(int x=0;x<array.length;x++){
//                                        String[] arrayAttr = array[x].split(":");
//                                        if(arrayAttr.length==3){
//                                            String skuKey = arrayAttr[0]+":"+arrayAttr[1];
//                                            for(ProductSkuModel sku:skuList){
//                                                if(skuKey.equals(sku.getAttrColorValue())){
//                                                    sku.setAttrColorValue(arrayAttr[2]);
//                                                }
//                                                if(skuKey.equals(sku.getAttrSpecValue())){
//                                                    sku.setAttrSpecValue(arrayAttr[2]);
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }else if(index.get(key).equals(InfoUtil.getStringToUtf(taobaoInputCustomCpv))){
//                                // 淘宝 自定义属性值
////                                1627207:914828945:xxx色;
//                                if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
//                                    String[] array = cellList.get(j).split(";");
//                                    for(int x=0;x<array.length;x++){
//                                        String[] arrayAttr = array[x].split(":");
//                                        if(arrayAttr.length==3){
//                                            String skuKey = arrayAttr[0]+":"+arrayAttr[1];
//                                            for(ProductSkuModel sku:skuList){
//                                                if(skuKey.equals(sku.getAttrColorValue())){
//                                                    sku.setAttrColorValue(arrayAttr[2]);
//                                                }
//                                                if(skuKey.equals(sku.getAttrSpecValue())){
//                                                    sku.setAttrSpecValue(arrayAttr[2]);
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }
//    
    
    /**
     * tsh 编辑导入验证 封装
     *
     * @param productModel
     * @return
     */
    private List<ProductModel> uploadUpdateProductExcelUtil(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo,Map<String,Object> index){
        List<ProductModel> listProductModel = new ArrayList<ProductModel>();
        ProductModel productModel = null;
        ProductModel oldProduct = null;
        ProductBrandModel brandModel = null;
        
        int catFlag = 0;
        if(index.size()>0 && list.size()>1){
            for(int i=2;i<list.size();i++){
                 List<String> cellList = list.get(i);
                 List<ProductAttributeModel> listAttribute = new ArrayList<ProductAttributeModel>();
                 ProductMallModel productMallModel = new ProductMallModel();
                 ProductOperateModel productOperateModel = new ProductOperateModel();
                 ProductInfoModel productInfoModel = new ProductInfoModel();
                 
                 productModel = new ProductModel();
                 
                 for(int j=0;j<cellList.size();j++){
                     
                     for(String key:index.keySet()){
                         
                         if(j==Integer.parseInt(key)){
                             if(index.get(key).equals(InfoUtil.getStringToUtf(productCode))){
                                 
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     oldProduct = productService.getProductByCode(cellList.get(j));
                                     if(oldProduct==null){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,导入商品编号不存在,请确认！");
                                     }
                                     productModel.setProductId(oldProduct.getProductId());
                                     productModel.setProductCode(cellList.get(j));
                                     productMallModel.setProductId(oldProduct.getProductId());
                                     productOperateModel.setProductId(oldProduct.getProductId());
                                     productInfoModel.setProductId(oldProduct.getProductId());
                                 }else{
                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,导入商品编号不能为空,请确认！");
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productName))){
                                 //验证 商品名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     productModel.setProductName(cellList.get(j));
                                 } 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productDesc))){
                                 //验证 商品简介
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     productModel.setProductDesc(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(brandCode))){
                                 //验证 品牌Code
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     try {
                                         brandModel = productBrandService.getEnableBrandByCode(cellList.get(j));
                                     } catch (Exception e) {
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,品牌系统异常！");
                                     }
                                     if(brandModel==null){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌编码不存在,请确认！"+cellList.get(j));
                                     }else{
                                         productModel.setBrandCode(brandModel.getBrandCode());
                                         productModel.setBrandName(brandModel.getBrandName());
                                     }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(brandName))){
                                 //验证 品牌名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(brandModel==null){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌名称不存在,请确认！"+cellList.get(j));
                                     }else{
                                         if(!brandModel.getBrandName().trim().equals(cellList.get(j).trim())){
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌名称与填写的品牌编码不对应或不存在,请确认！"+cellList.get(j));
                                         }
                                         productModel.setBrandName(cellList.get(j).trim());
                                     }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(catCode))){
                                 //验证 分类编号
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     
                                     if(cellList.get(j).equals(categoryPo.getCategoryCode())){
                                         catFlag = 1;
                                     }else{
                                         catFlag = 2;
                                     }
                                     productModel.setCatCode(categoryPo.getCategoryCode());
                                     
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(catName))){
                                 //验证 分类名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     productModel.setCatName(categoryPo.getCategoryName());
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(type))){
                                 //验证 商品类型
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(ProductType.PRODUCTTYPE_ENUM.getInstanceKey(cellList.get(j))!=null){
                                         productModel.setType(ProductType.PRODUCTTYPE_ENUM.getInstanceKey(cellList.get(j)).getKey());
                                     }else{
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品类型不正确或不存在,请确认！"+cellList.get(j));
                                     }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(ratePrice))){
                                 //验证 加价率
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     try {
                                         Double priceRate = Double.parseDouble(cellList.get(j));
                                         if(priceRate<0 || priceRate>100){
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,加价率取值0-100之间,请确认！"+cellList.get(j));
                                         }
                                         productOperateModel.setProductPriceRate(priceRate); 
                                    } catch (NumberFormatException e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,加价率不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(supplierId))){
                                 //验证 供应商编号
//                                 try {
//                                    if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
//                                        if(oldProduct.getSupplierId().longValue()!=Long.parseLong(cellList.get(j))){
//                                            throw new ZhaidouRuntimeException("第"+(i+1)+"行,,商品不在该供应商编号下,不能修改,请确认！");
//                                        }else{
//                                            productModel.setSupplierId(Long.parseLong(cellList.get(j)));
//                                        }
//                                     }
//                                } catch (NumberFormatException e) {
//                                    throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,供应商编号类型错误,请确认！"+cellList.get(j));
//                                }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(shopId))){
                                 //验证 店铺编号
//                                 try {
//                                     if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
//                                         if(oldProduct.getShopId().longValue()!=Long.parseLong(cellList.get(j))){
//                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,,商品不在该店铺编号下,不能修改,请确认！");
//                                         }else{
//                                             productModel.setShopId(Long.parseLong(cellList.get(j)));
//                                         }
//                                      }
//                                 } catch (NumberFormatException e) {
//                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,店铺编号类型错误,请确认！"+cellList.get(j));
//                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productInfoPc))){
                                 //商品描述 PC
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productInfoModel.setPcProductInfo(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productInfoApp))){
                                 //商品描述 APP
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productInfoModel.setAppProductInfo(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productShortName))){
                                 //验证 商品别名
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productOperateModel.setProductShortName(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productPrefix))){
                                 //验证 商品前缀
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productOperateModel.setProductPrefix(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productSuffix))){
                                 //验证 商品后缀
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productOperateModel.setProductSuffix(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productKeyword))){
                                 //验证 关键字
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productOperateModel.setProductKeyword(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productWeight))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductWeight(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该单品重量 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productLong))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductLong(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)长 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productWidth))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductWidth(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)宽 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productHeight))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductHeight(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)高 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productProducer))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     productMallModel.setProductProducer(cellList.get(j));
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productDensity))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductDensity(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该商品密度 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else{
                                 //验证 弹性域属性
                                 if(catFlag>0){
                                     if(catFlag==1){
                                         checkAttributeList(cellList.get(j),key,listAttr,listAttribute,index);
                                     }
                                 }
                             }
                         }
                     }
                 }
                 productModel.setProductInfoModel(productInfoModel);
                 productModel.setProductOperateModel(productOperateModel);
                 productModel.setProductAttributeList(listAttribute);
                 listProductModel.add(productModel);
            }
        }
        index.clear();
        return listProductModel;
    }
    
    /**
     * tsh 新增导入验证 封装
     *
     * @param productModel
     * @return
     */
    private List<ProductModel> uploadAddProductExcelUtil(List<List<String>> list,List<ProductCateAttrGroupModel> listAttr,CategoryPO categoryPo, Map<String,Object> index){
        List<ProductModel> listProductModel = new ArrayList<ProductModel>();
        ProductModel productModel = null;
        
        ProductBrandModel brandModel = null;
        SupplierModel supplierModel = null;
        
        String[] colorValueArray = null;
        String[] sizeValueArray = null;
        if(index.size()>0 && list.size()>1){
            for(int i=2;i<list.size();i++){
                 List<String> cellList = list.get(i);
                 
                 List<ProductSkuModel> listSku = new ArrayList<ProductSkuModel>();
                 List<ProductImageModel> listImage = new ArrayList<ProductImageModel>();
                 List<ProductAttributeModel> listAttribute = new ArrayList<ProductAttributeModel>();
                 productModel = new ProductModel();
                 ProductOperateModel productOperateModel = new ProductOperateModel();
                 ProductInfoModel productInfoModel = new ProductInfoModel();
                 ProductMallModel productMallModel = new ProductMallModel();
                 
                 String productCode="";
                try {
                    ProductModel productMod = new ProductModel();
                     productService.getProductCode(productMod);
                     String prefix = "";
                     if(categoryPo.getCategoryCode().length()==2){
                         prefix = "00"+categoryPo.getCategoryCode();
                     }else{
                         prefix = categoryPo.getCategoryCode().substring(0, 4);
                     }
                     productCode = BarCodeUtils.buildBarCode(prefix, String.valueOf(productMod.getProductId()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new ZhaidouRuntimeException("生成商品编号失败,请稍等！");
                }
                 
                 for(int j=0;j<cellList.size();j++){
                     
                     for(String key:index.keySet()){
                         
                         if(j==Integer.parseInt(key)){
                             
                             if(index.get(key).equals(InfoUtil.getStringToUtf(productName))){
                                 //验证 商品名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(cellList.get(j).length()>50){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品名称长度不能大于50个字符,请确认！");
                                     }
                                     productModel.setProductName(cellList.get(j));
                                 }else{
                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,导入商品名称不能为空,请确认！");
                                 } 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productDesc))){
                               //验证 商品简介
                                 productModel.setProductDesc(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(brandCode))){
                                 //验证 品牌Code
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     try {
                                         brandModel = productBrandService.getEnableBrandByCode(cellList.get(j));
                                         if(brandModel==null){
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌编码不存在,请确认！"+cellList.get(j));
                                         }else{
                                             productModel.setBrandCode(brandModel.getBrandCode());
                                         }
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,品牌系统异常！");
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(brandName))){
                                 //验证 品牌名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(brandModel==null){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌名称不存在,请确认！"+cellList.get(j));
                                     }else{
                                         if(!brandModel.getBrandName().trim().equals(cellList.get(j).trim())){
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该品牌名称与填写的品牌编码不对应或不存在,请确认！"+cellList.get(j));
                                         }
                                         productModel.setBrandName(brandModel.getBrandName());
                                     }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(catCode))){
                                 //验证 分类编号
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(cellList.get(j).trim().length()==6){
                                         if(categoryPo.getCategoryCode().trim().equals(cellList.get(j).trim())){
                                             productModel.setCatCode(categoryPo.getCategoryCode());
                                         }else{
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,分类编号与所选分类不一致,请确认！");
                                         }
                                     }else{
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,需选择三级类目,请确认！");
                                     }
                                 }else{
                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,类目不能为空,请确认！");
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(catName))){
                                 //验证 分类名称
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(categoryPo.getCategoryName().trim().equals(cellList.get(j).trim())){
                                         productModel.setCatName(categoryPo.getCategoryName());
                                     }else{
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,分类名称与所选分类不一致,请确认！");
                                     }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(type))){
                                 //验证 商品类型
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     if(ProductType.PRODUCTTYPE_ENUM.getInstanceKey(cellList.get(j))!=null){
                                         productModel.setType(ProductType.PRODUCTTYPE_ENUM.getInstanceKey(cellList.get(j)).getKey());
                                     }else{
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品类型不正确或不存在,请确认！"+cellList.get(j));
                                     }
                                 }else{
                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,商品类型不能为空,请确认！"+cellList.get(j));
                                 }
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(ratePrice))){
                                 //验证 加价率
                                 if(cellList.get(j) !=null && !"".equals(cellList.get(j))){
                                     try {
                                         Double priceRate = Double.parseDouble(cellList.get(j));
                                         if(priceRate<0 || priceRate>100){
                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,加价率取值0-100之间,请确认！"+cellList.get(j));
                                         }
                                         productOperateModel.setProductPriceRate(priceRate);
                                    } catch (NumberFormatException e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,加价率不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }else{
                                     productOperateModel.setProductPriceRate(10); 
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(supplierId))){
                                 //验证 供应商编号
//                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
//                                     supplierModel = new SupplierModel();
//                                     supplierModel.setId(Long.parseLong(cellList.get(j)));
//                                     try {
//                                         supplierModel = supplierService.getSupplier(supplierModel);
//                                     } catch (Exception e) {
//                                         logger.error(""+e.getStackTrace());
//                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,获取供应商错误！");
//                                     }
//                                     if(supplierModel!=null){
//                                         productModel.setSupplierId(supplierModel.getId());
//                                     }else{
//                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该供应商编号不存在,请确认！"+cellList.get(j));
//                                     }
//                                    }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(shopId))){
                                 //验证 店铺编号
//                                 try {
//                                     if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
//                                         Long shopId = Long.parseLong(cellList.get(j));
//                                         if(supplierModel.getShopId().longValue()!=shopId.longValue()){
//                                             throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,店铺编号与供应商编号不对应,请确认！"+cellList.get(j));
//                                         }
//                                         productModel.setShopId(shopId);
//                                      }
//                                 } catch (NumberFormatException e) {
//                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,店铺编号类型错误,请确认！"+cellList.get(j));
//                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(skuAttr))){
                               //验证 销售属性
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     String skuAttrValue = cellList.get(j);
                                     String[] skuAttrArray = skuAttrValue.split("&");
                                     if(skuAttrArray.length<=1 || skuAttrArray.length>2){
                                         throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,缺少销售属性或格式不正确,请确认！"+skuAttrValue);
                                     }else{
                                         colorValueArray = skuAttrArray[0].split("#");
                                         sizeValueArray = skuAttrArray[1].split("#");
                                     }
                                 }else{
                                     throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,销售属性不能为空,请确认！");
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(skuAttrList))){
                                 //验证 SKU属性组合
                                 checkSkuAttrList(cellList.get(j),categoryPo,listSku,colorValueArray,sizeValueArray);
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(picList))){
                                 //图片组合验证
//                                 checkPicList(cellList.get(j),listSku,listImage,productCode);
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productInfoPc))){
                                 //商品描述 PC
                                 productInfoModel.setPcProductInfo(cellList.get(j));
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productInfoApp))){
                                 //商品描述 APP
                                 productInfoModel.setAppProductInfo(cellList.get(j));
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productShortName))){
                                 //验证 商品别名
                                 productOperateModel.setProductShortName(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productPrefix))){
                               //验证 商品前缀
                                 productOperateModel.setProductPrefix(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productSuffix))){
                               //验证 商品后缀
                                 productOperateModel.setProductSuffix(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productDownShow))){
                               //验证 是否下架显示
                                     productOperateModel.setProductDownShow((long)1);
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productAutoUp))){
                               //验证 是否自动上架
                                     productOperateModel.setProductAutoUp((long)2);
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productKeyword))){
                                 //验证 关键字
                                 productOperateModel.setProductKeyword(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productWeight))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductWeight(StringUtil.getValueFromString(cellList.get(j), Double.class));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该单品重量 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                                 
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productLong))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductLong(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)长 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productWidth))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductWidth(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)宽 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productHeight))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductHeight(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该(包装)高 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productProducer))){
                                  productMallModel.setProductProducer(cellList.get(j));
                             }else if(index.get(key).equals(InfoUtil.getStringToUtf(productDensity))){
                                 if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                     try {
                                        productMallModel.setProductDensity(Double.parseDouble(cellList.get(j)));
                                    } catch (Exception e) {
                                        throw new ZhaidouRuntimeException("第"+(i+1)+"行,第"+(j+1)+"列,该商品密度 不是有效数字,请确认！"+cellList.get(j));
                                    }
                                 }
                             }else{
                                 //验证 弹性域属性
                                 checkAttributeList(cellList.get(j),key,listAttr,listAttribute,index);
                             }
                         }
                     }
                 }
                 
                 //创建 商品 编码 以及 SKU 编码
                 try {
                    
                    if(productCode!=null && !"".equals(productCode)){
                        productModel.setProductCode(productCode);
                        if(listSku!=null && listSku.size()>0){
                            int x=1;
                            for(ProductSkuModel sku :listSku){
                                String skuCode="";
                                if(i<10){
                                    skuCode = productCode+"00"+x;
                                }else{
                                    skuCode = productCode+"0"+x;
                                }
                                sku.setProductSkuCode(skuCode);
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new ZhaidouRuntimeException("创建商品编码 以及SKU编码 失败！");
                }
                 productModel.setProductInfoModel(productInfoModel);
                 productOperateModel.setProductDownShow(2l);
                 productModel.setProductOperateModel(productOperateModel);
                 productModel.setProductSkuList(listSku);
                 productModel.setProductAttributeList(listAttribute);
                 listProductModel.add(productModel);
            }
        }
        index.clear();
        return listProductModel;
    }
    
    /**
     * 验证 弹性域属性
     *
     * @param productModel
     * @return
     */
    private void checkAttributeList(String attributeListValue,String key,List<ProductCateAttrGroupModel> listAttr,
                                    List<ProductAttributeModel> listAttribute,Map<String,Object> index){
        
        if(listAttr!=null && listAttr.size()>0){
            for(ProductCateAttrGroupModel attrGroupModel:listAttr){
                if(index.get(key).equals(attrGroupModel.getAttrName())){
                    ProductAttributeModel productAttributeModel = new ProductAttributeModel();
                    //验证 弹性域值
                    if(attributeListValue!=null && !"".equals(attributeListValue)){
                        
                        if(attrGroupModel.getAttrDesign()!=null && attrGroupModel.getAttrDesign()==1){
                            String[] attrValueList = attrGroupModel.getAttrValue().split(",");
                            String[] attrValueExcel = attributeListValue.split(",");
                            
                            for(int b=0;b<attrValueExcel.length;b++){
                                boolean flag = false;
                                for(int c=0;c<attrValueList.length;c++){
                                    if(attrValueExcel[b].equals(attrValueList[c])){
                                        flag = true;
                                        break;
                                    }
                                }
                                if(!flag){
                                    logger.error("该属性值不存在 attrValueExcel["+b+"]=" +attrValueExcel[b]+
                                            Joiner.on(",").join(attrValueList));
                                    throw new ZhaidouRuntimeException("该属性值("+attrValueExcel[b]+")不存在,请确认！");
                                }
                            }
                            
                        }
                        productAttributeModel.setIsRequired(attrGroupModel.getRequired());
                        productAttributeModel.setType(String.valueOf(attrGroupModel.getAttrDesign()));
                        productAttributeModel.setProductAttributeCode(attrGroupModel.getAttrCode());
                        productAttributeModel.setProductAttributeName(attrGroupModel.getAttrName());
                        productAttributeModel.setProductAttributeValue(attributeListValue);
                        listAttribute.add(productAttributeModel);
                        
                    }
                }
            }
        }
    }
    
    /**
     * 验证 SKU属性组合
     *
     * @param productModel
     * @return
     */
    private void checkSkuAttrList(String skuAttrListString,CategoryPO categoryPo,List<ProductSkuModel> listSku,String[] colorValueArray,String[] sizeValueArray){
      //验证 sku属性组合
        if(skuAttrListString!=null && !"".equals(skuAttrListString)){
            //判断是否有主SKU
            boolean skuFlag = false;
            String[] skuListValue = skuAttrListString.split("&");
            for(int x=0;x<skuListValue.length;x++){
                String[] skuValue = skuListValue[x].split("#");
                if(skuValue.length==6){
                    
                    //建立 SKU 对象
                    ProductSkuModel productSkuModel = new ProductSkuModel();
                    //建立 价格对象
                    ProductPriceModel productPriceModel = new ProductPriceModel();
                    //建立 库存对象
                    ProductStockModel productStockModel = new ProductStockModel();
                    
                    for(int y=0;y<skuValue.length;y++){
                      
                        productSkuModel.setAttrColorName(categoryPo.getColorName());
                        productSkuModel.setAttrSpecName(categoryPo.getSizeName());
                        
                        if(skuValue[y]!=null && !"".equals(skuValue[y])){
                            if(y==0){
                                //验证 颜色值是否匹配
                                boolean flag = false;
                                for(int a=0;a<colorValueArray.length;a++){
                                    if(skuValue[y].equals(colorValueArray[a])){
                                        flag = true;
                                        break;
                                    }
                                }
                                if(!flag){
                                    throw new ZhaidouRuntimeException(skuValue[y]+" , 该值与销售属性不匹配,请确认！");
                                }else{
                                    productSkuModel.setAttrColorValue(skuValue[y]);
                                }
                            }else if(y==1){
                              //验证 尺码值是否匹配
                                boolean flag = false;
                                for(int a=0;a<sizeValueArray.length;a++){
                                    if(skuValue[y].equals(sizeValueArray[a])){
                                        flag = true;
                                        break;
                                    }
                                }
                                if(!flag){
                                    throw new ZhaidouRuntimeException(skuValue[y]+",该值与销售属性不匹配,请确认！");
                                }else{
                                    productSkuModel.setAttrSpecValue(skuValue[y]);
                                }
                            }else if(y==2){
                              //验证 是否主SKU
                                if("是".equals(skuValue[y])){
                                    skuFlag = true;
                                    if(listSku.size()>0){
                                        for(ProductSkuModel sku:listSku){
                                            if(1==sku.getMainSku()){
                                                throw new ZhaidouRuntimeException("只能有一个主SKU,请确认！");
                                            }
                                        }
                                    }
                                    productSkuModel.setMainSku((long)1);
                                }else if("否".equals(skuValue[y])){
                                    productSkuModel.setMainSku((long)0);
                                }else{
                                    throw new ZhaidouRuntimeException("是否主SKU 内容不正确,请确认！ "+skuValue[y]);
                                }
                            }else if(y==3){
                                //验证 成本价
                                try {
                                   productPriceModel.setTshPrice(Double.parseDouble(skuValue[y]));
                                   productPriceModel.setCostPrice(0.0);
                               } catch (NumberFormatException e) {
                                   throw new ZhaidouRuntimeException("销售价格式类型不正确,请确认！ "+skuValue[y]);
                               }
                            }else if(y==4){
                              //验证 市场价
                                try {
                                   productPriceModel.setMarketPrice(Double.parseDouble(skuValue[y]));
                                   productPriceModel.setTb(0l);
                               } catch (NumberFormatException e) {
                                   throw new ZhaidouRuntimeException("市场价格式类型不正确,请确认！"+skuValue[y]);
                               }
                            }else if(y==5){
                              //验证 实际库存
                                try {
                                   productStockModel.setVirtualStock(Double.parseDouble(skuValue[y]));
                               } catch (NumberFormatException e) {
                                   throw new ZhaidouRuntimeException("实际库存格式类型不正确,请确认！"+skuValue[y]);
                               }
                            }
                           
                        }else{
                            throw new ZhaidouRuntimeException("SKU组合内容不能为空,请确认！");
                        }
                    }
                    
                    productSkuModel.setProductPriceModel(productPriceModel);
                    productSkuModel.setProductStockModel(productStockModel);
                    listSku.add(productSkuModel);
                }else{
                    throw new ZhaidouRuntimeException("SKU组合格式不正确,请确认！"+skuAttrListString);
                }
            }
            if(!skuFlag){
                throw new ZhaidouRuntimeException("必须要有主SKU,请确认！");
            }
        }else{
            throw new ZhaidouRuntimeException("必须要有一个Sku,请确认！");
        }
    }
    
    /**
     * 图片组合验证
     *
     * @param productModel
     * @return
     */
    private void checkPicList(String picListString,List<ProductSkuModel> listSku,List<ProductImageModel> listImage,String productCode){
        if(picListString!=null && !"".equals(picListString)){
            String[] imageListValue = picListString.split("&");
            int[] skuIndex = new int[imageListValue.length];
            
            for(int x=0;x<imageListValue.length;x++){
                ProductImageModel productImageModel = new ProductImageModel();
                
                
                String[] imageValue = imageListValue[x].split("#");
                if(imageValue.length==3){
                    for(int y=0;y<imageValue.length;y++){
                        
                        if(y==0){
                            //验证 图片路径
                            if(imageValue[y]!=null && !"".equals(imageValue[y])){
                                if(imageValue[y].indexOf(".")>0){
                                    String imageFormat = imageValue[y].substring(imageValue[y].lastIndexOf(".")+1, imageValue[y].length());
                                    if(imageFormat.equals("jpg") || imageFormat.equals("png") ){
                                       
                                        File file = new File(imageValue[y]);
                                       
                                        if(file.exists()){
                                            
                                            try {
                                                FileInputStream in=new FileInputStream(file);
                                                File outFile = new File(uploadImgTmpDir+"/"+productCode);
                                                
                                                 if(!outFile.exists()){
                                                    outFile.mkdirs();
                                                }
                                                 
                                                 outFile = new File(uploadImgTmpDir+"/"+productCode+"/"+imageValue[y].substring(imageValue[y].lastIndexOf("/")+1, imageValue[y].length()));
                                                 if(!outFile.exists()){
                                                     outFile.createNewFile();
                                                 } 
                                                FileOutputStream out=new FileOutputStream(outFile);
                                                int c;
                                                byte[] buffer=new byte[1024];
                                                while((c=in.read(buffer))!=-1){
                                                    
                                                        out.write(buffer);        
                                                }
                                                in.close();
                                                out.close();
                                            } catch (Exception e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            productImageModel.setImage(imageValue[y]);
                                        }else{
                                            throw new ZhaidouRuntimeException("图片路径不正确或不存在,请确认！"+picListString);
                                        }
                                        
                                    }else{
                                        throw new ZhaidouRuntimeException("图片格式不正确,请确认！"+picListString);
                                    }
                                }else{
                                    throw new ZhaidouRuntimeException("图片路径格式不正确,请确认！"+picListString);
                                }
                            }else{
                                throw new ZhaidouRuntimeException("图片路径不能为空,请确认！"+picListString);
                            }
                        }else if(y==1){
                          //验证 验证第几个SKU
                            try {
                               skuIndex[x] = Integer.parseInt(imageValue[y]);
                               if(listSku!=null && listSku.size()<skuIndex[x]){
                                   throw new ZhaidouRuntimeException("该图片所属SKU不存在,请确认！"+picListString);
                               }
                           } catch (NumberFormatException e) {
                               throw new ZhaidouRuntimeException("所属第几个SKU不是整数类型,请确认！"+picListString);
                           }
                        }else if(y==2){
                          //验证  验证第几张图
                            try {
                                productImageModel.setLevel(Long.parseLong(imageValue[y]));
                            } catch (NumberFormatException e) {
                                throw new ZhaidouRuntimeException("该SKU的第几张图不是整数类型,请确认！"+picListString);
                            }
                        } 
                    }
                }else{
                    throw new ZhaidouRuntimeException("图片组合格式不正确,请确认！"+picListString);
                }
                listImage.add(productImageModel);
            }
                    
            for(int m=0;m<skuIndex.length;m++){
                List<ProductImageModel> imageList = new ArrayList<ProductImageModel>();
                int level = skuIndex[m]-1;
                imageList.add(listImage.get(m));
                for(int n=m+1;n<skuIndex.length;n++){
                    if(skuIndex[n]-1==level){
                        imageList.add(listImage.get(n));
                    }
                }
                listSku.get(level).setProductImagerList(imageList);
            }
            for(ProductSkuModel skuModel:listSku){
                if(skuModel.getMainSku()==1){
                    if(skuModel.getProductImagerList()!=null && skuModel.getProductImagerList().size()>0){
                        
                    }else{
                        throw new ZhaidouRuntimeException("主SKU 必须有图,请确认！"+picListString);
                    }
                }
            }
        }else{
            throw new ZhaidouRuntimeException("主SKU 必须有图,请确认！");
        }
    }
    
    /**
     * 淘宝 导入 销售属性组合 验证及封装
     * 
     * @param categoryPo
     * @param map
     * @param integer
     * @param productModel
     * @return
     */
    private void taobaoSkuGroupChecked(String value, Double costPrice,
            List<ProductSkuModel> skuList, Integer rownum,
            CategoryPO categoryPo) throws Exception {
        if (value != null && !"".equals(value)) { // 价格 库存 废弃 颜色
            String[] array = value.split(";");
            int i = 0;
            for (; i < array.length;) {
                ProductSkuModel productSkuModel = null;
                String[] arrayAttr = array[i].split(":");
                if (arrayAttr.length == 5) {
                    productSkuModel = new ProductSkuModel();

                    if (categoryPo.getColorName() != null) {
                        productSkuModel.setAttrColorName(categoryPo
                                .getColorName());
                    } else {
                        productSkuModel.setAttrColorName("颜色");
                    }

                    if (categoryPo.getSizeName() != null) {
                        productSkuModel.setAttrSpecName(categoryPo
                                .getSizeName());
                    } else {
                        productSkuModel.setAttrSpecName("尺码");
                    }

                    // productSkuModel.setAttrSpecValue(array[x + 1]);//字符串
                    ProductPriceModel productPriceModel = new ProductPriceModel();
                    ProductStockModel productStockModel = new ProductStockModel();

                    String colorCode = "";

                    for (int y = 0; y < arrayAttr.length; y++) {
                        if (y == 0) {
                            if (arrayAttr[y] != null
                                    && !"".equals(arrayAttr[y])) {
                                try {
                                    Double marketPrice = Double
                                            .parseDouble(arrayAttr[y]);
                                    productPriceModel.setCostPrice(costPrice);
                                    productPriceModel
                                            .setMarketPrice(marketPrice);

                                    java.text.DecimalFormat df = new java.text.DecimalFormat(
                                            "#0.##");
                                    productPriceModel
                                            .setTshPrice(Double.parseDouble(df.format(costPrice
                                                    * (1 + 10 / 100))));
                                    Long tb = (long) Math
                                            .ceil(productPriceModel
                                                    .getMarketPrice()
                                                    - productPriceModel
                                                            .getTshPrice());
                                    if (tb <= 0) {
                                        logger.error("第" + rownum
                                                + "行,供货价与市场价相差比例不符,请确认！"
                                                + arrayAttr[y]);
                                        throw new ZhaidouRuntimeException(
                                                "第"
                                                        + rownum
                                                        + "行,销售属性组合,商品SKU市场价格不是有效数字,请确认！"
                                                        + arrayAttr[y]);
                                    }
                                    productPriceModel.setTb(tb);
                                } catch (Exception e) {
                                    logger.error("第" + rownum
                                            + "行,销售属性组合,商品SKU市场价格不是有效数字,请确认！"
                                            + arrayAttr[y]);
                                    throw new ZhaidouRuntimeException("第"
                                            + rownum
                                            + "行,销售属性组合,商品SKU市场价格不是有效数字,请确认！"
                                            + arrayAttr[y]);
                                }
                            } else {
                                logger.error("第" + rownum
                                        + "行,销售属性组合,商品SKU市场价格不能为空,请确认！");
                                throw new ZhaidouRuntimeException("第" + rownum
                                        + "行,销售属性组合,商品SKU市场价格不能为空,请确认！");
                            }
                        } else if (y == 1) {
                            if (arrayAttr[y] != null
                                    && !"".equals(arrayAttr[y])) {
                                try {
                                    productStockModel.setVirtualStock(Double
                                            .parseDouble(arrayAttr[y]));
                                } catch (Exception e) {
                                    logger.error("第" + rownum
                                            + "行,销售属性组合,商品SKU库存数量不是有效数字,请确认！"
                                            + arrayAttr[y]);
                                    throw new ZhaidouRuntimeException("第"
                                            + rownum
                                            + "行,销售属性组合,商品SKU库存数量不是有效数字,请确认！"
                                            + arrayAttr[y]);
                                }
                            } else {
                                logger.error("第" + rownum
                                        + "行,销售属性组合,商品SKU库存数量不能为空,请确认！");
                                throw new ZhaidouRuntimeException("第" + rownum
                                        + "行,销售属性组合,商品SKU库存数量不能为空,请确认！");
                            }
                        } else if (y == 4) {
                            if (arrayAttr[y] != null
                                    && !"".equals(arrayAttr[y])) {
                                colorCode = arrayAttr[y - 1] + ":"
                                        + arrayAttr[y];
                                productSkuModel.setAttrColorValue(colorCode);
                            }

                        }
                    }
                    productSkuModel.setProductPriceModel(productPriceModel);
                    productSkuModel.setProductStockModel(productStockModel);

                    if (i < array.length - 1) {
                        String[] specValus = array[i + 1].split(":");
                        if (specValus.length == 2) {
                            productSkuModel.setAttrSpecValue(specValus[0] + ":"
                                    + specValus[1]);
                            if (i < array.length - 2) {
                                i = i + 2;
                            }
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }
                    skuList.add(productSkuModel);
                }
                i++;
            }

        }
    }
    
    @Resource
    private ImageSearchManager imageSearchManager;
    /**
     * 商品 导出 操作
     *
     * @param productModel
     * @return
     */
    @Override
    public String expoutProductLogic(List<ProductModel> listProduct,HttpServletResponse response) {
        String resultStr="";
        List<Long> productIdList = new ArrayList<Long>();
        try {
            if(listProduct!=null && listProduct.size()>0){
                for(ProductModel productModel:listProduct){
                    productIdList.add(productModel.getProductId());
                }
            }
            if(productIdList.size()>0){
                List<ProductAttributeModel> listAttri = productAttributeService.getAttributeListByProductId(productIdList);
                List<ProductSkuModel> listSku = productSkuService.getProductSkuListByProductId(productIdList);
                List<ProductOperateModel> listOperate = productOperateService.getProductOperateListByProductId(productIdList);
                List<ProductMallModel> listMall = productMallService.getProductMallListByProductId(productIdList);
                List<ProductInfoModel> listInfo = productInfoService.getProductInfoListByProductId(productIdList);
                List<ProductPriceModel> listPrice = productPriceService.getProductPriceListByProductId(productIdList);
                List<ProductStockModel> listStock = productStockService.getProductStockListByProductId(productIdList);
                
                Map<String,ProductOperateModel> mapOperate = new HashMap<String, ProductOperateModel>();
                for(ProductOperateModel productOperateModel:listOperate){
                    mapOperate.put(productOperateModel.getProductId()+"", productOperateModel);
                }
                
                Map<String,ProductInfoModel> mapInfo = new HashMap<String, ProductInfoModel>();
                for(ProductInfoModel productInfoModel:listInfo){
                    mapInfo.put(productInfoModel.getProductId()+"", productInfoModel);
                }
                
                Map<String,ProductMallModel> mapMall = new HashMap<String, ProductMallModel>();
                for(ProductMallModel productMallModel:listMall){
                    mapMall.put(productMallModel.getProductId()+"", productMallModel);
                }
                
                Map<String,ProductPriceModel> mapPrice = new HashMap<String, ProductPriceModel>();
                for(ProductPriceModel productPriceModel:listPrice){
                    mapPrice.put(productPriceModel.getProductSkuId()+"", productPriceModel);
                }
                
                Map<String,ProductStockModel> mapStock = new HashMap<String, ProductStockModel>();
                for(ProductStockModel productStockModel:listStock){
                    mapStock.put(productStockModel.getSkuId()+"", productStockModel);
                }
                
                List<String> skuCodeList = new ArrayList<String>();
                for(ProductSkuModel productSkuModel:listSku){
                    
                    //设置 价格
                    productSkuModel.setProductPriceModel(mapPrice.get(productSkuModel.getProductSkuId()+""));
                    
                    //设置 库存
                    productSkuModel.setProductStockModel(mapStock.get(productSkuModel.getProductSkuId()+""));
                    
                    skuCodeList.add(productSkuModel.getProductSkuCode());
                }
                //获取 图片集合
//                List<ProductImageModel> ProductImageList = FileUtil.imageList(skuCodeList,imageSearchManager,listSku);
                
                
                for(ProductModel productModel:listProduct){
                    //设置 运营属性
                    productModel.setProductOperateModel(mapOperate.get(productModel.getProductId()+""));
                    
                    //设置 扩张属性
                   productModel.setProductMallModel(mapMall.get(productModel.getProductId()+""));
                    
                    //设置 大字段描述
                   productModel.setProductInfoModel(mapInfo.get(productModel.getProductId()+""));
                    
                    //设置 弹性域
                    List<ProductAttributeModel> attribute = new ArrayList<ProductAttributeModel>();
                    for(ProductAttributeModel productAttributeModel:listAttri){
                        if(productModel.getProductId().longValue()==productAttributeModel.getProductId().longValue()){
                            attribute.add(productAttributeModel);
                        }
                    }
                    productModel.setProductAttributeList(attribute);
                    
                    //设置 SKU 属性
                    List<ProductSkuModel> sku = new ArrayList<ProductSkuModel>();
                    for(ProductSkuModel productSkuModel:listSku){
                        if(productModel.getProductId().longValue()==productSkuModel.getProductId().longValue()){
                            
                            sku.add(productSkuModel);
                        }
                    }
                    productModel.setProductSkuList(sku);
                }
            }
 
            
            int totalSize = StaticDefaultData.productExport.size();
            
            String[] titlesName = new String[totalSize+1]; 
            
            int index =0;
            for(String key:StaticDefaultData.productExport.keySet()){
                titlesName[index] = StaticDefaultData.productExport.get(key);
                index++;
            }
            exportExcel(listProduct,titlesName,response);
        } catch (Exception e) {
            resultStr = e.getMessage();
        }
        return resultStr;
    }
    
    @Value("#{propertyConfigurerForProject2['product_export_title']}")
    private String productExportTitle;      //商品列表
    
    private void exportExcel(List<ProductModel> listProduct,String[] titlesName,HttpServletResponse response){
        try {
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String((InfoUtil.getStringToUtf(productExportTitle)).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
             
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(InfoUtil.getStringToUtf(productExportTitle));  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            XSSFCell cell = null; 
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);
            cell = headRow.createCell(0);  
            cell.setCellStyle(headStyle); 
            cell.setCellValue("tsh 1.0.0");
            
            headRow = sheet.createRow(1);  
             
            for (int i = 0; i < titlesName.length; i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle); 
                cell.setCellValue(titlesName[i]);  
            }
            
           
            
            if(listProduct!=null && listProduct.size()>0){
                
                for(int x=0;x< listProduct.size();x++){
                    XSSFRow bodyRow = sheet.createRow(x+2);
                    for (int i = 0; i < titlesName.length; i++)  
                    {  
                        cell = bodyRow.createCell(i);  
                        cell.setCellStyle(bodyStyle); 
                        if(i==0){
                            //商品名称
                            cell.setCellValue(listProduct.get(x).getProductName()); 
                        }else if(i==1){
                            //商品编号
                            cell.setCellValue(listProduct.get(x).getProductCode());
                        }else if(i==2){
                            //商品简介
                            if(listProduct.get(x).getProductDesc()!=null){
                                cell.setCellValue(listProduct.get(x).getProductDesc());
                            }
                        }else if(i==3){
                            //品牌名称
                            cell.setCellValue(listProduct.get(x).getBrandName());
                        }else if(i==4){
                            //类目名称
                            cell.setCellValue(listProduct.get(x).getCatName());
                        }else if(i==5){
                            //类型
                            cell.setCellValue(listProduct.get(x).getType());
                        }else if(i==6){
                            //供应商编号
                            if(listProduct.get(x).getSupplierId()!=null){
                                cell.setCellValue(listProduct.get(x).getSupplierId());
                            }
                        }else if(i==7){
                            //店铺编号
                            if(listProduct.get(x).getShopId()!=null){
                                cell.setCellValue(listProduct.get(x).getShopId());
                            }
                        }else if(i==8){
                            //SKU属性组合
                            StringBuffer sb = new StringBuffer();
                            if(listProduct.get(x).getProductSkuList()!=null && listProduct.get(x).getProductSkuList().size()>0){
                                int j=1;
                                for(ProductSkuModel sku :listProduct.get(x).getProductSkuList()){
                                    if(j>1){
                                        sb.append("&");
                                    }
                                    sb.append(sku.getAttrColorValue()+"#"+sku.getAttrSpecValue());
                                    if(sku.getMainSku().longValue()==1){
                                        sb.append("#是");
                                    }else{
                                        sb.append("#否");
                                    }
                                    
                                    ProductPriceModel priceModel = sku.getProductPriceModel();
                                    sb.append("#"+priceModel.getCostPrice()+"#"+priceModel.getTshPrice()+"#"+priceModel.getMarketPrice()+"#"+priceModel.getTb());
                                    
                                    ProductStockModel stockModel = sku.getProductStockModel();
                                    if(null!=stockModel && null!=stockModel.getVirtualStock()){
                                    	sb.append("#"+stockModel.getVirtualStock());
                                    }else{
                                    	sb.append("#0");
                                    }
                                    
                                    j++;
                                }
                                cell.setCellValue(sb.toString());
                            }
                        }else if(i==9){
                            //图片组合
//                            StringBuffer sb = new StringBuffer();
//                            if(listProduct.get(x).getProductSkuList()!=null && listProduct.get(x).getProductSkuList().size()>0){
//                                int j = 1;
//                                int skuLevel = 1;
//                                for(ProductSkuModel sku :listProduct.get(x).getProductSkuList()){
//                                    
//                                    RequestObject reqeustObject = new RequestObject();
//                                    GetImagePathRequestPO requestPo = new GetImagePathRequestPO();
//                                    requestPo.setRelationCode(sku.getProductSkuCode());
//                                    requestPo.setRelationType(1l);
//                                    reqeustObject.setRequestParams(requestPo);
//                                    
//                                    ResponseObject responseObject = imageSearchManager.getImagePath(reqeustObject);
//                                    if(responseObject.getCode()==0){
//                                        List<GetImageResponsePO> responsePo = (List<GetImageResponsePO>)responseObject.getData();
//                                        if(responsePo!=null && responsePo.size()>0){
//                                            for(GetImageResponsePO imagePo: responsePo){
//                                                if(j>1){
//                                                    sb.append("&");
//                                                }
//                                                sb.append(imagePo.getImagePath()+"/"+imagePo.getImageName()+imagePo.getImageType()+"#"+skuLevel+"#"+imagePo.getImageIndex());
//                                                j++;
//                                            }
//                                        }
//                                    }
//                                    skuLevel++;
//                                }
//                            }
                            
                            cell.setCellValue("");
                        }else if(i==10){
                            //商品描述PC
                            if(listProduct.get(x).getProductInfoModel()!=null && listProduct.get(x).getProductInfoModel().getPcProductInfo()!=null){
                                cell.setCellValue(listProduct.get(x).getProductInfoModel().getPcProductInfo());
                            }
                        }else if(i==11){
                            //商品描述APP
                            if(listProduct.get(x).getProductInfoModel()!=null && listProduct.get(x).getProductInfoModel().getAppProductInfo()!=null){
                                cell.setCellValue(listProduct.get(x).getProductInfoModel().getAppProductInfo());
                            }
                        }else if(i==12){
                            //商品别名
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductShortName()!=null){
                                cell.setCellValue(listProduct.get(x).getProductOperateModel().getProductShortName());
                            }
                        }else if(i==13){
                            //商品前缀
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductPrefix()!=null){
                                cell.setCellValue(listProduct.get(x).getProductOperateModel().getProductPrefix());
                            }
                        }else if(i==14){
                            //商品后缀
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductSuffix()!=null){
                                cell.setCellValue(listProduct.get(x).getProductOperateModel().getProductSuffix());
                            }
                        }else if(i==15){
                            //是否下架显示
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductDownShow()!=null){
                                if(listProduct.get(x).getProductOperateModel().getProductDownShow()==1){
                                    cell.setCellValue("是");
                                }else{
                                    cell.setCellValue("否");
                                }
                            }
                        }else if(i==16){
                            //是否自动上架
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductAutoUp()!=null){
                                if(listProduct.get(x).getProductOperateModel().getProductAutoUp()==1){
                                    cell.setCellValue("是");
                                }else{
                                    cell.setCellValue("否");
                                }
                            }
                        }else if(i==17){
                            //关键字
                            if(listProduct.get(x).getProductOperateModel()!=null && listProduct.get(x).getProductOperateModel().getProductKeyword()!=null){
                                cell.setCellValue(listProduct.get(x).getProductOperateModel().getProductKeyword());
                            }
                        }else if(i==18){
                            //商品单品重量
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductWeight()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductWeight());
                            }
                        }else if(i==19){
                            //(包装)长
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductLong()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductLong());
                            }
                        }else if(i==20){
                            //(包装)长
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductWidth()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductWidth());
                            }
                        }else if(i==21){
                            //(包装)长
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductHeight()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductHeight());
                            }
                        }else if(i==22){
                            //商品产地
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductProducer()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductProducer());
                            }
                        }else if(i==22){
                            //密度
                            if(listProduct.get(x).getProductMallModel()!=null && listProduct.get(x).getProductMallModel().getProductDensity()!=null){
                                cell.setCellValue(listProduct.get(x).getProductMallModel().getProductDensity());
                            }
                        }else if(i==18){
                            //商品属性（弹性域）组合
                            if(listProduct.get(x).getProductAttributeList()!=null && listProduct.get(x).getProductAttributeList().size()>0){
                                int j=1;
                                StringBuffer sb = new StringBuffer();
                                for(ProductAttributeModel attributeModel:listProduct.get(x).getProductAttributeList()){
                                    if(j>1){
                                        sb.append("&");
                                    }
                                    sb.append(attributeModel.getProductAttributeName()+"#"+attributeModel.getProductAttributeValue());
                                }
                            }
                            
                        }
                    }
                }
            }
            try  
            {  
                workBook.write(outputStream);  
                  
            } finally  
            {  
                outputStream.flush();  
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 判断是否缺少必填字段
     *
     * @param categoryPo 
     * @param map 
     * @param integer 
     * @param productModel
     * @return
     */
    private String judgeField(Boolean nameFlag,Boolean brandCodeFlag,Boolean brandNameFlag,Boolean catCodeFlag,Boolean catNameFlag,Boolean supplierFlag,Boolean shopFlag){
        StringBuffer lackField = new StringBuffer("缺少必填字段列：");
        boolean flag = true;
        if(nameFlag!=null){
            if(!nameFlag){
                lackField.append("商品名称,");
                flag = false;
            }
        }
        if(brandCodeFlag!=null){
            if(!brandCodeFlag){
                lackField.append("品牌编号,");
                flag = false;
            }
        }
        if(brandNameFlag!=null){
            if(!brandNameFlag){
                lackField.append("品牌名称,");
                flag = false;
            }
        }
        if(catCodeFlag!=null){
            if(!catCodeFlag){
                lackField.append("分类编号,");
                flag = false;
            }
        }
        if(catNameFlag!=null){
            if(!catNameFlag){
                lackField.append("分类名称,");
                flag = false;
            }
        }
        if(supplierFlag!=null){
            if(!supplierFlag){
                lackField.append("供应商编号,");
                flag = false;
            }
        }
        if(shopFlag!=null){
            if(!shopFlag){
                lackField.append("店铺编号,");
                flag = false;
            }
        }
        if(flag){
            return null;
        }else{
            return lackField.toString();
        }
        
    }
}
