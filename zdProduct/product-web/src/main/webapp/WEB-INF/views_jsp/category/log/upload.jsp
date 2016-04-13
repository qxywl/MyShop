<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<h2 class="contentTitle"></h2>

<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<div class="pageContent" style="margin: 0 10px" layoutH="50">
	<div class="divider"></div>
	<input id="testFileInput" type="file" name="myFile" 
		uploaderOption="{
			swf:'styles/uploadify/scripts/uploadify.swf',
			uploader:'${contextPath}/category/log/import_excel',
			formData:{'userName':'xxx', 'qq':''},
			queueID:'fileQueue',
			buttonText:'添加文件',
			buttonClass:'my-uploadify-button',
			buttonImage:'styles/uploadify/img/add.jpg',
			fileTypeDesc:'*.xlsx;*xls;',
			fileTypeExts:'*.xlsx;*xls;',
			width:102,
			auto:false,
			'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#progress').html(totalBytesUploaded + ' bytes uploaded of ' + totalBytesTotal + ' bytes.');
           }
		}"
	/>
	<span id="progress"></span>
	<div id="fileQueue" class="fileQueue"></div>
	
	<input type="image" src="styles/uploadify/img/upload.jpg" onclick="$('#testFileInput').uploadify('upload', '*');"/>
	<input type="image" src="styles/uploadify/img/cancel.jpg" onclick="$('#testFileInput').uploadify('cancel', '*');"/>
	<div class="divider"></div>

</div>