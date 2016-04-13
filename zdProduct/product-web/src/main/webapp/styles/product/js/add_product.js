// JavaScript Document   

	function showPop(index){
		//document.getElementById('colorPop').style.display='block';
		$("#index").val(index+"");
		$('#colorPop').show();
	}
	function closePop(){
		document.getElementById('colorPop').style.display='none';
	}
	
	function showPopsize(index){
		//document.getElementById('colorPop').style.display='block';
		$("#index").val(index+"");
		$('#rulepop').show();
	}
	function closePopSize(){
		document.getElementById('rulepop').style.display='none';
	}
	

	function showTab(tab,n,m) {
		var temp;
		for(var i=1;i<=m;i++){
			var w = document.getElementById(tab+i).className;
			if(w=="ruleoff"){
				temp=i;
			}
		}
		for(var i=1;i<=m;i++){
			if(i==n){
				var className = document.getElementById(tab+i).className; 
		 		document.getElementById(tab+i).className="ruleoff";
				document.getElementById(tab+temp).className=className;
				document.getElementById(tab+"Content"+i).style.display="block";
			}else{
				document.getElementById(tab+i).className="ruleon";
				document.getElementById(tab+"Content"+i).style.display="none";
			}
		}
	}
	
	function getColor(){ 
		var color = $('#pop_color input[type="radio"]:checked').val();
		var index = $("#index").val();
		$('#colorPop').hide();
		var skuKey = $("#img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = color+":"+str[1];
 
		if(color!=undefined && color!=''){
			$("#color"+index).val(color);
			$("#color"+index).parent().next().find('input').val(color);
			$("#img"+index).html(skuKey1);
			
			var fileNames = $(".fileNames"+index);
			for(var i=0;i<fileNames.length;i++){
				if(fileNames[i].value!=undefined && fileNames[i].value!=''){
					var nameValue = fileNames[i].value.split("#-#");
					fileNames[i].value=color+str[1]+"#-#"+nameValue[1]+"#-#"+nameValue[2];
				}
			}
		} 
	}
	function getSize(){
		var size = $('#sizePop input[type="radio"]:checked').val();
		var index = $("#index").val();
		$('#rulepop').hide();
		var skuKey = $("#img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = str[0]+":"+size;
 
		if(size!=undefined && size!=''){
			$("#size"+index).val(size);
			$("#size"+index).parent().next().find('input').val(size);
			$("#img"+index).html(skuKey1);
			
			var fileNames = $(".fileNames"+index);
			for(var i=0;i<fileNames.length;i++){
				if(fileNames[i].value!=undefined && fileNames[i].value!=''){
					var nameValue = fileNames[i].value.split("#-#");
					fileNames[i].value=str[0]+size+"#-#"+nameValue[1]+"#-#"+nameValue[2];
				}
			}
		} 
	}
	
	function colorChange(index){
		var color = $("#diaocolor"+index).val();
		var skuKey = $("#img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = color+":"+str[1];
		
		$("#img"+index).html(skuKey1);
		var fileNames = $(".fileNames"+index);
		for(var i=0;i<fileNames.length;i++){
			if(fileNames[i].value!=undefined && fileNames[i].value!=''){
				var nameValue = fileNames[i].value.split("#-#");
				fileNames[i].value=color+str[1]+"#-#"+nameValue[1]+"#-#"+nameValue[2];
			}
		}
	}

	function sizeChange(index){
		var size = $("#diaosize"+index).val();
		var skuKey = $("#img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = str[0]+":"+size;
		
		$("#img"+index).html(skuKey1);
		var fileNames = $(".fileNames"+index);
		for(var i=0;i<fileNames.length;i++){
			if(fileNames[i].value!=undefined && fileNames[i].value!=''){
				var nameValue = fileNames[i].value.split("#-#");
				fileNames[i].value=str[0]+size+"#-#"+nameValue[1]+"#-#"+nameValue[2];
			}
		}
	}
	
$(document).ready(function() {
	
    $('#check_on').click(function(){
		$('#check_on').removeClass("check_off");
		$('#check_on').addClass("check_orange");
		$('#check_off').removeClass("check_orange");
		$('#check_off').addClass("check_off");
		$("#pc_txt").show(500);
		$("#phone_txt").hide();
	});
	$('#check_off').click(function(){
		$('#check_on').removeClass("check_orange");
		$('#check_on').addClass("check_off");
		$('#check_off').removeClass("check_off");
		$('#check_off').addClass("check_orange");
		$("#pc_txt").hide();
		$("#phone_txt").show(500);
	});
	
	$('#check_reserve').click(function(){
		$('#check_reserve').removeClass("check_off");
		$('#check_reserve').addClass("check_orange");
		$('#check_custom').removeClass("check_orange");
		$('#check_custom').addClass("check_off");
		$("#reserve_txt").show(500);
		$("#custom_txt").hide();
	});
	$('#check_custom').click(function(){
		$('#check_reserve').removeClass("check_orange");
		$('#check_reserve').addClass("check_off");
		$('#check_custom').removeClass("check_off");
		$('#check_custom').addClass("check_orange");
		$("#reserve_txt").hide();
		$("#custom_txt").show(500);
	});
});


var imgIndex = 0;
var h = 0;

function deleteSku(element){
	$("#aa"+element).remove();
	$("#bb"+element).remove();
	/*var skuMainSku = $(".skuMainSku");
	imgIndex = skuMainSku.length*5;*/
}


function addOther(){
	    var str = '';
	    var imgstr = '';
	    var marketPrice = $(".skuMarketPrice").eq(0).val();
	    var tshPrice = $(".skuTshPrice").eq(0).val();
	    var stock = $(".skuStock").eq(0).val();
	    if(marketPrice==undefined){
	    	marketPrice = '';
	    }
	    if(tshPrice==undefined){
	    	tshPrice = '';
	    }
	    if(stock==undefined){
	    	stock = '0';
	    }
	    
		$('#div_table').show();
	    str	+='<tr id="aa'+h+'" >'
		+ '		<td><input name="skuColorValue" onfocus="showPop('+imgIndex+');"  value="其他颜色" id="color'+imgIndex+'"  size="10"  type="text"></td>'
		+ '		<td><input size="10" type="text" class="colorAlias" id="diaocolor'+imgIndex+'" onblur="colorChange('+imgIndex+');" name="skuColorValueAlias"></td>'
		+ '		<td><input name="skuSpecValue"  onfocus="showPopsize('+imgIndex+');"  value="其他尺码" id="size'+imgIndex+'"   size="10"  type="text"></td>'
		+ '		<td><input size="10" class="specAlias" onblur="sizeChange('+imgIndex+');" id="diaosize'+imgIndex+'" name="skuSpecValueAlias" type="text"></td>'
		//+ '		<td><input name="skuCostPrice"  class="costPrice skuCostPrice" size="10" type="text" value="'+costPrice+'"/></td>'
		+ '		<td><input name="skuMarketPrice"   class="marketPrice skuMarketPrice" size="10" type="text" value="'+marketPrice+'"/></td>'
		+ '		<td><input name="skuTshPrice" class="skuTshPrice" size="10" type="text" value="'+tshPrice+'"/></td>'
		+ '		<td><input name="skuVirtualStock" class="skuStock" size="10" type="text" value="'+stock+'"/></td>'
		+ '		<td><select name="skuMainSku" id="'+imgIndex+'" class="skuMainSku mainSku" onchange="mainChange(this);"><option value="0">否</option><option value="1">是</option></select></td>'
		+ '		<td><input type="text" name="skuSupplierSkuCode" size="10"></td>'
		+ '		<td><input value="删除" onclick="deleteSku('+h+');" type="button"></td>'
		+ '</tr>';
		$('#div_table table').append(str);
		imgstr	
		    +='<div class="boximg" id="bb'+h+'">'
			+'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' rtg'+imgIndex+'" value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' rtg'+imgIndex+'" value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' rtg'+imgIndex+'" value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' rtg'+imgIndex+'" value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' rtg'+imgIndex+'" value=""/>'
			+'<div class="a">'
			+'<div class="boxcolor" id="m'+imgIndex+'"><span id="img'+imgIndex+'">其他颜色:其他尺码</span></div>'
            +'<div>'
			+'	<dt class="boximg_div" id="localImag'+(imgIndex+0)+'"><img class="preview'+(imgIndex+0)+'" style="width:100%;height:100%;diplay:none" /></dt>'
			+'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview(this,'+imgIndex+');" accept="image/*"/></dt>'
            +' </div>'
            +'<div>'
            +'	<dt class="boximg_div" id="localImag'+(imgIndex+1)+'"><img class="preview'+(imgIndex+1)+'" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+1)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+1)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div" id="localImag'+(imgIndex+2)+'"><img class="preview'+(imgIndex+2)+'" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+2)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+2)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div" id="localImag'+(imgIndex+3)+'"><img class="preview'+(imgIndex+3)+'" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+3)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+3)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div" id="localImag'+(imgIndex+4)+'"  ><img class="preview'+(imgIndex+4)+'" style="width:100%;height:100%;" style="diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+4)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+4)+',this);" />'
	 		+'</dt></div>'
            
            +'</div>'
            +'</div>';
		imgIndex=imgIndex+5;
		h=h+1;
		$('#box_img').append(imgstr);
}

//下面用于缩略图 图片上传预览功能 thumbnailImg
function setImagePreview3(element) {  
	var imgObjPreview=$(".thumbnailImg"); 
    //var docObj=document.getElementById("doc");
    if(element.files && element.files[0]){  
        //火狐下，直接设img属性  
    	imgObjPreview[0].style.display = 'block';   
//    	imgObjPreview[0].style.width = $(".thumbnailDiv").eq(0).width();//"197px";   
//        imgObjPreview[0].style.height = $(".thumbnailDiv").eq(0).height();//"108px";
        //imgObjPreview.src = docObj.files[0].getAsDataURL();  
          
        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
        imgObjPreview[0].src = window.URL.createObjectURL(element.files[0]); 
        $("#thumbnailFileName").val(element.files[0].name);
    }else{  
        //IE下，使用滤镜  
    	element.select();  
        var imgSrc = document.selection.createRange().text;  
        var localImagId = document.getElementById(".thumbnailImg");  
        //必须设置初始大小  
        localImagId.style.width = "150px";  
        localImagId.style.height = "150px";  
        //图片异常的捕捉，防止用户修改后缀来伪造图片  
        try{  
            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
        }catch(e){  
            alert("您上传的图片格式不正确，请重新选择!");  
            return false;  
        }
    }  
    return true;  
}

var json = new Function("return" + $("#jsonValue").html())();
var sb="<li class='textintentdiv'>商品属性</li>";

var requiredAttrSum = 0;
for(var i=0;i<json.length;i++){
	sb = sb +'<input type="hidden" name="attrbuteName" value="'
	 +json[i].attrName+'"/><input type="hidden" name="attrbuteCode" value="'
	 +json[i].attrCode+'"/><input type="hidden" name="attrbuteType" value="'
	 +json[i].attrDesign+'"/><input type="hidden" name="attrbuteRequired" value="'
	 +json[i].required+'"/>';
	 
	if(json[i].attrDesign==1){
		var arrStr = json[i].attrValue.split(",");
		if(json[i].required==1){
			requiredAttrSum++;
			sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>*"+json[i].attrName+"：</li><li class='code_buss_input'>";
			
			for(var j=0;j<arrStr.length;j++){
				 sb = sb+'<label><input type="checkbox" class="attrValue requiredAttr'+requiredAttrSum+'" name="attrbuteValue'+i+'" value="'
						 +arrStr[j]+'"/>'+arrStr[j]+'</label>';
			 }
			sb = sb+"</li></ul></div></li><br/>";
		}else{
			sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>"+json[i].attrName+"：</li><li class='code_buss_input'>";
			
			for(var j=0;j<arrStr.length;j++){
				 sb = sb+'<label><input type="checkbox" class="attrValue" name="attrbuteValue'+i+'" value="'
						 +arrStr[j]+'"/>'+arrStr[j]+'</label>';
			 }
			sb = sb+"</li></ul></div></li><br/>";
		}
		
	}else{
		if(json[i].required==1){
			requiredAttrSum++;
			if(json[i].attrValue!=null){
				sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>*"+json[i].attrName+"：</li><li class='code_buss_input'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
				json[i].attrValue+"'/></li></ul></div></li>";
			}else{
				sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>*"+json[i].attrName+"：</li><li class='code_buss_input'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
			}
			
		}else{
			if(json[i].attrValue!=null){
				sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>"+json[i].attrName+"：</li><li class='code_buss_input'><input type='text' name='attrbuteValue"+i+"' value='"+
				json[i].attrValue+"'/></li></ul></div></li>";
			}else{
				sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text'>"+json[i].attrName+"：</li><li class='code_buss_input'><input type='text' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
			}
			
		}
		
	}
}
$("#attrUl").html(sb);
$("#requiredAttrSum").val(requiredAttrSum);


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

//sku的价格计算
function skuCountPrice(element){
	//验证 是否为数字
	var re = /^\d+(\.\d+)?$/;
	if(!re.test(element.value)){
		alert("请输入有效数字！");
		element.value="";
	}else{
		//加价率
//		var _priceRate = $("#priceRate").val()/100;
//		
//		var _costPrice = $(".skuCostPrice");
//		var _marketPrice = $(".skuMarketPrice");
//		var _tshPrice = $(".skuTshPrice");
//		var _tb = $(".skuTb");
//		var tshPrice;
//		var tb;
//		for(var i=0;i<_costPrice.length;i++){
//			if(_costPrice[i].value.length!=0 && _marketPrice[i].value.length!=0){
//				tshPrice = parseFloat(_costPrice[i].value)*_priceRate+parseFloat(_costPrice[i].value);
//				var intTshPrice = tshPrice-parseInt(tshPrice)>0?parseInt(tshPrice)+1:parseInt(tshPrice);
//				tb = parseFloat(_marketPrice[i].value)-intTshPrice;
//				tb = tb-parseInt(tb)>0?parseInt(tb)+1:parseInt(tb);
//				_tshPrice[i].value=toDecimal(intTshPrice);
//				_tb[i].value=tb;
//			}
//		}
	}
}

function mainChange(element){
	var i = 0;
	if(element.value==1){
		$(".mainSku").each(function(){
			if($(this).val()==1){
				i++;
			}
		})
		if(i>=2){
			element.innerHTML='<option value="1">是</option><option value="0" selected="selected">否</option>';
			alert('只能有一个主SKU');
		}
	}
	
};


function checkProduct(form, callback){
	//验证 是否为数字
	var re = /^\d+(\.\d+)?$/;
	
	//商品名称
	var productName = $(".productName").val();
	if(productName==''){
		alert("商品名称不能为空");
		$(".productName").focus() 
		return false;
	}else if(productName!=''){
		if(productName.length>28){
			alert("商品名称长度不能超过28个字符");
			$(".productName").focus()
			return false;
		}
	}
	//商品短名称
	var productShortName = $(".productShortName").val();
	if(productShortName!=null){
		if(productShortName.length>50){
			alert("商品短名称长度不能超过50个字符");
			$(".productShortName").focus() 
			return false;
		}
	}
	//商品简介
	var productDesc = $(".productDesc").val();
	if(productDesc!=null){
		if(productDesc.length>999){
			alert("商品简介长度不能超过999个字符");
			$(".productDesc").focus() 
			return false;
		}
	}
	//商品前缀
	var productPrefix = $(".productPrefix").val();
	if(productPrefix!=null){
		if(productPrefix.length>50){
			alert("商品前缀长度不能超过50个字符");
			$(".productPrefix").focus() 
			return false;
		}
	}
	//商品后缀
	var productSuffix = $(".productSuffix").val();
	if(productSuffix!=null){
		if(productPrefix.length>50){
			alert("商品后缀长度不能超过50个字符");
			$(".productSuffix").focus() 
			return false;
		}
	}
	//商品关键字
	var productKeyword = $(".productKeyword").val();
	if(productKeyword!=null){
		if(productKeyword.length>200){
			alert("商品关键字长度不能超过200个字符");
			$(".productKeyword").focus() 
			return false;
		}
	}
	//品牌名称
	var brandName = $(".brandName").val();
	if(brandName==''){
		alert("品牌不能为空");
		$(".brandName").focus();
		return false;
	}
	
	//验证扩展属性
	//商品单品重量
	var productWeight = $(".productWeight").val();
	if(productWeight!=null && productWeight!=''){
		if(!re.test(productWeight)){
			alert("商品单品毛重量  要为数字型");
			$(".productWeight").focus();
			return false;
		}
	}
	//商品（包装）宽
	var productWidth = $(".productWidth").val();
	if(productWidth!=null && productWidth!=''){
		if(!re.test(productWidth)){
			alert("商品（包装）宽  要为数字型");
			$(".productWidth").focus();
			return false;
		}
	}
	//商品（包装）长
	var productLong = $(".productLong").val();
	if(productLong!=null && productLong!=''){
		if(!re.test(productLong)){
			alert("商品（包装）长  要为数字型");
			$(".productLong").focus();
			return false;
		}
	}
	//商品产地
	var productProducer = $(".productProducer").val();
	if(productProducer!=null && productProducer!=''){
		if(productProducer.length>50){
			alert("商品产地 长度不能超过50个字符");
			$(".productProducer").focus();
			return false;
		}
	}
	//商品（包装）高
	var productHeight = $(".productHeight").val();
	if(productHeight!=null && productHeight!=''){
		if(!re.test(productHeight)){
			alert("商品（包装）高  要为数字型");
			$(".productHeight").focus();
			return false;
		}
	}
	//商品密度 
	var productDensity = $(".productDensity").val();
	if(productDensity!=null && productDensity!=''){
		if(!re.test(productDensity)){
			alert("商品 密度  要为数字型");
			$(".productDensity").focus();
			return false;
		}
	}
	//加价率
//	var priceRate = $("#priceRate").val();
//	if(priceRate==""){
//		alert("默认加价率不能为空！");
//		return false;
//	}else if(!re.test(Number(priceRate))){
//		alert("默认加价率 不是有效数字！");
//		return false;
//	}
	
	//验证商品属性（弹性域）
	var requiredAttrSum = $("#requiredAttrSum").val();
	for(var m=1;m<=requiredAttrSum;m++){
		var attrFlag = false;
		
		for(var k=0;k<$(".requiredAttr"+m).length;k++){
			if(document.getElementsByClassName("requiredAttr"+m)[k].type=='checkbox'){
				if(document.getElementsByClassName("requiredAttr"+m)[k].checked){
					attrFlag = true;
				}
			}else{
				if(document.getElementsByClassName("requiredAttr"+m)[k].value!=''){
					attrFlag = true;
				}
			}
			
		}
		if(!attrFlag){
			alert("商品属性必填项不能为空值");
			return false;
		}
	}
	//验证SKU属性
	var marketPrice = $(".marketPrice");
	var skuStock = $(".skuStock");
	var mainSku = $(".mainSku");
	var colorAlias = $(".colorAlias");
	var specAlias = $(".specAlias");
	
	
	var sum = 0;
	if(marketPrice!=null && marketPrice.length>0){
		for(var i=0;i<marketPrice.length;i++){
			if(marketPrice[i].value=='' || marketPrice[i].value==''){
				alert("市场价 不能为空");
				return false;
			}else{
				if (!re.test(marketPrice[i].value) || !re.test(marketPrice[i].value)){
					alert("市场价 不是有效的数字");
					return false;
				}
			}
			if(skuStock[i].value==''){
				alert("库存数量 不能为空");
				return false;
			}else if(!re.test(Number(skuStock[i].value)) || Number(skuStock[i].value)<0){
				alert("库存 不是有效的数字");
				return false;
			}
			if(Number(mainSku[i].value)==1){
				sum++;
			}
			if(colorAlias[i].value=='' || specAlias[i].value==''){
				alert("吊牌别名不能为空");
				return false;
			}
		}
		if(sum==0){
			alert("至少有一个主SKU");
			return false;
		}
	}else{
		alert("请添加SKU,至少有一个SKU");
		return false;
	}
	
	//验证图片$(".mainSku");
	for(var n=0;n<$(".mainSku").length;n++){
		if($(".mainSku").eq(n).val()==1){
			if($(".rtg"+$(".mainSku").eq(n).attr("id")).eq(0).val()==''){
				alert("主SKU第一张图必须有图");
				return false;
			}
		}
	}
	//验证 描述字段
	if($("#appProductInfo").val()==''){
		if($("#pcProductInfo").val()!=''){
			if($("#pcProductInfo").val().length<=20000){
				$("#appProductInfo").val($("#pcProductInfo").val());
			}else{
				alert("商品段描述长度不能大于20000个字符");
				return false;
			}
		}else{
			alert("商品段描述不能为空");
			return false;
		}
	}else{
		if($("#appProductInfo").val().length<=20000){
			if($("#pcProductInfo").val()==''){
				$("#pcProductInfo").val($("#appProductInfo").val());
			}
		}else{
			alert("商品段描述长度不能大于20000个字符");
			return false;
		}
	}
	
	var $form = $(form),
	  $iframe = $('#callbackframe');
	  if (!$form.validationEngine('validate')) {
	    return false;
	  }
	  if ($iframe.size() == 0) {
	    $iframe = $('<iframe id=\'callbackframe\' name=\'callbackframe\' src=\'about:blank\' style=\'display:none\'></iframe>').appendTo('body');
	  }
	  if (!form.ajax) {
	    $form.append('<input type="hidden" name="ajax" value="1" />');
	  }
	  form.target = 'callbackframe';
	  _iframeResponse($iframe[0], callback || DWZ.ajaxDone);
}

//图片删除
function deleteImg(index,element){
	var yy = Math.floor((index)/5)*5;
	var nameIndex = (index+1)%5;
	var level = 1;
    if(index+1>5){
    	if(nameIndex!=0){
    		level = (index+1)%5;
    	}else{
    		level = 5;
    	}
    }else{
    	level = index+1;
    }
    
    $(element).prev().outerHTML += '';
	$(element).prev().val("");
    
    var fileNames=$(".fileNames"+yy);
    var imgObjPreview=$(".preview"+index);
    fileNames[level-1].value="";
	imgObjPreview[0].src = "";
}
