<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/shelves/log/list" page="${page}">
    <input type="hidden" name="productCode" value="${productShelvesLogModel.productCode }"/>
    <input type="hidden" name="productName" value="${productShelvesLogModel.productName }"/>
    <input type="hidden" name="startTime" value="${startTime}"/>
    <input type="hidden" name="endTime" value="${endTime}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/shelves/log/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
             <table width="100%">
			<tr>
                    <td width="80px" align="right"><label>商品编号：</label></td>
                    <td width="80px" align="right"><input type="text" name="productCode" value="${productShelvesLogModel.productCode }"/></td>
           
                    <td width="80px" align="right"><label>商品名称：</label></td>
                    <td width="80px" align="right"><input type="text" name="productName" value="${productShelvesLogModel.productName }"/></td>
              
                    <td width="80px" align="right"><label>创建时间：</label></td>
                    <td ><input type="text" name="startTime" class="date" value="${startTime}" dateFmt="yyyy-MM-dd" readonly="true" value=""/>
                    -
                     <input type="text" name="endTime" class="date" value="${endTime}" dateFmt="yyyy-MM-dd" readonly="true" value=""/>
                    </td>
                    <td><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></td>
 
		  </tr>
		 </table>
        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
        <!-- mask="true" width="1230" height="530" navTab-->
           <li><a class="icon" 
       	   href="${contextPath}/info/shelves/log_export_excel?productCode=${productShelvesLogModel.productCode}&productName=${productShelvesLogModel.productName }&
       	   status=${productShelvesLogModel.status}&startTime=${startTime }&endTime=${endTime }"  title="实要导出这些记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th width="150">商品名称</th>
                <th width="150">商品编码 </th>
                <th width="100">审核状态</th>
                <th width="100">说明</th>
                <th width="100">创建人名称 </th>
                <th width="100">创建时间 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${listShelvesLog!=null}">
	            <c:forEach var="productShelvesLog" items="${listShelvesLog}">
	            <tr target="slt_uid" rel="${productShelvesLog.productShelvesLog}" style="text-align: center;">
	                <td><input name="ids" value="${productShelvesLog.productShelvesLog}" type="checkbox" class="checkboxCtrl"></td>
	                
	                <td>${productShelvesLog.productName}</td>
	                <td>${productShelvesLog.productCode}</td>
	                <td>
	                	<c:if test="${productShelvesLog.status==1}">待审核</c:if>
	                	<c:if test="${productShelvesLog.status==2}">审核通过</c:if>
	                	<c:if test="${productShelvesLog.status==3}">待同步</c:if>
	                	<c:if test="${productShelvesLog.status==4}">同步成功</c:if>
	                	<c:if test="${productShelvesLog.status==9}">审核不通过</c:if>
	                </td>
	                <td>${productShelvesLog.reason}</td>
	                <td>${productShelvesLog.createUserName}</td>
	                <td>${productShelvesLog.createTimes}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>