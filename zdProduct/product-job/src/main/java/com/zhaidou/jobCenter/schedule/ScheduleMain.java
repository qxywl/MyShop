package com.zhaidou.jobCenter.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhaidou.jobCenter.utils.TSHLog4JConfigurator;


/**
 * 调度主启动类
 * 
 * @author kaili
 * 
 */
public class ScheduleMain {

	public static void main(String[] args) throws Exception {
		System.out.println("Schedule Main starting...... ");
		System.setProperty("properties.file.name", "application.properties");
		System.setProperty("log4j.configuratorClass", TSHLog4JConfigurator.class.getName());

		String[] configs = { "applicationContext-core.xml", "applicationContext-job.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(configs);

		// SystemInit systemInit = (SystemInit)context.getBean("systemInit");
		// systemInit.init();

		// 如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
		// context.getBean("startQuertz");
		// System.out.println(context);

		System.out.print("Schedule Main started... ");
	}

}
