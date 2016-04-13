<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
  .m_l { width:60%; float:left;padding-left:40px;padding-top:20px;}
  .m_cl{clear:both;}
  .m_r { width:25%; float:right;padding-top:50px;}
  .empty{}
  
  .dlt_a { color:blue; text-decoration:underline;}
  
</style>

<script type="text/javascript">

function addBrandTr(){
	var totBrdCount = $("#brdList_size_id").val();
	totBrdCount = parseInt(totBrdCount)+1;
	$("#brdList_size_id").val(totBrdCount);
	
	var brdAdd = '<tr height=\"24px\" id=\"brand_tr_'+totBrdCount+'\">'+
					'<td>' +
						'<input type=\"text\" class=\"textInput readonly\" id=\"brandName_'+totBrdCount+'\" name=\"productModel'+totBrdCount+'.brandName\" readonly=\"readonly\">'+
						'<input type=\"hidden\" id=\"brandCode_'+totBrdCount+'\" name=\"productModel'+totBrdCount+'.brandCode\">' +
					'</td>' + 
					'<td><a title=\"查找品牌\" class=\"btnLook\" href=\"${baseUrl}/dwz/selectback\" lookupGroup=\"productModel'+totBrdCount+'\">查找品牌</a></td>' + 
					'<td width=\"30px\" align=\"right\"><a href=\"javascript:;\" class=\"dlt_a\" onclick=\"deleteBrandTr('+totBrdCount+')\">删除</a></td>' +
				'</tr>';

   // $("#brand_tb>tbody").append(brdAdd);
	 var $tr = $(brdAdd);
	 var btbody= $("#brand_tb").find("tbody");
	 $tr.appendTo(btbody).initUI();
}

function deleteBrandTr(idxTr){
    $(this).parents("tr").remove();
    $("tr[id='brand_tr_"+idxTr+"']").remove();//删除当前行
};



function addCateTr(){
	var totBrdCount = $("#catList_size_id").val();
	totBrdCount = parseInt(totBrdCount)+1;
	$("#catList_size_id").val(totBrdCount);
	
	var brdAdd = '<tr height=\"24px\" id=\"cate_tr_'+totBrdCount+'\">'+
					'<td>' +
						'<input type=\"text\" class=\"textInput readonly\" id=\"cateName_'+totBrdCount+'\" name=\"categoryData'+totBrdCount+'.categoryName\" readonly=\"readonly\">'+
						'<input type=\"hidden\" id=\"cateId_'+totBrdCount+'\" name=\"categoryData'+totBrdCount+'.categoryId\">' +
					'</td>' + 
					'<td><a title=\"查找分类\" class=\"btnLook\" href=\"${baseUrl}/base-category/tree\" lookupGroup=\"categoryData'+totBrdCount+'\">查找分类</a></td>' + 
					'<td width=\"30px\" align=\"right\"><a href=\"javascript:;\" class=\"dlt_a\" onclick=\"deleteCateTr('+totBrdCount+')\">删除</a></td>' +
				'</tr>';
   // $("#category_tb>tbody").append(brdAdd);
   var $tr = $(brdAdd);
   var btbody= $("#category_tb").find("tbody");
   $tr.appendTo(btbody).initUI();
}

function deleteCateTr(idxTr){
    $(this).parents("tr").remove();
    $("tr[id='cate_tr_"+idxTr+"']").remove();//删除当前行
};

function saveRule(){
	
	var product_name = $("#product_name_id").val();
	var roduct_prefix = $("#product_prefix_id").val();
	var product_suffix = $("#product_suffix_id").val();
	var product_search = $("#product_search_tag").val();
	
	
	var name_flag = product_name == null || product_name == '' ;
	var prefix_flag = roduct_prefix == null || roduct_prefix == '' ;
	var suffix_flag = product_suffix == null || product_suffix == '' ;
	var search_flag = product_search == null || product_search == '' ;
	
	var brand_flag = checkBrandHasData();
	var cate_flag = checkCateHasData();
	
	var result_flag = name_flag && prefix_flag && prefix_flag && prefix_flag && !brand_flag && !cate_flag;;
	
	if(result_flag){
		alert("商品名称，商品前缀，商品后缀，关键词，品牌，分类 不能同时为空！！！");
		return;
	}
	
	$("#product_name_id1").val(product_name);
	$("#product_prefix_id1").val(roduct_prefix);
	$("#product_suffix_id1").val(product_suffix);
	$("#product_search_tag1").val(product_search);
	
	//品牌
	var totBrdCount = $("#brdList_size_id").val();
	var tmpId="";
	var totIds="";
	for(var i=0;i<=totBrdCount;i++){
		tmpId=$("#brandCode_"+i).val();
		if(tmpId != undefined && tmpId != ""){
			totIds = totIds + tmpId;
			if(i<totBrdCount){
				totIds = totIds+";";
			}
		}
	}
	
	if(totIds !=""){
		$("#brdList1").val(totIds);
	}else{
		$("#brdList1").val(null);
	}
	
	
	//分类
	totBrdCount = $("#catList_size_id").val();
	var totIds2="";
	for(var i=0;i<=totBrdCount;i++){
		tmpId=$("#cateId_"+i).val();
		if(tmpId != undefined && tmpId != ""){
			totIds2 = totIds2 + tmpId;
			if(i<totBrdCount){
				totIds2 = totIds2+";";
			}
		}
	}
	
	if(totIds2 != ""){
		$("#catList1").val(totIds2);
	}
	
	
	$("#mounuRuleForm").submit();
}

