package com.devsun.eternal.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;

@Controller
@RequestMapping("/")
public class JsonUserCheckController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 验证用户名重复
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/username_is_exist.json", method = RequestMethod.POST)
	@ResponseBody
	public boolean usernameIsExist(String username,Integer id){
		boolean result = true;
		//result 为 false 重复
		if (StringUtils.isNotBlank(username)) {
			User user=new User();
			user.setUsername(username);
			List<User> list=userService.selectByUser(user);
			if (list.size()>0) {
				Integer getId = list.get(0).getId();
				if (id==null) {
					result = false;
				}else if (getId.equals(id)) {
					result = true;
				}else{
					result = false;
				}
			}
		}
		return result;
	}
	
	/**
	 * 验证邮箱重复
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/email_is_exist.json", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailIsExist(String email,Integer id){
		boolean result = true;
		//result 为 false 重复
		if (StringUtils.isNotBlank(email)) {
			User user=new User();
			user.setEmail(email);
			List<User> list=userService.selectByUser(user);
			if (list.size()>0) {
				Integer getId = list.get(0).getId();
				if (id==null) {
					result = false;
				}else if (getId.equals(id)) {
					result = true;
				}else{
					result = false;
				}
			}
		}
		return result;
	}
	
}
