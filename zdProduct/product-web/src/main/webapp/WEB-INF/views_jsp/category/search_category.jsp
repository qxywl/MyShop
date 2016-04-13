<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/category/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/sale-category/search" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                 <li>
                    <label>产品编号</label>
                    <input type="text" name="productCode" value=""/>
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
    <table class="table" layoutH="137" width="100%">
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="saleCategory" items="${list}">
	            <tr target="slt_uid" rel="${saleCategory}" style="text-align: left;height: 50px">
	                <td>${saleCategory}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>