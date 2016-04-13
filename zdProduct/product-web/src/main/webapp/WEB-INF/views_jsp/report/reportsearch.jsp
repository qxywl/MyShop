<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <table width="746" border="1">
	<tr>
		<c:forEach items="${titleName}" var="title">
			<td>${title}</td>
		</c:forEach>
	</tr>
	<c:forEach items="${list}" var="item">
		<tr>
			<c:forEach items="${item}" var="map">
				<td>${map.value}</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table> --%>


<table class="table" layoutH="163" width="100%">
        <thead>
            <tr>
            	<c:forEach items="${titleName}" var="title">
					<td width="100"> <c:out value="${title }"></c:out></td>
				</c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="item">
				<tr>
				<c:forEach items="${titleEnName}" var="enName">
					<c:forEach items="${item}" var="map">
						<c:if test="${enName==map.key }">
							<td width="100"><c:out value="${map.value}"></c:out></td>
						</c:if>
					</c:forEach>
				</c:forEach>
				</tr>
			</c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>

