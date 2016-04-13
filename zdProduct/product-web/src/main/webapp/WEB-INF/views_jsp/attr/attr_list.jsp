<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<script type="text/javascript">
	function hiddenOrShowAttrValueList(attrDesign)
	{
		if(attrDesign == 2)
		{
			$("#scanValue").css("display","none");
		}
		else if(attrDesign == 1)
		{
			$("#scanValue").css("display","block");
		}
	}
</script>
<cas:paginationForm action="${contextPath}/attr/list" page="${page}">
    <input type="hidden" name="attrCode" value="${productAttrModel.attrCode}"/>
    <input type="hidden" name="attrName" value="${productAttrModel.attrName}"/>
    <input type="hidden" name="attrEname" value="${productAttrModel.attrEname}"/>
    <input type="hidden" name="creator" value="${productAttrModel.creator}"/>
    <input type="hidden" name="createTimes" value="${productAttrModel.createTimes}"/>
    <input type="hidden" name="endTime1" value="${endTime1}"/>
    <input type="hidden" name="updateUser" value="${productAttrModel.updateUser}"/>
    <input type="hidden" name="updateTimes" value="${productAttrModel.updateTimes}"/>
    <input type="hidden" name="endTime2" value="${endTime2}"/>
</cas:paginationForm> 

	<form id="test"method="post" action="${contextPath}/attr/list" onsubmit="return navTabSearch(this)">
	    <div class="pageHeader">
	        <div class="searchBar">
	            <table class="searchContent" style="width:100%;">
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
				<td>
					创建者：<input type="text" name="creator" value="${productAttrModel.creator }"/>
				</td>
				<td>
					创建时间：<input type="text" class="date" name="createTimes" size="15" dateFmt="yyyy-MM-dd" value="${productAttrModel.createTimes}" readonly="true"/>
                     -
                     <input type="text" class="date" name="endTime1" size="15" dateFmt="yyyy-MM-dd" value="${endTime1 }" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td>
					最后修改者：<input type="text" name="updateUser" value="${productAttrModel.updateUser }"/>
				</td>
				<td>
					最后修改时间：<input type="text" class="date" name="updateTimes" size="15" dateFmt="yyyy-MM-dd" value="${productAttrModel.updateTimes}" readonly="true"/>
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
          <li><a class="add"  target="dialog" rel="" mask="true" width="500" height="500" href="${contextPath}/attr/to_add"><span>添加属性</span></a></li>
          <li><a class="edit" target="dialog" rel="attrList" mask="true" width="500" height="350" href="${contextPath}/attr/to_update/{slt_uid}"><span>编辑属性</span></a></li>
       	  <li><a class="delete"  target="selectedTodo" rel="ids" href="${contextPath}/attr/delete" title="确认要删除?"  onsubmit="return navTabSearch(this);"><span>删除属性</span></a></li>
          <li><a class="icon" id="scanValue" target="dialog" rel="attrValueDialog" mask="true" width="1000" height="500" href="${contextPath}/attr/to_listAttrValue/{slt_uid}"><span>查看属性值</span></a></li>
        </ul>
    </div>
    
    <table class="table" layoutH="137" width="100%">
        <thead>
            <tr>
                <th ><input type="checkbox" group="ids" class="checkboxCtrl"></th>            
                <th >属性编号 </th>
                <th >属性名称 </th>
                <th >属性别名 </th>
                <th >属性说明</th>
                <th >是否预设项</th>
                <th >是否必填项</th>
                <th >排序号</th>
                <th >创建时间</th>
                <th >创建者 </th>
                <th >最后修改时间</th>
                <th >最后修改者 </th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${attrList!=null}">
	            <c:forEach var="attrModel" items="${attrList}">
	            <tr target="slt_uid" rel="${attrModel.attrId}" style="text-align: center;" onclick="hiddenOrShowAttrValueList(${attrModel.attrDesign})">
	                <td><input name="ids" value="${attrModel.attrId}" type="checkbox" class="checkboxCtrl"></td>
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
	                <td>    
	                  <c:choose>
			              <c:when test="${attrModel.required==1}">
			                                                              是
			              </c:when>
			              <c:when test="${attrModel.required==0}">
			                                                             否
			              </c:when>
			              <c:otherwise>
			                         -
			              </c:otherwise>
		              </c:choose>
	                </td>
	                <td>${attrModel.attrNum}</td>
	                <td>${attrModel.createTimes}</td>
	                <td>${attrModel.creator}</td>
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