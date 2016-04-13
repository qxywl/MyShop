package com.zhaidou.common.dao;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.framework.dao.IDao;

public interface ReportConDao extends IDao {
    
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel)throws Exception;
    
    public List<Map<Object,Object>> getSelectValue(ReportConModel reportConModel)throws Exception;
    
    public void deleteReportConByReportId(Integer reportId)throws Exception;
    
    public void deleteByIds(Map<String,Object> map)throws Exception;
    
}
