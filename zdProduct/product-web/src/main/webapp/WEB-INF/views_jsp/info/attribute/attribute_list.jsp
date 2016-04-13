<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/dwz/selectback" page="${page}">
    <input type="hidden" name="brandName" value="${productBrandModel.brandName}"/>
    <input type="hidden" name="brandCode" value="${productBrandModel.brandCode}"/>
</cas:paginationForm> 

	<form rel="pagerForm" method="post" action="${contextPath}/dwz/selectback" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="pageHeader">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>品牌名称:</label>
				<input class="textInput" name="brandName" value="" type="text">
			</li>	  
			<li>
				<label>品牌编号:</label>
				<input class="textInput" name="brandCode" value="" type="text">
			</li>
			</li> 
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</div>
	</form>

<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="orgNum">品牌编号</th>
				<th orderfield="leader">品牌名称</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${listBrand!=null }">
			<c:forEach items="${listBrand }" var="brand">
				<tr>
				<td>${brand.brandCode }</td>
				<td>${brand.brandName }</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'1', brandName:'${brand.brandName }', brandCode:'${brand.brandCode }'})" title="查找带回">选择</a>
				</td>
			</tr>
			</c:forEach>
		</c:if>	
			
			
		</tbody>
	</table>
	<!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>