<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">



$(document).ready(function(){
	  $("#btnDownload").click(function(){
		  window.location.href="${contextPath}/resources/batch_category_mount_template.xlsx"; 
	});
});


function checkFiletype(self){
	var fileName = self.value;
	var extList = ".xls.xlsx";
	var arr = fileName.split(".");
	var ext = "." + arr[arr.length - 1]; //扩展名
	
	if(extList.indexOf(ext) == -1){
//		self.select();
	//	document.selection.empty();
		alertMsg.error("文件类型不正确，请上传Excel 2003 或2007");
		if(file.outerHTML) {
			file.outerHTML = file.outerHTML;
		} else { // FF(包括3.5)
			file.value = "";
		}
		return false;
	}else{
		return true;
	}
}



</script>
<div class="pageContent">
<form method="post" action="${contextPath}/sale-category/batchCategoryImport" enctype="multipart/form-data"  onsubmit="return iframeCallback(this, closeDialogReloadNavTab)" >
    <div class="pageFormContent" layouth="56">
    	<input id="btnDownload" type="button"   value="下载模板" class="fontColor"/> 
        <input type="file" id="file"  name="file" value="选择文件"  class="fontColor validate[required]" onchange="return checkFiletype(this);"
        	accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />&nbsp;<b >一次最多导入三千条数据</b>  
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="submit">导入</button>        
                    </div>
                </div>
            </li>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="button" class="close">关闭</button></div>
                </div>
            </li>
        </ul>
    </div>
</form>  
</div>
<script>

/**
 * 自动刷新当前活动的navTab，并关闭当前打开的dialog
 * @param json
 */
function closeDialogReloadNavTab(json){
	alert(json.message);
	if (json.statusCode == DWZ.statusCode.ok){
		$.pdialog.closeCurrent(); 
	}
}
</script>