<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript" src="${contextPath }/styles/image/imagePreview.js" ></script>
<div class="pageContent" style="width=300px;">
<form method="POST" action="${contextPath}/sale-category/update" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    	<p>
            <label>运营分类原名称：</label>
            <input type="text" value="${curPo.categoryName }" readonly="readonly"/>
        </p>
        <c:if test="${not empty curPo.imageUrl }">
	        <p>
	            <label>运营分类原图片：</label>
	            <input  readonly="readonly" type="text" value="${curPo.imageUrl }">
	        </p>
	        
	        <div  class="unit">
	            <label>&nbsp;</label>
	            <div style="margin-left:130px;">
	              <a href="${curPo.imageUrl }"  target="_blank" >
	               <img style="height:100px;with:75px;"  src="${curPo.imageUrl }"  />
	             </a> 
	            </div>
	        </div>
	    </c:if>
        <c:if test="${isFirstLevel }">
        	 <p>
	            <label>颜色原名称：</label>
	            <input type="text" value="${curPo.colorName }" readonly="readonly"/>
	        </p>
	        <p>
	            <label>尺码原名称：</label>
	            <input type="text" value="${curPo.sizeName }" readonly="readonly"/>
	        </p>
        </c:if>
        
        <p>
            <label>运营分类新名称：</label>
            <!-- class="validate[required,maxSize[32]] required" -->
            <input type="text" name="categoryName" size="20" maxlength="32" class="validate[required]"/><span style="color: red">*</span>
        </p>
        <p>
        	<label>排序值</label>
        	<input type="text" name="sortNumber" class="validate[custom[integer]]" value="${curPo.sortNumber}"/>
        </p>
        <p style="height:40px">
            <label>运营分类新图片：</label>
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
        	<a target="dialog" style="color:blue;" rel="123" mask="true" width="630" height="330" href="${contextPath}/sale-category/to_image_upload"><span>上传图片</span></a>
        </p> --%>
        
        <c:if test="${isFirstLevel }">
        	 <p>
	            <label>颜色新名称：</label>
	            <input type="text" name="colorName" size="20" maxlength="32"/>
	        </p>
	        <p>
	            <label>尺码新名称：</label>
	            <input type="text" name="sizeName" size="20" maxlength="32"/>
	        </p>
        </c:if>
        
    </div>
    <input type="hidden" name="id" value="${curPo.id }">
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>