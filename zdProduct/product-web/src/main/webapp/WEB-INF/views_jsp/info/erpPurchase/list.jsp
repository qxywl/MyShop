<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<script type="text/javascript">
$(function(){
	$(".icon").click(function(){
		$.ajax({
	        type : "POST",   
	        url : '${contextPath}/info/purchaseStock/checkExport',
	        dataType : "json",
	        success : function(data) {
	        	if(data != null && data.result){
	        		window.location.href = "${contextPath}/info/purchaseStock/export";
	        	}else{
	        		alertMsg.error("没有需要导出的数据", {okCall:navTab.reload('${contextPath}/info/purchaseStock/list')});
	        	}
	        }
	   	});
	})
})
</script>

<style>
  .grid .gridTbody td div {
    display: block;
    overflow: hidden;
    height: 60px;
    white-space: nowrap;
    line-height:44px;
  }
  .img_div{
    display: block;
    overflow: hidden;
    height: 44px;
    white-space: nowrap;
    width:70px;
    line-height:  44px;
  }

</style>

<cas:paginationForm action="${contextPath}/info/purchaseStock/list" page="${page}">
</cas:paginationForm>

<form id="test"method="post" action="${contextPath}/info/purchaseStock/list" onsubmit="return navTabSearch(this)">
  <div class="pageHeader">
    <div class="searchBar">
      <table class="searchContent">
        <tbody><tr>
          <td>
          采购单ID：<input type="text" name="purchaseId" value="${productPurchaseStockModel.purchaseId}" class="textInput">
          </td>
          <td>
            商品代码：<input type="text" name=spdm value="${productPurchaseStockModel.spdm}" class="textInput">
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
   		    <li>
   		    	<a class="icon" ><span>导出未分配excel</span></a>
   		    </li>
    </ul>
  </div> 		

  <table class="table" layoutH="137" width="100%">
    <thead>
    <tr>
      <th> 采购商ID</th>
      <th>单据编号 </th>
      <th>采购类型</th>
      <th>审核人</th>
      <th>审核日期 </th>
      <th>商品代码</th>
      <th>商品名称</th>
      <th>规格代码</th>
      <th>规格名称</th>
      <th>总数量</th>
      <th>实库数量</th>
      <th>分配百分比</th>
      <th>公共库存数量</th>
      <th>公共库存状态</th>
      <th>分配人</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${purchaseStockList!=null}">
      <c:forEach var="productPurchaseStockModel" items="${purchaseStockList}">
        <tr target="slt_uid" rel="${productPurchaseStockModel.id}" style="text-align: center;" height="60px">
          <td>${productPurchaseStockModel.purchaseId}</td>
          <td>${productPurchaseStockModel.djbh}</td>
          <td>
          	<c:choose>
          		<c:when test="${productPurchaseStockModel.type == 0}">
          			采购入库
          		</c:when>
          		<c:when test="${productPurchaseStockModel.type == 1}">
          			采购退货
          		</c:when>
          		<c:otherwise>
          			状态错误
          		</c:otherwise>
          	</c:choose>
          </td>
          <td>${productPurchaseStockModel.purchaseShr}</td>
          <td><fmt:formatDate value="${productPurchaseStockModel.shrq}"  type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td>${productPurchaseStockModel.spdm}</td>
          <td>${productPurchaseStockModel.spmc}</td>
          <td>${productPurchaseStockModel.skudm}</td>
          <td>${productPurchaseStockModel.skumc}</td>
          <td>${productPurchaseStockModel.sl}</td>
          <td>${productPurchaseStockModel.realStock}</td>
          <td>
          	<c:choose>
          		<c:when test="${productPurchaseStockModel.assignPercent == null}">
          			0%
          		</c:when>
          		<c:otherwise>
          			${productPurchaseStockModel.assignPercent}%
          		</c:otherwise>	
          	</c:choose>
          </td>
          <td>${productPurchaseStockModel.publicStock}</td>
          <td>
         	<c:choose>
         		<c:when test="${productPurchaseStockModel.assignStatus == 0}">
         			未分配
         		</c:when>
         		<c:when test="${productPurchaseStockModel.assignStatus == 1}">
		          	已分配
		        </c:when>
         		<c:otherwise>
		          	状态错误
         		</c:otherwise>
         	</c:choose>
          </td>
          <td>${productPurchaseStockModel.assignBy}</td>
        </tr>
      </c:forEach>
    </c:if>
    </tbody>
  </table>
  <!-- 分页 -->
  <cas:pagination page="${page}"/>
</div>
