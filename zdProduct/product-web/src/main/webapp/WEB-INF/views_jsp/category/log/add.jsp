<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<div class="pageContent">
<form method="POST" action="${contextPath}/category/log/add" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
        <p>
            <label>基础分类ID</label>
            <input type="text" name="categoryId" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>基础分类名称</label>
            <input type="text" name="categoryName" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>基础分类编码</label>
            <input type="text" name="categoryCode" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>父分类ID</label>
            <input type="text" name="parentId" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>父分类编码</label>
            <input type="text" name="parentCode" class="validate[required,maxSize[32]] required" size="20" maxlength="32"/>
        </p>
        <p>
            <label>操作时间</label>
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