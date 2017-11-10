package com.devsun.eternal.service.http;

import com.devsun.eternal.model.http.HttpResult1;
import com.devsun.framework.entity.PageResult;
import com.devsun.framework.service.IBaseService;

public interface IHttpResult1Service extends IBaseService<HttpResult1> {
	
	public HttpResult1 selectByUuid(String uuid);
	
	public PageResult<HttpResult1> select(HttpResult1 httpResult1, int page, int rows);
	
}
