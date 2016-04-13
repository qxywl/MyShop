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


<script type="text/javascript">
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
			}
		});
	});
	function queryDomain(reqUrl){
		location.href=reqUrl;
	}
</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="" href="${contextPath}/index">Logo</a>
				<ul class="nav">
					<li><a href="${contextPath}/index">主页</a></li>
					<li><a href="${contextPath}/index/base"
						target="dialog" mask="true" width="550" height="250">修改用户信息</a></li>
					<li><a href="${contextPath}/index/pass"
						target="dialog" mask="true" width="500" height="200">修改密码</a></li>
					<li><a href="${contextPath}/logout">退出</a></li>
				</ul>

				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">blue</div></li>
					<li theme="green"><div>green</div></li>
					<li theme="purple"><div>purple</div></li>
					<li theme="silver"><div>silver</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>菜单</h2>
					<div>collapse</div>
				</div>
				<div class="accordion" fillSpace="sideBar">
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>商品管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/info/product/list" target="navTab" rel="info_1">商品信息管理</a></li>
							<li><a href="${contextPath}/info/product/examine_list" target="navTab" rel="info_1">商品审核</a></li>
							<li><a href="${contextPath}/info/product/log/list" target="navTab" rel="info_5">商品信息日志管理</a></li>
							
							<li><a href="${contextPath}/info/productPrice/list?" target="navTab" rel="info_3">商品价格导入</a></li>
							<li><a href="${contextPath}/info/productPrice/examinelist?status=1" target="navTab" rel="info_3">商品价格审核管理</a></li>
							<li><a href="${contextPath}/info/productPrice/logList" target="navTab" rel="info_7">商品价格日志管理</a></li>
							
							<li><a href="${contextPath}/info/shelves/list" target="navTab" rel="info_2">商品上下架管理</a></li>
							<li><a href="${contextPath}/info/shelves/log/list" target="navTab" rel="info_6">商品上下架日志管理</a></li>
							
							<li><a href="${contextPath}/info/stock/list" target="navTab" rel="info_7">商品库存列表</a></li>
							
							<li><a href="${contextPath}/info/price/list" target="navTab" rel="info_8">商品价格阶梯管理</a></li>
							
							<li><a href="${contextPath}/info/purchaseStock/list" target="navTab" rel="info_9">同步erp采购库单列表</a></li>
							
							<li><a href="http://www.baidu.com" target="navTab" rel="info_6" external="true"  >xxxxxxxx</a></li>							
							
							 
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>品牌信息管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/brand/list" target="navTab" rel="brand">品牌信息管理</a></li>
						</ul>
					</div>

					<div class="accordionHeader">
						<h2>
							<span>Folder</span>供应商管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/supplier/list" target="navTab" rel="supplier">供应商管理</a></li>
						</ul>
					</div>

					<%--日志管理 --%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>日志管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/category/log/main" target="navTab" rel="brand">基础分类日志管理</a></li>
							<li><a href="${contextPath}/category/salelog/main" target="navTab" rel="brand">运营分类日志管理</a></li>
							<li><a href="${contextPath}/sale-category/main" target="navTab" rel="brand">运营分类管理</a></li>
						</ul>
					</div>
					
					<%--属性管理 --%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>属性管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/attrgroup/list" target="navTab" rel="brand">属性组管理</a></li>
						</ul>
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/attr/list" target="navTab" rel="brand">属性项管理</a></li>
						</ul>
					</div>
					
					<%-- <c:forEach var="item2" items="${menuList2}" varStatus="status">
					 <div class="accordionHeader">
						<h2>
							<span>Folder</span>${item2.pageName}
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
					              <c:forEach var="item3" items="${menuList3}" varStatus="status">
					                          <c:if test="${item3.parentId == item2.pageId }" >
					                          <li><a href="${item3.pageUrl}" target="navTab" rel="role_${item3.pageId}">${item3.pageName}</a></li>
					                          </c:if>
					              </c:forEach>
					    </ul>
					</div>
					</c:forEach> --%>
					<%--基础分类管理 --%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>基础分类管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/base-category/main" target="navTab" rel="brand">基础分类管理</a></li>
						</ul>
					</div>
					
					 <%--运营分类管理 --%>
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>运营分类管理
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder expand">
							<li><a href="${contextPath}/sale-category/main" target="navTab" rel="brand">运营分类管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="container">
		<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent">
					<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
					<ul class="navTab-tab">
						<li tabid="main" class="main"><a href="javascript:void(0)"><span><span
									class="home_icon">主页</span></span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">left</div>
				<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
				<div class="tabsRight">right</div>
				<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
				<div class="tabsMore">more</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:void(0)">主页</a></li>
			</ul>
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
					<div class="accountInfo">
						<div class="right">
							<p>
								<fmt:formatDate value="<%=new Date() %>"
									pattern="yyyy-MM-dd EEEE" />
							</p>
						</div>
						<p>
							<span>欢迎, ${user.userName}.</span>
						</p>
					</div>
					<div class="pageFormContent" layouth="80">
						<fieldset>
							<legend>基本信息</legend>
							<dl>
								<dt>登录名称：</dt>
								<dd>
									<span class="unit">${user.userName }</span>
								</dd>
							</dl>
							<dl>
								<dt>真实名字：</dt>
								<dd>
								    <span class="unit">${user.contactName }</span>
								</dd>
							</dl>
							<dl>
								<dt>电话：</dt>
								<dd>
									<span class="unit">${user.mobile }</span>
								</dd>
							</dl>
							<dl>
								<dt>E-Mail：</dt>
								<dd>
									<span class="unit">${user.email }</span>
								</dd>
							</dl>
							<dl>
								<dt>创建时间：</dt>
								<dd>
									<span class="unit"><fmt:formatDate
											value="${user.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</dd>
							</dl>
							<dl>
								<dt>可用状态：</dt>
								<dd>
								 <span class="unit">${user.userStatus == "0" ? "可用":"不可用" }</span>
								</dd>
							</dl>
							<dl>
								<dt>系统选择：</dt>
								<dd>
								 <span class="unit">
								 <select name="domain" class="combox required"
										onchange="queryDomain('${contextPath}/index/domain/'+$(this).val())">
											<c:forEach var="item" items="${trees}" varStatus="status">
												<option value="${item.domainId}"   
												<c:if test="${item.domainId == user.userDomain }">selected='selected'</c:if> 
												>${item.domainName}</option>
											</c:forEach>
									</select>
								</span>
								</dd>
							</dl>
							
							<dl>
							</dl>
						</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<div id="footer">Copyright &copy; 2014-2015 后台管理系统 版权所有.</div>
</body>
</html>