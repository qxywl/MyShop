<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>



<script type="text/javascript">
	$(function() {
		$("#brandStatus_id").val("${brandModel.brandStatus}");
		$("#brandStatus_id").attr("disabled","disabled");
		$("#storeCertificationType_id").val("${brandModel.storeCertificationType}");
	})
</script>
<div class="pageContent">
<form method="POST" action="#" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
        <div class="unit">
         <label>品牌编号：</label>
            <input type="hidden" name="brandId" value="${brandModel.brandId }">
            <input type="text" name="brandCode" value="${brandModel.brandCode }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
            <label>品牌名称：</label>
            <input type="text" name="brandName" value="${brandModel.brandName }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
         <label>品牌别名：</label>
            <input type="text" name="brandEname" value="${brandModel.brandEname }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
            <label>品牌说明：</label>
            <input type="text" name="brandInfo" value="${brandModel.brandInfo }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
            <label>品牌国家：</label>
            <input type="text" name="brandCountry" value="${brandModel.brandCountry }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
          <label>品牌logo：</label>
          <div style="margin-left:130px;">
              <a  href="${brandModel.brandLogo }"  target="_blank" >
               <img  style="height:100px;with:75px;"  src="${brandModel.brandLogo }"  />
             </a> 
          </div>
        </div>
        
        <div class="unit">
         <label>状态：</label>
            <c:choose>
              <c:when test="${brandModel.brandStatus==1}">
                 <input type="text" name="brandStatus" value="启用" readonly="readonly"  size="20" maxlength="32"/>
              </c:when>
              <c:otherwise>
                <input type="text" name="brandStatus" value="禁用" readonly="readonly"  size="20" maxlength="32"/>
              </c:otherwise>
            </c:choose>
        </div>
        
        <div class="unit">
         <label>品牌旗舰店铺编号：</label>
            <input type="text" name="flagshipStoreCode" value="${brandModel.flagshipStoreCode }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
         <label>旗舰店认证状态：</label>
            <c:choose>
              <c:when test="${brandModel.storeCertificationType==1}">
                 <input type="text" name="storeCertificationType" value="已认证" readonly="readonly"  size="20" maxlength="32"/>
              </c:when>
              <c:otherwise>
                <input type="text" name="storeCertificationType" value="未认证" readonly="readonly"  size="20" maxlength="32"/>
              </c:otherwise>
            </c:choose>
        </div>
        
        <div class="unit">
        <label>品牌故事：</label>
            <input type="text" name="brandStory" value="${brandModel.brandStory }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
         <label>创建者：</label>
            <input type="text" name="creator" value="${brandModel.creator }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
        <label>最后修改者：</label>
            <input type="text" name="brandStory" value="${brandModel.updateUser }" readonly="readonly"  size="20" maxlength="32"/>
        </div>
        
        <div class="unit">
        <label>创建时间：</label>
            <input type="text" name="createTimes" value="${brandModel.createTimes }"    readonly="readonly"  size="20" maxlength="32"/>
        </div>
    </div>
            
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>