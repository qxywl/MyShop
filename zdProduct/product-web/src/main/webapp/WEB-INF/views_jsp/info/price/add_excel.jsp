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
	width: 450px;
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

.instructions-label label {
	width: 500px;
}

.instructions-label-red label {
    color:red;
	width: 500px;
}
</style>

<div class="pageContent">
	<form method="POST" action="${contextPath}/info/price/add_excel"
		enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, dialogReloadNavTab)">
		<div class="pageFormContent" layoutH="70">
			<div>
				<input type="file" id="file" name="file" value="选择文件"
					class="fontColor" />
			</div>
			<div class="divider"></div>
			<div class="instructions-label">
				<a id="btnDownload" href="${contextPath}/styles/image/addOrUpdatePriceLadderTemplet.xls" target="dwzExport"
					targetType="dialog"><input type="button" onclick="downExcel();"
					value="下载新增或者修改模板" class="fontColor" />点击下载新增或者修改模板excel</a>				
			</div>
			<div class="divider"></div>
			<div class="instructions-label">

				<label>使用说明:</label> <label>1.如果是已经新增过的商品则修改加价率</label>

			</div>

			<div class="instructions-label">
				<label>2.如果是没有新增过的商品则新增加价率</label>
			</div>
			
			<div class="instructions-label">
				<label>3.请注意12位的商品编码要修改成为文本格式,并且很多商品编码前都有0，请注意不要让excel自动去除商品编码前的0</label>
			</div>
			
			<div class="instructions-label">
				<label>4.请注意加价率都是整数，单位为千分比</label>
			</div>
			
				<div class="instructions-label-red">
				<label>5.一级加价率暂时不可修改！</label>
			</div>

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

