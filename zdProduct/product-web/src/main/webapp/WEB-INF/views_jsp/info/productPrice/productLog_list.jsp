<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<cas:paginationForm action="${contextPath}/info/productPrice/logList" page="${page}">
    <input type="hidden" name="productSkuCode" value="${productPriceLogModel.productSkuCode}"/>
    <input type="hidden" name="createUserName" value="${productPriceLogModel.createUserName}"/>
    <input type="hidden" name="updateUserName" value="${productPriceLogModel.updateUserName}"/>
    <input type="hidden" name="createPriceLogStartTime" value="${createPriceLogStartTime}"/>
    <input type="hidden" name="createPriceLogEndTime" value="${createPriceLogEndTime}"/>
    <input type="hidden" name="updatePriceLogStartTime" value="${updatePriceLogStartTime}"/>
    <input type="hidden" name="updatePriceLogEndTime" value="${updatePriceLogEndTime}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/productPrice/logList" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
         <table width="100%">
			<tr>
				<td width="100px" align="right">SKU编号：</td>
				<td><input type="text" name="productSkuCode" size="20" value="${productPriceLogModel.productSkuCode}"/></td>
				<td width="120px" align="right">创建人：</td>
				<td><input type="text" name="createUserName" size="20" value="${productPriceLogModel.createUserName}"/></td>
				<td width="100px" align="right">创建时间：</td>
				<td>
					<input type="text" class="date" name="createPriceLogStartTime" size="15" dateFmt="yyyy-MM-dd" value="${createPriceLogStartTime}" readonly="true"/>
                     -
                     <input type="text" class="date" name="createPriceLogEndTime" size="15" dateFmt="yyyy-MM-dd" value="${createPriceLogEndTime }" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td width="100px" align="right">修改或审核人：</td>
				<td><input type="text" name="updateUserName" size="20" value="${productPriceLogModel.updateUserName}"/></td>
				<td style="width:120px;" align="right">修改或审核时间：</td>
				<td>
					<input type="text" class="date" name="updatePriceLogStartTime" size="15" dateFmt="yyyy-MM-dd" value="${updatePriceLogStartTime}" readonly="true"/>
                     -
                     <input type="text" class="date" name="updatePriceLogEndTime" size="15" dateFmt="yyyy-MM-dd" value="${updatePriceLogEndTime }" readonly="true"/>
				</td>
				<td><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></td>
			</tr>
		</table>
           
        </div>
    </div>
</form>
<div class="pageContent">

    <div class="panelBar">
    
        <ul class="toolBar">
         <!-- mask="true" width="1230" height="530" navTab-->
       	  <li><a class="icon" href="${contextPath}/info/productPrice/exportExcel?productSkuCode=${productPriceLogModel.productSkuCode}&name=1 "    title="实要导出这些记录吗?"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    
      <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th style="width:100px;">SKU编号</th>
                <th width="50">售价</th>
                <th width="50">市场价</th>
                <th width="50">调价前售价</th>
                <th width="50">调价前市场价</th>
                <th width="60">创建人</th>
                <th width="100">创建时间</th>
                 <th width="60">修改或审核人</th>
                <th width="100">修改或审核时间</th>
                 <th width="100">备注</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${productPriceLogList!=null}">
	            <c:forEach var="ProductPriceLogModel" items="${productPriceLogList}">
	            <tr target="slt_uid" rel="${ProductPriceLogModel.productPriceLog}" style="text-align: center;">
	                <td width="22"><input name="ids" value="${ProductPriceLogModel.productPriceLog}" type="checkbox" class="checkboxCtrl"></td>
	                <td style="width:100px;">${ProductPriceLogModel.productSkuCode}</td>
	                <td>${ProductPriceLogModel.tshPrice}</td>
	                <td>${ProductPriceLogModel.marketPrice}</td>
	                <td>${ProductPriceLogModel.oldTshPrice}</td>
	                <td>${ProductPriceLogModel.oldMarketPrice}</td>
	                <td>${ProductPriceLogModel.createUserName}</td>
	                <td>${ProductPriceLogModel.createTimes}</td>
	                <td>${ProductPriceLogModel.updateUserName}</td>
	                <td>${ProductPriceLogModel.updateTimes}</td>
	                <td>${ProductPriceLogModel.reason}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>