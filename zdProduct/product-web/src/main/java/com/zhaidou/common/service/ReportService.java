package com.zhaidou.common.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.model.ReportModel;
import com.zhaidou.framework.util.page.Page;

public interface ReportService {
    
    public List<Map<Object,Object>> getReportSql(ReportModel reportModel) throws Exception;
    
    public Long getReportSqlCount(ReportModel reportModel) throws Exception;
    
    public ReportModel getReportById(Integer reportId)throws Exception;
    
    public void addReport(ReportModel reportModel)throws Exception;
    
    public List<ReportModel> getReportList(ReportModel reportModel,Page page)throws Exception;
    
    public void updateReport(ReportModel reportModel)throws Exception;

    public void deleteById(ReportModel reportModel)throws Exception;
}
