<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>



<script type="text/javascript">
	
	function reloadParentDialog()
	{
		$.pdialog.reload("${contextPath}/attr/to_listAttrValue/${attrId}",{data:{}, dialogId:"attrValueDialog", callback:null});
		$.pdialog.closeCurrent();
	}
</script>
<div class="pageContent">
<form method="POST" action="${contextPath}/attr/updateattrvalue" class="required-validate pageForm" onsubmit="return validateCallback(this, reloadParentDialog);">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>属性值编号：</label>
            <input type="hidden" name="attrValueId" value="${attrValueModel.attrValueId }">
            <input type="text" disabled="disabled" name="attrValueCode" value="${attrValueModel.attrValueCode }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>属性值：</label>
            <input type="text" name="attrValue" value="${attrValueModel.attrValue}" class="validate[required,maxSize[20]] required" size="20" maxlength="20"/>
        </p>
        <p>
            <label>排序号：</label>
            <input type="text" name="attrValueNum" value="${attrValueModel.attrValueNum}" class="validate[required,custom[integer],maxSize[11]] required" size="20"  maxlength="11"/>
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