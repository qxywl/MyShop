<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />

<title>基础分类操作</title>

<link href="${baseUrl }/styles/ztree/css/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${baseUrl }/styles/category/css/weebox.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" src="${baseUrl }/styles/category/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${baseUrl }/styles/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${baseUrl }/styles/category/js/bgiframe.js"></script>
<script type="text/javascript" src="${baseUrl }/styles/category/js/weebox.js"></script>

<script type="text/javascript" src="${baseUrl }/styles/category/js/cate-self.js"></script>

<style>
	#orgTree li span {
	    text-align:left;
	    float: left;
	    display: inline;
	} 
</style>
<style>
  .m_l { width:75%; float:left;}
  .m_cl{clear:both;}
  .m_r { width:25%; float:right;top:120px;}
  .empty{}
</style>

 <script type="text/javascript">
 $(function(){
		
		$("#addBaseCategoryBtn").click(function(){
			var parentCateId="";
			var totCount=0;
			var parentLevel=-1;
            var treeObj=$.fn.zTree.getZTreeObj("orgTree");
            nodes=treeObj.getCheckedNodes(true);
            for(var i=0;i<nodes.length;i++){
            	parentLevel = nodes[i].level; 
	            parentCateId =nodes[i].id; //获取选中节点的值
	            totCount +=1;
            }
			if(totCount>1){
				alert("只能在一个基础分类下添加子分类！");
				return;
			}
			if(parentLevel>1){
				alert("三级基础分类下不能添加子分类！");
				return;
			}
			
			$.weeboxs.open('#addCateDiv', {title:'增加基础分类',contentType:'selector',width:400,okBtnName:'增加',
				onok:function(box){
					var curName=$("#curAddCateName").val();
					if(curName == ''){
						$("#addCateDiv").css("display","none");
						alert("请输入基础分类名称！");
						return;
					}
					$("#parent_id").val(parentCateId);
					$('#addform').submit();
				}
			});
		});
		
		$("#udpBaseCategoryBtn").click(function(){
			var updateCateId="";
			var updateCateName="";
			var totCount=0;
			var treeObj=$.fn.zTree.getZTreeObj("orgTree");
            nodes=treeObj.getCheckedNodes(true);
            for(var i=0;i<nodes.length;i++){
            	updateCateId =nodes[i].id; //获取选中节点的值
            	updateCateName=nodes[i].name;
	            totCount +=1;
            }
			
			if(totCount==0){
				alert("请选择要修改的基础分类！");
				return;
			}
			if(totCount>1){
				alert("一次只能修改一个基础分类！");
				return;
			}
			
			//$("#oldCateName").val(updateCateName);
			
			$.weeboxs.open('#udpCateDiv', {title:'修改基础分类',contentType:'selector',width:400,okBtnName:'修改',
				onok:function(box){
					var curName=$("#curUdpCateName").val();
					if(curName == ''){
						$("#updateCateDiv").css("display","none");
						alert("请输入基础分类名称！");
						return;
					}
					$("#update_id").val(updateCateId);
					$('#udpform').submit();
				}
			});
		});
		
		$("#delBaseCategoryBtn").click(function(){
			var delIdsString="";
			var treeObj=$.fn.zTree.getZTreeObj("orgTree");
            nodes=treeObj.getCheckedNodes(true);
            for(var i=0;i<nodes.length;i++){
            	delIdsString=delIdsString.length==0?nodes[i].id:(delIdsString+","+nodes[i].id);
            }
			
			if(delIdsString.length==0){
				alert("请选择要删除的基础分类！");
				return;
			}
			$("#delete_ids").val(delIdsString);
			$('#delform').submit();
		});
		
	});
  
 </script>
</head>

<body>
 

  <c:if test="${ msgInfo != null }">
  		<script type="text/javascript">
	       alert('${msgInfo}');
	   </script>
  </c:if>
   
 
   <div style="width:100%;">
     <div class="m_l">
     	<ul id="orgTree" class="ztree" style="display: block;"></ul>
     </div>
     <div class="m_cl"></div>
     <div class="m_r">
        <input type="button" id="addBaseCategoryBtn" value="添加分类" /> <p/>
        <input type="button" id="udpBaseCategoryBtn" value="修改分类" /> <p/>
        <input type="button" id="delBaseCategoryBtn" value="删除分类" /> <p/>
        <b>搜索分类</b><br/>
                       分类名称:<input type="text" id="search_cate_name" class="empty">
     </div>
   </div>
	
	<!-- 添加分类 -->
	<div style="display:none" class="boxcontent" id="addCateDiv">
		<form action="${baseUrl }/base-category/add.do" method="post" id="addform">
			<table>
				<tr>
					<td>基础分类名称:</td><td><input type="text" name="categoryName" id="curAddCateName"/></td>
				</tr>
			</table>
			<input type="hidden" id="parent_id" name="parentId">
		</form>
	</div>
	
	<!-- 修改分类 -->
	<div style="display:none" class="boxcontent" id="udpCateDiv">
		<form action="${baseUrl }/base-category/update.do" method="post" id="udpform">
			<table>
				<!-- 
				<tr>
					<td>原基础分类名称:</td><td><input type="text" id="oldCateName"/></td>
				</tr>
				 -->
				<tr>
					<td>新基础分类名称:</td><td><input type="text" name="categoryName" id="curUpdCateName"/></td>
				</tr>
			</table>
			<input type="hidden" id="update_id" name="id">
		</form>
	</div>
	
	<form action="${baseUrl }/base-category/delete.do" method="post" id="delform">
		<input type="hidden" id="delete_ids" name="delIds">
	</form>


<script type="text/javascript">

var setting = {
    view: {
        //showIcon: false
    },
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
		fontCss: getFontCss
	}
};

var zNodes = [${orgTreeStr}];

$(document).ready(function(){
    var t = $("#orgTree");
    t = $.fn.zTree.init(t, setting, zNodes);
    t.expandAll(true); 
});


</script>

</body>
</html>

