<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<link href="${contextPath}/styles/product_css/index.css" type="text/css" rel="stylesheet">

<style>
.d_first, .d_second, .d_three{
width: 260px;
float: left;
position:inherit;
}


</style>

<div class="pageContent" style="overflow:hidden;height:500px;">
<form method="post" action="${contextPath}/info/product/upload_excel_add/2" enctype="multipart/form-data"  onsubmit="return iframeCallback(this, dialogReloadNavTab)" >
    <!-- <div class="pageFormContent" > -->
    	<div id="warp">
	<div class="title_ice02"><b>请选择分类，需要填写三级分类：</b></div>
    <div class="main_warpdiv">
        <div class="d_first" id="d_first">
            <div class="d_f_list_box">          
                <div class="d_f_list_div">                
                    <ul>
                      <li class="1">请选择一级分类</li>
                        <c:if test="${listCategory!=null }">
            			<c:forEach items="${listCategory }" var="category">
            				<li class="1"><input type="hidden" id="${category.categoryCode }" value="${category.categoryName }"/><a onclick="liClick(this);" href="#">${category.categoryName }</a></li>
            				
            			</c:forEach>
            			</c:if>
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <div class="d_second"  id='d_second'>
            <div class="d_f_list_box">
                <div class="d_f_list_div">
                    <ul id="category_second">
                           <li class="1">请选择二级分类</li>
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <div class="d_three" id='d_three' >
            <div class="d_f_list_box">
                <div class="d_f_list_div">
                    <ul id="category_three">
                         <li class="1">请选择三级分类</li>
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <input type="hidden" id="catName" name="catName" value="">
    	<input type="hidden" id="catCode" name="catCode" value="">
        <div class="title_ice"><div class="topico"></div><b id="showCate">您当前选择是：</b></div>
        
        
        <div class="exel">
            <div>
              <!--  <a id="btnDownload" href=""><input type="button"  onclick="downExcel();" value="下载新增模板" class="fontColor"/></a>  --> 
                <input type="file" id="file"  name="file" value="选择导入文件"  class="fontColor"/>&nbsp;  
            </div>
            <div class="formBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="submit">导入淘宝商品excel</button>        
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">关闭</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
    </div>
</div>
<div class="clear"></div>

    
<!-- </div> -->
</form>  
</div>

<script type="text/javascript">

function downExcel(){
	var catCode = $("#catCode").val();
	$("#btnDownload").attr("href","${contextPath}/info/product/export_product_template?catCode="+catCode);
}



var showCate = $("#showCate").html();
var firstValue = "";
var secondValue = "";
var thirdValue = "";
function liClick(element){
	//获取当前对象文本
	var str = element.parentNode.childNodes[0].id;
	var categoryCode = $("#"+str).val();
	$("#catName").val(categoryCode);
	$("#catCode").val(str);
	var	li  ;
	
	//alert($("#catName").val()+":"+$("#catCode").val());
	if(str.length==2){
		li = $('#d_first li');
		$('#d_second').show(); 
		var aj  = $.ajax({
			url : '${contextPath}/dwz/combox',  // 后台提供
			data:"code="+str+"&type=inputLi",
			type:'POST',
			cache:false,
			dataType:'text',
			success : function(data){
				//alert(data);
				$('#category_second').html(  "<li class='1'>请选择二级分类</li>"+data);
			   $('#category_three').html("<li class='1'>请选择三级分类</li>");
				//$('#d_second').show();
				$("#showCate").html("");
				firstValue = categoryCode;
				$("#showCate").html(showCate+firstValue);
				$("#catNameGroup").val(showCate+firstValue);
			},
			error : function(){
				alert("服务器异常!");
			}
		}); 
	}else if(str.length==4){
		
		li = $('#d_second li');
	//	$('#d_three').show(); 
		var aj  = $.ajax({
			url : '${contextPath}/dwz/combox',  // 后台提供
			data:"code="+str+"&type=inputLi",
			type:'POST',
			cache:false,
			dataType:'text',
			success : function(data){
				$('#category_three').html("<li class='1'>请选择三级分类</li>"+data);
				//$('#d_three').show();
				secondValue = categoryCode;
				$("#showCate").html(showCate+firstValue+" > "+secondValue);
				$("#catNameGroup").val(showCate+firstValue+" > "+secondValue);
			},
			error : function(){
				alert("服务器异常!");
			}
		}); 
	}else{
		li = $('#d_three li');
		thirdValue = categoryCode;
		$("#showCate").html(showCate+firstValue+" > "+secondValue+" > "+thirdValue);
		$("#catNameGroup").val(showCate+firstValue+" > "+secondValue+" > "+thirdValue);
	}
	
	//element.className="d_f_list_on";
	li.on("click",function(){
		$(this).addClass("d_f_list_on").siblings("li").removeClass("d_f_list_on");
	})
	//element.
	//li.each(function(){
		//$(this).attr("class","");
	//})
}

</script>