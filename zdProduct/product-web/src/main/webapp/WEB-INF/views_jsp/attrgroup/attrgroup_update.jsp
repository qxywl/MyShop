<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>



<script type="text/javascript">
	$(function() 
	{
		$("#attrGroupStatus_id").val("${attrGroupModel.groupStatus}");
	})
</script>
<div class="pageContent">
<form method="POST" action="${contextPath}/attrgroup/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>属性组编号：</label>
            <input type="hidden" name="groupId" value="${attrGroupModel.groupId }">
            <input type="text" disabled="disabled" name="groupCode" value="${attrGroupModel.groupCode }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>属性组名称：</label>
            <input type="text" name="groupName" value="${attrGroupModel.groupName }" class="validate[required,maxSize[50]] required" size="20" maxlength="50"/>
        </p>
        <p>
            <label>状态：</label>
            <select id="attrGroupStatus_id" class="combox" name="groupStatus"  width="360" >
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