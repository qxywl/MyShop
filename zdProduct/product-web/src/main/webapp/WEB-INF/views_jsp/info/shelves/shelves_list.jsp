<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/shelves/list" page="${page}">
    <input type="hidden" name="productCode" value="${productShelvesModel.productCode }"/>
    <input type="hidden" name="productName" value="${productShelvesModel.productName }"/>
    <input type="hidden" name="startTime" value="${startTime}"/>
    <input type="hidden" name="endTime" value="${endTime}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/shelves/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
          <table width="100%">
			<tr>
                    <td width="80px" align="right"><label>商品编号：</label></td>
                    <td width="80px" align="right"><input type="text" name="productCode" value="${productShelvesModel.productCode }"/></td>
           
                    <td width="80px" align="right"><label>商品名称：</label></td>
                    <td width="80px" align="right"><input type="text" name="productName" value="${productShelvesModel.productName }"/></td>
              
                    <td width="80px" align="right"><label>创建时间：</label></td>
                    <td ><input type="text" name="startTime" class="date" value="${startTime}" dateFmt="yyyy-MM-dd" readonly="true" value=""/>
                    -
                     <input type="text" name="endTime" class="date" value="${endTime}" dateFmt="yyyy-MM-dd" readonly="true" value=""/>
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
          <li><a class="icon" 
       	   href="${contextPath}/info/shelves/export_excel?productCode=${productShelvesModel.productCode}&productName=${productShelvesModel.productName }&
       	   status=${productShelvesModel.status}&startTime=${startTime }&endTime=${endTime }"  title="实要导出这些记录吗?"><span>导出excel</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/shelves/to_upload" target="dialog"  ><span>导入excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="150">商品名称</th>
                <th width="150">商品编码 </th>
                <th width="150">修改上下架状态 </th>
                <th width="100">审核状态</th>
                <th width="100">说明</th>
                <th width="100">创建人名称 </th>
                <th width="100">创建时间 </th>
                <th width="100">修改人名称 </th>
                <th width="100">修改时间 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${listShelves!=null}">
	            <c:forEach var="productShelves" items="${listShelves}">
	            <tr target="slt_uid" rel="${productShelves.productShelvesId}" style="text-align: center;">
	                <td>${productShelves.productName}</td>
	                <td>${productShelves.productCode}</td>
	                 <td>
	                	<c:if test="${productShelves.shelvesStatus==0}">下架</c:if>
	                	<c:if test="${productShelves.shelvesStatus==1}">上架</c:if>
	                </td>
	                <td>
	                	<c:if test="${productShelves.status==1}">待审核</c:if>
	                	<c:if test="${productShelves.status==2}">审核通过</c:if>
	                	<c:if test="${productShelves.status==3}">待同步</c:if>
	                	<c:if test="${productShelves.status==4}">同步成功</c:if>
	                	<c:if test="${productShelves.status==8}">审核不通过</c:if>
	                	<c:if test="${productShelves.status==9}">处理失败</c:if>
	                </td>
	                <td>${productShelves.reason}</td>
	                <td>${productShelves.createUserName}</td>
	                <td>${productShelves.createTimes}</td>
	                <td>${productShelves.updateUserName}</td>
	                <td>${productShelves.updateTimes}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>
<script>
	function examineClick(element){
		
		var str = prompt("审核不通过的原因","");
		if(str!=null && str!=""){
			var src = "${contextPath}/info/shelves/examine?productShelvesId={slt_uid}&status=8&reason="+str;
			$("#noExamine").attr("href",src);
		}
	}
</script>