<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>平台首页</title>

<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/dwz/themes/css/core.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/dwz/themes/css/print.css"
	rel="stylesheet" type="text/css" media="print" />
<link href="${contextPath}/styles/product/css/add_product.css" rel="stylesheet" type="text/css" media="screen" />
<link
	href="${contextPath}/styles/validationEngine/css/validationEngine.jquery.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/ztree/css/zTreeStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/styles/uploadify/css/uploadify.css"
	rel="stylesheet" type="text/css" media="screen" />
<!--[if IE]>
    <link href="${contextPath}/styles/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--[if lte IE 9]>
    <script src="${contextPath}/styles/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="${contextPath}/styles/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<%-- form验证 --%>
<script src="${contextPath}/styles/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.core.js" type="text/javascript"></script>

<script src="${contextPath}/styles/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.dialog.js" type="text/javascript"></script>



<script src="${contextPath}/styles/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.database.js" type="text/javascript"></script>

<script src="${contextPath}/styles/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.print.js" type="text/javascript"></script>


<script src="${contextPath}/styles/validationEngine/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/styles/validationEngine/js/jquery.validationEngine-2.6.4.js" type="text/javascript" charset="utf-8"></script>

<script src="${contextPath}/styles/dwz/js/dwz.min.js" type="text/javascript"></script>


<script src="${contextPath}/styles/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	
<script src="${contextPath}/styles/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	
<script src="${contextPath}/styles/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script src="${contextPath}/styles/dwz/js/customer.js" type="text/javascript"></script>
<%-- upload --%>
<script src="${contextPath}/styles/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
<%-- zTree --%>
<script src="${contextPath}/styles/ztree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<script src="${contextPath}/styles/dwz/js/dwz.ui.js" type="text/javascript"></script>

<script src="${contextPath}/styles/report/lhgcalendar.js" type="text/javascript"></script>
<script src="${contextPath}/styles/report/lhgcore.js" type="text/javascript"></script>
<script type="text/javascript">
var aa=1;
$(function() {
	DWZ.init("${contextPath}/styles/dwz/dwz.frag.xml", {
		loginUrl : "${contextPath}/login/timeout",
		loginTitle : "登录", // 弹出登录对话框
		debug : false, // 调试模式 【true|false】
		callback : function() {
			initEnv();
			$("#themeList").theme({
				themeBase : "${contextPath}/styles/dwz/themes"
			});
			setInterval('xxx()',40);
		}
	});
});
	function xxx(){
		if(aa==1){
		document.getElementById("test").click();
		aa=2;
		$('.navTab-tab li').eq(1).attr("style", "display:none");
		}
	}
</script>
</head>
<body scroll="no">
	<div id="layout">
	<li style='display:none'><a id="test" href="${contextPath}/${url}" target="navTab" rel="info_${token}">${name}</a></li>
	</div>
	<div id="container"  style ="weigth:100%; top: 0px;left: 0px;" >
		<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader"  >
				<div class="tabsPageHeaderContent">
					<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
					<ul class="navTab-tab"  id="cdl" >
						<li tabid="main" class="main" style="display:none" ><a href="javascript:void(0)"></a></li>
					</ul>
				</div>
				<div class="tabsLeft"></div>
				<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
				<div class="tabsRight"></div>
				<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
				<div class="tabsMore"></div>
			</div>
			<div class="navTab-panel tabsPageContent layoutBox" style ="height:100%" >
				<div class="page unitBox">
					 
				</div>
			</div>
		</div>
	</div>
</body>
</html>