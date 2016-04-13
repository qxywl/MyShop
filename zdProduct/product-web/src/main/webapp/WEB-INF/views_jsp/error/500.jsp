<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>

		.msgtips{
			padding: 10px;
			font-family: "Microsoft YaHei";
			text-align: center;
			
		}
		.msgtips h1{font-size: 18px!important;padding: 10px;}
		.msgtips .buttonContent{padding: 10px;}
	</style>

<div class="msgtips">
		<img src="${contextPath}/styles/product_img/error.png" alt="">
		<h1>系统发生内部错误.</h1>
		${errorMsg}
		<div class="buttonContent">
			<button type="button" class="close">关闭</button>
		</div>
</div>