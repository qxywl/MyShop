<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript" src="${baseUrl }/styles/category/js/cate-self.js"></script>

<style>
/*
	#orgTree li span {
	    text-align:left;
	    float: left;
	    display: inline;
	} 
	*/
</style>
<style>
  .m_l { width:65%; padding-left:40px;padding-top:12px;float:left;}
  .m_cl{clear:both;}
  .m_r { width:25%; float:right;vertical-align: middle; position: fixed; right: 0px;}
  .empty{}
    .ztree li span{float: none!important;}
</style>

 <script type="text/javascript">
 
 function addBaseCate(){
	 	var urlStr = '${baseUrl }/sale-category/product/noProducts';
		var totCount=0;
		var mountCateId="";
		var parentLevel=-1;
	    var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	    nodes=treeObj.getCheckedNodes(true);
	    for(var i=0;i<nodes.length;i++){
	    	mountCateId =nodes[i].id; //获取选中节点的值
	     	parentLevel = nodes[i].level; 
	        totCount +=1;
	    }
		if(totCount>1){
			alert("一次只能在一个分类下添加子分类！");
			return false;
		}
		if(parentLevel>1){
			alert("三级运营分类下不能添加子分类！");
			return false;
		}
		
		var result = false;
		if(parentLevel == -1){
			result = true;
		}else{
			$.ajax({
				type: 'get',
				url: urlStr,
				dataType: 'json',
				async:false,
				data: {
				 "currentCateId": mountCateId
				},
				timeout:1000,
				success: function(json){
					if(typeof(json) == 'object'){
						if (json.statusCode == DWZ.statusCode.ok){
							result = true;
						}else{
							DWZ.ajaxDone(json);
						}
					}
					return false;
				}
			});
		}
		
		return result;
}	
 
//自动挂载、筛选项挂载
function toMount(urlStr){
	var mountCateId="";
	var totCount=0;
	var curLevel="";
	var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	   nodes=treeObj.getCheckedNodes(true);
	   for(var i=0;i<nodes.length;i++){
	   	mountCateId =nodes[i].id; //获取选中节点的值
	   	curLevel = nodes[i].level;
	       totCount +=1;
	   }
	if(totCount==0){
		alert("请选择运营分类！");
		return false;
	}
	if(totCount>1){
		alert("一次只能在一个运营分类下操作！");
		return false;
	}
	$("#cur_cate_level_hidd").val(curLevel);
	$("#cur_cate_id_hidd").val(mountCateId);
	var _mFrm=$("#toMount_form");
	_mFrm.attr("action",urlStr);
	_mFrm.submit();

}


//手动挂载
function checkCategory(forwardUrl){
	var urlStr = '${baseUrl }/sale-category/checkCategory';
	var mountCateId="";
	var totCount=0;
	var curLevel="";
	var showFlag = "";
	var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	nodes=treeObj.getCheckedNodes(true);
	for(var i=0;i<nodes.length;i++){
		mountCateId =nodes[i].id; //获取选中节点的值
		curLevel = nodes[i].level;
		showFlag = nodes[i].showFlag;
	    totCount +=1;
	}
	if(totCount==0){
		alert("请选择运营分类！");
		return false;
	}
	if(totCount>1){
		alert("一次只能在一个运营分类下操作！");
		return false;
	}
	
	if(curLevel == 0){
		alert("一级分类不能挂载！");
		return false;
	}
	
	if(showFlag == 0){
		alert("已经隐藏不能挂载！");
		return false;
	}
	
	$.ajax({
		type: 'get',
		url: urlStr,
		dataType: 'json',
		data: {
		 "currentCateId": mountCateId,
		 "curLevel": curLevel
		},
		success: function(json){
		 if(typeof(json) == 'object'){
		 	DWZ.ajaxDone(json);
			var tabId = getCurrentNavtab().attr("tabid");
			if (json.statusCode == DWZ.statusCode.ok){
				navTab.reload(forwardUrl + "?currentCateId=" + mountCateId + "&curLevel=" + curLevel);
				/* if ("closeCurrent" == json.callbackType) {
					$.pdialog.closeCurrent();
				} */
			}
		 }
		}
	});
}
 
 
 function previewCate(){
	 var mountCateId="";
	 var totCount=0;
	 var treeObj=$.fn.zTree.getZTreeObj("orgTree");
     nodes=treeObj.getCheckedNodes(true);
     for(var i=0;i<nodes.length;i++){
    	mountCateId =nodes[i].id; //获取选中节点的值
       totCount +=1;
     }
		
	 if(totCount==0){
		alert("请选择运营分类！");
		return false;
	 }
	 if(totCount>1){
		alert("一次只能在一个运营分类下预览！");
		return false;
	 }
	 window.location.href="${baseUrl}/sale-category/preview?currentCateId="+mountCateId;
 }
 
function checkDeleteSaleCategory(urlStr){
	alertMsg.confirm("确定删除？", {
		okCall: function(){
			deleteSaleCategory(urlStr);
		}
	});
}

