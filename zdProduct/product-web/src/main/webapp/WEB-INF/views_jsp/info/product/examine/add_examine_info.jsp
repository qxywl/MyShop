<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<link href="${contextPath}/styles/product/css/add_product.css" rel="stylesheet" type="text/css" media="screen" />
<script src="${contextPath}/styles/product/js/info_examine.js" type="text/javascript"></script>


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
	width:8%;
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
	margin:100px auto 10px 20px;
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
                            <li class="code_l_m"><input type="text" name="productCode" value="${productModel.productCode }" readonly="readonly" class="pageinput"></li>
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
                        	<li class="code_l_text">商品简介：</li>
                            <li class="code_l_m"><input type="text" name="productDesc" value="${productModel.productDesc }" class="pageinput"></li>
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
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">供应商名称：</li>
                            <li class="code_l_m"><input type="text"  value="${productModel.supplierName }" readonly="readonly" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">供应商编号：</li>
                            <li class="code_l_m"><input type="text" name="supplierId" value="${productModel.supplierId }" readonly="readonly" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">店铺编号：</li>
                            <li class="code_l_m"><input type="text" name="shopId" value="${productModel.shopId }" readonly="readonly" class="pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
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
                        	<li class="code_l_text">是否下架显示：</li>
                            <li class="code_l_m">
                            	<select name="productOperateModel.productDownShow">
                            		
                            		<option <c:if test="${productModel.productOperateModel.productDownShow==1 }">selected="selected"</c:if> value="1">是</option>
                            		
                            		<option <c:if test="${productModel.productOperateModel.productDownShow==2 }">selected="selected"</c:if> value="2">否</option>
                            	</select>
                            </li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品视频链接：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productVideoUrl" value="${productModel.productOperateModel.productVideoUrl }" class="productVideoUrl pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品缩略图：</li>
                            <%-- <li class="code_l_m"><input type="text" name="productOperateModel.productThumbnail" value="${productModel.productOperateModel.productThumbnail }" class="productThumbnail pageinput"></li>
                            <li class="code_l_r"></li> --%>
                            <div> 
                            	<div class="thumbnailDiv" style="width:15%;height:200px;border:#e0e0e0 solid 1px;margin-left: 226px;">
                            		<img class="thumbnailImg" style="width:100%;height:100%;" src="${productModel.productOperateModel.productThumbnail }"/>
                            	</div>
                            </div>
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
                            	<dt class="code_buss_txt">加价率：</dt>
                                <dt class="code_buss_input"><input type="text" name="productOperateModel.productPriceRate" value="${productModel.productOperateModel.productPriceRate }" class="pageinput" /></dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">单品毛重量：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productWeight" value="${productModel.productMallModel.productWeight }" class="pageinput productWeight" /></dt>
                            	<dt class="code_buss_warm">kg</dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                    		<li class="code_bussmain">
                            	<dt class="code_buss_txt">货号：</dt>
                                <dt class="code_buss_input"><input type="text" value="10" name="productMallModel.productAtrNumber" value="${productModel.productMallModel.productAtrNumber}"  class="pageinput productAtrNumber"></dt>
                           		<dt class="code_buss_warm"></dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">产地：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productProducer" value="${productModel.productMallModel.productProducer }" class="pageinput" /></dt>
                            </li>
                        </ul>
                    </div>
                  	<div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）高：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productHeight" value="${productModel.productMallModel.productHeight }" class="pageinput" /></dt>
                                <dt class="code_buss_warm">mm</dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">体积：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productDensity" value="${productModel.productMallModel.productDensity }" class="pageinput" /></dt>
                                <dt class="code_buss_warm">cm3</dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）宽：</dt>
                                <dt class="code_buss_input"><input type="text"  name="productMallModel.productWidth" value="${productModel.productMallModel.productWidth }" class="pageinput"></dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）长：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productLong" value="${productModel.productMallModel.productLong }" class="pageinput" /></dt>
                                <dt class="code_buss_warm">mm</dt>
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
										尺码
										<input type="hidden" name="attrSpecName" value="尺码" />
									</c:otherwise>
								</c:choose></td>
                            <td>吊牌尺码</td>
                            <td>*供货价</td>
                            <td>*市场价</td>
                            <td>销售价</td>
                            <td>换购特币</td>
                            <td>*库存</td>
                            <td>供应商SKU编号</td>
                            <td>*是否主SKU</td>
                          </tr>
                        </table>
                    </div>
                    <div class="code_tab">
                    	<table id="skukk" width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<c:if test="${productModel.productSkuList!=null }">
                    		<c:forEach items="${productModel.productSkuList }" var="sku">
                    			<tr id="aa${sku.productSkuId }">
                    				<td>${sku.productSkuCode }</td>
                    				<td>
                    					${sku.attrColorValue }
                    				</td>
	                    			<td>${sku.colorValueAlias }</td>
	                    			<td>${sku.attrSpecValue }</td>
	                    			<td>${sku.specValueAlias }</td>
	                    			<td>${sku.productPriceModel.costPrice }</td>
	                    			<td>${sku.productPriceModel.marketPrice }</td>
	                    			<td>${sku.productPriceModel.tshPrice }</td>
	                    			<td>${sku.productPriceModel.tb }</td>
	                    			<td>${sku.productStockModel.virtualStock }</td>
	                    			<td>${sku.supplierSkuCode }</td>
	                    			<td>
	                    				<c:if test="${sku.mainSku==1 }">是</c:if>
	                    				<c:if test="${sku.mainSku==0 }">否</c:if>
	                    			</td>
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
                        	<li class="check_orange" id="check_on"><a href="javascript:void(0)">电脑版</a></li>
                            <li class="check_off" id="check_off"><a href="javascript:void(0)">手机版</a></li>
                        </ul>
                    </div>
                    <div class="check_textare" id="pc_txt">
                    	<textarea class="editor" name="productInfoModel.pcProductInfo" id="pcProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img&productCode=${productModel.productCode}" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf&productCode=${productModel.productCode}" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi&productCode=${productModel.productCode}" upMediaExt:"avi">
							</textarea>
                    </div>
                    <div class="check_textare" style="display:none;" id="phone_txt">
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
        
    </div>
