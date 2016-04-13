<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
	.btn{border-radius: 3px;border: 1px solid #a9a9a9}
	.btn-danger{background-color: #d43f3a;color: #fff;border: 1px solid #d43f3a}
	.btn-info{background-color: #46b8da;color: #fff;border: 1px solid #46b8da}
	.btn-success{background-color: #4cae4c;color: #fff;border: 1px solid #4cae4c}
	.btn-gp{margin-top: 15px;}
	
	.tab-bctable{width: 100%;background-color: #ddd;border-spacing:1px;}
	.tab-bctabletd{padding: 10px 0;background-color: #fff;text-align: center;}
	.tab-bcthead{text-align: center;}
	.tab-bcmselect{height: 80px;width: 120px;float: left;margin-left: 15px;}
</style>

<script type="text/javascript">

	//查询属性组
	function searchGrp(idx){
		var schStr=$('#sch_grp_'+idx).val();
		if(schStr == ''){
			alert("请输入属性组名称");
			return false;
		}
		$.ajax({
			url: "${baseUrl}/filter-mount/queryGroups",
			data: {"groupName":schStr},
			success: function(data) {
				var retStr = eval(data);
				var _grpSlt = $("#group_select_id_"+idx);
				_grpSlt.empty(); 
				_grpSlt.append('<option value="-1">---请选择---</option>');
				 $.each(retStr, function() {
					 _grpSlt.append('<option value="'+this.groupCode+'">'+this.groupName+'</option>');
			     });
			}
		});
	}
	
	//查询属性项
	function queryAttrs(idx){
		var groupCode = $("#group_select_id_"+idx).val();
		if(groupCode == -1){
			return;
		}
		$.ajax({
			url: "${baseUrl}/filter-mount/queryAttrs.do",
			data: {"groupCode":groupCode},
			success: function(data) {
				var retStr = eval(data);
				var sltStr='<select id="attr_select_id_'+idx+'" onchange="queryAttrVals('+idx+')">';
				sltStr = sltStr + '<option value="-1">---请选择---</option>';
				 $.each(retStr, function() {
					 sltStr = sltStr + '<option value="'+this.attrCode+'">'+this.attrName+'</option>';
			       // alert(this.attrCode+"\t"+this.attrName);
			     });
				 sltStr =sltStr + '</select>';
				 $("#attr_td_id_"+idx).html(sltStr);
			}
		});
	}
	
	// 查询属性值
	function queryAttrVals(idx){
		var attrCode = $("#attr_select_id_"+idx).val();
		if(attrCode == -1){
			return;
		}
		$.ajax({
			url: "${baseUrl}/filter-mount/queryAttrVals.do",
			data: {"attrCode":attrCode},
			success: function(data) {
				var retStr = eval(data);
				var sltStr='<select id="attrVal_select_id_'+idx+'" class="tab-bcmselect" multiple="multiple">';
				 $.each(retStr, function() {
					 sltStr = sltStr + "<option value='"+this.attrValueCode+"'"+" >"+this.attrValue+"</option>";
			     });
				 sltStr =sltStr + '</select><input id="attrVal_chk_box_'+idx+'" onclick="attrValCheckOpt('+idx+')" type="checkbox">全选';
				 $("#attrVal_td_id_"+idx).html(sltStr);
			}
		});
	}
	
	/*
	    属性值对应的复选框
	*/
	function attrValCheckOpt(idx){
		var _chkVal=$('#attrVal_chk_box_'+idx).is(':checked');
		if(_chkVal){
			$('#attrVal_td_id_'+idx).find('select option').prop('selected','selected');
			$('#attrVal_select_id_'+idx).prop("disabled", true);
		}else{
			$('#attrVal_td_id_'+idx).find('select option').removeAttr('selected');
			$('#attrVal_select_id_'+idx).prop("disabled", false);
		}
	}
	
	function addTableTr(){
		$.ajax({
			url: "${baseUrl}/filter-mount/queryGroups.do",
			success: function(data) {
				
				var retStr = eval(data);
				
				var tabtr=$("#table_tot_tr").val();
				++tabtr;
				$("#table_tot_tr").val(tabtr);
				
				var sltStr='<select id="group_select_id_'+tabtr+'" onchange="queryAttrs('+tabtr+')">';
				sltStr = sltStr + '<option value="-1">---请选择---</option>';
				 $.each(retStr, function() {
					 sltStr = sltStr + '<option value="'+this.groupCode+'">'+this.groupName+'</option>';
			     });
				sltStr =sltStr + '</select>';
				 var trStr = '<tr height="35px"  id="fm_tr_id_'+tabtr+'">'+
			    	'<td align="center"  class="tab-bctabletd">'+
			    		'<input type="text" id="sch_grp_'+tabtr+'" ><input type="button" onclick="searchGrp('+tabtr+')" value="搜索">'+ 
			    	   sltStr+
			    	'</td>'+
			    	'<td class="tab-bctabletd" id="attr_td_id_'+tabtr+'" align="center"></td>'+
			    	'<td class="tab-bctabletd" id="attrVal_td_id_'+tabtr+'"  align="center"></td>'+
			    	'<td class="tab-bctabletd" align="center"><input type="text" id="sort_value_'+tabtr+'"></td>'+
		  	    	'<td class="tab-bctabletd" align="center"><input type="button" onclick="delFlTr('+tabtr+')" class="btn btn-danger" value="删除"> </td>'+
			    '</tr>';
				 var _tr = $(trStr);
				 var btbody= $("#flt_table").find("tbody");
				 _tr.appendTo(btbody).initUI();
			}
		});
	}
	
	/*
	  删除新增的行
	*/
	function delFlTr(idxTr){
		$("tr[id='fm_tr_id_"+idxTr+"']").remove();//删除当前行
	}
	
	/*
	  删除原有的行
	*/
	function delOldFlTr(idxTr,oldId){
		delFlTr(idxTr);
		$('#old_del_ids_data').val($('#old_del_ids_data').val()+";"+oldId);
	}
	
	function saveFlts(){
		var totTr=$("#table_tot_tr").val();
		var oldTr = $("#table_old_tr").val();
		
		//原排序值
		var ordStr = '';
		if(oldTr > 0){
			for(var t=0;t<=oldTr;t++){
				var fmId = $('#fm_id_'+t).val();
				if(fmId != undefined && fmId != ''){
					var ordNewVal = $('#fm_order_id_'+t).val();
					var ordOldVal = $('#fm_order_old_id_'+t).val(); 
					if(ordNewVal == ordOldVal){
						continue;
					}
					ordStr+=fmId+":"+ordNewVal+";";
				}
			}
			if(ordStr != ''){
				$('#old_order_data').val(ordStr.substr(0,ordStr.length-1));
			}
		}
		
		//新数据
		var newData='';
		for(var t=oldTr;t<=totTr;t++){
			var attrCode = $('#attr_select_id_'+t).val();
			var _mchoice='';
			if(attrCode != undefined && attrCode != ''){
				 $('#attrVal_select_id_'+t).find('option:selected').each(function(){
					_mchoice += $(this).val()+","; 
				})
				newData+=attrCode+';'+_mchoice.substr(0,_mchoice.length-1)+';'+$('#sort_value_'+t).val()+"#";
			}
		}
		if(newData != ''){
			$('#new_add_filter_data').val(newData.substr(0,newData.length-1));
		}
		
		/*
		if(ordStr == '' && newData==''){
			alert('没有可保存的数据');
			return;
		} */
		
		//是否启用颜色筛选项
		if($('#color_show_flag_id').is(':checked')) {
		   $('#color_show_flagSave_id').val(1);
		}else{
			$('#color_show_flagSave_id').val(0);
		}
		
		//是否启用尺码筛选项
		if($('#size_show_flag_id').is(':checked')) {
		   $('#size_show_flagSave_id').val(1);
		}else{
			$('#size_show_flagSave_id').val(0);
		}
		
		$('#filterMountForm').submit();
	}
	
</script>

   <c:if test="${addOper }">
   		<form action="${baseUrl}/filter-mount/add" method="POST" id="filterMountForm"  onsubmit="return validateCallback(this, dialogReloadNavTab)">
   </c:if>
   <c:if test="${!addOper}">
   		<form action="${baseUrl }/filter-mount/update" method="POST" id="filterMountForm" onsubmit="return validateCallback(this, dialogReloadNavTab)">
  		   <!-- 已有数据排序值格式：id1:排序值1;id2:排序值2 -->
  		   <input type="hidden" name="oldOrderData" id="old_order_data">
  		   <!-- 已有数据 删除的数据：id1;id2 -->
  		   <input type="hidden" name="oldDelIdsData" id="old_del_ids_data">
   </c:if>
   		<input type="hidden" name="currentCateId" id="current_cate_id" value="${currentCateId }">
   		<!-- 新添加的数据 ，格式：属性项Code1;属性值Code11,属性值Code12;排序值1#属性项Code2;属性值Code21,属性值Code22;排序值2 -->
  		<input type="hidden" name="newAddFilterData" id="new_add_filter_data">
  		
  		<input type="hidden" name="colorShowFlag" id="color_show_flagSave_id">
  	    <input type="hidden" name="sizeShowFlag" id="size_show_flagSave_id">
  		<input type="hidden" name="isLevel1" value="${isLevel1 }">
   </form>
  
  <input type="hidden" id="table_tot_tr" value="${fn:length(fmShowList)}">
  <input type="hidden" id="table_old_tr" value="${fn:length(fmShowList)}">
    
  <div class="pageFormContent" layoutH="58">  
  <table class="tab-bctable" id="flt_table" border="0" cellspacing="1" cellpadding="0">
  	 <thead class="tab-bcthead">
  	    <tr height="35px">
  	    	<th width="36%">属性组</th>
  	    	<th width="22%">属性项</th>
  	    	<th width="22%">属性值</th>
  	    	<th width="10%">排序值</th>
  	    	<th width="10%">操作</th>
  	    </tr>
  	 </thead>
  	 <tbody>
  	 	<c:if test="${fn:length(fmShowList) == 0 }">
	  	    <tr height="35px" id="fm_tr_id_0">
	  	    	<td align="center" class="tab-bctabletd">
	  	    		<input type="text" id="sch_grp_0" ><input type="button" class="btn" onclick="searchGrp(0)" value="搜索"> 
	  	    	    <select id="group_select_id_0" onchange="queryAttrs(0)">
	  	    	        <option value="-1">---请选择---</option>
	  	    	    	<c:forEach var="grp" items="${groupList }">
	  	    	    		<option value="${grp.groupCode }">${grp.groupName }</option>
	  	    	    	</c:forEach>
	  	    	    </select>
	  	    	</td>
	  	    	<td class="tab-bctabletd" id="attr_td_id_0" align="center">&nbsp;</td>
	  	    	<td class="tab-bctabletd" id="attrVal_td_id_0" align="center">&nbsp;</td>
	  	    	<td class="tab-bctabletd" align="center"><input type="text" id="sort_value_0"></td>
	  	    	<td class="tab-bctabletd" align="center"><input type="button" onclick="delFlTr('0')" class="btn btn-danger" value="删除"> </td>
	  	    </tr>
  	    </c:if>
  	    <c:if test="${fn:length(fmShowList) > 0 }">
  	    	<c:forEach var="fmSh" items="${fmShowList }"  varStatus="fmStatus">
  	    		<tr height="35px" id="fm_tr_id_${fmStatus.index }">
		  	    	<td align="center" class="tab-bctabletd">&nbsp;</td>
		  	    	<td class="tab-bctabletd" align="center">
						<input type="text" value="${fmSh.attName }" readonly="readonly">
						<input type="hidden" value="${fmSh.id }" id="fm_id_${fmStatus.index }">
					</td>
		  	    	<td class="tab-bctabletd" align="center">
		  	    		 <select disabled="disabled" class="tab-bcmselect" multiple="multiple">
		  	    		 	<c:forEach var="fmVal" items="${fmSh.attrValNameList }">
		  	    		 		<option selected="selected">${fmVal }</option>
		  	    		 	</c:forEach>
		  	    		 </select>
		  	    	</td>
		  	    	<td class="tab-bctabletd" align="center">
		  	    		<input type="text" id="fm_order_id_${fmStatus.index }" value="${fmSh.orderNumber }">
		  	    		<input type="hidden" id="fm_order_old_id_${fmStatus.index }" value="${fmSh.orderNumber }">
		  	    	</td>
		  	    	<td class="tab-bctabletd" align="center"><input type="button" onclick="delOldFlTr(${fmStatus.index },'${fmSh.id }')" class="btn btn-danger" value="删除"> </td>
		  	    </tr>
  	    	</c:forEach>
  	    </c:if>
  	 </tbody>
  </table>
  
    <!-- 一级分类添加 -->
    <c:if test="${isLevel1 }">
        <c:choose>
        	<c:when test="${addOper }">
        		<div class="btn-gp">
			  	   <input type="checkbox" id="color_show_flag_id" checked="checked" >启用颜色筛选项&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			  	   <input type="checkbox" id="size_show_flag_id" checked="checked" > 启用尺码筛选项&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			  	</div>
        	</c:when>
        	<c:otherwise>
        		<div class="btn-gp">
			  	   <input type="checkbox" id="color_show_flag_id" <c:if test="${colorShowFlag }">checked="checked"</c:if> >启用颜色筛选项&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			  	   <input type="checkbox" id="size_show_flag_id" <c:if test="${sizeShowFlag }">checked="checked"</c:if> > 启用尺码筛选项&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
			  	</div>
        	</c:otherwise>
        </c:choose>
    </c:if>
  	
	<div class="btn-gp">
		<button type="button" onclick="addTableTr()" style="width: 80px;height: 34px;" class="btn btn-info">添加一行</button>
		<button type="button" class="btn btn-success" onclick="saveFlts()" style="width: 80px;height: 34px;">保存</button>
	</div>
</div>