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
    <div class="pageFormContent" layoutH="58" >
    		<input type="hidden" name="staus" class="staus" value="2">
	    	<input type="hidden" name="productInfoAuthId" value="${productInfoAuthModel.productInfoAuthId }">
    	
    		<div id="valueDiv" style="height:800px;">
    			<div class="title_ice02"><b>基本内容</b></div>
	        	<table class="mytable" cellspacing="0">
					<tr> 
					<th scope="col">属性名称</th> 
					<th scope="col">值</th> 
					</tr>
					<tr> 
						<td class="row">商品名称</td> 
						<td class="row newValue">${newProduct.productName }</td>
					</tr>
					<tr> 
						<td class="row">商品短名称</td> 
						<td class="row newValue">${newProduct.productOperateModel.productShortName }</td>
					</tr>
					<tr> 
						<td class="row">商品简介</td> 
						<td class="row newValue">${newProduct.productDesc }</td>
					</tr>
					<tr> 
						<td class="row">品牌名称</td> 
						<td class="row newValue">${newProduct.brandName }</td>
					</tr>
					<tr> 
						<td class="row">商品前缀</td> 
						<td class="row newValue">${newProduct.productOperateModel.productPrefix }</td>
					</tr>
					<tr> 
						<td class="row">商品后缀</td> 
						<td class="row newValue">${newProduct.productOperateModel.productSuffix }</td>
					</tr>
					<tr> 
						<td class="row">关键字</td> 
						<td class="row newValue">${newProduct.productOperateModel.productKeyword }</td>
					</tr>
					<tr> 
						<td class="row">是否下架显示</td> 
						<td class="row newValue">
							<c:if test="${newProduct.productOperateModel.productDownShow==1 }">是</c:if>
							<c:if test="${newProduct.productOperateModel.productDownShow==2 }">否</c:if>
						</td>
					</tr>
					<tr> 
						<td class="row">加价率</td> 
						<td class="row newValue"><c:if test="${newProduct.productOperateModel.productPriceRate!=0 }">${newProduct.productOperateModel.productPriceRate }</c:if></td>
					</tr>
				</table> 
				<br>
				<div class="title_ice02"><b>商品信息</b></div>
	        	<table class="mytable" cellspacing="0">
	        		<tr> 
						<th scope="col">属性名称</th> 
						<th scope="col">值</th> 
					</tr>
	        		<tr> 
						<td class="row">单品重量</td> 
						<td class="row newValue"><c:if test="${newProduct.productMallModel.productWeight!=0 }">${newProduct.productMallModel.productWeight }</c:if></td>
					</tr>
					<tr> 
						<td class="row">(包装) 长</td> 
						<td class="row newValue"><c:if test="${newProduct.productMallModel.productLong!=0 }">${newProduct.productMallModel.productLong }</c:if></td>
					</tr>
					<tr> 
						<td class="row">(包装) 宽</td> 
						<td class="row newValue"><c:if test="${newProduct.productMallModel.productWidth!=0 }">${newProduct.productMallModel.productWidth }</c:if></td>
					</tr>
					<tr> 
						<td class="row">(包装) 高</td> 
						<td class="row newValue"><c:if test="${newProduct.productMallModel.productHeight!=0 }">${newProduct.productMallModel.productHeight }</c:if></td>
					</tr>
					<tr> 
						<td class="row">密度</td> 
						<td class="row newValue"><c:if test="${newProduct.productMallModel.productDensity!=0 }">${newProduct.productMallModel.productDensity }</c:if> </td>
					</tr>
					<tr> 
						<td class="row">产地</td> 
						<td class="row newValue">${newProduct.productMallModel.productProducer }</td>
					</tr>
	        	</table>
	        	
	        	<br>
				<div class="title_ice02"><b>商品属性</b></div>
	        	<table class="mytable" cellspacing="0">
	        		<tr> 
						<th scope="col">属性名称</th> 
						<th scope="col">值</th> 
					</tr>
	        		<c:if test="${newProduct.productAttributeList!=null }">
	        			<c:forEach items="${newProduct.productAttributeList }" var="changeAttribute">
	        				<tr> 
								<td class="row">${changeAttribute.productAttributeName }</td> 
								<td class="row newValue" >${changeAttribute.productAttributeValue }</td> 
							</tr>
	        			</c:forEach>
	        		</c:if>
	        	</table>
	        	
	        	<br>
				<div class="title_ice02"><b>商品描述(大字段)</b></div>
				
				<table class="mytable" cellspacing="0">
	        		<tr> 
	        		    <th scope="col">PC端描述</th> 
						<th scope="col">APP端描述</th> 
	        	    </tr>
	        	    <tr> 
								<td class="row"><textarea rows="10" cols="20">${newProduct.productInfoModel.appProductInfo }</textarea></td> 
								<td class="row newValue" ><textarea rows="10" cols="20">${newProduct.productInfoModel.pcProductInfo }</textarea></td> 
					</tr>
	        	    
	        	</table>	
	        	
				<%-- <br>
				<div class="title_ice02"><b>新增销售属性</b></div>
	        	<table class="mytable" cellspacing="0">
	        		<tr> 
						<th scope="col">属性名称</th> 
						<th scope="col">旧值</th> 
						<th scope="col">新值</th>
						<th scope="col">旧值</th> 
						<th scope="col">新值</th> 
						<th scope="col">旧值</th> 
						<th scope="col">新值</th> 
						<th scope="col">旧值</th> 
						<th scope="col">新值</th>  
					</tr>
					<tr> 
						<td class="row">APP端描述</td> 
						<td class="row">${oldProduct.productInfoModel.appProductInfo }</td> 
						<td class="row newValue">${newProduct.productInfoModel.appProductInfo }</td>
					</tr>
					<tr> 
						<td class="row">PC端描述</td> 
						<td class="row">${oldProduct.productInfoModel.pcProductInfo }</td> 
						<td class="row newValue">${newProduct.productInfoModel.pcProductInfo }</td>
					</tr>
				</table> --%>
        	</div>
    </div>
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</div>
