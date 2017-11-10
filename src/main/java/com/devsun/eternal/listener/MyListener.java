package com.devsun.eternal.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.devsun.eternal.common.Memory;
import com.devsun.tool.base.PropertiesUtil;

public class MyListener implements ServletContextListener {

	/**
	 * App启动
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String projectHost = PropertiesUtil.newInstance().getProperty("project.host");
		if(StringUtils.isNotBlank(projectHost)){
			Memory.basePath = projectHost;
		}
	}

	/**
	 * App销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
