<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
		.searchBar{
			margin: 0 auto;
		}
		.searchBar table {height:50px}
		.searchBar table input{width: 100px}
		
		.grid .gridTbody td div{
			height:auto!important;
		}
</style>
<cas:paginationForm action="${contextPath}/info/product/list" page="${page}">
	<input type="hidden" name="productModel.orderField"       value="${productModel.orderField}" />  
	<input type="hidden" name="productModel.orderDirection"  value="${productModel.orderDirection}" />
    <input type="hidden" name="productName" value="${productModel.productName  }"/>
    <input type="hidden" name="productCode" value="${productModel.productCode  }"/>
    <input type="hidden" name="brandName" value="${productModel.brandName  }"/>
    <input type="hidden" name="supplierName" value="${productModel.supplierName  }"/>
    <input type="hidden" name="type" value="${productModel.type  }"/>
    <input type="hidden" name="createStartTime" value="${createStartTime  }"/>
    <input type="hidden" name="endTime1" value="${endTime1  }"/>
    <input type="hidden" name="productShelves" value="${productModel.productShelves  }"/>
    <input type="hidden" name="startPrice" value="${startPrice  }"/>
    <input type="hidden" name="endPrice" value="${endPrice  }"/>
    <input type="hidden" name="minTshPrice" value="${startPrice  }"/>
    <input type="hidden" name="maxTshPrice" value="${endPrice  }"/>
    <input type="hidden" name="catCode" value="${categoryCode3}"/> 
    <input type="hidden" name="categoryName1" value="${categoryName1  }"/>
    <input type="hidden" name="categoryName2" value="${categoryName2  }"/>
    <input type="hidden" name="categoryName3" value="${categoryName3  }"/>
    <input id="cc1" type="hidden" value="${categoryCode1}"/>
    <input id="cc2" type="hidden" value="${categoryCode2}"/>
    <input id="cc3" type="hidden" value="${categoryCode3}"/>
    <input type="hidden" name="startUpTime" value="${startUpTime  }"/>
    <input type="hidden" name="endUpTime" value="${endUpTime  }"/>
    <input type="hidden" name="startDownTime" value="${startDownTime  }"/>
    <input type="hidden" name="endDownTime" value="${endDownTime  }"/>
</cas:paginationForm> 
<form method="post" action="${contextPath}/info/product/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
           <table width="100%">
			<tr>
			    <td width="20px" align="right">创建时间区间：</td>
				<td width="220px">
					<input type="text" class="date" name="createStartTime" size="15" dateFmt="yyyy-MM-dd" value="${createStartTime}" readonly="true"/>
                     -
                     <input type="text" class="date" name="endTime1" size="15" dateFmt="yyyy-MM-dd" value="${endTime1 }" readonly="true"/>
                </td>
				<td width="80px" align="right">商品编号：</td>
				<td width="20px" ><input type="text" name="productCode" size="20" value="${productModel.productCode }" class="textInput"></td>
				<td width="80px" align="right">商品名称：</td>
				<td width="20px"><input type="text" name="productName" size="20" value="${productModel.productName }" class="textInput"></td>
				
				<td width="80px" align="right">分类名称：</td>
				<td>
					<select class="" name="categoryName1" id="category1" ref="category2" onchange="comboxChange(this);"style="width:100px;">
						<option value="">选择分类</option>
						 <c:if test="${listCategory!=null }">
            			<c:forEach items="${listCategory }" var="category">
            				<option value="${category.categoryCode }"  >${category.categoryName }</option>
            			</c:forEach>
            			</c:if>
					</select>
					-
					<select class="" name="categoryName2" id="category2" ref="category3" onchange="comboxChange(this);" style="width:100px;">
						<option value="">选择分类</option>
					</select>
					-
					<select class="" name="categoryName3" id="category3" style="width:100px;">
						<option value="">选择分类</option>
					</select>
				</td>
			</tr>
			<tr>
			    <td width="120px" align="right">下架时间区间：</td>
				<td>
					<input type="text" class="date" name="startDownTime" size="8" dateFmt="yyyy-MM-dd" value="${startDownTime}" readonly="true"/>
					-
                    <input type="text" class="date" name="endDownTime" size="8" dateFmt="yyyy-MM-dd" value="${endDownTime}" readonly="true"/>     
					
				</td>
				<td width="80px" align="right">品牌名称：</td>
				<td><input type="text" name="brandName" size="20" value="${productModel.brandName }" class="textInput"></td>
				<td width="80px" align="right">商品类型：</td>
				<td>
					<select name="type" style="width:100px;">
						<option value=""   >选择类型</option>
						<option value="01" <c:if test="${productModel.type=='01' }">selected="selected"</c:if> >特卖</option>
						<option value="02" <c:if test="${productModel.type=='02' }">selected="selected"</c:if> >零元购</option>
					</select>
				</td>
				<td width="100px" align="right">销售价格区间：</td>
				<td>
					<input type="text"  size="8" name="startPrice" class="textInput"   value="${startPrice}">
					-
					<input type="text"  size="8" name="endPrice" class="textInput" value="${endPrice}" >
				</td>
			</tr>
			<tr>
			    <td width="120px" align="right">上架时间区间：</td>
				<td>
				    <input type="text" class="date" name="startUpTime" size="8" dateFmt="yyyy-MM-dd" value="${startUpTime}" readonly="true"/>
					-
					<input type="text" class="date" name="endUpTime" size="8" dateFmt="yyyy-MM-dd" value="${endUpTime}" readonly="true"/>	
				</td>
				<td width="80px" align="right">上下架状态：</td>
				<td>
					<select name="productShelves" style="width:100px;">
						<option value="">请选择</option>
						<option value="0"  <c:if test="${productModel.productShelves==0 }">selected="selected"</c:if> >下架</option>
						<option value="1"  <c:if test="${productModel.productShelves==1 }">selected="selected"</c:if> >上架</option>
					</select>
				</td>
				<td align="center" >
					<button type="submit">  搜索  </button>
				</td>
			</tr>
		</table>
		</ul>
        </div>
    </div>
