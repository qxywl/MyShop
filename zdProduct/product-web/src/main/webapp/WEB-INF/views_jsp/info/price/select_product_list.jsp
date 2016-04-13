<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/price/selectback" page="${page}">
    <input type="hidden" name="productName" value="${productModel.productName}"/>
    <input type="hidden" name="productCode" value="${productModel.productCode}"/>
</cas:paginationForm> 
<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${contextPath}/info/price/selectback" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>商品名称:</label>
				<input class="textInput" name="productName" value="${productModel.productName}" type="text">
			</li>	  
			<li>
				<label>商品编号:</label>
				<input class="textInput" name="productCode" value="${productModel.productCode}" type="text">
			</li>
			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				
				<th >商品名称</th>
				<th >商品编号</th>
				<th >商品id</th>
				<th >供应商id</th>
				<th >加价率(一级价格调整率)</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${productList!=null }">
			<c:forEach items="${productList }" var="productModel">
				<tr>			
				<td><c:set var="productNameUtil" value="${fn:replace(productModel.productName, '\\'', '‘')}"/>
				${productModel.productName }-${productNameUtil}</td>
				<td>${productModel.productCode }</td>
				<td>${productModel.productId }</td>
				<td>${productModel.supplierId }</td>
				<td>${productModel.markUpRate }</td>		
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'1', productName:'${productNameUtil}', productCode:'${productModel.productCode }', productId:'${productModel.productId }', supplierId:'${productModel.supplierId }', markUpRate:'${productModel.markUpRate }'})" title="查找带回">选择</a>
				</td>
			</tr>
			</c:forEach>
		</c:if>	
		</tbody>
	</table>
	<!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>