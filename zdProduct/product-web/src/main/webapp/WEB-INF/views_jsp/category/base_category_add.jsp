<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript" src="${contextPath }/styles/image/imagePreview.js" ></script>

<div class="pageContent">
<form method="POST" action="${contextPath}/base-category/add" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    	<p>&nbsp;</p>
        <p>
            <label>基础分类名称：</label>
            <input type="text" name="categoryName"  class="validate[required]"/><span style="color: red">*</span>
        </p>
        <p style="height:40px">
            <label>分类图片：</label>
           <!-- <input id="categoryLogoId" readonly="readonly" type="text" name="imageUrl"> -->
           <input type="file" name="imageFile" id="file" value="选择文件" onchange="setImagePreviews(this,'logoShow')"/>
        </p>
        <div id="logoShowDiv" class="unit" style="display:none;">
            <label>&nbsp;</label>
            <div style="margin-left:130px;">
               <img id="logoShow" style="height:100px;with:75px;"  src=""  />
            </div>
        </div>
        <%-- <p>
        	<label>&nbsp;</label>
        	<a target="dialog" style="color:blue;" rel="123" mask="true" width="630" height="330" href="${contextPath}/base-category/to_image_upload"><span>上传图片</span></a>
        </p> --%>
        <p>
            <label>颜色名称：</label>
            <input type="text" name="colorName" size="20" maxlength="32"/>
        </p>
        <p>
            <label>尺码名称：</label>
            <input type="text" name="sizeName" size="20" maxlength="32"/>
        </p>
    </div>
    <input type="hidden" name="parentId" value="${currentCateId }">
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>