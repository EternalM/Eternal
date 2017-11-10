package com.devsun.eternal.test.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;
import com.devsun.eternal.test.base.BaseJunitTest;
import com.devsun.framework.entity.PageResult;

public class UserTest extends BaseJunitTest {

	@Autowired
	private IUserService userService;
	
	@Test
	public void testuser(){
		PageResult<User> page = userService.selectByUser(new User(), 1, 1);
		if(page!=null && page.getTotal()>0){
			System.out.println(page.getResult().get(0).getUsername());
		}
		else{
			System.out.println("没有数据");
		}
	}
	
}
