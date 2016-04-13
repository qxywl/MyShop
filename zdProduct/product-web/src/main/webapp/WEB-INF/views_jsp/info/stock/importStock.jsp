<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">
	
function callback(data){
	  confirm(data.message);
	  if(data.statusCode == 200){
		  $.pdialog.closeCurrent();
	  }
	  return true;
}
	
</script>

<div class="pageContent" >
<form method="POST"   action="${contextPath}/info/stock/importExcel" enctype="multipart/form-data" onsubmit="return iframeCallback(this, callback)">
    <div class="pageFormContent" layoutH="30">
        <div style="height:30px;width:400px;"> 
           <label> 请选择导入类型:</label>
               <select id="selectType" name="type" >
                  <option value="2">修改库存</option>
                  	<!--<option value="1">新增库存</option>-->
               </select>
       	 </div>
       	<!-- <div style="height:30px;width:400px;">
            <label>新增库存模板：</label><label style="margin-top:5px;"><a class="fontColor" href ="${contextPath}/styles/image/addStockTemplate.xls" target="_blank" >下载模板1</a></label>
       </div> -->
       <div style="height:30px;width:400px;">
          <label>修改库存模板：</label><label style="margin-top:5px;"><a class="fontColor" href="${contextPath}/styles/image/changeStockTemplate.xlsx">下载模板2</a></label>
       </div>
		<div style="height:30px;width:400px;"> 
           <label>请导入文件:</label>
               <input type="file" name="file" id="selectFile"  accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" class="fontColor"/>
       	 </div>
    </div>
            
    <div class="formBar" >
        <ul>
            <li><div class="button"><div class="buttonContent"><button class="subBtn" type="submit">导入</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>