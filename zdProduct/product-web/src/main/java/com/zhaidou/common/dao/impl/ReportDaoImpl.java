package com.zhaidou.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhaidou.common.dao.ReportDao;
import com.zhaidou.common.model.ReportModel;
import com.zhaidou.framework.dao.mybatis.impl.BaseDao;

@Repository("reportDao")
public class ReportDaoImpl extends BaseDao implements ReportDao {

    @Override
    public String getNameSpace() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<Map<Object,Object>> getReportSql(ReportModel reportModel) throws Exception {
        List<Map<Object,Object>> list = null;
        list = this.getSqlSession().selectList(getNameSpace()+".getReportSql",reportModel);
        return list;
    }

    @Override
    public Long getReportSqlCount(ReportModel reportModel) throws Exception {
        
        return this.getSqlSession().selectOne(getNameSpace()+".getReportSqlCount",reportModel);
    }
    
}
