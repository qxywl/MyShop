<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/uploadify/css/uploadify.css"
	rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">
	
	$(function() {
		$("#brandStatus_id").val("${brandModel.brandStatus}");
		$("#storeCertificationType_id").val("${brandModel.storeCertificationType}");
		$("#brandCountry_id").val("${brandModel.brandCountry}");
		
	})
	
	var op = {okName:$.regional.alertMsg.butMsg.ok, okCall:reloadNav};

    var callback= function(json){
		if(json.statusCode == DWZ.statusCode.error) {
			if(json.message && alertMsg) alertMsg.error(json.message);
		} else if (json.statusCode == DWZ.statusCode.timeout) {
			if(alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
			else DWZ.loadLogin();
		} else if (json.statusCode == DWZ.statusCode.forbidden) {
		    if(alertMsg) alertMsg.error(json.message || DWZ.msg("forbidden"));
		    else DWZ.loadLogin();// 重新登录
		} else {
		    if(json.message && alertMsg)  alertMsg.correct(json.message,op);;
		};
    }
    
    var myCallback = function(json){
    	alert(json.message);
    	if(json.statusCode == DWZ.statusCode.ok) {
    		reloadNav();
		}
    }
  
    function reloadNav(){
    	$.pdialog.closeCurrent(); 
    	navTab.reload('${contextPath}/brand/list');
    }
  
  
//下面用于多图片上传预览功能
  function setImagePreviews(avalue,id) {

      var imgObjPreview = document.getElementById("logoShow"); 
      //火狐下，直接设img属性
      imgObjPreview.style.display = 'block';
     /*  imgObjPreview.style.width = '100px';
      imgObjPreview.style.height = '75px'; */
      imgObjPreview.src = window.URL.createObjectURL(avalue.files[0]);
     
      if (avalue.files && avalue.files[0]) {
      	
      }
      else {
          //IE下，使用滤镜
          avalue.select();
          var imgSrc = document.selection.createRange().text;
         
          //必须设置初始大小
         /*  imgObjPreview.style.width = "100px";
          imgObjPreview.style.height = "75px"; */
          //图片异常的捕捉，防止用户修改后缀来伪造图片
          try {
          	imgObjPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
          	imgObjPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
          }
          catch (e) {
              alert("您上传的图片格式不正确，请重新选择!");
              return false;
          }
          imgObjPreview.style.display = 'none';
          document.selection.empty();
      }
      imgResize(100,100,imgObjPreview);
      return true;
  }
  
  
  function imgResize(maxWidth, maxHeight, imgObj) {
      var img = new Image();
      img.src = imgObj.src;

      if (img.width > 0 && img.height > 0) {
          if (img.width / img.height >= maxWidth / maxHeight) {
              if (img.width > maxWidth) {
                  imgObj.width = maxWidth;
                  imgObj.height = (img.height * maxWidth) / img.width;
              } else {
                  imgObj.width = img.width;
                  imgObj.height = img.height;
              }
          } else {
              if (img.height > maxHeight) {
                  imgObj.height = maxHeight;
                  imgObj.width = (img.width * maxHeight) / img.height;
              } else {
                  imgObj.width = img.width;
                  imgObj.height = img.height;
              }
          }
      }
      else {
          imgObj.width = maxWidth;
          imgObj.height = maxHeight;
      }
  }
</script>


<div class="pageContent">
<form method="POST" action="${contextPath}/brand/update" enctype="multipart/form-data" onsubmit="return iframeCallback(this, myCallback);">
    <div class="pageFormContent" layoutH="58">
        <div class="unit">
            <label>品牌编号：</label>
            <input type="hidden" name="brandId" value="${brandModel.brandId }">
            <input type="text" name="brandCode" value="${brandModel.brandCode }" class="validate[required,maxSize[32]] required" readonly="readonly" size="20" maxlength="32"/>
        </div>
        <div class="unit">
            <label>品牌名称：</label>
            <input type="hidden" name="brandOldName" value="${brandModel.brandName }"/>
            <input type="text" name="brandName" value="${brandModel.brandName }" class="validate[required,maxSize[32]] required"  size="20" maxlength="32"/>
        </div>
        <div class="unit">
            <label>品牌别名：</label>
            <input type="text" name="brandEname" value="${brandModel.brandEname }" class="validate[maxSize[32]]" size="20" maxlength="32"/>
        </div>
        <div class="unit">
            <label>品牌说明：</label>
            <input type="text" name="brandInfo" value="${brandModel.brandInfo }" class="validate[maxSize[32]]" size="20" maxlength="32"/>
        </div>
        <div class="unit">
            <label>品牌国家：</label>
            <select id="brandCountry_id" class="combox" name="brandCountry"  >
            <c:if test="${countryList!=null}">
	            <c:forEach var="country" items="${countryList}">
	               <option value="${country.countryAbbr}">${country.countryName}</option>
	            </c:forEach>
            </c:if>
           </select>
        </div>
        <div class="unit">
            <label>品牌logo：</label>
            <%-- <input id="brandLogoId" readonly="readonly" name="brandLogo" value="${brandModel.brandLogo }" class="validate[maxSize[255]]" size="20" maxlength="32"/> --%>
            <input type="file" name="file"  value="图片上传"  style="width:150px;" onchange="javascript:setImagePreviews(this);" accept="image/*"/>            
        </div>
        
        <div class="unit">
            <label>&nbsp;</label>
            <div style="margin-left:130px;">
              <a id="logoAshowid" href="${brandModel.brandLogo }" target="_blank" >
               <img id="logoShow"   onload="imgResize(100,100,this)" src="${brandModel.brandLogo }"  />
             </a> 
            </div>
        </div>
        
       <%--  <div class="unit">
         <label>&nbsp;</label>
         <ul class="toolBar">
           <li><a class="edit" target="dialog" rel="123" mask="true" width="630" height="330" href="${contextPath}/brand/to_image_upload"><span>上传图片</span></a></li>
          </ul>
        </div> --%>
        
        <div class="unit">
            <label>状态：</label>
            <select id="brandStatus_id" class="combox" name="brandStatus"  width="360" >
		      <option value="1">启用</option>
		      <option value="2">禁用</option>
            </select>
        </div>
        <!-- 
        <div class="unit">
            <label>旗舰店铺编号：</label>
            <input type="text" name="flagshipStoreCode" value="${brandModel.flagshipStoreCode }" class="validate[maxSize[32]]" size="20" maxlength="32"/>
        </div>
        <div class="unit">
            <label>旗舰店认证状态：</label>
            <select id="storeCertificationType_id" class="combox" name="storeCertificationType" value="${brandModel.storeCertificationType }"  >
		      <option value="1">已认证</option>
		      <option value="2">未认证</option>
            </select>
        </div>
         -->
        <div class="unit">
            <label>品牌故事：</label>
            <input type="text" name="brandStory" value="${brandModel.brandStory }" class="validate[maxSize[32]]" size="20" maxlength="32"/>
        </div>
   </div>

    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
    
</form>
</div>