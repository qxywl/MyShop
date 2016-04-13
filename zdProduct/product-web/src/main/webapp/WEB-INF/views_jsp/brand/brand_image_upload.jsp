<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<script type="text/javascript">
function uploadBack(data){
	
	if(data.statusCode == DWZ.statusCode.error) {
		if(data.message && alertMsg) alertMsg.error(data.message);
		return;
	}
	setParams(data);
}

function setParams(data){
	$.pdialog.closeCurrent(); 
    $('#logoShow').attr('src',data.message);
    $('#brandLogoId').val(data.message);
    $('#logoAshowid').attr('href',data.message);
    $('#logoShowDiv').css('display','block'); 
}
</script>

<div class="pageContent">
<form method="post" action="${contextPath}/brand/uploadImage" enctype="multipart/form-data" onsubmit="return iframeCallback(this, uploadBack)" >
    <div class="pageFormContent" layouth="56">
        <input type="file" id="file"  name="file" value="选择文件"  class="fontColor"/>&nbsp;   
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="submit">上传</button>        
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