//判断brand 是否有值
function checkBrandHasData(){
	var totBrdCount = $("#brdList_size_id").val();
	
	var tmpId="";
	for(var i=0;i<=totBrdCount;i++){
		tmpId=$("#brandCode_"+i).val();
		if(tmpId != null  && tmpId != undefined  && tmpId != '' && tmpId != ""){
			return true;  //只要一个有值就满足
		}
	}
	
	return false
}

//判断分类 是否有值
function checkCateHasData(){
	var totCateCount = $("#catList_size_id").val();
	
	var tmpId="";
	for(var i=0;i<=totCateCount;i++){
		tmpId=$("#cateId_"+i).val();
		if(tmpId != null  && tmpId != undefined  && tmpId != '' && tmpId != ""){
			return true;  //只要一个有值就满足
		}
	}
	
	return false
}

//返回上一下
function toBack(){
	var url = "${contextPath}/sale-category/main?currentCateId=${currentCateId}";
	navTab.closeCurrentTab();
	navTab.openTab("brand", url, { title:"运营分类管理", fresh:false, data:{} });
}


</script>

   <c:if test="${addOper }">
   		<form action="${baseUrl }/mount-rule/add" method="POST" id="mounuRuleForm" onsubmit="return validateCallback(this, dialogReloadNavTab)">
   		    <input type="hidden" name="categoryId" id="current_cate_id" value="${currentCateId }">
   </c:if>
   <c:if test="${!addOper}">
   		<form action="${baseUrl }/mount-rule/update" method="POST" id="mounuRuleForm" onsubmit="return validateCallback(this, dialogReloadNavTab)">
  		   <input type="hidden" name="id" value="${mountRulePo.id }">
   </c:if>
 	   <input type="hidden" id="product_name_id1" name="productName">
 	   <input type="hidden" id="product_prefix_id1" name="productPrefix">
 	   <input type="hidden" id="product_suffix_id1" name="productSuffix">
 	   <input type="hidden" id="product_search_tag1" name="productSearchTags">
 	   <input type="hidden" id="brdList1" name="brandCodeList">
 	   <input type="hidden" id="catList1" name="categoryCodeList">
   </form>
  
   <div class="pageFormContent" layoutH="58"> 
     <div class="m_l">
     	<table>
     		<tr height="35px">
     			<td width="55px;">商品名称:</td>
     			<td><input type="text" id="product_name_id" value="${mountRulePo.productName }"></td>
     		</tr>
     		<tr height="35px">
     			<td width="55px;">商品前缀:</td>
     			<td><input type="text" id="product_prefix_id" value="${mountRulePo.productPrefix }"></td>
     		</tr>
     		<tr height="35px">
     			<td width="55px;">商品后缀:</td>
     			<td><input type="text" id="product_suffix_id" value="${mountRulePo.productSuffix }"></td>
     		</tr>
     		<tr height="35px">
     			<td width="55px;">关键词:</td>
     			<td><input type="text" id="product_search_tag" name="productSearchTags" value="${mountRulePo.productSearchTags }"></td>
     		</tr>
     		<tr>
     			<td colspan="2" align="right"><input type="button" onclick="addBrandTr()"  value="添加品牌"/></td>
     		</tr>
     		<tr height="35px">
     			<td width="55px;">品牌:</td>
     			<td>
     				<input type="hidden" id="brdList_size_id" value="${mountRulePo.brandListSize }">
     				<table id="brand_tb">
     					<tbody>
     					<c:if test="${mountRulePo.brandList != null }">
     						<c:forEach var="brd" items="${mountRulePo.brandList }"  varStatus="brdStatus">
     							<tr height="24px" id="brand_tr_${brdStatus.index }">
     								<td colspan="2">
     									<input type="text" id="brandName_${brdStatus.index }"  value="${brd.brandName }" readonly="readonly">
     									<input type="hidden" id="brandCode_${brdStatus.index }" name="brandName_${brdStatus.index}" value="${brd.brandCode }">
     								</td>
     								<td width="30px" align="right"><a href="javascript:;" title="删除当前行的品牌数据" class="dlt_a" onclick="deleteBrandTr('${brdStatus.index}')">删除</a></td>
     							</tr>
     						</c:forEach>
     					</c:if>
     					<c:if test="${mountRulePo.brandList == null }">
     						<tr height="24px" id="brand_tr_0">
   								<td>
   									<input type="text" id="brandName_0" name="productModel.brandName" readonly="readonly">
   									<input type="hidden" id="brandCode_0" name="productModel.brandCode">
   								</td>
   								<td><a title="查找品牌" class="btnLook" href="${baseUrl}/dwz/selectback" lookupGroup="productModel">查找品牌</a></td>
   								<td width="30px" align="right"><a href="javascript:;" class="dlt_a" onclick="deleteBrandTr('0')">删除</a></td>
   							</tr>
						</c:if>
						</tbody>
     				</table>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" align="right"><input type="button" onclick="addCateTr()"  value="添加分类"/></td>
     		</tr>
     		<tr height="35px">
     			<td width="55px;">分类:</td>
     			<td>
     				<input type="hidden" id="catList_size_id" value="${mountRulePo.cateListSize }">
     				<table id="category_tb">
     					<tbody>
     					<c:if test="${mountRulePo.cateList != null }">
     						<c:forEach var="cat" items="${mountRulePo.cateList }"  varStatus="catStatus">
     							<tr id="cate_tr_${catStatus.index }">
     								<td colspan="2">
     									<input type="text" id="cateName_${catStatus.index }"  value="${cat.categoryName }"  readonly="readonly">
     									<input type="hidden" id="cateId_${catStatus.index }" name="cateName_${catStatus.index }" value="${cat.categoryCode }">
     								</td>
     								<td width="30px" align="right"><a href="javascript:;" class="dlt_a" onclick="deleteCateTr('${catStatus.index }')">删除</a></td>
     							</tr>
     						</c:forEach>
     					</c:if>
     					<c:if test="${mountRulePo.cateList == null }">
     						<tr id="cate_tr_0">
   								<td>
   									<input type="text" id="cateName_0" name="categoryData.categoryName"  readonly="readonly">
   									<input type="hidden" id="cateId_0" name="categoryData.categoryId">
   								</td>
   								<td><a title="查找分类" class="btnLook" href="${baseUrl }/base-category/tree.do" lookupGroup="categoryData">查询分类</a>
   								<td width="30px" align="right"><a href="javascript:;" class="dlt_a" onclick="deleteCateTr('0')">删除</a></td>
   							</tr>
						</c:if>
						</tbody>
     				</table>
     			</td>
     		</tr>
     	</table>
     </div>
     <div class="m_r">
     	<table>
     	    <tr height="35px">
     			<td> <input type="button" id="returnRuleBtn" onclick="toBack()" value="返回" /></td>
     		</tr>
     		<tr height="35px">
     			<td><input type="button" id="addRuleBtn" onclick="saveRule()" value="保 存" /></td>
     		</tr>
     		<tr height="35px">
     			<td>
     			 <c:if test="${addOper }">
     			     <input type="button" id="delRuleBtn" onclick="javascript:;" value="删 除" />
			     </c:if>
			     <c:if test="${!addOper}">
			     	 <input type="button" id="delRuleBtn" onclick="javascript:$('#mounuRuleFormDel').submit();" value="删 除" />
			     </c:if>
     			</td>
     		</tr>
     		<!-- 
     		<tr height="35px">
     			<td>
     				<input type="button" id="clsRuleBtn" onclick="javascript:$('#mounuRuleFormCls').submit();" value="取 消" />
     			</td>
     		</tr>
     		 -->
     	</table>
     </div>
     <div class="m_cl"></div>
     <form action="${baseUrl }/mount-rule/delete" method="POST" id="mounuRuleFormDel" onsubmit="return validateCallback(this, dialogReloadNavTab)">
   		    <input type="hidden" name="categoryId" value="${currentCateId }">
   		    <input type="hidden" name="id" value="${mountRulePo.id }">
   	 </form>
   	 <form action="${baseUrl }/sale-category/main" method="POST" id="mounuRuleFormCls" onsubmit="return validateCallback(this, dialogReloadNavTab)">
   		    <input type="hidden" name="categoryId" value="${currentCateId }">
   	 </form>
 </div>


