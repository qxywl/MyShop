<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>



<cas:paginationForm action="${contextPath}/sale-category/proNoCategory" page="${page}">
</cas:paginationForm> 
	
<form id="test"method="post" action="${contextPath}/sale-category/proNoCategory" onsubmit="return navTabSearch(this)">
	 <div class="pageHeader">
	 <div class="searchBar">
		<table class="searchContent">
			<tbody><tr>
				<td>
					品牌编号：<input type="text" name="brandCode" value="${goodsModel.brandCode}" class="textInput">
				</td>
				<td>
					品牌名称：<input type="text" name="brandName" value="${goodsModel.brandName}" class="textInput">
				</td>
				<td>
					基础分类号：<input type="text" name="baseCateCodes" value="${goodsModel.baseCateCodes[0]}" class="textInput">
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
       	  <li><a class="icon" href="${contextPath}/sale-category/proNoCategory/export" target="dialog"   width="630" height="350" title="按基础分类导出没挂载运营分类的商品"><span>导出excel</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th ><input type="checkbox" group="ids" class="checkboxCtrl"></th>  
                <th >商品名称</th>
                <th >商品编号 </th>
                <th >品牌名称</th>
                <th >品牌编号</th>
                <th >基础分类编号</th>
                <th >价格 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${goodsList != null}">
	            <c:forEach var="goodsModel" items="${goodsList}">
	            <tr target="slt_uid" rel="${goodsModel.id}" style="text-align: center;height: 30px">
	                <td><input name="ids" value="${goodsModel.id}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${goodsModel.goodsName}</td>
	                <td>${goodsModel.goodsCode}</td>
	                <td>${goodsModel.brandName}</td>
	                <td>${goodsModel.brandCode}</td>
	                
	                
	                <td>
	                
	                <c:forEach var="b" items="${goodsModel.baseCateCodes}" varStatus="status">
					  <c:out value="${b}"/>;&nbsp;
					</c:forEach>
	                </td>
	                <td>${goodsModel.price}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>