<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

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
<script type="text/javascript">

   function openPicture(){
	   navTab.openExternal("http://www.t.teshehui.com/data/files/store_57/goods_121/201401031345213536.jpg","body")
// 	   navTab.openTab("", "http://www.t.teshehui.com/data/files/store_57/goods_121/201401031345213536.jpg", { title:"picture", fresh:false, data:{},external:true });
   }
 

</script>
    <cas:paginationForm action="${contextPath}/brand/list" page="${page}">
        <input type="hidden" name="brandCode" value="${productBrandModel.brandCode}"/>
        <input type="hidden" name="brandName" value="${productBrandModel.brandName}"/>
    </cas:paginationForm> 
	
	<form id="test"method="post" action="${contextPath}/brand/list" onsubmit="return navTabSearch(this)">
	 <div class="pageHeader">
	 <div class="searchBar">
		<table class="searchContent">
			<tbody><tr>
				<td>
					品牌编号：<input type="text" name="brandCode" value="${productBrandModel.brandCode}" class="textInput">
				</td>
				<td>
					品牌名称：<input type="text" name="brandName" value="${productBrandModel.brandName}" class="textInput">
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
          <li><a class="add"  target="dialog" rel="brand" mask="true" width="630" height="520" href="${contextPath}/brand/to_add"><span>添加品牌</span></a></li>
          <li><a class="edit" target="dialog" rel="brand" mask="true" width="630" height="510" href="${contextPath}/brand/to_update/{slt_uid}"><span>编辑品牌</span></a></li>
          <li><a class="edit" target="dialog" rel="brand" mask="true" width="630" height="530" href="${contextPath}/brand/to_detail/{slt_uid}"><span>查看详情</span></a></li>  
       	  
       	  <li><a class="delete" target="ajaxTodo"    rel="" href="${contextPath}/brand/abandon/{slt_uid}" title="确认要删除?"  onsubmit="return navTabSearch(this);"><span>删除品牌</span></a></li>
       	  <%-- <li><a class="delete" target="selectedTodo"    rel="ids"  href="${contextPath}/brand/batchAbandon" title="确认要删除?"  onsubmit="return navTabSearch(this);"><span>批量删除品牌</span></a></li> --%>
       	  <li class="line">line</li>
       	  <li><a class="icon" href="${contextPath}/brand/export" target="dwzExport" targetType="navTab" title="确定要导出记录吗?"><span>导出excel</span></a></li>
          <li><a class="icon" href="${contextPath}/brand/to_import"  target="dialog"   width="530" height="250" title=""><span>导入excel</span></a></li>
          
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th ><input style="display:none;" type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th >品牌logo </th>
                <th >品牌编号 </th>
                <th >品牌名称 </th>
                <th >品牌别名</th>
                <th >品牌国家 </th>
                <th >状态</th>
                <!-- <th >品牌旗舰店铺编号  </th>
                <th >旗舰店认证状态  </th> -->
                <th >最后修改者</th>
                <th >修改时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${brandList!=null}">
	            <c:forEach var="brandModel" items="${brandList}">
	            <tr target="slt_uid" rel="${brandModel.brandId}" style="text-align: center;" height="60px">
	                <td><input name="ids" value="${brandModel.brandId}" type="radio" class="checkboxCtrl"></td>
	                <td>
		               <c:choose>
			              <c:when test="${brandModel.brandLogo!=null}">
			               <%--   <a href="${brandModel.brandLogo}" target="navTab"  rel="main12"   external="false">
			                     <img  style="height:60px;" src="${brandModel.brandLogo}" />
			                 </a> --%>
			                 <img  style="height:60px;" src="${brandModel.brandLogo}" /> 
			              </c:when>
			              <c:otherwise>
			                                             图片未上传
			              </c:otherwise>
			            </c:choose>
	                </td>
	                <td>${brandModel.brandCode}</td>
	                <td>${brandModel.brandName}</td>
	                <td>${brandModel.brandEname}</td>
	                <td>${brandModel.brandCountry}</td>
	                <td>    
	                  <c:choose>
			              <c:when test="${brandModel.brandStatus==1}">
			                                                             启用 
			              </c:when>
			              <c:when test="${brandModel.brandStatus==2}">
			                                                             禁用 
			              </c:when>
			              <c:when test="${brandModel.brandStatus==3}">
			                                                             已删除
			              </c:when>
			              <c:otherwise>
			                                                            状态错误                                     
			              </c:otherwise>
		              </c:choose>
	                </td>
	                <!-- 
	                <td>${brandModel.flagshipStoreCode}</td>
	                <td>
		                <c:choose>
				              <c:when test="${brandModel.storeCertificationType==1}">
				                                                             已认证 
				              </c:when>
				              <c:when test="${brandModel.storeCertificationType==2}">
				                                                             未认证
				              </c:when>
			              </c:choose>
	                </td>
	                 -->
	                <td>${brandModel.updateUser}</td>
	                <td><%-- <fmt:formatDate value="${brandModel.createTimes}"  type="date" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
	                    ${brandModel.updateTimes}
	                </td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>