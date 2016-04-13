<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<style>
   .table td div{height: auto!important;}
   .grid .gridTbody td div{height: auto!important;}
</style>

<script type="text/javascript">

function toBack(){
	var url = "${contextPath}/sale-category/main?currentCateId=${categoryId}";
	navTab.closeCurrentTab();
	navTab.openTab("brand", url, { title:"运营分类管理", fresh:false, data:{} });
}


</script>
<c:set var="imageServer" value="http://www.zhaidou.com" />

<cas:paginationForm action="${contextPath}/sale-category/product/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
    <input type="hidden" name="currentCateId" value="${categoryId}"/>
</cas:paginationForm> 

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
        <!-- mask="true" width="1230" height="530" navTab  dialog-->
       	  <li><a class="icon" href="${contextPath}/resources/product_mount_template.xlsx" ><span>下载模板</span></a></li>
       	  <li><a class="icon" href="${contextPath}/sale-category/product/upload_excel/${categoryId}" target="dialog"  rel="mount-dialog"><span>导入excel</span></a></li>
       	  <li><a class="icon" href="${contextPath}/sale-category/product/exportCategoryProds/${categoryId}" target="dwzExport" targetType="navTab" title="确定要导出记录吗?"><span>导出excel</span></a></li>
          <li><a id="a_back"  onclick="toBack()"   targetType="navTab" title="确定要导出记录吗?"><span>返回</span></a></li>
        
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="300">商品名称</th>
                <th width="150">商品编码 </th>
                <th width="150">状态</th>
                <th width="150">排序值</th>
                <%-- <th width="150" orderField="gmtCreate" class="${page.orderField eq 'gmtCreate' ? page.orderDirection : ''}">创建时间</th> --%>
                <th width="150">产品图片</th>
                <th width="150">品牌名称 </th>
                <th width="150">挂载类型</th>
                <th width="150">挂载人 </th>
                <th width="200">挂载时间 </th>
                <th width="120">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${relationList != null}">
	            <c:forEach var="relationModel" items="${relationList}">
	            <tr target="slt_uid" rel="${relationModel.productMallViewModel.productId}" style="text-align: center;height: 80px">
	                <td>${relationModel.productMallViewModel.productName}</td>
	                <td>${relationModel.productMallViewModel.productCode}</td>
	                <td>
		            	<c:forEach items="${shelvesTypeMap}" var="shelvesType">
		            		<c:if test="${ shelvesType.key == relationModel.productMallViewModel.productShelves}">${shelvesType.value }</c:if>
		            	</c:forEach>
		            </td>
		            <td>${relationModel.orderValue}</td>
	                <td>
	                	<c:if test="${!empty relationModel.productMallViewModel.mainPic}">
	                		<img alt="" src="${relationModel.productMallViewModel.mainPic}" style="width: 80px;height: 80px">
	                	</c:if>
	                </td>
	                <%-- <td><fmt:formatDate value="${item.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
	                <td>${relationModel.productMallViewModel.brandName}</td>
		            <td>
		            	<c:forEach items="${mountTypeMap}" var="mountType">
		            		<c:if test="${ mountType.key == relationModel.mountType}">${mountType.value }</c:if>
		            	</c:forEach>
		            </td>
		            <td>${relationModel.mountUser}</td>
		            <td><fmt:formatDate value="${relationModel.mountTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
		            <td>
				            <a class="edit" target="dialog" rel="" mask="true" width="536" height="180" href="${contextPath}/sale-category/product/toUpdateOrderVal/${relationModel.relationId}" >
		                       <span style="color: green;">编辑排序值</span>
		                    </a>
			         </td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
   
</div>

 <script>
   var $span = $("span", $("#a_back"));
     $span.css({
		"background-image":"url(${contextPath}/styles/dwz/themes/css/images/toolbar_icons16/" + "arrow_turn_left" + ".png)",
		"background-position": "0 3px"
	}); 
  
  
  
  
</script>