</form>

<div class="pageContent j-resizeGrid">
    <div class="panelBar">
        <ul class="toolBar">
        <!-- mask="true" width="1230" height="530" navTab  dialog-->
          <li><a class="add" target="navTab"   href="${contextPath}/info/product/to_add_category"><span>添加</span></a></li>
          <li><a class="edit" target="navTab" href="${contextPath }/info/product/to_info/{slt_uid}"><span>查看编辑</span></a></li>
          <li><a class="edit"  href="#" onclick="yulan();"><span>预览</span></a></li>
          <li><a class="edit" target="selectedTodo" rel="ids" href="${contextPath }/info/shelves/operation?shelvesType=1" title="确认上架?"><span>上架</span></a></li>
          <li><a class="edit downShelves" target="selectedTodo" rel="ids" href="${contextPath }/info/shelves/operation?shelvesType=0" title="确认下架?"  onclick="clickDownShelves(this);"><span>下架</span></a></li>
         <%-- <li><a class="edit" target="navTab" rel="" mask="true" width="1400" height="530" href="${contextPath }/info/product/to_look/{slt_uid}"><span>预览</span></a></li> --%>
       	  <%-- <li><a class="delete" target="ajaxTodo"  href="${contextPath}/info/product/delete/{slt_uid}" title="确认要删除?"><span>删除</span></a></li> --%>
       	  <li><a class="icon" href="${contextPath}/info/product/exportProduct_product" id="exportProduct_product" target="dwzExport"  targetType="dialog" onclick="setPage(this);" title="要导出这些记录吗?(最多导出5000条记录)"><span>商品级别导出</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/product/exportProduct_SKU" id="exportProduct_SKU" target="dwzExport"  targetType="dialog" onclick="setPage1(this);" title="要导出这些记录吗?(最多导出5000条记录)"><span>SKU级别导出</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/product/to_upload_add" target="dialog" mask="true" width="850" height="500" ><span>导入商品信息excel</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/product/to_upload_update" target="dialog" mask="true" width="850" height="500" ><span>excel批量编辑商品信息</span></a></li>
       	  <li><a class="icon" href="${contextPath}/info/product/to_upload_taobao_add" target="dialog" mask="true" width="850" height="500" ><span>导入淘宝商品信息excel</span></a></li>
          <li><a class="edit" href="${contextPath}/info/product/upload_image" target="navTab" rel="image"><span>图片导入</span></a></li> 
        </ul>
    </div>
    
    <table class="table"  layoutH="155" width="100%">
        <thead>
            <tr>
                <th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>      
                <th width="100">商品主图</th>
                <th width="150" orderField="product_name" <c:if test='${productModel.orderField == "product_name" }'>class="${productModel.orderDirection}"</c:if>>商品名称</th>
                <th width="150" orderField="product_code" <c:if test='${productModel.orderField == "product_code" }'>class="${productModel.orderDirection}"</c:if>>商品编码 </th>
                <th width="100" orderField="product_shelves" <c:if test='${productModel.orderField == "product_shelves" }'>class="${productModel.orderDirection}"</c:if>>上下架状态</th>
                <th width="100">最后上架时间</th>
                <th width="100">最后下架时间 </th> 
                <th width="100" orderField="brand_name" <c:if test='${productModel.orderField == "brand_name" }'>class="${productModel.orderDirection}"</c:if>>品牌名称 </th>
                <th width="100" orderField="cat_name" <c:if test='${productModel.orderField == "cat_name" }'>class="${productModel.orderDirection}"</c:if>>分类名称 </th>
                <th width="100" orderField="tsh_price" <c:if test='${productModel.orderField == "tsh_price" }'>class="${productModel.orderDirection}"</c:if>>销售价 </th>
                <th width="100" orderField="market_price" <c:if test='${productModel.orderField == "market_price" }'>class="${productModel.orderDirection}"</c:if>>市场价 </th>
                <th width="140" orderField="create_time" <c:if test='${productModel.orderField == "create_time" }'>class="${productModel.orderDirection}"</c:if>>创建时间  </th>
                <th width="100" orderField="type" <c:if test='${productModel.orderField == "type" }'>class="${productModel.orderDirection}"</c:if>>商品类型 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${productList!=null}">
	            <c:forEach var="productModel" items="${productList}">
	            <tr target="slt_uid" rel="${productModel.productId}" style="text-align: center;height:80px;">
	                <td><input name="ids" value="${productModel.productId}" type="checkbox" class="checkboxCtrl"></td>
	                <td> 
                        <img  style="height:80px;width:100px;" alt="图片不存在" src="${productModel.mainPic}" />
                      </td>
	                <td>${productModel.productName}</td>
	                <td>${productModel.productCode}</td>
	                <td>
	                	<c:if test="${productModel.productShelves==0 }">下架</c:if>
	                	<c:if test="${productModel.productShelves==1 }">上架</c:if>
	                </td>
	                <td>
	                    ${productModel.lastShelvesTimes}
	                </td> 
	                <td>${productModel.downShelvesTimes}</td>
	                <td>${productModel.brandName}</td>
	                <td>${productModel.catName}</td>
	                <td>${productModel.tshPrice}</td>
	                <td>${productModel.marketPrice}</td>
	                <td>${productModel.createTimes}</td>
	                <td>
	                	<c:if test="${productModel.type=='01'}">特卖</c:if>
		                <c:if test="${productModel.type=='02'}">零元购</c:if> 
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

