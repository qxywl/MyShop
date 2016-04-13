<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
</style>
<script type="text/javascript">
	function myCallback()
	{
		$.pdialog.reload("${contextPath}/attrgroup/to_relationattr/${attrGroupId}")
	}
</script>

<div class="pageContent" >
<cas:paginationForm action="${contextPath}/attrgroup/to_relationattr/${attrGroupId}" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm> 

 <form method="post" action="${contextPath}/attrgroup/to_relationattr/${attrGroupId}" onsubmit="return dialogSearch(this)">
	       <div class="pageHeader">
	        <div class="searchBar">
	              <table class="searchContent">
			<tbody><tr>
				<td>
					属性编号：<input type="text" name="attrCode" value="${productAttrModel.attrCode }"/>
				</td>
				<td>
					属性名称：<input type="text" name="attrName" value="${productAttrModel.attrName }"/>
				</td>
				<td>
					属性别名：<input type="text" name="attrEname" value="${productAttrModel.attrEname }"/>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div></td>
			</tr>
		</tbody></table>
	        </div>
	    </div>
	</form>

    <div class="panelBar">
        <ul class="toolBar">
          <li><a class="delete" callback="myCallback" href="${contextPath}/attrgroup/delRelation/${attrGroupId}" title="确认要取消这些属性关联?"  rel="attrIds" targetType ="dialog" target="selectedTodo" onsubmit="return dialogSearch(this);"><span>取消属性项</span></a></li>
          <li><a class="icon" id="scanValue" target="dialog" rel="attrValueDialog" mask="true" width="1000" height="500" href="${contextPath}/attrgroup/to_listAttrValue/{slt_uid}"><span>查看属性值</span></a></li>
        </ul>
    </div>
    <table class="table" layoutH="110" width="100%">
         <thead>
            <tr>
                <th ><input type="checkbox" group="attrIds" class="checkboxCtrl"></th>            
                <th >属性编号 </th>
                <th >属性名称 </th>
                <th >属性别名 </th>
                <th >属性说明</th>
                <th >是否预设项</th>
                <th >排序号</th>
                <th >最后修改时间</th>
                <th >最后修改者 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${productAttrModelList!=null}">
	            <c:forEach var="attrModel" items="${productAttrModelList}">
	            <tr target="slt_uid" rel="${attrModel.attrId}" style="text-align: center;">
	                <td><input name="attrIds" value="${attrModel.attrId}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${attrModel.attrCode}</td>
	                <td>${attrModel.attrName}</td>
	                <td>${attrModel.attrEname}</td>
	                <td>${attrModel.attrInfo}</td>
	                <td>    
	                  <c:choose>
			              <c:when test="${attrModel.attrDesign==1}">
			                                                              是
			              </c:when>
			              <c:when test="${attrModel.attrDesign==2}">
			                                                             否
			              </c:when>
			              <c:otherwise>
			                         -
			              </c:otherwise>
		              </c:choose>
	                </td>
	                <td>${attrModel.attrNum}</td>
	                <td>${attrModel.updateTimes}</td>
	                <td>${attrModel.updateUser}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
	</div>



	
