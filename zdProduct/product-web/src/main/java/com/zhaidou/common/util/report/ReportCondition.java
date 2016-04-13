/*
 * Copyright 2010 Mttang.com All right reserved. This software is the confidential and proprietary information of
 * Mttang.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Mttang.com.
 */
package com.zhaidou.common.util.report;

import java.util.List;
import java.util.Map;

/**
 * 类ReportCondition.java的实现描述：TODO 类实现描述
 * 
 * @author 郑斌 2010-6-18 下午07:15:52
 */
public class ReportCondition {

    /**
     * 文本框
     * @param name
     * @param property
     * @param value
     * @return
     * 
     * <td width="80px" align="right">商品名称：</td>
                <td><input type="text" name="productName" size="20" value="${productInfoAuthModel.productName }" class="textInput"></td>
     */
    public String input(String name, String property, String value) {
        if(value==null||value.equals("null")){
            value="";
        }
        StringBuilder str = new StringBuilder();
        str.append("<td width='80px' align='right'>"+name+":</td>").append("<td><input type='text' name='").append(property).append("'  value='").append(
                                                                                                                         value).append(
                                                                                                                                       "' /></td>");
        return str.toString();
    }
    /**
     *  下拉框
     * @param name
     * @param property
     * @param value
     * @param list
     * @return
     */
    public String select(String name, String property, String value, List<Map<Object, Object>> list) {
        if(value==null||value.equals("null")){
            value="";
        }
        StringBuilder str = new StringBuilder();
        if (list.size() > 0) {
            str.append("<td width='80px' align='right'>"+name+":</td>").append("<td><select    name='").append(property).append("' >");
            for (int i = 0; i < list.size(); i++) {
                str.append("<option   value='").append(list.get(i).get("ID")).append("' >").append(
                                                                                                   list.get(i).get(
                                                                                                                   "NAME")).append(
                                                                                                                                   "</option>");
            }
            str.append("  </select></td>");
        }
        return str.toString();
    }

    /**
     *   时间控件
     * @param name
     * @param property
     * @param value
     * @return
     * 
     * <input type="text" class="date" name="startTime2" size="15" dateFmt="yyyy-MM-dd" value="${startTime2}" readonly="true"/>
     */
    public String time(String name, String property, String value) {
        if(value==null||value.equals("null")){
            value="";
        }
        StringBuilder str = new StringBuilder();
//      <input name="tstart" size="26" id="tstart" class="Wdate"
//            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
//            style="width:160px" />
        str.append("<td width='80px' align='right'>"+name+":</td>").append("<td><input  type='text' class='date'").append("' name='").append( property).
        append( "'  dateFmt='yyyy-MM-dd' value='").
        append( value).append("'  readonly='readonly' /></td>");
        return str.toString();
    }

    /**
     * 4  起始时间     5结束时间
     * @param name
     * @param property
     * @param value
     * @param i
     * @return
     * <fmt:formatDate value="${date}" type="date"/>
     * <input type="text" class="date" name="startTime2" size="15" dateFmt="yyyy-MM-dd" value="${startTime2}" readonly="true"/>
                     -
       <input type="text" class="date" name="endTime2" size="15" dateFmt="yyyy-MM-dd" value="${endTime2}" readonly="true"/>
     */
    public String timeSegment(String name, String property1,String property2, String value1,String value2) {
        if(value1==null||value1.equals("null")){
            value1="";
        }
        if(value2==null||value2.equals("null")){
            value2="";
        }
        StringBuilder str = new StringBuilder();
        str.append("<td width='80px' align='right'>"+name+":</td>").append("<td><input type='text' class='date' dateFmt='yyyy-MM-dd' size='10' ").append(" name='").append(property1).
        append( "' value='").
        append( value1).append("' readonly='readonly'/>-<input  type='text' class='date' dateFmt='yyyy-MM-dd' size='10'").append(" name='").append(property2).
        append( "' value='").append( value2).append("' readonly='readonly'/>");
        return str.toString();
    }

}
