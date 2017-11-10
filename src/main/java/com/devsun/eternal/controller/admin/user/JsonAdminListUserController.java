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
import com.devsun.framework.entity.PageResult;

@Controller
@RequestMapping("/admin/user")
public class JsonAdminListUserController {
	
	@Autowired
	private IUserService userService;
	
	public static int PAGESIZE = 10;			//限定每页行数

	/**
	 * 后台 用户 列表
	 * @return
	 */
	@RequestMapping(value = "/index.json", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userList(String like_username,Integer pageIndex){
		Map<String, Object> map = new HashMap<String,Object>();
		User user = new User();
		user.setUsername(like_username);
		PageResult<User> pageResult = userService.selectByUser(user, pageIndex, PAGESIZE);
		if(pageResult.getTotal()!=0){
			for(User temp : pageResult.getResult()){
				temp.clean();
			}
		}
		map.put("userList", pageResult);
		return map;
	}
	
}
