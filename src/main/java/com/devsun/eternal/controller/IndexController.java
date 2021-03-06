package com.devsun.eternal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class IndexController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		return execute(request, response);
	}
	
}
