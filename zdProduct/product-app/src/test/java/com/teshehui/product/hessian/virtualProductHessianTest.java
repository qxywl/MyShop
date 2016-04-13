package com.teshehui.product.hessian;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.util.http.HessianClientUtil;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.manager.VirtualProductManager;

public class virtualProductHessianTest {
	private ProductManager productManager;

	/*@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"config/spring/spring-config-jdbc.xml","config/spring/spring-busi-resources.xml"});
		productManager = context.getBean(ProductManagerImpl.class);

	}*/
	

	private static VirtualProductManager virtualProductManager;
	
	private static String virtualProductManagerUrl;
	@Before
	public void setUp() throws Exception {
	 
		
		virtualProductManagerUrl="http://192.168.0.91:49090/remote/virtualProduct";
			
		virtualProductManager =  (VirtualProductManager)HessianClientUtil.getHessianService(virtualProductManagerUrl, VirtualProductManager.class);

	
	
	}

	/**
	 * 没有内容
	 */
	@Test
	public void getVirtualProductCodes() {
		RequestObject requestObj = new RequestObject();
		
		List<String> codeList = new ArrayList<String>();
		codeList.add("950100000002");
		codeList.add("150100000003");
		codeList.add("150100000005");
		codeList.add("150100000001");
		codeList.add("150100000006");
		codeList.add("070700000009");
		codeList.add("1111");
		
		requestObj.setRequestParams(codeList);
		Object result=virtualProductManager.getVirtualProductByCodes(requestObj);
		System.out.println(JSON.toJSON(result));
	}

}
