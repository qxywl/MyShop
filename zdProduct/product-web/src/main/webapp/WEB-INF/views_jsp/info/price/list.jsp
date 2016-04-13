<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<cas:paginationForm action="${contextPath}/info/price/list" page="${page}">

</cas:paginationForm>
<form method="post" action="${contextPath}/info/price/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li style="width:250px;">
                    <label>商品名称：</label>
                    <input type="text" name="productName" size="20" value="${productPriceLadderModel.productName}"/>
                </li>
            	<li style="width:250px;">
                    <label>商品编码：</label>
                     <input type="text" name="productCode" size="20" value="${productPriceLadderModel.productCode}"  />
                </li> 
                <li style="width:250px;">
                    <label>商品id：</label>
                     <input type="text" name="productId" size="20" value="${productPriceLadderModel.productId}"  />
                </li> 
                
                <li style="width:250px;">
                    <label>供应商id：</label>
                     <input type="text" name="supplierId" size="20" value="${productPriceLadderModel.supplierId}"  />
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
			<!-- mask="true" width="1230" height="530" navTab  dialog-->
			<li><a class="add" target="dialog" rel="add" mask="true"
				width="1200" height="600" href="${contextPath}/info/price/to_add"><span>新增</span></a></li>
			
			<li><a class="delete" target="selectedTodo" rel="ids"
				href="${contextPath}/info/price/delete" title="确认要删除?"
				onsubmit="return navTabSearch(this);"><span>批量删除</span></a></li>
				
			<li><a class="add" target="dialog" rel="add_excel" mask="true"
				width="600" height="400" href="${contextPath}/info/price/to_add_excel"><span>批量新增或者修改</span></a></li>
			 <li><a class="icon"
                   href="${contextPath}/info/price/export_product?productName=${productPriceLadderModel.productName}&productCode=${productPriceLadderModel.productCode }&productId=${productPriceLadderModel.productId }&supplierId=${productPriceLadderModel.supplierId }"
                   target="dwzExport" targetType="dialog" title="要导出这些记录吗(每次最多导出5000条记录，可以结合搜索条件分组导出)?"><span>导出excel</span></a></li>	
		</ul>
	</div>

	<table class="table" layoutH="137" width="100%" nowrapTD="false">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				
				<th width="220">商品名称</th>
				<th width="40">商品编码</th>
				<th width="40">商品id</th>
				<th width="40">供应商id</th>
				<th width="80">一级加价率(‰)(千分比)</th>
				<th width="80">二级加价率(‰)(千分比)</th>
				<th width="80">三级加价率(‰)(千分比)</th>
				<th width="80">四级加价率(‰)(千分比)</th>
				<th width="80">操作</th>				
			</tr>
		</thead>
		<tbody>
			<c:if test="${list!=null}">
				<c:forEach var="ProductPriceLadderModel" items="${list}">
					<tr target="slt_uid" rel="${ProductPriceLadderModel.id}"
						style="text-align: center;">
						<td><input name="ids"
							value="${ProductPriceLadderModel.id}" width="30" 
							type="checkbox" class="checkboxCtrl"></td>						
						<td>${ProductPriceLadderModel.productName}</td>
						<td>${ProductPriceLadderModel.productCode}</td>
						<td>${ProductPriceLadderModel.productId}</td>
						<td>${ProductPriceLadderModel.supplierId}</td>
						<td>${ProductPriceLadderModel.priceLevel1}</td>
						<td>${ProductPriceLadderModel.priceLevel2}</td>
						<td>${ProductPriceLadderModel.priceLevel3}</td>
						<td>${ProductPriceLadderModel.priceLevel4}</td>
						
						<td><a class="edit btn btn-info" target="dialog" rel="add" mask="true"
				width="1000" height="600" href="${contextPath}/info/price/to_update/${ProductPriceLadderModel.id}"><span>修改编辑</span></a>
				  <a class="edit btn btn-danger" target="ajaxTodo" title="确认要删除?" rel="" mask="true"
				width="1000" height="476" href="${contextPath}/info/price/delete/${ProductPriceLadderModel.id}"><span>删除</span></a>
				
				</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<!-- 分页 -->
	<cas:pagination page="${page}" />
</div>
