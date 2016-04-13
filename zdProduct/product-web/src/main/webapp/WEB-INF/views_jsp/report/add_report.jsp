<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
	table tr{
		width:100%;
	} 
	table tr td{
		width:30%;
	}
	.reportConTable tr{
		height:40px;
		line-height: 40px;
	}
	.reportConTable td{
		width:20%;
	}
	
</style>
<div class="pageContent">
	<form method="POST" action="${contextPath}/report/add"
		class="required-validate pageForm"
		onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<div class="pageFormContent" layoutH="70">
			<table style="width:100%;">
			<tr><td>
				<p style="width:100%;">
					<label>报表名称：</label>
					<input type="text" name="reportName" size="50" class="required" value="" />
				</p>
				</td>
				<td>
				<p style="width:100%;">
					<label>展示列中文名称：</label>
					<input type="text" name="reportShowName" size="50" class="required" value="" />
				</p></td>
				<td>
				<p style="width:100%;">
					<label>展示列英文名称：</label>
					<input type="text" name="reportShowEnName" size="50" class="required" value="" />
				</p></td>
				<td>
			</tr>
			<tr><td>
				<p style="width:100%;">
					
				</p>
				</td>
			</tr>
			<tr>
				<td>
				<p style="width:100%;">
					<label>报表sql：</label>
					<input type="text" name="reportSql" size="50" class="required" value="" />
				</p></td>
				<td>
				<p style="width:100%;">
					<label>报表总数sql：</label>
					<input type="text" name="reportCountSql" size="50" class="required" value="" />
				</p></td>
			</tr>
			</table>
			<!-- <br><br>
			<div><input type="button" onclick="addReportOperate();" value="新增操作"/></div>
			<br><br>
			<table class="reportOperateTable" id="reportOperateTable" style="width:100%">
				
			</table> -->
			<br><br>
			<div><input type="button" onclick="addReportCon();" value="新增查询属性"/></div>
			<br><br>
			<table class="reportConTable" id="reportConTable" style="width:100%">
				<!-- <tr>
					<td>
						<label>展示名称：</label>
						<input type="text" name="reportCon.conName" class="required" value="" />
					</td>
					<td>
						<label>属性名称：</label>
						<input type="text" name="reportCon.conProperty" class="required" value="" />
					</td>
					<td>
						<label>所属类型：</label>
						<select name="reportCon.conType">
							<option value="1">文本相等</option>
							<option value="2">文本模糊</option>
							<option value="3">下拉框</option>
							<option value="4">时间</option>
							<option value="5">时间段</option>
						</select>
					</td>
					<td>
						<label>默认值：</label>
						<input type="text" name="reportCon.conDefault" value="" />
					</td>
					<td>
						<label>执行SQL：</label>
						<input type="text" name="reportCon.conSql" value="" />
					</td>
				</tr> -->
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

<script>
	var i = 0;
	function addReportCon(){
		var conTableHtml = "<tr><td><label>展示名称：</label><input type='text' size='20' name='reportConList["+i+"].conName' class='required' value='' />"+
		" </td><td><label>属性名称：</label><input type='text' name='reportConList["+i+"].conProperty' size='20' class='required' value='' />"+
		" </td><td><label>所属类型：</label><select name='reportConList["+i+"].conType'><option value='1'>文本相等</option>"+
        " <option value='2'>文本模糊</option>"+
        " <option value='3'>下拉框</option>"+
        " <option value='4'>时间</option>"+
        " <option value='5'>时间段</option>"+
        " </select></td>"+
        " <td><label>执行SQL：</label><input type='text' name='reportConList["+i+"].conSelectSql' size='20' value='' /></td>"+
        " <td><label>查询字段：</label><input type='text' name='reportConList["+i+"].conSelectPropertyName' size='20' value='' class='required' /></td>"+
        " <td><input type='button' onclick='deleteCon(this);' value='删除' /></td></tr>";
		 $(".reportConTable").append(conTableHtml);
		i++;
	}
	function deleteCon(element){
		var i=element.parentNode.parentNode.rowIndex;
        document.getElementById('reportConTable').deleteRow(i);
	}
</script>
