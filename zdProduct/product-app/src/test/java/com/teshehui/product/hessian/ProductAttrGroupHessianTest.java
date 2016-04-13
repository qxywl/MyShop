package com.teshehui.product.hessian;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductAttrGroupManager;
import com.zhaidou.product.po.base.request.RequestAttrGroupValuePO;

public class ProductAttrGroupHessianTest {

	private static final String url ;
	private static final ProductAttrGroupManager stockClient;

	static
	{
		//本地
		/*url = "http://192.168.0.126:8080/product-app/remote/productAttrGroup";
		stockClient =  (ProductAttrGroupManager)HessianClientUtil.getHessianService(url, ProductAttrGroupManager.class);*/
		
		//91
		url = "http://192.168.0.91:49090/remote/productAttrGroup";
		stockClient =  (ProductAttrGroupManager)HessianClientUtil.getHessianService(url, ProductAttrGroupManager.class);
	}
	public void getProductAttrValByCateCode(){
		RequestObject requestObj = new RequestObject();
		
		RequestAttrGroupValuePO  attrPO = new RequestAttrGroupValuePO();
		attrPO.setCateCode("040101");
		requestObj.setRequestParams(attrPO);
		ResponseObject response = stockClient.getProductAttrValByCateCode(requestObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	
	public static void main(String[] args) {
		ProductAttrGroupHessianTest pt = new ProductAttrGroupHessianTest();
		pt.getProductAttrValByCateCode();
	}
	

	
}
