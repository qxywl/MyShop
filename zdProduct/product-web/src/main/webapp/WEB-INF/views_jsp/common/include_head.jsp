<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
 --%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="description" content="后台管理系统" />
<meta name="keywords" content="后台管理系统" />
<title>后台管理系统</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/resources/img/favicon.ico" />
<%@ taglib prefix="cas" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- 公用CSS文件存放 -->
<style>
		
		.btn{
			margin-top: 5px;
			margin-bottom: 5px;
			display: inline-block;
			padding: 3px 6px;
			margin-bottom: 0;
			font-size: 14px;			
			font-weight: 400;
			line-height: 1.42857143;
			text-align: center;
			white-space: nowrap;
			vertical-align: middle;
			-ms-touch-action: manipulation;
			touch-action: manipulation;
			cursor: pointer;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #fff;
		}
		.btn-default{background-color: #fff;border-color: #ccc;color: #333}
		.btn-primary{background-color: #337ab7;border-color: #2e6da4;}
		.btn-success{background-color: #5cb85c;border-color: #4cae4c;}
		.btn-info{background-color: #5bc0de;border-color: #46b8da;}
		.btn-warning{background-color: #f0ad4e;border-color: #eea236;}
		.btn-danger{background-color: #d9534f;border-color: #d43f3a;}
		.td-operation{text-align: left;}
		.subBar{text-align: right;}
	</style>

<!-- 公用JS文件存放 -->

 