package com.zhaidou.common.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReportConModel implements Serializable {
    
    private Integer conId;
    private String conName;
    private String conProperty;
    private Integer conType;
    private String conDefault;
    private String conSelectSql;
    private Integer reportId;
    private String conSelectPropertyName;
    
    public Integer getConId() {
        return conId;
    }
    public void setConId(Integer conId) {
        this.conId = conId;
    }
    public String getConName() {
        return conName;
    }
    public void setConName(String conName) {
        this.conName = conName;
    }
    public String getConProperty() {
        return conProperty;
    }
    public void setConProperty(String conProperty) {
        this.conProperty = conProperty;
    }
    public Integer getConType() {
        return conType;
    }
    public void setConType(Integer conType) {
        this.conType = conType;
    }
    public String getConDefault() {
        return conDefault;
    }
    public void setConDefault(String conDefault) {
        this.conDefault = conDefault;
    }
    public String getConSelectSql() {
        return conSelectSql;
    }
    public void setConSelectSql(String conSelectSql) {
        this.conSelectSql = conSelectSql;
    }
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
    public String getConSelectPropertyName() {
        return conSelectPropertyName;
    }
    public void setConSelectPropertyName(String conSelectPropertyName) {
        this.conSelectPropertyName = conSelectPropertyName;
    }
}
