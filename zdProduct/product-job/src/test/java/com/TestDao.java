package com;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.zhaidou.framework.redis.RedisCache;
import com.zhaidou.product.manager.ProductInfoManager;
import com.zhaidou.product.model.ProductObjBufCondiction;


public class TestDao {

	private ApplicationContext context;
	
	private ProductInfoManager productInfoManager ;
	
	private RedisCache redisCache;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("config/spring/spring-busi-resources.xml");
		redisCache=(RedisCache)context.getBean("redisCache");
		productInfoManager = (ProductInfoManager) context.getBean("productInfoManager");
		
	}
	
	public void synWms(){
//		TestPO user =new  TestPO();
//		user.setName("1231231xxxxxx");
		try {
//			redisCache.setObject("user", user);
//			System.out.println(redisCache.getObject("user"));
//			redisCache.deleteObject("user");
//			System.out.println(redisCache.getObject("user"));
			ProductObjBufCondiction condiction = new ProductObjBufCondiction();
			condiction.setState(Integer.valueOf(0));
			condiction.setObjType(1);
//			productInfoManager.doJob(condiction);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
