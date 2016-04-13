package com.zhaidou.common.util.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhaidou.common.model.ReportConModel;
import com.zhaidou.framework.util.date.DatetimeUtil;

public class ReportUtil {
    
    public static Map<String,Map> reportMap = new HashMap<String,Map>();
    
    public static String removeUnderline(String str){
        while(str.indexOf("_")>0){
            int index = str.indexOf("_");
            str = str.replace(str.substring(index, index+2),str.substring(index+1, index+2).toUpperCase());
        }
        return str;
    }
    
    public static String getSql(List<ReportConModel> reportConList,HttpServletRequest req){
        StringBuffer sb = new StringBuffer();
        
        if(reportConList!=null && reportConList.size()>0){
            for(ReportConModel reportConMod:reportConList){
                
                String name = reportConMod.getConProperty();
                String propertyName = ReportUtil.removeUnderline(name);
                String nameValue = req.getParameter(propertyName);
                String selectName = reportConMod.getConSelectPropertyName();
                
                int typeNum = reportConMod.getConType();
                if(nameValue!=null && !"".equals(nameValue)){
                    if(typeNum==2){
                        sb.append(" and "+selectName+" like '%"+nameValue+"%' ");
                    }else if(typeNum==4){
                        Long startTime = DatetimeUtil.stringToDate(nameValue+" 00:00:01").getTime()/1000;
                        Long endTime = DatetimeUtil.stringToDate(nameValue+" 23:59:59").getTime()/1000;
                        sb.append(" and "+selectName+">="+startTime+" and "+selectName+"<="+endTime);
                    }else if(typeNum==5){
                        String num = name.substring(name.length()-1, name.length());
                        if("1".equals(num)){
                            Long startTime = DatetimeUtil.stringToDate(nameValue+" 00:00:01").getTime()/1000;
                            sb.append(" and "+selectName+">="+startTime);
                        }else{
                            Long endTime = DatetimeUtil.stringToDate(nameValue+" 23:59:59").getTime()/1000;
                            sb.append(" and "+selectName+"<="+endTime);
                        }
                    }else{
                        sb.append(" and "+selectName+"="+nameValue);
                    }
                }
                
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(ReportUtil.removeUnderline("d_derg_sdfgfdg"));
        int index = "d_derg_sdfgfdg".indexOf("_");
        System.out.println(index);
        System.out.println("d_derg_sdfgfdg".substring(1, 3));
        System.out.println("d_derg_sdfgfdg".substring(0,"d_derg_sdfgfdg".length()-1));
    }
}
