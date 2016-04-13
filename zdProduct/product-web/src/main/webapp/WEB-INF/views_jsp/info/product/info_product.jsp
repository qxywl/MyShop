<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/product/css/add_product.css" rel="stylesheet" type="text/css" media="screen" />
<script src="${contextPath}/styles/product/js/info_product.js" type="text/javascript"></script>
<link href="${contextPath}/styles/product/css/category.css" rel="stylesheet" type="text/css" media="screen" />


<style>
/*--内边框-*/
* {
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}

.hide, .hidden{display:none;}
/* body{
	font:12px/24px,Arial,sans-serif;
	text-decoration:none;
	设置轮廓
	outline:none;
	text-align:left;
	background-color:#f5f9ff;
	支持IE,FF超出自动换行
	word-wrap:break-word;
	height:100%;
	overflow:hidden;
	clear:both;
} */
/*abbr,acronym首字母缩写（<abbr title="United Nations">U.N.</abbr>）*/
fieldset,img,a img,abbr,acronym{
	border:0;
}

ul,li,ol{
	list-style:none;
}

abbr,acronym{
	font-variant:normal;
}

q:before,q:after{
	content:'';
}

blockquote,q{
	quotes:"" "";
}

:focus{
	outline:0;
}

/* 重置，减少对行高的影响 */
sup{
	vertical-align:text-top;
}
sub{
	vertical-align:text-bottom;
}
/* for ie6 */
legend{
	color:#000;
}
/*----------强行换行----------*/ 
p{
	word-wrap:break-word;
}

blockquote:before,blockquote:after{
	content:"";
}


