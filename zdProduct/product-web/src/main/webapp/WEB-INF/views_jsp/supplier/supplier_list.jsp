<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>

<style>
  .grid .gridTbody td div {
    display: block;
    overflow: hidden;
    height: 60px;
    white-space: nowrap;
    line-height:44px;
  }
  .img_div{
    display: block;
    overflow: hidden;
    height: 44px;
    white-space: nowrap;
    width:70px;
    line-height:  44px;
  }

</style>

<cas:paginationForm action="${contextPath}/supplier/list" page="${page}">
  <input type="hidden" name="brandCode" value="${supplierModel.id}"/>
  <input type="hidden" name="brandName" value="${supplierModel.name}"/>
</cas:paginationForm>

<form id="test"method="post" action="${contextPath}/supplier/list" onsubmit="return navTabSearch(this)">
  <div class="pageHeader">
    <div class="searchBar">
      <table class="searchContent">
        <tbody><tr>
          <td>
           供应商代码：<input type="text" name="supplierCode" value="${supplierModel.supplierCode}" class="textInput">
          </td>
          <td>
            供应商名称：<input type="text" name=name value="${supplierModel.name}" class="textInput">
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
      <li><a class="icon" href="${contextPath}/supplier/to_import"  target="dialog"   width="530" height="250" title=""><span>导入excel</span></a></li>
    </ul>
  </div>

  <table class="table" layoutH="137" width="100%">
    <thead>
    <tr>
      <th ><input type="checkbox" group="ids" class="checkboxCtrl"></th>
      <th> 供应商代码</th>
      <th >供应商名称 </th>
      <th >供应商登录名称</th>
      <th >供应商店铺名称</th>
      <th >状态  </th>
      <th >联系人手机</th>
      <th >联系人邮箱</th>
      <th >创建时间</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${supplierModelList!=null}">
      <c:forEach var="supplierModel" items="${supplierModelList}">
        <tr target="slt_uid" rel="${supplierModel.id}" style="text-align: center;" height="60px">
          <td><input name="ids" value="${supplierModel.id}" type="radio" class="checkboxCtrl"></td>
          <td>${supplierModel.supplierCode}</td>
          <td>${supplierModel.name}</td>
          <td>${supplierModel.userName}</td>
          <td>${supplierModel.shopName}</td>
          <td>
            <c:choose>
            <c:when test="${supplierModel.status == 1}">
              激活
            </c:when>
            <c:when test="${supplierModel.status == 2}">
              未激活
            </c:when>
            <c:when test="${supplierModel.status == 3}">
              作废
            </c:when>
            <c:otherwise>
              状态错误
            </c:otherwise>
            </c:choose>
          </td>
          <td>${supplierModel.contactsPhone}</td>
          <td>${supplierModel.contactsEmail}</td>
          <td><%-- <fmt:formatDate value="${brandModel.createTimes}"  type="date" pattern="yyyy-MM-dd HH:mm:ss" /> --%>
              ${supplierModel.createTimes}
          </td>
        </tr>
      </c:forEach>
    </c:if>
    </tbody>
  </table>
  <!-- 分页 -->
  <cas:pagination page="${page}"/>
</div>