<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<div class="pageContent">
<form method="post" action="${contextPath}/info/shelves/upload_excel" enctype="multipart/form-data"  onsubmit="return iframeCallback(this, dialogReloadNavTab)" >
    <div class="pageFormContent" layouth="56">
        <a  class="fontColor" href ="${contextPath}/styles/image/shelves.xls" target="_blank">下载模板</a>  
        <input type="file" id="file"  name="file" value="选择文件"  class="fontColor"/>&nbsp;   
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