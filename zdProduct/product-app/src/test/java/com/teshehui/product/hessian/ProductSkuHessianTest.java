package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.manager.ProductSkuManager;
import com.zhaidou.product.po.request.RequestProductMallSkuPO;

public class ProductSkuHessianTest {
	
	private static  String url="" ;
	private static  String productInfoUrl="" ;
	private static  ProductSkuManager productSkuClient=null;
	private static  ProductManager productClient;

	@Before
	public void setUp() throws Exception {
	 
		//本地
	    url = "http://192.168.0.91:49090/remote/productSku";
	    productInfoUrl = "http://192.168.0.91:49090/remote/productInfo";
	    productSkuClient =  (ProductSkuManager)HessianClientUtil.getHessianService(url, ProductSkuManager.class);
	    productClient =  (ProductManager)HessianClientUtil.getHessianService(productInfoUrl, ProductManager.class);

		//91
	// url = "http://192.168.0.91:49090/remote/productBrand";
	 /*brandClient =  (ProductBrandManager)HessianClientUtil.getHessianService(url, ProductBrandManager.class);*/
	}
	
	@Test
	public void getProductSkuViewByProdId(){
		RequestObject requestObject = new RequestObject();
		RequestProductMallSkuPO skuPO = new RequestProductMallSkuPO();
		skuPO.setProductId((long) 382);
		requestObject.setRequestParams(skuPO);
		requestObject.setVersion("1.0.0");
		ResponseObject response = productSkuClient.getProductSkuViewByProdId(requestObject);
		
		
		
		System.out.println(JSON.toJSONString(response));
	}
	
	@Test
	public void getProductSkuViewBySkuCodeList(){
		RequestObject requestObject = new RequestObject();
		RequestProductMallSkuPO skuPO = new RequestProductMallSkuPO();
		List<String> skuList = new ArrayList<String>();
		skuList.add("010700000382001");
		skuList.add("150100000002001");
		skuList.add("150100000003001");
		skuList.add("090100000516001");
		
		skuPO.setSkuList(skuList);;
		requestObject.setRequestParams(skuPO);
		requestObject.setVersion("1.0.0");
		ResponseObject response = productSkuClient.getProductSkuViewBySkuList(requestObject);
		System.out.println(JSON.toJSONString(response));
	}
	
	
	
	
}
