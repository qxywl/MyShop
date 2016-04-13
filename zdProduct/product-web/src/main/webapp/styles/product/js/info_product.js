// JavaScript Document   
	
	function showCategory(){
		$('#category').show();
	}

	function closeCategory(){
		document.getElementById('category').style.display='none';
	}
	
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
		var skuKey = $(".img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = color+":"+str[1];
		//验证SKU唯一
//		var boxColor = $(".boxcolor");
//		var flag = false;
//		for(var i=0;i<boxColor.length;i++){
//			if($(".img"+index).parent().attr("id")!=boxColor[i].id){
//				var skuKey2 = boxColor.eq(i).find("span").html();
//				if(skuKey1==skuKey2){
//					
//					flag = true;
//				}
//			}
//		}
//		if(flag){
//			alert("销售属性不能重复");
//		}else{
		if(color!=undefined && color!=''){
			$("#color"+index).val(color);
			$("#color"+index).parent().next().find('input').val(color);
			$(".img"+index).html(skuKey1);
			
			var fileNames = $(".change"+index);
			for(var i=0;i<fileNames.length;i++){
				if(fileNames[i].value!=undefined && fileNames[i].value!=''){
					var nameValue = fileNames[i].value.split("#-#");
					fileNames[i].value=color+str[1]+"#-#"+nameValue[1]+"#-#"+nameValue[2];
				}
			}
		}
//		}
	}
	function getSize(){
		var size = $('#sizePop input[type="radio"]:checked').val();
		var index = $("#index").val();
		$('#rulepop').hide();
		var skuKey = $(".img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = str[0]+":"+size;
		
		//验证SKU唯一
//		var boxColor = $(".boxcolor");
//		var flag = false;
//		for(var i=0;i<boxColor.length;i++){
//			if($(".img"+$("#index").val()).parent().attr("id")!=boxColor[i].id){
//				var skuKey2 = boxColor.eq(i).find("span").html();
//				if(skuKey1==skuKey2){
//					flag = true;
//				}
//			}
//		}
//		if(flag){
//			alert("销售属性不能重复");
//		}else{
		if(size!=undefined && size!=''){
			$("#size"+index).val(size);
			$("#size"+index).parent().next().find('input').val(size);
			$(".img"+index).html(skuKey1);
			
			var fileNames = $(".change"+index);
			for(var i=0;i<fileNames.length;i++){
				if(fileNames[i].value!=undefined && fileNames[i].value!=''){
					var nameValue = fileNames[i].value.split("#-#");
					fileNames[i].value=str[0]+size+"#-#"+nameValue[1]+"#-#"+nameValue[2];
				}
			}
		}
//		}
	}
	
	function colorChange(index){
		var color = $("#diaocolor"+index).val();
		var skuKey = $(".img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = color+":"+str[1];
		
		$(".img"+index).html(skuKey1);
		var fileNames = $(".change"+index);
		for(var i=0;i<fileNames.length;i++){
			if(fileNames[i].value!=undefined && fileNames[i].value!=''){
				var nameValue = fileNames[i].value.split("#-#");
				fileNames[i].value=color+str[1]+"#-#"+nameValue[1]+"#-#"+nameValue[2];
			}
		}
	}

	function sizeChange(index){
		var size = $("#diaosize"+index).val();
		var skuKey = $(".img"+index).html();
		var str = skuKey.split(":");
		var skuKey1 = str[0]+":"+size;
		
		$(".img"+index).html(skuKey1);
		var fileNames = $(".change"+index);
		for(var i=0;i<fileNames.length;i++){
			if(fileNames[i].value!=undefined && fileNames[i].value!=''){
				var nameValue = fileNames[i].value.split("#-#");
				fileNames[i].value=str[0]+size+"#-#"+nameValue[1]+"#-#"+nameValue[2];
			}
		}
	}
	
$(document).ready(function() {
	$("#pcProductInfo").html($("#pcInfo").html());
	$("#appProductInfo").html($("#appInfo").html());
	
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
//	var skuMainSku = $(".skuMainSku");
//	imgIndex = skuMainSku.length*5;
}

function deleteSkua(element){
	$("#aa"+element.id).remove();
	$("#bb"+element.id).remove();
	var skuId = document.getElementById("skuId");
	if(skuId.value.length>0){
		skuId.value=skuId+","+element.id;
	}else{
		skuId.value=element.id;
	}
}

function addOther(){
	    var str = '';
	    var imgstr = '';
		$('#div_table').show();
	    str	+='<tr id="aa'+h+'" >'
	    + '		<td></td>'
		+ '		<td><input name="skuColorValue"  onfocus="showPop('+imgIndex+');"  value="其他颜色" id="color'+imgIndex+'"  size="10"  type="text"></td>'
		+ '		<td><input size="10" type="text" class="colorAlias" id="diaocolor'+imgIndex+'" onblur="colorChange('+imgIndex+');" name="skuColorValueAlias"></td>'
		+ '		<td><input name="skuSpecValue"  onfocus="showPopsize('+imgIndex+');" value="其他尺码" id="size'+imgIndex+'" size="10"  type="text"></td>'
		+ '		<td><input size="10" name="skuSpecValueAlias" class="specAlias" onblur="sizeChange('+imgIndex+');" id="diaosize'+imgIndex+'" type="text"></td>'
		+ '		<td><input name="skuMarketPrice"   class="marketPrice skuMarketPrice" size="10" type="text" /></td>'
		+ '		<td><input name="skuTshPrice" class="skuTshPrice"   size="10" type="text"/></td>'
		+ '		<td><input name="skuVirtualStock" class="skuStock" size="10" type="text" /></td>'
		+ '		<td><select name="skuMainSku" id="'+imgIndex+'" class="mainSku" onchange="mainChange(this);"><option value="0">否</option><option value="1">是</option></select></td>'
		+ '		<td><input type="text" name="skuSupplierSkuCode" size="10"></td>'
		+ '		<td><input value="删除" onclick="deleteSku('+h+');" type="button"></td>'
		+ '</tr>';
		$('#div_table table').append(str);
		imgstr	
		    +='<div class="boximg" id="bb'+h+'">'
			+'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' change'+imgIndex+' " value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' change'+imgIndex+' " value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' change'+imgIndex+' " value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' change'+imgIndex+' " value=""/>'
            +'<input type="hidden" name="fileNames" class="fileNames'+imgIndex+' change'+imgIndex+' " value=""/>'
			+'<div class="a">'
			+'<div class="boxcolor" id="m'+imgIndex+'"><span id="img'+imgIndex+'" class="img'+imgIndex+'">其他颜色:其他尺码</span></div>'
            +'<div>'
			+'	<dt class="boximg_div"><img class="preview'+(imgIndex+0)+' rtg'+imgIndex+'" src="" style="width:100%;height:100%;diplay:none" /></dt>'
			+'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview(this,'+imgIndex+');" accept="image/*"/></dt>'
            +' </div>'
            +'<div>'
            +'	<dt class="boximg_div"><img class="preview'+(imgIndex+1)+' rtg'+imgIndex+'" src="" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+1)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+1)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div"><img class="preview'+(imgIndex+2)+' rtg'+imgIndex+'" src="" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+2)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+2)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div"><img class="preview'+(imgIndex+3)+' rtg'+imgIndex+'" src="" style="width:100%;height:100%;diplay:none" /></dt>'
            +'<dt><input type="file" name="file" class="img'+imgIndex+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview(this,'+(imgIndex+3)+');" accept="image/*"/>'
            +'<input type="button" value="删除" style="margin-left:10px;" onclick="deleteImg('+(imgIndex+3)+',this);" />'
	 		+'</dt></div>'
            
            +'<div>'
            +'	<dt class="boximg_div"><img class="preview'+(imgIndex+4)+' rtg'+imgIndex+'" src="" style="width:100%;height:100%;diplay:none" /></dt>'
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
//		//加价率
//		var _priceRate = $("#priceRate").val()/100;
//		
//		var _costPrice = $(".skuCostPrice");
//		var _marketPrice = $(".skuMarketPrice");
//		var _tshPrice = $(".skuTshPrice");
//		var _tb = 0;
//		var tshPrice;
//		var tb;
//		for(var i=0;i<_costPrice.length;i++){
//			if(_costPrice[i].value.length!=0 && _marketPrice[i].value.length!=0){
//				tshPrice = parseFloat(_costPrice[i].value)*_priceRate+parseFloat(_costPrice[i].value);
//				var intTshPrice = tshPrice-parseInt(tshPrice)>0?parseInt(tshPrice)+1:parseInt(tshPrice);	 
//				_tshPrice[i].value=toDecimal(intTshPrice);
//				_tb[i].value=0;
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

if($("#imgJson").html()!='null'&&$("#imgJson").html().length>0){

	var imgJson = new Function("return" + $("#imgJson").html())();
	var imgIndexJSP=0;
	var h = 0;
	var imgHtml = "";
	var skuHtml = "";
	for(var i=0;i<imgJson.length;i++){
		
		imgHtml+='<div class="boximg" id="bb'+imgJson[i].productSkuId+'">'
		   +'<input type="hidden" name="fileNames" class="fileNames1  change'+imgJson[i].productSkuId+'" value=""/>'
		   +'<input type="hidden" name="fileNames" class="fileNames1  change'+imgJson[i].productSkuId+'" value=""/>'
		   +'<input type="hidden" name="fileNames" class="fileNames1  change'+imgJson[i].productSkuId+'" value=""/>'
		   +'<input type="hidden" name="fileNames" class="fileNames1  change'+imgJson[i].productSkuId+'" value=""/>'
		   +'<input type="hidden" name="fileNames" class="fileNames1  change'+imgJson[i].productSkuId+'" value=""/>'
		   +'<div class="a">'
		   +'<div class="boxcolor" id="m'+imgJson[i].productSkuId+'"><span class="color_spec img'+imgJson[i].productSkuId+'" id="imga'+imgIndexJSP+'">'+imgJson[i].colorValueAlias+':'+imgJson[i].specValueAlias+'</span></div>';
			var falg = false;
			if(imgJson[i].productImagerList.length>0){
				
				for(var j=0;j<5;j++){
					falg = false;
					var kk = 0;
					for(var x=0;x<imgJson[i].productImagerList.length;x++){
						if(j+1==imgJson[i].productImagerList[x].level){
							kk=x;
							falg = true;
						}
					}
					if(falg){
						imgHtml+='<div>'
					 		 +'<dt class="boximg_div"><img class="previewa'+(imgIndexJSP+j)+' rtg'+imgJson[i].productSkuId+'" src="'+imgJson[i].productImagerList[kk].image+'" style="width:100%;height:100%;" /></dt>'
					 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+j)+','+imgJson[i].productSkuId+');" accept="image/*"/>';
					 		if(j!=0){
					 			imgHtml+='<input type="button" value="删除" class="1" style="margin-left:10px;" onclick="deleteImage('+(imgIndexJSP+j)+','+imgJson[i].productSkuId+',this,\''+imgJson[i].productSkuCode+'\');" />';
					 		 }
					 		imgHtml+='</dt></div>';
					}else{
						imgHtml+='<div>'
					 		 +'<dt class="boximg_div"><img class="previewa'+(imgIndexJSP+j)+' rtg'+imgJson[i].productSkuId+'" src="" style="width:100%;height:100%;display:none;" /></dt>'
					 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+j)+','+imgJson[i].productSkuId+');" accept="image/*"/>';
					 		if(j!=0){
					 			imgHtml+='<input type="button" value="删除" class="2" style="margin-left:10px;" onclick="deleteImage('+(imgIndexJSP+j)+','+imgJson[i].productSkuId+',this,\''+imgJson[i].productSkuCode+'\');" />';
					 		 }
					 		imgHtml+='</dt></div>';
					}
				}
			}else{
				for(var y=0;y<5;y++){
					imgHtml+='<div>'
				 		 +'<dt class="boximg_div"><img class="previewa'+(imgIndexJSP+y)+'  rtg'+imgJson[i].productSkuId+'" src="" style="width:100%;height:100%;display:none;" /></dt>'
				 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:70%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+y)+','+imgJson[i].productSkuId+');" accept="image/*"/>';
				 		if(y!=0){
				 			imgHtml+='<input type="button" value="删除" class="2" style="margin-left:10px;" onclick="deleteImage('+(imgIndexJSP+y)+','+imgJson[i].productSkuId+',this,\''+imgJson[i].productSkuCode+'\');" />';
				 		 }
				 		imgHtml+='</dt></div>';
				}
			}
	/*for(var x=0;x<5-imgListLength;x++){
		
		imgHtml+='<div>'
			+'<input type="hidden" name="skuImageId" class="skuImageId" value=""/>'
		 +'<dt class="boximg_div"><img class="previewa'+(5-(5-imgJson[i].productImagerList.length)+x+imgIndexJSP)+'" style="display:none;" /></dt>'
		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview1(this,'+(5-(5-imgJson[i].productImagerList.length)+x+imgIndexJSP)+');" accept="image/*"/></dt>'
		 +'</div>';
	}*/
	imgHtml+='</div></div>';
	imgIndexJSP=imgIndexJSP+5;
	}
	$("#imgManager").html(imgHtml);
}

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
			alert("商品简介长度不能超过50个字符");
			$(".productPrefix").focus() 
			return false;
		}
	}
	//商品后缀
	var productSuffix = $(".productSuffix").val();
	if(productSuffix!=null){
		if(productPrefix.length>50){
			alert("商品简介长度不能超过50个字符");
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
			if($(".rtg"+$(".mainSku").eq(n).attr("id")).eq(0).attr("src")==''){
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



