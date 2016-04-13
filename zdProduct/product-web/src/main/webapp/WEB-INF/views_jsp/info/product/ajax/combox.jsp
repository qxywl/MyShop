<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<c:if test="${listCategory!=null }">
		<option value="">选择分类</option>
	<c:forEach items="${listCategory }" var="category">
		<option value="${category.categoryCode }" data-value="${category.categoryName }">${category.categoryName }</option>
	</c:forEach>
</c:if>
