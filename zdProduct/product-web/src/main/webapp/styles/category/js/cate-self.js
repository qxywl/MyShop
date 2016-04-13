/* 
 * 自定义JS
 */


 function queryCategory(){
		var parentCateId="";
		var totCount=0;
		var parentLevel=-1;
	    var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	    nodes=treeObj.getCheckedNodes(true);
	    for(var i=0;i<nodes.length;i++){
	    	parentCateId =nodes[i].id; //获取选中节点的值
	        break;
	    }
		return parentCateId;
}	
 
 function udpCategory(){
	var updateCateId="";
	var totCount=0;
	var treeObj=$.fn.zTree.getZTreeObj("orgTree");
    nodes=treeObj.getCheckedNodes(true);
    for(var i=0;i<nodes.length;i++){
     	updateCateId =nodes[i].id; //获取选中节点的值
        totCount +=1;
    }
	if(totCount==0){
		alert("请选择要修改的分类！");
		return false;
	}
	if(totCount>1){
		alert("一次只能修改一个分类！");
		return false;
	}
	return true;
 } 
 
 function checkDeleteCategory(urlStr){
	alertMsg.confirm("确定删除？", {
		okCall: function(){
			deleteCategory(urlStr);
		}
	});
}
 
 function deleteCategory(urlStr){
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

function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	function blurKey(e) {
		if (key.get(0).value === "") {
			key.addClass("empty");
		}
	}
	var lastValue = "", nodeList = [], fontCss = {};
	function clickRadio(e) {
		lastValue = "";
		searchNode(e);
	}
	function searchNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree");
		var value = $.trim(key.get(0).value);
		var keyType = "name";
		if (key.hasClass("empty")) {
			value = "";
		}
		if (lastValue === value) return;
		lastValue = value;
		if (value === "") return;
		updateNodes(false);

		nodeList = zTree.getNodesByParamFuzzy(keyType, value);
		
		updateNodes(true);

	}
	function updateNodes(highlight) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree");
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			if(nodeList[i].level==2){
				zTree.expandNode(nodeList[i].getParentNode(),true,true,true);
			}else{
				if(nodeList[i].level ==1 && nodeList[i].children == undefined){
					zTree.expandNode(nodeList[i].getParentNode(),true,true,true);
				}else{
					zTree.expandNode(nodeList[i],true,true,true);
				}
			}
			zTree.updateNode(nodeList[i]);
		}
	}
	
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	function filter(node) {
		return !node.isParent && node.isFirstNode;
	}

	var key;
	$(document).ready(function(){
		key = $("#search_cate_name");
		key.bind("focus", focusKey)
		.bind("blur", blurKey)
		.bind("propertychange", searchNode)
		.bind("input", searchNode);
	});
 

