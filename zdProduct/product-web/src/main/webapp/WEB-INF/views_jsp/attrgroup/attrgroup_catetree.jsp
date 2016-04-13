<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/styles/category/js/cate-self.js"></script>


<style>
  .m_l { width:100%; float:left;}
  .m_cl{clear:both;}
  .m_r { width:25%; top:0px;float:right;vertical-align: middle;}
  .empty{}
  .ztree li span{float: none!important;}
</style>
 <script type="text/javascript">
 
 function previewCate(){
	 var checkIdStr = "" ;
	 var treeObj=$.fn.zTree.getZTreeObj("orgTree");
     nodes=treeObj.getCheckedNodes(true);
     for(var i=0;i<nodes.length;i++){
    	 checkIdStr += nodes[i].id; //获取选中节点的值
    	 checkIdStr += ",";
     }
     if(checkIdStr != "")
     {
	     checkIdStr = checkIdStr.substr(0,checkIdStr.length-1);
     }
     else
    {
    	 checkIdStr = "nodate";
    }
     $.ajax({
         type: "post",
         url: "${contextPath}/attrgroup/saveGroupcate/${attrGroupId}",
         data: "checkIdStr="+checkIdStr,
         dataType: "text",
         success: function(data){
//         	 	 var zNodes = eval("["+data+"]");
//         	     var t = $("#orgTree");
//         	     t.html("");
//         	     t = $.fn.zTree.init(t, setting, zNodes);
//         	    // t.expandAll(true); 
        	     
//         	     /**禁止选择一级二级分类
//         	 	**/
//         	     var treeObj=$.fn.zTree.getZTreeObj("orgTree");
//         	     var notCheckedNodes=treeObj.getCheckedNodes(false);
//         	     for(var i=0;i<notCheckedNodes.length;i++)
//         	     {
//         	    	 	if(notCheckedNodes[i].level == 0 || notCheckedNodes[i].level == 1)
//         	    	 	{
//         	    	 		treeObj.setChkDisabled(notCheckedNodes[i], true);
//         	    	 	}
//         	     }
         }
    })
		
 }
 </script>
 	
   <div style="width:100%;" class="pageContent">
     <div class="m_l" style="overflow:scroll;height:350px">
     	<ul id="orgTree" class="ztree" style="display: block;"></ul>
     </div>
     
	    <!--  <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        	<li><a id="" class="edit" onclick="previewCate();"  href="javascript:void(0);" ><span>提交</span></a></li>
	        </ul>
	    </div> 
   
     <div class="m_cl"></div>-->
     <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button"  onclick="previewCate();">确定</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
   </div>
	

<script type="text/javascript">

var setting = {
    view: {
        //showIcon: false
    },
    check: {
    	enable: true,
    	chkDisabledInherit: true,
    	//chkStyle = "radio",
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
		fontCss: getFontCss
	},
	 
};

function zTreeOnCheck(event, treeId, treeNode) {
    //alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked + "," + treeNode.level);
    
    if(treeNode.level == 0)
    {
    	return;
    }
    var treeObj=$.fn.zTree.getZTreeObj("orgTree");
    var treeNode = changeParentChecked(treeNode,treeObj);
    while(treeNode.level > 0)
    {
    	treeNode = changeParentChecked(treeNode,treeObj);
    }
}
/**
 * 节点的选择条件，若选择父接点，则所有子节点都选择，若取消一个子节点，则父接点也取消选择，以此递推
 */
function changeParentChecked(treeNode,treeObj)
{
	var $parentNode = treeNode.getParentNode();
    if(treeNode.checked == false)
    {
    	$parentNode.checked = false;
    }
    else
    {
    		
    	var nodes = $parentNode.children;
    	var checkCount = 0;
    	 for(var i=0;i<nodes.length;i++)
    	    {
    	   	 	if(nodes[i].checked == true)
    	   	 	{
    	   	 	checkCount++;
    	   	 	}
    	    }
    	if(checkCount == nodes.length)
    	{
    		$parentNode.checked = true;
    	}
    }
    treeObj.updateNode($parentNode); //如果没有这一句，则需要鼠标移动到节点，才会选择或取消选择
    return $parentNode;
}


var zNodes = [${orgTreeStr}];

$(document).ready(function(){
    var t = $("#orgTree");
    t = $.fn.zTree.init(t, setting, zNodes);
   // t.expandAll(true); 
    
	/**禁止选择一级二级分类
	
    var treeObj=$.fn.zTree.getZTreeObj("orgTree");
    var notCheckedNodes=treeObj.getCheckedNodes(false);
    for(var i=0;i<notCheckedNodes.length;i++)
    {
   	 	if(notCheckedNodes[i].level == 0 || notCheckedNodes[i].level == 1)
   	 	{
   	 		treeObj.setChkDisabled(notCheckedNodes[i], true);
   	 	}
    }
    **/
});


</script>

</body>
</html>

