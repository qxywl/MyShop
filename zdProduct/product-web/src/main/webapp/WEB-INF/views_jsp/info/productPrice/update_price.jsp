<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
	table tr{
		margin-top: 10px;
	}
</style>
<div class="pageContent">
	<form method="POST" action="${contextPath}/info/productPrice/updatePrice"
		class="required-validate pageForm"
		onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<div class="pageFormContent" layoutH="70">
		<input type="hidden" name="productPriceList" value="${productPriceListModel.productPriceList }" />
		<input type="hidden" name="productSkuCode" value="${productPriceListModel.productSkuCode }" />
		<table>
		<tr>
			<p>
				<label>加价率：</label>
				<input type="text" name="priceRate" onblur="changePrice(this);" class="validate[required,min[1],max[99],custom[number]] required priceRate" value="${productPriceListModel.priceRate }" />
			</p>
		</tr>
		<tr>
			<p>
				<label>供货价：</label>
				<input type="text" name="costPrice" onblur="changePrice(this);" class="validate[required,min[1],custom[number]] required costPrice" value="${productPriceListModel.costPrice }" />
			</p>
		</tr>
		<tr>
			<p>
				<label>市场价：</label>
				<input type="text" name="marketPrice" onblur="changePrice(this);" class="validate[required,min[1],custom[number]] required marketPrice" value="${productPriceListModel.marketPrice }" />
			</p>
		</tr>
		<tr>
			<p>
				<label>销售价：</label>
				<input type="text" name="tshPrice" onblur="changePrice(this);" value="${productPriceListModel.tshPrice }" class="tshPrice" readonly="readonly" />
			</p>
		</tr>
		<tr>
			<p>
				<label>特币：</label>
				<input type="text" name="tb" onblur="changePrice(this);" value="${productPriceListModel.tb }" class="tb" readonly="readonly"/>
			</p>
		</tr>
		</table>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
<script>
	function changePrice(element){
		if($(".priceRate").val()!=''){
			//加价率
			var _priceRate = $(".priceRate").val()/100;
			
			var _costPrice = $(".costPrice");
			var _marketPrice = $(".marketPrice");
			var _tshPrice = $(".tshPrice");
			var _tb = $(".tb");
			var tshPrice;
			var tb;
				if(_costPrice.val().length!=0 && _marketPrice.val().length!=0){
					tshPrice = parseFloat(_costPrice.val())*_priceRate+parseFloat(_costPrice.val());
					var intTshPrice = tshPrice-parseInt(tshPrice)>0?parseInt(tshPrice)+1:parseInt(tshPrice);
					tb = parseFloat(_marketPrice.val())-intTshPrice;
					tb = tb-parseInt(tb)>0?parseInt(tb)+1:parseInt(tb);
					if(tb<=0){
						alert("销售价不能大于市场价,请确认");
						element.value='';
					}else{
						_tshPrice.val(toDecimal(intTshPrice));
						_tb.val(tb);
					}
				}
		}else{
			alert("请先填写加价率");
		}
		
	}
	//保留两位小数   
	//功能：将浮点数四舍五入，取小数点后2位  
	function toDecimal(x) {  
	    var f = parseFloat(x);  
	    if (isNaN(f)) {  
	        return;  
	    }  
	    f = Math.round(x*100)/100;  
	    return f;  
	}  
</script>