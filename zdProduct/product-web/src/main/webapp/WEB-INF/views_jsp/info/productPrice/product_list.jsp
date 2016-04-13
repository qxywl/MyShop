<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<cas:paginationForm action="${contextPath}/info/productPrice/list" page="${page}">
    <input type="hidden" name="productSkuCode" value="${productPriceListModel.productSkuCode}"/>
    <input id="cc1" name="categoryCode" type="hidden" value="${productPriceListModel.categoryCode}"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/productPrice/list" onsubmit="return navTabSearch(this)" rel="pagerForm">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>skucode：</label>
                    <input type="text" name="productSkuCode"    value="${productPriceListModel.productSkuCode}"/>
                </li>
                <li>
                    <label>审核状态：</label>
                     <select name="status" >
                          <option value=""  >请选择</option>
                          <option value="1" <c:if test="${productPriceListModel.status==1}">selected='selected'</c:if> >待审核</option>
                          <option value="2" <c:if test="${productPriceListModel.status==2}">selected='selected'</c:if> >审核通过</option>
                          <option value="3" <c:if test="${productPriceListModel.status==3}">selected='selected'</c:if> >待同步</option>
                          <option value="4" <c:if test="${productPriceListModel.status==4}">selected='selected'</c:if> >审核通过,同步成功</option>
                          <option value="8" <c:if test="${productPriceListModel.status==8}">selected='selected'</c:if> >审核拒绝</option>
                          <option value="5" <c:if test="${productPriceListModel.status==5}">selected='selected'</c:if> >审核拒绝,同步成功</option>
                          <option value="9" <c:if test="${productPriceListModel.status==9}">selected='selected'</c:if> >处理失败</option>
                    </select>
                </li>
                <li>
                    <label>一级分类：</label>
                    <select class="" name="categoryCode" id="category"   style="width:100px;">
						 <option value="">选择分类</option>
						 <c:if test="${listCategory!=null }">
            			<c:forEach items="${listCategory }" var="category">
            				<option value="${category.categoryCode }"  >${category.categoryName }</option>
            			</c:forEach>
            			</c:if>
					</select>
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
<div class="pageContent j-resizeGrid">

    <div class="panelBar">
    
        <ul class="toolBar">
         <!-- mask="true" width="1230" height="530" navTab-->
          <li><a class="icon" href="${contextPath}/info/productPrice/to_upload_excel" target="dialog"  ><span>导入excel</span></a></li>
        </ul>
    </div>
    
    
      <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th width="100">skucode</th>
                <th width="100">售价</th>
                <th width="100">市场价</th>
                <th width="100">调价前售价</th>
                <th width="100">调价前市场价</th>
                <th width="100">价格浮动率</th>
                <th width="100">创建人</th>
                <th width="100">创建时间</th>
                <th width="100">审核人</th>
                <th width="100">审核时间</th>
                <th width="100">审核原因</th>
                <th width="100">状态</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${productPriceList!=null}">
	            <c:forEach var="productPriceListModel" items="${productPriceList}">
	            <tr target="slt_uid" rel="${productPriceListModel.productPriceList}" style="text-align: center;">
	                <c:if test="${productPriceListModel.status==1 }">
	                <td width="22"><input name="ids" value="${productPriceListModel.productPriceList}" type="checkbox" class="checkboxCtrl"></td>
	                <a <c:if test="${productInfoAuthModel.staus==1}">style="color:red;"</c:if> target="navTab" rel="2" href="${contextPath }/info/product/to_info/${productInfoAuthModel.productCode}">${productInfoAuthModel.productCode}</a>
	                </c:if>
	                <c:if test="${productPriceListModel.status!=1 }">
	                <td width="22"><input  type="checkbox" disabled=“disabled” class="checkboxCtrl"></td>
	                </c:if>
	                <c:if test="${productPriceListModel.costPrice > productPriceListModel.oldCostPrice }">
	                <td width="120" >
	                	<a style="color:red;" target="navTab" rel="2" href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
	                </td>
	                </c:if>
	                <c:if test="${productPriceListModel.oldCostPrice==null }">
	                	<td width="120" >
		                	<a target="navTab" rel="2" href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
		                </td>
	                </c:if>
	                 <c:if test="${productPriceListModel.costPrice <= productPriceListModel.oldCostPrice }">
	                  <td width="120">
	                  	<a style="color:green;" target="navTab" rel="2" href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
	                  </td>
	                </c:if>
	                <td width="100">${productPriceListModel.tshPrice}</td>
	                <td width="100">${productPriceListModel.marketPrice}</td>
	                <td width="100">${productPriceListModel.oldTshPrice}</td>
	                <td width="100">${productPriceListModel.oldMarketPrice}</td>
	                <td width="100">
	                 <c:if test="${!empty  productPriceListModel.oldTshPrice}">
	                <fmt:formatNumber type="number" value="${productPriceListModel.tshPrice/productPriceListModel.oldTshPrice}" maxFractionDigits="2"/>
	                </c:if>
	                </td>
	                <td width="100">${productPriceListModel.createUserName}</td>
	                <td width="100">${productPriceListModel.createTimes}</td>
	                <td width="100">${productPriceListModel.updateUserName}</td>
	                <td width="100">${productPriceListModel.updateTimes}</td>
	                <td width="100">${productPriceListModel.reason}</td>
	                <td width="100">
	                <c:if test="${productPriceListModel.status==1}">待审核</c:if>
	                <c:if test="${productPriceListModel.status==2}">审核通过</c:if>
	                <c:if test="${productPriceListModel.status==3}">待同步</c:if>
	                <c:if test="${productPriceListModel.status==4}">审核通过,同步成功</c:if>
	                <c:if test="${productPriceListModel.status==8}">审核拒绝</c:if>
	                <c:if test="${productPriceListModel.status==5}">审核拒绝,同步成功</c:if>
	                <c:if test="${productPriceListModel.status==9}">处理失败</c:if>
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
	function examineClick(element){
		var str = prompt("审核不通过的原因","");
		if(str!=null && str!=""){
			var src = "${contextPath}/info/productPrice/updateNo?reason="+str;
			$("#noExamine").attr("href",src);
		}
	}
	
	$(document).ready(function(){
		var cc1=$("#cc1").val(); 
		$("#category").val(cc1);
		});
</script>