function setPage(element){
		var str = prompt("请根据总条数设置导出第几页,输入规则：1、输入大于最大页数,则导出最后一页  2、输入不是有效数字或空,则导出第一页(每页最多5000条)：","");
		if(str!=null && str!=""){
			 var re = /^\d+(\.\d+)?$/;
			if(!re.test(str)){
				str="1";
			} 
			var sta = encodeURIComponent(str);
			var src = "${contextPath}/info/product/exportProduct_product?page="+sta;
			$("#exportProduct_product").attr("href",src);
		}else{
			var src = "${contextPath}/info/product/exportProduct_product?page=1";
			$("#exportProduct_product").attr("href",src);
		}
}
function setPage1(element){
	var str = prompt("请根据总条数设置导出第几页,输入规则：1、输入大于最大页数,则导出最后一页  2、输入不是有效数字或空,则导出第一页(每页最多5000条)：","");
	if(str!=null && str!=""){
		 var re = /^\d+(\.\d+)?$/;
		if(!re.test(str)){
			str="1";
		} 
		var sta = encodeURIComponent(str);
		var src = "${contextPath}/info/product/exportProduct_SKU?page="+sta;
		$("#exportProduct_SKU").attr("href",src);
	}else{
		var src = "${contextPath}/info/product/exportProduct_SKU?page=1";
		$("#exportProduct_SKU").attr("href",src);
	}
}
function clickDownShelves(element){
	
	var str = prompt("下架原因","");
	if(str!=null && str!=""){
		var sta = encodeURIComponent(str);
		var src = "${contextPath }/info/shelves/operation?shelvesType=0&reason="+sta;
		$(".downShelves").attr("href",src);
	}
}


function comboxChange(element){
		$.ajax({
             type: "post",
             url: "${contextPath}/dwz/combox",
             data: "code="+element.value+"&type=combox",
             dataType: "text",
             success: function(data){
            	if(element.id=='category1'){
            		$("#category2").html(data);
            		$("#category3").html("<option value=''>选择分类</option>");
            		
            	}else{
            		$("#category3").html(data);
            	}
             }
        })
	
};

function aaa(id,value){
	$.ajax({
         type: "post",
         url: "${contextPath}/dwz/combox",
         data: "code="+value+"&type=combox",
         dataType: "text",
         async: false,
         success: function(data){
        	if(id=='category1'){
        		$("#category2").html(data);
        		$("#category3").html("<option value=''>选择分类</option>");
        		
        	}else{
        		$("#category3").html(data);
        	}
         }
    })

};

$(document).ready(function(){
var cc1=$("#cc1").val();
var cc2=$("#cc2").val();
	var cc3=$("#cc3").val();
$("#category1").val(cc1);
if(cc1.length>1){
		aaa('category1',cc1);
}
if(cc2.length>1){
$("#category2").val(cc2);
aaa('category2',cc2);
}
if(cc3.length>1){
	
$("#category3").val(cc3);
}
});

function yulan(){
	 var str="";
     $("input[name='ids']:checkbox").each(function(){ 
         if($(this).attr("checked")){
             str = $(this).val();
         }
     })
    if(str==""){
    	alert("请选择");
    }else{ 
	window.open("${contextPath }/info/product/to_look/"+str);
    }
}

</script>