package com.devsun.eternal.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseAdminController {

	/**
	 * 加载后台主界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
		if(super.getCurrUser()!=null){
			redirectHome(request, response);
			return null;
		}
		ModelAndView view = new ModelAndView("admin/login");
		return view;
	}
}
