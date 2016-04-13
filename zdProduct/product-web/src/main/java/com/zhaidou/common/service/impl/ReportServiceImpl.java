package com.zhaidou.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.common.dao.ReportDao;
import com.zhaidou.common.model.ReportModel;
import com.zhaidou.common.service.ReportService;
import com.zhaidou.framework.util.page.Page;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
    
    @Resource
    private ReportDao reportDao;
    
    @Override
    public List<Map<Object,Object>> getReportSql(ReportModel reportModel) throws Exception {
        
        return reportDao.getReportSql(reportModel);
    }

    @Override
    public ReportModel getReportById(Integer reportId) throws Exception {
        ReportModel reportModel = new ReportModel();
        reportModel.setReportId(reportId);
        return reportDao.queryOne(reportModel);
    }

    @Override
    public Long getReportSqlCount(ReportModel reportModel) throws Exception {
        
        return reportDao.getReportSqlCount(reportModel);
    }

    @Override
    public void addReport(ReportModel reportModel) throws Exception {
        
        reportDao.insert(reportModel);
    }

    @Override
    public List<ReportModel> getReportList(ReportModel reportModel, Page page) throws Exception {
        
        return reportDao.queryListPage(reportModel, page.getPageNum(), page.getNumPerPage());
    }

    @Override
    public void updateReport(ReportModel reportModel) throws Exception {
        reportDao.update(reportModel);
    }

    @Override
    public void deleteById(ReportModel reportModel) throws Exception {
       
        reportDao.delete(reportModel);
    }
    
}