function deleteSaleCategory(urlStr){
	alertMsg.confirm("确定删除？");
	var delIdsString="";
	var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	    nodes=treeObj.getCheckedNodes(true);
	    for(var i=0;i<nodes.length;i++){
	    	delIdsString=delIdsString.length==0?nodes[i].id:(delIdsString+","+nodes[i].id);
	    }
	
	if(delIdsString.length==0){
		alert("请选择要删除的分类！");
		return;
	}
	
	//var href=$("#delete_a_id").attr("href");
	//_href.attr("href",_href.attr("href")+"?delIds="+delIdsString);
	//urlStr=urlStr + "?delIds=" + delIdsString;
	 
	 $.ajax({
		type: 'get',
		url: urlStr,
		dataType: 'json',
		data:{
			delIds: delIdsString
		},
		timeout:1000,
		async: false,
		success: function(json){
			if(typeof(json) == 'object'){
				DWZ.ajaxDone(json);
				if (json.statusCode == DWZ.statusCode.ok){
					navTab.reload(json.forwardUrl);
				}
			}
		}
	});
	 
	 return false;
 }	

 //显示分类
 function checkShowCategory(urlStr){
	alertMsg.confirm("确定显示分类吗？", {
		okCall: function(){
			showSaleCategory(urlStr);
		}
	});
 }
 
 function showSaleCategory(urlStr){
		var showIdsString="";
		var treeObj=$.fn.zTree.getZTreeObj("orgTree");
		    nodes=treeObj.getCheckedNodes(true);
		    for(var i=0;i<nodes.length;i++){
		    	showIdsString=showIdsString.length==0?nodes[i].id:(showIdsString+","+nodes[i].id);
		    }
		
		if(showIdsString.length==0){
			alert("请选择要隐藏的分类！");
			return;
		}
		 
		 $.ajax({
			type: 'get',
			url: urlStr,
			dataType: 'json',
			data:{
				showIds: showIdsString
			},
			timeout:1000,
			async: false,
			success: function(json){
				if(typeof(json) == 'object'){
					DWZ.ajaxDone(json);
					if (json.statusCode == DWZ.statusCode.ok){
						navTab.reload(json.forwardUrl);
					}
				}
			}
		});
		 
		 return false;
	 }	
 
 
 //隐藏分类
 function checkHiddenCategory(urlStr){
	alertMsg.confirm("确定隐藏分类吗？", {
		okCall: function(){
			hiddenSaleCategory(urlStr);
		}
	});
 }
 
 
 
 function hiddenSaleCategory(urlStr){
		alertMsg.confirm("确定隐藏？");
		var hidIdsString="";
		var treeObj=$.fn.zTree.getZTreeObj("orgTree");
		    nodes=treeObj.getCheckedNodes(true);
		    for(var i=0;i<nodes.length;i++){
		    	hidIdsString=hidIdsString.length==0?nodes[i].id:(hidIdsString+","+nodes[i].id);
		    }
		
		if(hidIdsString.length==0){
			alert("请选择要隐藏的分类！");
			return;
		}
		 
		 $.ajax({
			type: 'get',
			url: urlStr,
			dataType: 'json',
			data:{
				hidIds: hidIdsString
			},
			timeout:1000,
			async: false,
			success: function(json){
				if(typeof(json) == 'object'){
					DWZ.ajaxDone(json);
					if (json.statusCode == DWZ.statusCode.ok){
						navTab.reload(json.forwardUrl);
					}
				}
			}
		});
		 
		 return false;
	 }
    
 
     //跳转到批量导入页面
	 function toBatchImportCate(){
		 window.location.href="${baseUrl}/sale-category/toBatchImportCate";
	 }
		
 </script>
 <div class="pageFormContent" layoutH="58">
 <!-- 
 <div style="width:100%;max-height:445px;overflow: auto;position: relative;">
  -->
     <div class="m_l">
     	<ul id="orgTree" class="ztree" style="display: block;"></ul>
     </div>
     <div class="m_r">
     
     	<div class="m_cl" style="height:35px;"></div>
        <!-- add -->
      	<div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	          <li><a class="add" target="dialog"  mask="true" width="450" height="400" SetCateCode="queryCategory" Validation="addBaseCate"  href="${baseUrl}/sale-category/to-add"><span>添加分类</span></a><p/></li>
	        </ul>
	        <br/>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!-- update -->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a class="edit" target="dialog"  mask="true" width="630" height="510" SetCateCode="queryCategory" Validation="udpCategory" href="${baseUrl}/sale-category/to-update"><span>修改分类</span></a></p></li>
	        </ul>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!-- delete -->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a id="delete_a_id" class="delete" onclick="checkDeleteSaleCategory('${baseUrl}/sale-category/delete')" title="确认要删除?"><span>删除分类</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <!-- filter mount -->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a id="filter_mount_a_id" class="edit" onclick="toMount('${baseUrl}/filter-mount/main')"><span>筛选项挂载</span></a></li>
	        </ul>
	    </div>
	    <!--  mount  rule-->

	    <div class="m_cl" style="height:15px;"></div>
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a id="mount_rule_a_id"  class="edit"  onclick="checkCategory('${baseUrl}/mount-rule/main')" ><span>自动挂载规则</span></a></li>
	        </ul>
	    </div>

    	<form action="" id="toMount_form" onsubmit="return navTabSearch(this)">
    		<input type="hidden" id="cur_cate_id_hidd" name="currentCateId">
    		<input type="hidden" id="cur_cate_level_hidd" name="currentCateLevel">
    	</form>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <!--  mount  product-->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<%-- <li><a id="mount_product_a_id" class="edit" target="dialog" onclick="return toMount('mount_product_a_id');" onsubmit="return navTabSearch(this);" href="${contextPath}/sale-category/product/upload_excel" close ="navTabSearch(this);" width="630" height="430" title=""><span>手动商品挂载</span></a></li> --%>
	        	<li><a id="mount_product_a_id" class="edit"  onclick="checkCategory('${baseUrl}/sale-category/product/list')"><span>手动商品挂载</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <!--  mount  product-->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	            <li> <a class="edit" href="${baseUrl}/sale-category/toBatchImportCate" target="dialog"  rel="batch-import"><span>手动批量挂载</span></a></li>
	        </ul>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!--  mount  product-->
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<%-- <li><a id="mount_product_a_id" class="edit" target="dialog" onclick="return toMount('mount_product_a_id');" onsubmit="return navTabSearch(this);" href="${contextPath}/sale-category/product/upload_excel" close ="navTabSearch(this);" width="630" height="430" title=""><span>手动商品挂载</span></a></li> --%>
	        	<li><a id="mount_product_a_id" class="edit"  onclick="checkShowCategory('${baseUrl}/sale-category/show')"><span>显示分类</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<%-- <li><a id="mount_product_a_id" class="edit" target="dialog" onclick="return toMount('mount_product_a_id');" onsubmit="return navTabSearch(this);" href="${contextPath}/sale-category/product/upload_excel" close ="navTabSearch(this);" width="630" height="430" title=""><span>手动商品挂载</span></a></li> --%>
	        	<li><a id="mount_product_a_id" class="edit"  onclick="checkHiddenCategory('${baseUrl}/sale-category/hidden')"><span>隐藏分类</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        
	        	<li><a class="icon" href="${baseUrl}/sale-category/export" target="dwzExport" targetType="navTab" title="确定要导出记录吗?"><span>全量导出分类</span></a></li>
	        </ul>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!--  preview   此功能暂时不开放，故注释掉
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a id="preview_a_id" class="edit" target="ajaxTodo" href="javascript:previewCate();" target="_blank"><span>预览</span></a></li>
	        </ul>
	    </div>
	     -->
	    <div class="m_cl" style="height:25px;"></div>
	    <div class="panelBar" style="width:145px;height:auto!important;padding:5px;">
          <b>搜索分类</b>&nbsp;&nbsp;分类名称:<br/><input type="text" id="search_cate_name" class="empty" style="margin-top:8px">
        </div>
     </div>
     <div class="m_cl"></div>
   </div>
	

