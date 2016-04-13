<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/product/css/add_product.css" rel="stylesheet" type="text/css" media="screen" />

<style type="text/css"> 
body { 
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
color: #4f6b72; 
background: #E6EAE9; 
} 

a { 
color: #c75f3e; 
} 

.mytable { 
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
.title_ice02 {
	width:100%;
	height:33px;
	font-size:16px;
	line-height:33px;
	text-intent:30px;
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
<form method="post" id="exmaineForm" action="${contextPath}/info/product/product_examine" class="required-validate pageForm" onsubmit="return examineBack(this, navTabAjaxDone);">
    <div class="pageFormContent" layoutH="58" >
    		<input type="hidden" name="staus" class="staus" value="2">
	    	<input type="hidden" name="ids" value="${infoAuthId }">
	    	<input type="hidden" id="reason" name="reason" value="" />
	    	<input type="hidden" name="submitType" value="2" />
    		<input type="hidden" id="submitType1" value="1" />
    		
    		<div id="valueDiv" style="height:800px;">
    			<div class="title_ice02"><b>基本内容</b></div>
	        	<table class="mytable" cellspacing="0">
					<tr> 
					<th scope="col">属性名称</th> 
					<th scope="col">旧值</th> 
					<th scope="col">新值</th> 
					</tr>
					<tr> 
						<td class="row">商品编码</td> 
						<td class="row">${oldProduct.productCode }</td> 
						<td class="row <c:if test="${oldProduct.productCode!=newProduct.productCode }">newValue</c:if>">${newProduct.productCode }</td>
					</tr>
					<tr> 
						<td class="row">商品名称</td> 
						<td class="row">${oldProduct.productName }</td> 
						<td class="row <c:if test="${oldProduct.productName!=newProduct.productName }">newValue</c:if>">${newProduct.productName }</td>
					</tr>
					<tr> 
						<td class="row">商品短名称</td> 
						<td class="row">${oldProduct.productOperateModel.productShortName}</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productShortName!=newProduct.productOperateModel.productShortName }">newValue</c:if>">${newProduct.productOperateModel.productShortName }</td>
					</tr>
					<tr> 
						<td class="row">商品简介</td> 
						<td class="row">${oldProduct.productDesc }</td> 
						<td class="row <c:if test="${oldProduct.productDesc!=newProduct.productDesc }">newValue</c:if>">${newProduct.productDesc }</td>
					</tr>
					<tr> 
						<td class="row">品牌名称</td> 
						<td class="row">${oldProduct.brandName }</td> 
						<td class="row <c:if test="${oldProduct.brandName!=newProduct.brandName }">newValue</c:if>">${newProduct.brandName }</td>
					</tr>
					<tr> 
						<td class="row">商品前缀</td> 
						<td class="row">${oldProduct.productOperateModel.productPrefix }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productPrefix!=newProduct.productOperateModel.productPrefix }">newValue</c:if>">${newProduct.productOperateModel.productPrefix }</td>
					</tr>
					<tr> 
						<td class="row">商品后缀</td> 
						<td class="row">${oldProduct.productOperateModel.productSuffix }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productSuffix!=newProduct.productOperateModel.productSuffix }">newValue</c:if>">${newProduct.productOperateModel.productSuffix }</td>
					</tr>
					<tr> 
						<td class="row">关键字</td> 
						<td class="row">${oldProduct.productOperateModel.productKeyword }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productKeyword!=newProduct.productOperateModel.productKeyword }">newValue</c:if>">${newProduct.productOperateModel.productKeyword }</td>
					</tr>
					<tr> 
						<td class="row">是否下架显示</td> 
						<td class="row">
						<c:if test="${oldProduct.productOperateModel.productDownShow==1 }">是</c:if>
						<c:if test="${oldProduct.productOperateModel.productDownShow==2 }">否</c:if>
						</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productDownShow!=newProduct.productOperateModel.productDownShow }">newValue</c:if>">
							<c:if test="${newProduct.productOperateModel.productDownShow==1 }">是</c:if>
							<c:if test="${newProduct.productOperateModel.productDownShow==2 }">否</c:if>
						</td>
					</tr>
					<tr> 
						<td class="row">商品视频链接</td> 
						<td class="row">${oldProduct.productOperateModel.productVideoUrl }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productVideoUrl!=newProduct.productOperateModel.productVideoUrl }">newValue</c:if>">${newProduct.productOperateModel.productVideoUrl }</td>
					</tr>
					<tr> 
						<td class="row">商品缩略图地址</td> 
						<td class="row">${oldProduct.productOperateModel.productThumbnail }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productThumbnail!=newProduct.productOperateModel.productThumbnail }">newValue</c:if>">${newProduct.productOperateModel.productThumbnail }</td>
					</tr>
					<tr> 
						<td class="row">加价率</td> 
						<td class="row">${oldProduct.productOperateModel.productPriceRate }</td> 
						<td class="row <c:if test="${oldProduct.productOperateModel.productPriceRate!=newProduct.productOperateModel.productPriceRate }">newValue</c:if>"><c:if test="${newProduct.productOperateModel.productPriceRate!=0 }">${newProduct.productOperateModel.productPriceRate }</c:if></td>
					</tr>
				</table> 
				<br>
				<div class="title_ice02"><b>商品信息</b></div>
	        	<table class="mytable" cellspacing="0">
	        		<tr> 
						<th scope="col">属性名称</th> 
						<th scope="col">旧值</th> 
						<th scope="col">新值</th> 
					</tr>
					<tr> 
						<td class="row">货号</td> 
						<td class="row">${oldProduct.productMallModel.productAtrNumber }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productAtrNumber!=newProduct.productMallModel.productAtrNumber }">newValue</c:if>">${newProduct.productMallModel.productAtrNumber }</td>
					</tr>
	        		<tr> 
						<td class="row">单品重量</td> 
						<td class="row">${oldProduct.productMallModel.productWeight }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productWeight!=newProduct.productMallModel.productWeight }">newValue</c:if>">${newProduct.productMallModel.productWeight }</td>
					</tr>
					<tr> 
						<td class="row">(包装) 长</td> 
						<td class="row">${oldProduct.productMallModel.productLong }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productLong!=newProduct.productMallModel.productLong }">newValue</c:if>">${newProduct.productMallModel.productLong }</td>
					</tr>
					<tr> 
						<td class="row">(包装) 宽</td> 
						<td class="row">${oldProduct.productMallModel.productWidth }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productWidth!=newProduct.productMallModel.productWidth }">newValue</c:if>">${newProduct.productMallModel.productWidth }</td>
					</tr>
					<tr> 
						<td class="row">(包装) 高</td> 
						<td class="row">${oldProduct.productMallModel.productHeight }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productHeight!=newProduct.productMallModel.productHeight }">newValue</c:if>">${newProduct.productMallModel.productHeight }</td>
					</tr>
					<tr> 
						<td class="row">体积</td> 
						<td class="row">${oldProduct.productMallModel.productDensity }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productDensity!=newProduct.productMallModel.productDensity }">newValue</c:if>">${newProduct.productMallModel.productDensity }</td>
					</tr>
					<tr> 
						<td class="row">产地</td> 
						<td class="row">${oldProduct.productMallModel.productProducer }</td> 
						<td class="row <c:if test="${oldProduct.productMallModel.productProducer!=newProduct.productMallModel.productProducer }">newValue</c:if>">${newProduct.productMallModel.productProducer }</td>
					</tr>
	        	</table>
	        	
	        	<br>
	        	<c:if test="${newProduct.catCode==oldProduct.catCode }">
	        		<div class="title_ice02"><b>商品属性</b></div>
		        	<table class="mytable" cellspacing="0">
		        		<tr> 
							<th scope="col">属性名称</th> 
							<th scope="col">新值</th> 
							<th scope="col">旧值</th> 
						</tr>
						
						<c:if test="${newProduct.productAttributeList!=null }">
								<c:forEach  items="${newProduct.productAttributeList }" var="changeAttribute">
			        				<tr> 
										<td class="row">${changeAttribute.productAttributeName }</td> 
										<td class="row">${changeAttribute.productAttributeValue }</td> 
										<td class="row">
										<c:if test="${oldProduct.productAttributeList!=null }">
											<c:forEach items="${oldProduct.productAttributeList }" var="oldAttribute">
												<c:if test="${changeAttribute.productAttributeCode==oldAttribute.productAttributeCode }">
													${oldAttribute.productAttributeValue }
												</c:if>
											</c:forEach>
										</c:if>
										</td>
									</tr>
		        				</c:forEach>	
						</c:if>
						
		        	</table>
	        	</c:if>
				<c:if test="${newProduct.catCode!=oldProduct.catCode }">
					<div class="title_ice02"><b>(原)商品属性</b></div>
		        	<table class="mytable" cellspacing="0">
		        		<tr> 
							<th scope="col">属性名称</th> 
							<th scope="col">属性值</th> 
						</tr>
						
						<c:if test="${oldProduct.productAttributeList!=null }">
								<c:forEach  items="${oldProduct.productAttributeList }" var="oldAttr">
			        				<tr> 
										<td class="row">${oldAttr.productAttributeName }</td> 
										<td class="row">${oldAttr.productAttributeValue }</td>
									</tr>
		        				</c:forEach>	
						</c:if>
						
		        	</table>
		        	<div class="title_ice02"><b>(现)商品属性</b></div>
		        	<table class="mytable" cellspacing="0">
		        		<tr> 
							<th scope="col">属性名称</th> 
							<th scope="col">属性值</th> 
						</tr>
						
						<c:if test="${newProduct.productAttributeList!=null }">
								<c:forEach  items="${newProduct.productAttributeList }" var="newAttr">
			        				<tr> 
										<td class="row">${newAttr.productAttributeName }</td> 
										<td class="row newValue">${newAttr.productAttributeValue }</td>
									</tr>
		        				</c:forEach>	
						</c:if>
						
		        	</table>
				</c:if>
	        	<br>
				
				
				
				<div class="title_ice02"><b>新增的SKU</b></div>
	        	<table class="mytable" cellspacing="0">
	        		<tr> 
						<th scope="col">SKU编号</th> 
						<th scope="col">
							<c:choose>
								<c:when test="${colorName!=null && colorName!='' }">
										${colorName }
								</c:when>
								<c:otherwise>
										颜色
								</c:otherwise>
							</c:choose>
						</th> 
						<th scope="col">吊牌颜色</th>
						<th scope="col">
							<c:choose>
									<c:when test="${specName!=null && specName!='' }">
										${specName }
									</c:when>
									<c:otherwise>
										尺码
									</c:otherwise>
								</c:choose>
						</th> 
						<th scope="col">吊牌尺码</th> 
						<th scope="col">供货价</th> 
						<th scope="col">市场价</th> 
						<th scope="col">销售价</th> 
						<th scope="col">换购特币</th>
						<th scope="col">库存</th>
						<th scope="col">是否主SKU</th>
						<th scope="col">供应商SKU编号</th>
					</tr>
					<tr>
						<c:if test="${insertListSku!=null }">
							<c:forEach items="${insertListSku }" var="sku">
								<td>${sku.productSkuCode }</td>
								<td>${sku.attrColorValue }</td>
								<td>${sku.colorValueAlias }</td>
								<td>${sku.attrSpecValue }</td>
								<td>${sku.specValueAlias }</td>
								<td>${sku.productPriceModel.costPrice }</td>
								<td>${sku.productPriceModel.marketPrice }</td>
								<td>${sku.productPriceModel.tshPrice }</td>
								<td>${sku.productPriceModel.tb }</td>
								<td>${sku.productStockModel.virtualStock }</td>
								<td>${sku.mainSku }</td>
								<td>${sku.supplierSkuCode }</td>
							</c:forEach>
						</c:if>
					</tr>
				</table>
				<br>
				 <div class="j_div">
        			<ul>
            	<li class="textintentdiv">商品描述 电脑版</li>
                <li>
                	<div class="check_link">
                    	<ul>
                        	<li class="check_orange" id="check_on"><a href="javascript:void(0)">新值</a></li>
                            <li class="check_off" id="check_off"><a href="javascript:void(0)">旧值</a></li>
                        </ul>
                    </div>
                    <div class="check_textare" id="pc_txt">
                    	<textarea class="editor"  id="newPcProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi">
							</textarea>
                    </div>
                    <div class="check_textare" style="display:none;" id="phone_txt">
                    	<textarea class="editor" style="width:100%;" id="oldPcProductInfo" rows="15" cols="90"
							upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
							upImgUrl="${contextPath}/dwz/editor_upload?attrName=app&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png"
							upFlashUrl="${contextPath}/dwz/editor_upload?attrName=app&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
							upMediaUrl="${contextPath}/dwz/editor_upload?attrName=app&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi" >
						</textarea>
                    </div>
                </li>
            </ul>
        </div>
        
        <div class="j_div">
        			<ul>
            	<li class="textintentdiv">商品描述 手机版</li>
                <li>
                	<div class="check_link1">
                    	<ul>
                        	<li class="check_orange1" id="check_on1"><a href="javascript:void(0)">新值</a></li>
                            <li class="check_off1" id="check_off1"><a href="javascript:void(0)">旧值</a></li>
                        </ul>
                    </div>
                    <div class="check_textare1" id="pc_txt1">
                    	<textarea class="editor" style="width:100%"  id="newAppProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi">
							</textarea>
                    </div>
                    <div class="check_textare1" style="display:none;" id="phone_txt1">
                    	<textarea class="editor" style="width:100%" id="oldAppProductInfo" rows="15" cols="90"
							upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
							upImgUrl="${contextPath}/dwz/editor_upload?attrName=app&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png"
							upFlashUrl="${contextPath}/dwz/editor_upload?attrName=app&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
							upMediaUrl="${contextPath}/dwz/editor_upload?attrName=app&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi" >
						</textarea>
                    </div>
                </li>
            </ul>
        </div>
        
        <textarea id="newPcInfo" style="display:none;">${newProduct.productInfoModel.pcProductInfo }</textarea>
    	<textarea id="oldPcInfo" style="display:none;">${oldProduct.productInfoModel.pcProductInfo }</textarea>
    	
    	<textarea id="newAppInfo" style="display:none;">${newProduct.productInfoModel.appProductInfo }</textarea>
    	<textarea id="oldAppInfo" style="display:none;">${oldProduct.productInfoModel.appProductInfo }</textarea>
	        	
				<br>
        	</div>
    </div>
    <c:choose>
					<c:when test="${status=='0' || status=='1'}">
						<div class="box">
							<input  type="submit" value="审核通过" class="btn_pass">
							<input type="submit"   value="审核不通过" class="btn_notby">
						</div>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>
