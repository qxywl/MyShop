<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<script type="text/javascript">
   var index = 0;
	
   function opDelete(id){
		
		var length = $(".add_tr").length; //dwz 框架会自动生成<tr> ,就是一个<tr> 会有两个出现
		
		if(length>1){//保留一个
			$(id).parents("tr").remove();
     		$(".add_td").remove();//删除原来
     		var str1 = "<td class=\"add_td\"><input type=\"button\" onclick=\"opAdd()\" class=\"add-btn\" value=\"添加\"></td>";
     		$(".add_tr:last").append(str1);
     	
     	}
	}
	
	function opAdd(){
		index++;
		var str='<tr class="add_tr" style="padding-left: 20px;">'
			+'<td ><label>属性值：<input type="text" name="attrValueList['+index+'].attrValue" class="validate[required,maxSize[50]] required" size="20" maxlength="50"/>'
			+'</label></td><td style="padding-left:90px;"><label>排序号：<input type="text" name="attrValueList['+index+'].attrValueNum" class="validate[required,custom[integer],maxSize[50]] required digits textInput" size="11" maxlength="11"/></label></td>'
			+'<td style="padding-left:90px;"><button button type="button" onclick="opDelete(this)">删除</button></td><tr>' ;
		$("#tableAttr").find("tbody").append(str);
		getTableChild();
	}
	
	function getTableChild(){
     	var length = $(".add_tr").length;//dwz 框架会自动生成<tr> ,就是一个<tr> 会有两个出现
     	$(".add_td").remove();//删除原来
     	var index = length - 1;
     	var str1 = "<td class=\"add_td\"><input type=\"button\" onclick=\"opAdd()\" class=\"add-btn\" value=\"添加\"></td>";
     	$(".add_tr:last").append(str1);
		
	}
	
	
	function attrCallback()
		{
			$.pdialog.reload("attr/to_listAttrValue/${attrId}", {data:{}, dialogId:"attrValueDialog", callback:null}) 
			$.pdialog.closeCurrent();
		}
</script>

<div class="pageContent">
<form METHOD="POST"  action="${contextPath}/attr/addAttrValue/${attrId}"  class="required-validate pageForm"  onsubmit="return validateCallback(this,attrCallback);">
    <div layoutH="58" style="height:100%;">
        <table  border="0" cellspacing="10" cellpadding="10" id="tableAttr">
        <tbody id="tbodyAttr">
        <tr style="padding-left: 20px;" class="add_tr">
            <td >
            	<label>属性值：<input type="text" name="attrValueList[0].attrValue" class="validate[required,maxSize[50]] required" size="20" maxlength="50"/></label>
            </td>
            <td style="padding-left:90px;">
            	<label>排序号：<input type="text" name="attrValueList[0].attrValueNum" class="validate[required,custom[integer],maxSize[50]] required digits textInput" size="11" maxlength="11"/></label>
            </td>  
            <td style="padding-left:90px;"><button type="button" onclick="opDelete(this)" >删除</button></td>      
            <td class="add_td"><input type="button" onclick="opAdd()" class="add-btn" value="添加"></td>    
        <tr>
        </tbody>
      </table>
      
    </div>
            
    <div class="formBar" >
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>