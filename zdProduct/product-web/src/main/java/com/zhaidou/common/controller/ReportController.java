package com.zhaidou.common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhaidou.common.manager.ReportConManager;
import com.zhaidou.common.manager.ReportManager;
import com.zhaidou.common.model.ReportConListModel;
import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.common.model.ReportModel;
import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.util.ExcelUtil;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value="/report")
public class ReportController {
    private static Logger logger = Logger.getLogger(ReportController.class);
    
    @Resource
    private ReportConManager reportConManager;
    @Resource
    private ReportManager reportManager;
    
    /**
     * 跳转动态列表
     * @param request
     * @param response
     * @return
     * @throws Exception  
     */
    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView report(HttpServletRequest req,Page page,ModelMap map1){
        String reportId = req.getParameter("reportId");
        
        Map<String,Object> map = null;
        try {
            map = reportManager.getReportHTML(reportId,req,page);
        } catch (Exception e) {
            logger.error("",e);
        }
        Map<String,String> hiddenValue = new HashMap<String, String>();
        try {
            hiddenValue = reportConManager.getReportConHTML(reportId,req);
        } catch (Exception e) {
            logger.error("",e);
        }
        
        map1.put("titleEnName", (List<String>)map.get("titleEnName"));
        map1.put("titleName", (List<String>)map.get("titleName"));
        map1.put("list", (List<Map<Object,Object>>)map.get("list"));
        map1.put("reportId", reportId);
        map1.put("searchValue", hiddenValue.get("content"));
        map1.put("hiddenValue", hiddenValue.get("hiddenValue"));
        map1.put("page", page);       
        map1.put("path",req.getContextPath());
        return new ModelAndView("report/report");
    }
    
    @Value("#{propertyConfigurerForProject2['info_examine_export_num']}")
    private Integer infoExamineExportNum;
    
