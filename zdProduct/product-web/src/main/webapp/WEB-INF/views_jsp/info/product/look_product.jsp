<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
		.clearfix:before, .clearfix:after {content: "";display: table}
		.clearfix:after{clear: both;overflow: hidden}
		.clearfix{zoom: 1}

		.tab{width: 100%;background-color: #fff;font-family: "Microsoft YaHei"}
		.tab .tab-h{background-color: #f5f5f5;border:1px solid #e5e5e5;}
		.tab .tab-h a{padding: 12px 20px;font-size:20px;display: inline-block;float: left;color: #3c3c3c;text-decoration: none;border-right: 1px solid #e5e5e5;border-top: 2px solid #f5f5f5}
		
		.tab .tab-h a.on{border-top: 2px solid #ff4400;background-color: #fff;color: #ff4400}
		
		.tab .tab-b{margin-top: 20px;width:100%;height:80%}
		.tab .tab-b .on{display: block;}
		.tab .tab-bc{display: none;border:1px solid #e5e5e5;width:100%;height:100%}
		.tab .tab-b .tab-bc .tab-label{width:30%;}
		
		.clearfix:before, .clearfix:after {content: "";display: table}
		.clearfix:after{clear: both;overflow: hidden}
		.clearfix{zoom: 1}
		
		.tb{width:95%;margin: 10px auto;background-color: #fff;background-color: #ddd;}
		.tb td{padding: 10px;background-color:  #f5f5f5;text-align: left;font-size: 14px;}
		.tb th{padding: 10px;font-size: 18px;text-align: left;}
		.tb td .tb-label{width:40%}
	</style>
<div class="pageContent">
    <div class="pageFormContent" layoutH="58">
    	<div class="tab">
		<div class="tab-h clearfix">
			<a href="javascript:;" class="on">商品信息</a>
			<a href="javascript:;">商品属性</a>
			<a href="javascript:;">运营属性</a>
			<a href="javascript:;">扩展属性</a>
			<a href="javascript:;">SKU属性</a>
		</div>
		<div class="tab-b">
			<!-- 基本属性 -->
			<div class="tab-bc on">
				<table class="tb" border="0" cellspacing="1" cellpadding="0">
					
					
					<tr>
						
						<td><label class="tb-label">商品名称：</label>
							<input type="hidden" name="productId" value="${productModel.productId }" />
							 ${productModel.productName } 
						</td>
						<td><label class="tb-label">商品编号：</label> ${productModel.productCode } </td>
						<td><label class="tb-label">商品简介：</label>< ${productModel.productDesc } /></td>
						<td>
							<label class="tb-label">上下架状态：</label>
							  <c:if test="${productModel.productShelves==1 }">上架</c:if> 
							  <c:if test="${productModel.productShelves==0 }">下架</c:if>  
						</td>
					</tr>
					<tr>
						<td>
							<label class="tb-label">品牌名称：</label>
							 ${productModel.brandName} 
							<input  name="productModel.brandCode" value="${productModel.brandCode}"  type="hidden"/>
							
						</td>
						<td><label class="tb-label">分类编码：</label> ${productModel.catCode }</td>
						<td>
							<label class="tb-label">分类名称：</label>
							<select class="" name="one_category" id="one_category" ref="two_category" onchange="comboxChange(this);">
								
							      <option value="">所有分类</option>
								  <c:if test="${listCategory!=null }">
								  		<c:forEach items="${listCategory }" var="category">
								  			<option value="${category.categoryCode }" <c:if test="${category.categoryCode==catCodeOne }">selected="selected"</c:if>>${category.categoryName }</option>
								  		</c:forEach>
								  </c:if>
							</select>
							<select class="" name="two_category" id="two_category" onchange="comboxChange(this);" ref="third_category" >
								<c:if test="${categoryPo!=null }">
							      <option value=${categoryPo.categoryCode }>${categoryPo.categoryName }</option>
								</c:if>
								<option value="">选择分类</option>
							</select>
							<select class="" name="third_category" id="third_category" onchange="comboxChange(this);">
									<c:if test="${productModel.catCode!=null and productModel.catCode!=''}">
								      <option value=${productModel.catCode }>${productModel.catName }</option>
									</c:if>
									<option value="">选择分类</option>
							</select>
							<input type="button" class="selbutton" style="width:37px;" value="确定">
						</td>
						<td><label class="tb-label">成本价：</label><input type="text" name="costPrice" value="${productModel.costPrice }"  size="20"/></td>
					</tr>
					<tr>
						<td><label class="tb-label">销售价：</label><input type="text" name="tshPrice" value="${productModel.tshPrice }" readonly="readonly" size="20"/></td>
						<td><label class="tb-label">市场价：</label><input type="text" name="marketPrice" value="${productModel.marketPrice }"  size="20"/></td>
						<td><label class="tb-label">特币额：</label><input type="text" name="tb" value="${productModel.tb }" readonly="readonly" size="20"/></td>
						<td>
							<label class="tb-label">商品类型：</label>
							<select name="type">
								<option value="01" <c:if test="${productModel.type=='01' }">selected="selected"</c:if> >商城</option>
								<option value="02" <c:if test="${productModel.type=='02' }">selected="selected"</c:if> >机票</option>
								<option value="03" <c:if test="${productModel.type=='03' }">selected="selected"</c:if> >酒店</option>
								<option value="04" <c:if test="${productModel.type=='04' }">selected="selected"</c:if> >鲜花</option>
								<option value="05" <c:if test="${productModel.type=='05' }">selected="selected"</c:if> >团购</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label class="tb-label">商品状态：</label>
							<c:if test="${productModel.staus==0  }">
								<input type="text" value="待处理" readonly="readonly">
							</c:if>
							<c:if test="${productModel.staus==1  }">
								<input type="text" value="待审核" readonly="readonly">
							</c:if>
							<c:if test="${productModel.staus==2  }">
								<input type="text" value="审核通过" readonly="readonly">
							</c:if>
							<c:if test="${productModel.staus==3  }">
								<input type="text" value="待同步" readonly="readonly">
							</c:if>
							<c:if test="${productModel.staus==4  }">
								<input type="text" value="同步成功" readonly="readonly">
							</c:if>
							<c:if test="${productModel.staus==9  }">
								<input type="text" value="审核失败" readonly="readonly">
							</c:if>
						</td>
						<td><label class="tb-label">第一次上架人：</label><input type="text" name="firstShelvesName"  value="${productModel.firstShelvesName }" readonly="readonly" size="20"/></td>
						<td>
							<label class="tb-label">第一次上架时间：</label>
							<input type="text" name="productFirstShelvesTime" value="<c:if test="${productFirstShelvesTime!=null }">${productFirstShelvesTime }</c:if>" readonly="readonly" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" size="20"/>
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
						<td><label class="tb-label">最后一次上架人：</label><input type="text" name="lastShelvesName" value="${productModel.lastShelvesName }" readonly="readonly" size="20"/></td>
						
					</tr>
					<tr>
						<td>
							<label class="tb-label">最后一次上架时间：</label>
							<input type="text" name="productLastShelvesTime" value="<c:if test="${productLastShelvesTime!=null }">${productLastShelvesTime }</c:if>" readonly="readonly" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
						<td><label class="tb-label">最后一次下架人：</label><input type="text" name="downShelvesName" value="${productModel.downShelvesName }" readonly="readonly" size="20"/></td>
						<td>
							<label class="tb-label">最后一次下架时间：</label>
							<input type="text" name="productDownShelvesTime" value="<c:if test="${productDownShelvesTime!=null }">${productDownShelvesTime }</c:if>" readonly="readonly" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
						<td><label class="tb-label">创建人：</label><input type="text" name="createUserName" value="${productModel.createUserName }" readonly="readonly" size="20"/></td>
						
					</tr>
					<tr>
						<td>
							<label class="tb-label">创建时间：</label>
							<input type="text" name="productCreateTime" value="${productModel.createTimes }"  class="date"  dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" size="20"/>
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
						<td><label class="tb-label">修改人：</label><input type="text" name="updateUserName" value="${productModel.updateUserName }" readonly="readonly" size="20"/></td>
						<td>
							<label class="tb-label">修改时间：</label>
							<input type="text" name="productUpdateTime" value="${productModel.updateTimes }"  class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly"  size="20"/>
							<a class="inputDateButton" href="javascript:;">选择</a>
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>主图路径：</label><input type="text" id="mainPic" name="mainPic" value="${productModel.mainPic }" readonly="readonly" size="35"/>
							
							<img alt="" src="${productModel.mainPic }" width="50" height="50">
						</td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>商品描述 PC：</label>
							<input type="hidden" name="productInfoModel.productId" value="${productModel.productInfoModel.productId }"/>
							<input type="hidden" name="productInfoModel.productInfoId" value="${productModel.productInfoModel.productInfoId }"/>
							
							<textarea class="editor" name="prodcutInfoModel.pcProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=img" upImgExt="jpg,jpeg,gif,png" 
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=swf" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=pc&type=avi" upMediaExt:"avi">
							</textarea>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2">
							<label>商品描述 APP：</label>
							<textarea class="editor" name="productInfoModel.appProductInfo" rows="15" cols="90"
								upLinkUrl="upload.php" upLinkExt="zip,rar,txt" 
								upImgUrl="${contextPath}/dwz/editor_upload?attrName=app&type=img" upImgExt="jpg,jpeg,gif,png"
								upFlashUrl="${contextPath}/dwz/editor_upload?attrName=app&type=swf" upFlashExt="swf"
								upMediaUrl="${contextPath}/dwz/editor_upload?attrName=app&type=avi" upMediaExt:"avi" >
							</textarea>
					    </td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<!-- 属性组 -->
			<div class="tab-bc">
				<table class="tb" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<th>属性编号</th>
					<th>属性名称</th>
					<th>属性值</th>
				</tr>
				
					<c:if test="${productModel.productAttributeList!=null }">
						<c:forEach items="${productModel.productAttributeList }" var="attribute">
							<tr>
							<td>${attribute.productAttributeCode }</td>
							<td>${attribute.productAttributeName }</td>
							<td>${attribute.productAttributeValue }</td>
							</tr>
						</c:forEach>
					</c:if>
				
				</table>
			</div>
			
			<!-- 运营属性 -->
			<div class="tab-bc">
				<table class="tb" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td>
						<input type="hidden" name="productOperateModel.productOperateId" value="${productModel.productOperateModel.productOperateId }">
						<input type="hidden" name="productOperateModel.productId" value="${productModel.productOperateModel.productId }">
						<label class="tb-label">商品短名称：</label><input type="text" name="productOperateModel.productShortName" value="${productModel.productOperateModel.productShortName }"  size="20"/></td>
						<td><label class="tb-label">商品前缀：</label><input type="text" name="productOperateModel.productPrefix" value="${productModel.productOperateModel.productPrefix }"  size="20"/></td>
						<td><label class="tb-label">商品后缀：</label><input type="text" name="productOperateModel.productSuffix" value="${productModel.productOperateModel.productSuffix }"  size="20"/></td>
						<td><label class="tb-label">关键字：</label><input type="text" name="productOperateModel.productKeyword" value="${productModel.productOperateModel.productKeyword }"  size="20"/></td>
					</tr>
					<tr>
						<td><label class="tb-label">是否下架显示：</label>
							<select name="productOperateModel.productDownShow">
									<option value="1"
									<c:if test="${productModel.productOperateModel.productDownShow==1 }">
									 selected="selected"
									</c:if>>是</option>
									<option value="2"
									<c:if test="${productModel.productOperateModel.productDownShow==2 }">
									 selected="selected"
									</c:if>>否</option>
							</select>
						</td>
						<td><label class="tb-label">是否自动上架：</label>
							<select name="productOperateModel.productAutoUp">
									<option value="1"
									<c:if test="${productModel.productOperateModel.productAutoUp==1 }">
									 selected="selected"
									</c:if>>是</option>
									<option value="2"
									<c:if test="${productModel.productOperateModel.productAutoUp==2 }">
									 selected="selected"
									</c:if>>否</option>
							</select>
						</td>
						<td><label class="tb-label">尺码对照表：</label><input type="text" name="productOperateModel.productSizeCompare" value="${productModel.productOperateModel.productSizeCompare }"  size="20"/></td>
						<td><label class="tb-label">运营分类编码：</label><input type="text" name="productOperateModel.productCatCode" id="productCatCode" value="${productModel.productOperateModel.productCatCode }" readonly="readonly"  size="20"/></td>
					</tr>
					<tr>
						<td>
							<label class="tb-label">运营分类名称：</label>
							<select name="productOperateModel.productCatName" id="productCatName">
								<c:if test="${listSaleCategory!=null }">
									<c:forEach items="${listSaleCategory }" var="categorySale">
										<option data-name="${categorySale.categoryCode }" value="${categorySale.categoryName }" <c:if test="${productModel.productOperateModel.productCatName==categorySale.categoryName }">selected="selected"</c:if>>
											${categorySale.categoryName }
										</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			
			<!-- 扩展属性 -->
			<div class="tab-bc">
				<select class="brand">
					<option>ddd</option>
				</select>
			</div>
			
			<!-- sku属性 -->
			<div class="tab-bc">
				<table class="tb" border="0" cellspacing="1" cellpadding="0" id="tab1">
					<tr>
						<th>Sku编号</th>
						<th>${attrColorName }<input type="hidden" name="attrColorName" value="${attrColorName }" /></th>
						<th>${attrSpecName }<input type="hidden" name="attrSpecName" value="${attrSpecName }" /></th>
						<th>是否主Sku</th>
						<th>成本价</th>
						<th>市场价</th>
						<th>库存</th>
						<th>图片展示</th>
						<th></th>
					</tr >
					<c:if test="${productModel.productSkuList!=null }">
						<c:forEach items="${productModel.productSkuList}" var="sku">
							<tr class="sku-tr">
								<td>
									<input type="hidden" name="skuId" value="${sku.productSkuId }"/>
									<input type="hidden" name="productPriceId" value="${sku.productPriceModel.productPriceId }"/>
									<input type="hidden" name="stockId" value="${sku.productStockModel.stockId }"/>
									
									${sku.productSkuCode }
								</td>
								<td>${sku.attrColorValue }</td>
								<td>${sku.attrSpecValue }</td>
								<td>
									<select name="mainSku" class="mainSku"  onchange="mainChange(this);">
										<option value="1"
										<c:if test="${sku.mainSku==1 }">
										 selected="selected"
										</c:if>>是</option>
										<option value="0"
										<c:if test="${sku.mainSku==0 }">
										 selected="selected"
										</c:if>>否</option>
									</select>
								</td>
								<td>${sku.productPriceModel.costPrice }</td>
								<td>${sku.productPriceModel.marketPrice }</td>
								<td>${sku.productStockModel.entityStock }</td>
								<td><c:if test="${sku.productImagerList!=null }">
									<c:forEach items="${sku.productImagerList}" var="img">
										<img src="${img.image}" width="50" height="50"/>
									</c:forEach>
								</c:if>
								</td>
								<td></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	<script>
	
	function mainChange(element){
		var i = 0;
		if(element.value==1){
			$(".mainSku").each(function(){
				if($(this).val()==1){
					i++;
				}
			})
			if(i>=2){
				element.innerHTML='<option value="1">是</option><option value="0" selected="selected">否</option>';
				alert('只能有一个主SKU');
			}
		}
		
	};
		function comboxChange(element){
			if(element.id=='third_category'){
				$(".catName").val($("#third_category").find("option:selected").attr("data-value"));
			}else{
				$.ajax({
		             type: "post",
		             url: "${contextPath}/dwz/combox",
		             data: "code="+element.value+"&type=combox",
		             dataType: "text",
		             success: function(data){
		            	if(element.id=='one_category'){
		            		$("#two_category").html(data);
		            		$("#third_category").html("<option value=''>选择分类</option>");
		            		$(".catName").val($("#one_category").find("option:selected").attr("data-value"));
		            	}else{
		            		$("#third_category").html(data);
		            		$(".catName").val($("#two_category").find("option:selected").attr("data-value"));
		            	}
		             }
		        })
			}
		};
		$(function(){
			//table 页切换
			$(".tab-h a").click(function(){
				var _this=$(this),
					idx=_this.index();

				_this.addClass("on").siblings("a").removeClass("on");
				$(".tab-b .tab-bc").eq(idx).addClass("on").siblings(".tab-bc").removeClass("on")
			})
			
			//删除table行
			$("#tab1").on('click','button',function(){
				var total = 0;
				$(".sku-tr").each(function(){
					total++;
				})
				if(total<=1){
					alert("最少要有一个SKU");
				}else{
					$(this).parents("tr").remove();
				}
					
			})
			
			//添加table行
			var str='<tr class="sku-tr"><td></td>'+
					'<td><input type="text" size="5" name="attrColorValue" class="textInput"/></td>'+
					'<td><input type="text" size="5" name="attrSpecValue" class="textInput"/></td>'+
					'<td><select name="mainSku" class="mainSku" onchange="mainChange(this);"><option value="1">是</option><option selected="selected" value="0">否</option></select></td>'+
					'<td><input type="text" size="5" name="priceCostPrice" class="textInput"/></td>'+
					'<td><input type="text" size="5" name="priceMarketPrice" class="textInput"/></td>'+
					'<td><input type="text" size="5" name="entityStock" class="textInput"/></td>'+
					'<td></td><td><button>删除</button></td></tr>';

			$(".add-btn").on('click',function(){
				$("#tab1").find("tbody").append(str);
			});	
			
			$(".selbutton").on("click",function(){
				
				var value="";
				if($("#third_category").val()!="" && $("#third_category").val()!=null){
					value=$("#third_category").val();
				}else{
					if($("#two_category").val()!="" && $("#two_category").val()!=null){
						value=$("#two_category").val();
					}else{
						value=$("#one_category").val();
					}
					
				}
				$("#catCode").val(value);
			})
		})
		$("#productCatName").change(function(){
			$("#productCatCode").val($("#productCatName").find("option:selected").attr("data-name"));
		})
	</script>
        
    </div>
</div>