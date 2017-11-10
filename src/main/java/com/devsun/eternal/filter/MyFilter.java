package com.devsun.eternal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.entity.user.AdminTicket;

public class MyFilter implements Filter {

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Constants.SESSION_THREAD_LOCAL.set(session);
		// 获取地址
		String curUrl = request.getServletPath();
		//忽略列表
		if(ignore(curUrl)){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		//后台管理
		if(curUrl.indexOf("/admin")==0){
			if(session.getAttribute(Constants.SESSION_NAME_ADMIN)!=null){
				AdminTicket adminTicket = (AdminTicket)session.getAttribute(Constants.SESSION_NAME_ADMIN);
				
			}
			else{
				String path = request.getContextPath();
				response.sendRedirect(path+"/admin/login");
				return;
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		
	}
	
	/**
	 * 登录前忽略列表
	 * @param curUrl
	 * @return
	 */
	private boolean ignore(String curUrl){
		//静态文件
		if(
				(curUrl.endsWith(".js"))
				|| (curUrl.endsWith(".css"))
				|| (curUrl.endsWith(".swf")) 
				|| (curUrl.endsWith(".gif")) 
				|| (curUrl.endsWith(".jpg"))
				|| (curUrl.endsWith(".png")) 
				|| (curUrl.endsWith(".html")) 
				|| (curUrl.endsWith(".htm"))
				|| (curUrl.endsWith(".woff")) 
				|| (curUrl.endsWith(".ttf")) 
				|| (curUrl.endsWith(".svg"))
				|| (curUrl.endsWith(".eot")) 
				){
			return true;
		}
		//后台管理相关
		else if(curUrl.startsWith("/admin/login")//登录相关，包含/admin/login.json
				){
			return true;
		}
		return false;
	}
	
}
