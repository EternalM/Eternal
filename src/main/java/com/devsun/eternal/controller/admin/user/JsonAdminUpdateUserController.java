package com.devsun.eternal.controller.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
 * 后台 user-修改
 * @author devsun
 *
 */
@Controller
@RequestMapping("/admin/user")
public class JsonAdminUpdateUserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(Integer userId,String userName,String realName,String email,
			String password,Integer type){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean result = false;
		if (userId!=null) {
			int index = 0;
			User user = userService.selectById(userId);
			if (user!=null) {
				user.setRealname(realName);
				user.setEmail(email);
				if (StringUtils.isNotEmpty(password)) {
					user.setPassword(PwdCoderUtil.userPwdEncryption(user.getUsername(), password));
				}
				user.setType(type);
				index = userService.update(user);
			}
			if (index>0) {
				result = true;
			}
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 修改状态  （使用/禁用）
	 * @return
	 */
	@RequestMapping(value = "/revise_status.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> revise_status(Integer userId,Integer userStatus){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean result = false;
		if (userId!=null && userId!=0 && userStatus!=null) {
			User user = userService.selectById(userId);
			int index = 0;
			if(user!=null){
				if (userStatus==Constants.USER_STATUS_DISABLED) {
					user.setStatus(Constants.USER_STATUS_ENABLED);
				}else{
					user.setStatus(Constants.USER_STATUS_DISABLED);
				}
				index = userService.update(user);
			}
			if (index>0) {
				result = true;
			}
		}
		map.put("result", result);
		return map;
	}
	
}
