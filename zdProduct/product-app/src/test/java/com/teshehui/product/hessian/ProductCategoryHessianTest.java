package com.teshehui.product.hessian;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductCategoryManager;
import com.zhaidou.product.po.request.RequestProductCategoryPO;

public class ProductCategoryHessianTest {

	private static final String url ;
	private static final ProductCategoryManager brandClient;

	static
	{
		//本地
		url = "http://localhost:8080/product-app/remote/productCategory";
		brandClient =  (ProductCategoryManager)HessianClientUtil.getHessianService(url, ProductCategoryManager.class);
		
		//91
		/*url = "http://192.168.0.91:49090/remote/productBrand";
		brandClient =  (ProductCategoryManager)HessianClientUtil.getHessianService(url, ProductBrandManager.class);*/
		
		
	}
	
	
	public void getProductCategoryList(){
		RequestObject requestObj = new RequestObject();
		requestObj.setVersion("1.0.0");
		requestObj.setBusinessType("01");
		
		RequestProductCategoryPO reqProCatePO = new RequestProductCategoryPO();
		requestObj.setRequestParams(reqProCatePO);
		
		ResponseObject response = brandClient.getProductCategoryList(requestObj);
		System.out.println(JSON.toJSONString(response));
	}
	
	public static void main(String[] args) {
		ProductCategoryHessianTest pt = new ProductCategoryHessianTest();
		pt.getProductCategoryList();

	}
	

	
}
