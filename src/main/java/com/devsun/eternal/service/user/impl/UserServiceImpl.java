package com.devsun.eternal.service.user.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.devsun.eternal.model.user.User;
import com.devsun.eternal.service.user.IUserService;
import com.devsun.framework.entity.PageResult;
import com.devsun.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	
	@Override
	public List<User> selectByUser(User user) {
		Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(user.getUsername())) {
			criteria.andEqualTo("username", user.getUsername());
		}
		if(StringUtils.isNotEmpty(user.getEmail())){
			criteria.andEqualTo("email",user.getEmail());
		}
		if(user.getType()!=null){
			criteria.andEqualTo("type",user.getType());
		}
//		if(user.getObjParams().get("like_username")!=null && StringUtils.isNotEmpty(user.getObjParams().get("username").toString())){
//			criteria.andEqualTo("username", user.getUsername());
//		}
		return super.selectByExample(example);
	}
	
	@Override
    public PageResult<User> selectByUser(User user, int page, int rows) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(user.getUsername())) {
			criteria.andLike("username", "%" + user.getUsername() + "%");
		}
		//排序
		if(StringUtils.isNotBlank(user.getOrderByClause())){
			example.setOrderByClause(user.getOrderByClause());
		}
		return super.selectByExample(example, page, rows);
    }

}
