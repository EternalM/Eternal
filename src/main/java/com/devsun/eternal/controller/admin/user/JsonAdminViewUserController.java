package com.devsun.eternal.controller.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;

/**
 * 后台 user-详情
 * @author devsun
 *
 */
@Controller
@RequestMapping("/admin/user")
public class JsonAdminViewUserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/view.json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addUser(Integer userId){
		Map<String, Object> map = new HashMap<String,Object>();
		if (userId!=null) {
			User user = userService.selectById(userId);
			if (user!=null) {
				map.put("user", user.clean());
			}
		}
		return map;
	}
}
