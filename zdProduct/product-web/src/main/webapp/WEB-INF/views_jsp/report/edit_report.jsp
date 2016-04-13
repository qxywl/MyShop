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
		width:16%;
	}
	
</style>
<div class="pageContent">
	<form method="POST" action="${contextPath}/report/edit"
		class="required-validate pageForm"
		onsubmit="return validateCallback(this, dialogReloadNavTab);">
		<div class="pageFormContent" layoutH="70">
			<table style="width:100%;">
			<tr><td>
				<p style="width:100%;">
					<label>报表编号：</label>
					<input type="text" name="reportId" size="50" readonly="readonly" value="${reportModel.reportId }" />
				</p>
				</td>
				<td>
				<p style="width:100%;">
					<label>报表名称：</label>
					<input type="text" name="reportName" size="50" class="required" value="${reportModel.reportName }" />
					<input type="hidden" name="reportId" value="${reportModel.reportId }" />
				</p>
				</td>
				<td>
				<p style="width:100%;">
					<label>展示列中文名称：</label>
					<input type="text" name="reportShowName" size="50" class="required" value="${reportModel.reportShowName }" />
				</p></td>
			</tr>
			<tr><td>
				<p style="width:100%;"></p>
				</td>
			</tr>
			<tr><td>
				<p style="width:100%;">
					<label>展示列英文名称：</label>
					<input type="text" name="reportShowEnName" size="50" class="required" value="${reportModel.reportShowEnName }" />
				</p></td>
				<td>
				<p style="width:100%;">
					<label>报表sql：</label>
					<input type="text" name="reportSql" size="50" class="required" value="${reportModel.reportSql }" />
				</p></td>
				<td>
				<p style="width:100%;">
					<label>报表总数sql：</label>
					<input type="text" name="reportCountSql" size="50" class="required" value="${reportModel.reportCountSql }" />
				</p></td>
			</tr>
			</table>
			<br><br>
			<div><input type="button" onclick="addReportCon();" value="新增查询属性"/></div>
			<br><br>
			<table class="reportConTable" id="reportConTable" style="width:100%">
				<c:forEach items="${reportConList }" varStatus="status" var="reportCon">
					<tr>
						<td>
							<label>展示名称：</label>
							<input type="text" name="reportConList[${status.index }].conName" class="required" value="${reportCon.conName }" />
							<input type="hidden" name="reportConList[${status.index }].conId" value="${reportCon.conId }" />
						</td>
						<td>
							<label>属性名称：</label>
							<input type="text" name="reportConList[${status.index }].conProperty" class="required" value="${reportCon.conProperty }" />
						</td>
						<td>
							<label>所属类型：</label>
							<select name="reportConList[${status.index }].conType">
								<option <c:if test="${reportCon.conType==1 }">selected="selected"</c:if> value="1">文本相等</option>
								<option <c:if test="${reportCon.conType==2 }">selected="selected"</c:if> value="2">文本模糊</option>
								<option <c:if test="${reportCon.conType==3 }">selected="selected"</c:if> value="3">下拉框</option>
								<option <c:if test="${reportCon.conType==4 }">selected="selected"</c:if> value="4">时间</option>
								<option <c:if test="${reportCon.conType==5 }">selected="selected"</c:if> value="5">时间段</option>
							</select>
						</td>
						<td>
							<label>执行SQL：</label>
							<input type="text" name="reportConList[${status.index }].conSelectSql" value="${reportCon.conSelectSql }" />
						</td>
						<td>
							<label>查询字段：</label>
							<input type="text" name="reportConList[${status.index }].conSelectPropertyName" value="${reportCon.conSelectPropertyName }" class="required" />
						</td>
						<td>
							<input type="button" onclick="deleteCon(this);" value="删除" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" class="conIndex" value="${fn:length(reportConList) }" />
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
	
	function addReportCon(){
		var i = Number($(".conIndex").val());
		
		var conTableHtml = "<tr><td><label>展示名称：</label><input type='text' size='20' name='reportConList["+i+"].conName' class='required' value='' />"+
		" </td><td><label>属性名称：</label><input type='text' name='reportConList["+i+"].conProperty' size='20' class='required' value='' />"+
		" </td><td><label>所属类型：</label><select name='reportConList["+i+"].conType'><option value='1'>文本相等</option>"+
        " <option value='2'>文本模糊</option>"+
        " <option value='3'>下拉框</option>"+
        " <option value='4'>时间</option>"+
        " <option value='5'>时间段</option>"+
        " </select></td>"+
        " <td><label>执行SQL：</label><input type='text' name='reportConList["+i+"].conSelectSql' size='20' class='textInput' value='' /></td>"+
        " <td><label>查询字段：</label><input type='text' name='reportConList["+i+"].conSelectPropertyName' size='20' value='' class='required'/></td>"+
        " <td><input type='button' onclick='deleteCon(this);' value='删除' /></td></tr>";
		 $(".reportConTable").append(conTableHtml);
		 $(".conIndex").val(""+(i+1));
	}
	
	function deleteCon(element){
		var i=element.parentNode.parentNode.rowIndex;
        document.getElementById('reportConTable').deleteRow(i);
	}
</script>
