<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">
	
	$(function(){
	
	})
	function back()
	{
		$.pdialog.reload("${contextPath}/attr/to_listAttrValue/${attrId}", "attrValueDialog", "属性值");
	}
</script>
<%-- <cas:paginationForm action="${contextPath}/attr/list" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
</cas:paginationForm> 
--%>
<div class="pageContent" >

    <div class="panelBar">
        <ul class="toolBar">
  <%-- <li><a class="add"  target="dialog" rel="" target="selectedTodo" callback="back" mask="true" width="1000" height="300" href="${contextPath}/attr/to_addAttrValue/${attrId}"><span>添加属性值</span></a></li>
       --%>  
          <li><a class="edit" target="dialog" rel="" mask="true" width="500" height="300" href="${contextPath}/attr/to_updateattrvalue/{slt_uid}?attrId=${attrId}"><span>编辑属性值</span></a></li>
       	  <li><a class="delete" targetType ="dialog" callback="back" target="selectedTodo"  rel="attrValueIds" href="attr/deleteAttrValue" title="确认要删除这些属性值?"><span>删除属性值</span></a></li>
       	  	<li><a class="add"  target="dialog" rel="" mask="true" width="800" height="300" href="${contextPath}/attr/to_addAttrValue/${attrId}"><span>添加属性值</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th ><input type="checkbox" group="attrValueIds" class="checkboxCtrl"></th>            
                <th >属性值编码 </th>
                <th >属性值 </th>
                <th >排序号</th>
                <th >最后修改时间</th>
                <th >最后修改者 </th>
            </tr>
        </thead>
        <tbody >
            <c:if test="${valueList!=null}">
	            <c:forEach var="attrValueModel" items="${valueList}">
	            <tr target="slt_uid" rel="${attrValueModel.attrValueId}" style="text-align: center;">
	             <td><input name="attrValueIds" value="${attrValueModel.attrValueId}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${attrValueModel.attrValueCode}</td>
	                <td>${attrValueModel.attrValue}</td>
	                <td>${attrValueModel.attrValueNum}</td>
	                <td>${attrValueModel.updateTimes}</td>
	                <td>${attrValueModel.updateUser}</td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
   <%-- <cas:pagination page="${page}"/>--%>
</div>