package com.teshehui.product.hessian;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductAttrGroupManager;
import com.zhaidou.product.manager.ProductBrandManager;
import com.zhaidou.product.model.base.ProductBrandModel;
import com.zhaidou.product.po.base.request.RequestAttrGroupValuePO;
import com.zhaidou.product.po.base.request.RequestProductBrandPO;

public class ProductBrandHessianTest {

	private static final String url ;
	private static final ProductBrandManager brandClient;

	static
	{
	/*	//本地
		url = "http://localhost:8080/product-app/remote/productBrand";
		brandClient =  (ProductBrandManager)HessianClientUtil.getHessianService(url, ProductBrandManager.class);*/
		
		//91
		url = "http://192.168.0.91:49090/remote/productBrand";
		
		brandClient =  (ProductBrandManager)HessianClientUtil.getHessianService(url, ProductBrandManager.class);
		
		
	}
	
	public void getProductAttrValByCateCode(){
		RequestObject requestObj = new RequestObject();
		
		RequestProductBrandPO  brandPO = new RequestProductBrandPO();
		brandPO.setBrandCode("123");
		requestObj.setRequestParams(brandPO);
		ResponseObject response = brandClient.getEnableBrandByCode(requestObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	
	public void getEnableBrandByName(){
		RequestObject requestObj = new RequestObject();
		RequestProductBrandPO reqBrandPO = new RequestProductBrandPO();
		reqBrandPO.setBrandName("777");
		requestObj.setRequestParams(reqBrandPO);
//		requestObj.setVersion("1.0.0");
		ResponseObject response = brandClient.getEnableBrandByName(requestObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	public void getAllEnableProductBrandList(){
		RequestObject requestObj = new RequestObject();
		requestObj.setVersion("1.0.0");
		requestObj.setBusinessType("01");
		
		ResponseObject response = brandClient.getAllEnableProductBrandList(requestObj);
		List<ProductBrandModel> list = (List<ProductBrandModel>)response.getData();
		System.out.println(JSON.toJSONString(response));
		System.out.println(list.size());
	}
	
	
	public void getEnableProductBrandListPage(){
		RequestObject requestObj = new RequestObject();
		requestObj.setVersion("1.0.0");
		requestObj.setBusinessType("01");
		
		ResponseObject response = brandClient.getEnableProductBrandListPage(requestObj);
		List<ProductBrandModel> list = (List<ProductBrandModel>)response.getData();
		System.out.println(JSON.toJSONString(response));
		System.out.println(list.size());
	}
	
	public static void main(String[] args) {
		ProductBrandHessianTest pt = new ProductBrandHessianTest();
//		pt.getEnableBrandByName();
//		pt.getEnableProductBrandListPage();
		pt.getEnableProductBrandListPage();
	}
	

	
}
