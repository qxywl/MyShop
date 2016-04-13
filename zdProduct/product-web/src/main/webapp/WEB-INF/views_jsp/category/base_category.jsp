<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<script type="text/javascript" src="${baseUrl }/styles/category/js/cate-self.js"></script>

<style>
	/*#orgTree li span {
	    text-align:left;
	    float: left;
	    display: inline;
	} */
</style>
<style>
  .m_l { width:65%; padding-left:40px;padding-top:12px; float:left;}
  .m_cl{clear:both;}
  .m_r { width:25%; float:right;vertical-align: middle; position: fixed; right: 0px;}
  input.empty {color: lightgray;}
  .ztree li span{float: none!important;}
</style>

 <script type="text/javascript">
 
 function addBaseCate(){
		var totCount=0;
		var parentLevel=-1;
	    var treeObj=$.fn.zTree.getZTreeObj("orgTree");
	    nodes=treeObj.getCheckedNodes(true);
	    for(var i=0;i<nodes.length;i++){
	     	parentLevel = nodes[i].level; 
	        totCount +=1;
	    }
		if(totCount>1){
			alert("一次只能在一个分类下添加子分类！");
			return false;
		}
		if(parentLevel>1){
			alert("三级基础分类下不能添加子分类！");
			return false;
		}
		return true;
 }	
 

 </script>
	<div class="pageFormContent" layoutH="58"> 
	<!-- 
   <div class="pageContent" style="width:100%;height:500px; overflow: auto; position: relative;">
    -->
     <div class="m_l">
     	<ul id="orgTree" class="ztree" style="display: block;"></ul>
     </div>
     <div class="m_r">
         <div class="m_cl" style="height:35px;"></div>
        <!-- add -->
      	<div class="panelBar" style="width:90px;">
	        <ul class="toolBar">
	          <li><a class="add" target="dialog"  mask="true" width="550" height="350" SetCateCode="queryCategory" Validation="addBaseCate"  href="${baseUrl}/base-category/to-add"><span>添加分类</span></a><p/></li>
	        </ul>
	        <br/>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!-- update -->
	    <div class="panelBar" style="width:90px;">
	        <ul class="toolBar">
	        	<li><a class="edit" target="dialog"  mask="true" width="450" height="500" SetCateCode="queryCategory" Validation="udpCategory" href="${baseUrl}/base-category/to-update"><span>修改分类</span></a></p></li>
	        </ul>
	    </div>
	    <div class="m_cl" style="height:15px;"></div>
	    <!-- delete -->
	    <div class="panelBar" style="width:90px;">
	        <ul class="toolBar">
	        	<li><a id="delete_a_id" class="delete" onclick="checkDeleteCategory('${baseUrl}/base-category/delete')" title="确认要删除?" ><span>删除分类</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:15px;"></div>
	    <div class="panelBar" style="width:110px;">
	        <ul class="toolBar">
	        
	        	<li><a class="icon" href="${baseUrl}/base-category/export" target="dwzExport" targetType="navTab" title="确定要导出记录吗?"><span>全量导出分类</span></a></li>
	        </ul>
	    </div>
	    
	    <div class="m_cl" style="height:25px;"></div>
	    <div class="panelBar" style="width:145px;height:auto!important;padding:5px;">
	      <b>搜索分类</b>&nbsp;&nbsp;分类名称:<br/><input type="text" id="search_cate_name" class="empty" style="margin-top:8px">
	    </div>
     </div>
  	 <div class="m_cl"></div>
   </div>
	

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
   // t.expandAll(true); 
});


</script>
