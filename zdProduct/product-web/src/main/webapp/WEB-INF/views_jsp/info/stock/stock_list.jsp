<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
		.searchBar{
			margin: 0 auto;
		}
		.searchBar table {height:50px}
		.searchBar table input{width: 100px}
</style>

<script type="text/javascript">
$(function(){
	$(".edit").click(function(){
		$.ajax({
	        type : "POST",   
	        url : '${contextPath}/info/stock/syncPurchaseStock',
	        dataType : "json",
	        success : function(data) {
	        	if(data != null){
	        		alertMsg.confirm(data.msg);
	        	}
	        }
	   	});
	})
})
</script>

<cas:paginationForm action="${contextPath}/info/stock/list" page="${page}">
	<input type="hidden" name="categoryCode" value="${productStockPo.categoryCode }"/>
	<input type="hidden" name="categoryName" value="${productStockPo.categoryName }"/>
	<input type="hidden" name="brandCode" value="${productStockPo.brandCode }"/>
	<input type="hidden" name="brandName" value="${productStockPo.brandName }"/>
	<input type="hidden" name="productCode" value="${productStockPo.productCode }"/>
	<input type="hidden" name="productName" value="${productStockPo.productName }"/>
	<input type="hidden" name="skuCode" value="${productStockPo.skuCode }"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/stock/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
    
    <div class="searchBar">
	            <table class="searchContent">
			<tbody><tr>
				<td>
					分类编号：<input type="text" name="categoryCode" value="${productStockPo.categoryCode }"/>
				</td>
				<td>
					分类名称：<input type="text" name="categoryName" value="${productStockPo.categoryName }"/>
				</td>
				<td>
					品牌编号：<input type="text" name="brandCode" value="${productStockPo.brandCode }"/>
				</td>
				</tr>
				<tr>
				<td>
					品牌名称：<input type="text" name="brandName" value="${productStockPo.brandName }"/>
				</td>
				<td>
					商品编号：<input type="text" name="productCode" value="${productStockPo.productCode }"/>
				</td>
				<td>
					商品名称：<input type="text" name="productName" value="${productStockPo.productName }"/>
				</td>
				<td>
					sku编号：<input type="text" name="skuCode" value="${productStockPo.skuCode }"/>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div></td>
			</tr>
		</tbody></table>
	        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
          <li><a class="icon"  target="dialog" rel="" mask="true" width="500" height="250" href="${contextPath}/info/stock/to_exportExcel?
          categoryCode=${productStockPo.categoryCode }&categoryName=${productStockPo.categoryName }
          &brandCode=${productStockPo.brandCode }&brandName=${productStockPo.brandName }
          &productCode=${productStockPo.productCode }&productName=${productStockPo.productName }
          &skuCode=${productStockPo.skuCode }&totalCount=${page.totalCount}"><span>导出库存详情</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/stock/to_importStock" target="dialog" mask="true" width="600" height="300" ><span>导入库存信息excel</span></a></li>
       	   <li><a class="edit"><span>同步ERP增量库存</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th >库存编号</th>
                <th >sku编码</th>
                <th >商品编号</th>
                <th >商品名称</th>
                <th >品牌编号</th>
                <th >品牌名称</th>
                <th >分类编号</th>
                <th >分类名称</th>
                <th >虚拟库类型</th>
                <th >对接库存数</th>
                <th >手工虚拟库</th>
                <th >实库数</th>
            </tr>
        </thead>
        
        <tbody>
            <c:if test="${list!=null}">
	            <c:forEach var="po" items="${list}">
	            <tr target="slt_uid"  style="text-align: center;">
	                <td >${po.stockCode }</td>
	                <td >${po.skuCode }</td>
	                <td >${po.productCode }</td>
	                <td >${po.productName }</td>
	                <td >${po.brandCode }</td>
	                <td >${po.brandName }</td>
	                <td >${po.categoryCode }</td>
	                <td >${po.categoryName }</td>
	                <td >
	                	<c:if test="${po.stockType == 1 }">手工库</c:if>
	                	<c:if test="${po.stockType == 2 }">对接库</c:if>
	                	<c:if test="${po.stockType == 3 }">实体库</c:if>
	                </td>
	                <td >${po.virtualStock }</td>
	                <td >${po.manualStock }</td>
	                <td >${po.entityStock }</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>
<script>

</script>