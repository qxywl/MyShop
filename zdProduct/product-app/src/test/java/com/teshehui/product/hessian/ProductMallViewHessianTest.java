package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.po.request.RequestMallProductPO;
import com.zhaidou.product.po.request.RequestProductInfoPO;
import com.zhaidou.product.po.request.RequestProductListPO;
import com.zhaidou.product.util.ProductConstantUtil;

public class ProductMallViewHessianTest {
	private static final String url ;
	private static final ProductManager productClient;

	static
	{
		//本地
//        		url = "http://121.42.206.45:5080/remote/productInfo";
		url = "http://127.0.0.1:8080/product-app/remote/productInfo";
		productClient =  (ProductManager)HessianClientUtil.getHessianService(url, ProductManager.class);
	}
	
	
	/******************  商城   **************************************/
	public void getProductInfoByCodes(){
		
		RequestObject requstObj =  new RequestObject();
		RequestProductInfoPO request = new RequestProductInfoPO();
		requstObj.setVersion("1.0.0");
		requstObj.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		List<String> productSKUCodes = new ArrayList<String>();
		productSKUCodes.add("020400185911001");
		request.setProductSKUCodes(productSKUCodes);
		List<String> productCodes=new ArrayList<String>();
		productCodes.add("020400185911");
		request.setProductCodes(productCodes);
		requstObj.setRequestParams(request);
		ResponseObject response = productClient.getProductInfoByCodes(requstObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	public void getMallProductInfo(){
		RequestObject requstObj =  new RequestObject();
		RequestProductInfoPO request = new RequestProductInfoPO();
		request.setProductCode("040400185912");
		requstObj.setVersion("1.0.0");
		requstObj.setBusinessType("02");
		requstObj.setRequestParams(request);
		ResponseObject response = productClient.getProductInfoDetail(requstObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	
	public void getMallProductInfoBySKUCode(){
		RequestObject requstObj =  new RequestObject();
		RequestProductInfoPO request = new RequestProductInfoPO();
		requstObj.setVersion("1.0.0");
		requstObj.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		List<String> productSKUCodes = new ArrayList<String>();
		productSKUCodes.add("040300185877001");
		request.setProductSKUCodes(productSKUCodes);
		requstObj.setRequestParams(request);
		ResponseObject response = productClient.getProductInfoForSKU(requstObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	 
	public static void main(String[] args) {
		
		ProductMallViewHessianTest pt = new ProductMallViewHessianTest();
		/*** 商城 ***/
//		pt.getProductInfoByCodes();
		pt.getMallProductInfo();
//		pt.getMallProductInfoBySKUCode();
		
	}
	
}