<script type="text/javascript">

var setting = {
   /*  view: {
        //showIcon: false
    }, */
    check: {
    	enable: true,
    	chkDisabledInherit: true,
    	chkboxType: {"Y" : "", "N" : "ps"}
    	},
    data: {
        simpleData: {
            enable:true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: "",
            codeKey: "code"
        }
    },
    view: {
		fontCss: getFontCss,
		nameIsHTML: true
	}
};


var zNodes = [${orgTreeStr}];

$(document).ready(function(){
    var t = $("#orgTree");
    t = $.fn.zTree.init(t, setting, zNodes);
    var treeObj = $.fn.zTree.getZTreeObj("orgTree");
    openNode(treeObj);
    var nodes = treeObj.getNodes();
  
    if (nodes.length>0) {
    	for(var i in nodes){
    		
    		highLight(nodes[i],treeObj);
    		var childrens2 = nodes[i].children;
    		
    		for(var j in childrens2){
    		
    			highLight(childrens2[j],treeObj);
    			var childrens3 =  childrens2[j].children;
    			
    			for(var k in childrens3 ){
    				highLight(childrens3[k],treeObj);
    			}
    		}
    		
    	}

    }
});

function highLight(node,treeObj){
	if(node != null && node.showFlag != null && node.showFlag == 0){
		node.name = "<font  style=\"font-weight:bold;\">"+node.name+"</font>" + "<font color='green'>(已隐藏)</font>";
		treeObj.updateNode(node);
	} 
}

//打开当前被选中的节点与其他的父几节点
function openNode(treeObj){
	var nodes=treeObj.getCheckedNodes(true);
	for(var i=0;i<nodes.length;i++){
    	var node =nodes[i]; //获取选中节点的值
    	if(node.isParent){
    		treeObj.expandNode(node, true, true, true);
    		return;
    	}else{
    		treeObj.expandNode(node.getParentNode(), true, true, true);
    	}
    }

}

</script>

</body>
</html>