    @RequestMapping(value="/export",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String reportExport(HttpServletRequest req,HttpServletResponse resp, Page page){
        String reportId = req.getParameter("reportId");
        
        String page1 = req.getParameter("page");
        Integer pageNum = 1;
        pageNum = Integer.parseInt(page1);
        page.setPageNum(pageNum);
        page.setNumPerPage(infoExamineExportNum);
        
        try {
            Map<String,Object> map = reportManager.getReportHTML(reportId,req,page);
            List<Map<Object,Object>> list = (List<Map<Object,Object>>)map.get("list");
            List<String> titleEnName = (List<String>)map.get("titleEnName");
            List<String> titleName= (List<String>)map.get("titleName");
            String excelName = (String)map.get("excelName");
            reportManager.exportExcel(excelName, titleName, titleEnName, list, resp);

        } catch (Exception e) {
            logger.error("",e);
            req.setAttribute("errorMsg", e.getMessage());
            return InfoUtil.ERROR_500JSP;
        }
        return null;
    }
    
    /**
     * 跳 内容
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/content",method={RequestMethod.POST,RequestMethod.GET})
    public String getContent(HttpServletRequest req,Page page){
        String reportId = req.getParameter("reportId");
        Map<String,Object> map = null;
        try {
            map = reportManager.getReportHTML(reportId,req,page);
        } catch (Exception e) {
            logger.error("",e);
        }
        if(map!=null){
            req.setAttribute("titleEnName", (List<String>)map.get("titleEnName"));
            req.setAttribute("titleName", (List<String>)map.get("titleName"));
            req.setAttribute("list", (List<Map<Object,Object>>)map.get("list"));
        }
        return "report/reportsearch";
    }
    
    /**
     * 跳 查询条件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/paramet",method={RequestMethod.POST,RequestMethod.GET})
    public String getParamet(HttpServletRequest req){
        String reportId = req.getParameter("reportId");
        Map<String,String> hiddenValue = null;
        try {
            hiddenValue = reportConManager.getReportConHTML(reportId,req);
        } catch (Exception e) {
            logger.error("",e);
        }
        req.setAttribute("reportId", reportId);
        req.setAttribute("content", hiddenValue.get("content"));
        return "report/reportcondition";
    }
    
    
    /**
     * 跳报表列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/to_report_list",method={RequestMethod.POST,RequestMethod.GET})
    public String toReportList(HttpServletRequest req,ReportModel reportModel,Page page){
        List<ReportModel> list = null;
        try {
            list = reportManager.getReportList(reportModel, page);
        } catch (Exception e) {
            logger.error("",e);
        }
        req.setAttribute("reportList", list);
        return "report/report_list";
    }
    
    /**
     * 跳新增报表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/to_add",method={RequestMethod.POST,RequestMethod.GET})
    public String toAddReport(HttpServletRequest req){
        return "report/add_report";
    }
    
    /**
     * 跳编辑报表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/to_edit/{id}",method={RequestMethod.POST,RequestMethod.GET})
    public String toEditReport(@PathVariable Integer id,HttpServletRequest req){
        ReportModel reportModel = null;
        try {
            reportModel = reportManager.getReportById(id);
            List<ReportConModel> reportConList = null;
            if(reportModel!=null){
                ReportConModel reportConModel = new ReportConModel();
                reportConModel.setReportId(id);
                
                reportConList = reportConManager.getConListByReportId(reportConModel);
            }
            req.setAttribute("reportModel", reportModel);
            req.setAttribute("reportConList", reportConList);
        } catch (Exception e) {
            logger.error("",e);
        }
        
        return "report/edit_report";
    }
    
    /**
     * 编辑报表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/edit",method={RequestMethod.POST})
    @ResponseBody
    public String editReport(ReportModel reportModel,ReportConListModel reportConListModel,HttpServletRequest req){
        String result = "";
        try {
            List<Integer> list = new ArrayList<Integer>();
            reportManager.updateReport(reportModel);
            if(reportConListModel!=null && reportConListModel.getReportConList().size()>0){
                for(ReportConModel reportConModel:reportConListModel.getReportConList()){
                    if(reportConModel.getConName()!=null && !"".equals(reportConModel.getConName())){
                        if(reportConModel.getConId()==null){
                            reportConModel.setReportId(reportModel.getReportId());
                            reportConManager.addReportCon(reportConModel);
                            list.add(reportConModel.getConId());
                        }else{
                            reportConManager.updateReportCon(reportConModel);
                            list.add(reportConModel.getConId());
                        }
                    }
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("reportId", reportModel.getReportId());
                map.put("list", list);
                reportConManager.deleteByIds(map);
            }else{
                reportConManager.deleteReportConByReportId(reportModel.getReportId());
            }
            result = AjaxObject.newOkForward("编辑成功","report/to_report_list").toString();
            logger.info("编辑报表成功! 编辑内容：报表主要信息="+JSONUtil.toString(reportModel)+" ,刷选内容="+JSONUtil.toString(reportConListModel));
        } catch (Exception e) {
            logger.error("",e);
            result = AjaxObject.newError("编辑失败").toString();
        }
        return result;
    }
    
    /**
     * 新增报表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/add",method={RequestMethod.POST})
    @ResponseBody
    public String addReport(HttpServletRequest req,ReportModel reportModel,ReportConListModel reportConListModel){
        String result = "";
        try {
            reportManager.addReport(reportModel);
            
            if(reportConListModel!=null && reportConListModel.getReportConList().size()>0){
                for(ReportConModel reportConModel:reportConListModel.getReportConList()){
                    reportConModel.setReportId(reportModel.getReportId());
                    reportConManager.addReportCon(reportConModel);
                }
            }
            
            result = AjaxObject.newOkForward("新增成功","report/to_report_list").toString();
            logger.info("新增报表成功! 添加内容：报表主要信息="+JSONUtil.toString(reportModel)+" ,刷选内容="+JSONUtil.toString(reportConListModel));
        } catch (Exception e) {
            logger.error("",e);
            result = AjaxObject.newError("新增失败").toString();
        }
        
        return result;
    }
    
    /**
     * 删除报表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/delete",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String deleteReport(Integer[] ids,HttpServletRequest req){
        String result = "";
        try {
            if(ids!=null && ids.length>0){
                for(int reportId:ids){
                    reportManager.deleteById(reportId);
                    
                    reportConManager.deleteById(reportId);
                }
            }
            result = AjaxObject.newOkForward("删除成功","report/to_report_list").toString();
        } catch (Exception e) {
            logger.error("删除报表失败!",e);
            result = AjaxObject.newError("删除失败").toString();
        }
        return result;
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
