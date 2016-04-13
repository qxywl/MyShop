<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>


<style>
  
</style>
<cas:paginationForm action="${contextPath}/attrgroup/list" page="${page}">
    <input type="hidden" name="groupCode" value="${productAttrGroupModel.groupCode}"/>
    <input type="hidden" name="groupName" value="${productAttrGroupModel.groupName}"/>
    <input type="hidden" name="creator" value="${productAttrGroupModel.creator}"/>
    <input type="hidden" name="createTimes" value="${productAttrGroupModel.createTimes}"/>
    <input type="hidden" name="endTime1" value="${endTime1}"/>
    <input type="hidden" name="updateUser" value="${productAttrGroupModel.updateUser}"/>
    <input type="hidden" name="updateTimes" value="${productAttrGroupModel.updateTimes}"/>
    <input type="hidden" name="endTime2" value="${endTime2}"/>
</cas:paginationForm> 

	<form id="test"method="post" action="${contextPath}/attrgroup/list" onsubmit="return navTabSearch(this)">
	    <div class="pageHeader">
	        <div class="searchBar">
	        <table class="searchContent" style="width:100%;">
			<tbody><tr>
				<td>
					属性组编号：<input type="text" name="groupCode" value="${productAttrGroupModel.groupCode }"/>
				</td>
				<td>
					属性组名称：<input type="text" name="groupName" value="${productAttrGroupModel.groupName }"/>
				</td>
				<td>
					创建者：<input type="text" name="creator" value="${productAttrGroupModel.creator }"/>
				</td>
				<td>
					创建时间：<input type="text" class="date" name="createTimes" size="15" dateFmt="yyyy-MM-dd" value="${productAttrGroupModel.createTimes}" readonly="true"/>
                     -
                     <input type="text" class="date" name="endTime1" size="15" dateFmt="yyyy-MM-dd" value="${endTime1 }" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td>
					最后修改者：<input type="text" name="updateUser" value="${productAttrGroupModel.updateUser }"/>
				</td>
				<td>
					最后修改时间：<input type="text" class="date" name="updateTimes" size="15" dateFmt="yyyy-MM-dd" value="${productAttrGroupModel.updateTimes}" readonly="true"/>
                     -
                     <input type="text" class="date" name="endTime2" size="15" dateFmt="yyyy-MM-dd" value="${endTime2 }" readonly="true"/>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div></td>
			</tr>
		</tbody></table>
	        </div>
	    </div>
	</form>
<div class="pageContent">

    <div class="panelBar">
        <ul class="toolBar">
          <li><a class="add"  target="dialog" rel="" mask="true" width="500" height="300" href="${contextPath}/attrgroup/to_add"><span>添加属性组</span></a></li>
          <li><a class="edit" target="dialog" rel="" mask="true" width="500" height="300" href="${contextPath}/attrgroup/to_update/{slt_uid}"><span>编辑属性组</span></a></li>
         <li><a class="add"  target="dialog" rel="nobundDia" mask="true" width="800" height="400" href="${contextPath}/attrgroup/to_nobundList/{slt_uid}"><span>新增关联属性项</span></a></li>
         <li><a class="icon" target="dialog" rel="relationDia" mask="true" width="800" height="400" href="${contextPath}/attrgroup/to_relationattr/{slt_uid}"><span>查看关联属性项</span></a></li>
       	 
       	  <%-- <li><a class="edit" target="dialog" rel="relationDialog" mask="true" width="1000" height="600" href="${contextPath}/attrgroup/to_relationattrPannel/{slt_uid}"><span>相关联属性</span></a></li>--%>
       	  <li><a class="edit" target="dialog" rel="" mask="true" width="500" height="450"  href="${contextPath}/attrgroup/to_relationcate/{slt_uid}"><span>相关联分类</span></a></li>
       	  <li><a class="delete" target="ajaxTodo"    rel="" href="${contextPath}/attrgroup/delete/{slt_uid}" title="确认要删除?"  onsubmit="return navTabSearch(this);"><span>删除属性组</span></a></li>
        </ul>
    </div>
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
               <!--   <th ><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->           
                <th >属性组编号 </th>
                <th >属性组名称 </th>
                <th >属性组状态</th>
                <th >最后修改时间</th>
                <th >最后修改者 </th>
                <th >创建者</th>
                <th >创建时间</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${attrGroupList!=null}">
	            <c:forEach var="attrGroupModel" items="${attrGroupList}">
	            <tr target="slt_uid" rel="${attrGroupModel.groupId}" style="text-align: center;">
	               <!--   <td><input name="ids" value="${attrGroupModel.groupId}" type="checkbox" class="checkboxCtrl"></td>-->
	                <td>${attrGroupModel.groupCode}</td>
	                <td>${attrGroupModel.groupName}</td>
	                <td>    
	                  <c:choose>
			              <c:when test="${attrGroupModel.groupStatus==1}">
			                                                             启用 
			              </c:when>
			              <c:when test="${attrGroupModel.groupStatus==2}">
			                                                             禁用 
			              </c:when>
			              <c:otherwise>
			                                                             已删除
			              </c:otherwise>
		              </c:choose>
	                </td>
	                <td>${attrGroupModel.updateTimes}</td>
	                <td>${attrGroupModel.updateUser}</td>
	                <td>${attrGroupModel.creator}</td>
	                <td><%-- <fmt:formatDate value="${attrGroupModel.createTimes}"  type="date" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
	                    ${attrGroupModel.createTimes}
	                </td>
	            </tr>           
	           </c:forEach>
            </c:if>
        </tbody>
    </table>
    <!-- 分页 -->
    <cas:pagination page="${page}"/>
</div>