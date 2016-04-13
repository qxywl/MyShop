package com.zhaidou.common.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhaidou.common.model.ReportModel;
import com.zhaidou.framework.util.page.Page;

public interface ReportManager {
    
    public Map<String,Object> getReportHTML(String reportId,HttpServletRequest req,Page page) throws Exception;
    
    public void addReport(ReportModel reportModel)throws Exception;
    
    public List<ReportModel> getReportList(ReportModel reportModel, Page page) throws Exception;
    
    public ReportModel getReportById(Integer reportId) throws Exception;
    
    public void updateReport(ReportModel reportModel)throws Exception;
    
    public void exportExcel(String titleName,List<String> thName,List<String> enName,List<Map<Object,Object>> list,HttpServletResponse resp)throws Exception;
    
    public void deleteById(Integer reportId)throws Exception;
}
