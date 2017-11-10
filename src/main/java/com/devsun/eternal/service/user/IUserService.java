package com.devsun.eternal.service.user;

import java.util.List;

import com.devsun.eternal.model.user.User;
import com.devsun.framework.entity.PageResult;
import com.devsun.framework.service.IBaseService;

public interface IUserService extends IBaseService<User> {
	
	/**
	 * 查找实体  获得集合
	 * @param user
	 * @return
	 */
	public List<User> selectByUser(User user);
	
	/**
     * 根据条件分页查询
     * @param user
     * @param page
     * @param rows
     * @return
     */
	public PageResult<User> selectByUser(User user, int page, int rows);
}
