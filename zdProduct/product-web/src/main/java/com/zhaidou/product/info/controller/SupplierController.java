package com.zhaidou.product.info.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBObject;
import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.Util.StringUtil;
import com.zhaidou.product.brand.util.ExcelUtil;
import com.zhaidou.product.info.model.SupplierModel;
import com.zhaidou.product.info.service.SupplierService;


/**
 * Created by google on 2015/12/10.
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    private static Logger logger = Logger.getLogger(SupplierController.class);
    private static String SupplierList = "supplier/supplier_list"; //供应商列表
    private static String SupplierImport = "supplier/supplier_import"; //下载模板页面

    /**导入*/
	String [] strUploadTitle ={"是否停用","供应商代码","供应商名称","供应商类型编号","供应商类型名称","税务登记号","营业执照","合同开始日期","合同截止日期","联系人一","联系人二"};
	String [] strUploadField ={"","supplierCode","name","","","","","","","",""};
    
    @Resource
    private SupplierService supplierService;

    /**
     * 获取供应商分页信息列表
     * @param page
     * @param supplierModel
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String List(Page page, SupplierModel supplierModel,Map<String, Object> map, HttpServletRequest request){
        List<SupplierModel> supplierModelList = null;
        try {
            supplierModelList  = supplierService.getSupplier(supplierModel, page);
            map.put("supplierModel", supplierModel);
            map.put("supplierModelList", supplierModelList);
            map.put("page", page);
        } catch (Exception e) {
            logger.info("获取供应商分页列表异常=="+e.getMessage());
        }
        return SupplierList;
    }
    
    /**
     * 下载模板处理方法
     * @param response
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/downTemplate", method = {RequestMethod.GET})
    @ResponseBody
    public String downTemplate(HttpServletResponse response){
    	  String strReturn = null;
          AjaxObject returnAjax = null;
          List<SupplierModel> brandList = null;
         
          String fileType=".xlsx";
          String fileName = "供应商导入表单模板.xlsx";
          String sheetName = "供应商表单";
      	
      	List<String> titleList = Arrays.asList(strUploadTitle);
      	List<String> fieldList = Arrays.asList(strUploadField);
      	
      	Workbook book = null;
      	OutputStream os = null;
      	try {
          	
      		 brandList = getTmplateSupplier();
          	 book = new ExcelUtil().writeExcel(fileType,sheetName, titleList, fieldList, brandList);
          	response.reset();
          	response.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtil.toUtf8String(fileName) + "\"");
          	response.setContentType("application/vnd.ms-excel; charset=utf-8");
               os = response.getOutputStream();
               book.write(os);
               returnAjax = AjaxObject.newOk("导出成功");
          } catch (Exception e) {
          	
          	logger.info("export供应商异常信息=="+e.getMessage(),e);
          	returnAjax = AjaxObject.newError("export供应商异常信息=="+e.getMessage());
          	
          } finally {
              if (os != null) {
                  try {
                      os.close();
                  } catch (Exception e) {
                      logger.error("关闭输出流出错", e);
                  }
              }
          }
      	
      	strReturn = returnAjax.toString();
        return strReturn;
    }
    
    /**
     * 跳转下载模板页面
     */
    @RequestMapping(value = "/to_import", method = {RequestMethod.GET, RequestMethod.POST})
    public String toImport(){
    	return SupplierImport;
    }
    
    /**
     * 上传
     * @param request
     * @param response
     * @param file
     * @return
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/importSupplier", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String importSupplier(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam("file") MultipartFile file){
    	
     AjaxObject returnAjax = null;
   	 List<DBObject> brandList = null;
   	 
        String filename = file.getOriginalFilename();
        byte[] buf = null;
        try {
			buf = file.getBytes();
			logger.info(filename + "==" + buf.length);
			if (buf == null) {
	             returnAjax = AjaxObject.newError("文件为空");
	        }else{
        	 String impStr = supplierService.importdSupplier(filename, buf,Arrays.asList(strUploadTitle),Arrays.asList(strUploadField));
           	 returnAjax = AjaxObject.newOk(impStr);
	        }
		 }catch (IOException e) {
			logger.error("读取上传文件出错",e);
			returnAjax = AjaxObject.newError("导入失败："+e.getMessage());
		 }catch (ZhaidouRuntimeException e) {
			 returnAjax = AjaxObject.newError(e.getMessage());
	         logger.error("导入供应商异常信息=="+e.getMessage());
	     }catch (Exception e) {
			logger.error("读取上传文件出错",e);
        	returnAjax = AjaxObject.newError("导入失败：系统错误");
		 }
        String ss = returnAjax.toString();
        return returnAjax.toString();
    } 
    
    /**
     * 模板内容
     * @return
     */
    private List<SupplierModel> getTmplateSupplier() {
    	List<SupplierModel>  list = new ArrayList<SupplierModel>();
    	SupplierModel brandModel = new SupplierModel();
    	
    	brandModel.setSupplierCode(4567897);
    	brandModel.setName("三星手机");
    	list.add(brandModel);
		return list;
	}
}
