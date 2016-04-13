<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/product/log/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
    
    <input type="hidden" name="productCode" value="${productInfoLogModel.productCode }"/>
    <input type="hidden" name="productName" value="${productInfoLogModel.productName }"/>
    <input type="hidden" name="startTime" value="${startTime}"/>
    <input type="hidden" name="endTime1" value="${endTime1}"/>
    <input type="hidden" name="createUserName" value="${productInfoLogModel.createUserName }"/>
    <input type="hidden" name="updateUserName" value="${productInfoLogModel.updateUserName }"/>
    
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/product/log/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
        	<ul class="searchContent">
				<li style="width:200px;">
                    <label>商品编号：</label>
                    <input type="text" name="productCode" size="15" value="${productInfoLogModel.productCode }"/>
                </li>
                <li style="width:200px;">
                    <label>商品名称：</label>
                    <input type="text" name="productName" size="15" value="${productInfoLogModel.productName }"/>
                </li>
				<!-- <td width="80px" align="right">商品类型：</td>
				<td>
				<select name="type">
                    	<option value="">选择类型</option>
                    	<option value="01">商城</option>
                    	<option value="02">机票</option>
                    	<option value="03">酒店</option>
                    	<option value="04">鲜花</option>
                    	<option value="05">团购</option>
                </select>
                </td> -->
                <li style="width:350px;">
                    <label>创建时间：</label>
                     <input type="text" class="date" name="startTime" size="15" dateFmt="yyyy-MM-dd" value="${startTime}" readonly="true"/>
                     -
                     <input type="text" class="date" name="endTime1" size="15" dateFmt="yyyy-MM-dd" value="${endTime1 }" readonly="true"/>
                </li>
                <li style="width:200px;">
                    <label>更新人名称：</label>
                    <input type="text" name="createUserName" size="15" value="${productInfoLogModel.createUserName }"/>
                </li>
                <li style="width:200px;">
                    <label>审核人名称：</label>
                    <input type="text" name="updateUserName" size="15" value="${productInfoLogModel.updateUserName }"/>
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

    <div class="panelBar">
        <ul class="toolBar">
        <!-- mask="true" width="1230" height="530" navTab
          <li><a class="edit" target="dialog" rel="" mask="true" width="1400" height="530" href="${contextPath }/info/product/log/log_info/{slt_uid}"><span>查看详情</span></a></li>-->
       	  
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="22px"><input type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th width="150px">商品名称</th>
                <th width="150px">商品编码 </th>
                <th width="100px">更新类型 </th>
                <th width="100px">更新时间 </th>
                <th width="100px">更新人名称 </th>
                <th width="100px">审核时间 </th>
                <th width="100px">审核人名称</th>
                <th width="100px">审核状态</th>
                <th width="100px">说明</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${infoLogList!=null}">
	            <c:forEach var="productinfoLog" items="${infoLogList}">
	            <tr target="slt_uid" rel="${productinfoLog.productLogId}" style="text-align: center;">
	                <td><input name="ids" value="${productinfoLog.productLogId}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${productinfoLog.productName}</td>
	                <td>${productinfoLog.productCode}</td>
	                <td>
	                	<c:if test="${productinfoLog.type==1}">新增商品</c:if>
	                	<c:if test="${productinfoLog.type==2}">页面修改</c:if>
	                	<c:if test="${productinfoLog.type==3}">导入修改</c:if>
	                </td>
	                <td>${productinfoLog.createTimes}</td>
	                <td>${productinfoLog.createUserName}</td>
	                <td>${productinfoLog.updateTimes}</td>
	                <td>${productinfoLog.updateUserName}</td>
	                <td>
	                	<c:if test="${productinfoLog.status==0}">待处理</c:if>
	                	<c:if test="${productinfoLog.status==1}">待审核</c:if>
	                	<c:if test="${productinfoLog.status==2}">审核通过</c:if>
	                	<c:if test="${productinfoLog.status==3}">待同步</c:if>
	                	<c:if test="${productinfoLog.status==4}">审核通过,同步成功</c:if>
	                	<c:if test="${productinfoLog.status==8}">审核不通过</c:if>
	                	<c:if test="${productinfoLog.status==5}">审核不通过,同步成功</c:if>
	                	<c:if test="${productinfoLog.status==9}">处理失败</c:if>
	                	<c:if test="${productinfoLog.status==6}">系统处理</c:if>
	                </td>
	                <td>${productinfoLog.reason}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>