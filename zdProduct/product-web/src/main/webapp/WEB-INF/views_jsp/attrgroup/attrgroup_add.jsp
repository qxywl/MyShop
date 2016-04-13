<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<div class="pageContent">
<form method="POST" action="${contextPath}/attrgroup/add" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>属性组名称：</label>
            <input type="text" name="groupName" class="validate[required,maxSize[50]] required" size="20" maxlength="50"/>
        </p>
        <p>
            <label>状&nbsp;&nbsp;态：</label>
            <select class="combox" name="groupStatus" style="width:360px"  >
		      <option value="1">启用</option>
		      <option value="2">禁用</option>
            </select>
        </p>
       
    </div>
            
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>