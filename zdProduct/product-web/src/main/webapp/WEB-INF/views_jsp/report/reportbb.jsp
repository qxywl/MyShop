<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>

	<script type="text/javascript">
	
	function adjustOutFrame(iframeName)
	{
		dyniframesizeHeight(iframeName);
		dyniframesizeWidth(iframeName);
	}
	
	function adjustCondFrame(iframe)
	{
		iframe.height=report_cond_frame.document.body.scrollHeight;
	}
	
	function autoSubmit(iframe)
	{
	    document.frames['top'].document.getElementById('html').click();
	}
</script>

<%-- <table width="100%">
<tr valign="top" >
<td  width="100%">
<iframe   align="top"  id="top"  name="top" width="100%" frameborder="0"   scrolling="no"  onload="adjustCondFrame(this);autoSubmit(this);"   src="${pageContext.request.contextPath}/report/paramet?reportId=${reportId}"  ></iframe>
</td>
</tr>
<tr valign="top">
<td>
<iframe align="top" id="mainrep"   name="mainrep" width="100%" height="650" frameborder="0"   scrolling="no" onload="adjustOutFrame('mainrep');"  src="${pageContext.request.contextPath}/report/content?reportId=${reportId}"    ></iframe>
</td>
</tr>
</table> --%>





<iframe   align="top"  id="top"  name="top" width="100%" frameborder="0"   scrolling="no"  onload="adjustCondFrame(this);autoSubmit(this);"   src="${pageContext.request.contextPath}/report/paramet?reportId=${reportId}"  ></iframe>

<div class="pageContent">
	    <div class="panelBar">
	        <ul class="toolBar">
	        </ul>
	    </div>
	    
	    <iframe align="top" id="mainrep"   name="mainrep" width="100%" height="650" frameborder="0"   scrolling="no" onload="adjustOutFrame('mainrep');"  src="${pageContext.request.contextPath}/report/content?reportId=${reportId}"    ></iframe>
</div>
