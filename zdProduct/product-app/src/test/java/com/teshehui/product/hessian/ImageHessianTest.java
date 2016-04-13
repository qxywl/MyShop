package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.imageservice.model.request.GetImagePathBatchRequestPO;
import com.zhaidou.imageservice.model.request.GetImagePathRequestPO;

public class ImageHessianTest {
	private static final String url ;
	private static final ImageSearchManager imageSearch;
	static
	{
	/*	//本地
		url = "http://localhost:8080/product-app/remote/productBrand";
		brandClient =  (ProductBrandManager)HessianClientUtil.getHessianService(url, ProductBrandManager.class);*/
		
		
		//91
//		url = "http://www.t.teshehui.com:1087/imageManager";
		url = "http://www.t.teshehui.com:1092/imageManager";
		imageSearch =  (ImageSearchManager)HessianClientUtil.getHessianService(url, ImageSearchManager.class);
	}
	
	
    public void test(){
		
		GetImagePathRequestPO request = new GetImagePathRequestPO();
		
		request.setRelationCode("ACT45");
		request.setRelationType(1L);
//		request.setRelationSubCode("dd");
		RequestObject obj = new RequestObject();
		obj.setRequestParams(request);
		
		ResponseObject response = imageSearch.getImagePath(obj);
        System.out.println(JSON.toJSONString(response));
        
	}
    
  
    
    public void test1(){
    	
    	List<String> skuCodeList = new ArrayList<String>();
    	
    	skuCodeList.add("140700000002");
    	String[] skuStr = {"KR00001", "AU04128", "CN04129", "CN04130", "CN04131", "CN04132", "KR04133", "US04134", "CN04135", "KR04136", "KR04137", "BE04138", "CN04139", "DE04140", "US04141"};
    	GetImagePathBatchRequestPO batchPo = new GetImagePathBatchRequestPO();
		batchPo.setRelationCodes(Arrays.asList(skuStr));
		batchPo.setRelationType(3L);
		
		RequestObject obj = new RequestObject();
		obj.setRequestParams(batchPo);
		
		ResponseObject response = imageSearch.getImagePathBatch(obj);
		System.out.println(JSON.toJSONString(response));
    	
    }
    
    
    
    public static void main(String[] args) {
    	ImageHessianTest it = new ImageHessianTest();
    	it.test1();
	}
	

}
