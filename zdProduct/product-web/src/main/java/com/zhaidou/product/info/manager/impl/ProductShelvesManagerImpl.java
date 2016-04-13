package com.zhaidou.product.info.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductShelvesManager;
import com.zhaidou.product.info.model.ProductModel;
import com.zhaidou.product.info.model.ProductShelvesLogModel;
import com.zhaidou.product.info.model.ProductShelvesModel;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;
import com.zhaidou.product.info.service.ProductService;
import com.zhaidou.product.info.service.ProductShelvesLogService;
import com.zhaidou.product.info.service.ProductShelvesService;
import com.zhaidou.product.info.service.ProductShelvesTmpService;
import com.zhaidou.product.info.util.InfoUtil;

@Service("productShelvesManager")
public class ProductShelvesManagerImpl implements ProductShelvesManager {
    private static Logger logger = Logger.getLogger(ProductShelvesManagerImpl.class);
    
    @Resource
    private ProductShelvesService productShelvesService;
    @Resource
    private ProductShelvesTmpService productShelvesTmpService;
    @Resource
    private ProductShelvesLogService productShelvesLogService;
    @Resource
    private ProductService productService;
    
    //properties 注入
    //上下架
    @Value("#{propertyConfigurerForProject2['product_id']}")
    private String productId;
    @Value("#{propertyConfigurerForProject2['product_name']}")
    private String productName;
    @Value("#{propertyConfigurerForProject2['product_code']}")
    private String productCode;
    @Value("#{propertyConfigurerForProject2['status']}")
    private String status;
    @Value("#{propertyConfigurerForProject2['reason']}")
    private String reason;
    @Value("#{propertyConfigurerForProject2['shelves_status']}")
    private String shelvesStatus;
    @Value("#{propertyConfigurerForProject2['create_by']}")
    private String createBy;
    @Value("#{propertyConfigurerForProject2['create_user_name']}")
    private String createUserName;
    @Value("#{propertyConfigurerForProject2['create_time']}")
    private String createTime;
    @Value("#{propertyConfigurerForProject2['update_by']}")
    private String updateBy;
    @Value("#{propertyConfigurerForProject2['update_user_name']}")
    private String supdateUserName;
    @Value("#{propertyConfigurerForProject2['update_time']}")
    private String updateTime;
    @Value("#{propertyConfigurerForProject2['shelves_export_title']}")
    private String shelvesExportTitle;
    @Value("#{propertyConfigurerForProject2['time_start']}")
    private String timeStart;

    
    //上下架日志
    @Value("#{propertyConfigurerForProject2['shelveslog_export_title']}")
    private String shelvesLogExportTitle;
    
    /**
     *列表查询 
     */
    @Override
    public List<ProductShelvesModel> getProductShelves(ProductShelvesModel productShelvesModel, Page page) {
        List<ProductShelvesModel> listShelves = null;
        try {
            listShelves = productShelvesService.getProductShelves(productShelvesModel, page);
        } catch (Exception e) {
            logger.error("",e);
        }
        if(listShelves!=null && listShelves.size()>0){
            for(ProductShelvesModel productShelves:listShelves){
                if(productShelves.getCreateTime()!=null){
                    productShelves.setCreateTimes(InfoUtil.dateLongToString(productShelves.getCreateTime(), InfoUtil.dateString));
                }
                if(productShelves.getUpdateTime()!=null){
                    productShelves.setUpdateTimes(InfoUtil.dateLongToString(productShelves.getUpdateTime(), InfoUtil.dateString));
                }
            }
        }
        return listShelves;
    }
    
