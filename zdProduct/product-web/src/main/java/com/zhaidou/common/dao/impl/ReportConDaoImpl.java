package com.zhaidou.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.dao.ReportConDao;
import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;

@Repository("reportConDao")
public class ReportConDaoImpl extends BaseDao implements ReportConDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel) throws Exception {
        List<ReportConModel> list = null;
        list = this.getSqlSession().selectList(getNameSpace()+".getConListByReportId",reportConModel);
        return list;
    }
    
    @Override
    public List<Map<Object,Object>> getSelectValue(ReportConModel reportConModel) throws Exception {
        List<Map<Object,Object>> list = null;
        list = this.getSqlSession().selectList(getNameSpace()+".getSelectValue",reportConModel);
        return list;
    }

    @Override
    public void deleteReportConByReportId(Integer reportId) throws Exception {
        this.getSqlSession().delete(getNameSpace()+".deleteReportConByReportId",reportId);
    }

    @Override
    public void deleteByIds(Map<String,Object> map) throws Exception {
        this.getSqlSession().delete(getNameSpace()+".deleteByIds",map);
    }
}
