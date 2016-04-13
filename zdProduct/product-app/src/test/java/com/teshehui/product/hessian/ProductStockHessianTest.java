package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.stock.manager.ProductStockManager;
import com.zhaidou.product.stock.po.request.GetStockRequestPO;
import com.zhaidou.product.stock.po.response.ProductStockRemainResponse;

public class ProductStockHessianTest {
	private static final String url ;
	private static final ProductStockManager productStockManager;

	static
	{
		//本地
		url = "http://121.42.206.45:6080/remote/productStock";
		productStockManager =  (ProductStockManager)HessianClientUtil.getHessianService(url, ProductStockManager.class);
		
 	}
	
	public static void main(String[] args) {
		RequestObject managerRequestPO = new RequestObject();
		managerRequestPO.setBusinessType("01");
		managerRequestPO.setClientType("APP");
		managerRequestPO.setVersion("1.0.0");
		List<GetStockRequestPO> requestParams = new ArrayList<GetStockRequestPO>();
		GetStockRequestPO getStockRequestPO = new GetStockRequestPO();
		getStockRequestPO.setSkuCode("040300185877001");
		requestParams.add(getStockRequestPO);
		managerRequestPO.setRequestParams(requestParams);
		ResponseObject productStockManagerResponsePO = productStockManager.getStockList(managerRequestPO);
		Map<String,ProductStockRemainResponse> map = (Map<String,ProductStockRemainResponse>) productStockManagerResponsePO.getData();
        System.out.println(map.get("040300185877001").getEntityStock());
	}
	
}
