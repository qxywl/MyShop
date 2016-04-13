// JavaScript Document   
	
//	alert($("#newPcInfo").html());
	
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
});

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
					 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+j)+','+imgJson[i].productSkuId+');" accept="image/*"/></dt>'
				    		 +'</div>';
					}else{
						imgHtml+='<div>'
					 		 +'<dt class="boximg_div"><img class="previewa'+(imgIndexJSP+j)+' rtg'+imgJson[i].productSkuId+'" src="" style="width:100%;height:100%;display:none;" /></dt>'
					 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+j)+','+imgJson[i].productSkuId+');" accept="image/*"/></dt>'
				    		 +'</div>';
					}
				}
			}else{
				for(var y=0;y<5;y++){
					imgHtml+='<div>'
				 		 +'<dt class="boximg_div"><img class="previewa'+(imgIndexJSP+y)+' rtg'+imgJson[i].productSkuId+'" src="" style="width:100%;height:100%;display:none;" /></dt>'
				 		 +'<dt><input type="file" name="file" class="imga'+imgIndexJSP+'" multiple="multiple" value="图片上传"  style="width:100%;padding-top:5px;" onchange="setImagePreview1(this,'+(imgIndexJSP+y)+','+imgJson[i].productSkuId+');" accept="image/*"/></dt>'
			    		 +'</div>';
				}
			}
	imgHtml+='</div></div>';
	imgIndexJSP=imgIndexJSP+5;
	}
	$("#imgManager").html(imgHtml);
}
