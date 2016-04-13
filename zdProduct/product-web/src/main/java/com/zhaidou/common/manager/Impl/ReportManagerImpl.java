package com.zhaidou.common.manager.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.MapKey;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.zhaidou.common.manager.ReportManager;
import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.common.model.ReportModel;
import com.zhaidou.common.service.ReportConService;
import com.zhaidou.common.service.ReportService;
import com.zhaidou.common.util.ExportUtil;
import com.zhaidou.common.util.report.ReportUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.product.info.util.InfoUtil;

@Service("reportManager")
public class ReportManagerImpl implements ReportManager {
    
    @Resource
    private ReportService reportService;
    @Resource
    private ReportConService reportConService;
    
    /**
     * 获取列表显示 HTML
     */
    @Override
    public Map<String,Object> getReportHTML(String reportId,HttpServletRequest req,Page page) throws Exception {
        Map<String,Object> returnMap = new HashMap<String, Object>();
        
            StringBuffer sb = new StringBuffer();
            ReportConModel reportConModel = new ReportConModel();
            reportConModel.setReportId(Integer.parseInt(reportId));
            List<ReportConModel> reportConList = reportConService.getConListByReportId(reportConModel);
            
            //获取筛选条件sql
            sb.append(ReportUtil.getSql(reportConList, req));
            
            ReportModel reportModel = reportService.getReportById(Integer.parseInt(reportId));
            
            String sql = "";
            String sqlCount = "";
            
            if(!"".equals(sb.toString())){
                sql = reportModel.getReportSql()+sb.toString();
                sqlCount = reportModel.getReportCountSql()+sb.toString();
            }else{
                sql = reportModel.getReportSql();
                sqlCount = reportModel.getReportCountSql();
            }
            //获取查询记录总数
            reportModel.setReportCountSql(sqlCount);
            Long count = reportService.getReportSqlCount(reportModel);
            //设置总记录数
            page.setTotalCount(count);
            sql = sql + " limit "+(page.getPageNum()-1)*page.getNumPerPage()+","+page.getNumPerPage();
            
            //每列中文标题
            String showName = reportModel.getReportShowName();
            String[] showNames = showName.split(",");
            List<String> titleName = new ArrayList<String>();
            for(String str :showNames){
                titleName.add(str);
            }
            //每列英文标题
            String showEnName = reportModel.getReportShowEnName();
            String[] showEnNames = showEnName.split(",");
            List<String> titleEnName = new ArrayList<String>();
            for(String str :showEnNames){
                titleEnName.add(str);
            }
            
            //获取查询列表
            reportModel.setReportSql(sql);
            List<Map<Object,Object>> list = reportService.getReportSql(reportModel);
            
            //处理查询结果 Map 中有NULL值的数据
            boolean flag = false;
            for(Map<Object,Object> objMap:list){
                for(String enName:titleEnName){
                    flag = false;
                    for(Object key:objMap.keySet()){
                        if(enName.equals(key)){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        objMap.put(enName, "");
                    }
                }
            }
            
            returnMap.put("excelName", reportModel.getReportName());
            returnMap.put("titleEnName", titleEnName);
            returnMap.put("titleName", titleName);
            returnMap.put("list", list);
//        }
        return returnMap;
    }
    
    @Override
    public void addReport(ReportModel reportModel) throws Exception {
        
        reportService.addReport(reportModel);
    }

    @Override
    public List<ReportModel> getReportList(ReportModel reportModel, Page page) throws Exception {
        
        return reportService.getReportList(reportModel, page);
    }

    @Override
    public ReportModel getReportById(Integer reportId) throws Exception {
        ReportModel reportModel = reportService.getReportById(reportId);
        return reportModel;
    }

    @Override
    public void updateReport(ReportModel reportModel) throws Exception {
        reportService.updateReport(reportModel);
    }

    @Override
    public void exportExcel(String titleName,List<String> thNames, List<String> enName,List<Map<Object, Object>> list,HttpServletResponse resp) throws Exception {
        
        try {
            ServletOutputStream outputStream = resp.getOutputStream();  
            String fileName = new String(titleName.getBytes(), "ISO8859_1");  
            resp.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            
             
            // 创建一个workbook 对应一个excel应用文件  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            // 在workbook中添加一个sheet,对应Excel文件中的sheet  
            XSSFSheet sheet = workBook.createSheet(titleName);  
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
            XSSFCell cell = null; 
            // 构建表头  
            XSSFRow headRow = sheet.createRow(0);  
             
            for (int i = 0; i < thNames.size(); i++)  
            {  
                cell = headRow.createCell(i);  
                cell.setCellStyle(headStyle); 
                cell.setCellValue(thNames.get(i));  
            }
            XSSFRow bodyRow = null;
            for(int x = 0; x < list.size();x++){
                Map<Object,Object> map = list.get(x);
                bodyRow = sheet.createRow(x+1);
                for (int i = 0; i < enName.size(); i++)  
                {  
                    for(Object key:map.keySet()){
                        if(enName.get(i).equals(key)){
                            cell = bodyRow.createCell(i);  
                            cell.setCellStyle(bodyStyle); 
                            cell.setCellValue(String.valueOf(map.get(key)));
                        }
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
            throw new ZhaidouRuntimeException("商品编辑模版  导出 失败！"+e);
        }
    }

    @Override
    public void deleteById(Integer reportId) throws Exception {
        ReportModel reportModel = new ReportModel();
        reportModel.setReportId(reportId);
        reportService.deleteById(reportModel);
        
    }
}
