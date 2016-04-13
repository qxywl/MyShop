package com.teshehui.product;

import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.product.manager.ProductManager;
import com.zhaidou.product.manager.VirtualProductManager;
import com.zhaidou.product.manager.impl.ProductManagerImpl;
import com.zhaidou.product.manager.impl.VirtualProductManagerImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class productPriceLadderTest {
	private ProductManager productManager;
    private VirtualProductManager virtualProductManager;
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"config/spring/spring-config-jdbc.xml","config/spring/spring-busi-resources.xml"});
		productManager = context.getBean(ProductManagerImpl.class);
		virtualProductManager = context.getBean(VirtualProductManagerImpl.class);

	}

	/**
	 * 没有内容
	 */
	@Test
	public void getProductPriceLadderCodeList() {
		RequestObject requestObj = new RequestObject();
		
		List<String> codeList = new ArrayList<String>();
		codeList.add("150100000001");
		codeList.add("150100000006");
		codeList.add("070800000008");
		codeList.add("070700000012");
		codeList.add("130600000022");
		codeList.add("050100000027");
		codeList.add("1111");
		
		requestObj.setRequestParams(codeList);
		Object result=productManager.getProductPriceLadderByCodes(requestObj);		
	}
	
	
	/**
	 * 没有内容
	 */
	@Test
	public void getProductMallViewByProductCodes() {
		RequestObject requestObj = new RequestObject();
		
		List<String> codeList = new ArrayList<String>();
		codeList.add("150100000002");
		codeList.add("150100000003");
		codeList.add("150100000005");
		codeList.add("150100000001");
		codeList.add("150100000006");
		codeList.add("070700000009");
		codeList.add("1111");
		
		requestObj.setRequestParams(codeList);
		Object result=productManager.getProductIsPayForExpensiveByCodes(requestObj);
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
//		System.out.println(JSON.toJSON(result));
	}

}
