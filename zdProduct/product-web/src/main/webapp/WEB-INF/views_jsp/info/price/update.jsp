<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
.leftSelect {
	text-align: left;
}

.tablebox {
	width: 80%;
	margin: 15px auto;
	border: 1px solid #ddd;
	border-collapse: collapse;
	font-family: "Microsoft YaHei"
}

.tablebox td {
	padding: 10px;
	border: 1px solid #ddd;
}

.tablebox td strong {
	font-weight: normal;
	font-size: 18px;
}

.tablebox td input {
	height: 30px;
	line-height: 30px;
	border-radius: 3px;
	border: 1px solid #999;
	margin-left: 15px;
}

.tablebox td textarea {
	width: 400px;
	height: 150px
}

.tablebox td.hd {
	border-bottom: 1px solid #ddd;
	font-size: 25px;
}

.tablebox td.btn {
	text-align: center;
}

.tablebox td.btn2 {
	text-align: center;
}

.tablebox td.btn button {
	width: 180px;
	height: 45px;
	line-height: 30px;
	margin: 10px 0;
	background-color: #5cb85c;
	color: #fff;
	border: 1px solid #5cb85c;
	-ms-border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	cursor: pointer;
}

.tablebox td.btn2 button {
	width: 180px;
	height: 45px;
	line-height: 30px;
	margin: 10px 0;
	background-color: #2E6DA4;
	color: #fff;
	border: 1px solid #5cb85c;
	-ms-border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	cursor: pointer;
}

.tablebox td.btn button:hover {
	background-color: #449d44;
}

.tablebox a {
	text-decoration: none;
	vertical-align: middle;
}

/*2015-5-29 新增*/
.clearfix:before, .clearfix:after {
	content: "";
	display: table;
}

.clearfix:after {
	clear: both;
	overflow: hidden;
}

.clearfix {
	zoom: 1;
}

.tablebox .tablebox_select {
	padding: 15px;
}

.tablebox .tablebox_select .tablebox_select_list {
	margin-bottom: 10px
}

.tablebox .tablebox_select .tablebox_select_list select {
	height: 30px;
	width: 130px;
	line-height: 30px;
	margin-right: 8px;
	display: inline-block;
	float: left;
	background-color: #FFF;
}

.tablebox .tablebox_select .tablebox_select_list button {
	height: 30px;
	width: 80px;
	line-height: 30px;
	vertical-align: middle;
	cursor: pointer;
	background-color: #d9534f;
	border-color: #d43f3a;
	border: 1px solid transparent;
	color: #fff;
	border-radius: 4px;
}

.tablebox_select_btn {
	background-color: #f0ad4e;
	border-color: #eea236;
	height: 30px;
	width: 200px;
	line-height: 30px;
	border: 1px solid transparent;
	color: #fff;
	border-radius: 4px;
	cursor: pointer;
	margin-bottom: 15px;
	margin-left: 15px;
}
</style>

<div class="pageContent">
	<form method="POST" action="${contextPath}/info/price/update"
		class="required-validate pageForm"
		onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<div class="pageFormContent" layoutH="70">

			<table class="tablebox">

				<tr>
					<td>商品名称</td>
					<td><input type="hidden" name="id"
						value="${ProductPriceLadderModel.id }" />
						 ${ProductPriceLadderModel.productName}
					</td>

				</tr>

				<tr>
					<td>商品编码</td>
					<td>
						<div>
							<input type="hidden" name="productCode"
								value="${ProductPriceLadderModel.productCode }"
								class="validate[required,maxSize[32]] required" size="20"
								maxlength="32" />
								${ProductPriceLadderModel.productCode }
						</div>
					</td>

				</tr>
				
				
				<tr>
					<td>商品id</td>
					<td>
						<div>
							<input type="hidden" name="productId"
								value="${ProductPriceLadderModel.productId }"
								class="validate[required,maxSize[32]] required" size="20"
								maxlength="32" />
								${ProductPriceLadderModel.id }
						</div>
					</td>

				</tr>
				
				
				<tr>
					<td>供应商id</td>
					<td>
						<div>
							<input type="hidden" name="supplierId"
								value="${ProductPriceLadderModel.supplierId }"
								class="validate[required,maxSize[32]] required" size="20"
								maxlength="32" />
								${ProductPriceLadderModel.supplierId }
						</div>
					</td>

				</tr>
				
				<tr>
					<td>一级加价率(‰)(千分比)</td>
					<td>
						<div>
							<input type="text" name="priceLevel1"  readonly="readonly"	
								value="${ProductPriceLadderModel.priceLevel1 }"
								class="validate[required,maxSize[4],custom[signlessInteger]] required" size="20"
								maxlength="32" /><label>[暂时不可修改!]</label>
						</div>
					</td>

				</tr>
				
				
				<tr>
					<td>二级加价率(‰)(千分比)</td>
					<td>
						<div>
							<input type="text" name="priceLevel2"
								value="${ProductPriceLadderModel.priceLevel2 }"
								class="validate[required,maxSize[4],custom[signlessInteger]] required" size="20"
								maxlength="32" />
						</div>
					</td>

				</tr>
				
				<tr>
					<td>三级加价率(‰)(千分比)</td>
					<td>
						<div>
							<input type="text" name="priceLevel3"
								value="${ProductPriceLadderModel.priceLevel3 }"
								class="validate[required,maxSize[4],custom[signlessInteger]] required" size="20"
								maxlength="32" />
						</div>
					</td>

				</tr>
				
				<tr>
					<td>四级加价率(‰)(千分比)</td>
					<td>
						<div>
							<input type="text" name="priceLevel4"
								value="${ProductPriceLadderModel.priceLevel4 }"
								class="validate[required,maxSize[4],custom[signlessInteger]] required" size="20"
								maxlength="32" />
						</div>
					</td>

				</tr>

							

			</table>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="submit">确定</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">关闭</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
