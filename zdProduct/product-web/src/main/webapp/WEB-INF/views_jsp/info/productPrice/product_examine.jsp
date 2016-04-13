<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
<cas:paginationForm
	action="${contextPath}/info/productPrice/examinelist" page="${page}">
	<input type="hidden" name="productSkuCode"
		value="${productPriceListModel.productSkuCode}" />
	<input type="hidden" name="status"
		value="${productPriceListModel.status}" />
	<input type="hidden" name="startTime1" value="${startTime1}" />
	<input type="hidden" name="endTime1" value="${endTime1}" />
	<input type="hidden" name="startTime2" value="${startTime2}" />
	<input type="hidden" name="endTime2" value="${endTime2}" />
	<input id="cc1" name="categoryCode" type="hidden"
		value="${productPriceListModel.categoryCode}" />
	<input type="hidden" name="shopName"
		value="${productPriceListModel.shopName  }" />
	<input type="hidden" name="productName"
		value="${productPriceListModel.productName  }" />
	<input type="hidden" name="brandName"
		value="${productPriceListModel.brandName  }" />
	<input type="hidden" name="catName"
		value="${productPriceListModel.catName  }" />
	<input type="hidden" name="createUserName"
		value="${productPriceListModel.createUserName }" />
	<input type="hidden" name="updateUserName"
		value="${productPriceListModel.updateUserName  }" />
</cas:paginationForm>
<form method="post"
	action="${contextPath}/info/productPrice/examinelist"
	onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<table width="100%">
				<tr>
					<td width="80px" align="right">商品名称：</td>
					<td><input type="text" name="productName" size="15"
						value="${productPriceListModel.productName }" class="textInput"></td>
					<td width="80px" align="right">店铺名称：</td>
					<td><input type="text" name="shopName" size="15"
						value="${productPriceListModel.shopName }" class="textInput"></td>
					<td width="80px" align="right">SKU编号：</td>
					<td><input type="text" name="productSkuCode" size="15"
						value="${productPriceListModel.productSkuCode }" class="textInput"></td>
					<td width="80px" align="right">创建时间：</td>
					<td><input type="text" class="date" name="startTime1"
						size="15" dateFmt="yyyy-MM-dd" value="${startTime1}"
						readonly="true" /> - <input type="text" class="date"
						name="endTime1" size="15" dateFmt="yyyy-MM-dd"
						value="${endTime1 }" readonly="true" /> <%-- <input type="text" class="date" name="createTimes" dateFmt="yyyy-MM-dd" value="${productPriceListModel.createTimes }" readonly="true" value=""/> --%>
					</td>

					<td width="80px" align="right"><label>一级分类：</label></td>
					<td><select class="" name="categoryCode" id="category"
						style="width: 100px;">
							<option value="">选择分类</option>
							<c:if test="${listCategory!=null }">
								<c:forEach items="${listCategory }" var="category">
									<option value="${category.categoryCode }">${category.categoryName }</option>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<td width="80px" align="right">品牌名称：</td>
					<td><input type="text" name="brandName" size="15"
						value="${productPriceListModel.brandName }" class="textInput"></td>
					<td width="80px" align="right">审核状态：</td>
					<td><select name="status">
							<option value="">请选择</option>
							<option value="1"
								<c:if test="${productPriceListModel.status==1}">selected='selected'</c:if>>待审核</option>
							<option value="2"
								<c:if test="${productPriceListModel.status==2}">selected='selected'</c:if>>审核通过</option>
							<option value="3"
								<c:if test="${productPriceListModel.status==3}">selected='selected'</c:if>>待同步</option>
							<option value="4"
								<c:if test="${productPriceListModel.status==4}">selected='selected'</c:if>>审核通过,同步成功</option>
							<option value="8"
								<c:if test="${productPriceListModel.status==8}">selected='selected'</c:if>>审核拒绝</option>
							<option value="5"
								<c:if test="${productPriceListModel.status==5}">selected='selected'</c:if>>审核拒绝,同步成功</option>
							<option value="9"
								<c:if test="${productPriceListModel.status==9}">selected='selected'</c:if>>处理失败</option>
							<option value="6"
								<c:if test="${productPriceListModel.status==6}">selected='selected'</c:if>>系统处理</option>

					</select></td>
					<td width="80px" align="right">审核人：</td>
					<td><input type="text" name="updateUserName" size="15"
						value="${productPriceListModel.updateUserName }" class="textInput"></td>
					<td width="80px" align="right">创建人：</td>
					<td><input type="text" name="createUserName" size="15"
						value="${productPriceListModel.createUserName }" class="textInput"></td>
					<td width="80px" align="right">审核时间：</td>
					<td><input type="text" class="date" name="startTime2"
						size="15" dateFmt="yyyy-MM-dd" value="${startTime2}"
						readonly="true" /> - <input type="text" class="date"
						name="endTime2" size="15" dateFmt="yyyy-MM-dd" value="${endTime2}"
						readonly="true" /> <%-- <input type="text" class="date" name="createTimes" dateFmt="yyyy-MM-dd" value="${productPriceListModel.createTimes }" readonly="true" value=""/> --%>
					</td>
					<td><div class="button">
							<div class="buttonContent">
								<button type="submit">搜索</button>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
