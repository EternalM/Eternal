package com.devsun.eternal.controller.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.service.user.IUserService;

@Controller
@RequestMapping("/admin/user")
public class JsonAdminDelUserController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 后台 批量删除
	 * @return
	 */
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userDel(String delete_id){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean result =false;
		String[] IdList = delete_id.split(",");
		if (IdList!=null & IdList.length>0) {
			for (String string : IdList) {
				Integer uInteger = Integer.valueOf(string);
				userService.delete(uInteger);
				result = true;
			}
		}else{
			result = false;
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 后台 删除（单条）
	 * @return
	 */
	@RequestMapping(value = "/delete_by_id.json", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userDelById(Integer delete_id){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean result = false;
		if (delete_id!=null) {
			int index = userService.delete(delete_id);
			if (index>0) {
				result = true;
			}
		}
		map.put("result", result);
		return map;
	}
	
}