    /**
     * 导入上下架 操作
     *
     * @param list
     * @return
     */
    @Override
    public String uploadShelvesExcel(List<List<String>> list,Map<String,String> map) {
        String result = "";
        List<String> cellList = null;
        
        boolean flag = true;
        if (list != null) {
            cellList = list.get(0);
            
            for(int x=0;x<cellList.size();x++){
                if(x==0){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(productCode))){
                        flag = false;
                    }
                }
                if(x==1){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(shelvesStatus))){
                        flag = false;
                    }
                }
                if(x==2){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(timeStart))){
                        flag = false;
                    }
                }
                if(x==3){
                    if(!cellList.get(x).equals(InfoUtil.getStringToUtf(reason))){
                        flag = false;
                    }
                }
            }
            
            if(flag){
                List<ProductShelvesModel> listShelves = new ArrayList<ProductShelvesModel>();
                ProductShelvesModel productShelves = null;
                for (int i = 1; i < list.size(); i++) {
                    
                    cellList = list.get(i);
                    productShelves = new ProductShelvesModel();
                    for (int j = 0; j < cellList.size(); j++) {
                        
                        productShelves.setStatus(0);
                        
                        if(j==0){
                            if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                ProductModel productModel = productService.getProductByCode(cellList.get(j).trim());
                                
                                //判断 导入商品编号是否存在
                                if(productModel==null){
                                    logger.error("第"+i+"行,导入 商品编号 不存在或不匹配！"+cellList.get(j));
                                    result = "第"+i+"行,导入 商品编号 不存在或与商品不匹配！";
                                    return result;
                                }else{
                                    productShelves.setProductName(productModel.getProductName());
                                    productShelves.setProductCode(cellList.get(j));
                                }
                            }else{
                                result = "第"+i+"行,商品编号不能为空,请确认！";
                                return result;
                            }
                        }
                        if(j==1){
                            if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                    if("上架".equals(cellList.get(j))){
                                        productShelves.setShelvesStatus(1);  
                                    }else if("下架".equals(cellList.get(j))){
                                        productShelves.setShelvesStatus(0);
                                    }else{
                                        result = "第"+i+"行,上下架状态 文本值 不正确！";
                                        return result;
                                    }
                            }else{
                                result = "第"+i+"行,商品上下架状态不能为空,请确认！";
                                return result;
                            }
                        }
                        if(j==2){
                            if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                                if(cellList.get(j).length()==19){
                                    try {
                                        productShelves.setTimeStart(DatetimeUtil.stringToDate(cellList.get(j)).getTime()/1000);
                                    } catch (Exception e) {
                                        logger.error("第"+i+"行,上下架 上传操作,生效时间 格式不符合！");
                                        result = "第"+i+"行,生效时间 格式不符合！";
                                        return result;
                                    }
                                }else{
                                    logger.error("第"+i+"行,上下架 上传操作,生效时间 格式不符合！");
                                    result = "第"+i+"行,生效时间 格式不符合！";
                                    return result;
                                }
                            }
                        }
                        if(j==3){
                        	if(cellList.get(j)!=null && !"".equals(cellList.get(j))){
                        		productShelves.setReason(cellList.get(j));
                        	}
                        }
                    }
                    listShelves.add(productShelves);
                }
                //判断是否有重复数据
                if(listShelves!=null && listShelves.size()>0){
                    for(int i=0;i<listShelves.size();i++){
                        for(int j=i+1;j<listShelves.size();j++){
                            if(listShelves.get(i).getProductCode().equals(listShelves.get(j).getProductCode())){
                                logger.error("上下架 上传操作,商品Code 重复！ productCode:"+listShelves.get(i).getProductCode());
                                result = "  商品Code 重复！  productCode:"+listShelves.get(i).getProductCode();
                                return result;
                            }
                        }
                    }
                }else{
                    result="无数据导入,请确认！";
                    return result;
                }
                try {
                    productShelvesService.addProductShelves(listShelves,map);
                    logger.debug("上下架导入成功："+JSONUtil.toString(listShelves));
                } catch (Exception e) {
                    result = e.getMessage();
                    logger.error("",e);
                }
            }else{
                result = "上传文件不符合规范！";
            }
           
        }
        return result;
    }
    
    /**
     * 导出上下架 操作
     *
     * @param list
     * @return
     */
    @Override
    public String exportShelvesExcel(List<ProductShelvesModel> listShelves,HttpServletResponse response) {
        logger.debug("上下架日志 导出 开始····！");
        try {
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String((InfoUtil.getStringToUtf(shelvesExportTitle)).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
            String[] titles = { InfoUtil.getStringToUtf(productId), InfoUtil.getStringToUtf(productName), 
                    InfoUtil.getStringToUtf(productCode), InfoUtil.getStringToUtf(status),InfoUtil.getStringToUtf(shelvesStatus), InfoUtil.getStringToUtf(createBy),
                    InfoUtil.getStringToUtf(createUserName), InfoUtil.getStringToUtf(createTime),InfoUtil.getStringToUtf(updateBy),
                    InfoUtil.getStringToUtf(supdateUserName),InfoUtil.getStringToUtf(updateTime),InfoUtil.getStringToUtf(reason)};  
              
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(InfoUtil.getStringToUtf(shelvesExportTitle));  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);  
            XSSFCell cell = null;  
            for (int i = 0; i < titles.length; i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle);  
                cell.setCellValue(titles[i]);  
            }  
            // 构建表体数据  
            if (listShelves != null && listShelves.size() > 0)  
            {  
                for (int j = 0; j < listShelves.size(); j++)  
                {  
                    XSSFRow bodyRow = sheet.createRow(j + 1);  
                    ProductShelvesModel productShelvesModel = listShelves.get(j);  
                    cell = bodyRow.createCell(0);  
                    cell.setCellStyle(bodyStyle);  
                    if(null!=productShelvesModel.getProductId()){
                    cell.setCellValue(productShelvesModel.getProductId());
                    }else{
                    	cell.setCellValue("");
                    }
                    cell = bodyRow.createCell(1);  
                    cell.setCellStyle(bodyStyle);  
                    if(null!=productShelvesModel.getProductName()){
                    	     cell.setCellValue(productShelvesModel.getProductName());
                        }else{
                        	cell.setCellValue("");
                    }
                    cell = bodyRow.createCell(2);  
                    cell.setCellStyle(bodyStyle);  
                    cell.setCellValue(productShelvesModel.getProductCode());
					if (null != productShelvesModel.getProductCode()) {
						cell.setCellValue(productShelvesModel.getProductCode());
					} else {
						cell.setCellValue("");
					}
                    cell = bodyRow.createCell(3);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getStatus()!=null){
                        if(productShelvesModel.getStatus()==1){
                            cell.setCellValue("待审核");
                        }else if(productShelvesModel.getStatus()==2){
                            cell.setCellValue("审核通过");
                        }else if(productShelvesModel.getStatus()==3){
                            cell.setCellValue("待同步");
                        }else if(productShelvesModel.getStatus()==4){
                            cell.setCellValue("同步成功");
                        }else if(productShelvesModel.getStatus()==9){
                            cell.setCellValue("审核失败");
                        }else{
                            cell.setCellValue("无状态");
                        }
                    }else{
                        cell.setCellValue("无状态");
                    }
                    
                    cell = bodyRow.createCell(4);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getShelvesStatus()!=null){
                        if(productShelvesModel.getShelvesStatus()==0){
                            cell.setCellValue("下架");
                        }else if(productShelvesModel.getShelvesStatus()==1){
                            cell.setCellValue("上架");
                        }
                    }
                    
                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getCreateBy()!=null){
                        cell.setCellValue(productShelvesModel.getCreateBy());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(6);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getCreateUserName()!=null){
                        cell.setCellValue(productShelvesModel.getCreateUserName());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(7);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getCreateTime()!=null){
                        cell.setCellValue(InfoUtil.dateLongToString(productShelvesModel.getCreateTime(), InfoUtil.dateString));
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(8);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getUpdateBy()!=null){
                        cell.setCellValue(productShelvesModel.getUpdateBy());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(9);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getUpdateUserName()!=null){
                        cell.setCellValue(productShelvesModel.getUpdateUserName());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(10);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getUpdateTime()!=null){
                        cell.setCellValue(InfoUtil.dateLongToString(productShelvesModel.getUpdateTime(), InfoUtil.dateString));
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(11);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesModel.getReason()!=null){
                        cell.setCellValue(productShelvesModel.getReason());
                    }else{
                        cell.setCellValue("");
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
            logger.error("上下架 导出 失败！",e);
        }  
        logger.debug("上下架 导出 成功！"+JSONUtil.toString(listShelves));
        return null;
    }
    
    /**
     * 修改上下架操作
     *
     * @param list
     * @return
     */
    @Override
    public String updateShelves(ProductShelvesModel productShelvesModel) {
        String result = "";
        try {
            productShelvesService.updateProductShelves(productShelvesModel);
        } catch (Exception e) {
            logger.error("",e);
            result = e.getMessage(); 
        }
        return result;
    }
    
    /**
     * 商品 上下架 日志 列表
     *
     * @param productShelvesModel
     * @param page
     * @return
     */
    @Override
    public List<ProductShelvesLogModel> getProductShelvesLogList(ProductShelvesLogModel productShelvesLogModel,
            Page page) {
        List<ProductShelvesLogModel> listShelvesLog = null;
        try {
            listShelvesLog = productShelvesLogService.getProductShelvesLog(productShelvesLogModel, page);
        } catch (Exception e) {
        }
        if(listShelvesLog!=null && listShelvesLog.size()>0){
            for(ProductShelvesLogModel productShelvesLog:listShelvesLog){
                if(productShelvesLog.getCreateTime()!=null){
                    productShelvesLog.setCreateTimes(InfoUtil.dateLongToString(productShelvesLog.getCreateTime(), InfoUtil.dateString));
                }
                if(productShelvesLog.getUpdateTime()!=null){
                    productShelvesLog.setUpdateTimes(InfoUtil.dateLongToString(productShelvesLog.getUpdateTime(), InfoUtil.dateString));
                }
            }
        }
        return listShelvesLog;
    }
    
    /**
     * 导出上下架日志 操作
     *
     * @param list
     * @return
     */
    @Override
    public String exportShelvesExcelLog(List<ProductShelvesLogModel> listShelvesLog, HttpServletResponse response) {
        logger.debug("上下架 导出 开始····！");
        try {
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String((InfoUtil.getStringToUtf(shelvesLogExportTitle)).getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
            String[] titles = { InfoUtil.getStringToUtf(productId), InfoUtil.getStringToUtf(productName), 
                    InfoUtil.getStringToUtf(productCode), InfoUtil.getStringToUtf(status),InfoUtil.getStringToUtf(reason),
                    InfoUtil.getStringToUtf(createBy),InfoUtil.getStringToUtf(createUserName), InfoUtil.getStringToUtf(createTime)};  
            
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(InfoUtil.getStringToUtf(shelvesLogExportTitle));  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);  
            XSSFCell cell = null;  
            for (int i = 0; i < titles.length; i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle);  
                cell.setCellValue(titles[i]);  
            }  
            // 构建表体数据  
            if (listShelvesLog != null && listShelvesLog.size() > 0)  
            {  
                for (int j = 0; j < listShelvesLog.size(); j++)  
                {  
                    XSSFRow bodyRow = sheet.createRow(j + 1);  
                    ProductShelvesLogModel productShelvesLogModel = listShelvesLog.get(j);  
                    cell = bodyRow.createCell(0);  
                    cell.setCellStyle(bodyStyle);  
                    if(null!=productShelvesLogModel.getProductId()){
                    	 cell.setCellValue(productShelvesLogModel.getProductId());
                    }else{
                    	 cell.setCellValue("");
                    }
                    cell = bodyRow.createCell(1);  
                    cell.setCellStyle(bodyStyle);  
					if (null != productShelvesLogModel.getProductName()) {
						cell.setCellValue(productShelvesLogModel.getProductName());
					} else {
						cell.setCellValue("");
					}
                    cell = bodyRow.createCell(2);  
                    cell.setCellStyle(bodyStyle);  
                    if (null != productShelvesLogModel.getProductCode()) {
                    	cell.setCellValue(productShelvesLogModel.getProductCode());
					} else {
						cell.setCellValue("");
					}
                    
                    cell = bodyRow.createCell(3);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesLogModel.getStatus()!=null){
                        if(productShelvesLogModel.getStatus()==1){
                            cell.setCellValue("待审核");
                        }else if(productShelvesLogModel.getStatus()==2){
                            cell.setCellValue("审核通过");
                        }else if(productShelvesLogModel.getStatus()==3){
                            cell.setCellValue("待同步");
                        }else if(productShelvesLogModel.getStatus()==4){
                            cell.setCellValue("同步成功");
                        }else if(productShelvesLogModel.getStatus()==9){
                            cell.setCellValue("审核失败");
                        }else{
                            cell.setCellValue("无状态");
                        }
                    }else{
                        cell.setCellValue("无状态");
                    }
                    
                    cell = bodyRow.createCell(4); 
                    cell.setCellStyle(bodyStyle);  
                    cell.setCellValue(productShelvesLogModel.getReason());
                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesLogModel.getCreateBy()!=null){
                        cell.setCellValue(productShelvesLogModel.getCreateBy());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(6);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesLogModel.getCreateUserName()!=null){
                        cell.setCellValue(productShelvesLogModel.getCreateUserName());
                    }else{
                        cell.setCellValue("");
                    }
                    
                    cell = bodyRow.createCell(7);  
                    cell.setCellStyle(bodyStyle);
                    if(productShelvesLogModel.getCreateTime()!=null){
                        cell.setCellValue(InfoUtil.dateLongToString(productShelvesLogModel.getCreateTime(), InfoUtil.dateString));
                    }else{
                        cell.setCellValue("");
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
            logger.error("上下架 导出 失败！",e);
        }  
        logger.debug("上下架 导出 成功！"+JSONUtil.toString(listShelvesLog));
        return null;
    }
    
    /**
     * 上下架操作
     *
     * @param list
     * @return
     */
    @Override
    public String shelvesOperation(Long[] ids,String shelvesType,String reason,Map<String,String> map) {
        String resultStr = "";
        List<ProductShelvesModel> listShelves = null;
        if(ids!=null && ids.length>0){
            listShelves = new ArrayList<ProductShelvesModel>();
            for(Long productId:ids){
                
                ProductModel productModel = new ProductModel();
                productModel.setProductId(productId);
                try {
                    productModel = productService.getProductById(productModel);
                } catch (Exception e) {
                    logger.error("上下架操作,系统错误!",e);
                    return  "上下架操作,系统错误!";
                }
                
                ProductShelvesModel productShelvesModel = new ProductShelvesModel();
                productShelvesModel.setStatus(0);
                productShelvesModel.setProductCode(productModel.getProductCode());
                productShelvesModel.setProductName(productModel.getProductName());
                if("1".equals(shelvesType)){
                    productShelvesModel.setShelvesStatus(1);
                }else{
                    productShelvesModel.setShelvesStatus(0);
                    productShelvesModel.setReason(reason);
                }
                
                productShelvesModel.setUpdateBy(Long.parseLong(map.get("userId")));
                productShelvesModel.setUpdateUserName(map.get("userName"));
                productShelvesModel.setUpdateTime(new Date().getTime()/1000);
                
                listShelves.add(productShelvesModel);
            }
        }
        
        try {
            productShelvesService.addProductShelves(listShelves, map);
        } catch (Exception e) {
            logger.error("上下架系统错误!",e);
            return "上下架系统错误!";
        }
        return resultStr;
    }

    @Override
    public void addShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel) throws Exception {
        productShelvesTmpService.addShelvesTmp(productShelvesTmpModel);
    }

    @Override
    public ProductShelvesTmpModel getShelvesTmpByProductCode(String productCode) throws Exception {
        ProductShelvesTmpModel productShelvesTmpModel = new ProductShelvesTmpModel();
        productShelvesTmpModel.setProductCode(productCode);
        return productShelvesTmpService.getShelvesTmpByProductCode(productShelvesTmpModel);
    }

    @Override
    public void updateShelvesTmp(ProductShelvesTmpModel productShelvesTmpModel) throws Exception {
        productShelvesTmpService.updateShelvesTmp(productShelvesTmpModel);
    }
    
    /**
     * 待上架查询
     *
     * @param list
     * @return
     */
    @Override
    public List<ProductShelvesTmpModel> getShelvesTmpList(ProductShelvesTmpModel productShelvesTmpModel, Page page)
            throws Exception {
        
        return productShelvesTmpService.getShelvesTmpList(productShelvesTmpModel, page);
    }
    
}
