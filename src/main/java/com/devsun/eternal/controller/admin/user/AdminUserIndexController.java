package com.devsun.eternal.controller.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user")
public class AdminUserIndexController {
	
	/**
	 * 加载用户 user 主界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView exec_user(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("admin/user/user_index");
		return view;
	}
	
}