</div>
    </div> 
    
    <textarea id="appInfo" style="display:none;">${productModel.productInfoModel.appProductInfo }</textarea>
    <textarea id="pcInfo" style="display:none;">${productModel.productInfoModel.pcProductInfo }</textarea>
</form>
<script>
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
    			sb = sb+"<li class='clearfix' style='clear:both;'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
    		}else{
    			sb = sb+"<li class='clearfix' style='clear:both;'><div class='codemange'><ul class='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
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
    		 sb = sb+'<input type="hidden" name="productAttributeId" value="'+attributeId+'"/><li calss="clearfix">';
    		 
    		 if(json[i].required==1){
    			 requiredAttrSum++;
    			 if(json2Value!=""){
    	    			sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
    	    			json2Value+"'/></li></ul></div></li>";
    	    		}else{
    	    			if(json[i].attrValue!=null){
    	    				sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
        	    			json[i].attrValue+"'/></li></ul></div></li>";
    	    			}else{
    	    				sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
    	    			}
    	    			
    	    		}
    		 }else{
    			 if(json2Value!=""){
 	    			sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
 	    			json2Value+"'/></li></ul></div></li>";
 	    		}else{
 	    			if(json[i].attrValue!=null){
 	    				sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
 	 	    			json[i].attrValue+"'/></li></ul></div></li>";
 	    			}else{
 	    				sb = sb+"<div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value=''/></li></ul></div></li>";
 	    			}
 	    			
 	    		}
    		 }
    		
    		
    	}
    	
    }
    
    $("#attrUl").html(sb);
    $("#requiredAttrSum").val(requiredAttrSum);
}else{
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
				sb = sb+"<li calss='clearfix'><div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
				
				for(var j=0;j<arrStr.length;j++){
					 sb = sb+'<input type="checkbox" class="attrValue requiredAttr'+requiredAttrSum+'" name="attrbuteValue'+i+'" value="'
							 +arrStr[j]+'"/>'+arrStr[j];
				 }
				sb = sb+"</li></ul></div></li>";
			}else{
				sb = sb+"<li calss='clearfix'><div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'>";
				
				for(var j=0;j<arrStr.length;j++){
					 sb = sb+'<input type="checkbox" class="attrValue" name="attrbuteValue'+i+'" value="'
							 +arrStr[j]+'"/>'+arrStr[j];
				 }
				sb = sb+"</li></ul></div></li>";
			}
		}else{
			if(json[i].required==1){
				requiredAttrSum++;
				sb = sb+"<li calss='clearfix'><div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>*"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' class='requiredAttr"+requiredAttrSum+"' name='attrbuteValue"+i+"' value='"+
				json[i].attrValue+"'/></li></ul></div></li>";
			}else{
				sb = sb+"<li calss='clearfix'><div class='codemange'><ul calss='clearfix'><li class='code_l_text clearfix'>"+json[i].attrName+"：</li><li class='code_buss_input clearfix'><input type='text' name='attrbuteValue"+i+"' value='"+
				json[i].attrValue+"'/></li></ul></div></li>";
			}
		}
		
	}
	$("#attrUl").html(sb);
	$("#requiredAttrSum").val(requiredAttrSum);
}
</script>