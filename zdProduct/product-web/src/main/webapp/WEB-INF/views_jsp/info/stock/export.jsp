<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">
    
	function submitForm()
	{
		var $obj = $("#pageNumId");
		var value = $obj.val();
		var maxPageNum = $("#maxPageNum").val();
		var reg = new RegExp("^[0-9]*$");  
        if(!reg.test(value.trim()) || parseInt(value) > maxPageNum || value <= 0)
        {
        	alert("请输入不大于" + maxPageNum + "的整数");	
        	return;
        }
		$("#formId_import").submit();
	}
</script>

<div class="pageContent">
<form method="POST" id="formId_import" action="${contextPath}/info/stock/exportExcel" enctype="multipart/form-data" >
    <div class="pageFormContent" layoutH="30">
        <p style="margin-top:20px;">
        	
             <label > 请输入导出的页码:</label><input type="text" name="pageNum" id="pageNumId"/>
             <label>页</label><label></label>
             <label style="width:300px;color:red">*注意：此处按一千分页</label><label></label>
             <label>当前总页数：${totalCount}</label>
        </p>
         <input type="hidden" id="maxPageNum"  value="${totalCount}"/>
         <input type="hidden" name="categoryCode" value="${productStockPo.categoryCode }"/>
		 <input type="hidden" name="categoryName" value="${productStockPo.categoryName }"/>
		 <input type="hidden" name="brandCode" value="${productStockPo.brandCode }"/>
		 <input type="hidden" name="brandName" value="${productStockPo.brandName }"/>
		 <input type="hidden" name="productCode" value="${productStockPo.productCode }"/>
		 <input type="hidden" name="productName" value="${productStockPo.productName }"/>
		 <input type="hidden" name="skuCode" value="${productStockPo.skuCode }"/>
		
    </div>
            
    <div class="formBar" style="margin-bottom: 2px;">
        <ul>
            <li><div class="button"><div class="buttonContent"><button class="subBtn" type="button" onclick="submitForm();">导出</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>