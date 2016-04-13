package com.zhaidou.product.info.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.ImportExecl;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.manager.ProductShelvesManager;
import com.zhaidou.product.info.model.ProductShelvesLogModel;
import com.zhaidou.product.info.model.ProductShelvesModel;
import com.zhaidou.product.info.model.ProductShelvesTmpModel;
import com.zhaidou.product.info.util.ExcelUtil;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value="/info/shelves")
public class ProductShelvesController {
    
    private static final Log logger = LogFactory.getLog(ProductShelvesController.class);
    
    private static String TOUPLOAD = "info/shelves/shelves_upload";
    private static String SHELVESLIST = "info/shelves/shelves_list";
    private static String SHELVESLOGLIST = "info/shelves/log/shelves_log_list";
    
    @Resource
    private ProductShelvesManager productShelvesManager;
    
    
    
    /**
     *获取 登录用户的信息 
     * */
    private Map<String,String> getUserMap(HttpServletRequest request){
        Map<String,String> userMap = (Map<String,String>)request.getSession().getAttribute("user");
        return userMap;
    }
    
    /**
     * 转至 上下架 列表
     *
     * @return
     */
    @RequestMapping(value="/list")
    public String getShelvesList(ProductShelvesModel productShelvesModel,Page page,Map<String,Object> map,HttpServletRequest req){
        
    	String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        if(startTime!=null && !"".equals(startTime)){
        	productShelvesModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
        	productShelvesModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime", endTime);
        }

        List<ProductShelvesModel> listShelves = productShelvesManager.getProductShelves(productShelvesModel, page);
        
        map.put("listShelves", listShelves);
        return SHELVESLIST;
    }
    
    /**
     * 转至 上下架 日志 列表
     *
     * @return
     */
    @RequestMapping(value="/log/list")
    public String getShelvesLogList(ProductShelvesLogModel productShelvesLogModel,Page page,Map<String,Object> map,HttpServletRequest req){
        
    	String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        if(startTime!=null && !"".equals(startTime)){
        	productShelvesLogModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
        	productShelvesLogModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime", endTime);
        }
        
        List<ProductShelvesLogModel> listShelvesLog = productShelvesManager.getProductShelvesLogList(productShelvesLogModel, page);
        
        map.put("listShelvesLog", listShelvesLog);
        return SHELVESLOGLIST;
    }
    
    
   
    /**
     * 转至 上传 页面
     *
     * @return
     */
    @RequestMapping(value = "/to_upload")  
    public String upload(){  
        return TOUPLOAD;  
    }
    
    /**
     * 上下架 导入 操作
     *
     * @return
     */
    @RequestMapping(value = "/upload_excel")  
    @ResponseBody
    public String uploadShelvesExcel(@RequestParam("file")   MultipartFile file,HttpServletRequest req){
        Map<String,String> map = getUserMap(req);
        if(map!=null){
            ImportExecl poi = new ImportExecl();
            List<List<String>> list = null;
            try {
                list = poi.read(file.getOriginalFilename(),file.getInputStream());
                if (list != null) {
                    String result = productShelvesManager.uploadShelvesExcel(list,map);
                    if(!"".equals(result)){
                        return AjaxObject.newError(result).toString();
                    }
                }
            } catch (Exception e) {
                return AjaxObject.newError("上传添加失败").toString();
            }
            
            return AjaxObject.newOk("上传添加成功").toString();
        }else{
            return AjaxObject.newError("请先登录").toString();
        }
        
    }
    
    /**
     * 上下架 导出 操作
     *
     * @return
     */
    @RequestMapping(value = "/export_excel") 
    @ResponseBody
    public String exportShelvesExcel(HttpServletResponse response,HttpServletRequest req,ProductShelvesModel productShelvesModel,Page page){
       
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        if(startTime!=null && !"".equals(startTime)){
            productShelvesModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
            productShelvesModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime", endTime);
        }
        page.setNumPerPage(5000);
        List<ProductShelvesModel> listShelves = productShelvesManager.getProductShelves(productShelvesModel, page);
        productShelvesManager.exportShelvesExcel(listShelves, response);
        
        return null;
    }
    
