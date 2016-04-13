<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style type="text/css"> 


body { 
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 

a { 
color: #c75f3e; 
} 

#mytable { 
width:100%; 
padding: 0; 
margin: 0; 
} 

caption { 
padding: 0 0 5px 0; 
width: 100%; 
font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
text-align: right; 
} 

th { 
font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
border-top: 1px solid #C1DAD7; 
letter-spacing: 2px; 
text-transform: uppercase; 
text-align: left; 
padding: 6px 6px 6px 12px; 
background: #CAE8EA  no-repeat; 
} 

th.nobg { 
border-top: 0; 
border-left: 0; 
border-right: 1px solid #C1DAD7; 
background: none; 
} 

td { 
border-right: 1px solid #C1DAD7; 
border-bottom: 1px solid #C1DAD7; 
background: #fff; 
font-size:14px; 
padding: 6px 6px 6px 12px; 
color: #4f6b72; 
} 


td.alt { 
background: #F5FAFA; 
color: #797268; 
} 

th.spec { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #fff no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
border-left: 1px solid #C1DAD7; 
border-top: 0; 
background: #f5fafa no-repeat; 
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #797268; 
}
.newValue{color:red;}
/*---------for IE 5.x bug*/ 
html>body td{ font-size:14px;} 
body,td,th { 
font-family: 宋体, Arial; 
font-size: 14px; 
} 

		.box .btn_pass,.box .btn_notby{padding: 3px 8px 3px 23px;line-height: 18px;vertical-align: middle;border:1px solid #a4a4a4;background: #e8e8e8 url('${contextPath}/styles/product_img/add.png') no-repeat 3px 2px;border-radius: 3px;outline: none;cursor: pointer;}
		.box .btn_notby{background-image: url('${contextPath}/styles/product_img/delete.png');}

		.box .btn_pass:hover,.box .btn_notby:hover{background-color: #f6f6f6}
		.box .btn_pass:active,.box .btn_notby:active{background-color: #e9e9e9}
		.box input{float: right;margin-right: 10px;margin-top: 10px;}
</style> 
<div class="pageContent">
<form method="post" id="exmaineForm" action="${contextPath}/info/product/product_examine" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    
    	该数据为后台操作
        
    </div>
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>
<script>

	$(function(){
		
		$(".btn_notby").click(function(){
			var str = prompt("审核不通过的原因","");
			if(str!=null && str!=""){
				var src ="${contextPath}/info/product/product_examine?reason="+str;
				$("#exmaineForm").attr("action",src);
				$(".staus").val("9");
			}
		});
	})
</script>