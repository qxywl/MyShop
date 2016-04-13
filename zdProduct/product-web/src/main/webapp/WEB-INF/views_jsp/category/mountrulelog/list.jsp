<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
<!--
	.searchBar ul.searchContent li { width:auto ;padding:2px 20px 0px 5px;}
	.searchBar label { float:left; width:auto; padding-right: 10px}
-->
</style>
<cas:paginationForm action="${contextPath}/mount-rule/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm>
<form method="post" action="${contextPath}/mount-rule/log/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>分类编号</label>
                    <input type="text" name="categoryCode" value="${mountRuleLog.categoryCode }"/>
                </li>
                 <li>
                    <label>操作者</label>
                    <input type="text" name="operateUser" value="${mountRuleLog.operateUser }"/>
                </li>
                <li>
                    <label>记录时间</label>
                    	<input type="text" name="logDateFrom" value='<fmt:formatDate value="${mountRuleLog.logDateFrom }" pattern="yyyy-MM-dd HH:mm:ss" />'
                    		class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
                    	&nbsp;->&nbsp;
						<!-- <a class="inputDateButton" href="javascript:;">选择</a> &nbsp;：&nbsp; -->
                    	<input type="text" name="logDateTo"	value='<fmt:formatDate value="${mountRuleLog.logDateTo }" pattern="yyyy-MM-dd HH:mm:ss" />' 
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
<%-- <form id="pagerForm"> 
	缓存搜索条件，在导出excel时会用到这里的条件
	<input type="hidden" name="categoryCode" value="${mountRuleLog.categoryCode }"/>
	<input type="hidden" name="operateUser" value="${mountRuleLog.operateUser }"/>
	<input type="hidden" name="logDateFrom" value="${mountRuleLog.logDateFrom }"/>
	<input type="hidden" name="logDateTo" value="${mountRuleLog.logDateTo }"/>
</form> --%>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
       	  <li><a class="icon" href="${contextPath}/mount-rule/log/export" target="dwzExport" t title="确定要导出本页记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="100">挂载规则ID</th>
                <th width="100">分类号</th>
                <th width="100">分类编码</th>
                <th width="100">产品名</th>
                <th width="100">产品前缀</th>
                <th width="100">产品后缀</th>
                <th width="100">查询关键字</th>
                <th width="100">品牌编码组 </th>
                <th width="100">分类编码组 </th>
                <th width="100">操作类型 </th>
                <th width="100">操作人</th>
                <th width="150">操作时间 </th>
                <th width="150">日志记录时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="mountRuleLog" items="${list}">
	            <tr target="slt_uid" rel="${mountRuleLog.id}" style="text-align: center;">
	                <td>${mountRuleLog.mountId}</td>
	                <td>${mountRuleLog.categoryId}</td>
	                <td>${mountRuleLog.categoryCode}</td>
	                <td>${mountRuleLog.productName}</td>
	                <td>${mountRuleLog.productPrefix}</td>
	                <td>${mountRuleLog.productSuffix}</td>
	                <td>${mountRuleLog.searchTag}</td>
	                <td>${mountRuleLog.brandCodes}</td>
	                <td>${mountRuleLog.categoryCodes}</td>
	                <td>
	                	<c:forEach var="optType" items="${optTypeMap}">
	                		<c:if test="${optType.key == mountRuleLog.optType }">${optType.value }</c:if>
	                	</c:forEach>	
	                </td>
	                <td>${mountRuleLog.operateUser}</td>
	                <td><fmt:formatDate value="${mountRuleLog.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                <td><fmt:formatDate value="${mountRuleLog.logDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>