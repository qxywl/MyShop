
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<link href="${contextPath}/styles/product/css/add_product.css" rel="stylesheet" type="text/css" media="screen" />
<script src="${contextPath}/styles/product/js/add_product.js" type="text/javascript"></script>

<style>
.code_tab table tr td{
	width:8%;
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

<form method="post" action="${contextPath}/info/product/add" enctype="multipart/form-data" onsubmit="return checkProduct(this, dialogReloadNavTab)">
    <div class="pageFormContent" layoutH="58">
    	<input type="hidden" name="catCode" value="${catCode }"/>
    	<input type="hidden" name="catName" value="${catName }"/>
    	<input type="hidden" name="productCode" class="productCode" value="${productCode }"/>
    	<div id="input_warp">
		<div id="input_main">
		<!--<div class="title_ice02"><b>${catNameGroup }</b></div> -->
		<div class="j_divtitle" style="border:#ffdcab solid 1px; font-size:16px;width:100%; min-height:33px;">
			<ul>
				<li style="height:33px; line-height:33px; text-indent:20px;"><b>${catNameGroup }</b></li>
			</ul>
		</div>
    	<div class="j_div">
        	<ul>
            	<li class="textintentdiv">基本内容编辑（*表示必填）</li>
                <li>
                	<div class="codemange">
                    	<ul>
                        	<li class="code_l_text">*商品名称：</li>
                            <li class="code_l_m"><input type="text" name="productName" class="productName pageinput" /></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品短名称：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productShortName" class="productShortName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">宅豆点评：</li>
                            <li class="code_l_m"><input type="text" name="productDesc" class="productDesc pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">供应商：</li>
                            <li class="code_l_m">
                            <select name="supplierId"  >
                            <c:forEach items="${shopList }" var="supplier">
                    			<option value="${supplier.id }">${supplier.name }</option>
                    			</c:forEach>
                            </select>
                            </li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    
                    <input type="hidden" name="shopId" value="${shopId }" readonly="readonly" class="pageinput">
                    
                    
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品类型：</li>
                            <li class="code_l_m">
                                 <select  id="protype" name="type"  onchange="tmlg();">
                            	    <option value="01">特卖</option>
                            		<option value="02">零元购</option>
                            	</select></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="t01" class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品特卖限制数量：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.userMaxNum" value="9999" class="productShortName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="t02" class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品特卖限制周期：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.userMaxType" value="1" >天</li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div id="l01" class="codemange" style="display:none;" >
                    	<ul>
                        	<li class="code_l_text">零元购每日限购数量：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.zeroMaxCount" value="1" class="productShortName pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">*品牌：</li>
                            <li class="code_l_m">
	                            <input class="brandName" readonly="true" name="productModel.brandName" type="text" />
								<a class="btnLook" href="${contextPath}/dwz/selectback" lookupGroup="productModel">品牌列表</a>

								<input  name="productModel.brandCode"   type="hidden"/>
							</li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品前缀：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productPrefix" class="productPrefix pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品后缀：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productSuffix" class="productSuffix pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品关键字：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productKeyword" class="productKeyword pageinput"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                     <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">商品排序号：</li>
                            <li class="code_l_m"><input type="text" name="productOperateModel.productLevel" class="productKeyword pageinput" value="1000"></li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                        	<li class="code_l_text">是否下架显示：</li>
                            <li class="code_l_m">
                            	<select name="productOperateModel.productDownShow">
                            	    <option value="2">否</option>
                            		<option value="1">是</option>
                            	</select>
                            </li>
                            <li class="code_l_r"></li>
                        </ul>
                    </div>
                    <input type="hidden" name="productOperateModel.productVideoUrl"  />
                    
                    <div style="display:none;"  >
                    <input type="file" onchange="setImagePreview3(this);" name="thumbnailFile" multiple="multiple" value="" style="diplay:none;" accept="image/*">
                    <div class="thumbnailDiv" style="width:15%;height:200px;border:#e0e0e0 solid 1px;margin-left: 226px;display:none;"  >
                            		<img src="" class="thumbnailImg" style="width:0%;height:0%;diplay:none;"/>
                            		<input type="hidden" name="thumbnailFileName" id="thumbnailFileName" value="1" />
                    </div>
                    </div>
                    
                    <input type="hidden" name="productOperateModel.isExpensive" value ="2" />
                    <input type="hidden" value="0" name="productOperateModel.productPriceRate"   id="priceRate" class="pageinput">
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
                                <dt class="code_buss_input"><input type="text"  name="productMallModel.productAtrNumber"  class="pageinput"></dt>
                           		<dt class="code_buss_warm"></dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">产地：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productProducer" class="productProducer pageinput"></dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">单品毛重量：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productWeight" class="productWeight pageinput"></dt>
                                <dt class="code_buss_warm">kg</dt>
                            </li>
                            <li class="code_bussmain">
                            	 <dt class="code_buss_txt">（包装）宽：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productWidth" class="productWidth pageinput"></dt>
                                <dt class="code_buss_warm">mm</dt>
                            	
                            </li>
                        </ul>
                    </div>
                  	<div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）长：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productLong" class="productLong pageinput"></dt>
                                <dt class="code_buss_warm">mm</dt>
                            </li>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">（包装）高：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productHeight" class="productHeight pageinput"></dt>
                                <dt class="code_buss_warm">mm</dt>
                            </li>
                        </ul>
                    </div>
                    <div class="codemange">
                    	<ul>
                            <li class="code_bussmain">
                            	<dt class="code_buss_txt">体积：</dt>
                                <dt class="code_buss_input"><input type="text" name="productMallModel.productDensity" class="productDensity pageinput"></dt>
                                <dt class="code_buss_warm">cm3</dt>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<div id="jsonValue" style="display:none;">${listAttr}</div>
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
                            <td>
								<c:choose>
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
                            <td>吊牌<c:choose>
									<c:when test="${attrColorName!=null && attrColorName!='' }">
										${attrColorName }
										<input type="hidden" name="attrColorName" value="${attrColorName }" />
									</c:when>
									<c:otherwise>
										颜色
										<input type="hidden" name="attrColorName" value="颜色" />
									</c:otherwise>
								</c:choose></td>
                            <td>
                            	<c:choose>
									<c:when test="${attrSpecName!=null && attrSpecName!='' }">
										${attrSpecName }
										<input type="hidden" name="attrSpecName" value="${attrSpecName }" />
									</c:when>
									<c:otherwise>
										型号
										<input type="hidden" name="attrSpecName" value="型号" />
									</c:otherwise>
								</c:choose>
                            </td>
                            <td>吊牌<c:choose>
									<c:when test="${attrSpecName!=null && attrSpecName!='' }">
										${attrSpecName }
										<input type="hidden" name="attrSpecName" value="${attrSpecName }" />
									</c:when>
									<c:otherwise>
										型号
										<input type="hidden" name="attrSpecName" value="型号" />
									</c:otherwise>
								</c:choose></td>
                            <td>*市场价</td>
                            <td>销售价</td>
                            <td>*库存</td>
                            <td>*是否主SKU</td>
                            <td>供应商SKU编号</td>
                            <td><input type="button" name="button" value="添加其他" id="ordelbtn" onclick="addOther();" ></td>
                          </tr>
                        </table>
                    </div>
                    <div class="code_tab" id="div_table" id="tab1" style="display:none; margin-bottom:10px;">
                    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tbody></tbody>
                    	</table>
                    	
                    </div>
                </li>
            </ul>
        </div>
        <div class="j_div">
        	<ul>
            	<li class="textintentdiv">图片管理</li>
                <li id="box_img">
                    <div id="box_img" style="display:none"></div>
                    <!-- <div class="box_btndiv">
                    	<div class="box_btn_top">
                        	<ul>
                            	<li><button>从本地上传</button></li>
                                <li><button>从图片空间上传</button></li>
                            </ul>
                        </div>
                        <div class="box_btn_top colorred">
                        	提示：<br><br>
							1.图片尺寸为800*800,单张大小不超过1024kb.仅支持jpg,jpeg,png格式<br><br>
							2.图片质量要清晰,不能虚化,建议主图为白色背景的正面图。<br><br>
							3.服装类目列表图,点击保存后,可在“主图管理”中进行设置。
                        </div>
                    </div> -->
                    <div style="clear:both;"></div>
                </li>
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
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img&productCode=${productCode }" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf&productCode=${productCode }" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi&productCode=${productCode }" upMediaExt:"avi">
							</textarea>
                    </div>
                    <div class="check_textare"  id="phone_txt">
                    	<textarea class="editor" style="width:100%;" name="productInfoModel.appProductInfo" id="appProductInfo" rows="15" cols="90"
							upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
							upImgUrl="${contextPath}/dwz/editor_upload?attrName=app&type=img&productCode=${productCode }" upImgExt="jpg,jpeg,gif,png"
							upFlashUrl="${contextPath}/dwz/editor_upload?attrName=app&type=swf&productCode=${productCode }" upFlashExt="swf"
							upMediaUrl="${contextPath}/dwz/editor_upload?attrName=app&type=avi&productCode=${productCode }" upMediaExt:"avi" >
						</textarea>
                    </div>
                </li>
            </ul>
        </div>
         
        
        <div class="main_warp_main">
            <input type="hidden"  id="imgxz" />
        	<button type="submit">提交售卖审核</button>
            <button type="button" class="close">取消</button>
        </div>
    </div>
</div>
    </div> 
    <input type="hidden" value="" id="index"/> 
    <input type="hidden" id="requiredAttrSum" value=""/>
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
    
  //下面用于图片上传预览功能
    function setImagePreview(element,index) {  
        //var docObj=document.getElementById("doc");
    	var yy = Math.floor((index)/5)*5;
    	var imgObjPreview=$(".preview"+index);  
    	var fileNames=$(".fileNames"+yy);
    	var value = document.getElementById(element.className).innerHTML;
    	value = value.replace(":","");
    	
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
        if(element.files.length>1){
        	alert("只能选择一张图片.");
        }else{
    	    if(element.files && element.files[0]){ 
    	        //火狐下，直接设img属性  
    	        imgObjPreview[0].style.display = 'block';  
    	//        imgObjPreview[0].style.width = $(".boximg_div").eq(0).width();//"197px";   
    	//        imgObjPreview[0].style.height = $(".boximg_div").eq(0).height();//"108px";                      
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
    	        //IE下，使用滤镜  
    	    	/*element.select();  
    	        var imgSrc = document.selection.createRange().text;  
    	        var localImagId = document.getElementById("localImag"+index);  
    	        //必须设置初始大小  
    	        localImagId.style.width = $(".boximg_div").eq(0).width();//"138px";  
    	        localImagId.style.height = $(".boximg_div").eq(0).height();//"108px";  
    	        //图片异常的捕捉，防止用户修改后缀来伪造图片  
    	        try{  
    	            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
    	            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
    	        }catch(e){  
    	            alert("您上传的图片格式不正确，请重新选择!");  
    	            return false;  
    	        }  */
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
</script>