    /**
     * 上下架日志 导出 操作
     *
     * @return
     */
    @RequestMapping(value = "/log_export_excel") 
    @ResponseBody
    public String exportShelvesLogExcel(HttpServletResponse response,HttpServletRequest req,ProductShelvesLogModel productShelvesLogModel,Page page){
        if(productShelvesLogModel.getProductName()!=null && !"".equals(productShelvesLogModel.getProductName())){
            productShelvesLogModel.setProductName(InfoUtil.getStringToUtf(productShelvesLogModel.getProductName()));;
        }
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        if(startTime!=null && !"".equals(startTime)){
            productShelvesLogModel.setCreateTime(DatetimeUtil.stringToDate(startTime+" 00:00:01").getTime()/1000);
            req.setAttribute("startTime", startTime);
        }
        if(endTime!=null && !"".equals(endTime)){
            productShelvesLogModel.setEndTime1(Long.toString(DatetimeUtil.stringToDate(endTime+" 23:59:59").getTime()/1000));
            req.setAttribute("endTime", endTime);
        }
        page.setNumPerPage(5000);
        List<ProductShelvesLogModel> listShelvesLog = productShelvesManager.getProductShelvesLogList(productShelvesLogModel, page);
        productShelvesManager.exportShelvesExcelLog(listShelvesLog, response);
        
        return null;
    }
    
    /**
     * 上下架 审核 操作
     *
     * @return
     */
    @RequestMapping(value = "/examine")
    @ResponseBody
    public String shelvesExamine(ProductShelvesModel productShelvesModel,HttpServletRequest req){
        Map<String,String> map = getUserMap(req);
        
        if(productShelvesModel.getStatus()==2){
            productShelvesModel.setReason("");
        }
        productShelvesModel.setUpdateBy(Long.parseLong(map.get("userId")));
        productShelvesModel.setUpdateUserName(map.get("userName"));
        productShelvesModel.setUpdateTime(new Date().getTime()/1000);
        
        String resultStr = productShelvesManager.updateShelves(productShelvesModel);
        
        String result = "";
        if(resultStr!=null && !"".equals(resultStr)){
            result = new AjaxObject().newErrorForward("操作失败","info/shelves/list").toString();
            
        }else{
            result = new AjaxObject().newOkForward("操作成功","info/shelves/list").toString();
        }
        return result;
    }
    
    /**
     * 上下架 操作
     *
     * @return
     */
    @RequestMapping(value = "/operation")
    @ResponseBody
    public String shelvesOperation(Long[] ids,String shelvesType,String reason,HttpServletRequest req){
        Map<String,String> map = getUserMap(req);
        if(map!=null){
            String resultStr = "";
            
            resultStr = productShelvesManager.shelvesOperation(ids, shelvesType,reason, map);
            String result = "";
            
            if(resultStr!=null && !"".equals(resultStr)){
                result = new AjaxObject().newErrorForward("操作失败!"+resultStr,"info/product/list").toString();
                
            }else{
                result = new AjaxObject().newOkForward("操作成功","info/product/list").toString();
            }
            return result;
        }else{
            return new AjaxObject().newError("请先登录！").toString();
        }
        
    }
    
    /**
     * 待上架列表页
     *
     * @return
     */
    @RequestMapping(value = "/wait_shelves")
    public String waitShelves(ProductShelvesTmpModel productShelvesTmpModel,HttpServletRequest req,Page page){
        List<ProductShelvesTmpModel> shelvesTmpList = null;
        try {
            shelvesTmpList = productShelvesManager.getShelvesTmpList(productShelvesTmpModel, page);
        } catch (Exception e) {
            logger.error("",e);
            return InfoUtil.ERROR_500JSP;
        }
        req.setAttribute("shelvesTmpList", shelvesTmpList);
        return "info/shelves/wait_shelves";
    }
    
    
    @Value("#{propertyConfigurerForProject2['wait_shelves_export_num']}")
    private Integer waitShelvesExportNum;

    /**
     * 待上架列表 导出Excel
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public String exportWaitShelves(ProductShelvesTmpModel productShelvesTmpModel,HttpServletRequest req,String page,HttpServletResponse response){
        List<ProductShelvesTmpModel> shelvesTmpList = null;
        Page page1 = new Page();
        
        page1.setPageNum(Integer.parseInt(page));
        page1.setNumPerPage(waitShelvesExportNum);
        try {
            shelvesTmpList = productShelvesManager.getShelvesTmpList(productShelvesTmpModel, page1);
        } catch (Exception e) {
            logger.error("",e);
            return null;
        }
        exportProductExcel(response, shelvesTmpList,"config/excel/waitShelves.xls", req,"waitShelves.xls");
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
}