#warp {
	width:auto !important;
	/* min-width:500px;
	min-height:300px; */
	height:auto !important;
	
	
	padding:20px;
}
.d_first,.d_second,.d_three {
	width:228px;
	height:240px;
	position:absolute;
	border:#d5e5fb solid 1px;
	background:#fff;
	overflow:auto;/*多余设置滚动条*/
}
.d_second {
	left:250px;
}
.d_f_search {
	width:100%;
	height:28px;
	background:#f5f9ff;
}
.d_f_search_div {
	width:95%;
	height:24px;
	background:#fff;
	border:#d5e5fb solid 1px;
	margin:2px auto 2px auto;
}
.d_f_input {
	background:none;
	border:none;
	text-indent:24px;
}
.d_f_list_box {
	width:90%;
	height:auto !important;
	min-height:50px;
	margin:0 auto;
	padding-top:10px;
}
.d_f_list_box div {
	font-size:12px;
	line-height:24px ;
	height:24px ;
	color:#404040;
	text-indent:15px;
}
.d_f_blue { color:#3266cb;}
.d_f_list_box div a {
	color:#404040;
	text-decoration:none;
	display:block;
	border:1px dashed #fff;
	line-height:24px
}
.d_f_list_box div a:hover {
	background:#84bde1;
	color:#fff;
	border:1px dashed #dff1fb;
}
.d_f_list_box div a:active {
	background:#dff1fb;
	color:#000;
	border:1px dashed #84bde1;
}
.d_f_list_on {
	background:#dff1fb;
	color:#000;
	border:1px dashed #84bde1;
}
.right_ico {
	width:14px;
	height:14px;
	position:absolute;
	right:0;
}
.right_ico img {
	width:14px;
	height:14px;
	background-size:100%;
}
.dashedline {
	width:100%;
	height:2px;
	border-top:1px dashed #c9c9cb;
	margin-top:5px;
}
.clear { clear:both;}
.d_f_list_div {
	width:100%;
	height:auto !important;
	min-height:50px;
}
.span_num {
	width:22px;
	height:15px;
	background:#ababab;
	color:#fff;
	text-align:center;
	padding:2px 4px 2px 4px;
	margin-right:5px;
}
.d_three {
	left:480px;
}
.fdj {
	width:22px;
	height:19px;
	position:absolute;
}
.title_ice,.title_ice02 {
	width:95%;
	height:33px;
	background:#fffbf3;
	border:#ffdcab solid 1px;
	position:absolute;
	font-size:16px;
	line-height:33px;
	text-indent:24px;
}
.title_ice {
	margin-top:250px;
}
.title_ice02 {
	margin-top:5px;
}
.topico {
	width:14px;
	height:7px;
	background:url(/product-web/styles/product_img/topico.png) no-repeat;
	position:absolute;
	margin-left:50px;
	margin-top:-7px;
}
.rightico {
	width:14px;
	height:14px;
	
	background:url(/product-web/styles/product_img/right_ico.png) no-repeat;
	position:absolute;
	right:10px;
	margin-top:-22px;
}
.main_warpdiv {
	width:100%;
	height:auto !important;
	margin-top:45px;
}
.exel {
	width:95%;
	height:auto !important;
	position:absolute;
	margin-top:290px;
}




.code_tab table tr td{
	width:7%;
}

.colorpop_category{
	width:800px;
	height:400px;
	min-height:400px;
	background:#edf3f5;
	border:#b9d1d7 solid 1px;
	border-radius:10px;
	position:absolute;
	left:35%;
	top:40%;
	z-index:999;
	margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
	margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
	margin-top:0px;
	position:fixed!important;/* FF IE7*/
	position:absolute;/*IE6*/
	_top:       expression(eval(document.compatMode &&
				document.compatMode=='CSS1Compat') ?
				documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
				document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/

}
.colorpop_bg {
	width:800px;
	height:auto !important;
	_height:260px;
	min-height:260px;
	background:#edf3f5;
	border:#b9d1d7 solid 1px;
	border-radius:10px;
	position:absolute;
	left:35%;
	top:40%;
	z-index:999;
	margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
	margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
	margin-top:0px;
	position:fixed!important;/* FF IE7*/
	position:absolute;/*IE6*/
	_top:       expression(eval(document.compatMode &&
				document.compatMode=='CSS1Compat') ?
				documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
				document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
.colorpop_top {
	height:36px;
	line-height:36px;
	text-indent:10px;
	border-bottom:#b9d1d7 solid 1px;
}
.colorpop_main {
	width:98%;
	height:auto !important;
	_height:200px;
	min-height:200px;
	margin:10px auto 10px auto;
	border:#b9d1d7 solid 1px;
}
.closepop {
	float:right;
	margin:10px 10px 0 0;
}
.popbtndiv {
	height:40px;
	margin:120px auto 10px 20px;
}

.rulepop_div {
	width:100%;
	height:30px;
	line-height:30px;
	background:#fff;
	border-bottom:#d1d1d2 solid 1px;
	margin-bottom:20px;
}
.rulepop_div ul li {
	float:left;
	width:auto;
	min-width:100px;
	height:30px;
	border-bottom:#d1d1d2 solid 1px;
	background:#b9d1d7;
	margin-right:1px;
}
.rulepop_div ul li a { text-align:center; display:block; margin:0; padding-right:10px;}
.rulepop_div ul li a:hover {
	background:#edf3f5;
	color:#000;
}
.rulepopwarp {
	width:100%;
	height:auto !important;
	_height:140px;
	min-height:140px;
}
.rulepopwarp ul li {
	float:left;
	width:20%;
	height:40px;
}
.ruleon,.ruleon a {
	background:#b9d1d7;
	color:#000;
	height:30px;
	line-height:30px;
}
.ruleon a:hover {
	background:#edf3f5;
	color:#000;
}
.ruleoff,.ruleoff a {
	background:#d7640f;
	color:#fff;
	height:30px;
	line-height:30px;
}

.pageFormContent label {
	width:auto;
}
</style>
<form method="post" action="${contextPath}/info/product/update" enctype="multipart/form-data" onsubmit="return checkProduct(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    	
    	<div id="input_warp">
    		<input type="hidden" name="productId" value="${productModel.productId }" />
    		
    		<input type="hidden" name="productOperateModel.productOperateId" value="${productModel.productOperateModel.productOperateId }" />
    		<input type="hidden" name="productOperateModel.productId" value="${productModel.productOperateModel.productId }" />
    		
    		<input type="hidden" name="productMallModel.productMallId" value="${productModel.productMallModel.productMallId }" />
    		<input type="hidden" name="productMallModel.productId" value="${productModel.productMallModel.productId }" />
    		
    		<input type="hidden" name="productInfoModel.productId" value="${productModel.productInfoModel.productId }" />
    		<input type="hidden" name="productInfoModel.productInfoId" value="${productModel.productInfoModel.productInfoId }" />
		<div id="input_main">
		<!--<div class="title_ice02"><b>${catNameGroup }</b></div> -->
		<div class="j_divtitle" style="border:#ffdcab solid 1px; font-size:16px;width:100%; min-height:33px;">
			<ul>
				<li style="height:33px; line-height:33px; text-indent:20px;">
					<b id="categoryText">类目：${productModel.catName1 }>>${productModel.catName2 }>>${productModel.catName }</b>
					<input type="hidden" id="catName" name="catName" value="${productModel.catName }" />
					<input type="hidden" id="catCode" name="catCode" value="${productModel.catCode }" />
					
					<input type="hidden" id="c_catName" value="" />
					<input type="hidden" id="c_catCode" value="" />
					<span style="float:right;"><input type="button" onclick="showCategory();" value="修改分类" /></span>
				</li>
			</ul>
		</div>
    	<div class="j_div">
        	<ul>
            	<li class="textintentdiv">基本内容编辑（*表示必填）</li>
                <li>
                	<div class="codemange">
                    	<ul>
                        	<li class="code_l_text">*商品编号：</li>
                            <li class="code_l_m"><input type="text" name="productCode" value="${productModel.productCode }" readonly="readonly" class="productCode pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                	<div class="codemange">
                    	<ul>
                        	<li class="code_l_text">*商品名称：</li>
                            <li class="code_l_m"><input type="text" name="productName" value="${productModel.productName }" class=" productName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品短名称：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productShortName" value="${productModel.productOperateModel.productShortName }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">豆豆点评：</li>
                            <li class="code_l_m"><input type="text" name="productDesc" value="${productModel.productDesc }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">供应商：</li>
                            <li class="code_l_m">
                            <select name="supplierId"  >
                            <c:forEach items="${shopList }" var="supplier">
                    			<option value="${supplier.id }" <c:if test="${productModel.supplierId==supplier.id }">selected="selected"</c:if>  >${supplier.name }</option>
                    			</c:forEach>
                            </select>
                            </li> 
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    
                     <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品类型：</li>
                            <li class="code_l_m">
                                 <select  id="protype" name="type"  onchange="tmlg();">
                            		<option <c:if test="${productModel.type=='01' }">selected="selected"</c:if> value="01">特卖</option>
                            		<option <c:if test="${productModel.type=='02' }">selected="selected"</c:if> value="02">零元购</option>
                            	</select></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="t01" class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品特卖限制数量：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.userMaxNum" value="${productModel.productOperateModel.userMaxNum }" class="productShortName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="t02" class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品特卖限制周期：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.userMaxType" value="${productModel.productOperateModel.userMaxType }"  >天</li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="l01" class="codemange" style="display:none;" >
                    	<ul>
                        	<li class="code_l_text">零元购每日限购数量：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.zeroMaxCount" value="${productModel.productOperateModel.zeroMaxCount }" class="productShortName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">品牌：</li>
                            <li class="code_l_m">
	                            <input class="required brandName"  readonly="true" name="productModel.brandName" value="${productModel.brandName }" type="text" />
								<a class="btnLook" href="${contextPath}/dwz/selectback" lookupGroup="productModel">品牌列表</a>
								<input  name="productModel.brandCode" value="${productModel.brandCode }"  type="hidden"/>
							</li>
                        </ul>
                    </div>
                    
                    <input type="hidden" value="${productModel.supplierName }" readonly="readonly" class="pageinput">
                    <input type="hidden" name="supplierId" value="${productModel.supplierId }" readonly="readonly" class="pageinput">
                    <input type="hidden" name="shopId" value="${productModel.shopId }" readonly="readonly" class="pageinput">
                    <div style="display:none;"  >
                    <input type="text" name="productOperateModel.productVideoUrl" value="${productModel.productOperateModel.productVideoUrl }" class="productVideoUrl pageinput">
                    <input type="file" onchange="setImagePreview3(this);" name="thumbnailFile" multiple="multiple" value="图片上传"  accept="image/*">
                            	<div class="thumbnailDiv" style="width:15%;height:200px;border:#e0e0e0 solid 1px;margin-left: 226px;">
                            		<img class="thumbnailImg" style="width:100%;height:100%;" src="${productModel.productOperateModel.productThumbnail }"/>
                            		<input type="hidden" name="thumbnailFileName" id="thumbnailFileName" value="" />
                            	</div>
                    </div>        	
                    <input type="hidden" name="productOperateModel.isExpensive" value ="2" />
                    <input type="hidden" value="0" name="productOperateModel.productPriceRate"   id="priceRate" class="pageinput">
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品前缀：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productPrefix" value="${productModel.productOperateModel.productPrefix }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品后缀：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productSuffix" value="${productModel.productOperateModel.productSuffix }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品关键字：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productKeyword" value="${productModel.productOperateModel.productKeyword }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品排序号：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productLevel" value="${productModel.productOperateModel.productLevel }" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                     <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">是否下架显示：</li>
                            <li class="code_l_m">
                            	<select name="productOperateModel.productDownShow">
                            		<option <c:if test="${productModel.productOperateModel.productDownShow==2 }">selected="selected"</c:if> value="2">否</option>
                            		<option <c:if test="${productModel.productOperateModel.productDownShow==1 }">selected="selected"</c:if> value="1">是</option>
                            	</select>
                            </li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<ul>
            	<li class="textintentdiv">商品信息</li>
                <li>
                	<div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">货号：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productAtrNumber" value="${productModel.productMallModel.productAtrNumber }" class="pageinput"></dt>
                           		<dt class="code_buss_warm"></dt>
                            </li>
                             <li class="code_bussmain">
                            	<dt class="code_buss_txt">产地：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productProducer" value="${productModel.productMallModel.productProducer }" class="pageinput productProducer" /></dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）长：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productLong" value="${productModel.productMallModel.productLong }" class="pageinput productLong" /></dt>
                                <dt class="code_buss_warm">mm</dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）宽：</dt>
                                <dt class="code_buss_input"><input type="text"  name="productMallModel.productWidth" value="${productModel.productMallModel.productWidth }" class="pageinput productWidth"></dt>
                            </li>
                        </ul>
                    </div>
                  	<div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）高：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productHeight" value="${productModel.productMallModel.productHeight }" class="pageinput productHeight" /></dt>
                                <dt class="code_buss_warm">mm</dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">体积：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productDensity" value="${productModel.productMallModel.productDensity }" class="pageinput productDensity" /></dt>
                                <dt class="code_buss_warm">cm3</dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">单品毛重量：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productWeight" value="${productModel.productMallModel.productWeight }" class="pageinput productWeight" /></dt>
                            	<dt class="code_buss_warm">kg</dt>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<div id="jsonValue" style="display:none;">${listAttr}</div>
        	<div id="jsonValue2" style="display:none;">${listAttr2}</div>
        	
        	<ul id="attrUl">
            	<li class="textintentdiv">商品属性</li>
                <li>
                	<%-- <c:if test="${listAttr!=null }">
                		<c:forEach items="${listAttr }" var="attr">
                			<c:if test="${attr.attrDesign==1}">
                				<div class="codemange">
			                    	<ul>
			                        	<li class="code_l_text">*类型：</li>
			                            <li class="code_buss_input">
			                            </li>
			                        </ul>
			                    </div>
                			</c:if>
                		</c:forEach>
                	</c:if> --%>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<ul>
            	<li class="textintentdiv">销售属性</li>
                <li>
                    <div class="check_textare" style="display:none;" id="custom_txt">
                    	
                    </div>
                    
                    <div class="code_tab" style="height:35px;">
                    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                          	<td>SKU编号</td>
                            <td><c:choose>
                                    <c:when test="${attrColorName!=null && attrColorName!='' }">
										${attrColorName }
										<input type="hidden" name="attrColorName" value="${attrColorName }" />
									</c:when>
									<c:otherwise>
										颜色
										<input type="hidden" name="attrColorName" value="颜色" />
									</c:otherwise>
									</c:choose>
                            </td>
                            <td>吊牌颜色</td>
                            <td><c:choose>
									<c:when test="${attrSpecName!=null && attrSpecName!='' }">
										${attrSpecName }
										<input type="hidden" name="attrSpecName" value="${attrSpecName }" />
									</c:when>
									<c:otherwise>
										
										<input type="hidden" name="attrSpecName" value="型号" />
									</c:otherwise>
								</c:choose></td>
                            <td>吊牌型号</td>
                            <td>*市场价</td>
                            <td>销售价</td>
                            <td>*库存</td>
                            <td>*是否主SKU</td>
                            <td>供应商SKU编号</td>
                            <td><input type="button" name="button" value="添加其他" id="ordelbtn" onclick="addOther();" ></td>
                          </tr>
                        </table>
                    </div>
                    <div class="code_tab">
                    	<table id="skukk" width="100%" border="0" style="clear:float;" cellspacing="0" cellpadding="0">
                    		<c:if test="${listSku!=null }">
                    		<c:forEach items="${listSku }" var="sku">
                    			<tr id="aa${sku.productSkuId }" >
                    				<td><input type="text" name="priceProductSkuCode" value="${sku.productSkuCode }" size="12" readonly="readonly" /></td>
                    				<td>
                    					<input type="text" name="priceColorValue" onfocus="showPop(${sku.productSkuId});"  id="color${sku.productSkuId }" class="priceColorValue" value="${sku.attrColorValue }" size="10"  />
                    					<input type="hidden" name="priceProductSkuId" value="${sku.productSkuId }" />
                    					<input type="hidden" name="priceId" value="${sku.productPriceModel.productPriceId }" />
                    					<input type="hidden" name="stockId" value="${sku.productStockModel.stockId }" />
                    				</td>
	                    			<td><input  name="priceColorValueAlias" class="colorAlias" id="diaocolor${sku.productSkuId }" onblur="colorChange(${sku.productSkuId });"  value="${sku.colorValueAlias }" size="10"  type="text"></td>
	                    			<td><input name="priceSpecValue" onfocus="showPopsize(${sku.productSkuId});"   class="priceSpecValue" value="${sku.attrSpecValue }" id="size${sku.productSkuId }" size="10"  type="text" ></td>
	                    			<td><input size="10" class="specAlias" name="priceSpecValueAlias" value="${sku.specValueAlias }" onblur="sizeChange(${sku.productSkuId });" id="diaosize${sku.productSkuId }" type="text"></td>
	                    			<td><input name="priceMarketPrice" value="${sku.productPriceModel.marketPrice }" class="marketPrice skuMarketPrice" size="10"   type="text"></td>
	                    			<td><input name="priceTshPrice" value="${sku.productPriceModel.tshPrice }" class="skuTshPrice" size="10"   type="text"></td>
	                    			<td><input name="priceVirtualStock" value="${sku.productStockModel.virtualStock }" class="skuStock" size="10" type="text"></td>
	                    			<td>
	                    			<select name="priceMainSku" id="${sku.productSkuId }" class="mainSku" onclick="mainChange(this);">
	                    			
	                    				<option <c:if test="${sku.mainSku==1 }">selected="selected"</c:if> value="1">是</option>
	                    				<option <c:if test="${sku.mainSku==0 }">selected="selected"</c:if> value="0">否</option></select>
	                    			</td>
	                    			<td><input name="priceSupplierSkuCode" value="${sku.supplierSkuCode }" size="10" type="text"></td>
	                    			<td><input value="删除" onclick="deleteSkua(this);" id="${sku.productSkuId }" type="button"></td>
                    			</tr>
                    		</c:forEach>
                    		</c:if>
                    	</table>
                    </div>
                    <div class="code_tab" id="div_table">
                    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	</table>
                    </div>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<div id="imgJson" style="display:none;">${listSkuJson }</div>
        	<ul>
            	<li class="textintentdiv">图片管理</li>
                <li id="imgManager">
                
                </li>
                <li id="box_img"></li>
                <div style="clear:both;"></div>
            </ul>
        </div>
        
        <div class="j_div">
        	<ul>
            	<li class="textintentdiv">商品描述</li>
                <li>
                	<div class="check_link">
                    	<ul>
                    	    <li class="check_orange" id="check_off"><a href="javascript:void(0)">手机版</a></li>
                        	<li class="check_off" id="check_on"><a href="javascript:void(0)">电脑版</a></li>
                        </ul>
                    </div>
                    <div class="check_textare" style="display:none;" id="pc_txt">
                    	<textarea class="editor" style="width:100%;" name="productInfoModel.pcProductInfo" id="pcProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi">
							</textarea>
                    </div>
                    <div class="check_textare" id="phone_txt">
                    	<textarea class="editor" style="width:100%;" id="appProductInfo" name="productInfoModel.appProductInfo" rows="15" cols="90"
							upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
							upImgUrl="${contextPath}/dwz/editor_upload?attrName=app&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png"
							upFlashUrl="${contextPath}/dwz/editor_upload?attrName=app&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
							upMediaUrl="${contextPath}/dwz/editor_upload?attrName=app&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi" >
						</textarea>
                    </div>
                </li>
            </ul>
        </div>
        <input type="hidden" value="" id="index"/>
        <input type="hidden" value="" id="indexa"/>
        <div class="main_warp_main">
        <input type="hidden"  id="imgxz" />
        	<button type="submit">提交售卖审核</button>
            <button type="button" class="close">取消</button>
        </div>
    </div>
</div>
    </div> 
    <!--颜色弹出-->
    <div class="colorpop_bg" id="colorPop" style="display:none;">
    	<div class="colorpop_top">
        	颜色选择
        </div>
        <div class="colorpop_main" id="pop_color" >
        	<div class="rulepop_div">
                	<ul>
                    	<li onClick="showTab('tabc',1,5);" id="tabc1" class="ruleoff"><a href="#">颜色</a></li>
                        <li onClick="showTab('tabc',2,5);" id="tabc2" class="ruleon"><a href="#">口味</a></li>
                        <li onClick="showTab('tabc',3,5);" id="tabc3" class="ruleon"><a href="#">香味</a></li>
                        <li onClick="showTab('tabc',4,5);" id="tabc4" class="ruleon"><a href="#">功能</a></li>
                        <li onClick="showTab('tabc',5,5);" id="tabc5" class="ruleon"><a href="#">材质</a></li>
                    </ul>
                </div>
                <div class="rulepopwarp" id="tabcContent1">
                	<ul>
                		<c:if test="${color1!=null }">
                			<c:forEach items="${color1 }" var="color">
                				<label>
		                    		<li><input type="radio" value="${color }" name="radio" id="radio18">${color }</li>
		                        </label>        
                			</c:forEach>
                		</c:if>
                    </ul>
                </div>
               
                <div id="tabcContent2" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${color2!=null }">
	                			<c:forEach items="${color2 }" var="color">
	                				<label>
			                    		<li><input type="radio" value="${color }" name="radio" id="radio18">${color }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>

               <div id="tabcContent3" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${color3!=null }">
	                			<c:forEach items="${color3 }" var="color">
	                				<label>
			                    		<li><input type="radio" value="${color }" name="radio" id="radio18">${color }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>
                <div id="tabcContent4" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${color4!=null }">
	                			<c:forEach items="${color4 }" var="color">
	                				<label>
			                    		<li><input type="radio" value="${color }" name="radio" id="radio18">${color }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>
                <div id="tabcContent5" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${color5!=null }">
	                			<c:forEach items="${color5 }" var="color">
	                				<label>
			                    		<li><input type="radio" value="${color }" name="radio" id="radio18">${color }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>
            
          <div class="popbtndiv">
          	<input type="button" onclick="getColor();" name="button2" id="popbtn2" value="确定">
          	<input type="button" onclick="closePop();" value="关闭">
          </div>
          
        </div>
           
    </div> 
    
    <!-- 尺码弹出 -->
    <div class="colorpop_bg" id="rulepop" style="display:none;">
    	<div class="colorpop_top">
        	尺码选择
            
        </div>
        <div class="colorpop_main" id="sizePop">
        	<div class="textintentdiv">
            	<div class="rulepop_div">
                	<ul>
                    	<li onClick="showTab('tab',1,5);" id="tab1" class="ruleoff"><a href="#">尺寸/规格</a></li>
                        <li onClick="showTab('tab',2,5);" id="tab2" class="ruleon"><a href="#">套数</a></li>
                        <li onClick="showTab('tab',3,5);" id="tab3" class="ruleon"><a href="#">净重</a></li>
                        <li onClick="showTab('tab',4,5);" id="tab4" class="ruleon"><a href="#">清洁养护（容量）</a></li>
                        <li onClick="showTab('tab',5,5);" id="tab5" class="ruleon"><a href="#">功能</a></li>
                    </ul>
                </div>
                <div class="rulepopwarp" id="tabContent1">
                	<ul>
                		<c:if test="${size1!=null }">
                			<c:forEach items="${size1 }" var="size">
                				<label>
		                    		<li><input type="radio" value="${size }" name="radio" id="radio25">${size }</li>
		                        </label>        
                			</c:forEach>
                		</c:if>
                    </ul>
                </div>
               
                <div id="tabContent2" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${size2!=null }">
	                			<c:forEach items="${size2 }" var="size">
	                				<label>
			                    		<li><input type="radio" value="${size }" name="radio" id="radio25">${size }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>
                <div id="tabContent3" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                        	<c:if test="${size3!=null }">
	                			<c:forEach items="${size3 }" var="size">
	                				<label>
			                    		<li><input type="radio" value="${size }" name="radio" id="radio25">${size }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                </div>
                <div id="tabContent4" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                            <c:if test="${size4!=null }">
	                			<c:forEach items="${size4 }" var="size">
	                				<label>
			                    		<li><input type="radio" value="${size }" name="radio" id="radio25">${size }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <div id="tabContent5" style="display:none;">
                	<div class="rulepopwarp">
                        <ul>
                            <c:if test="${size5!=null }">
	                			<c:forEach items="${size5 }" var="size">
	                				<label>
			                    		<li><input type="radio" value="${size }" name="radio" id="radio25">${size }</li>
			                        </label>
	                			</c:forEach>
	                		</c:if>
                        </ul>
                    </div>
                    <div style="clear:both;"></div>
                </div>
            </div>
			
          <div class="popbtndiv" style="clear:left;">
	          <input type="button" name="button2" id="popbtn3" onclick="getSize();" value="确定">
	          <input type="button" onclick="closePopSize();" value="关闭">
          </div>
           
        </div>
        <div style="clear:both;"></div>
       
    </div>  
    <!-- 类目弹出 -->
    <div class="colorpop_category" id="category" style="display:none;">
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
    	<input type="hidden" id="catNameGroup" name="catNameGroup" value="">
        <div class="title_ice"><div class="topico"></div><b id="showCate">您选择目录是：</b></div>
        
        
        <div class="exel">
            <!-- <div>
                <a id="btnDownload" href=""><input type="button"  onclick="downExcel();" value="下载模板" class="fontColor"/></a> 
                <input type="file" id="file"  name="file" value="选择文件"  class="fontColor"/>&nbsp;  
            </div> -->
            <div class="formBar">
                <ul>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button onclick="quedinCate();" type="button">确定</button> 
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" onclick="closeCategory();" >关闭</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
    </div>
</div>
    </div>
    
      
    <input type="hidden" value="" id="index"/>  
    <input type="hidden" id="requiredAttrSum" value=""/>
    <textarea id="appInfo" style="display:none;">${productModel.productInfoModel.appProductInfo }</textarea>
    <textarea id="pcInfo" style="display:none;">${productModel.productInfoModel.pcProductInfo }</textarea>
</form>
<script>
var qitasz=2;
var qitacl=2;
    function   testaa(){
    	var aa= $("#ul_size").html();
    	aa=aa+'<div><ul><label><li><input type="checkbox"   onclick="showDiv();"  value="其他尺码'+qitasz+'"></li><li>其他尺码'+qitasz+'</li></label></ul></div>';
    	qitasz=qitasz+1;
    	$("#ul_size").html(aa);
    }
    
    function   testbb(){
    	var aa= $("#ul_color").html();
    	aa=aa+'<div><ul><label><li><input type="checkbox"   onclick="showDiv();"  value="其他颜色'+qitacl+'"></li><li><dt class="" style="width:12px; height:12px;margin:4px 5px 0 3px;"></dt></li><li>其他颜色'+qitacl+'</li></label></ul></div>';
    	qitacl=qitacl+1;
    	$("#ul_color").html(aa);
    }
    var json = new Function("return" + $("#jsonValue").html())();
    var json2;
    if($("#jsonValue2").html().length>1){
    json2 = new Function("return" + $("#jsonValue2").html())();
    }else{
    json2 = $("#jsonValue2").html();
    }

    if(json2.length>0){
    	var requiredAttrSum = 0;
    var sb="<li class='textintentdiv'>商品属性</li>";
    
    for(var i=0;i<json.length;i++){
    	
		 
    	var attributeId = "";
    	if(json[i].attrDesign==1){
    		sb = sb +'<input type="hidden" name="attrbuteName" value="'
		   		 +json[i].attrName+'"/><input type="hidden" name="attrbuteCode" value="'
		   		 +json[i].attrCode+'"/><input type="hidden" name="attrbuteType" value="'
		   		 +json[i].attrDesign+'"/><input type="hidden" name="attrbuteRequired" value="'
		   		 +json[i].required+'"/>';
    		var attributeValues = json[i].attrValue.split(",");
    		
    		if(json[i].required==1){
    			requiredAttrSum++;
    			sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
    		}else{
    			sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
    		}
    		
    		
    		
    		for(var j=0;j<attributeValues.length;j++){
    			var flag = 0;
    			for(var x=0;x<json2.length;x++){
    				if(json2[x].type=='1'){
    					
    					if(json[i].attrCode==json2[x].productAttributeCode){
    						
    						attributeId = json2[x].productAttributeId;
    						 var arrStr = json2[x].productAttributeValue.split(",");
    						 
    						 for(var y=0;y<arrStr.length;y++){
    							 if($.trim(arrStr[y])==$.trim(attributeValues[j])){
    								 flag = 1;
    								 
    								 break;
    							 }
    						 }
    					 }
    				}
    			 }
    			
    			if(json[i].required==1){ 
    				
    				if(flag==1){
        				
        				sb = sb+'<label><input type="checkbox" class="requiredAttr'+requiredAttrSum+'" checked="checked" name="attrbuteValue'+i+'" value="'
        					+attributeValues[j]+'"/>'+attributeValues[j]+'</label>';
        			 }else{
        				sb = sb+'<label><input type="checkbox" class="requiredAttr'+requiredAttrSum+'" name="attrbuteValue'+i+'" value="'
        					+attributeValues[j]+'"/>'+attributeValues[j]+'</label>';
        			 }
        		 }else{
        			 if(flag==1){
         				
         				sb = sb+'<label><input type="checkbox"  checked="checked" name="attrbuteValue'+i+'" value="'
         					+attributeValues[j]+'"/>'+attributeValues[j]+'</label>';
         			 }else{
         				sb = sb+'<label><input type="checkbox" name="attrbuteValue'+i+'" value="'
         					+attributeValues[j]+'"/>'+attributeValues[j]+'</label>';
         			 }
        		 }
        		
    			}
    		sb = sb+'<input type="hidden" name="productAttributeId" value="'+attributeId+'"/>';
    		sb = sb+"</li></ul></div></li><br/>";
    	}else{
    		var json2Value = "";
    		var attributeId = "";
    		for(var x=0;x<json2.length;x++){
    			if(json2[x].type!='1'){
    				if(json[i].attrCode==json2[x].productAttributeCode){
    					attributeId = json2[x].productAttributeId;
    					json2Value = json2[x].productAttributeValue;
    				}
    			}
    		}
    		sb = sb +'<input type="hidden" name="attrbuteName" value="'
    		 +json[i].attrName+'"/><input type="hidden" name="attrbuteCode" value="'
    		 +json[i].attrCode+'"/><input type="hidden" name="attrbuteType" value="'
    		 +json[i].attrDesign+'"/><input type="hidden" name="attrbuteRequired" value="'
			 +json[i].required+'"/>';
    		 sb = sb+'<input type="hidden" name="productAttributeId" value="'+attributeId+'"/><li class="clearfix">';
    		 
    		 if(json[i].required==1){
    			 requiredAttrSum++;
    			 if(json2Value!=""){
    	    			sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
    	    			json2Value+"'/></li></ul></div></li>";
    	    		}else{
    	    			if(json[i].attrValue!=null){
    	    				sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
        	    			json[i].attrValue+"'/></li></ul></div></li>";
    	    			}else{
    	    				sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
    	    			}
    	    			
    	    		}
    		 }else{
    			 if(json2Value!=""){
 	    			sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
 	    			json2Value+"'/></li></ul></div></li>";
 	    		}else{
 	    			if(json[i].attrValue!=null){
 	    				sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
 	 	    			json[i].attrValue+"'/></li></ul></div></li>";
 	    			}else{
 	    				sb = sb+"<div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
 	    			}
 	    			
 	    		}
    		 }
    		
    		
    	}
    	
    }
    $("#attrUl").html(sb);
    $("#requiredAttrSum").val(requiredAttrSum);
    }

    
    var showCate = $("#showCate").html();
    var firstValue = "";
    var secondValue = "";
    var thirdValue = "";
    function liClick(element){
    	//获取当前对象文本
    	var str = element.parentNode.childNodes[0].id;
    	var categoryCode = $("#"+str).val();
    	$("#c_catName").val(categoryCode);
    	$("#c_catCode").val(str);
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
    	//element.className="d_f_list_on";
    	li.on("click",function(){
    		$(this).addClass("d_f_list_on").siblings("li").removeClass("d_f_list_on");
    	})
    	//element.
    	//li.each(function(){
    		//$(this).attr("class","");
    	//})
    }
    
    function quedinCate(){
    	var requiredAttrSum = 0;
    	var str = $("#c_catCode").val();
    	if(str.length!=6){
    		alert("需选择三级目录");
    	}else{
    		$("#catCode").val($("#c_catCode").val());
    		$("#catName").val($("#c_catName").val());
    		$("#categoryText").html($("#catNameGroup").val());
        	document.getElementById('category').style.display='none';
        	var aj  = $.ajax({
    			url : '${contextPath}/dwz/attr_group',  // 后台提供
    			data:"catCode="+str,
    			type:'POST',
    			cache:false,
    			dataType:'json',
    			success : function(json){
    				var sb="<li class='textintentdiv'>商品属性</li>";

    				for(var i=0;i<json.length;i++){
    					sb = sb +'<input type="hidden" name="attrbuteName" value="'
    					 +json[i].attrName+'"/><input type="hidden" name="attrbuteCode" value="'
    					 +json[i].attrCode+'"/><input type="hidden" name="attrbuteType" value="'
    					 +json[i].attrDesign+'"/><input type="hidden" name="attrbuteRequired" value="'
    					 +json[i].required+'"/>';
    					 
    					if(json[i].attrDesign==1){
    						var arrStr = json[i].attrValue.split(",");
    						
    						if(json[i].required==1){
    							requiredAttrSum++;
    							sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
    							
    							for(var j=0;j<arrStr.length;j++){
    								 sb = sb+'<input type="checkbox" class="attrValue requiredAttr'+requiredAttrSum+'" name="attrbuteValue'+i+'" value="'
    										 +arrStr[j]+'"/>'+arrStr[j];
    							 }
    							sb = sb+"</li></ul></div></li>";
    						}else{
    							sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
    							
    							for(var j=0;j<arrStr.length;j++){
    								 sb = sb+'<input type="checkbox" class="attrValue" name="attrbuteValue'+i+'" value="'
    										 +arrStr[j]+'"/>'+arrStr[j];
    							 }
    							sb = sb+"</li></ul></div></li>";
    						}
    					}else{
    						var aa =json[i].attrValue;
    						if(aa == undefined ){
    							aa="";
    						}
    						if(json[i].required==1){
    							requiredAttrSum++;
    							sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
    							aa+"'/></li></ul></div></li>";
    						}else{
    							sb = sb+"<li class='clearfix'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
    							aa+"'/></li></ul></div></li>";
    						}
    					}
    				}
    				$("#attrUl").html(sb);
    				$("#requiredAttrSum").val(requiredAttrSum);
    			},
    			error : function(){
    				alert("服务器异常!");
    			}
    		});
    	}
    	
    }
    
	//图片删除
	function deleteImage(index,skuId,element,skuCode){
		var level = 1;
	    if(index+1>5){
	    	if((index+1)%5!=0){
	    		level = (index+1)%5;
	    	}else{
	    		level = 5;
	    	}
	    }else{
	    	level = index+1;
	    }
	    var imgObjPreview=$(".previewa"+index);
	    $("#bb"+skuId+" input").eq(level-1).val("");
		imgObjPreview[0].style.display = 'none'; 
		imgObjPreview[0].src = "";
		
		var productCode = $(".productCode").val();
		
		$(element).prev().outerHTML += '';
		$(element).prev().val("");
		
		if(element.className=='1'){
			var aj  = $.ajax({
				url : '${contextPath}info/product/delete_img',  // 后台提供
				data:"productCode="+productCode+"&productSkuCode="+skuCode+"&level="+level,
				type:'POST',
				cache:false,
				dataType:'text',
				success : function(data){
					if(data=='success'){
						alert("删除图片成功!");
					}else{
						alert("删除图片失败!");
					}
				},
				error : function(){
					alert("服务器异常!");
				}
			}); 
		}
	}
	
	//图片删除
	function deleteImg(index,element){
		var yy = Math.floor((index)/5)*5;
		var nameIndex = (index+1)%5;
		var level = 1;
	    if(index+1>5){
	    	if(nameIndex!=0){
	    		level = (index+1)%5;
	    	}else{
	    		level = 5;
	    	}
	    }else{
	    	level = index+1;
	    }
	    
	    $(element).prev().outerHTML += '';
		$(element).prev().val("");
	    
	    var fileNames=$(".fileNames"+yy);
	    var imgObjPreview=$(".preview"+index);
	    fileNames[level-1].value="";
		imgObjPreview[0].src = "";
	}
	
	//下面用于图片上传预览功能
	function setImagePreview(element,index) {  
	    //var docObj=document.getElementById("doc");
		var yy = Math.floor((index)/5)*5;
		var nameIndex = (index+1)%5;
		var level = 1;
	    if(index+1>5){
	    	if(nameIndex!=0){
	    		level = (index+1)%5;
	    	}else{
	    		level = 5;
	    	}
	    }else{
	    	level = index+1;
	    }
	    
	    var imgObjPreview=$(".preview"+index);  
	    var fileNames=$(".fileNames"+yy);
	    var value = document.getElementById(element.className).innerHTML;
	    value = value.replace(":","");
	    
	    if(element.files.length>1){
	    	alert("只能选择一张图片.");
	    }else{
		    if(element.files && element.files[0]){  
		        //火狐下，直接设img属性  
		        imgObjPreview[0].style.display = 'block';  
		//        imgObjPreview[0].style.width = '138px';  
		//        imgObjPreview[0].style.height = '108px';                      
		        //imgObjPreview.src = docObj.files[0].getAsDataURL();  
		          
		        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
		        imgObjPreview[0].src = window.URL.createObjectURL(element.files[0]); 
		        
		        var oldFileName = element.files[0].name;
		        
		        var fileSuffix = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length);
		        
		        var timestamp=new Date().getTime();
		        var num = generateRandomAlphaNum(4);
		        var newFileName = ""+timestamp+num+fileSuffix;
		        
		        fileNames[level-1].value=value+"#-#"+newFileName+"#-#"+level;
		        
		        var fileObj = element.files[0]; // 获取文件对象
	            var FileController = "${contextPath}/dwz/image_ajax";                    // 接收上传文件的后台地址 
	            // FormData 对象
	            var form = new FormData();
	            form.append("productCode", $(".productCode").val());                        // 可以增加表单数据
	            form.append("fileName", newFileName);
	            form.append("file", fileObj);                           // 文件对象
	            // XMLHttpRequest 对象
	            var xhr = new XMLHttpRequest();
	            xhr.open("post", FileController, true);
	            
	            xhr.send(form);
		    }else{  
		    	
		    	fileNames[level-1].value="";
		//    	imgObjPreview[0].style.display = 'none'; 
		    	imgObjPreview[0].src = "";
		    	/*//IE下，使用滤镜  
		    	element.select();  
		        var imgSrc = document.selection.createRange().text;  
		        var localImagId = document.getElementById("localImag"+index);  
		        //必须设置初始大小  
		//        localImagId.style.width = $(".boximg_div").eq(0).width();  
		//        localImagId.style.height = $(".boximg_div").eq(0).height(); 
		        //图片异常的捕捉，防止用户修改后缀来伪造图片  
		        try{
		            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
		            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
		        }catch(e){  
		            alert("您上传的图片格式不正确，请重新选择!");  
		            return false;  
		        }  
		        fileNames[level-1].value=value+element.files[0].name+"#-#"+level;*/
		    }  
		    return true;  
	    }
	}
	function setImagePreview1(element,index,skuId) {  
	    //var docObj=document.getElementById("doc");
	    var imgObjPreview=$(".previewa"+index);  
	    var fileNames=$(".fileNames1");
	    var value = document.getElementById(element.className).innerHTML;
	    
	    value = value.replace(":","");
	    
	    var level = 1;
	    if(index+1>5){
	    	if((index+1)%5!=0){
	    		level = (index+1)%5;
	    	}else{
	    		level = 5;
	    	}
	    }else{
	    	level = index+1;
	    }
	    
	    if(element.files.length>1){
	    	alert("只能选择一张图片.");
	    }else{
		    if(element.files && element.files[0]){  
		        //火狐下，直接设img属性  
		        imgObjPreview[0].style.display = 'block';  
		//        imgObjPreview[0].style.width = $(".boximg_div").eq(0).width();  
		//        imgObjPreview[0].style.height = $(".boximg_div").eq(0).height();                      
		        //imgObjPreview.src = docObj.files[0].getAsDataURL();  
		          
		        //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
		        imgObjPreview[0].src = window.URL.createObjectURL(element.files[0]); 
		        
		        var oldFileName = element.files[0].name;
		        
		        var fileSuffix = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length);
		        
		        var timestamp=new Date().getTime();
		        var num = generateRandomAlphaNum(4);
		        var newFileName = ""+timestamp+num+fileSuffix;
		        
		        $("#bb"+skuId+" input").eq(level-1).val(value+"#-#"+newFileName+"#-#"+level);
		        
		        var fileObj = element.files[0]; // 获取文件对象
	            var FileController = "${contextPath}/dwz/image_ajax";                    // 接收上传文件的后台地址 
	            // FormData 对象
	            var form = new FormData();
	            form.append("productCode", $(".productCode").val());                        // 可以增加表单数据
	            form.append("fileName", newFileName);
	            form.append("file", fileObj);                           // 文件对象
	            // XMLHttpRequest 对象
	            var xhr = new XMLHttpRequest();
	            xhr.open("post", FileController, true);
	            
	            xhr.send(form);
		    }else{  
		    	$("#bb"+skuId+" input").eq(level-1).val("");
		    	imgObjPreview[0].style.display = 'none'; 
		    	imgObjPreview[0].src = "";
		    	/*//IE下，使用滤镜  
		    	element.select();  
		        var imgSrc = document.selection.createRange().text;  
		        var localImagId = document.getElementById("localImag"+index);  
		        //必须设置初始大小  
		//        localImagId.style.width = $(".boximg_div").eq(0).width();  
		//        localImagId.style.height = $(".boximg_div").eq(0).height(); 
		        //图片异常的捕捉，防止用户修改后缀来伪造图片  
		        try{
		            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
		            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
		        }catch(e){  
		            alert("您上传的图片格式不正确，请重新选择!");  
		            return false;  
		        }  
		        var fileNames=$(".fileNames1");
		        var level = 1;
		        if(index+1>5){
		        	if((index+1)%5!=0){
		        		level = (index+1)%5;
		        	}else{
		        		level = 5;
		        	}
		        }else{
		        	level = index+1;
		        }
		        fileNames[index].value=value+"#-#"+element.files[0].name+"#-#"+level;*/ 
		    }  
		    return true; 
	    }
	}
	

	function generateRandomAlphaNum(len) {

		var rdmString = "";

		for (; rdmString.length < len; rdmString += Math.random().toString(36).substr(2));

		return rdmString.substr(0, len);

		}
	
	 function tmlg(){
	    	var aa= $("#protype").val();
	    	if(aa=='01'){
	    		$("#t01").show();
	    		$("#t02").show();
	    		$("#l01").hide();
	    	}else{
	    		$("#t01").hide();
	    		$("#t02").hide();
	    		$("#l01").show();
	    	}
	    }
	 tmlg();
</script>