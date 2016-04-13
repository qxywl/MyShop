package com.zhaidou.common.manager.Impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zhaidou.common.manager.ReportConManager;
import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.common.service.ReportConService;
import com.zhaidou.common.util.report.ReportCondition;
import com.zhaidou.common.util.report.ReportUtil;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;

@Service("reportConManager")
public class ReportConManagerImpl implements ReportConManager {
    
    @Resource
    private ReportConService reportConService;
    
    /**
     * 获取条件查询 HTML
     */
    @Override
    public Map<String,String> getReportConHTML(String reportId,HttpServletRequest req) throws Exception {
        
        ReportConModel reportConModel = new ReportConModel();
        reportConModel.setReportId(Integer.parseInt(reportId));
        //获取该报表下所有查询条件
        List<ReportConModel> conList = reportConService.getConListByReportId(reportConModel);
        
        //记录已添加过的时间段 propertyName
        List<String> nameList = new ArrayList<String>();
        
        StringBuilder sb = new StringBuilder();
        StringBuilder hiddenValue = new StringBuilder();
        
        Map<String,String> map = new HashMap<String, String>();
        
        if(conList!=null && conList.size()>0){
            ReportCondition condition = new ReportCondition();
            int i = 1;
            for(ReportConModel conModel:conList){
                if(i%4==1){
                    sb.append("<tr>");
                }
                String name = conModel.getConName();
                String propertyName = ReportUtil.removeUnderline(conModel.getConProperty());
                String value = req.getParameter(propertyName);
                
                if(value!=null && !"".equals(value)){
                    hiddenValue.append("<input type='hidden' name='"+propertyName+"' value='"+value+"' />");
                }
                
                if(conModel.getConType()==1){
                    //文本输入框类型
                    sb.append(condition.input(name, propertyName, value));
                }else if(conModel.getConType()==2){
                    //下拉框类型
                    sb.append(condition.input(name, propertyName, value));
                }else if(conModel.getConType()==3){
                    //下拉框类型
                    if(conModel.getConSelectSql()!=null && !"".equals(conModel.getConSelectSql())){
                        List<Map<Object,Object>> list = reportConService.getSelectValue(conModel.getConSelectSql());
                        sb.append(condition.select(name, propertyName, value, list));
                    }else{
                        throw new ZhaidouRuntimeException("下拉框类型,请设置查询SQL!!");
                    }
                }else if(conModel.getConType()==4){
                    //时间类型
                    sb.append(condition.time(name, propertyName,value));
                }else if(conModel.getConType()==5){
                    //判断是否已添加
                    boolean flag = true;
                    if(nameList.size()>0){
                        for(String str:nameList){
                            if(str.equals(name)){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(flag){
                        //时间段类型  以查询字段为基础    name1 为开始时间  name2 为结束时间
                        String propertyName2 = "";
                        String value2 = "";
                        for(ReportConModel conMod:conList){
                            String str = ReportUtil.removeUnderline(conMod.getConProperty());
                            if(str.indexOf(propertyName.substring(0, propertyName.length()-1))>=0){
                                String aa = str.substring(propertyName.length()-1,propertyName.length());
                                String bb = propertyName.substring(propertyName.length()-1,propertyName.length());
                                if(!aa.equals(bb)){
                                    propertyName2 = str;
                                    nameList.add(name);
                                    value2 = (String)req.getParameter(propertyName2);
                                    if(value2!=null && !"".equals(value2)){
                                        hiddenValue.append("<input type='hidden' name='"+propertyName2+"' value='"+value2+"' />");
                                    }
                                }
                            }
                        }
                        sb.append(condition.timeSegment(name, propertyName, propertyName2, value,value2));
                    }
                }
                if(i==conList.size()){
                    sb.append("<td><div class='button'><div class='buttonContent'><button type='submit'>搜索</button></div></div></td>");
                    if(!sb.toString().substring(sb.toString().length()-5, sb.toString().length()).equals("</tr>")){
                        sb.append("</tr>");
                    }
                }
                if(i%4==0){
                    sb.append("</tr>");
                }
                i++;
            }
        }
        map.put("content", sb.toString());
        map.put("hiddenValue", hiddenValue.toString());
        return map;
    }

    @Override
    public void addReportCon(ReportConModel reportConModel) throws Exception {
        reportConService.addReportCon(reportConModel);
    }

    @Override
    public List<ReportConModel> getConListByReportId(ReportConModel reportConModel) throws Exception {
        return reportConService.getConListByReportId(reportConModel);
    }

    @Override
    public void updateReportCon(ReportConModel reportConModel) throws Exception {
        reportConService.updateReportCon(reportConModel);
    }
    
    @Override
    public void deleteReportConByReportId(Integer reportId) throws Exception {
        reportConService.deleteReportConByReportId(reportId);
    }

    @Override
    public void deleteByIds(Map<String,Object> map) throws Exception {
        reportConService.deleteByIds(map);
    }

    @Override
    public void deleteById(Integer reportId) throws Exception {
        reportConService.deleteById(reportId);
    }
    
}
