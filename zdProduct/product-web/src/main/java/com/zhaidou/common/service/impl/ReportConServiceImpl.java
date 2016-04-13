package com.zhaidou.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhaidou.common.dao.ReportConDao;
import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.common.service.ReportConService;

@Service("reportConService")
public class ReportConServiceImpl implements ReportConService {
    
    @Resource
    private ReportConDao reportConDao;
    
    @Override
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel) throws Exception {
        
        return reportConDao.getConListByReportId(reportConModel);
    }

    @Override
    public List<Map<Object,Object>> getSelectValue(String conSelectSql) throws Exception {
        ReportConModel reportConModel = new ReportConModel();
        reportConModel.setConSelectSql(conSelectSql);
        return reportConDao.getSelectValue(reportConModel);
    }

    @Override
    public void addReportCon(ReportConModel reportConModel) throws Exception {
        reportConDao.insert(reportConModel);
    }

    @Override
    public void updateReportCon(ReportConModel reportConModel) throws Exception {
        reportConDao.update(reportConModel);
    }

    @Override
    public void deleteReportConByReportId(Integer reportId) throws Exception {
        reportConDao.deleteReportConByReportId(reportId);
    }

    @Override
    public void deleteByIds(Map<String,Object> map) throws Exception {
        reportConDao.deleteByIds(map);
    }

    @Override
    public void deleteById(Integer reportId) throws Exception {
        ReportConModel reportConModel = new ReportConModel();
        reportConModel.setReportId(reportId);
        reportConDao.delete(reportConModel);
    }

}
