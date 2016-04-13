<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
<!--
	.searchBar ul.searchContent li { width:auto ;padding:2px 20px 0px 5px; position: relative;}
	.searchBar label { float:left; width:auto; padding-right: 10px}
-->
</style>
<cas:paginationForm action="${contextPath}/filter-mount/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm>
<form method="post" action="${contextPath}/filter-mount/log/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>分类编号</label>
                    <input type="text" name="categoryCode" value="${filterMountLog.categoryCode }"/>
                </li>
                 <li>
                    <label>操作者</label>
                    <input type="text" name="operateUser" value="${filterMountLog.operateUser }"/>
                </li>
                <li>
                    <label>记录时间</label>
                    	<input type="text" name="logDateFrom" value='<fmt:formatDate value="${filterMountLog.logDateFrom }" pattern="yyyy-MM-dd HH:mm:ss" />'
                    		class="date textInput readonly" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20" />
						<!-- <a class="inputDateButton" href="javascript:;" style="display: block;">选择</a> -->
                    	&nbsp;->&nbsp;
                    	<input type="text" name="logDateTo"	value='<fmt:formatDate value="${filterMountLog.logDateTo }" pattern="yyyy-MM-dd HH:mm:ss" />' 
                    		class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20" />
						<!-- <a class="inputDateButton" href="javascript:;" style="display: block;">选择</a> -->
                </li>
            </ul>
            <div class="subBar">
                <ul>                        
                    <li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
                </ul>
            </div>
        </div>
    </div>
</form>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
       	  <li><a class="icon" href="${contextPath}/filter-mount/log/export" target="dwzExport" t title="确定要导出本页记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="100">筛选项ID</th>
                <th width="100">分类号</th>
                <th width="100">分类编码</th>
                <th width="100">属性项ID</th>
                <th width="100">属性项编码</th>
                <th width="100">属性值ID</th>
                <th width="100">属性值编码</th>
                <th width="100">排序值</th>
                <th width="100">操作类型 </th>
                <th width="100">操作人</th>
                <th width="150">操作时间 </th>
                <th width="150">日志记录时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="filterMountLog" items="${list}">
	            <tr target="slt_uid" rel="${filterMountLog.id}" style="text-align: center;">
	                <td>${filterMountLog.relationId}</td>
	                <td>${filterMountLog.categoryId}</td>
	                <td>${filterMountLog.categoryCode}</td>
	                <td>${filterMountLog.attrId}</td>
	                <td>${filterMountLog.attrCode}</td>
	                <td>${filterMountLog.attrvalueId}</td>
	                <td>${filterMountLog.attrvalueCode}</td>
	                <td>${filterMountLog.orderNumber}</td>
	                <td>
	                	<c:forEach var="optType" items="${optTypeMap}">
	                		<c:if test="${optType.key == filterMountLog.optType }">${optType.value }</c:if>
	                	</c:forEach>	
	                </td>
	                <td>${filterMountLog.operateUser}</td>
	                <td><fmt:formatDate value="${filterMountLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td><fmt:formatDate value="${filterMountLog.logDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>