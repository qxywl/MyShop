package com.zhaidou.common.dao;

import java.util.List;
import java.util.Map;

import com.zhaidou.common.model.ReportModel;
import com.zhaidou.framework.dao.IDao;

public interface ReportDao extends IDao {
    
    public List<Map<Object,Object>> getReportSql(ReportModel reportModel)throws Exception;
    
    public Long getReportSqlCount(ReportModel reportModel)throws Exception;
    
}
