<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
	  $("#btnDownload").click(function(){
		  window.location.href="${contextPath}/supplier/downTemplate"; 
	});
});
	
	
	function test(){
		 alert(123);
	}
	
	
function callback(data){
	  alert(data.message);
	  if(data.statusCode == 200){
		  $.pdialog.closeCurrent();
		  navTab.reload('${contextPath}/supplier/list');
	  }
	  return true;
}


</script>

<div class="pageContent">
<form method="post" action="${contextPath}/supplier/importSupplier" enctype="multipart/form-data" onsubmit="return iframeCallback(this, callback)" >
    <div class="pageFormContent" layouth="56">
        <input type="button" id="btnDownload" value="下载模板" class="fontColor"/> 
        <input type="file" id="file"  name="file" value="选择文件" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="fontColor"/>&nbsp;   
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="submit">开始导入</button>        
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