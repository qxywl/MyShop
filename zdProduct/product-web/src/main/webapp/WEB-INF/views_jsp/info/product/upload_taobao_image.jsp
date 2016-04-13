<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
.tip {
	padding: 10px;
}

.tip div {
	padding: 9px;
	font-size: 14px
}

.tip div span {
	color: green;
	font-size: 20px
}

.my-uploadify-button {
	background: none;
	border: none;
	text-shadow: none;
	border-radius: 0;
}

.uploadify:hover .my-uploadify-button {
	background: none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}

.title-div {
	padding: 10px;
	font-size: 14px
}

.uploader-div {
	float: left;
	margin-right: 50px;
}

.tip-div {
	
}
</style>
<div style=" height: 100%;overflow:auto;">
	<div style="text-align: left; ">

		<div class="tip">
			<div>上传商品图片</div>
		</div>
		<div class="uploader-div">
			<%-- <input type ="hidden" value="${pageContext.session.id}" id="sessionId"/> --%>
			<input id="testFileInput3" type="file" name="image"
				uploaderOption="{
			swf:'${contextPath}/styles/uploadify/scripts/uploadify.swf',
			uploader:'${contextPath}/info/product/taobaoImagesUpload',
			formData:{},
			buttonText:'请选择图片',
			fileSizeLimit:'2000KB',
			queueID:'fileQueue',
			fileTypeDesc:'*.jpg;',
			fileTypeExts:'*.jpg;',
			auto:false,
		    'fileObjName'   : 'file',
			multi:true,
			 'onUploadStart':upTaobaoImageStart,
			onUploadSuccess:upTaobaoImageSuccess,	     
            onQueueComplete:upTaobaoImageComplete,
            onUploadError:upTaobaoImageonError
		}" />
			<div id="fileQueue" class="fileQueue"></div>
			<input type="image"
				src="${contextPath}/styles/uploadify/img/upload.jpg"
				onclick="$('#testFileInput3').uploadify('upload', '*');" /> <input
				type="image" src="${contextPath}/styles/uploadify/img/cancel.jpg"
				onclick="$('#testFileInput3').uploadify('cancel', '*');" />
		</div>

		<div class="tip-div tip">
			<div>
				注意事项:<br> 
				<br> 1.上传图片格式   skuCode+"_"+序号（1,2,3,4,5）例如 040300185877001_1.jpg<br>
				<br> 2.上传的图片将会自动添加到对应产品SKU图片中<br> <br>
				     3.最多导入1000条数据<br>
			</div>
		</div>
	</div>
	<div class="pageContent" style="width:600px; height:400px; overflow:auto; text-align:left;  border:1px solid #000000;">
		   <div id="result" ></div>
	    </div>
</div>
<script type="text/javascript">
	var image_num = 0;
	var image_names;


	function upTaobaoImageSuccess(file, data, response) {
		var msgData = data.split(",");
		if (data != "false") { 
			if(msgData[1] == 1){
				$("#result").append(msgData[0]+' 成功</br>'); 
			}
			if(msgData[1] == 2){
				$("#result").append(msgData[0]+' 失败</br>'); 
			}
		}else{
			alert("上传异常");
		}
	}

	function upTaobaoImageComplete(file) {

		//alert("所有图片已经上传完毕，成功上传" + image_num + "张图片");
		if (image_num > 0) {
			alertMsg.correct("所有图片已经上传完毕，成功上传" + image_num + "张图片");
			image_num = 0;
		}
	}

	function upTaobaoImageStart(file) {
		var param = {};
		$("#testFileInput3").uploadify("settings", "formData", param);
	}

	function upTaobaoImageonError(file) {
		//不提示任何上传错误
	}
</script>