<script>
	
$(document).ready(function() {
	
	$("#newPcProductInfo").html($("#newPcInfo").html());
	$("#oldPcProductInfo").html($("#oldPcInfo").html());
	
	$("#newAppProductInfo").html($("#newAppInfo").html());
	$("#oldAppProductInfo").html($("#oldAppInfo").html());
	
    $('#check_on').click(function(){
		$('#check_on').removeClass("check_off");
		$('#check_on').addClass("check_orange");
		$('#check_off').removeClass("check_orange");
		$('#check_off').addClass("check_off");
		$("#pc_txt").show(500);
		$("#phone_txt").hide();
	});
	$('#check_off').click(function(){
		$('#check_on').removeClass("check_orange");
		$('#check_on').addClass("check_off");
		$('#check_off').removeClass("check_off");
		$('#check_off').addClass("check_orange");
		$("#pc_txt").hide();
		$("#phone_txt").show(500);
	});
	
	 $('#check_on1').click(function(){
			$('#check_on1').removeClass("check_off1");
			$('#check_on1').addClass("check_orange1");
			$('#check_off1').removeClass("check_orange1");
			$('#check_off1').addClass("check_off1");
			$("#pc_txt1").show(500);
			$("#phone_txt1").hide();
		});
		$('#check_off1').click(function(){
			$('#check_on1').removeClass("check_orange1");
			$('#check_on1').addClass("check_off1");
			$('#check_off1').removeClass("check_off1");
			$('#check_off1').addClass("check_orange1");
			$("#pc_txt1").hide();
			$("#phone_txt1").show(500);
		});
});
	
	$(function(){
		$(".btn_notby").click(function(){
			$("#submitType1").val("2");
		});
		$(".btn_pass").click(function(){
			$("#submitType1").val("1");
		});
	})
	
	function examineBack(form, callback, confirmMsg){
		if($("#submitType1").val()==1){
		}else{
			var str = prompt("审核不通过的原因","");
			if(str!=null && str!=""){
				$(".staus").val("8");
				$("#reason").val(str);
			}else{
				return false;
			}
		}
		
		var $form = $(form);
		  if (!$form.validationEngine('validate')) {
		    return false;
		  }
		  var _submitFn = function () {
		    $.ajax({
		      type: form.method || 'POST',
		      url: $form.attr('action'),
		      data: $form.serializeArray(),
		      dataType: 'json',
		      cache: false,
		      success: callback || DWZ.ajaxDone,
		      error: DWZ.ajaxError
		    });
		  }
		  if (confirmMsg) {
		    alertMsg.confirm(confirmMsg, {
		      okCall: _submitFn
		    });
		  } else {
		    _submitFn();
		  }
		  return false;
	}
</script>