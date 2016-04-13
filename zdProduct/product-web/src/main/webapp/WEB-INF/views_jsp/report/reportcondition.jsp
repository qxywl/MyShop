<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <form id="form1" name="form1" target="mainrep"  method="post" action="${pageContext.request.contextPath}/report/content">
<input type="hidden" value='${reportId }' name="REPORTID" id="REPORTID"/>
<table width="100%" >
   ${content }
</table>
</form> --%>

<form method="post" target="mainrep" action="${pageContext.request.contextPath}/report/content">
<input type="hidden" value='${reportId }' name="reportId" id="reportId"/>
    <div class="pageHeader">
        <div class="searchBar">
         	<table width="100%" height="100px">
				${content }
			</table>
        </div>
    </div>
</form>
