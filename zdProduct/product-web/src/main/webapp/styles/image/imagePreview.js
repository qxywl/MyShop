/**
 * 
 */

// 下面用于图片上传本地预览功能
function setImagePreviews(self, img) {
	var imgObjPreview = document.getElementById(img);

	if (self.files) {
		// 火狐下，直接设img属性
		imgObjPreview.style.display = 'block';
		// imgObjPreview.src = docObj.files[0].getAsDataURL();
		// 火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		var src = window.URL.createObjectURL(self.files.item(0));
		if(isImage(self.value)){
			imgObjPreview.src = src;
		}else{
			document.selection.empty();
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}

	} 
	else {
		// IE下，使用滤镜
		self.select();
		var imgSrc = document.selection.createRange().text;
		if(!isImage(self.value)){
			document.selection.empty();
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		
//		var localImagId = self;
		// 图片异常的捕捉，防止用户修改后缀来伪造图片
		try {
			imgObjPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObjPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		imgObjPreview.style.display = 'block';
//		document.selection.empty();
	}
	document.getElementById("logoShowDiv").style.display = 'block';
	return true;
}

//判断文件扩展名为图片
function isImage(fileName){
	var extList = ".gif.jpg.bmp.jpeg";
	var arr = fileName.split(".");
	var ext = arr[arr.length - 1];
	if(extList.indexOf(ext) == -1){
		return false;
	}else{
		return true;
	}
	
}