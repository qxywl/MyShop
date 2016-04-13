<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>



<script type="text/javascript">
	
	$(function() {
		$("#brandStatus_id").val("${brandModel.brandStatus}");
		$("#storeCertificationType_id").val("${brandModel.storeCertificationType}");
		
	})
	
	
</script>

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

<div class="pageContent">
<form method="POST" action="${contextPath}/category/log/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    	<p>
            <label>基础分类ID</label>
            <input type="hidden" name="id" value="${baseCategoryOperateLog.id }" />
            <input type="text" name="categoryId" value="${baseCategoryOperateLog.categoryId }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>基础分类名称</label>
            <input type="text" name="categoryName" value="${baseCategoryOperateLog.categoryName }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>基础分类编码</label>
            <input type="text" name="categoryCode" value="${baseCategoryOperateLog.categoryCode }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>父分类ID</label>
            <input type="text" name="parentId" value="${baseCategoryOperateLog.parentId }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>父分类编码</label>
            <input type="text" name="parentCode" value="${baseCategoryOperateLog.parentCode }" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>操作时间</label>
        </p>
        
        <p>
            <label>状态：</label>
            <select id="brandStatus_id" class="combox" name="brandStatus"  width="360" >
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