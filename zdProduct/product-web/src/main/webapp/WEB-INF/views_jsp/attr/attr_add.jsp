<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">
	function subForm()
	{
		$("#addAttrFormId").submit();
	}
</script>

<div class="pageContent">
<form method="POST" action="${contextPath}/attr/add" id="addAttrFormId" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>属性名称：</label>
            <input type="text" name="attrName" class="validate[required,maxSize[20]] required" size="20" maxlength="20"/>
        </p>
        <p>
            <label>属性别名：</label>
            <input type="text" name="attrEname" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>属性说明：</label>
            <input type="text" name="attrInfo" class="validate[required,maxSize[200]] required" size="20" maxlength="200"/>
        </p>
        <p>
            <label>排序号：</label>
            <input type="text" name="attrNum" class="validate[required,custom[integer],maxSize[11]] required" size="20"  maxlength="11"/>
        </p>
        <p>
            <label>是否预设项：</label>
            <select class="combox" name="attrDesign" width="360" >
		      <option value="1">是</option>
		      <option value="2">否</option>
            </select>
        </p>
        <p>
            <label>是否必填项：</label>
            <select class="combox" name="required" width="360" >
		      <option value="1">是</option>
		      <option value="0">否</option>
            </select>
        </p>
    </div>
            
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button class="subBtn" type="button" onclick="subForm();">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>