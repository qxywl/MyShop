<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<cas:paginationForm action="${contextPath}/info/product/examine_list" page="${page}">
	<input type="hidden" name="productModel.orderField"       value="${productModel.orderField}" />  
	<input type="hidden" name="productModel.orderDirection"  value="${productModel.orderDirection}" />
    <input type="hidden" name="productCode" value="${productInfoAuthModel.productCode  }"/>
    <input type="hidden" name="infoAuthStartTime" value="${infoAuthStartTime}"/>
    <input type="hidden" name="infoAuthEndTime" value="${infoAuthEndTime}"/>
    <input type="hidden" name="startTime2" value="${startTime2}"/>
    <input type="hidden" name="endTime2" value="${endTime2}"/>
    <input type="hidden" name="staus" value="${productInfoAuthModel.staus  }"/>
    <input type="hidden" name="shopName" value="${productInfoAuthModel.shopName  }"/>
    <input type="hidden" name="productName" value="${productInfoAuthModel.productName  }"/>
    <input type="hidden" name="brandName" value="${productInfoAuthModel.brandName  }"/>
    <input type="hidden" name="catName" value="${productInfoAuthModel.catName  }"/>
    <input type="hidden" name="createUserName" value="${productInfoAuthModel.createUserName }"/>
    <input type="hidden" name="updateUserName" value="${productInfoAuthModel.updateUserName  }"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/product/examine_list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
         <table width="100%">
			<tr>
				<td width="80px" align="right">商品名称：</td>
				<td><input type="text" name="productName" size="20" value="${productInfoAuthModel.productName }" class="textInput"></td>
				<td width="80px" align="right">店铺名称：</td>
				<td><input type="text" name="shopName" size="20" value="${productInfoAuthModel.shopName }" class="textInput"></td>
				<td width="80px" align="right">类目名称：</td>
				<td><input type="text" name="catName" size="20" value="${productInfoAuthModel.catName }" class="textInput"></td>
				<td width="80px" align="right">品牌名称：</td>
				<td><input type="text" name="brandName" size="20" value="${productInfoAuthModel.brandName }" class="textInput"></td>
					
			</tr>
			<tr>
			    <td width="80px" align="right">商品编号：</td>
				<td><input type="text" name="productCode" size="20" value="${productInfoAuthModel.productCode }" class="textInput"></td>
			<td width="80px" align="right">创建时间：</td>
				<td>
					<input type="text" class="date" name="infoAuthStartTime" size="15" dateFmt="yyyy-MM-dd" value="${infoAuthStartTime}" readonly="true"/>
                     -
                     <input type="text" class="date" name="infoAuthEndTime" size="15" dateFmt="yyyy-MM-dd" value="${infoAuthEndTime }" readonly="true"/>
					<%-- <input type="text" class="date" name="createTimes" dateFmt="yyyy-MM-dd" value="${productInfoAuthModel.createTimes }" readonly="true" value=""/> --%>
				</td>
				<td width="80px" align="right">审核状态：</td>
	            <td><select name="staus">
	                    <option value="">请选择</option>
	                	<option value="1" <c:if test="${productInfoAuthModel.staus==1}">selected="selected"</c:if>>待审核</option>
	                	<option value="2" <c:if test="${productInfoAuthModel.staus==2}">selected="selected"</c:if>>审核通过</option>
	                	<option value="3" <c:if test="${productInfoAuthModel.staus==3}">selected="selected"</c:if>>待同步</option>
	                	<option value="4" <c:if test="${productInfoAuthModel.staus==4}">selected="selected"</c:if>>审核通过,同步成功</option>
	                	<option value="8" <c:if test="${productInfoAuthModel.staus==8}">selected="selected"</c:if>>审核不通过</option>
	                	<option value="5" <c:if test="${productInfoAuthModel.staus==5}">selected="selected"</c:if>>审核不通过,同步成功</option>
	                	<option value="9" <c:if test="${productInfoAuthModel.staus==9}">selected="selected"</c:if>>处理失败</option>
	                	<option value="6" <c:if test="${productInfoAuthModel.staus==6}">selected='selected'</c:if> >系统处理</option>
	                </select>
	            </td>
				<td width="80px" align="right">审核人：</td>
				<td><input type="text" name="updateUserName" size="20" value="${productInfoAuthModel.updateUserName }" class="textInput"></td>
	            <td><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></td>
			</tr>
		    <tr>
		        <td width="80px" align="right">创建人：</td>
	            <td><input type="text" name="createUserName" size="20" value="${productInfoAuthModel.createUserName }" class="textInput"></td>
	            <td width="80px" align="right">审核时间：</td>
				<td>
					<input type="text" class="date" name="startTime2" size="15" dateFmt="yyyy-MM-dd" value="${startTime2}" readonly="true"/>
                     -
                    <input type="text" class="date" name="endTime2" size="15" dateFmt="yyyy-MM-dd" value="${endTime2}" readonly="true"/>
					
				</td>
		    </tr>
			</table>
        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
        <!-- mask="true" width="1230" height="530" navTab-->
          <li><a class="add" target="selectedTodo" rel="ids" href="${contextPath}/info/product/product_examine?submitType=1&info=0&staus=2" title="确定审核通过？"><span>审核通过</span></a></li>
       	  <li><a class="delete" target="selectedTodo" rel="ids" id="noExamine" href="${contextPath}/info/product/product_examine?submitType=1&info=0&staus=8" title="确定审核不通过？" onclick="examineClick(this);" ><span>审核不通过</span></a></li>
       	  <li><a class="edit" target="navtab" rel="" mask="true" width="800" height="530" href="${contextPath }/info/product/info_examine/{slt_uid}?pageType=1"><span>查看详情</span></a></li>
       	  <!--  <li><a class="edit" href="#" onclick="yulan1(this);"><span>预览</span></a></li>-->
       	  <li><a class="icon" href="${contextPath}/info/product/examine/export" id="exportExamine" target="dwzExport"  targetType="dialog" onclick="setPage1(this);" title="要导出这些记录吗?(最多导出10000条记录)"><span>导出审核记录</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="155" width="100%">
        <thead>
            <tr>
                <th width="22"><input type="checkbox" group="ids"  class="checkboxCtrl"></th>
                      
                <th width="100" orderField="product_code" <c:if test='${productModel.orderField == "product_code" }'>class="${productModel.orderDirection}"</c:if>>商品编码 </th>
                <th width="100">商品名称 </th> 
                <th width="100">类目名称 </th>
                <th width="100">品牌名称 </th>
                <th width="100">店铺名称 </th>
                <th width="100" orderField="type" <c:if test='${productModel.orderField == "type" }'>class="${productModel.orderDirection}"</c:if>>审核类型 </th>
                <th width="100" orderField="create_user_name" <c:if test='${productModel.orderField == "create_user_name" }'>class="${productModel.orderDirection}"</c:if>>创建人</th>
                <th width="100" orderField="create_time" <c:if test='${productModel.orderField == "create_time" }'>class="${productModel.orderDirection}"</c:if>>创建时间 </th>
                <th width="100" orderField="update_user_name" <c:if test='${productModel.orderField == "update_user_name" }'>class="${productModel.orderDirection}"</c:if>>审核人 </th>
                <th width="100" orderField="update_time" <c:if test='${productModel.orderField == "update_time" }'>class="${productModel.orderDirection}"</c:if>>审核时间 </th>
                <th width="100" orderField="staus" <c:if test='${productModel.orderField == "staus" }'>class="${productModel.orderDirection}"</c:if>>审核状态 </th>
                <th width="100">说明 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${listInfoAuth!=null}">
	            <c:forEach var="productInfoAuthModel" items="${listInfoAuth}">
	            <tr target="slt_uid" rel="${productInfoAuthModel.productInfoAuthId}" style="text-align: center;">
	                <td><input <c:if test="${productInfoAuthModel.staus==1 }">name="ids"</c:if> value="${productInfoAuthModel.productInfoAuthId}" type="checkbox" <c:if test="${productInfoAuthModel.staus!=1 }">disabled="disabled"</c:if>  class="checkboxCtrl"></td>
	                <td >
	                	<a 
	                	<c:if test="${productInfoAuthModel.staus==1}">
	                	style="color:red;" 
	                	</c:if>
	                	
	                	target="navTab" rel="2" 
	                	
	                	href="${contextPath }/info/product/info_examine/${productInfoAuthModel.productInfoAuthId}?pageType=1"
	                	>${productInfoAuthModel.productCode}</a>
	                </td>
	                <td >${productInfoAuthModel.productName}</td>
	                <td >${productInfoAuthModel.catName}</td>
	                <td >${productInfoAuthModel.brandName}</td>
	                <td >${productInfoAuthModel.shopName}</td>
	                <td>
	                	<c:if test="${productInfoAuthModel.type==1}">新增商品</c:if>
	                	<c:if test="${productInfoAuthModel.type==2}">页面修改</c:if>
	                	<c:if test="${productInfoAuthModel.type==3}">导入修改</c:if>
	                </td>
	                <td>${productInfoAuthModel.createUserName}</td>
	                <td>${productInfoAuthModel.createTimes}</td>
	                <td>${productInfoAuthModel.updateUserName}</td>
	                <td>${productInfoAuthModel.updateTimes}</td>
	                <td>
	                	<c:if test="${productInfoAuthModel.staus==0}">待处理</c:if>
	                	<c:if test="${productInfoAuthModel.staus==1}">待审核</c:if>
	                	<c:if test="${productInfoAuthModel.staus==2}">审核通过</c:if>
	                	<c:if test="${productInfoAuthModel.staus==3}">待同步</c:if>
	                	<c:if test="${productInfoAuthModel.staus==4}">审核通过,同步成功</c:if>
	                	<c:if test="${productInfoAuthModel.staus==8}">审核不通过</c:if>
	                	<c:if test="${productInfoAuthModel.staus==5}">审核不通过,同步成功</c:if>
	                	<c:if test="${productInfoAuthModel.staus==9}">处理失败</c:if>
	                	<c:if test="${productInfoAuthModel.staus==6}">系统处理</c:if>
	                </td>
	                <td>${productInfoAuthModel.reason}</td>
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
			var sta = encodeURIComponent(str);
			var src = "${contextPath}/info/product/product_examine?submitType=1&info=0&staus=8&reason="+sta;
			$("#noExamine").attr("href",src);
		}
	}
	
	function yulan1(element){
		 var str="";
	     $("input[name='ids']:checkbox").each(function(){ 
	         if($(this).attr("checked")){
	             str = $(this).val();
	         }
	     })
	    if(str==""){
	    	alert("请选择");
	    }else{ 
		window.open("${contextPath }/info/product/to_examine_look/"+str);
	   }
	}
	
	function setPage1(element){
		
			var str = prompt("请根据总条数设置导出第几页,输入规则：1、输入大于最大页数,则导出最后一页  2、输入不是有效数字或空,则导出第一页(每页最多10000条)：","");
			if(str!=null && str!=""){
				 var re = /^\d+(\.\d+)?$/;
				if(!re.test(str)){
					
					str="1";
				} 
				var sta = encodeURIComponent(str);
				var src = "${contextPath}/info/product/examine/export?page="+sta+"&exportType="+str1;
				$("#exportExamine").attr("href",src);
			}else{
				var src = "${contextPath}/info/product/examine/export?page=1&exportType="+str1;
				$("#exportExamine").attr("href",src);
			}
		
	}
</script>