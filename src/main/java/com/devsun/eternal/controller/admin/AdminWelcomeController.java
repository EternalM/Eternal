package com.devsun.eternal.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminWelcomeController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("admin/welcome");
		return view;
	}
}
