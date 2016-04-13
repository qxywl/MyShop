<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/product/css/category.css" rel="stylesheet" type="text/css" media="screen" />

<div class="pageContent" style="overflow:hidden;height:500px;">

    <!-- <div class="pageFormContent" > -->
    <div id="warp">
	<div class="title_ice02"><b>请选择类型：</b></div>
    <div class="main_warpdiv">
        <div class="d_first" id="d_first">
            <div class="d_f_list_box">
                <div class="d_f_list_div">
                    <ul>
                        <c:if test="${listCategory!=null }">
            			<c:forEach items="${listCategory }" var="category">
            				<li class="1"><input type="hidden" id="${category.categoryCode }" value="${category.categoryName }"/><a onclick="liClick(this);" href="#">${category.categoryName }</a><div class="rightico"></div></li>
            				
            			</c:forEach>
            			</c:if>
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <div class="d_second" style="display:none;" id='d_second'>
            <div class="d_f_list_box">
                <div class="d_f_list_div">
                    <ul id="category_second">
                        
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <div class="d_three" id='d_three' style="display:none;">
            <div class="d_f_list_box">
                <div class="d_f_list_div">
                    <ul id="category_three">
                        
                    </ul>
                </div>
            </div>
        </div>
        <!---->
        <input type="hidden" id="catName" name="catName" value="">
    	<input type="hidden" id="catCode" name="catCode" value="">
    	<input type="hidden" id="catNameGroup" name="catNameGroup" value="">
        <div class="title_ice"><div class="topico"></div><b id="showCate">您选择目录是：</b></div>
        
        
        <div class="exel">
            <div class="formBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <a id="toAddProduct" target="navTab"  href=""><button onclick="quedinCate();" type="button">确定</button>   </a>     
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
</div>
<script>

function quedinCate(){
	var catCode = $("#catCode").val();
	var catName = $("#catName").val();
	var catNameGroup = $("#catNameGroup").val();
	$("#toAddProduct").attr("href","${contextPath}/info/product/to_add_product?catCode="+catCode+"&catName="+catName+"&catNameGroup="+catNameGroup);
	
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
				$('#category_second').html(data);
				$('#category_three').html("");
				$('#d_second').show();
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
		$('#d_three').show(); 
		var aj  = $.ajax({
			url : '${contextPath}/dwz/combox',  // 后台提供
			data:"code="+str+"&type=inputLi",
			type:'POST',
			cache:false,
			dataType:'text',
			success : function(data){
				$('#category_three').html(data);
				$('#d_three').show();
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
	li.on("click",function(){
		$(this).addClass("d_f_list_on").siblings("li").removeClass("d_f_list_on");
	})
}
</script>