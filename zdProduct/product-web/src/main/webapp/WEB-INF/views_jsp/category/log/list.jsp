<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
<!--
	.searchBar ul.searchContent li { width:auto ;padding:2px 20px 0px 5px;}
	.searchBar label { float:left; width:auto; padding-right: 10px}
-->
</style>
<cas:paginationForm action="${contextPath}/category/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/category/log/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent" style="padding:0px">
                <li>
                    <label>分类ID</label>
                    <input type="text" name="categoryId" value="${baseCategoryOperateLog.categoryId }"/>
                </li>
                 <li>
                    <label>分类名称</label>
                    <input type="text" name="categoryName" value="${baseCategoryOperateLog.categoryName }"/>
                </li>
                <li>
                    <label>分类编号</label>
                    <input type="text" name="categoryCode" value="${baseCategoryOperateLog.categoryCode }"/>
                </li>
             	<li>
                    <label>操作用户</label>
                    <input type="text" name="operateUser" value="${baseCategoryOperateLog.operateUser }"/>
                </li>
                <li>
                    <label>记录时间</label>
                    	<input type="text" name="logDateFrom" class="date" value='<fmt:formatDate value="${baseCategoryOperateLog.logDateFrom }" pattern="yyyy-MM-dd HH:mm:ss" />' 
                    		dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
                    	&nbsp;->&nbsp;
                    	<input type="text" name="logDateTo" class="date" value='<fmt:formatDate value="${baseCategoryOperateLog.logDateTo }" pattern="yyyy-MM-dd HH:mm:ss" />'
                    		dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
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
<%--           <li><a class="add"  target="dialog" rel="" mask="true" width="630" height="430" href="${contextPath}/category/log/add"><span>添加品牌</span></a></li>
          <li><a class="edit" target="dialog" rel="" mask="true" width="630" height="430" href="${contextPath}/category/log/update/{slt_uid}"><span>编辑品牌</span></a></li>
          <li><a class="edit" target="dialog" rel="" mask="true" width="800" height="430" href="${contextPath}/category/log/detail/{slt_uid}"><span>查看详情</span></a></li>  
       	  <li><a class="delete" target="ajaxTodo"    rel="" href="${contextPath}/category/log/delete/{slt_uid}" title="确认要删除?"  onsubmit="return navTabSearch(this);"><span>删除品牌</span></a></li> --%>
       	  <li><a class="icon" href="${contextPath}/category/log/export" target="dwzExport"  title="确定要导出本页记录吗?"><span>导出excel</span></a></li>
          <%-- <li><a class="icon" href="${contextPath}/sale-category/product/upload_excel" target="dialog"  close ="navTabSearch(this);" width="630" height="430" title=""><span>导入excel</span></a></li> --%>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
               <!--  <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->            
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
	            <tr target="slt_uid" rel="${categoryLog.id}" style="text-align: left;">
	               <%--  <td><input name="ids" value="${categoryLog.id}" type="checkbox" class="checkboxCtrl"></td> --%>
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