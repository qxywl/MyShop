<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<script type="text/javascript">

	function callback(json){
		if(typeof(json) == "object"){
			alert(json.message);
		}
		$.pdialog.closeCurrent(); 
		var currentCateId = "${mountProModel.categoryId}";
		var url = '${contextPath}/sale-category/product/list?currentCateId='+currentCateId;
		navTab.reload(url);
	}
	
	function uploadBack(data){
		if(data.statusCode == DWZ.statusCode.error) {
			if(data.message && alertMsg) alertMsg.error(data.message);
			return;
		}
	}

</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pageContent">
	<form method="post"  action="${contextPath}/sale-category/product/updateOrderVal" enctype="multipart/form-data" onsubmit="return validateCallback(this, callback)">
		<input type="hidden" name="relationId" value="${mountProModel.relationId }" />
		<div class="pageFormContent" layoutH="58">
			<p></p>
			<p>
				<label>排序值：</label> 
				<input type="text" name="orderValue" value="${mountProModel.orderValue }"
					class="validate[required,custom[integer],maxSize[9]] required" size="20" maxlength="9" />
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>