package com.zhaidou.common.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhaidou.common.model.ReportConModel;


public interface ReportConManager {
    
    public Map<String,String> getReportConHTML(String reportId,HttpServletRequest req) throws Exception;
    
    public void addReportCon(ReportConModel reportConModel) throws Exception;
    
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel) throws Exception;
    
    public void updateReportCon(ReportConModel reportConModel) throws Exception;
    
    public void deleteReportConByReportId(Integer reportId) throws Exception;
    
    public void deleteByIds(Map<String,Object> map) throws Exception;
    
    public void deleteById(Integer reportId) throws Exception;
}
