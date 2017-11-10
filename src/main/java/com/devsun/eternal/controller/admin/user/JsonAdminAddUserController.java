package com.devsun.eternal.controller.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;
import com.devsun.tool.encryption.PwdCoderUtil;

/**
 * 后台 user-添加用户
 * @author devsun
 *
 */
@Controller
@RequestMapping("/admin/user")
public class JsonAdminAddUserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(String userName,String realName,String email,
			String password,Integer type){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean result = false;
		User user = new User();
		user.setUsername(userName);
		user.setRealname(realName);
		user.setEmail(email);
		user.setPassword(PwdCoderUtil.userPwdEncryption(userName, password));
		user.setType(type);
		user.setStatus(Constants.TRUE);
		user.setEmailStatus(Constants.FALSE);
		int index = userService.insert(user);
		if (index>0) {
			result = true;		//新增成功
		}
		map.put("result", result);
		return map;
	}
	
}
