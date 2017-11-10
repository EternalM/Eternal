package com.devsun.eternal.task;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.devsun.eternal.service.user.IUserService;
import com.devsun.eternal.service.user.impl.UserServiceImpl;
import com.devsun.framework.util.SpringContextUtil;

/**
 * 定时任务
 * @author dongminghao
 *
 */
public class JobTask extends QuartzJobBean {
	
	private static Logger logger = Logger.getLogger(JobTask.class);
	
	private IUserService userService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("***************************定时任务开始***************************");
		run();
		logger.info("***************************定时任务结束***************************");
	}
	
	private void run(){
		userService = (UserServiceImpl) SpringContextUtil.getBean("userServiceImpl");
	}
	
}
