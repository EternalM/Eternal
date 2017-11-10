package com.devsun.eternal.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devsun.eternal.common.Constants;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

	/**
	 * 加载后台主界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("admin/index");
		//当前登录用户
		if(Constants.SESSION_THREAD_LOCAL.get().getAttribute(Constants.SESSION_NAME_ADMIN)!=null){
			view.addObject("login", Constants.SESSION_THREAD_LOCAL.get().getAttribute(Constants.SESSION_NAME_ADMIN));
		}
		return view;
	}
	
}
