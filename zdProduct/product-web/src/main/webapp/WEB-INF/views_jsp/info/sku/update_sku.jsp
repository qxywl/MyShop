<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views_jsp/common/include_head.jsp"%>
<div class="pageContent">
<form method="POST" action="${contextPath}/info/sku/update" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
    <div class="pageFormContent" layoutH="58">
    	<div style="width:100%;heighe:15%;line-height: 75px;font-size: 20px;font-weight: bold;text-align: center;">编辑SKU属性：</div>
    	<input type="hidden" name="productSkuId" value="${productSkuModel.productSkuId }">
        <p>
            <label>SKU编号：</label>
            <input type="text" name="productSkuCode" value="${productSkuModel.productSkuCode }" readonly="readonly" size="20"/>
        </p>
        <p>
            <label>颜色属性名称：</label>
            <input type="text" name="attrColorName" value="${productSkuModel.attrColorName }"  size="20"/>
        </p>
        <p>
            <label>颜色属性值：</label>
            <input type="text" name="attrColorValue" value="${productSkuModel.attrColorValue }"  size="20"/>
        </p>
        <p>
            <label>尺码属性名称：</label>
            <input type="text" name="attrSpecName" value="${productSkuModel.attrSpecName }"  size="20"/>
        </p>
        <p>
            <label>尺码属性值：</label>
            <input type="text" name="attrSpecValue" value="${productSkuModel.attrSpecValue }"  size="20"/>
        </p>
        <p>
            <label>是否主sku：</label>
            <input type="text" name="mainSku" value="${productSkuModel.mainSku }"  size="20"/>
        </p>
    </div>
            
    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
            <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
        </ul>
    </div>
</form>
</div>