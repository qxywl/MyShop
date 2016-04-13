<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/report/to_report_list" page="${page}">
	<input type="hidden" name="reportName" value="${reportModel.reportName }"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/report/to_report_list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
    
    <div class="searchBar">
	            <table width="100%" class="searchContent">
			<tbody><tr>
				<td>
					报表名称：<input type="text" name="reportName" value="${reportModel.reportName }"/>
				</td>
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div></td>
			</tr>
		</tbody></table>
	        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
       	  <li><a class="add" href="${contextPath}/report/to_add" target="navTab" mask="true" width="600" height="300" ><span>新增报表</span></a></li>
       	  <li><a class="edit" href="${contextPath}/report/to_edit/{slt_uid}" target="navTab" mask="true" width="600" height="300" ><span>编辑报表</span></a></li>
       	  <li><a class="delete" target="selectedTodo" rel="ids"  href="${contextPath}/report/delete" title="确认要删除?"><span>删除</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
          	 	<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>      
                <th width="100">报表名称</th>
                <th width="100">展示列中文名称</th>
                <th width="100">展示列英文名称</th>
                <th width="100">报表sql</th>
                <th width="100">报表总数sql</th>
            </tr>
        </thead>
        
        <tbody>
            <c:if test="${reportList!=null}">
	            <c:forEach var="report" items="${reportList}">
	            <tr target="slt_uid" rel="${report.reportId}" style="text-align: center;height:50px;">
	                <td><input name="ids" value="${report.reportId}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${report.reportName}</td>
	                <td>${report.reportShowName}</td>
	                <td>${report.reportShowEnName}</td>
	                <td>${report.reportSql}</td>
	                <td>${report.reportCountSql}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
        
        
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>
<script>

</script>