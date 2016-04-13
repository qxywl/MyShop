<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

            		<c:if test="${listCategory!=null }">
            			<c:forEach items="${listCategory }" var="category">
            				<li><input type="hidden" id="${category.categoryCode }" value="${category.categoryName }"/><a onclick="liClick(this);" href="#">${category.categoryName }</a></li>
            				
            			</c:forEach>
            		</c:if>
