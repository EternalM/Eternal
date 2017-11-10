package com.devsun.eternal.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.entity.user.AdminTicket;

public class BaseAdminController {

	/**
	 * 获取当前登录用户
	 * @return
	 */
	protected AdminTicket getCurrUser(){
		if(Constants.SESSION_THREAD_LOCAL.get().getAttribute(Constants.SESSION_NAME_ADMIN)!=null){
			return (AdminTicket)Constants.SESSION_THREAD_LOCAL.get().getAttribute(Constants.SESSION_NAME_ADMIN);
		}
		return null;
	}
	
	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	protected int getCurrUserId(){
		AdminTicket ticket = getCurrUser();
		if(ticket!=null){
			return ticket.getId();
		}
		return 0;
	}
	
	/**
	 * 返回主页
	 * @param request
	 * @param response
	 */
	protected void redirectHome(HttpServletRequest request, HttpServletResponse response){
		String path = request.getContextPath();
		try {
			response.sendRedirect(path+"/admin");
		} catch (IOException e) {
			
		}
	}
	
}
