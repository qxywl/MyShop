<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/shelves/wait_shelves" page="${page}">
	<input type="hidden" name="productCode"  value="${productShelvesTmpModel.productCode}" />
	<input type="hidden" name="productName"  value="${productShelvesTmpModel.productName}" />
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/shelves/wait_shelves" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
         <table width="100%">
			<tr>
				<td width="80px" align="right">商品编号：</td>
				<td><input type="text" name="productCode" size="20" value="${productShelvesTmpModel.productCode }" class="textInput"></td>
				<td width="80px" align="right">商品名称：</td>
				<td><input type="text" name="productName" size="20" value="${productShelvesTmpModel.productName }" class="textInput"></td>
				<td><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></td>
			</tr>
			</table>
        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
        <li><a class="icon" href="${contextPath}/info/shelves/export" id="exportWaitShelves" target="dwzExport"  targetType="dialog" onclick="setPage(this);" title="要导出这些记录吗?(最多导出5000条记录)"><span>导出审核记录</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="100">商品编号 </th> 
                <th width="100">商品名称 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${shelvesTmpList!=null}">
	            <c:forEach var="shelvesTmpModel" items="${shelvesTmpList}">
	            <tr target="slt_uid" rel="${shelvesTmpModel.productShelvesTmpId}" style="text-align: center;">
	                <td >
	                	<a 
	                	target="navTab" rel="2" 
	                	href="${contextPath }/info/product/to_info/${shelvesTmpModel.productCode}?waitShelvesInfo=1"
	                	>${shelvesTmpModel.productCode}</a>
	                </td>
	                <td >${shelvesTmpModel.productName}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>
<script>
function setPage(){
	
		var str = prompt("请根据总条数设置导出第几页,输入规则：1、输入大于最大页数,则导出最后一页  2、输入不是有效数字或空,则导出第一页(每页最多5000条)：","");
		if(str!=null && str!=""){
			 var re = /^\d+(\.\d+)?$/;
			if(!re.test(str)){
				str="1";
			} 
			var sta = encodeURIComponent(str);
			var src = "${contextPath}/info/shelves/export?page="+sta
			$("#exportWaitShelves").attr("href",src);
		}else{
			var src = "${contextPath}/info/shelves/export?page=1";
			$("#exportWaitShelves").attr("href",src);
		}
}
</script>