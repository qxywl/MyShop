package com.zhaidou.common.service;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.model.ReportConModel;

public interface ReportConService {
    
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel) throws Exception;
    
    public List<Map<Object,Object>> getSelectValue(String conSelectSql) throws Exception;
    
    public void addReportCon(ReportConModel reportConModel) throws Exception;
    
    public void updateReportCon(ReportConModel reportConModel) throws Exception;
    
    public void deleteReportConByReportId(Integer reportId) throws Exception;
    
    public void deleteByIds(Map<String,Object> map) throws Exception;
    
    public void deleteById(Integer reportId) throws Exception;
}
