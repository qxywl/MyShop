<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<style>
	
</style>
<script type="text/javascript">

$(function(){
	$(".tabs").attr("currentIndex",${currentIndex});
})

function firstCallback()
{
	
	$.pdialog.reload("${contextPath}/attrgroup/to_relationattrPannel/${groupId}?currentIndex=0", {data:{}, dialogId:"relationDialog", callback:null}) 
	
}

function secondCallback()
{
	
	$.pdialog.reload("${contextPath}/attrgroup/to_relationattrPannel/${groupId}?currentIndex=1", {data:{}, dialogId:"relationDialog", callback:null}) 
	
}

function changeState(index)
{
	$(":input[name='currentIndex']").val(index);
	alert($(":input[name='currentIndex']").val);
}

function dddd()
{
	alert("callback");
}
</script>

<div class="pageContent" >

<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li id="first"><a id="first" class="j-ajax" callback="dddd" onclick="changeState(0);" href="${contextPath}/attrgroup/to_relationattr/${groupId}" ><span>已关联</span></a></li>
					<li id="seconde" class=""><a  class="j-ajax" onclick="changeState(1);" href="${contextPath}/attrgroup/to_nobundList/${groupId}"><span >未关联</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:500px;">
			<div>
			<div class="pageContent" >
<cas:paginationForm action="${contextPath}/attrgroup/to_relationattrPannel/${groupId}" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/> 
    <input type="hidden" name="currentIndex" value="0"/>
</cas:paginationForm> 

 <form method="post"  action="${contextPath}/attrgroup/to_relationattrPannel/${groupId}?currentIndex=0" onsubmit="return dialogSearch(this)">
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
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div></td>
			</tr>
		</tbody></table>
	        </div>
	    </div>
	</form>

    <div class="panelBar">
        <ul class="toolBar">
          <li><a class="delete" callback="firstCallback" href="${contextPath}/attrgroup/delRelation/${groupId}" title="确认要取消这些属性关联?"  rel="attrIds" targetType ="dialog" target="selectedTodo" ><span>取消属性项</span></a></li>
        </ul>
    </div>
    <table class="table" layoutH="137" width="100%">
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
    <cas:pagination page="${relationPage}"/>
	</div>
	</div>
			<div rel="secondContent">
			<div class="pageContent" >
<cas:paginationForm action="${contextPath}/attrgroup/to_relationattrPannel/${groupId}" page="${page}">
    <input type="hidden" name="domainName" value="${domainName}"/>
    <input type="hidden" name="currentIndex" value="1"/>
</cas:paginationForm> 

 <form method="post" action="${contextPath}/attrgroup/to_relationattrPannel/${groupId}?currentIndex=1" onsubmit="return dialogSearch(this)">
	       <div class="pageHeader">
	        <div class="searchBar">
	            <ul class="searchContent">
	                <li>
	                    <label>属性编号：</label>
	                    <input type="text" name="attrCode" value=""/>
	                </li>
	                <li>
	                    <label>属性名称：</label>
	                    <input type="text" name="attrName" value=""/>
	                </li>
	                <li>
	                 <div class="button"><div class="buttonContent"><button type="submit">&nbsp;搜索&nbsp;</button></div></div>
	                 </li>
	            </ul>
	            <div class="subBar">
	            </div>
	        </div>
	    </div>
	</form>

    <div class="panelBar">
        <ul class="toolBar">
          <li><a class="delete"  href="${contextPath}/attrgroup/addRelation/${groupId}" callback="back" title="确认要关联这些属性?" rel="addIds"  target="selectedTodo" ><span>关联属性项</span></a></li>
        </ul>
    </div>
    <table class="table" layoutH="137" width="100%">
         <thead>
            <tr>
                <th ><input type="checkbox" group="addIds" class="checkboxCtrl"></th>            
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
	            <c:forEach var="noRelationModel" items="${productAttrModelList}">
	            <tr target="slt_uid" rel="${noRelationModel.attrId}" style="text-align: center;">
	                <td><input name="addIds" value="${noRelationModel.attrId}" type="checkbox" class="checkboxCtrl"></td>
	                <td>${noRelationModel.attrCode}</td>
	                <td>${noRelationModel.attrName}</td>
	                <td>${noRelationModel.attrEname}</td>
	                <td>${noRelationModel.attrInfo}</td>
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
			
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>




	
