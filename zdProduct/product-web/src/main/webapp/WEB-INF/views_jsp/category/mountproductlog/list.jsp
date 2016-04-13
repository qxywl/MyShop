<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
<!--
	.searchBar ul.searchContent li { width:auto ;padding:2px 20px 0px 5px;}
	.searchBar label { float:left; width:auto; padding-right: 10px}
-->
</style>
<cas:paginationForm action="${contextPath}/mount-product/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm>
<form method="post" action="${contextPath}/mount-product/log/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>分类编号</label>
                    <input type="text" name="categoryCode" value="${mountProductLog.categoryCode }"/>
                </li>
                 <li>
                    <label>操作者</label>
                    <input type="text" name="operateUser" value="${mountProductLog.operateUser }"/>
                </li>
                <li>
                    <label>记录时间</label>
                    	<input type="text" name="logDateFrom" value='<fmt:formatDate value="${mountProductLog.logDateFrom }" pattern="yyyy-MM-dd HH:mm:ss" />'
                    		class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
                    	&nbsp;->&nbsp;
						<!-- <a class="inputDateButton" href="javascript:;">选择</a> &nbsp;：&nbsp; -->
                    	<input type="text" name="logDateTo"	value='<fmt:formatDate value="${mountProductLog.logDateTo }" pattern="yyyy-MM-dd HH:mm:ss" />' 
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
       	  <li><a class="icon" href="${contextPath}/mount-product/log/export" target="dwzExport" t title="确定要导出本页记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="100">挂载ID</th>
                <th width="100">分类ID</th>
                <th width="100">分类编码</th>
                <th width="100">产品ID</th>
                <th width="100">产品编码</th>
                <th width="100">挂载类型</th>
                <th width="100">排序值</th>
                <th width="100">备注 </th>
                <th width="100">操作类型 </th>
                <th width="100">操作人</th>
                <th width="150">操作时间 </th>
                <th width="150">日志记录时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="mountProductLog" items="${list}">
	            <tr target="slt_uid" rel="${mountProductLog.id}" style="text-align: center;">
	                <td>${mountProductLog.relationId}</td>
	                <td>${mountProductLog.categoryId}</td>
	                <td>${mountProductLog.categoryCode}</td>
	                <td>${mountProductLog.productId}</td>
	                <td>${mountProductLog.productCode}</td>
	                <td>
	                	<c:forEach var="mountType" items="${mountTypeMap}">
	                		<c:if test="${mountType.key == mountProductLog.mountType }">${mountType.value }</c:if>
	                	</c:forEach>	
	                </td>
	                <td>${mountProductLog.orderValue }</td>
	                <td>${mountProductLog.description }</td>
	                <td>
	                	<c:forEach var="optType" items="${optTypeMap}">
	                		<c:if test="${optType.key == mountProductLog.optType }">${optType.value }</c:if>
	                	</c:forEach>	
	                </td>
	                <td>${mountProductLog.operateUser}</td>
	                <td><fmt:formatDate value="${mountProductLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td><fmt:formatDate value="${mountProductLog.logDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>