</form>

<div class="pageContent j-resizeGrid">
    <div class="panelBar">
        <ul class="toolBar">
			<!-- mask="true" width="1230" height="530" navTab-->
			<li><a class="delete" target="selectedTodo" rel="ids"
				href="${contextPath}/info/productPrice/update/" title="确认要审核?"><span>审核通过</span></a></li>
			<li><a class="delete" target="selectedTodo" id="noExamine"
				rel="ids" href="${contextPath}/info/productPrice/updateNo/"
				title="确认要审核?" onclick="examineClick(this);"><span>审核拒绝</span></a></li>
			<!--<li><a class="edit" href="#" onclick="yulan1(this);"><span>预览</span></a></li>-->
			<li><a class="icon"
				href="${contextPath}/info/productPrice/examine/export"
				id="exportExamine" target="dwzExport" targetType="dialog"
				onclick="setPage2(this);" title="要导出这些记录吗?(最多导出10000条记录)"><span>导出审核记录</span></a></li>
			<!-- <li><a class="icon" href="javascript:saveList();"><span>一键保存</span></a></li>
			<li><a class="icon" href="javascript:cancelList();"><span>一键取消</span></a></li> -->
		</ul>
	</div>
	 <table class="table"  layoutH="170" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th width="120">SKU编号</th>
				<th width="110">商品名称</th>
				<th width="100">类目名称</th>
				<th width="100">品牌名称</th>
				<th width="70">售价</th>
				<th width="70">市场价</th>
				<th width="70">调价前售价</th>
				<th width="70">调价前市场价</th>
				<th width="70">价格浮动率</th>
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
				<c:forEach var="productPriceListModel" items="${productPriceList}" varStatus="status" >
					<tr
						target="slt_uid" rel="${productPriceListModel.productPriceList}"
						style="text-align: center;height:30px;">
						<c:if test="${productPriceListModel.status==1 }">
							<td width="22"><input name="ids"
								value="${productPriceListModel.productPriceList}"
								type="checkbox" class="checkboxCtrl"></td>
						</c:if>
						<c:if test="${productPriceListModel.status!=1 }">
							<td width="22"><input type="checkbox" disabled=“disabled”
								class="checkboxCtrl" value="${productPriceListModel.productPriceList}" /></td>
						</c:if>
						<c:if
							test="${productPriceListModel.costPrice > productPriceListModel.oldCostPrice }">
							<td width="100"><a style="color: red;" target="navTab"
								rel="2"
								href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
							</td>
						</c:if>
						<c:if test="${productPriceListModel.oldCostPrice==null }">
							<td width="100"><a target="navTab" rel="2"
								href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
							</td>
						</c:if>
						<c:if
							test="${productPriceListModel.costPrice <= productPriceListModel.oldCostPrice }">
							<td width="100"><a style="color: green;" target="navTab"
								rel="2"
								href="${contextPath }/info/product/to_info/${productPriceListModel.productSkuCode}">${productPriceListModel.productSkuCode}</a>
							</td>
						</c:if>
						<td>${productPriceListModel.productName }</td>
						<td>${productPriceListModel.catName }</td>
						<td>${productPriceListModel.brandName }<input id="productSkuCode${productPriceListModel.productPriceList }"  value="${productPriceListModel.productSkuCode}" type="hidden"/></td>
						 
						<td width="70"
							id="tshPrice${productPriceListModel.productPriceList }">${productPriceListModel.tshPrice}
							</td>
						<td width="70" 
							id="marketPrice${productPriceListModel.productPriceList }">${productPriceListModel.marketPrice}
							</td> 
						<td width="70" id="oldTshPrice${productPriceListModel.productPriceList }" >${productPriceListModel.oldTshPrice}</td>
						<td width="70">${productPriceListModel.oldMarketPrice}</td>
						<td width="70" id="ratio${productPriceListModel.productPriceList }"><c:if
								test="${!empty  productPriceListModel.oldTshPrice}">
								<fmt:formatNumber type="number"
									value="${productPriceListModel.tshPrice/productPriceListModel.oldTshPrice}"
									maxFractionDigits="2" />
							</c:if></td>
						<td width="100">${productPriceListModel.createUserName}</td>
						<td width="100">${productPriceListModel.createTimes}</td>
						<td width="100">${productPriceListModel.updateUserName}</td>
						<td width="100">${productPriceListModel.updateTimes}</td>
						<td width="100">${productPriceListModel.reason}</td>
						<td width="100"><c:if
								test="${productPriceListModel.status==1}">待审核</c:if> <c:if
								test="${productPriceListModel.status==2}">审核通过</c:if> <c:if
								test="${productPriceListModel.status==3}">待同步</c:if> <c:if
								test="${productPriceListModel.status==4}">审核通过,同步成功</c:if> <c:if
								test="${productPriceListModel.status==8}">审核拒绝</c:if> <c:if
								test="${productPriceListModel.status==5}">审核拒绝,同步成功</c:if> <c:if
								test="${productPriceListModel.status==9}">处理失败</c:if> <c:if
								test="${productPriceListModel.status==6}">系统处理</c:if>
					   </td>
						<input type="hidden" class="oldRatio${productPriceListModel.productPriceList }" value="<c:if
								test="${!empty  productPriceListModel.oldTshPrice}">
								<fmt:formatNumber type="number"
									value="${productPriceListModel.tshPrice/productPriceListModel.oldTshPrice}"
									maxFractionDigits="2" />
							</c:if>" />
						<input type="hidden" class="productPriceList" value="${productPriceListModel.productPriceList }"/>
						<input type="hidden" class="productSkuCode" value="${productPriceListModel.productSkuCode }"/>
						<input type="hidden" class="oldCost${productPriceListModel.productPriceList }" value="${productPriceListModel.costPrice}" />
						<input type="hidden" class="oldTsh${productPriceListModel.productPriceList }" value="${productPriceListModel.tshPrice}" />
						<input type="hidden" class="oldTb${productPriceListModel.productPriceList }" value="${productPriceListModel.tb}" />
						<input type="hidden" class="oldMarket${productPriceListModel.productPriceList }" value="${productPriceListModel.marketPrice}" />
						<input type="hidden" class="oldPriceRate${productPriceListModel.productPriceList }" value="${productPriceListModel.priceRate}" />
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	  <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>
<script>
	
	function saveList(){
		var listIds = $(".productPriceList");
		var codes = $(".productSkuCode");
		
		for(var i=0;i<listIds.length;i++){
			save(listIds[i].value,codes[i].value);
		}
	}
	
	function cancelList(){
		var listIds = $(".productPriceList");
		
		for(var i=0;i<listIds.length;i++){
			cancel(listIds[i].value);
		}
	}

	function save(id,skuCode) {//costPrice   tshPrice tb  marketPrice
		var flag = false;
		var data = 'productPriceList=' + id+"&productSkuCode="+skuCode+"&oldPriceRate="+$(".oldPriceRate"+id).val();
		var classCost = $(".costPrice"+id).val();
		var classTsh = $(".tshPrice"+id).val();
		var classMarket = $(".marketPrice"+id).val();
		var classTb = $(".tb"+id).val();
		var classPriceRate = $(".priceRate"+id).val();
		
		var costPrice;
		var tshPrice;
		var marketPrice;
		var tb;
		var priceRate;
		var ratio;
		
		if(classCost == undefined){
			var idCode = $("#costPrice"+id).html();
			var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
			data = data +'&costPrice='+aa;
			costPrice = Number(aa);
		}else{
			data = data +'&costPrice='+classCost;
			costPrice = Number(classCost);
			flag = true;
		}
		
		if(classTsh == undefined){
			var idCode = $("#tshPrice"+id).html();
			var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
			data = data +'&tshPrice='+aa;
			tshPrice = Number(aa);
		}else{
			data = data +'&tshPrice='+classTsh;
			tshPrice = Number(classTsh);
		}
		
		if(classMarket == undefined){
			var idCode = $("#marketPrice"+id).html();
			var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
			data = data +'&marketPrice='+aa;
			marketPrice = Number(aa);
		}else{
			data = data +'&marketPrice='+classMarket;
			marketPrice = Number(classMarket);
			flag = true;
		}
		
		if(classTb == undefined){
			var idCode = $("#tb"+id).html();
			var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
			data = data +'&tb='+aa;
			tb = Number(aa);
		}else{
			data = data +'&tb='+classTb;
			tb = Number(classTb);
		}
		
		if(classPriceRate == undefined){
			var idCode = $("#priceRate"+id).html();
			var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
			data = data +'&priceRate='+aa;
			priceRate = Number(aa);
		}else{
			data = data +'&priceRate='+classPriceRate;
			priceRate = Number(classPriceRate);
			flag = true;
			
		}
		
		var idRatio = $("#ratio"+id).html();
		var aaRatio = idRatio.substring(idRatio.indexOf("<div>")+5,idRatio.indexOf("</div>"));
		ratio = aaRatio;
		
		//alert(costPrice+":"+marketPrice+":"+tshPrice+":"+tb+":"+ratio+":"+priceRate);
		if (tb <= 0) {
			alert("销售价不能大于或等于市场价,请确认");
		} else {
			if(flag){
				$.ajax({
					type : 'post',
					dataType : 'text',
					url : '${contextPath}/info/productPrice/updatePrice',
					data : data,
					success : function(msg) {
						if(msg==''){
							$("#priceRate"+id).html("<div>"+priceRate+"</div>");
							$("#costPrice"+id).html("<div>"+costPrice+"</div>");
							$("#marketPrice"+id).html("<div>"+marketPrice+"</div>");
							$("#tshPrice"+id).html("<div>"+tshPrice+"</div>");
							$("#tb"+id).html("<div>"+tb+"</div>");
							$("#ratio"+id).html("<div>"+ratio+"</div>");
							
							$(".oldPriceRate"+id).val(priceRate);
							$(".oldCost"+id).val(costPrice);
							$(".oldTsh"+id).val(tshPrice);
							$(".oldTb"+id).val(tb);
							$(".oldMarket"+id).val(marketPrice);
							$(".oldRatio"+id).val(ratio);
							alert("修改成功");
						}else{
							alert(msg);
						}
					}
				});
			}
		}
		//$(".aHref").attr("href","${contextPath}/info/productPrice/updatePrice?"+data+"&productSkuCode="+skuCode);
	}

	function cancel(id){
		$("#priceRate"+id).html("<div>"+$(".oldPriceRate"+id).val()+"</div>");
		$("#costPrice"+id).html("<div>"+$(".oldCost"+id).val()+"</div>");
		$("#tshPrice"+id).html("<div>"+$(".oldTsh"+id).val()+"</div>");
		$("#tb"+id).html("<div>"+$(".oldTb"+id).val()+"</div>");
		$("#marketPrice"+id).html("<div>"+$(".oldMarket"+id).val()+"</div>");
		$("#ratio"+id).html("<div>"+$(".oldRatio"+id).val()+"</div>");
	}
	
	function changePrice(element,id){
		var re = /^\d+(\.\d+)?$/;
		if(!re.test(Number(element.value)) || element.value<=0){
			alert("请输入有效数字！");
			element.value="";
		}else{
			
			var costPrice;
			var tshPrice;
			var marketPrice;
			var tb;
			var priceRate;
			var ratio;
			
			var classCost = $(".costPrice"+id).val();
			var classMarket = $(".marketPrice"+id).val();
			var classPriceRate = $(".priceRate"+id).val();
			var classTb = $(".tb"+id).val();
			var classTsh = $(".tshPrice"+id).val();
			
			if(classCost == undefined){
				var idCode = $("#costPrice"+id).html();
				var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
				costPrice = Number(aa);
			}else{
				costPrice = Number(classCost);
			}
			if(classMarket == undefined){
				var idCode = $("#marketPrice"+id).html();
				var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
				marketPrice = Number(aa);
			}else{
				marketPrice = Number(classMarket);
			}
			
			if(classPriceRate == undefined){
				var idCode = $("#priceRate"+id).html();
				var aa = idCode.substring(idCode.indexOf("<div>")+5,idCode.indexOf("</div>"));
				priceRate = Number(aa);
			}else{
				priceRate = Number(classPriceRate);
			}
			
				//加价率
				var _priceRate = priceRate/100;
				
						
			tshPrice = parseFloat(costPrice) * _priceRate
					+ parseFloat(costPrice);
			
			var intTshPrice = tshPrice - parseInt(tshPrice) > 0 ? parseInt(tshPrice) + 1
					: parseInt(tshPrice);
			tb = parseFloat(marketPrice) - intTshPrice;
			tb = tb - parseInt(tb) > 0 ? parseInt(tb) + 1 : parseInt(tb);
			
			
				if (classTb == undefined) {
					$("#tb" + id).html("<div>" + tb + "</div>");
				} else {
					$(".tb" + id).val(tb);
				}

				if (classTsh == undefined) {
					$("#tshPrice" + id).html(
							"<div>" + intTshPrice + "</div>");
				} else {
					$(".tshPrice" + id).val(intTshPrice);
				}
			
			//计算浮动比率
			var idRatio = $("#ratio"+id).html();
			var aaRatio = idRatio.substring(idRatio.indexOf("<div>")+5,idRatio.indexOf("</div>"));
			
			var idOldTshPrice = $("#oldTshPrice"+id).html();
			var aaOldTshPrice = idOldTshPrice.substring(idOldTshPrice.indexOf("<div>")+5,idOldTshPrice.indexOf("</div>"));
			if(aaRatio!=''){
				
				$("#ratio"+id).html("<div>"+toDecimal(intTshPrice/aaOldTshPrice)+"</div>");
			}
		}
	}

	function toDecimal(x) {  
	    var f = parseFloat(x);  
	    if (isNaN(f)) {  
	        return;  
	    }  
	    f = Math.round(x*100)/100;  
	    return f;  
	}  
	
	function change(emet, listId) {

		var value = emet.innerHTML;
		var aa = value.substring(value.indexOf("<div>") + 5, value
				.indexOf("</div>"));
		var ab = value.split('input');

		if (ab.length > 1) {
			return false;
		}
		emet.innerHTML = '<input type="text" style="width:95%;" class="'
				+ emet.id + '" onkeyUp="changePrice(this,' + listId
				+ ');" value="' + aa + '">'
	}

	/* function dbltable(element, priceRate) {
		if (element.getAttribute("target") == 'slt_uid') {
			$.pdialog.open("${contextPath}/info/productPrice/to_updatePrice/"
					+ element.getAttribute("rel") + "?priceRate=" + priceRate,
					"pdialogid", "编辑价格", {
						mask : false,
						width : 500,
						height : 300
					});
		}
	} */

	function examineClick(element) {
		var str = prompt("审核不通过的原因", "");
		if (str != null && str != "") {
			var sta = encodeURIComponent(str);
			var src = "${contextPath}/info/productPrice/updateNo?reason=" + sta;
			$("#noExamine").attr("href", src);
		}
	}
	$(document).ready(function() {
		var cc1 = $("#cc1").val();
		$("#category").val(cc1);
	});

	function yulan1(element) {
		var str = "";
		$("input[name='ids']:checkbox").each(function() {
			if ($(this).attr("checked")) {
				str = $(this).val();
			}
		})
		if (str == "") {
			alert("请选择");
		} else {
			window.open("${contextPath }/info/productPrice/to_look/" + str);
		}
	}

	function setPage2(element) {

		var str = prompt(
				"请根据总条数设置导出第几页,输入规则：1、输入大于最大页数,则导出最后一页  2、输入不是有效数字或空,则导出第一页(每页最多10000条)：",
				"");
		if (str != null && str != "") {
			var re = /^\d+(\.\d+)?$/;
			if (!re.test(str)) {
				str = "1";
			}
			var sta = encodeURIComponent(str);
			var src = "${contextPath}/info/productPrice/examine/export?page="
					+ sta + "&exportType=" + str1;
			$("#exportExamine").attr("href", src);
		} else {
			var src = "${contextPath}/info/productPrice/examine/export?page=1&exportType="
					+ str1;
			$("#exportExamine").attr("href", src);
		}
	}
</script>