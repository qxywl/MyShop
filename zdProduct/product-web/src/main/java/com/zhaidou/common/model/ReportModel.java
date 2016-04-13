package com.zhaidou.common.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReportModel implements Serializable{
    
    private Integer reportId;
    private String reportName;
    private String reportShowName;
    private String reportShowEnName;
    private String reportSql;
    private String reportCountSql;
    private Integer reportType;
    private String x;
    private String y;
    private String xScall;
    
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
    public String getReportName() {
        return reportName;
    }
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    public String getReportShowName() {
        return reportShowName;
    }
    public void setReportShowName(String reportShowName) {
        this.reportShowName = reportShowName;
    }
    public String getReportSql() {
        return reportSql;
    }
    public void setReportSql(String reportSql) {
        this.reportSql = reportSql;
    }
    public Integer getReportType() {
        return reportType;
    }
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }
    public String getxScall() {
        return xScall;
    }
    public void setxScall(String xScall) {
        this.xScall = xScall;
    }
    public String getReportCountSql() {
        return reportCountSql;
    }
    public void setReportCountSql(String reportCountSql) {
        this.reportCountSql = reportCountSql;
    }
    public String getReportShowEnName() {
        return reportShowEnName;
    }
    public void setReportShowEnName(String reportShowEnName) {
        this.reportShowEnName = reportShowEnName;
    }
}
