<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>


.pageFormContent label {
float: left;
width: 136px;
padding: 0 5px;
line-height: 21px;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	
	
	  $("#btnExport").click(function(){
		  var spCodesTemp = "";
		  $('input:checkbox[name=baseCateCodes]:checked').each(function(i){
		       if(0==i){
		        spCodesTemp ="baseCateCodes=" + $(this).val();
		       }else{
		        spCodesTemp += ("&baseCateCodes="+$(this).val());
		       }
		   });
		
		  window.location.href="${contextPath}/sale-category/exportProNoCategory?"+spCodesTemp; 
	});
});
	

</script>


<div class="pageContent">
  <div>
    <div class="pageFormContent" layouth="56">
		
		<c:if test="${cateList!=null}">
			<c:forEach items="${cateList}" var="item">
		    	<label><input type="checkbox" name="baseCateCodes" value="${item.categoryCode}" />${item.categoryName}</label>
			</c:forEach>
		</c:if>
    </div>
    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <label ><input type="checkbox" class="checkboxCtrl" group="baseCateCodes" />全选</label>
                    </div>
                </div>
                <div class="buttonActive"><div class="buttonContent"><button id="btnExport">导出</button></div></div>
            </li>
        </ul>
    </div>
</div> 
</div>

