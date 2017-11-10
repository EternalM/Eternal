package com.devsun.eternal.service.http.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsun.eternal.model.http.HttpResult1;
import com.devsun.eternal.service.http.IHttpResult1Service;
import com.devsun.framework.entity.PageResult;
import com.devsun.framework.service.impl.BaseServiceImpl;
import com.devsun.tool.base.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class HttpResult1ServiceImpl extends BaseServiceImpl<HttpResult1> implements IHttpResult1Service {
	
	@Override
	public HttpResult1 selectByUuid(String uuid) {
		if (StringUtil.isBlank(uuid)) {
			return null;
		}
		Example example = new Example(HttpResult1.class);
        Criteria criteria = example.createCriteria();
		criteria.andEqualTo("uuid", uuid);
		List<HttpResult1> list = super.selectByExample(example);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public PageResult<HttpResult1> select(HttpResult1 httpResult1, int page, int rows) {
		Example example = new Example(HttpResult1.class);
        Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(httpResult1.getName())) {
			criteria.andLike("name", "%"+httpResult1.getName()+"%");
		}
		if (StringUtil.isNotBlank(httpResult1.getImage())) {
			criteria.andLike("image", "%"+httpResult1.getImage()+"%");
		}
		if (StringUtil.isNotBlank(httpResult1.getpUrl())) {
			criteria.andLike("pUrl", "%"+httpResult1.getpUrl()+"%");
		}
		if (StringUtil.isNotBlank(httpResult1.getOrderByClause())) {
			example.setOrderByClause(httpResult1.getOrderByClause());
		}
		return super.selectByExample(example, page, rows);
	}

}
