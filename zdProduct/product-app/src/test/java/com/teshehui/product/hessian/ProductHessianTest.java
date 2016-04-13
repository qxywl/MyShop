package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.po.request.RequestProductInfoPO;
import com.zhaidou.product.util.ProductConstantUtil;

public class ProductHessianTest {

	private static ProductManager productClient;
	private static String url;

	static {
		url = "http://localhost:8080/product-app/remote/productInfo";
		productClient = (ProductManager) HessianClientUtil.getHessianService(
				url, ProductManager.class);
	}

	public void getMallProductList() {
		RequestObject requstObj = new RequestObject();

		RequestProductInfoPO requestPO = new RequestProductInfoPO();
		requstObj
				.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);

		List<String> skuCodes = new ArrayList<String>();
		skuCodes.add("WUH,SHA,1434902400,MU2509,4,F,F,0,0");
		requestPO.setProductSKUCodes(skuCodes);
		requstObj.setVersion("1.0.0");

		requstObj.setRequestParams(requestPO);

		ResponseObject response = productClient.getProductInfoForSKU(requstObj);
		System.out.println(JSON.toJSONString(response));
	}

	public void getMallProductInfoDetail() {

		RequestObject requstObj = new RequestObject();

		RequestProductInfoPO po = new RequestProductInfoPO();
		// po.setProductCode("20");
		po.setProductCode("272");
		requstObj.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		requstObj.setVersion("1.0.0");

		requstObj.setRequestParams(po);

		ResponseObject result = productClient.getProductInfoDetail(requstObj);
		System.out.println(JSON.toJSONString(result));
	}

	public void getMallProductInfoForSKU() {
		RequestObject requstObj = new RequestObject();
		requstObj.setBusinessType(ProductConstantUtil.BIZ_PRODUCT_MALL_CODE);
		requstObj.setVersion("1.0.0");

		RequestProductInfoPO po = new RequestProductInfoPO();
		List<String> skuCodes = new ArrayList<String>();
		skuCodes.add("20");
		skuCodes.add("21");
		skuCodes.add("22");
		po.setProductSKUCodes(skuCodes);

		requstObj.setRequestParams(po);
		ResponseObject result = productClient.getProductInfoForSKU(requstObj);
		System.out.println(JSON.toJSONString(result));
	}

	public static void main(String[] args) {
		ProductHessianTest pt = new ProductHessianTest();

		// 商城
		pt.getMallProductList();// 190100100369
		pt.getMallProductInfoDetail();
		pt.getMallProductInfoForSKU();

	}
}
