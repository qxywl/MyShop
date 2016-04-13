<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
<!--
	.searchBar ul.searchContent li { width:auto ;padding:2px 20px 0px 5px;}
	.searchBar label { float:left; width:auto; padding-right: 10px}
-->
</style>
<cas:paginationForm action="${contextPath}/category/salelog/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/category/salelog/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>分类ID</label>
                    <input type="text" name="categoryId" value="${saleCategoryOperateLog.categoryId }"/>
                </li>
                 <li>
                    <label>分类名称</label>
                    <input type="text" name="categoryName" value="${saleCategoryOperateLog.categoryName }"/>
                </li>
                 <li>
                    <label>分类编号</label>
                    <input type="text" name="categoryCode" value="${saleCategoryOperateLog.categoryCode }"/>
                </li>
                 <li>
                    <label>操作用户</label>
                    <input type="text" name="operateUser" value="${saleCategoryOperateLog.operateUser }"/>
                </li>
                <li>
                    <label>记录时间</label>
                    	<!-- <input type="text" name="logDateFrom" value=""/> -->
                    	<!-- <input type="text" name="logDateTo" value=""/> -->
                    	<input type="text" name="logDateFrom" value='<fmt:formatDate value="${saleCategoryOperateLog.logDateFrom }" pattern="yyyy-MM-dd HH:mm:ss" />' 
                    		class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
                    	&nbsp;->&nbsp;
						<!-- <a class="inputDateButton" href="javascript:;">选择</a> &nbsp;：&nbsp; -->
                    	<input type="text" name="logDateTo" value='<fmt:formatDate value="${saleCategoryOperateLog.logDateTo }" pattern="yyyy-MM-dd HH:mm:ss" />' 
                    		class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
						<!-- <a class="inputDateButton" href="javascript:;">选择</a> &nbsp;：&nbsp; -->
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
       	  <li><a class="icon" href="${contextPath}/category/salelog/export" target="dwzExport" t title="确定要导出本页记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="150">基础分类ID</th>
                <th width="150">基础分类名称</th>
                <th width="100">基础分类编码</th>
                <th width="100">父分类ID</th>
                <th width="100">父分类名称</th>
                <th width="100">操作类型</th>
                <th width="100">操作者</th>
                <th width="100">最近操作时间 </th>
                <th width="100">日志记录时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="categoryLog" items="${list}">
	            <tr target="slt_uid" rel="${categoryLog.id}" style="text-align: center;">
	                <td>${categoryLog.categoryId}</td>
	                <td>${categoryLog.categoryName}</td>
	                <td>${categoryLog.categoryCode}</td>
	                <%-- <td><fmt:formatDate value="${item.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
	                <td>${categoryLog.parentId}</td>
	                <td>${categoryLog.parentCode}</td>
	                <td><%-- ${categoryLog.operateType} --%>
	                	<c:choose>
	                		<c:when test="${categoryLog.operateType == 1 }">插入</c:when>
	                		<c:when test="${categoryLog.operateType == 2 }">修改</c:when>
	                		<c:when test="${categoryLog.operateType == 3 }">删除</c:when>
	                		<c:when test="${categoryLog.operateType == 4 }">删除子分类</c:when>
	                	</c:choose> </td>
	                <td>${categoryLog.operateUser}</td>
	                <td><fmt:formatDate value="${categoryLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td><fmt:formatDate value="${categoryLog.logDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>