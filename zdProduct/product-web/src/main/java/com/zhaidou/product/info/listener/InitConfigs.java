package com.zhaidou.product.info.listener;

import com.zhaidou.product.info.config.StaticDefaultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化需要的数据及配置
 * 
 * @author mingbao.wu
 *
 *         2015-4-21
 */
public class InitConfigs implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitConfigs.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			LOGGER.info("config loading...");

			StaticDefaultData.setStaticFileData();
			LOGGER.info("config load over.");
		} catch (Exception e) {
			LOGGER.error("初始化配置或定时缓存启动失败！",e);
		}
	}

}
