package com.devsun.eternal.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devsun.eternal.common.Constants;
import com.devsun.eternal.entity.user.AdminTicket;
import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;
import com.devsun.tool.encryption.PwdCoderUtil;

@Controller
@RequestMapping("/admin")
public class JsonAdminLoginController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request,String username,String password,String vcode){
		if (StringUtils.isBlank(vcode)) {
			return "4"; //验证码错误
		}
		String kaptchaExpected = null;
		if ((request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY))!=null) {
			kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		}
		if (StringUtils.isNotBlank(vcode) && !vcode.equals(kaptchaExpected)) {
			return "4";  //验证码错误
		}
		
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			User user=new User();
			user.setUsername(username);
			user.setType(Constants.USER_TYPE_ADMIN);
			List<User> userList=userService.selectByUser(user);
			if(userList.size()>0){
				if(userList.get(0).getStatus()==Constants.USER_STATUS_DISABLED){
					return "2";//该用户已被禁用
				}
				if(PwdCoderUtil.userPwdCheck(username, password, userList.get(0).getPassword())){
					//记录Session
					Constants.SESSION_THREAD_LOCAL.get().setAttribute(Constants.SESSION_NAME_ADMIN, new AdminTicket(userList.get(0)));
					return "0";
				}else{
					return "3";//用户名或密码错误
				}
			}
			else{
				return "3";//用户名或密码错误
			}
		}
		return "1";
	}
	
	@RequestMapping(value = "/logout.json", method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpSession httpSession){
		httpSession.setAttribute(Constants.SESSION_NAME_ADMIN, null);
		return "0";
	}
	
}
