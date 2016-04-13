<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript" src="${baseUrl }/styles/category/js/cate-self.js"></script>


<style>
	#orgTree li span {
	    text-align:left;
	    float: left;
	    display: inline;
	} 
	.ztree li span{float: none!important;}
</style>

 <script type="text/javascript">
 $(function(){
		
		$("#addTreeNode11").click(function(){
			var parentCateId="";
			var totCount=0;
			var parentLevel=-1;
            var treeObj=$.fn.zTree.getZTreeObj("orgTree_dialog");
            nodes=treeObj.getCheckedNodes(true);
            for(var i=0;i<nodes.length;i++){
	           	cateName = nodes[i].name; 
	            cateId =nodes[i].id; //获取选中节点的值
	            totCount +=1;
            }
			if(totCount>1){
				alert("一次只能选择一个运营分类！");
				return;
			}
			$.bringBack({categoryName:cateName, categoryId:cateId});
		});
 });
  
 </script>


   <div style="width:100%; overflow: auto; 	height: 350px;">
     <div class="m_l">
     	<ul id="orgTree_dialog" class="ztree" style="display: block;"></ul>
     </div>
     <div class="m_r">
        <input type="button" id="addTreeNode11" value="确定" /> <p/>
     </div>
   </div>
	

<script type="text/javascript">

var setting1 = {
    view: {
        //showIcon: false
    },
    check: {
    	enable: true,
    	chkStyle:"radio",
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
		fontCss: getFontCss
	}
};

var zNodes1 = [${orgTreeStr}];

$(document).ready(function(){
    var t = $("#orgTree_dialog");
    t = $.fn.zTree.init(t, setting1, zNodes1);
    //t.expandAll(true); 
});


</script>


