package com.devsun.eternal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class BaseController {
	
	protected void printText(HttpServletResponse response, String text){
		try {
			response.reset();
			response.setContentType("text/plain;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
