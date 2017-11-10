package com.devsun.eternal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.tool.base.I18nUtil;

/**
 * 用于js国际化
 * @author eternal
 *
 */
@Controller
public class JsonI18nController {
	
	@RequestMapping(value = "/get_i18n.json", method = RequestMethod.POST)
	@ResponseBody
	public String getI18n(String name){
		return I18nUtil.getI18nPropertiesText(name);
	}
	
}
